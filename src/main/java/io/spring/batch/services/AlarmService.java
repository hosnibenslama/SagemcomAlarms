package io.spring.batch.services;

import io.spring.batch.domain.Alarm;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;


public interface AlarmService {

    void generateFileOfAlarms(List<Alarm> alarms) throws IOException;


}




