package com.example.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Wait {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String COLUMN_GUEST_NAME;
    private String COLUMN_PARTY_SIZE;
    private String COLUMN_TIMESTAMP = "timestamp";

    public Wait(String COLUMN_GUEST_NAME, String COLUMN_PARTY_SIZE) {
        this.COLUMN_GUEST_NAME = COLUMN_GUEST_NAME;
        this.COLUMN_PARTY_SIZE = COLUMN_PARTY_SIZE;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCOLUMN_GUEST_NAME() {
        return COLUMN_GUEST_NAME;
    }

    public void setCOLUMN_GUEST_NAME(String COLUMN_GUEST_NAME) {
        this.COLUMN_GUEST_NAME = COLUMN_GUEST_NAME;
    }

    public String getCOLUMN_PARTY_SIZE() {
        return COLUMN_PARTY_SIZE;
    }

    public void setCOLUMN_PARTY_SIZE(String COLUMN_PARTY_SIZE) {
        this.COLUMN_PARTY_SIZE = COLUMN_PARTY_SIZE;
    }

    public String getCOLUMN_TIMESTAMP() {
        return COLUMN_TIMESTAMP;
    }

    public void setCOLUMN_TIMESTAMP(String COLUMN_TIMESTAMP) {
        this.COLUMN_TIMESTAMP = COLUMN_TIMESTAMP;
    }
}

