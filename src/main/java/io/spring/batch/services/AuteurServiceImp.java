package io.spring.batch.services;

import io.spring.batch.domain.Auteur;
import io.spring.batch.repositories.AuteurRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Hosni on 15/03/2017.
 */
@Service
public class AuteurServiceImp implements AuteurService {


    @Autowired
    AuteurRepo auteurRepo;

    @Override
    public List<Auteur> findAll() {

        return auteurRepo.findAll();
    }

    @Override
    public void save(Auteur auteur) {


        auteurRepo.save(auteur);

    }
}
