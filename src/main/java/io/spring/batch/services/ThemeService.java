package io.spring.batch.services;

import io.spring.batch.domain.Theme;

import java.util.List;

/**
 * Created by Hosni on 15/03/2017.
 */
public interface ThemeService {

    public List<Theme> findAll();

    public void save(Theme theme);
}
