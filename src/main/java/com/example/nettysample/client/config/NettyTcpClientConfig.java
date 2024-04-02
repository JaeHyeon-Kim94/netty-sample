package com.example.nettysample.client.config;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NettyTcpClientConfig {

    @Value("${netty.tcp.client.connection-timeout-millis}")
    private int connTimeoutMil;

    @Value("${netty.tcp.client.keep-alive}")
    private boolean keepAlive;

    @Value("${netty.tcp.client.group-quantity}")
    private int groupQuantity;

    @Bean
    public Bootstrap bootstrap(EventLoopGroup nioEventLoopGroup){
        Bootstrap b = new Bootstrap();
        b.group(nioEventLoopGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, keepAlive)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, connTimeoutMil);

        return b;
    }

    @Bean
    public EventLoopGroup nioEventLoopGroup(){
        return new NioEventLoopGroup(groupQuantity);
    }

}
