package com.employee.data.dao;

import lombok.Data;

@Data
public class AddressDao {

	String street;
	String city;
	int pincode;
	String state;
	String country;
	String phoneNo;
}
