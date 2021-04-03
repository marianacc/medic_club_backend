package com.Appointment;

import com.Doctor.Doctor;
import com.Interval.ScheduleInterval;
import com.IntervalTaken.IntervalTaken;
import com.Patient.Patient;

import javax.print.Doc;

public class AppointmentModel {

    private IntervalTaken intervalTaken;

    public IntervalTaken getIntervalTaken() {
        return intervalTaken;
    }

    public void setIntervalTaken(IntervalTaken intervalTaken) {
        this.intervalTaken = intervalTaken;
    }




}
