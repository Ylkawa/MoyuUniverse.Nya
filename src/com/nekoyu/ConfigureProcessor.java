package com.nekoyu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class ConfigureProcessor {
    File configureFile = null;
    Map Configure;
    Map<String, String> requiredNodes = new HashMap<>();
    String type = "";
    Logger logger;

    public ConfigureProcessor(String uri){
        setURI(uri);
    }

    public void setURI(String uri) {
        String URI[] = uri.split("://", 2);
        this.configureFile = new File(URI[1]);
        this.type = URI[0];
    }

    public boolean read() throws FileNotFoundException {
        Configure = new Yaml().loadAs(new FileReader(configureFile), Map.class);
        return true;
    }

    public Object getNode(String node){
        String[] keys = node.split("\\.");
        Map<String, Object> currentMap = Configure;
        Object value = null;

        for (String key : keys) {
            value = currentMap.get(key);

            // 如果值是 Map，继续深入
            if (value instanceof Map) {
                currentMap = (Map<String, Object>) value;
            } else {
                // 如果值不是 Map，返回该值（可能是叶子节点）
                return value;
            }
        }

        // 如果循环完成后，value 仍是 Map，意味着这是一个中间节点，返回它
        return value;
    }

    public void setNode(String node, Object value) {
        String[] keys = node.split("\\.");
        Map<String, Object> currentMap = Configure;

        for (int i = 0; i < keys.length - 1; i++) {
            String key = keys[i];

            // 如果不存在当前 key，创建一个新的子 Map
            if (!currentMap.containsKey(key) || !(currentMap.get(key) instanceof Map)) {
                currentMap.put(key, new HashMap<String, Object>());
            }

            currentMap = (Map<String, Object>) currentMap.get(key);
        }

        // 设置最后一个键的值
        currentMap.put(keys[keys.length - 1], value);
    }

    public void requireNode(String node, String pattern) {
        requiredNodes.put(node, pattern);
    }
}
