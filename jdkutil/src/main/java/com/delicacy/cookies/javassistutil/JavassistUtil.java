package com.delicacy.cookies.javassistutil;

import com.delicacy.cookies.javassistutil.entity.Person;
import javassist.*;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * java 字节码增强工具
 * 字节码工具无敌存在
 * <p>
 * 能create，modify
 * 能修改动态，静态方法与变量
 * <p>
 * 可以通过反射去获取
 * <p>
 * 参考：
 * https://juejin.im/post/5c693e16f265da2db1560939
 * https://www.cnblogs.com/rickiyang/p/11336268.html
 *
 * @author linzhenghui
 * @date 2020/7/14
 */
public class JavassistUtil {


    // 是 ClassPool 会在内存中维护所有被它创建过的 CtClass，
    // 当 CtClass 数量过多时，会占用大量的内存，
    // API中给出的解决方案是有意识的调用CtClass的detach()方法以释放内存。
    static ClassPool pool = ClassPool.getDefault();

    // 将修改后的CtClass加载至当前线程的上下文类加载器中
    // 需要注意的是一旦调用该方法，则无法继续修改已经被加载的class;
    // pool.toClass(cc);


    /**
     * 创造字节码
     *
     * @throws NotFoundException
     * @throws CannotCompileException
     * @throws IOException
     */
    public static CtClass createPerson() throws NotFoundException, CannotCompileException, IOException {


        // 1. 创建一个空类
        CtClass cc = pool.makeClass("com.delicacy.cookies.javassistutil.entity.Person1");

        // cc.freeze(); 冻结一个类，使其不可修改
        // cc.isFrozen(); 判断一个类是否已被冻结
        // cc.prune();  删除类不必要的属性，以减少内存占用。调用该方法后，许多方法无法将无法正常使用，慎用；
        // cc.defrost(); 冻一个类，使其可以被修改。如果事先知道一个类会被defrost， 则禁止调用 prune 方法；
        // cc.detach(); 将该class从ClassPool中删除；
        // cc.writeFile();  根据CtClass生成 .class 文件；
        // cc.toClass() : 通过类加载器加载该CtClass。

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
        // cc.writeFile("C:\\Users\\Administrator\\IdeaProjects\\cookies\\jdkutil\\target\\classes");
        return cc;
    }

    /**
     * 由于没有实现类，只有编译后的class文件，所以每次用反射去执行与获取相当麻烦，而且开销也大，这个时候可以考虑用接口实现
     * 反射加载字节码
     */
    public static void reflectLoad(CtClass cc) throws Exception {
        Object person1 = cc.toClass().newInstance();
        Method setName = person1.getClass().getMethod("setName", String.class);
        setName.invoke(person1, "袁华");

        Method printName = person1.getClass().getMethod("printName");
        printName.invoke(person1);
    }

    /**
     * 通过接口去修改，能够避免使用反射
     * @throws Exception
     */
    public static void interfaceLoad() throws Exception {
        // 获取接口
        CtClass ctClass = pool.get("com.delicacy.cookies.javassistutil.entity.Person");
        // 获取实现类
        CtClass cc = pool.get("com.delicacy.cookies.javassistutil.entity.Person1");
        // 设置继承关系
        cc.setInterfaces(new CtClass[]{ctClass});

        Person person = (Person)cc.toClass().newInstance();
        person.setName("彩霞");
        person.printName();
    }

    /**
     * 增强DemoService
     */
    public static void modifyExist() throws Exception {

        CtClass cc = pool.get("com.delicacy.cookies.javassistutil.entity.DemoService");

        CtMethod ctMethod = cc.getDeclaredMethod("delete");
        cc.removeMethod(ctMethod);
        // todo


    }



    public static void main(String[] args) throws Exception {
        CtClass cc = createPerson();
        // reflectLoad(cc);
        interfaceLoad();
    }


}
