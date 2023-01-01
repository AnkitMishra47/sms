package com.example.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController //Telling the java application --> this class will contain API endpoints
public class StudentController {


    @Autowired //Automatically takes care of the StudentService Object Creation
    StudentService studentService; //Object has been created --> so that it can call the functions

    //Add a Student
    @PostMapping("/add-student")
    public ResponseEntity<String> addStudent(@RequestBody() Student student){

        //Calling the service layer
        String response = studentService.addStudent(student);
        return new ResponseEntity<>(response,HttpStatus.CREATED);

    }

    //Get a Student by id
    @GetMapping("/get-student")
    public ResponseEntity<Student> getStudentById(@RequestParam("id")Integer id){

        //Call the Service Layer
        Student resultStudent = studentService.getStudentById(id);

        if(resultStudent==null)
        {
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
        else
        {
            return new ResponseEntity<>(resultStudent,HttpStatus.OK);
        }
    }

    @GetMapping("/get-student/{id}")
    public ResponseEntity<Student> getByPath(@PathVariable("id")Integer id){

        //We are reutilizing these functions ---> of the service and repository layer
        Student resultStudent = studentService.getStudentById(id);

        if(resultStudent==null){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(resultStudent,HttpStatus.OK);

    }

    //Get a student by Name
    @GetMapping("/get-student-by-name")
    public ResponseEntity<Student> getStudentByName(@RequestParam("name")String searchName)
    {
        Student resultantStudent = studentService.getStudentByName(searchName);

        if (resultantStudent == null)
        {
            return new ResponseEntity<>(null , HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(resultantStudent,HttpStatus.OK);
    }

    //Update a Student
    @PutMapping("/update-student")
    public ResponseEntity<Student> updateStudent(@RequestBody()Student student){

        //Get the key
        return new ResponseEntity<>(studentService.updateStudent(student),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete-student")
    public ResponseEntity<String> deleteStudent(@RequestParam("id")Integer id){

        String msg = studentService.deleteStudent(id);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

}
