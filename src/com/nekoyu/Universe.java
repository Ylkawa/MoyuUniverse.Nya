package com.nekoyu;

import com.nekoyu.LawsLoader.LawsManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class Universe {
    static public Logger logger = LoggerFactory.getLogger(Universe.class);
    static public ConfigureProcessor PublicConfig = new ConfigureProcessor("YAML://config.yml", logger);

    public static void main(String[] args) {
        {
            File[] necessaryDictionaries = {new File("./config/"), new File("./laws/")};
            for (File necessaryDictionary : necessaryDictionaries) {
                if (!necessaryDictionary.exists()) {
                    necessaryDictionary.mkdir();
                } else if (necessaryDictionary.isFile()) {

                }
            }
        }

        File lawsDir = new File("laws/");
        if (lawsDir.exists()) {
            if (!lawsDir.isDirectory()) {
                logger.warn("无法加载宇宙法则，因为laws路径被文件占用");
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
            logger.info("Exiting");
            lawsManager.disableLaws();
        }));
    }
}