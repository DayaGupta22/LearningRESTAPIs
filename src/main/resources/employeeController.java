package com.TechieSpring.LearningRESTAPis.controllers;

import com.TechieSpring.LearningRESTAPis.entities.EmployeeEntity;
import com.TechieSpring.LearningRESTAPis.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class employeeController {
//    @GetMapping(path = "/getmessage")
//            public String getMymessage(){
//        return "Hello World!";
//    }
//    private final EmployeeRepository employeeRepositoy;
     private final EmployeeService employeeService;
     public employeeController(EmployeeService employeeService){
         this.employeeService = employeeService;
     }
// create a new user
public EmployeeEntity CreateNewEmployee(@RequestBody EmployeeEntity inputEmployee){
    return employeeService.createNewEmployee(inputEmployee);
}
    // get the employees

    @GetMapping("/{employeeId}")
    public EmployeeEntity getEmployeeById(@PathVariable(name="employeeId") Long id){

        return employeeService.getEmployeeById(id);

    }

    // get the all employees
    @GetMapping
    public List<EmployeeEntity> getAllEmployees(@RequestParam(required = false, name="inputage") Integer age,
                                                @RequestParam(required=false )String sortBy){

        return employeeService.getAllEmployees();
    }


}
