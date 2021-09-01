package com.codegym.repository;

import com.codegym.model.AppUser;
import org.springframework.data.repository.CrudRepository;

public interface IAppUserRepo extends CrudRepository<AppUser, Integer> {
    AppUser findByUsername(String name);
}
