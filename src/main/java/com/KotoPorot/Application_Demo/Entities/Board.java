package com.KotoPorot.Application_Demo.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "boards")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private String owner;

    @ManyToMany(mappedBy = "subscribe")
    @JsonManagedReference
    List<Users> subscribers;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public List<Users> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<Users> subscribers) {
        this.subscribers = subscribers;
    }
    public List<Users> addSubscriber(Users user){
       if (user!=null&&!subscribers.contains(user)){
           subscribers.add(user);
           if(!user.getSubscribe().contains(this)){
               user.getSubscribe().add(this);
           }
       }
       return subscribers;
    }
}
