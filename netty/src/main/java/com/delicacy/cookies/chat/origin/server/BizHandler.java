package com.delicacy.cookies.chat.origin.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author linzhenghui
 * @date 2020/11/17
 */
@ChannelHandler.Sharable
public class BizHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) {
        System.out.println(msg);
        Channel channel = ctx.channel();
        channel.writeAndFlush(msg);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // 当出现异常关闭连接
        Channel incoming = ctx.channel();
        ctx.close();
    }

}
