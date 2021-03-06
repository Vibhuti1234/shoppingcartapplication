package com.mindtree.cartapplication.entity;

import java.util.ArrayList;
import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Cart implements Comparable<Cart> {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartId;
    @Column(columnDefinition = "int default 0")
    private int cartQuantity;
    @Column(columnDefinition = "int default 0")
    private float cartPrice;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart")
    private List<Product> productList=new ArrayList<>();


    public int getCartId() {
        return cartId;
    }


    public void setCartId(int cartId) {
        this.cartId = cartId;
    }


    public int getCartQuantity() {
        return cartQuantity;
    }


    public void setCartQuantity(int cartQuantity) {
        this.cartQuantity = cartQuantity;
    }


    public float getCartPrice() {
        return cartPrice;
    }


    public void setCartPrice(float cartPrice) {
        this.cartPrice = cartPrice;
    }


    public List<Product> getProductList() {
        return productList;
    }


    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + cartId;
        result = prime * result + Float.floatToIntBits(cartPrice);
        result = prime * result + cartQuantity;
        result = prime * result + ((productList == null) ? 0 : productList.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cart other = (Cart) obj;
        if (cartId != other.cartId)
            return false;
        if (Float.floatToIntBits(cartPrice) != Float.floatToIntBits(other.cartPrice))
            return false;
        if (cartQuantity != other.cartQuantity)
            return false;
        if (productList == null) {
            if (other.productList != null)
                return false;
        } else if (!productList.equals(other.productList))
            return false;
        return true;
    }


    @Override
    public int compareTo(Cart cart) {
        
        if(cartPrice==cart.getCartPrice())
        {
            return 0;
        }
        else if(cartPrice>cart.getCartPrice())
        {
            return 1;
        }
        else
            return -1;
    }


}
 


















