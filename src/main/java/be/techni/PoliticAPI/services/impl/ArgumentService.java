package be.techni.PoliticAPI.services.impl;

import be.techni.PoliticAPI.exceptions.RessourceNotFound;
import be.techni.PoliticAPI.jwt.JwtProvider;
import be.techni.PoliticAPI.models.dto.ArgumentDTO;
import be.techni.PoliticAPI.models.entities.Argument;
import be.techni.PoliticAPI.models.entities.Category;
import be.techni.PoliticAPI.models.entities.Source;
import be.techni.PoliticAPI.models.entities.User;
import be.techni.PoliticAPI.models.forms.ArgumentForm;
import be.techni.PoliticAPI.models.forms.ArgumentModificationForm;
import be.techni.PoliticAPI.repositories.ArgumentRepository;
import be.techni.PoliticAPI.repositories.CategoryRepository;
import be.techni.PoliticAPI.repositories.SourceRepository;
import be.techni.PoliticAPI.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArgumentService {

    private final ArgumentRepository argumentRepo;
    private final SourceRepository sourceRepo;
    private final CategoryRepository categoryRepo;
    private final UserRepository clientRepo;

    @Autowired
    public ArgumentService(ArgumentRepository argumentRepository, SourceRepository sourceRepo, CategoryRepository categoryRepo, UserRepository clientRepo, JwtProvider jwtProvider) {
        this.argumentRepo = argumentRepository;
        this.sourceRepo = sourceRepo;
        this.categoryRepo = categoryRepo;
        this.clientRepo = clientRepo;
        this.jwtProvider = jwtProvider;
    }

    public List<ArgumentDTO> getListLastArguments(int listLength) {
        List<Argument> lastArguments = argumentRepo.findLastArguments(listLength);

        return lastArguments.stream()
                .map(ArgumentDTO::fromEntity)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ResponseEntity<?> addArgument(ArgumentForm form, BindingResult result) {
        Argument newArgument = new Argument();

        newArgument.setTitle(form.getTitle());
        newArgument.setDescription(form.getDescription());

        User user = clientRepo.findById(form.getClientId())
                .orElseGet(() -> {
                    result.rejectValue("clientId", "argument.client.invalid", "Client ID %d not found".formatted(form.getClientId()));
                    return null;
                });

        if (user != null) {
            newArgument.setAuthor(user);
        }

        if (form.getAnswerTo() != null) {
            Argument answerTo = argumentRepo.findById(form.getAnswerTo())
                    .orElseGet(() -> {
                        result.rejectValue("answerTo", "argument.answerTo.invalid", "Argument not found");
                        return null;
                    });

            if (answerTo != null) {
                newArgument.setAnswerTo(answerTo);
            }
        }

        for (Long categoryId : form.getCategoriesId()) {
            Category categoryToAdd = categoryRepo.findById(categoryId)
                    .orElseGet(() -> {
                        result.rejectValue("categoriesId", "argument.category.invalid", "Category ID %d not found".formatted(categoryId));
                        return null;
                    });

            if (categoryToAdd != null) {
                categoryRepo.save(categoryToAdd);
                newArgument.getCategories().add(categoryToAdd);
            }
        }

        for (String source : form.getSources()) {
            Source sourceToAdd = sourceRepo.findByDescription(source)
                    .orElseGet(() -> {
                        Source newSource = new Source();
                        newSource.setDescription(source);
                        sourceRepo.save(newSource);
                        return newSource;
                    });

            sourceToAdd.setDescription(source);
            sourceRepo.save(sourceToAdd);
            newArgument.getSources().add(sourceToAdd);
        }


        if (result.hasErrors())
            return ResponseEntity.badRequest().body(result.getAllErrors());

        argumentRepo.save(newArgument);
        return ResponseEntity.ok().build();
    }

    public void addCategoryToArgument(Argument argument, int... categoriesID) {
        List<Category> categories = new ArrayList<>();

        for (long categoryId : categoriesID) {
            Category categoryToAdd = categoryRepo.findByIdEagerFetch(categoryId)
                    .orElseThrow(() -> new RessourceNotFound("Category ID %d not found".formatted(categoryId)));

            if (categoryToAdd != null)
                categories.add(categoryToAdd);
        }

        addCategoryToArgument(argument, categories);
    }

    public void addCategoryToArgument(Argument argument, String... categoriesName) {
        List<Category> categories = new ArrayList<>();

        for (String categoryName : categoriesName) {
            Category categoryToAdd = categoryRepo.findByNameEagerFetch(categoryName)
                    .orElseThrow(() -> new RessourceNotFound("Category %s not found".formatted(categoryName)));

            if (categoryToAdd != null) {
                categories.add(categoryToAdd);
            }
        }

        addCategoryToArgument(argument, categories);
    }

    public void addCategoryToArgument(Argument argument, List<Category> categories) {
        for (Category category : categories) {
            if (category != null && !argument.getCategories().contains(category)) {
                argument.getCategories().add(category);
                category.getArguments().add(argument);
                argumentRepo.save(argument);
                categoryRepo.save(category);
            }
        }
    }

    public void updateArgument(ArgumentModificationForm form, long argumentId, String userName) {
        Argument argument = argumentRepo.findById(argumentId)
                .orElseThrow(() -> new RessourceNotFound("Argument ID %d not found".formatted(argumentId)));

        User user = clientRepo.findByName(userName)
                .orElseThrow(() -> new RessourceNotFound("User %s not found".formatted(userName)));
    }
}
