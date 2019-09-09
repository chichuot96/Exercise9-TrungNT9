package vn.topica.itlab4.homework_9.exercise1;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Product {

	private String code;
	private String name;
	private String owner;
	private Date inputDate;
	private int warrantyYear;
	SimpleDateFormat dateFormat = new SimpleDateFormat("DD/MM/YYYY");
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}

	public Product(String code, String name, String owner, Date inputDate, int warrantyYear) {
		super();
		this.code = code;
		this.name = name;
		this.owner = owner;
		this.inputDate = inputDate;
		this.warrantyYear = warrantyYear;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public Date getInputDate() {
		return inputDate;
	}
	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}
	public int getWarrantyYear() {
		return warrantyYear;
	}
	public void setWarrantyYear(int warrantyYear) {
		this.warrantyYear = warrantyYear;
	}
	@Override
	public String toString() {
		return  code + "," + name + "," + owner + "," + dateFormat.format(inputDate)
				+ "," + warrantyYear;
	}
}
