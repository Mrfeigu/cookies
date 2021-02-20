package com.delicacy.cookies.jvm.reference;

import com.delicacy.cookies.jvm.finalize.finalizeDemo;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * 强软弱虚引用
 * 参考：https://juejin.cn/post/6844904085091516430
 *
 * @author linzhenghui
 * @date 2021/2/19
 */
public class StrongReference {

    public static void main(String[] args) {

        /**
         * 强 GC 不收
         */
        finalizeDemo.UserThree userThree = new finalizeDemo.UserThree();
        /**
         * 软 GC 后 内存还不够 收
         * 比较适合做缓存（内存不够就回收）
         */
        SoftReference<finalizeDemo.UserThree> studentSoftReference = new SoftReference<>(new finalizeDemo.UserThree());
        System.out.println("软" + studentSoftReference.get().getName());

        /**
         * 弱 GC就收
         * 可以很清楚的看到明明内存还很充足，但是触发了GC，资源还是被回收了。 弱引用在很多地方都有用到，比如ThreadLocal、WeakHashMap。
         */
        WeakReference<finalizeDemo.UserThree> weakReference = new WeakReference<>(new finalizeDemo.UserThree());
        System.out.println("弱" + weakReference.get().getName());

        /**
         * 虚引用特点之一：无法通过虚引用来获取对一个对象的真实引用。
         * 虚引用的特点之二： 虚引用必须与ReferenceQueue一起使用，当GC准备回收一个对象，如果发现它还有虚引用，就会在回收之前，把这个虚引用加入到与之关联的ReferenceQueue中。
         */
        ReferenceQueue queue = new ReferenceQueue();
        PhantomReference<finalizeDemo.UserThree> phantomReference = new PhantomReference<>(new finalizeDemo.UserThree(), queue);
        System.out.println(phantomReference.get());


        System.out.println("挠耳朵");

    }


}
