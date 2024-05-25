//package com.example.Ecommerce.Controller;
//
//import com.example.Ecommerce.DTO.CategoryDto;
//import com.example.Ecommerce.Entity.Category;
//import com.example.Ecommerce.Service.CategoryService;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequestMapping("/api")
//public class CategoryController {
//
//    private final CategoryService categoryService;
//
//    @Autowired
//    private CategoryController(CategoryService categoryService){
//        this.categoryService=categoryService;
//    }
//    @PostMapping("/admin/add/category")
//    private ResponseEntity<Object> addCategory(@Valid @RequestBody CategoryDto categoryDto){
//         return new ResponseEntity<>(categoryService.save(categoryDto), HttpStatus.OK);
//    }
//
//}
