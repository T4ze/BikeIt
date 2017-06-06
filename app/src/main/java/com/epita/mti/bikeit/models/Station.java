package com.epita.mti.bikeit.models;

/**
 * Created by T4ze on 24/05/2017.
 */

public class Station {
    private final int id;
    private final Record record;

    public Station(int id, Record record) {
        this.id = id;
        this.record = record;
    }

    public int getId() {
        return id;
    }

    public Record getRecord() {
        return record;
    }
}
