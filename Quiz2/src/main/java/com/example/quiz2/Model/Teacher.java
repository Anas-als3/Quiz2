package com.example.quiz2.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Teacher {
    @NotEmpty(message = "id Cannot be empty")
private String id;
    @NotEmpty(message = "Name Cannot be Empty")
private String name;
    @NotNull(message = "Salary Cannot be null")
private int salary;
}
