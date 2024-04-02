package com.example.nettysample.client.connection;


import io.netty.channel.Channel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ChannelManager {

    private final Map<String, Channel> channels = new ConcurrentHashMap<>();


    public void putChannel(String key, Channel channel){
        if(StringUtils.isEmpty(key) || channel == null ) return;
        Channel prevChannel = channels.put(key, channel);
        if(prevChannel != null) prevChannel.close();
    }

    public Channel getChannel(String key){
        return StringUtils.isEmpty(key) ? null : channels.get(key);
    }

    public void discardChannel(String key){
        if(StringUtils.isEmpty(key)) return;
        channels.remove(key);
    }
}
