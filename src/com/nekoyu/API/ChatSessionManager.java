package com.nekoyu.API;

import java.util.LinkedList;
import java.util.List;

public class ChatSessionManager {
    final List<ChatSession> sessions = new LinkedList<>();

    public void addSession(ChatSession session){
        sessions.add(session);
    }

    public void removeSession(ChatSession session){
        sessions.remove(session);
    }
}
