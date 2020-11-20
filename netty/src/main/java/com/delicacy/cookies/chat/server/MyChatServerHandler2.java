package com.delicacy.cookies.chat.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 参考
 */
public class MyChatServerHandler2 extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(msg);

        // todo 使用ctx.fireChannelRead，才能传递到下一个Handler
        ctx.fireChannelRead(msg + "\n");
    }


}