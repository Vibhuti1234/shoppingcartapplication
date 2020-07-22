package com.mindtree.cartapplication.entity;

import javax.persistence.Entity;

@Entity
public class Book extends Product{
	private String genre;
	private String author;
	private String publications;
	
	
	

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Book(int productId, String productName, float productPrice, int productQuantity, Cart cart) {
		super(productId, productName, productPrice, productQuantity, cart);
		// TODO Auto-generated constructor stub
	}
	
	public Book(int productId, String productName, float productPrice, int productQuantity, Cart cart, String genre,
			String author, String publications) {
		super(productId, productName, productPrice, productQuantity, cart);
		this.genre = genre;
		this.author = author;
		this.publications = publications;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublications() {
		return publications;
	}
	public void setPublications(String publications) {
		this.publications = publications;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		result = prime * result + ((publications == null) ? 0 : publications.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (genre == null) {
			if (other.genre != null)
				return false;
		} else if (!genre.equals(other.genre))
			return false;
		if (publications == null) {
			if (other.publications != null)
				return false;
		} else if (!publications.equals(other.publications))
			return false;
		return true;
	}
	
	

}
