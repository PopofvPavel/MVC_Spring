package com.example.services;

import com.example.model.Director;
import com.example.services.exceptions.DirectorNotFoundException;

import java.util.List;

public interface DirectorService {


    void createDirector(Director director);

    void updateDirector(Director director);

    Director getDirectorByFullName(String fullName) throws DirectorNotFoundException;

    void deleteDirectorByFullName(String fullName) throws DirectorNotFoundException;

    List<Director> getDirectors();
}
