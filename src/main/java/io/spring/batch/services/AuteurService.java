package io.spring.batch.services;

import io.spring.batch.domain.Auteur;

import java.util.List;

/**
 * Created by Hosni on 15/03/2017.
 */
public interface AuteurService {

    public List<Auteur> findAll();

    public void save(Auteur auteur);


}
