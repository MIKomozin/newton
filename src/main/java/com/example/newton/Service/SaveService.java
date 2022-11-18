package com.example.newton.Service;

import com.example.newton.data.Entity.ListProduct;
import com.example.newton.data.Entity.Product;

public interface SaveService {

    //добавить продукт в БД
    Product addProduct(String name, String description, int kcal);

    //добавить список в БД
    ListProduct addList(String name);

    //добовать продукт в определненный список
    ListProduct addProductToList(String productName, String listName);
}
