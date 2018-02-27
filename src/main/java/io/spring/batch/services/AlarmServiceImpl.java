package io.spring.batch.services;

import io.spring.batch.domain.Alarm;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class AlarmServiceImpl implements AlarmService {

    private static final String FILENAME = "C:\\Users\\g361121.ADSLOCAL\\Desktop\\Hedi\\filename.txt";



    @Override
    public void generateFileOfAlarms(List<Alarm> alarms) throws IOException {

        StringBuilder values = new StringBuilder();

        for (Alarm alarm : alarms){
            buildTopologyBulkQuery(values , alarm);
        }

        writeToFile(values.toString());
    }

    private void buildTopologyBulkQuery(StringBuilder values, Alarm alarm){

        values.append("{\"index\":{\"_index\":\"").append("nms_alarms2017-06-01")
                .append("\",\"_type\":\"").append("devices_alarms").append("\"}}\n");
        values.append("{\"code\":").append('"').append(alarm.getCodeCIS()).append("\",");
        values.append("\"description\":").append('"').append(alarm.getDescription()).append("\"");
        values.append("}\n");

    }


    public  void writeToFile(String text) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME))) {
            bw.write(text);

        } catch (IOException e) {

            e.printStackTrace();

        }

    }


}
