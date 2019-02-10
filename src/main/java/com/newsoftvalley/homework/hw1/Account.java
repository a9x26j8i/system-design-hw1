package com.newsoftvalley.homework.hw1;

public class Account {

    private String name;
    private String email;
    private Integer age;
    private String address;
    private Integer[] creditCards;
    private Long id;

    public Account setName(String name) {
        this.name = name;
        return this;
    }

    public Account setEmail(String email) {
        this.email = email;
        return this;
    }

    public Account setAge(Integer age) {
        this.age = age;
        return this;
    }

    public Account setAddress(String address) {
        this.address = address;
        return this;
    }

    public Account setCreditCards(Integer[] creditCards) {
        this.creditCards = creditCards;
        return this;
    }

    public Account setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Integer getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public Integer[] getCreditCards() {
        return creditCards;
    }

    public Long getId() {
        return id;
    }

}
