package model;

import java.math.BigDecimal;
import java.sql.Date;


public class Order {
	
	private final int orderId;
	private final Date orderDatum;
	private final int medewerkerId;
	private final int klantId;
	private final int productA_Id;
	private final int productA_aantal;
	private final int productB_Id;
	private final int productB_aantal;
	private final int productC_Id;
	private final int productC_aantal;
	private final BigDecimal totaalBedrag;
	
	//constructor
	public Order(OrderBuilder builder){
	
		
		this.orderId = builder.getOrderId();
		this.orderDatum = builder.getOrderDatum();
		this.medewerkerId = builder.getMedewerkerId();
		this.klantId = builder.getKlantId();
		this.productA_Id = builder.getProductA_Id();
		this.productA_aantal = builder.getProductA_aantal();
		this.productB_Id = builder.getProductB_Id();
		this.productB_aantal = builder.getProductB_aantal();
		this.productC_Id = builder.getProductC_Id();
		this.productC_aantal = builder.getProductC_aantal();
		this.totaalBedrag = builder.getTotaalBedrag();
		
	}
	
	@Override
	public String toString() {
		int totaalProducten = productA_aantal +productB_aantal + productC_aantal;
		return String.format("id=%s, datum=%s, medew=%s, klant=%s, totProd=%s, totBedrag=%s "+"\n",
						orderId, orderDatum, medewerkerId, klantId, totaalProducten, totaalBedrag);
	}
	
	
	
	// getters

	public int getOrderId() {
		return orderId;
	}
	public Date getOrderDatum() {
		return orderDatum;
	}
	public int get1eMedewerkerId() {
		return medewerkerId;
	}
	public int getKlantId() {
		return klantId;
	}
	public int getProductA_Id() {
		return productA_Id;
	}
	public int getProductA_aantal() {
		return productA_aantal;
	}
	public int getProductB_Id() {
		return productB_Id;
	}
	public int getProductB_aantal() {
		return productB_aantal;
	}
	public int getProductC_Id() {
		return productC_Id;
	}
	public int getProductC_aantal() {
		return productC_aantal;
	}
	public BigDecimal getTotaalBedrag() {
		return totaalBedrag;
	}


	
	// Builder inner class
	public static class OrderBuilder{
		
		private int orderId;
		private Date orderDatum;
		private int medewerkerId;
		private int klantId;
		private int productA_Id;
		private int productA_aantal;
		private int productB_Id;
		private int productB_aantal;
		private int productC_Id;
		private int productC_aantal;
		private BigDecimal totaalBedrag;
		
		
		//getters
		public int getOrderId() {
			return orderId;
		}
		public Date getOrderDatum() {
			return orderDatum;
		}
		public int getMedewerkerId() {
			return medewerkerId;
		}
		public int getKlantId() {
			return klantId;
		}
		public int getProductA_Id() {
			return productA_Id;
		}
		public int getProductA_aantal() {
			return productA_aantal;
		}
		public int getProductB_Id() {
			return productB_Id;
		}
		public int getProductB_aantal() {
			return productB_aantal;
		}
		public int getProductC_Id() {
			return productC_Id;
		}
		public int getProductC_aantal() {
			return productC_aantal;
		}
		public BigDecimal getTotaalBedrag() {
			return totaalBedrag;
		}
	
		
		
		// setters
		public OrderBuilder orderId(int orderId) {
			this.orderId = orderId;
			return this;
		}
		public OrderBuilder orderDatum(Date orderDatum) {
			this.orderDatum = orderDatum;
			return this;
		}
		public OrderBuilder medewerkerId(int medewerkerId) {
			this.medewerkerId = medewerkerId;
			return this;
		}
		public OrderBuilder klantId(int klantId) {
			this.klantId = klantId;
			return this;
		}
		public OrderBuilder productA_Id(int productA_Id) {
			this.productA_Id = productA_Id;
			return this;
		}
		public OrderBuilder productA_aantal(int productA_aantal) {
			this.productA_aantal = productA_aantal;
			return this;
		}
		public OrderBuilder productB_Id(int productB_Id) {
			this.productB_Id = productB_Id;
			return this;
		}
		public OrderBuilder productB_aantal(int productB_aantal) {
			this.productB_aantal = productB_aantal;
			return this;
		}
		public OrderBuilder productC_Id(int productC_Id) {
			this.productC_Id = productC_Id;
			return this;
		}
		public OrderBuilder productC_aantal(int productC_aantal) {
			this.productC_aantal = productC_aantal;
			return this;
		}
		public OrderBuilder totaalBedrag(BigDecimal totaalBedrag) {
			this.totaalBedrag = totaalBedrag;
			return this;
		}
		
		
		public Order build() {
			return new Order(this);
		}
		
		
	}

}
