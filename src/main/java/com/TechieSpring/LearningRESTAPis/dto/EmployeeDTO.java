package com.TechieSpring.LearningRESTAPis.dto;

import com.TechieSpring.LearningRESTAPis.annotations.EmployeeRoleValidation;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private Long id;
    @Pattern( regexp="^[A-Za-z]+$",
    message="name should contain letter and spaces ")
    @NotBlank(message="Required field in employeename")
    @Size(min=3,max=15 , message="Nummbers of characters of name should be range:[3,15]")
    private String name;

    @NotBlank(message="email of the Employee cannot be  blank")
    @Email(message="Email should be valid format")
    private String email;

    @NotNull(message="age cannot be null")
    @Min(value=20 , message="age should be greater than 20")
    @Max(value=80 , message="age cannot be greater tha 80")
    private Integer age;

    @NotBlank(message="Role of the Employee cannot be  blank")
//    @Pattern(regexp = "^(ADMIN|USER)$", message = "role only admin or user")
    @EmployeeRoleValidation
    private String role;

    @PastOrPresent(message="DateOfJoinig fiels in employee can not be in the future ")
    @NotNull(message = "Date of joining is required")
    private LocalDate dateOfJoining;

    @NotNull(message ="salary can not be null")
    @Positive(message="Salary of Employee shuold be positive")
    @Digits(integer=6,fraction=2,message="Salarey should be int eh form of XXXX.YY")
    @DecimalMin(value="100.50")
    @DecimalMax(value="1000000.99")
    private double salary;

    @NotNull(message = "Active status is required")
    @JsonProperty("isActive")
    @AssertTrue(message="Employee should be Active ")
    private Boolean isActive;

}
