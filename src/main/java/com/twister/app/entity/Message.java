package com.twister.app.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "MESSAGES")
@Setter
@Getter
public class Message {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "TEXT")
    private String text;
    @Column(name = "TAG")
    private String tag;

    public Message(){}

    public Message(String text, String tag){
        this.text = text;
        this.tag = tag;}

    @Override
    public String toString() {
        return String.format("id = %d, text = %s, tag = %s", id, text, tag);}
    }

