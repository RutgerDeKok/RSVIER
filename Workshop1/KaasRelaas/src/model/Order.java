package model;

import java.math.BigDecimal;
import java.sql.Date;


public class Order {
	
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
	
	//constructor
	public Order(){
	}
	
	public Order(int orderId, Date orderDatum, int medewerkerId, int klantId, int productA_Id,
		int productA_aantal, int productB_Id, int productB_aantal, int productC_Id, 
		int productC_aantal, BigDecimal totaalBedrag){
		
		this.orderId = orderId;
		this.orderDatum = orderDatum;
		this.medewerkerId = medewerkerId;
		this.klantId = klantId;
		this.productA_Id = productA_Id;
		this.productA_aantal = productA_aantal;
		this.productB_Id = productB_Id;
		this.productB_aantal = productB_aantal;
		this.productC_Id = productC_Id;
		this.productC_aantal = productC_aantal;
		this.totaalBedrag = totaalBedrag;
		
	}
	
	@Override
	public String toString() {
		int totaalProducten = productA_aantal +productB_aantal + productC_aantal;
		return String.format("id=%s, datum=%s, medew=%s, klant=%s, totProd=%s, totBedrag=%s "+"\n",
						orderId, orderDatum, medewerkerId, klantId, totaalProducten, totaalBedrag);
	}
	
	
	
	// getters and setters 

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Date getOrderDatum() {
		return orderDatum;
	}

	public void setOrderDatum(Date orderDatum) {
		this.orderDatum = orderDatum;
	}

	public int getMedewerkerId() {
		return medewerkerId;
	}

	public void setMedewerkerId(int medewerkerId) {
		this.medewerkerId = medewerkerId;
	}

	public int getKlantId() {
		return klantId;
	}

	public void setKlantId(int klantId) {
		this.klantId = klantId;
	}

	public int getProductA_Id() {
		return productA_Id;
	}

	public void setProductA_Id(int productA_Id) {
		this.productA_Id = productA_Id;
	}

	public int getProductA_aantal() {
		return productA_aantal;
	}

	public void setProductA_aantal(int productA_aantal) {
		this.productA_aantal = productA_aantal;
	}

	public int getProductB_Id() {
		return productB_Id;
	}

	public void setProductB_Id(int productB_Id) {
		this.productB_Id = productB_Id;
	}

	public int getProductB_aantal() {
		return productB_aantal;
	}

	public void setProductB_aantal(int productB_aantal) {
		this.productB_aantal = productB_aantal;
	}

	public int getProductC_Id() {
		return productC_Id;
	}

	public void setProductC_Id(int productC_Id) {
		this.productC_Id = productC_Id;
	}

	public int getProductC_aantal() {
		return productC_aantal;
	}

	public void setProductC_aantal(int productC_aantal) {
		this.productC_aantal = productC_aantal;
	}

	public BigDecimal getTotaalBedrag() {
		return totaalBedrag;
	}

	public void setTotaalBedrag(BigDecimal totaalBedrag) {
		this.totaalBedrag = totaalBedrag;
	}
	

}
