package com.linkedladies.troop.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Message implements Parcelable {
    private String id;
    private String title;

    private Message(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
    }

    public String getTitle() {
        return title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
    }

    public static final Creator CREATOR = new Creator() {
        @Override
        public Object createFromParcel(Parcel source) {
            return new Message(source);
        }

        @Override
        public Object[] newArray(int size) {
            return new Message[size];
        }
    };
}
