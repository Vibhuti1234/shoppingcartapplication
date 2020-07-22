package com.mindtree.cartapplication.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mindtree.cartapplication.entity.Cart;

public class ProductDto  implements Comparable<ProductDto>{
	
	    private int productId;
	    private String productName;
	   
	    private float productPrice;
	    
	    private int productQuantity;
	    @JsonIgnore
	    private Cart cart;
	    
		public ProductDto() {
			super();
			// TODO Auto-generated constructor stub
		}
		

		public ProductDto(int productId, String productName, float productPrice, int productQuantity, Cart cart) {
			super();
			this.productId = productId;
			this.productName = productName;
			this.productPrice = productPrice;
			this.productQuantity = productQuantity;
			this.cart = cart;
		}


		public int getProductId() {
			return productId;
		}


		public void setProductId(int productId) {
			this.productId = productId;
		}


		public String getProductName() {
			return productName;
		}


		public void setProductName(String productName) {
			this.productName = productName;
		}


		public float getProductPrice() {
			return productPrice;
		}


		public void setProductPrice(float productPrice) {
			this.productPrice = productPrice;
		}


		public int getProductQuantity() {
			return productQuantity;
		}


		public void setProductQuantity(int productQuantity) {
			this.productQuantity = productQuantity;
		}


		public Cart getCart() {
			return cart;
		}


		public void setCart(Cart cart) {
			this.cart = cart;
		}


		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((cart == null) ? 0 : cart.hashCode());
			result = prime * result + productId;
			result = prime * result + ((productName == null) ? 0 : productName.hashCode());
			result = prime * result + Float.floatToIntBits(productPrice);
			result = prime * result + productQuantity;
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
			ProductDto other = (ProductDto) obj;
			if (cart == null) {
				if (other.cart != null)
					return false;
			} else if (!cart.equals(other.cart))
				return false;
			if (productId != other.productId)
				return false;
			if (productName == null) {
				if (other.productName != null)
					return false;
			} else if (!productName.equals(other.productName))
				return false;
			if (Float.floatToIntBits(productPrice) != Float.floatToIntBits(other.productPrice))
				return false;
			if (productQuantity != other.productQuantity)
				return false;
			return true;
		}


		@Override
		public int compareTo(ProductDto product) {
			// TODO Auto-generated method stub
			 
	        if(productQuantity==product.getProductQuantity())
	        {
	            return 0;
	        }
	        else if(productQuantity>product.productQuantity)
	        {
	            return 1;
	        }
	        else
	            return -1;
	    	}
}
