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
        ColaImportService colaImportService = (ColaImportService)context.getBean(ColaImportService.class);
        String colaName = colaImportService.getColaName();
        Integer colaType = colaImportService.getColaType();
        System.out.println("colaName:" + colaName + " colaType:" + colaType);
        System.out.println("ending...");

    }



}
