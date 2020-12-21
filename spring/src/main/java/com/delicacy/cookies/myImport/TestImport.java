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
        ColaImportService2 colaImportService3 = context.getBean(ColaImportService2.class);
        String colaName = colaImportService3.getColaName();
        Integer colaType = colaImportService3.getColaType();
        System.out.println("colaName:" + colaName + " colaType:" + colaType);
        System.out.println("ending...");

    }



}
