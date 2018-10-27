package com.example.paulo.marcadortento.Models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class History implements Serializable {

    public int ptsTeam1;
    public int ptsTeam2;
    public String date;
    public String time;

    public History(int ptsTeam1, int ptsTeam2) {
        this.ptsTeam1 = ptsTeam1;
        this.ptsTeam2 = ptsTeam2;

        Date actualDate = Calendar.getInstance().getTime();
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatTime = new SimpleDateFormat("HH:MM");

        this.date = formatDate.format(actualDate);
        this.time = formatTime.format(actualDate);
    }
}
