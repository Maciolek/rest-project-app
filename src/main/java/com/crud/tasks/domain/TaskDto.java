package com.crud.tasks.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class TaskDto {

    @ApiModelProperty(value = "The id", position = 1)
    private Long id;
    @ApiModelProperty(value = "The title", position = 2)
    private String title;
    @ApiModelProperty(value = "The content", position = 3)
    private String content;

}
