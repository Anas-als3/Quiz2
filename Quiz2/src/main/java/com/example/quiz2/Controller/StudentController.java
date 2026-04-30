package com.example.quiz2.Controller;

import com.example.quiz2.ApiResponse.ApiResponse;
import com.example.quiz2.Model.Student;
import com.example.quiz2.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService students;
    @GetMapping("/get")
    public ResponseEntity<?> getStudents(){
        return ResponseEntity.status(200).body(students.getStudents());
    }
    @PostMapping("/add")
    public ResponseEntity<?> addStudent(@RequestBody @Valid Student student, Errors errors){
        if (errors.hasErrors()){
            String message= errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }students.addStudent(student);
        return ResponseEntity.status(200).body(new ApiResponse("Added Student"));
    }@DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable String id){
        int result= students.deleteStudent(id);
        if (result==1){
            return ResponseEntity.status(200).body(new ApiResponse("Deleted Student Successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("no student with that id Exist"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable String id,@RequestBody @Valid Student student,Errors errors){
        if (errors.hasErrors()){
            String message= errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        int result= students.updateStudent(id,student);
        if (result==1){
            return ResponseEntity.status(200).body(new ApiResponse("Updated Student!"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Failed to update"));
    }
    @GetMapping("/get-name/{name}")
    public ResponseEntity<?> getStudentByName(@PathVariable String name){
        Student student=students.getStudentByName(name);
        if(student!=null){
            return ResponseEntity.status(200).body(student);
        }
        return ResponseEntity.status(400).body(new ApiResponse("No Studend with that name exist"));

    }
    @GetMapping("/get-major/{major}")
    public ResponseEntity<?> getStudentsByMajor(@PathVariable String major){
        ArrayList<Student>majorStudents=students.getStudentsByMajor(major);
        if (!majorStudents.isEmpty()){
            return ResponseEntity.status(200).body(majorStudents);
        }
        return ResponseEntity.status(400).body(new ApiResponse("No Students With that major"));
    }

}
