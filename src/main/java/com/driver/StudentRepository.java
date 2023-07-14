package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentRepository {
    Map<String,Student> studentDB = new HashMap<>();
    Map<String,Teacher> teacherDB = new HashMap<>();
    Map<String, List<String>> studentTeacherPair = new HashMap<>();
    public void addStudent(Student student) {
        studentDB.put(student.getName(), student);
    }

    public void addTeacher(Teacher teacher) {
        teacherDB.put(teacher.getName(), teacher);
    }

    public void addStudentTeacherPair(String student, String teacher) {

        if(!studentTeacherPair.containsKey(teacher)){
            List<String> temp = new ArrayList<>();
            temp.add(student);
            studentTeacherPair.put(teacher,temp);
        }
        else{
            List<String> temp = studentTeacherPair.get(teacher);
            temp.add(student);
            studentTeacherPair.put(teacher,temp);
        }
    }

    public Student getStudentByName(String name) {
        if(!studentDB.containsKey(name))return null;
        return studentDB.get(name);
    }

    public Teacher getTeacherByName(String name) {
        if(!teacherDB.containsKey(name))return null;
        return teacherDB.get(name);
    }

    public List<String> getStudentsByTeacherName(String teacher) {
        if(!studentTeacherPair.containsKey(teacher))return new ArrayList<>();
        return studentTeacherPair.get(teacher);
    }

    public List<String> getAllStudents() {
        List<String> list = new ArrayList<>(studentDB.keySet());

        return list;
    }

    public void deleteTeacherByName(String teacher) {
        teacherDB.remove(teacher);
        studentTeacherPair.remove(teacher);
    }

    public void deleteAllTeachers() {
        for(String key :teacherDB.keySet()){
            teacherDB.remove(key);
        }
        for(String key : studentTeacherPair.keySet()){
            studentTeacherPair.remove(key);
        }
    }
}
