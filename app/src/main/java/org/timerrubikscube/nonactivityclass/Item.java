package org.timerrubikscube.nonactivityclass;

import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class Item {
    float timing;
    Date timestamp;
    long timeFromBeginning;
    String scramble;
    String id;

    public Item() {
    }

    public Item(float timing, Date timestamp, long timeFromBeginning, String scramble){
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


    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
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
