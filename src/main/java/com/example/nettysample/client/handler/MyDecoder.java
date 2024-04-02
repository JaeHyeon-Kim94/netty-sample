package com.example.nettysample.client.handler;

import com.example.nettysample.client.model.FormatInfo;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public abstract class MyDecoder extends ByteToMessageDecoder {

    private List<FormatInfo> infoList;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

    }


}
