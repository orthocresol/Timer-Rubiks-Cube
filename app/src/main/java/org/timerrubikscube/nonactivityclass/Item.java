package org.timerrubikscube.nonactivityclass;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class Item {
    float timing;
    Date timestamp;
    int serialID;

    public Item() {
    }

    public Item(float timing, Date date, int serialID){
        this.timing = timing;
        timestamp = date;
        this.serialID = serialID;
    }

    public float getTiming() {
        return timing;
    }

    public void setTiming(float timing) {
        this.timing = timing;
    }


    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getSerialID() {
        return serialID;
    }

    public void setSerialID(int serialID) {
        this.serialID = serialID;
    }
}
