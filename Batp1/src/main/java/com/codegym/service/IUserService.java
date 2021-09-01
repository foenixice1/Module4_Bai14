package com.codegym.service;

import com.codegym.model.AppUser;

import java.util.ArrayList;

public interface IUserService {
    ArrayList<AppUser> findAll();
    AppUser findById(int id);
}
