package com.delicacy.cookies.util;

import io.netty.handler.ssl.ClientAuth;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;

/**
 * 返回证书封装SslContext
 * @author linzhenghui
 * @date 2020/11/23
 */
public class SslUtils {


    private static String cerFile = "xxx";

    private static String pemFile = "xxx";

    private static Long cerFileLastModified;
    private static Long pemFileLastModified;

    /**
     * 创建一个证书
     * @return
     * @throws Exception
     */
    public static SslContext createSslContext() throws Exception {
        File file = new File(cerFile);
        File key = new File(pemFile);
        if (file.canRead() && key.canRead()) {
            cerFileLastModified = file.lastModified();
            pemFileLastModified = key.lastModified();
            return SslContextBuilder.forServer(file, key).clientAuth(ClientAuth.NONE).build();
        } else {
            cerFileLastModified = null;
            pemFileLastModified = null;
            return null;
        }
    }

}
