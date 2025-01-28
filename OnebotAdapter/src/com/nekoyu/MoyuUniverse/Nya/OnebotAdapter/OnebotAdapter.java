package com.nekoyu.MoyuUniverse.Nya.OnebotAdapter;

import com.nekoyu.API.MessageChannel;
import com.nekoyu.ConfigureProcessor;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.io.File;
import java.io.IOException;

public class OnebotAdapter extends MessageChannel {
    ConfigureProcessor configure;
    WebSocketClient webSocketClient;

    public OnebotAdapter() {

    }

    @Override
    public void onPrepare() {
        File configFileLoc = new File("./config/Onebot.yml");
        if (configFileLoc.isFile()) { // 已经有了，直接用
            configure = new ConfigureProcessor("YAML://./config/Onebot.yml");
        } else if (!configFileLoc.exists()) { // 这个配置文件不存在，创建一个
            try {
                configFileLoc.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (configFileLoc.isDirectory()) { // 这里就不是我们该管的了，抽象

        }
    }

    @Override
    public void run() {

    }

    @Override
    public void onStop() {

    }
}