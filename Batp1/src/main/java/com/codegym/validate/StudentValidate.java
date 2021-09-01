package com.codegym.validate;

import com.codegym.model.Student;
import com.codegym.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class StudentValidate implements Validator {
    @Autowired
    IStudentService iStudentService;
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Iterable<Student> list = iStudentService.findAll();
        Student student = (Student) target;
        for (Student s : list) {
            if(s.getEmail().equals(student.getEmail())) {
                errors.rejectValue("email" , "email.duplicate");
                break;
            }
        }
    }
}
