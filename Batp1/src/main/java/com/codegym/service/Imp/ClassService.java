package com.codegym.service.Imp;

import com.codegym.model.ClassRoom;
import com.codegym.repository.IClassRoomRepo;
import com.codegym.service.IClassService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ClassService implements IClassService {
    @Autowired
    IClassRoomRepo iClassRoomRepo;
    @Override
    public Iterable findAll() {
        return iClassRoomRepo.findAll();
    }

    @Override
    public Optional findById(Integer id) {
        return iClassRoomRepo.findById(id);
    }

    @Override
    public void save(ClassRoom classRoom) {
        iClassRoomRepo.save(classRoom);
    }


    @Override
    public void delete(Integer id) {
        iClassRoomRepo.deleteById(id);
    }
}
