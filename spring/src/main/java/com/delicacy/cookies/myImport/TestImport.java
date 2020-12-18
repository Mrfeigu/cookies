package com.delicacy.cookies.myImport;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author linzhenghui
 * @date 2020/12/16
 */
public class TestImport {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.delicacy.cookies.myImport");
        ImportDemo importDemo = (ImportDemo)context.getBean(ImportDemo.class);
        ColaImportService2 colaImportService2 = (ColaImportService2)context.getBean(ColaImportService2.class);
        String colaName = colaImportService2.getColaName();
        Integer colaType = colaImportService2.getColaType();
        System.out.println("colaName:" + colaName + " colaType:" + colaType);
        System.out.println("ending...");

    }



}
