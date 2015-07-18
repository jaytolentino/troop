package com.linkedladies.troop.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Message implements Parcelable {

    private String sender;
    @SerializedName("message_type") private String messageType;
    private String time;

    private Message(Parcel in) {
        this.sender = in.readString();
        this.messageType = in.readString();
        this.time = in.readString();
    }

    public String getMessageType() {
        return messageType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(sender);
        dest.writeString(messageType);
        dest.writeString(time);
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
