package com.edu.ulab.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("ALL")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntityUser extends EntityStorage {


    private String fullName;
    private String title;
    private int age;
    private Set<Long> booksId = new HashSet<>();

    public void update(EntityUser user){
        this.age = user.getAge();
        this.title = user.getTitle();
        this.fullName = user.getFullName();
        this.id = user.getId();
    }
}
