package org.timerrubikscube.aatimer.nonactivityclass;

import com.google.firebase.firestore.Exclude;

import java.util.Date;

public class Item {
    float timing;
    String timestamp; //date -> string
    long timeFromBeginning;
    String scramble;
    String id;

    public Item() {
    }

    public Item(float timing, String timestamp, long timeFromBeginning, String scramble){
        this.timing = timing;
        this.timestamp = timestamp;
        this.timeFromBeginning = timeFromBeginning;
        this.scramble = scramble;
    }

    public float getTiming() {
        return timing;
    }

    public void setTiming(float timing) {
        this.timing = timing;
    }


    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public long getTimeFromBeginning() {
        return timeFromBeginning;
    }

    public void setTimeFromBeginning(long timeFromBeginning) {
        this.timeFromBeginning = timeFromBeginning;
    }

    public String getScramble() {
        return scramble;
    }

    public void setScramble(String scramble) {
        this.scramble = scramble;
    }

    @Exclude
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
