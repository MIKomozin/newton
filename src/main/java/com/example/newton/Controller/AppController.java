package com.example.newton.Controller;

import com.example.newton.data.DTO.*;
import com.example.newton.data.Entity.ListProduct;
import com.example.newton.data.Entity.Product;
import com.example.newton.Service.AppService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api("APP API")
public class AppController {

    private final AppService appService;

    @Autowired
    public AppController(AppService appService) {
        this.appService = appService;
    }

    @GetMapping("/get/products")
    @ApiOperation("GET запрос для получения списка всех имеющихся продуктов")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(appService.getAllProducts());
    }

    @GetMapping("/get/list")
    @ApiOperation("GET запрос для получения определенного списка по id с содержащимися в нем продуктами")
    public ResponseEntity<ListProduct> getListProductById(@RequestParam("id") int id) {
        return ResponseEntity.ok(appService.getListProductById(id));
    }

    //отдельный метод для усложненного задания
    @GetMapping("/get/listWithKcal")
    @ApiOperation("GET запрос для получения определенного списка по id с содержащимися в нем продуктами и суммой" +
            "килокаллорий данных продуктов в списке")
    public ResponseEntity<ListProductWthSumKcal> getListWithKCalSumById(@RequestParam("id") int id) {
        return ResponseEntity.ok(appService.getListProductAndSumKcalById(id));
    }

    @PostMapping(value = "/post/addProduct")
    @ApiOperation("POST запрос добавления продукта в БД")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody ProductDTO productDTO) {
        Product product = appService.addProduct(productDTO.getName(), productDTO.getDescription(), productDTO.getKcal());
        ApiResponse response = new ApiResponse();
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
    @ApiOperation("POST запрос добавления списка (без продуктов) в БД")
    public ResponseEntity<ApiResponse> addList(@RequestBody ListProductDTO listProductDTO) {
        ListProduct listProduct = appService.addList(listProductDTO.getName());
        ApiResponse response = new ApiResponse();
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
    @ApiOperation("POST запрос добавления определенного продукта (по названию) в существующий список (по наименованию)")
    public ResponseEntity<ApiResponse> addProductToList(@RequestBody ProductToListDTO productToListDTO) {
        ListProduct listProduct = appService.addProductToList(productToListDTO.getProductName(), productToListDTO.getListName());
        ApiResponse response = new ApiResponse();
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
