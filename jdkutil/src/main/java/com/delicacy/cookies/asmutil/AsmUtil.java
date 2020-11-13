package com.delicacy.cookies.asmutil;

import aj.org.objectweb.asm.ClassWriter;
import aj.org.objectweb.asm.MethodVisitor;
import aj.org.objectweb.asm.Opcodes;

import java.lang.reflect.Method;

/**
 * 网上demo
 * asm 字节码增强，生成一个helloworld程序的demo
 * 相对Javassist操作api更多，但相对复杂
 *
 * @author linzhenghui
 * @date 2020/7/14
 */
public class AsmUtil {

    private static byte[] generate() {
        ClassWriter cw = new ClassWriter(0);
        // 定义对象头：版本号、修饰符、全类名、签名、父类、实现的接口
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "com/delicacy/cookies/asmutil/HelloWorld",
                null, "java/lang/Object", null);
        // 添加方法：修饰符、方法名、描述符、签名、抛出的异常
        MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, "main",
                "([Ljava/lang/String;)V", null, null);
        // 执行指令：获取静态属性
        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        // 加载常量 load constant
        mv.visitLdcInsn("HelloWorld!");
        // 调用方法
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        // 返回
        mv.visitInsn(Opcodes.RETURN);
        // 设置栈大小和局部变量表大小
        mv.visitMaxs(2, 1);
        // 方法结束
        mv.visitEnd();
        // 类完成
        cw.visitEnd();
        // 生成字节数组
        return cw.toByteArray();
    }

    /**
     * 自定义ClassLoader以支持加载字节数组形式的字节码
     *
     * @author dadiyang
     */
    static class MyClassLoader extends ClassLoader {
        public Class<?> defineClass(String name, byte[] b) {
            // ClassLoader是个抽象类，而ClassLoader.defineClass 方法是protected的
            // 所以我们需要定义一个子类将这个方法暴露出来
            return super.defineClass(name, b, 0, b.length);
        }
    }

    public static void main(String[] args) throws Exception {
        // 生成二进制字节码
        byte[] bytes = generate();
        // 使用自定义的ClassLoader
        MyClassLoader cl = new MyClassLoader();
        // 加载我们生成的 HelloWorld 类
        Class<?> clazz = cl.defineClass("com.delicacy.cookies.asmutil.HelloWorld", bytes);
        // 反射获取 main 方法
        Method main = clazz.getMethod("main", String[].class);
        // 调用 main 方法
        main.invoke(null, new Object[]{new String[]{}});
    }

}

