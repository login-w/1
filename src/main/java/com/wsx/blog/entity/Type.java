package com.wsx.blog.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="t_type")
public class Type {
    @Id
    @GeneratedValue
    private int id;

    private  int blognums;



    private String name;
    @OneToMany(mappedBy = "type")
    private List<Blog> blogs=new ArrayList<>();

    public Type() {
    }


    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBlognums() {
        return blognums;
    }

    public void setBlognums(int blognums) {
        this.blognums = blognums;
    }

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", blognums=" + blognums +
                ", name='" + name + '\'' +
                ", blogs=" + blogs +
                '}';
    }
}
