package com.delicacy.cookies.jvm.loader;

import java.io.File;
import java.io.FileInputStream;

/**
 * todo
 * @author linzhenghui
 * @date 2020/12/4
 */
public class MyClassLoader extends ClassLoader {

    /**
     * loadClass() 方法是加载目标类的入口，它首先会查找当前 ClassLoader 以及它的双亲里面是否已经加载了目标类，如果没有找到就会让双亲尝试加载
     * 如果双亲都加载不了，就会调用 findClass() 让自定义加载器自己来加载目标类。
     *
     * 这个类不要轻易覆盖，容易破坏双清委派
     *
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return super.loadClass(name);
    }

    /**
     * 自定义加载
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        File file = new File("C:\\Users\\linzhenghui\\idea_obj\\cookies\\jdkutil\\target\\classes\\com\\delicacy\\cookies\\javassistutil\\entity\\DemoService.class");
        try{
            byte[] classBytes = getClassBytes(file);
            //defineClass方法可以把二进制流字节组成的文件转换为一个java.lang.Class
            Class<?> c = this.defineClass(name, classBytes, 0, classBytes.length);
            return c;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return super.findClass(name);
    }


    private byte[] getClassBytes(File file) throws Exception {
        // 这里要读入.class的字节，因此要使用字节流
        FileInputStream fis = new FileInputStream(file);
        // ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] byteArray = new byte[(int)file.length()];
        fis.read(byteArray);
        fis.close();
        return byteArray;
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        MyClassLoader myClassLoader = new MyClassLoader();
        Class<?> aClass1 = myClassLoader.loadClass("com.delicacy.cookies.javassistutil.entity.DemoService");

        Class<?> aClass = Class.forName("com.delicacy.cookies.javassistutil.entity.DemoService", true, new MyClassLoader());
        aClass.getName();
        ClassLoader classLoader = aClass.newInstance().getClass().getClassLoader();

        if(classLoader instanceof MyClassLoader) {
            System.out.println("芜湖~");
        } else {
            System.out.println("哦吼~");
        }

        System.out.println("ending...");
    }


}
