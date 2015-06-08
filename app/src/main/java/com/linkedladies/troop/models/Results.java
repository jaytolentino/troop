package com.linkedladies.troop.models;

import java.util.List;

public class Results {

    private List<Friend> friends;

    private List<Message> chatRoom;

    private Message message;

    public List<Friend> getFriends() {
        return friends;
    }

    public List<Message> getChatRoom() {
        return chatRoom;
    }

    public Message getMessage() {
        return message;
    }
}
