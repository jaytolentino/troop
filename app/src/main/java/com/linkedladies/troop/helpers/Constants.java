package com.linkedladies.troop.helpers;

import android.util.Log;

public class Constants {

    public enum UserState {
        NORMAL("normal"),
        REQUESTS_SUPPORT("requests support"),
        UNDECIDED("undecided"),
        SUPPORTS("supports"),
        RECOVERABLE("recoverable");

        private String prettyName;

        private UserState(String prettyName) {
            this.prettyName = prettyName;
        }

        public static UserState fromString(String name) {
            switch(name) {
                case "normal":
                    return NORMAL;
                case "requests_support":
                    return REQUESTS_SUPPORT;
                case "undecided":
                    return UNDECIDED;
                case "supports":
                    return SUPPORTS;
                case "recoverable":
                    return RECOVERABLE;
                default:
                    Log.e(Constants.class.getSimpleName(), "Unrecognized UserState enum: " + name);
                    return NORMAL;
            }
        }

        @Override
        public String toString() {
            return prettyName;
        }
    }
}
