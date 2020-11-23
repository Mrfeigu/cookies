package com.delicacy.cookies.http.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.*;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * @author linzhenghui
 * @date 2020/11/17
 */
@ChannelHandler.Sharable
public class HttpBizHandler extends ChannelInboundHandlerAdapter {

    private static final String FAVICON_ICO = "/favicon.ico";

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        System.out.println("可以");

        ByteBuf content = Unpooled.copiedBuffer("Hello World!", CharsetUtil.UTF_8);
        DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                HttpResponseStatus.OK,
                content);

        response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");
        response.headers().set(HttpHeaderNames.CONTENT_LENGTH,content.readableBytes());

        ctx.writeAndFlush(response);
        ctx.channel().close();

        ReferenceCountUtil.release(msg);
    }




}
