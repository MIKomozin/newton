package com.example.newton.Service;

import com.example.newton.data.Entity.ListProduct;
import com.example.newton.data.Entity.Product;
import com.example.newton.Repository.ListProductRepository;
import com.example.newton.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppService implements SaveService, GetService {

    private final ListProductRepository listProductRepository;
    private final ProductRepository productRepository;

    @Autowired
    public AppService(ListProductRepository listProductRepository, ProductRepository productRepository) {
        this.listProductRepository = listProductRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public ListProduct getListProductById(int id) {
        return listProductRepository.findListProductById(id);
    }

    @Override
    public Product addProduct(String name, String description, int kcal) {
        if (productRepository.findProductByName(name) == null) {
            Product product = new Product();
            product.setName(name);
            product.setDescription(description);
            product.setKcal(kcal);
            productRepository.save(product);
            return product;
        }
        return null;
    }

    @Override
    public ListProduct addList(String name) {
        if (listProductRepository.findListProductByName(name) == null) {
            ListProduct listProduct = new ListProduct();
            listProduct.setName(name);
            listProductRepository.save(listProduct);
            return listProduct;
        }
        return null;
    }

    @Override
    public ListProduct addProductToList(String productName, String listName) {
        Product product = productRepository.findProductByName(productName);
        ListProduct listProduct = listProductRepository.findListProductByName(listName);
        if (product != null && listProduct != null) {

            List<Product> list2product = listProduct.getList2product();
            list2product.add(product);
            listProduct.setList2product(list2product);
            listProductRepository.save(listProduct);

            List<ListProduct> product2list = product.getProduct2list();
            product2list.add(listProduct);
            product.setProduct2list(product2list);
            productRepository.save(product);
            return listProduct;
        }
        return null;
    }
}
