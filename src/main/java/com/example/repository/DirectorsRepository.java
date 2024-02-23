package com.example.repository;

import com.example.model.Director;
import com.example.services.exceptions.DirectorNotFoundException;

import java.util.List;

public interface DirectorsRepository {
    void addDirector(Director director);

    void deleteDirector(Director director);

    void updateDirector(Director director);

    int getDirectorIndexInListByName(Director director);

    Director getDirectorByFullName(String fullName) throws DirectorNotFoundException;

    List<Director> getDirectors();
}
