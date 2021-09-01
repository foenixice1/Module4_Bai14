package com.codegym.repository;

import com.codegym.model.ClassRoom;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IClassRoomRepo extends PagingAndSortingRepository<ClassRoom, Integer> {
}
