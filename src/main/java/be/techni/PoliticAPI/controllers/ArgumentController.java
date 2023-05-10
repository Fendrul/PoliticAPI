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
    public List<ArgumentDTO> getArgumentList(@PathVariable("id") int listLength) {
        return argumentService.getListLastArguments(listLength);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addArgument(@RequestBody @Validated ArgumentForm form, BindingResult result) {
        if (result.hasErrors())
            return ResponseEntity.badRequest().body(result.getAllErrors());

        return argumentService.addArgument(form, result);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<?> updateArgument(Authentication auth, @RequestBody @Valid ArgumentModificationForm form, BindingResult result, @PathVariable("id") int argumentId) {
        if (result.hasErrors())
            ResponseEntity.badRequest().body(result.getAllErrors());

        String username = auth.getName();

        argumentService.updateArgument(form, argumentId, username);

        return ResponseEntity.ok().build();
    }
}
