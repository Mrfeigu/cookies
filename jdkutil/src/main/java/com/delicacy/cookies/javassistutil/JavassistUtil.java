package com.delicacy.cookies.javassistutil;

import javassist.*;

import java.io.IOException;

/**
 * java 字节码增强工具
 * 能create，还可以modify，delete吗？
 * 可以通过反射去获取
 *
 * 参考：
 * https://juejin.im/post/5c693e16f265da2db1560939
 * https://www.cnblogs.com/rickiyang/p/11336268.html
 *
 * @author linzhenghui
 * @date 2020/7/14
 */
public class JavassistUtil {

    /**
     * 创造字节码
     * @throws NotFoundException
     * @throws CannotCompileException
     * @throws IOException
     */
    public static void createPerson() throws NotFoundException, CannotCompileException, IOException {

        ClassPool pool = ClassPool.getDefault();

        // 1. 创建一个空类
        CtClass cc = pool.makeClass("com.delicacy.cookies.javassistutil.entity.Person1");

        // 2. 新增一个字段 private String name;
        CtField param = new CtField(pool.get("java.lang.String"), "name", cc);// 字段名为name
        param.setModifiers(Modifier.PRIVATE);
        cc.addField(param, CtField.Initializer.constant("xiaolin"));// 初始值是 "xiaolin"

        // 3. 生成 getter、setter 方法
        cc.addMethod(CtNewMethod.setter("setName", param));
        cc.addMethod(CtNewMethod.getter("getName", param));

        // 4. 添加无参的构造函数
        CtConstructor cons = new CtConstructor(new CtClass[]{}, cc);
        cons.setBody("{name = \"xiaoming\";}");
        cc.addConstructor(cons);

        // 5. 添加有参的构造函数
        cons = new CtConstructor(new CtClass[]{pool.get("java.lang.String")}, cc);
        cons.setBody("{$0.name = $1;}");// $0=this / $1,$2,$3... 代表方法参数
        cc.addConstructor(cons);

        // 6. 创建一个名为printName方法，无参数，无返回值，输出name值
        CtMethod ctMethod = new CtMethod(CtClass.voidType, "printName", new CtClass[]{}, cc);
        ctMethod.setModifiers(Modifier.PUBLIC);
        ctMethod.setBody("{System.out.println(name);}");
        cc.addMethod(ctMethod);

        //这里会将这个创建的类对象编译为.class文件
        cc.writeFile("C:\\Users\\linzhenghui\\idea_obj\\cookies\\jdkutil\\target\\classes");
    }

    /**
     * 反射加载字节码
     */
    public static void reflectLoad(){
        // todo 反射加载生成字节码

    }

    public static void main(String[] args) {
        try {
            createPerson();
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
