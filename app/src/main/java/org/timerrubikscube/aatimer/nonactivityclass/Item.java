package org.timerrubikscube.aatimer.nonactivityclass;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.firestore.Exclude;

import java.util.Date;

public class Item implements Parcelable {
    float timing;
    String timestamp; //date -> string
    long timeFromBeginning;
    String scramble;
    String id;
    Boolean isOk;
    Boolean isDNF;
    Boolean isPlus2;

    public Item() {
    }

    public Item(float timing, String timestamp, long timeFromBeginning, String scramble, Boolean isOk, Boolean isDNF, Boolean isPlus2){
        this.timing = timing;
        this.timestamp = timestamp;
        this.timeFromBeginning = timeFromBeginning;
        this.scramble = scramble;
        this.isOk = isOk;
        this.isDNF = isDNF;
        this.isPlus2 = isPlus2;
    }

    protected Item(Parcel in) {
        timing = in.readFloat();
        timestamp = in.readString();
        timeFromBeginning = in.readLong();
        scramble = in.readString();
        id = in.readString();
        byte tmpIsOk = in.readByte();
        isOk = tmpIsOk == 0 ? null : tmpIsOk == 1;
        byte tmpIsDNF = in.readByte();
        isDNF = tmpIsDNF == 0 ? null : tmpIsDNF == 1;
        byte tmpIsPlus2 = in.readByte();
        isPlus2 = tmpIsPlus2 == 0 ? null : tmpIsPlus2 == 1;
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    public Boolean getOk() {
        return isOk;
    }

    public void setOk(Boolean ok) {
        isOk = ok;
    }

    public Boolean getDNF() {
        return isDNF;
    }

    public void setDNF(Boolean DNF) {
        isDNF = DNF;
    }

    public Boolean getPlus2() {
        return isPlus2;
    }

    public void setPlus2(Boolean plus2) {
        isPlus2 = plus2;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(timing);
        dest.writeString(timestamp);
        dest.writeLong(timeFromBeginning);
        dest.writeString(scramble);
        dest.writeString(id);
        dest.writeByte((byte) (isOk == null ? 0 : isOk ? 1 : 2));
        dest.writeByte((byte) (isDNF == null ? 0 : isDNF ? 1 : 2));
        dest.writeByte((byte) (isPlus2 == null ? 0 : isPlus2 ? 1 : 2));
    }
}
