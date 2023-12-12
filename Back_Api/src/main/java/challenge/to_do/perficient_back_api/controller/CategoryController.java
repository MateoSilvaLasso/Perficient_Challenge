package challenge.to_do.perficient_back_api.controller;

import challenge.to_do.perficient_back_api.repository.model.Category;
import challenge.to_do.perficient_back_api.service.ICategoryService;
import challenge.to_do.perficient_back_api.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private ICategoryService service;

    @PostMapping("")
    public Optional<Category> create(@RequestBody Category category){
        return this.service.save(category);
    }

}
