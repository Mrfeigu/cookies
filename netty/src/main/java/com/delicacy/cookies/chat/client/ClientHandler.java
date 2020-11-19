package com.delicacy.cookies.chat.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 参考
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {

    private ChannelHandlerContext ctx;
 
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        this.ctx = ctx;
        System.out.println("用户成功连接服务器");
        ctx.channel().writeAndFlush("wocao");
    }
 
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println((String) msg);
    }
 
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("与服务器断开连接：" + cause.getMessage());
        ctx.close();
    }
}