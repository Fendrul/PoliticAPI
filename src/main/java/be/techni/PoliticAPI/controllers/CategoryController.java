package be.techni.PoliticAPI.controllers;

import be.techni.PoliticAPI.models.dto.ArgumentDTO;
import be.techni.PoliticAPI.models.dto.CategoryDTO;
import be.techni.PoliticAPI.services.impl.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {

    private final CategoryService categoryServ;

    @Autowired
    public CategoryController(CategoryService categoryServ) {
        this.categoryServ = categoryServ;
    }

    @GetMapping("/id:{id}/get_all")
    public List<ArgumentDTO> getArgumentsByCategoryId(@PathVariable("id") long id) {
        return categoryServ.getArgumentsByCategoryId(id);
    }

    @GetMapping("/get_all")
    public List<CategoryDTO> getAllCategories() {
        return categoryServ.getAllCategories();
    }
}
