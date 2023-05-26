package be.techni.PoliticAPI.controllers;

import be.techni.PoliticAPI.models.dto.ArgumentDTO;
import be.techni.PoliticAPI.models.forms.ArgumentForm;
import be.techni.PoliticAPI.models.forms.ArgumentModificationForm;
import be.techni.PoliticAPI.services.impl.ArgumentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/argument")
@CrossOrigin("*")
public class ArgumentController {

    private final ArgumentService argumentService;

    @Autowired
    public ArgumentController(ArgumentService argumentService) {
        this.argumentService = argumentService;
    }

    @GetMapping("/list:{id}")
    public List<ArgumentDTO> getArgumentList(@PathVariable("id") long listLength) {
        return argumentService.getListLastArguments(listLength);
    }

    @GetMapping("/pending_arguments_list")
    public List<ArgumentDTO> getPendingArgumentList() {
        return argumentService.getListPendingArguments();
    }

    @GetMapping("/category/{id}")
    public List<ArgumentDTO> getArgumentFromCategoryId(@PathVariable("id") long categoryId) {
        return argumentService.getArgumentFromCategoryId(categoryId);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addArgument(Authentication auth, @RequestBody @Validated ArgumentForm form, BindingResult result) {
        if (result.hasErrors())
            return ResponseEntity.badRequest().body(result.getAllErrors());

        String username = auth.getName();

        argumentService.addArgument(form, result, username);

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/validate/{id}")
    public ResponseEntity<?> validateArgument(@PathVariable("id") long argumentId) {
        argumentService.validateArgument(argumentId);

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/refuse/{id}")
    public ResponseEntity<?> refuseArgument(@PathVariable("id") long argumentId) {
        argumentService.deleteArgument(argumentId);

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<?> updateArgument(Authentication auth, @RequestBody @Valid ArgumentModificationForm form, BindingResult result, @PathVariable("id") long argumentId) {
        if (result.hasErrors())
            ResponseEntity.badRequest().body(result.getAllErrors());

        String username = auth.getName();

        argumentService.updateArgument(form, argumentId, username);

        return ResponseEntity.ok().build();
    }
}
