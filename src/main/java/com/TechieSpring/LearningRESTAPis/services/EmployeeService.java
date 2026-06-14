package com.TechieSpring.LearningRESTAPis.services;
import com.TechieSpring.LearningRESTAPis.dto.EmployeeDTO;
import com.TechieSpring.LearningRESTAPis.entities.EmployeeEntity;
import com.TechieSpring.LearningRESTAPis.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;
    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper){
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<EmployeeDTO> getEmployeeById(Long id) {
//        EmployeeEntity employeeEntity= employeeRepository.findById(id);
//
//        return modelMapper.map(employeeEntity , EmployeeDTO.class);
        return employeeRepository.findById(id).map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class));

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

    public EmployeeDTO updateEmployeeById(Long employeeId, EmployeeDTO employeeDto) {
        EmployeeEntity employeeEntity = modelMapper.map(employeeDto ,EmployeeEntity.class);
        employeeEntity.setId(employeeId);
        EmployeeEntity updatedEmployeeEntity = employeeRepository.save(employeeEntity);
        return modelMapper.map(updatedEmployeeEntity ,EmployeeDTO.class);

    }
    public boolean isExists(Long employeeId){
       return  employeeRepository.existsById(employeeId);


    }

    public boolean deleteEmployeeById(Long employeeId) {
        boolean exists = employeeRepository.existsById(employeeId);
        if (!exists) { return false;}
        employeeRepository.deleteById(employeeId);
        return true;
    }
    // patch mapping ar used to partial update on the data
    public EmployeeDTO patchEmployeeById(Long employeeId , Map<String,Object> updates) {
        System.out.println( employeeId);
        System.out.print( updates);
        boolean exists = isExists(employeeId);
        if (!exists) {
            return null;
        }
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).orElseThrow(() -> new RuntimeException(
                "Employee not found with id: " + employeeId));
        updates.forEach((field, value) -> {
            Field fieldToBeUpdated = ReflectionUtils.getRequiredField(EmployeeEntity.class, field);
            System.out.println(fieldToBeUpdated);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated, employeeEntity, value);
        });
        return modelMapper.map(employeeRepository.save(employeeEntity), EmployeeDTO.class);
    }

//    }
//    @Transactional
//    public EmployeeDTO patchEmployeeById(Long employeeId, Map<String, Object> updates) {
//
//        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId)
//                .orElseThrow(() -> new RuntimeException(
//                        "Employee not found with id: " + employeeId));
//
//        updates.forEach((field, value) -> {
//            Field fieldToBeUpdated =
//                    ReflectionUtils.findField(EmployeeEntity.class, field);
//
//            if (fieldToBeUpdated != null) {
//                fieldToBeUpdated.setAccessible(true);
//                ReflectionUtils.setField(fieldToBeUpdated, employeeEntity, value);
//            }
//        });
//
//        EmployeeEntity savedEmployee = employeeRepository.save(employeeEntity);
//
//        return modelMapper.map(savedEmployee, EmployeeDTO.class);
//    }


}
