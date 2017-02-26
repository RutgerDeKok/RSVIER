package product;

import java.math.BigDecimal;

public class Product {
	
	private final int productId;
	private final String productNaam;
	private final BigDecimal productPrijs;
	private final int aantalVoorraad;
	
	public Product(ProductBuilder builder ){
		
		this.productId = builder.getProductId();
		this.productNaam = builder.getProductNaam();
		this.productPrijs = builder.getPrijs();
		this.aantalVoorraad = builder.getAantalVoorraad();
	}
	
	// getters
	public int getId() {
		return productId;
	}
	public String getNaam() {
		return productNaam;
	}
	public BigDecimal getPrijs() {
		return productPrijs;
	}
	public int getAantalVoorraad() {
		return aantalVoorraad;
	}
	
	
	
	
	public static class ProductBuilder{
		
		
		private int productId;
		private String productNaam;
		private BigDecimal productPrijs;
		private int aantalVoorraad;
		
		
		public int getProductId() {
			return productId;
		}
		public String getProductNaam() {
			return productNaam;
		}
		public BigDecimal getPrijs() {
			return productPrijs;
		}
		public int getAantalVoorraad() {
			return aantalVoorraad;
		}
		
		
		public ProductBuilder productId(int productId) {
			this.productId = productId;
			return this;
		}
		public ProductBuilder productNaam(String productNaam) {
			this.productNaam = productNaam;
			return this;
		}
		public ProductBuilder prijs(BigDecimal prijs) {
			productPrijs = prijs;
			return this;
		}
		public ProductBuilder aantalVoorraad(int aantalVoorraad) {
			this.aantalVoorraad = aantalVoorraad;
			return this;
		}
		
		public Product build(){
			return new Product(this);
		}
		
	}
	


}
