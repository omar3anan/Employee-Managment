package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employees")  //3alshan el JPA repository 3amlha employees
public class EmployeeController {

 	private EmployeeService employeeService;

	 public EmployeeController(EmployeeService theEmployeeService) {
		 employeeService = theEmployeeService;
	 }


	@GetMapping("/list")
	public String listEmployees(Model theModel) {
		List <Employee> theEmployees = employeeService.findAll();
		// add to the spring model
		theModel.addAttribute("employees", theEmployees);
		return "employees/list-employees";  //path of template
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		Employee theEmployee = new Employee();  //Empty Employee
		theModel.addAttribute("employeex", theEmployee);  //employeex delokty fadya fa hatla3 fyl model empty (*{firstName}) w haakza
		return "employees/emplyee-form";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int theId, Model theModel) {  //@RequestParam("employeeId") int theId  //el name of the model attribute must be the same as the name of the form
		Employee theEmployee = employeeService.findById(theId);

		// set employee as a model attribute to pre-populate the form
		theModel.addAttribute("employeex", theEmployee);//employeex msh fadya

		// send over to our form
		return "employees/emplyee-form";
	}

	@PostMapping("/save")  //for Create or Update
	public String saveEmployee(@ModelAttribute("employeex") Employee theEmployee) {  //3alshan akhod el employee
		employeeService.save(theEmployee); // save the employee
		return "redirect:/employees/list"; // use a redirect to prevent duplicate submissions
	}

	@GetMapping("/delete")
	public String deleteEmployee(@RequestParam("employeeId") int theId, Model theModel){
		 employeeService.deleteById(theId);
		return "redirect:/employees/list";

	}

}

//what is Model ? Model is a container for the data. You can use it to pass data between the controller and the view.


//@ModelAttribute("employeex") ==> from the form
//@RequestBody
//@RequestParam("employeex") ==> from the URL (in Views)
//@PathVariable("employeex") ==> from the URL (in APIs)





