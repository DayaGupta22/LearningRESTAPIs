package com.TechieSpring.LearningRESTAPis.controllers;
import com.TechieSpring.LearningRESTAPis.dto.EmployeeDTO;
import com.TechieSpring.LearningRESTAPis.entities.EmployeeEntity;
import com.TechieSpring.LearningRESTAPis.services.EmployeeService;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.TechieSpring.LearningRESTAPis.entities.EmployeeEntity;
import com.TechieSpring.LearningRESTAPis.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
//    private final EmployeeRepository employeeRepositoy;
    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    // get the employees
    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO>  getEmployeeById(@PathVariable(name="employeeId") Long id){
// this is the firsy step
//        EmployeeDTO employeeDto =employeeService.getEmployeeById(id);
//        if(employeeDto == null) { return ResponseEntity.notFound().build();}
//        return ResponseEntity.ok(employeeDto);
        // another method is
        Optional<EmployeeDTO> employeeDto = employeeService.getEmployeeById(id);
        return employeeDto
                .map(employeeDto1 -> ResponseEntity.ok(employeeDto1))
                .orElse(ResponseEntity.notFound().build());

    }
    // get the all employees
    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(@RequestParam(required = false, name="inputage") Integer age,
                                                @RequestParam(required=false )String sortBy){

        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> CreateNewEmployee(@RequestBody @Valid EmployeeDTO inputEmployee){
       EmployeeDTO CreatedUser= employeeService.createNewEmployee(inputEmployee);
        return new ResponseEntity<>(CreatedUser, HttpStatus.CREATED);
    }

    @PutMapping( path ="/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@RequestBody @Valid EmployeeDTO employeeDto , @PathVariable Long employeeId){
        return ResponseEntity.ok(employeeService.updateEmployeeById(employeeId ,employeeDto));
    }
    @DeleteMapping(path="/{employeeId}")
    public ResponseEntity< Boolean>deleteEmployeeById(@PathVariable Long employeeId){
        boolean gotDeleted =employeeService.deleteEmployeeById(employeeId);
        if(gotDeleted){
            return ResponseEntity.ok(true);

        }
        return ResponseEntity.notFound().build();
// if we want to customize code then
//       return ResponseEntity.ok(employeeService.deleteEmployeeById(employeeId));
    }
    @PatchMapping(path="/{employeeId}")
    public ResponseEntity<EmployeeDTO> patchEmployeeById(@PathVariable Long employeeId ,@RequestBody Map<String ,Object> updates){
        EmployeeDTO employeeDto= employeeService.patchEmployeeById(employeeId,updates);
        if(employeeDto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employeeDto);
    }


}
