package kr.web;

import java.io.Serializable;

public class Customer implements Serializable {
	// 속성
	static int serialNums = 1;
	
	protected String customerID;
	protected String name;
	protected String customerGrade;
	protected int bonusPoint;
	protected float bonusPointRatio;

	// 행위
	public int calculatePrice(int price) {
		this.bonusPoint += price * bonusPointRatio;
		
		return price;
	}

	// Default Constructor
	public Customer() {
	}

	public Customer(String customerID, String name) {
		this(name);
		this.customerID = customerID;
	}
	
	public Customer(String name) {
		this.customerID = "Customer" + serialNums++;
		this.name = name;
		this.customerGrade = "SILVER";
		this.bonusPoint = 0;
		this.bonusPointRatio = 0.01f;
	}
	
	protected void printMyInfo() {
		System.out.printf("Customer(customerID=%s, name=%s, customerGrade=%s, bonusPoint=%d)%n",
				this.customerID, this.name, this.customerGrade, this.bonusPoint);		
	}

	@Override
	public String toString() {
		return "Customer{" +
				"customerID='" + customerID + '\'' +
				", name='" + name + '\'' +
				", customerGrade='" + customerGrade + '\'' +
				", bonusPoint=" + bonusPoint +
				", bonusPointRatio=" + bonusPointRatio +
				'}';
	}
}
