package com.example.newton.Controller;

import com.example.newton.data.DTO.ListProductDTO;
import com.example.newton.data.DTO.ProductDTO;
import com.example.newton.data.DTO.ProductToListDTO;
import com.example.newton.data.Entity.ListProduct;
import com.example.newton.data.Entity.Product;
import com.example.newton.Service.AppService;
import com.example.newton.data.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AppController {

    private final AppService appService;

    @Autowired
    public AppController(AppService appService) {
        this.appService = appService;
    }

    @GetMapping("/get/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(appService.getAllProducts());
    }

    @GetMapping("/get/listProduct")
    public ResponseEntity<ListProduct> getListProductById(@RequestParam("id") int id) {
        return ResponseEntity.ok(appService.getListProductById(id));
    }

    @PostMapping(value = "/post/addProduct")
    public ResponseEntity<ApiResponse<Product>> addProduct(@RequestBody ProductDTO productDTO) {
        Product product = appService.addProduct(productDTO.getName(), productDTO.getDescription(), productDTO.getKcal());
        ApiResponse<Product> response = new ApiResponse<>();
        if (product != null) {
            response.setDebugMessage("Продукт добавлен в БД");
            response.setStatus(HttpStatus.OK);
        } else {
            response.setDebugMessage("Продукт с таким именем уже существует");
            response.setStatus(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/post/addListProduct")
    public ResponseEntity<ApiResponse<ListProduct>> addList(@RequestBody ListProductDTO listProductDTO) {
        ListProduct listProduct = appService.addList(listProductDTO.getName());
        ApiResponse<ListProduct> response = new ApiResponse<>();
        if (listProduct != null) {
            response.setDebugMessage("Список добавлен в БД");
            response.setStatus(HttpStatus.OK);
        } else {
            response.setDebugMessage("Список с таким именем уже существует");
            response.setStatus(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/post/addProductToList")
    public ResponseEntity<ApiResponse<ListProduct>> addProductToList(@RequestBody ProductToListDTO productToListDTO) {
        ListProduct listProduct = appService.addProductToList(productToListDTO.getProductName(), productToListDTO.getListName());
        ApiResponse<ListProduct> response = new ApiResponse<>();
        if (listProduct != null) {
            response.setDebugMessage("Продукт добавлен в существующий список");
            response.setStatus(HttpStatus.OK);
        } else {
            response.setDebugMessage("Проверьте правильность введенных данных");
            response.setStatus(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(response);
    }
}
