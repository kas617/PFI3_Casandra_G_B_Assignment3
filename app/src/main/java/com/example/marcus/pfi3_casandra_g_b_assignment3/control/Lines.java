package com.example.marcus.pfi3_casandra_g_b_assignment3.control;

import java.util.ArrayList;

public class Lines {
    private ArrayList<Line> lines;
    private Station station;
    //Perhaps more variables and methods?
    /**Creates a list of departing lines from a station*/
    public Lines(Station station) {
        this.station = station;
        lines = new ArrayList<Line>();
    }

    public void addLine(Line line) {
        this.lines.add(line);
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Line> getLines() {
        return lines;
    }

    public void clearJourneys() {
        this.lines.clear();
    }
}