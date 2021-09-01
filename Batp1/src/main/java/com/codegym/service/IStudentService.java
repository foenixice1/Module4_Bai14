package com.codegym.service;

import com.codegym.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;

public interface IStudentService extends IGeneralService<Student> {
    ArrayList<Student> findAllByName(String name);
    ArrayList<Student> showAllStudent();
    Page<Student> fillAll(Pageable pageable);
}
