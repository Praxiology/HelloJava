package com.step.demo.hellojava;

public class TestConstructor {

    /**
     * 如果父类只有带参数的构造方法,子类必须实现带参数的构造方法
     * **/


}
class Parent{

    String name;


    public Parent(String name) {
        this.name = name;
    }
}

class Child extends Parent{

    public Child(String aa) {
        super(aa);
    }
}
