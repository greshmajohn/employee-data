package com.learn.ms.insurance.dao;

public enum Designation {
	SYSTEM_ENGINEER("System Engineer"),
    DELIVERY_MANAGER("Delivery Manager"),
    PROJECT_MANAGER(""),
    SENIOR_PROJECT_MANAGER("North America"),
	SENIOR_SYSTEM_ENGINEER("Senior System Engineer"),
	TECHNOLOGY_ANALYST("Technology Analyst"),
	TECHNOLOGY_LEAD("Technology Lead"),
	CEO("CEO"),
	TEST_ENGINEER("Test Engineer"),
	SENIOR_TEST_ENGINEER("Senior Test Engineer"),
	CONSULTANT("Consultant"),
	SENIOR_CONSULTANT("Senior Consultant"),
	HR("Human Resource Manager");
	
	
	
	
	
	
	
	private String name;

	Designation(String name) {
        this.name = name;
    }
	public String getName() {
		return name;
	}
	

}
