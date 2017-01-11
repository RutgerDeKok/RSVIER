package model;

import java.math.BigDecimal;

public class Product {
	
	private int productId;
	private String productNaam;
	private BigDecimal productPrijs;
	private int aantalVoorraad;
	
	public Product(int productId, String productNaam, BigDecimal productPrijs, int aantalVoorraad ){
		
		this.productId = productId;
		this.productNaam = productNaam;
		this.productPrijs = productPrijs;
		this.aantalVoorraad = aantalVoorraad;
	}
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductNaam() {
		return productNaam;
	}
	public void setProductNaam(String productNaam) {
		this.productNaam = productNaam;
	}
	public BigDecimal getPrijs() {
		return productPrijs;
	}
	public void setPrijs(BigDecimal prijs) {
		productPrijs = prijs;
	}
	public int getAantalVoorraad() {
		return aantalVoorraad;
	}
	public void setAantalVoorraad(int aantalVoorraad) {
		this.aantalVoorraad = aantalVoorraad;
	}


}
