package com.delicacy.cookies.factory.single;


public class Car extends BaseVehicle {

    Car(String name){
        super(name);
    }

    @Override
    public void use() {
        System.out.println();
        System.out.println("卟卟卟卟");
    }

}
