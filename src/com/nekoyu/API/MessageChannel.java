package com.nekoyu.API;

public abstract class MessageChannel {
    String type;

    abstract public Session getSession(String sessionId);
    abstract public void load();
    abstract public void stop();
    abstract public void sendMessage(String message, String sessionId);
}
