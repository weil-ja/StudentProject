package com.taotao.manage.pojo;

public class TestStudent extends TestPojo {
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public TestStudent setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public TestStudent setAge(Integer age) {
        this.age = age;
        return this;
    }

    @Override
    public String toString() {
        return "TestStudent{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
