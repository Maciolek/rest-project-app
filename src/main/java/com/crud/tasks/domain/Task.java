package com.crud.tasks.domain;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "tasks")
//@ApiModel
public class Task {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
//    @ApiModelProperty(position = 1, required = true, value = "user's ID")
    private Long id;
    @Column(name = "name")
//    @ApiModelProperty(position = 2, required = true, value = "titile of the task")
    private String title;
//    @ApiModelProperty(position = 3, required = true, value = "description of the task")
    @Column(name = "description")
    private String content;
}
