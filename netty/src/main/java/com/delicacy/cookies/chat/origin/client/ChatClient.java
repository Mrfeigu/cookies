package com.delicacy.cookies.chat.origin.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author linzhenghui
 * @date 2020/11/17
 */
public class ChatClient {

    public static int DEFAULT_PORT = 8888;

    public static void main(String[] args) throws IOException, InterruptedException {

        // 循环组，一个就够用了，客户端
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        try {
            //客户端使用的是bootstrap
            Bootstrap bootstrap = new Bootstrap();

            bootstrap.group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChatClientInitializer());


            //注意此处，使用的是connect,不是使用的bind
            bootstrap
                    .connect("127.0.0.1", DEFAULT_PORT)
                    .channel()
                    .closeFuture()
                    .sync();

/*
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            for (; ; ) {
                channel.writeAndFlush(br.readLine()+ "\r\n");
            }
*/

        } finally {
            eventLoopGroup.shutdownGracefully();
        }


    }



}
