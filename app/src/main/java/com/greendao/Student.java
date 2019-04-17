package com.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Unique;

@Entity
public class Student {
    @Unique
    int Id;
    String name;
    String age;
    
    public Student() {
    }

    @Generated(hash = 31613300)
    public Student(int Id, String name, String age) {
        this.Id = Id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return this.Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return this.age;
    }

    public void setAge(String age) {
        this.age = age;
    }


}
