package io.spring.batch.domain;

import java.util.List;

public class M2M {

    private List<Alarm> alarms;

    public List<Alarm> getAlarms() {
        return alarms;
    }

    public void setAlarms(List<Alarm> alarms) {
        this.alarms = alarms;
    }
}
