/**
 * 
 */
package com.qst.supermarket.model;

/**
 * @author 12345678
 *
 */
public class Goods {
	private String barcode;
	private double price;
	private int num;
	private String gname;
	private String tcode;

	private String tname;
	
	
	
	
	
	

	public Goods() {
		
	}

	public Goods(String barcode, double price, int num, String gname, String tcode, String tname) {
		super();
		this.barcode = barcode;
		this.price = price;
		this.num = num;
		this.gname = gname;
		this.tcode = tcode;
		this.tname = tname;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public String getTcode() {
		return tcode;
	}

	public void setTcode(String tcode) {
		this.tcode = tcode;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	@Override
	public String toString() {
		return "Goods [barcode=" + barcode + ", price=" + price + ", num=" + num + ", gname=" + gname + ", tcode="
				+ tcode + ", tname=" + tname + ", getBarcode()=" + getBarcode() + ", getPrice()=" + getPrice()
				+ ", getNum()=" + getNum() + ", getGname()=" + getGname() + ", getTcode()=" + getTcode()
				+ ", getTname()=" + getTname() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	

	
	
}
