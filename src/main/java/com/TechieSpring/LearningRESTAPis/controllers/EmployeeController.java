package com.TechieSpring.LearningRESTAPis.controllers;
import com.TechieSpring.LearningRESTAPis.dto.EmployeeDTO;
import com.TechieSpring.LearningRESTAPis.exceptions.ResourceNotFoundException;
import com.TechieSpring.LearningRESTAPis.services.EmployeeService;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final ModelMapper modelMapper;
    public EmployeeController(EmployeeService employeeService,ModelMapper modelMapper) {
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO>  getEmployeeById(@PathVariable(name="employeeId") Long id){

        EmployeeDTO employeeDto = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employeeDto);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(
            @RequestParam(required = false, name="inputage") Integer age,
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

    }

    @PatchMapping(path="/{employeeId}")
    public ResponseEntity<EmployeeDTO> patchEmployeeById(@PathVariable Long employeeId ,@RequestBody Map<String ,Object> updates){
        EmployeeDTO employeeDto= employeeService.patchEmployeeById(employeeId,updates);
        if(employeeDto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employeeDto);
    }


}
