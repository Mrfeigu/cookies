package com.delicacy.cookies.jvm;

import lombok.Data;

/**
 * 垃圾回收demo
 *
 *
 * @author linzhenghui
 * @date 2020/8/11
 */
public class CycleReference {


    public static CycleReference SAVE_HOOK = null;

    public void isAlive() {
        System.out.println("yes, i am still alive");
    }

    /**
     * 测试对象自救
     * @throws Throwable
     */
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed!");
        // 自救
        CycleReference.SAVE_HOOK = this;
    }

    public static void main(String[] args) throws InterruptedException {

        SAVE_HOOK = new CycleReference();
        SAVE_HOOK = null;
        // full gc
        System.gc();
        //因为finapze方法优先级很低，所以暂停0.5秒以等待它, 不停顿，没有执行完finalize就直接GC掉了
        Thread.sleep(500);
        //
        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("no, i am dead");
        }

    }


    public static void test(String[] args) {

        /**
         * 引用计数算法
         * 引用计数是垃圾收集器中的早期策略。在这种方法中，堆中每个对象实例都有一个引用计数。
         * 当一个对象被创建时，就将该对象实例分配给一个变量，该变量计数设置为1。
         * 当任何其它变量被赋值为这个对象的引用时，计数加1（a = b,则b引用的对象实例的计数器+1），但当一个对象实例的某个引用超过了生命周期或者被设置为一个新值时，对象实例的引用计数器减1。
         * 任何引用计数器为0的对象实例可以被当作垃圾收集。
         * 当一个对象实例被垃圾收集时，它引用的任何对象实例的引用计数器减1。
         *
         *
         * 优缺点：
         * 1. 优点：实现运行简单，引用计数收集器可以很快的执行，交织在程序运行中。对程序需要不被长时间打断的实时环境比较有利。
         * 2. 缺点：无法检测出循环引用。如父对象有一个对子对象的引用，子对象反过来引用父对象。这样，他们的引用计数永远不可能为0。
         */

        // 关联关系的循环引用
        MyObject myObject1 = new MyObject();
        MyObject myObject2 = new MyObject();
        myObject1.setMyObject(myObject2);
        myObject2.setMyObject(myObject1);
    }

    @Data
    public static class MyObject{
        private MyObject myObject;
    }

}
