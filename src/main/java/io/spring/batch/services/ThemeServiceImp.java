package io.spring.batch.services;

import io.spring.batch.domain.Theme;
import io.spring.batch.repositories.ThemeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Hosni on 15/03/2017.
 */
@Service
public class ThemeServiceImp implements ThemeService {

    @Autowired
    private ThemeRepo themeRepo;

    @Override
    public List<Theme> findAll() {
       return themeRepo.findAll();
    }

    @Override
    public void save(Theme theme) {

        themeRepo.save(theme);

    }
}
