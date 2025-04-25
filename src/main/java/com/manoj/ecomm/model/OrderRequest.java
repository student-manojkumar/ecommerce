package com.manoj.ecomm.model;

import java.util.Map;

public class OrderRequest {

    //key-product id
    //value- qunatity

    private Map<Long,Integer> productQuantities;

    private double totalAmount;

    public void setProductQuantities(Map<Long, Integer> productQuantities) {
        this.productQuantities = productQuantities;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Map<Long, Integer> getProductQuantities() {
        return productQuantities;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}
