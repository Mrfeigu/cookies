package com.delicacy.cookies.factory.single;


public class Plane extends BaseVehicle{

    Plane(String name){
        super(name);
    }


    @Override
    public void use() {
        System.out.println(this.getName());
        System.out.println("呼呼呼呼");
    }


}
