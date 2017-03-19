package com.suning.entity;

import net.sf.oval.constraint.NotNull;

public class Man extends Person {

	@NotNull(message="国家不能为空")
	private String country;
	
	@NotNull(message="省不能为空")
	private String province;
	
	@NotNull(message="市不能为空")
	private String city;
	
	@NotNull(message="镇不能为空")
	private String town;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}
	
}
