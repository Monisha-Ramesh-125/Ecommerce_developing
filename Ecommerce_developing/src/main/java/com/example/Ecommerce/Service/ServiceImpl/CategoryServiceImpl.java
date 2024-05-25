//package com.example.Ecommerce.Service.ServiceImpl;
//
//import com.example.Ecommerce.DTO.CategoryDto;
//import com.example.Ecommerce.Entity.Category;
//import com.example.Ecommerce.Exception.ResourceAlreadyExistsException;
//import com.example.Ecommerce.Mapper.CategoryMapper;
//import com.example.Ecommerce.Repository.CategoryRepository;
//import com.example.Ecommerce.Service.CategoryService;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CategoryServiceImpl implements CategoryService {
//    private final CategoryRepository categoryRepository;
//
//    public CategoryServiceImpl(CategoryRepository categoryRepository) {
//        this.categoryRepository = categoryRepository;
//    }
//
//
//    @Override
//    public Category save(CategoryDto categoryDto) {
//        Category category = categoryRepository.findByCategoryName(categoryDto.getCategoryName());
//        if(category!=null){
//            throw new ResourceAlreadyExistsException("The category with the name"+ categoryDto.getCategoryName()+"already exists!!!");
//
//        }
//      category = CategoryMapper.mapToCategory(categoryDto);
//        categoryRepository.save(category);
//    return category;
//    }}
//
