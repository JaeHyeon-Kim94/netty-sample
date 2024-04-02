package com.example.nettysample.client.connection;

import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ConnectionInfo {

    private final String uniqueId;
    private final String ip;
    private final int port;
    private final List<ChannelInboundHandlerAdapter> handlers;

    public ConnectionInfo(String uniqueId, String ip, int port, List<ChannelInboundHandlerAdapter> handlers) {
        this.uniqueId = uniqueId;
        this.ip = ip;
        this.port = port;
        this.handlers = new ArrayList<>(handlers);
    }
}
