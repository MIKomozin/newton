package com.example.newton.Repository;

import com.example.newton.data.Entity.ListProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListProductRepository extends JpaRepository<ListProduct, Integer> {

    ListProduct findListProductById(Integer id);
    ListProduct findListProductByName(String name);
}
