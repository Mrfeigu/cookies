package com.delicacy.cookies.factory.single;

public class test {
    public static void main(String[] args) {
        SimpleFactory factory = SimpleFactory.builder().build();
        BaseVehicle car = factory.create(Plane.class);
        car.use();
        System.out.println(car.getName());
    }



}
