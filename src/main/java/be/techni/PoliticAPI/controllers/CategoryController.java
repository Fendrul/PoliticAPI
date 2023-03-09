package be.techni.PoliticAPI.controllers;

import be.techni.PoliticAPI.models.dto.ArgumentDTO;
import be.techni.PoliticAPI.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryServ;

    @Autowired
    public CategoryController(CategoryService categoryServ) {
        this.categoryServ = categoryServ;
    }

    @GetMapping("/id:{id}/getAll")
    public List<ArgumentDTO> getArgumentsByCategoryId(@PathVariable("id") long id) {
        return categoryServ.getArgumentsByCategoryId(id);
    }
}
