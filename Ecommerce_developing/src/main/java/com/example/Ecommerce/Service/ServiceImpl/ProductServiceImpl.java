package com.example.Ecommerce.Service.ServiceImpl;

import com.example.Ecommerce.DTO.ProductDTO;
import com.example.Ecommerce.Entity.Product;
import com.example.Ecommerce.Entity.Seller;
import com.example.Ecommerce.Exception.ResourceAlreadyExistsException;
import com.example.Ecommerce.Repository.ProductRepository;
import com.example.Ecommerce.Repository.SellerRepository;
import com.example.Ecommerce.Service.ProductService;
import com.example.Ecommerce.config.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import static com.example.Ecommerce.Mapper.ProductMapper.MaptoProductDto;

@Service
public class ProductServiceImpl implements ProductService {
    private JwtService jwtService;
    private SellerRepository userRepository;
    private ProductRepository productRepository;
    @Autowired
    public ProductServiceImpl(JwtService jwtService, SellerRepository userRepository, ProductRepository productRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }




    @Override
    public List<ProductDTO> addProduct( Product product, HttpServletRequest request) {
        String jwt = request.getHeader("Authorization").substring(7);
        String userEmail = jwtService.extractUsername(jwt);
        Optional<Seller> user = userRepository.findByEmail(userEmail);
        product.setCategory(product.getCategory());
        Optional<Product> existingProduct = productRepository.findByNameAndDescription(product.getName(), product.getDescription());
        if(product.getSku()==null){
            String categoryname = product.getCategory().name().substring(0,4);
            Random random = new Random();
            List<Product> products = productRepository.findBySeller(user.get());
            String randomDigits = String.format("%02d", random.nextInt(100));
            String username = user.get().getUsername().substring(0,2);
            String noofProducts = String.valueOf(products.size());
            String sku = categoryname + randomDigits + username + noofProducts;
            product.setSku(sku);
        }
        if (existingProduct.isPresent()) {
            throw new ResourceAlreadyExistsException("Product with  name " + product.getName() + " and" + " description " + product.getDescription() + " already exists");}
         else {
            double specialPrice = product.getPrice() - ((product.getDiscount() * 0.01) * product.getPrice());
            product.setSpecialPrice(specialPrice);
            product.setSeller(user.get());
            productRepository.save(product);
        }
         List<ProductDTO> productDTOList =  productRepository.findBySeller(user.get()).stream().map(x -> MaptoProductDto(x)).collect(Collectors.toList());
        return productDTOList;
    }
}
