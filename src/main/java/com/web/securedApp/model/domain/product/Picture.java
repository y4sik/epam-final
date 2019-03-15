package com.web.securedApp.model.domain.product;

import com.web.securedApp.model.domain.Entity;
import com.web.securedApp.model.domain.product.Product;

public class Picture extends Entity {

    private String picturePath;
    private Product productOwner;

    public Picture(){}

    public Picture(int id, String productPicture) {
        super(id);
        this.picturePath = productPicture;
    }

    public Picture(String picturePath, Product productOwner) {
        this.picturePath = picturePath;
        this.productOwner = productOwner;
    }

    public Picture(int id, String picturePath, Product productOwner) {
        super(id);
        this.picturePath = picturePath;
        this.productOwner = productOwner;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public Product getProductOwner() {
        return productOwner;
    }

    public void setProductOwner(Product productOwner) {
        this.productOwner = productOwner;
    }

    @Override
    public String toString() {
        return "Picture: " +
                "id=" + getId() +
                ", picturePath=" + picturePath;
    }
}
