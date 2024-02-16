package com.example.repository;

import com.example.database.DataBase;
import com.example.model.Director;
import com.example.services.implementation.DirectorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Repository
public class DirectorsRepository {

    DataBase dataBase;

    @Autowired
    public void setDataBase(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    private List<Director> directors;

    @PostConstruct
    public void init() {
        directors = dataBase.getDirectors();
    }

    public void addDirector(Director director) {
        this.directors.add(director);
    }

    public void deleteDirector(Director director) {
        int index = getDirectorIndexInListByName(director);
        directors.remove(index);
    }

    public void updateDirector(Director director) {
        int index = getDirectorIndexInListByName(director);
        directors.set(index, director);
    }



    public int getDirectorIndexInListByName(Director director) {
        for (int i = 0; i < directors.size(); i++) {
            Director currentDirector = directors.get(i);
            if (director.getFullName().equals(currentDirector.getFullName())) {
                return i;
            }
        }
        return -1;
    }

    public Director getDirectorByFullName(String fullName) throws DirectorNotFoundException {
        Optional<Director> result = directors.stream()
                .filter(director -> director.getFullName().equals(fullName))
                .findFirst();
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new DirectorNotFoundException();
        }
    }

    public List<Director> getDirectors() {
        return directors;
    }
}
