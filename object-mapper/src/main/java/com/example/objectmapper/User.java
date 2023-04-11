package com.example.objectmapper;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    private String name;
    private int age;
    @JsonProperty("phone_number")
    private String phoneNumber;

    public String getphoneNumber() {
        return phoneNumber;
    }

    public User() {
        this.name = null;
        this.age=0;
        this.phoneNumber=null;
    }
    public User (String name, int age, String phoneNumber){
        this.name=name;
        this.age=age;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

//    object wapper를 사용하려면 꼭꼭꼭 method에 get을 빼줘야한다!!!!!


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}