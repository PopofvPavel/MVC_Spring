package com.example.services.implementation;

import com.example.model.Director;
import com.example.repository.DirectorsRepository;
import com.example.services.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectorServiceImpl implements DirectorService {

    DirectorsRepository directorsRepository;

    @Autowired
    public void setDirectorsRepository(DirectorsRepository directorsRepository) {
        this.directorsRepository = directorsRepository;
    }

    @Override
    public void createDirector(Director director) {
        directorsRepository.addDirector(director);
    }

    @Override
    public void updateDirector(Director director) {
        directorsRepository.updateDirector(director);
    }

    @Override
    public Director getDirectorByFullName(String fullName) throws DirectorNotFoundException {

            return directorsRepository.getDirectorByFullName(fullName);

    }

    @Override
    public void deleteDirectorByFullName(String fullName) throws DirectorNotFoundException {
        Director director = getDirectorByFullName(fullName);
        directorsRepository.deleteDirector(director);
    }

    @Override
    public List<Director> getDirectors() {
        return directorsRepository.getDirectors();
    }
}
