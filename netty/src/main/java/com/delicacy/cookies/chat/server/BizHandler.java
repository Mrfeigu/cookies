package com.delicacy.cookies.chat.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author linzhenghui
 * @date 2020/11/17
 */
@ChannelHandler.Sharable
public class BizHandler extends SimpleChannelInboundHandler<String> {

    /** 管理所有channel组*/
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();
        channel.writeAndFlush(msg + "\n");
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // 当出现异常关闭连接
        Channel incoming = ctx.channel();
        ctx.close();
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {

        Channel channel = ctx.channel();
        if(null == channelGroup.find(channel.id())){
            channelGroup.add(channel);
        }

        /** 当前channel组广播*/
        channelGroup.writeAndFlush(channel.remoteAddress() + ": 上线啦" + "\n");
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
        //无需手工的移除，  会自动将断掉的链接移除。
        channelGroup.remove(channel);
    }

    /**
     * 活跃定义
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress()+" 上线");
    }

    /**
     * 不活跃定义
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress()+" 下线");
    }

    /**
     * 可以实现心跳检测
     * 监听信道空闲实现内容
     *
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

        IdleStateEvent stateEvent = (IdleStateEvent) evt;

        switch (stateEvent.state()) {
            case READER_IDLE:
                // 读通道处于空闲状态
                break;
            case WRITER_IDLE:
                //  写通道处于空闲状态
                break;
            case ALL_IDLE:
                // 全部通道
                break;
            default:
                break;
        }




    }



}
