package com.delicacy.cookies.chat.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 *
 *
 * 参考：https://my.oschina.net/u/4416387/blog/4063604
 *
 * @author linzhenghui
 * @date 2020/11/17
 */
public class ChatServer {

    public static int DEFAULT_PORT = 8888;

    public static void main(String[] args) throws InterruptedException {
        // 定义两个事件组
        // 监听组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // 工作组
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            //服务器端 启动类
            ServerBootstrap serverBootstrap = new ServerBootstrap()
                    // 绑定组
                    .group(bossGroup, workerGroup)
                    // 执行
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new SocketServerChannelInitializer());

            ChannelFuture channelFuture = serverBootstrap.bind(DEFAULT_PORT).sync();
            channelFuture.channel().closeFuture().sync();

        } finally {
            //关闭循环组
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }




}
