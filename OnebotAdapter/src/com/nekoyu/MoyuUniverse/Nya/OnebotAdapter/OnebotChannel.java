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

public class OnebotChannel extends MessageChannel {
    ConfigureProcessor configure; // 这种靠底层的东西在adapter中实现，明天删了
    WebSocketClient wsConnection;
    boolean isReady = true;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    URI uri;
    // 构建对象的时候，需要同时准备配置
    public OnebotChannel() {
        File configFileLoc = new File("./config/Onebot.yml");
        if (configFileLoc.isFile()) { // 已经有了，直接用
            configure = new ConfigureProcessor("YAML://./config/Onebot.yml", logger);
        } else if (!configFileLoc.exists()) { // 这个配置文件不存在，创建一个
            try {
                configFileLoc.createNewFile();
            } catch (IOException e) {
                isReady = false;
                throw new RuntimeException(e);
            }
        } else if (configFileLoc.isDirectory()) { // 这里就不是我们该管的了，抽象
            isReady = false;
        }

        // 检查 URI 格式
        try {
            uri = new URI((String) configure.getNode("ServiceURI"));
        } catch (URISyntaxException e) {
            stop();
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
        // 至此才加载完配置文件，还没连接
    }

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
        wsConnection = new WebSocketClient(uri) {
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