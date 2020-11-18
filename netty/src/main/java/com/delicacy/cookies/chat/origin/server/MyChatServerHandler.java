package com.delicacy.cookies.chat.origin.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * 参考
 */
public class MyChatServerHandler extends SimpleChannelInboundHandler<String> {

    /**
     * 用来保存一个个的channel对象的。
     */
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * 收到任何一个消息之后，的回调函数
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.forEach(ch -> {
            if (channel != ch) {
                ch.writeAndFlush(channel.remoteAddress() + "发送的消息：" + msg + "\n");
            } else {
                channel.writeAndFlush("【自己】" + "发送的消息：" + msg + "\n" );
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    /**
     * 1.当连接建立好的时候，就代表有一个客户端和服务端建立起连接了。  handlerAdded
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        //1.建立起连接
        Channel channel = ctx.channel();
        //2.进行广播。
        channelGroup.writeAndFlush("【服务器】：" + channel.remoteAddress() + "加入\n");
        //3.添加到组
        channelGroup.add(channel);
    }

    /**
     * 2.当离开的时候
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("【服务器】：" + channel.remoteAddress() + "离开 \n ");
        //无需手工的移除，  会自动将断掉的链接移除  。 现在先不管，以后可以看看
        // channelGroup.remove(channel);
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress()+" 上线");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress()+" 下线");
    }
}