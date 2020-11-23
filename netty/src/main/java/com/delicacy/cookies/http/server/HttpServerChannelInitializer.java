package com.delicacy.cookies.http.server;

import com.delicacy.cookies.chat.server.BizHandler;
import com.delicacy.cookies.chat.server.MyChatServerHandler2;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;


/**
 *
 * @author linzhenghui
 * @date 2020/11/17
 */
public class HttpServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline()
                .addLast("httpServerCodec",new HttpServerCodec())
                .addLast("httpBizHandler", new HttpBizHandler());
    }
}
