package be.techni.PoliticAPI.services.impl;

import be.techni.PoliticAPI.models.dto.ArgumentDTO;
import be.techni.PoliticAPI.models.dto.CategoryDTO;
import be.techni.PoliticAPI.models.entities.Argument;
import be.techni.PoliticAPI.repositories.ArgumentRepository;
import be.techni.PoliticAPI.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ArgumentRepository argumentRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, ArgumentRepository argumentRepository) {
        this.categoryRepository = categoryRepository;
        this.argumentRepository = argumentRepository;
    }

    public List<ArgumentDTO> getArgumentsByCategoryId(long id) {
        List<Argument> arguments = argumentRepository.findAllByCategoryId(id);

        return arguments.stream()
                .map(ArgumentDTO::fromEntity)
                .toList();
    }

    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(CategoryDTO::fromEntity)
                .toList();
    }
}
