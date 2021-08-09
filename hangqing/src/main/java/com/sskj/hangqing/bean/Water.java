package com.sskj.hangqing.bean;

/**
 * 创建时间: 2018/1/9
 */

public class Water {
    private float product_get;
    private String id;
    private int imgWater;

    public int getImgWater() {
        return imgWater;
    }

    public void setImgWater(int imgWater) {
        this.imgWater = imgWater;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Water(int product_get) {
        this.product_get = product_get;
    }

    public Water(int product_get, int imgWater) {
        this.product_get = product_get;
        this.imgWater = imgWater;
    }

    public float getProduct_get() {
        return product_get;
    }

    public void setProduct_get(float product_get) {
        this.product_get = product_get;
    }
}