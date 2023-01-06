package com.example.sms;


import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class StudentRepository {

    HashMap<Integer,Student> studentDb = new HashMap<>();



    String addStudentToDb(Student student){

        int key = student.id;
        //Add it to the studentDb
        studentDb.put(key,student);

        return "Successfully added";

    }


    Student getStudentFromDb(int id){
        return studentDb.getOrDefault(id, null);
    }

    Student getStudentByNameFromDb(String searchName){

        for(Student s : studentDb.values()){
            if(s.name.equals(searchName)){
                return s;
            }
        }

        return null;
    }

    Student updateStudentInDb(Student student){

        int key = student.id;

        studentDb.put(key,student);

        return student;
    }

    String deleteStudent(int id){
        String IS_DELETED = "The student has been deleted";
        String IS_NOT_AVAILABLE = "The student is not available";

        if (studentDb.containsKey(id)){
            studentDb.remove(id);
            return IS_DELETED;
        }
        return IS_NOT_AVAILABLE;
    }

}
