package com.delicacy.cookies.base;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Comparator;
import java.util.List;

/**
 * https://www.cnblogs.com/houzheng/p/10903403.html
 * http://ifeve.com/google-guava-ordering/
 * 链式排序器
 * @author linzhenghui
 * @date 2020/10/10
 */
public class GuavaBaseOrderUtils implements Comparable<GuavaBaseOrderUtils.Person> {

    private Person person;

    @Override
    public int compareTo(Person o) {
        // Guava比较器
        return ComparisonChain.start()
                .compare(this.person.age, o.age)
                .compare(this.person.name, o.name)
                .result();
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Person {
        private Integer age;
        private String name;
    }

    public static void createOreing(){
        //对可排序类型做自然排序，如数字按大小，日期按先后排序
        Ordering<Comparable> natural = Ordering.natural();
        //按对象的字符串形式做字典排序
        Ordering.usingToString();
        //把给定的Comparator转化为排序器或者继承Ordering实现自定义排序器
        Ordering<Integer> from = Ordering.from(Ints::compare);
        System.out.println(from.max(5, 6));
    }

    /**
     * 排序器的自然排序
     */
    public static void naturalOrdering(){
        //对可排序类型做自然排序，如数字按大小，日期按先后排序
        Ordering<Integer> natural = Ordering.natural().reverse();
        List<Integer> list = Lists.newArrayList(1,2,3,4);
        list.sort(natural);
    }

    /**
     * 排序器比较器排序
     */
    public static void comparingOrdering(){
        Ordering<Person> natural = Ordering.from(Comparator.comparing(Person::getAge)).reverse();
        List<Person> people = personList();
        people.sort(natural);
    }

    /**
     * 排序器，判断null值
     */
    public static void comparingNull(){
        Ordering<Person> natural = Ordering.from(Comparator.comparing(Person::getAge)).nullsFirst().reverse();
        List<Person> people = personList();
        people.sort(natural);
    }

    public static List<Person> personList(){
        Person person1 = new Person(1, "张三");
        Person person2 = new Person(2, "李四");
        Person person3 = new Person(3, "王五");
        Person person4 = new Person(6, "赵六");
        Person person5 = new Person(null, "田七");
        return Lists.newArrayList(person1, person2, person3, person4, person5);
    }

    public static void main(String[] args) {

    }


}
