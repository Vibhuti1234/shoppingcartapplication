package com.mindtree.cartapplication.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mindtree.cartapplication.entity.Product;

public class CartDto  implements Comparable<CartDto>{

   
    private int cartId;
    private int cartQuantity;
    private float cartPrice;
    @JsonIgnore
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
		CartDto other = (CartDto) obj;
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
    public int compareTo(CartDto cart) {
        
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
