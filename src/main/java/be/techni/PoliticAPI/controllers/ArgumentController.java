package be.techni.PoliticAPI.controllers;

import be.techni.PoliticAPI.models.dto.ArgumentDTO;
import be.techni.PoliticAPI.models.forms.ArgumentForm;
import be.techni.PoliticAPI.services.impl.ArgumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<?> addArgument(@RequestBody @Valid ArgumentForm form, BindingResult result) {
        if (result.hasErrors())
            return ResponseEntity.badRequest().body(result.getAllErrors());

        return argumentService.addArgument(form, result);
    }
}
