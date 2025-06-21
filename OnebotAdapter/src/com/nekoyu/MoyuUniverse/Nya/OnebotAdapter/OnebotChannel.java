package com.nekoyu.MoyuUniverse.Nya.OnebotAdapter;

import com.nekoyu.API.MessageChannel;
import com.nekoyu.API.Session;
import com.nekoyu.ConfigureProcessor;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class OnebotChannel extends MessageChannel {
    WebSocketClient wsConnection;
    boolean isReady = true;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    URI uri;
    String ID;
    String token;

    // 构建对象的时候，需要同时准备配置
    public OnebotChannel() {}

    @Override
    public Session getSession(String sessionId) {
        Session session = new OnebotSession() {
            @Override
            public void sendMessage() {

            }
        };
        return session;
    }

    @Override
    public void load() {
        Map<String, String> header = new HashMap<>();
        header.put("Authorization", "Bearer " + token);
        wsConnection = new WebSocketClient(uri, header) {
            @Override
            public void onOpen(ServerHandshake serverHandshake) {

            }

            @Override
            public void onMessage(String s) {

            }

            @Override
            public void onClose(int i, String s, boolean b) {

            }

            @Override
            public void onError(Exception e) {

            }
        };
    }

    @Override
    public void stop() {
        isReady = false;
        wsConnection.close();
    }

    @Override
    public void sendMessage(String message, String sessionId) {

    }
}