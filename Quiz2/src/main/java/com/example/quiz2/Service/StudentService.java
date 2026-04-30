package com.example.quiz2.Service;

import com.example.quiz2.Model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StudentService {
    ArrayList<Student> students = new ArrayList<>();

    public ArrayList<Student> getStudents() {
        return students;
    }

    public Student addStudent(Student student) {
        students.add(student);
        return student;
    }

    public int updateStudent(String id, Student student) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equalsIgnoreCase(id)) {
                students.set(i, student);
                return 1;
            }
        }
        return 2;
    }

    public int deleteStudent(String id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equalsIgnoreCase(id)) {
                students.remove(i);
                return 1;
            }
        }
        return 2;
    }
    public Student getStudentByName(String name){
        for (int i=0;i<students.size();i++){
            if (students.get(i).getName().equalsIgnoreCase(name)){
                return students.get(i);
            }
        }return null;
    }
    public ArrayList<Student> getStudentsByMajor(String major){
        ArrayList<Student>majorStudents=new ArrayList<>();
        for (int i=0;i<students.size();i++){
            if (students.get(i).getMajor().equalsIgnoreCase(major)){
                majorStudents.add(students.get(i));
            }
        }return majorStudents;
    }
}
