package com.example.nettysample.client;


import com.example.nettysample.client.connection.ChannelManager;
import com.example.nettysample.client.connection.ConnectionInfo;
import com.example.nettysample.client.handler.MyChannelInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class NettyTcpClient {

    private final Bootstrap bootstrap;
    private final ChannelManager channelManager;


    public void connect(ConnectionInfo... infos){
        for(ConnectionInfo info : infos){
            _connect(info);
        }
    }

    //TODO message arg
    public void writeAndFlush(ConnectionInfo info, String message){
        if(!isConnected(info)){
            reconnect(info);
        }

        Channel channel = channelManager.getChannel(info.getUniqueId());
        channel.writeAndFlush(message);
    }

    private void reconnect(ConnectionInfo info) {
        _connect(info);
        if(!isConnected(info))
            throw new RuntimeException("failed to connect");
    }

    private void _connect(ConnectionInfo info) {
        if(!validateInfo(info)) return;
        if(isConnected(info)) return;

        try {
            MyChannelInitializer initializer = new MyChannelInitializer();
            ChannelFuture channelFuture = bootstrap.handler(initializer.createDefaultChannelInitializer(channel -> {
                info.getHandlers().forEach(h -> channel.pipeline().addLast(h));
            })).connect().sync();

            channelManager.putChannel(info.getUniqueId(), channelFuture.channel());

        } catch (Exception e) {
            //TODO
            log.error("", e);
        }
    }

    private boolean isConnected(ConnectionInfo info) {
        Channel channel = channelManager.getChannel(info.getUniqueId());
        return (channel != null && channel.isActive());
    }

    private boolean validateInfo(ConnectionInfo info) {
        //TODO validate ip with regexp
        return (info != null && StringUtils.isNotEmpty(info.getIp()) && info.getPort() != 0);
    }
}
