package com.example.newton.Service;

import com.example.newton.data.Entity.ListProduct;
import com.example.newton.data.Entity.Product;

import java.util.List;

public interface GetService {

    //получить перечень продуктов, которые есть в БД
    List<Product> getAllProducts();

    //получить список продуктов по id
    ListProduct getListProductById(int id);
}
