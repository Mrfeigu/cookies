package com.delicacy.cookies.ioc.postorocessor;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.xml.NamespaceHandler;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * NamespaceHandler 是 Spring 提供的 命名空间处理器（实际上也没发现作用）
 *
 *
 *
 * @author linzhenghui
 * @date 2020/12/21
 */
@Service
public class NamespaceHandlerService implements NamespaceHandler {

    @Override
    public void init() {
        System.out.println("init");
    }

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        System.out.println("parse");
        return null;
    }

    @Override
    public BeanDefinitionHolder decorate(Node source, BeanDefinitionHolder definition, ParserContext parserContext) {
        System.out.println("decorate");
        return null;
    }

    public static void main(String[] args) {

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext("com.delicacy.cookies.ioc");
        NamespaceHandlerService bean = ac.getBean(NamespaceHandlerService.class);
        System.out.println("ending...");

    }


}
