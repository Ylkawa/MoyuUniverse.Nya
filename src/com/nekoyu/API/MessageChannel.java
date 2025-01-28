package com.nekoyu.API;

import com.nekoyu.LawsLoader.Law;

//比如 bot 这类的提供消息进出的为 MessageChannel
public abstract class MessageChannel extends Law {
    public void sendMessage(String target, String message){

    }
}
