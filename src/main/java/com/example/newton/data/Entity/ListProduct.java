package com.example.newton.data.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "lists")
public class ListProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "VARCHAR(128) NOT NULL")
    private String name;

    //по условию задачи список может содержать любое количество продуктов
    @ManyToMany
    @JoinTable(
            name = "list2product",
            joinColumns = @JoinColumn(name = "list_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    List<Product> list2product;

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

    public List<Product> getList2product() {
        return list2product;
    }

    public void setList2product(List<Product> list2product) {
        this.list2product = list2product;
    }
}
