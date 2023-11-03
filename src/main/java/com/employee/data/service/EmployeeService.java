package com.employee.data.service;

import java.util.List;

import com.employee.data.dao.DeliveryUnitDao;
import com.employee.data.dao.EmployeeAdminRequestDao;
import com.employee.data.dao.EmployeeRequestDao;
import com.employee.data.dao.EmployeeResponseDao;
import com.employee.data.dao.ProjectCodeDao;
import com.employee.data.dao.ResponseDto;
import com.employee.data.entity.DeliveryUnitEntity;
import com.employee.data.exception.DataNotFoundException;


public interface EmployeeService {
	
	ResponseDto saveEmployeeDetails(EmployeeRequestDao employeeRequest);
	
	ResponseDto updateEmployeeDetails(EmployeeAdminRequestDao employeeAdminObj) throws DataNotFoundException;
	
	ResponseDto saveProjectCode(ProjectCodeDao projectCode);
	
	ResponseDto addDeliveyUnit(DeliveryUnitDao deliveryUnit) throws DataNotFoundException;
	
	DeliveryUnitEntity updateDeliveryUnit(DeliveryUnitDao deliveryUnit) throws DataNotFoundException;
	
	List<EmployeeResponseDao> getEmployeeDetails();
	
	List<DeliveryUnitDao> getDeliveryUnitList();
	
	
	
	
	

}
