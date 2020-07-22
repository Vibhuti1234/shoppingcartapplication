package com.mindtree.cartapplication.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;


import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NamedNativeQueries({
        @NamedNativeQuery(name = "product.findByProductName", query = "select p from Product p where p.productName=?"),
        @NamedNativeQuery(name = "product.findByProductType", query = "select p from Product p where p.category=?"), })
public class Product implements Comparable<Product>{


    @Id
    private int productId;
    @Column(unique=true)
    private String productName;
    @Column(columnDefinition = "float default 0.0")
    private float productPrice;
    @Column(columnDefinition = "int default 0")
    private int productQuantity;
    @ManyToOne(cascade = CascadeType.ALL)
    private Cart cart;



	public Product(int productId, String productName, float productPrice, int productQuantity, Cart cart) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productQuantity = productQuantity;
		this.cart = cart;
	}


	public Product() {
		super();
		// TODO Auto-generated constructor stub
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


    @JsonIgnore
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
        Product other = (Product) obj;
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
    public int compareTo(Product product) {
        
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
 



















