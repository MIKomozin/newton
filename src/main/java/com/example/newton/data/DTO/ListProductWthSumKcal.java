package com.example.newton.data.DTO;

import com.example.newton.data.Entity.ListProduct;

public class ListProductWthSumKcal {

    private ListProduct listProduct;
    private int sumKcal;

    public ListProduct getListProduct() {
        return listProduct;
    }

    public void setListProduct(ListProduct listProduct) {
        this.listProduct = listProduct;
    }

    public int getSumKcal() {
        return sumKcal;
    }

    public void setSumKcal(int sumKcal) {
        this.sumKcal = sumKcal;
    }
}
