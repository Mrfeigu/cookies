package com.delicacy.cookies.thread.task;

/**
 * @author linzhenghui
 * @date 2020/10/9
 */
public class DemoTask extends BaseTask {


    @Override
    public void run() {

        for (int i = 0; i < 100; i++) {
            System.out.println("this demoTask run " + i);
        }

    }


}
