package com.delicacy.cookies.jvm.finalize;

import lombok.Data;

/**
 * finalize 试验，一般不建议使用finalize
 * @author linzhenghui
 * @date 2021/2/19
 */
public class finalizeDemo {

    public static void main(String[] args) throws InterruptedException {

        UserThree ut = new UserThree();
        ut = null;
        System.gc();

    }


    @Data
    public static class UserThree {
        private Integer id;
        private Integer name;

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("GG");
        }
    }

}


