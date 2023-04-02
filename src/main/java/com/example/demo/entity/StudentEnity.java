package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class StudentEnity {
     
    @Id()
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_student")
    @SequenceGenerator(name="seq_student")
    Long id;
    
    String name;
    
    String branch;
    int rollno;

    String stuext;

    public StudentEnity() {
    }

    public StudentEnity(Long id, String name, String branch, int rollno, String stuext) {
        this.name = name;
        this.branch = branch;
        this.rollno = rollno;
        this.stuext=stuext;

    }

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

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public int getRollno() {
        return rollno;
    }

    public void setRollno(int rollno) {
        this.rollno = rollno;
    }

    public String getStuext() {
        return stuext;
    }

    public void setStuext(String stuext) {
        this.stuext = stuext;
    }

    @Override
    public String toString() {
        return "StudentEnity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", branch='" + branch + '\'' +
                ", rollno=" + rollno +
                ", stuext='" + stuext + '\'' +
                '}';
    }
}
