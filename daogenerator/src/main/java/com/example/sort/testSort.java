package com.example.sort;

import java.util.Comparator;

/**
 * Created by lwh on 2018/8/13.
 */
public class testSort {
    public static void main(String[] args) {
        Dog[] dogs=new Dog[5];
        dogs[0]=new Dog(5,"dog5");
        dogs[1]=new Dog(3,"dog3");
        dogs[2]=new Dog(2,"dog4");
        dogs[3]=new Dog(1,"dog1");
        dogs[4]=new Dog(2,"dog2");

        Sorting.bubbling(dogs, new Comparator<Dog>() {
            @Override public int compare(Dog dog, Dog t1) {
                return dog.getAge()-t1.getAge();
            }
        });

    }
}
