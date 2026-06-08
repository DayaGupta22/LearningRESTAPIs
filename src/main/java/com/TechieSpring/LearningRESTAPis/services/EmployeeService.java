package com.TechieSpring.LearningRESTAPis.services;

import com.TechieSpring.LearningRESTAPis.dto.EmployeeDTO;
import com.TechieSpring.LearningRESTAPis.entities.EmployeeEntity;
import com.TechieSpring.LearningRESTAPis.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;
    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper){
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public EmployeeDTO getEmployeeById(Long id) {
        EmployeeEntity employeeEntity= employeeRepository.findById(id).orElse(null);

        return modelMapper.map(employeeEntity , EmployeeDTO.class);

    }

    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeEntity> employeeEntities= employeeRepository.findAll();
       return employeeEntities
                .stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity,EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public EmployeeDTO createNewEmployee(EmployeeDTO inputEmployee) {
       EmployeeEntity employeeEntity = modelMapper.map(inputEmployee, EmployeeEntity.class);
       EmployeeEntity savedEmployeeEntity  = employeeRepository.save(employeeEntity);
       return modelMapper.map(savedEmployeeEntity,EmployeeDTO.class);
    }
}
