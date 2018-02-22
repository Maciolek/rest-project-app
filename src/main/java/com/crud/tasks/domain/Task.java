package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "tasks")
public class Task {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long Id;
    @Column(name = "name")
    private String title;
    @Column(name = "description")
    private String content;
}
