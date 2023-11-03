package com.employee.data.dao;

public enum Designation {
	
	UNIT_HR("Unit Hr"),
	SENIOR_HR("Senior Hr"),
	SYSTEM_ENGINEER("System Engineer"),
	TECHNOLOGY_ANALYST("Technology Analyst"),
	TECHNOLOGY_LEAD("Technology Lead"),
	PROJECT_MANAGER("Project Manager"),
	SENIOR_PROJECT_MANAGER("Senior Project Manager"),
	DELIVERY_MANAGER("Delivery Manager"),
	SENIOR_SOFTWARE_ENGINEER("Senior Software Engineer"),
	TECHNOLOGY_ARCHITECT("Technology Architect"),
	SENIOR_TECHNOLOGY_ARCHITECT("Senior Technology Architect"),
	CEO("CEO"),
	DELIVERY_HEAD("Delivery Head"),
	SCRUM_MASTER("Scrum Master"),
	CONSULTANT("Consultant"),
	SENIOR_CONSULTANT("Senior Consultant"),
	TEST_ENGINEER("Test Engineer"),
	SENIOR_TEST_ENGINEER("Senior Test Engineer");

	private String name;

	Designation(String name) {
        this.name = name;
    }
	public String getName() {
		return name;
	}
	/*
	 * method to validate the enum value. Check whether given input exist or not.
	 */
	
	public static Boolean IfNameExist(String dest) {
		
		for(Designation destination:values()) {
			if(destination.getName().equals(dest)) {
				return true;
			}
		}
		
		return false;
	}
	
	

	
}
