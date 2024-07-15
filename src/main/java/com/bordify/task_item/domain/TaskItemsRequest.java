package com.bordify.task_item.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class TaskItemsRequest {

    private UUID id;
    private String content;

    private UUID taskId;
    private Boolean isDone;

}
