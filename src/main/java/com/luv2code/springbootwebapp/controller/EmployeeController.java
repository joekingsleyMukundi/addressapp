package com.luv2code.springbootwebapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.luv2code.springbootwebapp.entity.Employee;
import com.luv2code.springbootwebapp.repository.EmployeeRepository;

@Controller
public class EmployeeController {

	//autowire the EmployeeRepository to call the jpa methods
	//we will create a handler method with HTTP Get method
	@Autowired
	public EmployeeRepository eRepo;
	
	
    //create a handler method public to return Model and view
	@GetMapping({"/showEmployees", "/", "/list"})
	public ModelAndView showEmployees() {
		
		//modelandview object creation
		ModelAndView mav = new ModelAndView("list-employees.html");
		
		//using the eRepo to call the repository method which is findAll()
		//this should give us the list of employees
		List<Employee> list = eRepo.findAll();
				
		//add list to the model and view object
	    //will take two parameters, key and the value
				mav.addObject("employees", list);
				return mav;
		
		
	}
	
	@GetMapping("/addEmployeeForm")
	public ModelAndView addEmployeeForm() {
		ModelAndView mav = new ModelAndView("add-employee-form");
		//need to create a new employee object so that we can bind it to the employee form
		Employee newEmployee = new Employee();
		
		//add this new employee to the model and view object
		mav.addObject("employee", newEmployee);
		
		return mav;
	}
	
	@PostMapping("/saveEmployee")
	//@ModelAttribute binds Employee details to employee object
	public String saveEmployee(@ModelAttribute Employee employee) {
		eRepo.save(employee);
		return"redirect:/list";
		
	}
	
	@GetMapping("/showUpdateForm")
	public ModelAndView showUpdateForm(@RequestParam Long employeeId) {
		ModelAndView mav = new ModelAndView("add-employee-form");
		Employee employee = eRepo.findById(employeeId).get();
		mav.addObject("employee", employee);
		return mav;
	}
	
	@GetMapping("/deleteEmployee")
	public String deleteEmployee(@RequestParam Long employeeId) {
		eRepo.deleteById(employeeId);
		return "redirect:/list";
	}
}
