package com.nekoyu;

import com.nekoyu.API.ChatSessionManager;
import com.nekoyu.LawsLoader.LawsManager;

import java.io.File;

public class Universe {
    static public ConfigureProcessor PublicConfig = new ConfigureProcessor("YAML://config.yml");
    static public ChatSessionManager chatSessionManager = new ChatSessionManager();

    public static void main(String[] args) {

        {
            File[] necessaryDictionaries = {new File("./config/")};
            for (File necessaryDictionary : necessaryDictionaries) {
                if (necessaryDictionary.isDirectory()){
                    continue;
                } else if (!necessaryDictionary.exists()) {
                    necessaryDictionary.mkdir();
                } else if (necessaryDictionary.isFile()) {
                    
                }
            }
        }

        File lawsDir = new File("laws");
        if (lawsDir.exists()) {
            if (!lawsDir.isDirectory()) {
                System.out.println("无法加载宇宙法则，因为laws路径被文件占用");
            }
        } else {
            lawsDir.mkdir();
        }

        LawsManager lawsManager = new LawsManager(lawsDir);

        // 加载配置文件
        PublicConfig.read();

        // 加载宇宙法则
        lawsManager.loadLaws();
        lawsManager.enableLaws();


        //程序退出动作
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Exiting...");
            lawsManager.disableLaws();
        }));
    }
}