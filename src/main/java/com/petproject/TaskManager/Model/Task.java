package com.petproject.TaskManager.Model;

import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.UUID;
@Data
public class Task {
    private String id;
    private String name;
    private String description;
    private int priority;
    private String status;

    public Task() {
        this.id = UUID.randomUUID().toString();
    }

    public Object getDateCreated() {
        return null;
    }
}
