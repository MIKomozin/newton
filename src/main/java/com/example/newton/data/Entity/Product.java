package com.example.newton.data.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "VARCHAR(128) NOT NULL")
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "INT NOT NULL")
    private Integer kcal;

    //из условия задачи непонятно можно ли добавлять продукт в несколько списков (сделаем что можно)
    @JsonIgnore
    @ManyToMany(mappedBy = "list2product")
    List<ListProduct> product2list;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getKcal() {
        return kcal;
    }

    public void setKcal(Integer kcal) {
        this.kcal = kcal;
    }

    public List<ListProduct> getProduct2list() {
        return product2list;
    }

    public void setProduct2list(List<ListProduct> product2list) {
        this.product2list = product2list;
    }
}
