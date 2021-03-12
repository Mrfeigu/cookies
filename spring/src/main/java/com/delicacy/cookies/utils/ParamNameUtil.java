package com.delicacy.cookies.utils;

import com.sun.deploy.util.StringUtils;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.PrioritizedParameterNameDiscoverer;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @author linzhenghui
 * @date 2021/3/12
 */
public class ParamNameUtil {

    /**
     * 通过spring工具获取参数
     * @param method 方法名
     * @param param 参数
     * @param i 参数序号
     * @return
     */
    public static String getParamName(Method method, Parameter param, int i) {

        if (param.isNamePresent()) {
            return param.getName();
        }

        LocalVariableTableParameterNameDiscoverer lvtpnd = new LocalVariableTableParameterNameDiscoverer();
        String[] paramNames = lvtpnd.getParameterNames(method);

        if (paramNames != null && paramNames.length >= i + 1) {
            return paramNames[i];
        }

        PrioritizedParameterNameDiscoverer ppnd = new PrioritizedParameterNameDiscoverer();
        paramNames = ppnd.getParameterNames(method);

        if (paramNames != null && paramNames.length >= i + 1) {
            return paramNames[i];
        }

        return null;
    }


}
