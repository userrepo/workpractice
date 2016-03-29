package com.curdoperations.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.curdoperations.entity.Employee;

@Repository("EmployeeDao")
public  class EmployeeDao implements IEmployeeDao
{

	
	@Autowired
	public SessionFactory sessionFactory;
	
	@Override
	public void addEmployee(Employee e) {
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		session.save(e);
		session.getTransaction().commit();
		session.close();
		
	}

	@Override
	public List<Employee> getListOfEmloyees() {
		Session session=sessionFactory.openSession();
		List<Employee> EmployeeList=session.createQuery("from Employee").list();
		
		return EmployeeList;
	}

	@Override
	public void update(Employee e) {
	
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		session.update(e);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public Employee getEmployeeByID(int id) {
		Session session=sessionFactory.openSession();
		Employee employee=(Employee)session.load(Employee.class,new Integer(id));
		return employee;
	}

	@Override
	public void deletEmployeeById(int id) {
		Session session=sessionFactory.openSession();
		Employee e=(Employee)session.load(Employee.class,new Integer(id));
		session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
		session.close();
		
		
	}
	
}