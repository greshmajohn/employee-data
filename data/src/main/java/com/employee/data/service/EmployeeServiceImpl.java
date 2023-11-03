package com.employee.data.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.data.dao.AddressDao;
import com.employee.data.dao.DeliveryUnitDao;
import com.employee.data.dao.Designation;
import com.employee.data.dao.EmployeeAdminRequestDao;
import com.employee.data.dao.EmployeeRequestDao;
import com.employee.data.dao.EmployeeResponseDao;
import com.employee.data.dao.ProjectCodeDao;
import com.employee.data.dao.ResponseDto;
import com.employee.data.entity.AddressEntity;
import com.employee.data.entity.DeliveryUnitEntity;
import com.employee.data.entity.EmployeeEntity;
import com.employee.data.entity.ProjectCodeEntity;
import com.employee.data.exception.DataNotFoundException;
import com.employee.data.repository.AddressRepository;
import com.employee.data.repository.DeliveryUnitRepository;
import com.employee.data.repository.EmployeeReposiory;
import com.employee.data.repository.ProjectCodeRepository;

import lombok.extern.slf4j.Slf4j;
/*
 * perform all CRUD operations in tables:
 *  	1.address
 *  	2.emp
 *  	3.projevt_code
 *  	4.delivery_unit
 */
@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
	

	EmployeeReposiory empRepository;
	
	AddressRepository addressRepo;
	
	DeliveryUnitRepository deliveryUnitRepo;
	
	ProjectCodeRepository projectRepository;
	
	public EmployeeServiceImpl(EmployeeReposiory empRepository,AddressRepository addressRepo,DeliveryUnitRepository deliveryUnitRepo,
			ProjectCodeRepository projectRepository) {
		this.empRepository=empRepository;
		this.addressRepo=addressRepo;
		this.deliveryUnitRepo=deliveryUnitRepo;
		this.projectRepository=projectRepository;
	}

	@Override
	public ResponseDto saveEmployeeDetails(EmployeeRequestDao employeeRequest) {
		
		ResponseDto response=new ResponseDto("Failed","Failed to save employee data");
		
		EmployeeEntity employee=new EmployeeEntity();
		employee.setEmpName(employeeRequest.getFirstName()+" "+employeeRequest.getLastName());
		employee.setDateOfBirth(employeeRequest.getDateOfBirth());
		employee.setExperience(employeeRequest.getExperienceInMonths());
		employee.setDateOfJoin(employeeRequest.getDateOfJoin());
		
		employee.setAddress(setAddressObject(employeeRequest));
		employee.setCreatedBy("SYSTEM");
		employee.setCreatedOn(LocalDateTime.now());
		employee.setEmpId(generateEmpId(employeeRequest.getFirstName()));
		
		EmployeeEntity empResult=empRepository.save(employee);
		if(empResult!=null)
			response=new ResponseDto("Success","Employee data saved successfully !");
		
		System.out.println("empResult"+empResult);
		return response ;
	}

	private AddressEntity setAddressObject(EmployeeRequestDao employeeRequest) {
		AddressEntity address=new AddressEntity();
		address.setCity(employeeRequest.getCity());
		address.setCountry(employeeRequest.getCountry());
		address.setPhoneNo(employeeRequest.getPhoneNo());
		address.setPincode(employeeRequest.getPincode());
		address.setState(employeeRequest.getState());
		address.setStreet(employeeRequest.getStreet());
		return address;
	}

	private String generateEmpId(String firstName) {
		
		return firstName.substring(0,2)+getRandomNumber();
	}

	private String getRandomNumber() {
		char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder((100000 + rnd.nextInt(900000)));
		for (int i = 0; i < 5; i++)
		    sb.append(chars[rnd.nextInt(chars.length)]);

		return sb.toString();
	}

	@Override
	public List<EmployeeResponseDao> getEmployeeDetails() {
		System.out.println("delivery manager");
		List<EmployeeResponseDao> empResult=new ArrayList<EmployeeResponseDao>();
		List<EmployeeEntity> empList=empRepository.findAll();
		for(EmployeeEntity empObj:empList) {
			EmployeeResponseDao empResultObj=new EmployeeResponseDao();
			empResultObj.setEmpId(empObj.getEmpId());
			empResultObj.setAddress(getAddressobj(empObj.getAddress()));
			empResultObj.setName(empObj.getEmpName());
			empResultObj.setDateOfBirth(empObj.getDateOfBirth());
			empResultObj.setDateOfJoin(empObj.getDateOfJoin());
			empResultObj.setDesignation(empObj.getDesignation());
			log.info("delivery manager");
			if(empObj.getDeliveryUnit()!=null)
				empResultObj.setDepartment(empObj.getDeliveryUnit().getDeliveryUnit());
			if(empObj.getReportingManager()!=null)
				empResultObj.setReportingManager(empObj.getReportingManager().getEmpName());
			
			if(empObj.getDeliveryUnit()!=null&&empObj.getDeliveryUnit().getUnitHr()!=null)
				empResultObj.setReportingHr(empObj.getDeliveryUnit().getUnitHr().getEmpName());
			
			if(empObj.getProjectCode()!=null)
				empResultObj.setProjectCode(empObj.getProjectCode().getProjectCode());
			
			empResultObj.setExperience(convertExperienceToyear(empObj.getExperience()));
			empResult.add(empResultObj);
			
		}
		return empResult;
	}

	private String convertExperienceToyear(int experience) {
		Period period = Period.ofMonths(experience).normalized();
		return period.getYears()+" years";
	}

	private AddressDao getAddressobj(AddressEntity address) {
		AddressDao addressResult=new AddressDao();
		addressResult.setCity(address.getCity());
		addressResult.setCountry(address.getCountry());
		addressResult.setPhoneNo(address.getPhoneNo());
		addressResult.setPincode(address.getPincode());
		addressResult.setState(address.getState());
		addressResult.setStreet(address.getStreet());
		return addressResult;
	}

	@Override
	public List<DeliveryUnitDao> getDeliveryUnitList() {
		
		
		return null;
	}

	@Override
	public ResponseDto updateEmployeeDetails(EmployeeAdminRequestDao emp) throws DataNotFoundException {
		ResponseDto resp= new ResponseDto("Failed","Update operation failed");
		
			Optional<EmployeeEntity> OptionalEmployee=empRepository.findEmployeeByEmpId(emp.getEmpId());
			if(OptionalEmployee.isPresent()) {
				if(Designation.IfNameExist(emp.getDesignation()))
						OptionalEmployee.get().setDesignation(emp.getDesignation());
				else
					throw new IllegalArgumentException("Invalid Designation");
				Optional<ProjectCodeEntity> projectCode=projectRepository.findByProjectCode(emp.getProjectCode());
				if(projectCode.isPresent())
					OptionalEmployee.get().setProjectCode(projectCode.get());
				else
					throw new DataNotFoundException("Employee details not found for the given project code. Please give a valid projectCode");
				Optional<DeliveryUnitEntity> deliveryUnit=deliveryUnitRepo.findByDeliveryunitName(emp.getDeliveryUnit());
				if(deliveryUnit.isPresent())
					OptionalEmployee.get().setDeliveryUnit(deliveryUnit.get());
				else
					throw new DataNotFoundException("Delivery Unit not found . Please give a valid deliveryUnit");
				
//				get reporting manager reference
				Optional<EmployeeEntity> repotingManager=empRepository.findByEmployeeName(emp.getReportingManager());
				if(repotingManager.isPresent())
					OptionalEmployee.get().setReportingManager(repotingManager.get());
				else
					throw new DataNotFoundException("Reporting Manager not found . Please provide valid employee name");
				
				OptionalEmployee.get().setUpdatedBy("ADMIN");
				OptionalEmployee.get().setUpdatedOn(LocalDateTime.now());
				EmployeeEntity employeeResult=empRepository.save(OptionalEmployee.get());
				if(employeeResult!=null)
					resp=new ResponseDto("Success","Employee Details upldated successfully !");
				
			}else
				throw new DataNotFoundException("Employee details not found for the given empId. Please provide a valid empId");
			
		
			
		
		
		return resp;
	}

	
	@Override
	public ResponseDto addDeliveyUnit(DeliveryUnitDao deliveryUnit) throws DataNotFoundException {
		DeliveryUnitEntity delUnit=new  DeliveryUnitEntity();
		delUnit.setDeliveryUnit(deliveryUnit.getDeliveryUnit());
		
		Optional<EmployeeEntity> optionalManager=empRepository.findByEmployeeName(deliveryUnit.getDeliveryManager());
		if(optionalManager.isPresent())
			delUnit.setDeliveryManager(optionalManager.get());
		else
			throw new DataNotFoundException("Given Delivery manager not found");
		
		Optional<EmployeeEntity> optionalHr=empRepository.findByEmployeeName(deliveryUnit.getUnitHr());
		if(optionalHr.isPresent())
			delUnit.setUnitHr(optionalHr.get());
		else
			throw new DataNotFoundException("Unit Hr not found");
		
		DeliveryUnitEntity delUnitResult=deliveryUnitRepo.save(delUnit);
		if(delUnitResult!=null)
			return new ResponseDto("Success","Delivery Unit Saved Successfully !");
		else
		 return new ResponseDto("Failed","Save operation failed");
	}

	/*
	 * save project code details
	 */
	@Override
	public ResponseDto saveProjectCode(ProjectCodeDao projectCode) {
		ProjectCodeEntity projectEntity=new ProjectCodeEntity();
		projectEntity.setBudget(projectCode.getBudget());
		projectEntity.setProjectCode(projectCode.getProjectCode());
		projectEntity.setValidFrom(projectCode.getValidFrom().atStartOfDay());
		projectEntity.setValidTo(projectCode.getValidTo().atStartOfDay());
		projectEntity.setCreatedBy("SYSTEM");
		projectEntity.setCreatedOn(LocalDateTime.now());
		DeliveryUnitEntity delUnit=getDiveryUnitObject(projectCode.getDeliveryUnit());
		if(delUnit!=null)
			projectEntity.setDeliveryUnit(getDiveryUnitObject(projectCode.getDeliveryUnit()));
		else 
			throw new IllegalArgumentException("Delivery Unit not found");
		
		ProjectCodeEntity projectResponse=projectRepository.save(projectEntity);
		
		if(projectResponse!=null)
			return new ResponseDto("Success","Project Code added successfully ! ");
		return new ResponseDto("Failed","Failed to add Project Code");
	}

	private DeliveryUnitEntity getDiveryUnitObject(String deliveryUnit) {
		Optional<DeliveryUnitEntity> deliveryObj=deliveryUnitRepo.findByDeliveryunitName(deliveryUnit);
		if(deliveryObj.isPresent()) {
			return deliveryObj.get();
		}
		return null;
	}
	

	@Override
	public DeliveryUnitEntity updateDeliveryUnit(DeliveryUnitDao deliveryUnit) throws DataNotFoundException {
		EmployeeEntity unitHR=null;
		if(deliveryUnit.getUnitHr()!=null)
			 unitHR=getEmployeeByDesignation(deliveryUnit.getUnitHr(),Designation.UNIT_HR.getName());
		
		EmployeeEntity manager=null;
		if(deliveryUnit.getDeliveryManager()!=null)
			 manager=getEmployeeByDesignation(deliveryUnit.getDeliveryManager(),Designation.DELIVERY_MANAGER.getName());
		if(unitHR!=null||manager!=null) {
			Optional<DeliveryUnitEntity> result=deliveryUnitRepo.findByDeliveryunitName(deliveryUnit.getDeliveryUnit());
			if(result.isPresent()) {
				if(unitHR!=null)
					result.get().setUnitHr(unitHR);
				if(manager!=null)
					result.get().setDeliveryManager(manager);
				
				return deliveryUnitRepo.save(result.get());
			}
			else
				throw new DataNotFoundException("Delivery Unit not found . Please give a valid deliveryUnit");
			
		}else
			throw new IllegalArgumentException("No data to update");

	}

	private EmployeeEntity getEmployeeByDesignation(String unitHr,String destination) {
		Optional<EmployeeEntity> result=empRepository.findEmployeeByNameAndDesignation(unitHr,destination);
		return result.isPresent()?result.get():null;
	}

}
