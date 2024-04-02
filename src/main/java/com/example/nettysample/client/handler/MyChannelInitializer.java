package com.example.nettysample.client.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.util.function.Consumer;

public class MyChannelInitializer {

    public ChannelInitializer<SocketChannel> createDefaultChannelInitializer(Consumer<SocketChannel> handlerConfigurer){
        return new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) {
                handlerConfigurer.accept(ch);
                ch.pipeline().addLast(new StringEncoder());
            }
        };
    }

}
