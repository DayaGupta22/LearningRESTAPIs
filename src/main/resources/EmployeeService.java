package com.TechieSpring.LearningRESTAPis.services;

import com.TechieSpring.LearningRESTAPis.entities.EmployeeEntity;
import com.TechieSpring.LearningRESTAPis.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
            this.employeeRepository = employeeRepository;
    }

    public EmployeeEntity getEmployeeById(Long id) {
        EmployeeEntity employeeEntity= employeeRepository.findById(id).orElse(null);
        ModelMapper mapper = new ModelMapper();
        mapper.map(employeeEntity ,EmployeeDTO.class);

    }

    public List<EmployeeEntity> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public EmployeeEntity createNewEmployee(EmployeeEntity inputEmployee) {
        return employeeRepository.save(inputEmployee);
    }
}
