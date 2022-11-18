package com.example.newton.data.Entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "lists")
@ApiModel(description = "Объект ListProduct(List)")
public class ListProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("ID объкта ListProduct. Генерируется автоматически")
    private Integer id;

    @Column(columnDefinition = "VARCHAR(128) NOT NULL")
    @ApiModelProperty("Наименование списка")
    private String name;

    //по условию задачи список может содержать любое количество продуктов
    @ApiModelProperty("Перечень продуктов входящих в данный список")
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
