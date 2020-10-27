package com.delicacy.cookies.factory.single;

import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
public class SimpleFactory {

    public BaseVehicle create(Class obj){

        if(obj.equals(Car.class)){
            return new Car("bubu");
        }
        if(obj.equals(Plane.class)){
            return new Plane("huhu");
        }
        return null;

    }



}
