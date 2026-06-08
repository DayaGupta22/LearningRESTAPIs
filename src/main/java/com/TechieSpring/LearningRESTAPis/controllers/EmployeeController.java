package com.TechieSpring.LearningRESTAPis.controllers;
import com.TechieSpring.LearningRESTAPis.dto.EmployeeDTO;
import com.TechieSpring.LearningRESTAPis.entities.EmployeeEntity;
import com.TechieSpring.LearningRESTAPis.services.EmployeeService;
import java.util.List;
import com.TechieSpring.LearningRESTAPis.entities.EmployeeEntity;
import com.TechieSpring.LearningRESTAPis.services.EmployeeService;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/employees")
public class EmployeeController {
//    private final EmployeeRepository employeeRepositoy;
    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }
    // create a new user

    // get the employees
    @GetMapping("/{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable(name="employeeId") Long id){

        return employeeService.getEmployeeById(id);

    }
    // get the all employees
    @GetMapping
    public List<EmployeeDTO> getAllEmployees(@RequestParam(required = false, name="inputage") Integer age,
                                                @RequestParam(required=false )String sortBy){

        return employeeService.getAllEmployees();
    }
    @PostMapping
    public EmployeeDTO CreateNewEmployee(@RequestBody EmployeeDTO inputEmployee){
        return employeeService.createNewEmployee(inputEmployee);
    }


}
