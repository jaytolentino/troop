package com.linkedladies.troop.helpers;

import android.util.Log;

public class Constants {

    public enum SessionAction {
        LOVE("Love"),
        HELP("Help"),
        RECOVER("Recover"),
        SUPPORT("Support");

        private String prettyName;

        SessionAction(String prettyName) {
            this.prettyName = prettyName;
        }

        public static SessionAction fromString(String name) {
            for (SessionAction action : SessionAction.values()) {
                if (action.toString().equals(name)) {
                    return action;
                }
            }
            throw new EnumConstantNotPresentException(SessionAction.class,
                    "Could not find enum with name " + name);
        }

        @Override
        public String toString() {
            return prettyName;
        }
    }

    public enum UserState {
        NORMAL("normal"),
        REQUESTS_SUPPORT("requests support"),
        UNDECIDED("undecided"),
        SUPPORTS("supports"),
        RECOVERABLE("recoverable");

        private String prettyName;

        UserState(String prettyName) {
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
                    Log.e(UserState.class.getSimpleName(), "Unrecognized UserState enum: " + name);
                    return NORMAL;
            }
        }

        @Override
        public String toString() {
            return prettyName;
        }
    }
}
