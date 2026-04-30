package com.example.quiz2.Controller;

import com.example.quiz2.ApiResponse.ApiResponse;
import com.example.quiz2.Model.Teacher;
import com.example.quiz2.Service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/teacher")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teachers;

    @GetMapping("/get")
    public ResponseEntity<?> getTeachers() {
        return ResponseEntity.status(200).body(teachers.getTeachers());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addTeacher(@RequestBody @Valid Teacher teacher, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        teachers.addTeacher(teacher);
        return ResponseEntity.status(200).body(new ApiResponse("Teacher added Successfully"));

    }@PutMapping("/update/{id}")
    public ResponseEntity<?> updateTeacher(@PathVariable String id,@RequestBody @Valid Teacher teacher,Errors errors){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        int result = teachers.updateTeacher(id,teacher);
        if (result==1){
            return ResponseEntity.status(200).body(new ApiResponse("Teacher Updated Successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("No teacher with that id exist"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTeacher(@PathVariable String id){
        int result= teachers.deleteTeacher(id);
        if (result==1){
            return ResponseEntity.status(200).body(new ApiResponse("Teacher Deleted Successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("No teacher with that id exist"));

    }
    @GetMapping("/get-id/{id}")
    public ResponseEntity<?> getTeachersById(@PathVariable String id){
        Teacher teacher=teachers.getTeacherById(id);
        if (teacher!=null){
            return ResponseEntity.status(200).body(teacher);
        }
        return ResponseEntity.status(400).body(new ApiResponse("No teacher with that id exist"));
    }
    @GetMapping("/get-salary/{salary}")
    public ResponseEntity<?> getTeachersBySalary(@PathVariable int salary){
        ArrayList<Teacher>teachersSalary=teachers.getTeachersBySalary(salary);

        if (!teachersSalary.isEmpty()){
            return ResponseEntity.status(200).body(teachersSalary);
        }
        return ResponseEntity.status(400).body(new ApiResponse("No teachers with that Salary exist"));

    }


}
