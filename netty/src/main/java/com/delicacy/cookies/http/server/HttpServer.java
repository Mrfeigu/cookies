package com.delicacy.cookies.http.server;

import com.delicacy.cookies.chat.server.SocketServerChannelInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author linzhenghui
 * @date 2020/11/23
 */
public class HttpServer {


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
                    .childHandler(new HttpServerChannelInitializer());

            ChannelFuture channelFuture = serverBootstrap.bind(8081).sync();
            channelFuture.channel().closeFuture().sync();

        } finally {
            //关闭循环组
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }



}
