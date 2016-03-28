package com.curdoperations.dao;

import java.util.List;

import com.curdoperations.entity.Employee;

public interface IEmployeeDao{
	
public void addEmployee(Employee e);
public List<Employee> getListOfEmloyees();
public  void update(Employee e);

public Employee getEmployeeByID(int id);

public void deletEmployeeById(int id);


}