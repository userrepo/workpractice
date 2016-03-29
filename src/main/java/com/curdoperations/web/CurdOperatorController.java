package com.curdoperations.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.curdoperations.dao.IEmployeeDao;
import com.curdoperations.entity.Employee;


@Controller
public class CurdOperatorController {

@Autowired	
public  IEmployeeDao EmployeeDao;



@RequestMapping(value="/Create")
public String create(@ModelAttribute("Employee")Employee e)
{
	
if(e.getId()==0)
	EmployeeDao.addEmployee(e);
else
	EmployeeDao.update(e);

return "redirect:/EmployeesList";

}

@RequestMapping(value="EmployeesList",method=RequestMethod.GET)
public String getEmployeeList(Model model)
{

	List<Employee> listofEmployees=EmployeeDao.getListOfEmloyees();
	 model.addAttribute("Employee", new Employee());
     model.addAttribute("listofemployees", listofEmployees);
     
	return "Employees";
}

@RequestMapping(value="/remove/{id}")
public String deleteEmployeeById(@PathVariable("id") int id)
{
	
	EmployeeDao.deletEmployeeById(id);
	
	return "redirect:/EmployeesList";
}

@RequestMapping("/edit/{id}")
public String editPerson(@PathVariable("id") int id, Model model){
    model.addAttribute("Employee", EmployeeDao.getEmployeeByID(id));
    model.addAttribute("listofemployees",EmployeeDao.getListOfEmloyees());
    return "Employees";
}

}
