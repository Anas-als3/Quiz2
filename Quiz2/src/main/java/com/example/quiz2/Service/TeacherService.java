package com.example.quiz2.Service;

import com.example.quiz2.Model.Teacher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TeacherService {
    ArrayList<Teacher> teachers = new ArrayList<>();

    public ArrayList<Teacher> getTeachers() {
        return teachers;
    }

    public Teacher addTeacher(Teacher teacher) {
        teachers.add(teacher);
        return teacher;
    }

    public int updateTeacher(String id, Teacher teacher) {
        for (int i = 0; i < teachers.size(); i++) {
            if (teachers.get(i).getId().equalsIgnoreCase(id)) {
                teachers.set(i, teacher);
                return 1;
            }
        }
        return 2;

    }

    public int deleteTeacher(String id) {
        for (int i = 0; i < teachers.size(); i++) {
            if (teachers.get(i).getId().equalsIgnoreCase(id)) {
                teachers.remove(i);
                return 1;
            }
        }
        return 2;
    }
    public Teacher getTeacherById(String id){
        for (int i=0;i<teachers.size();i++){
            if (teachers.get(i).getId().equalsIgnoreCase(id)){
                return teachers.get(i);
            }
        }return null;
    }
    public ArrayList<Teacher> getTeachersBySalary(int salary){
        ArrayList<Teacher>teachersSalary=new ArrayList<>();
        for (int i =0;i<teachers.size();i++){
            if (teachers.get(i).getSalary()>=salary){
                teachersSalary.add(teachers.get(i));
            }

        }return teachersSalary;
    }
}
