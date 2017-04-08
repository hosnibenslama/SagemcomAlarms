package io.spring.batch.repositories;

import io.spring.batch.domain.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Hosni on 15/03/2017.
 */
@Repository
public interface ThemeRepo extends JpaRepository<Theme, Integer>{


}
