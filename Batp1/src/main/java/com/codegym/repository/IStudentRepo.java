package com.codegym.repository;

import com.codegym.model.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface IStudentRepo extends PagingAndSortingRepository<Student, Integer> {
    @Query(value = "SELECT * FROM qlhocvien.Student join qlhocvien.ClassRoom on qlhocvien.student.id_class = qlhocvien.classroom.id order by  age asc", nativeQuery = true)
    ArrayList<Student> sortAllStudent();

    @Query(value = "SELECT * FROM qlhocvien.Student where name like concat('%',:name,'%')", nativeQuery = true)
    public ArrayList<Student> findAllByName(@Param("name") String name);


}
