package be.techni.PoliticAPI.controllers;

import be.techni.PoliticAPI.models.dto.ArgumentDTO;
import be.techni.PoliticAPI.services.ArgumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/argument")
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
}
