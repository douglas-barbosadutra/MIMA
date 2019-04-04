package com.mima.machine.service.dto;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the TaskScheduled entity.
 */
public class TaskScheduledDTO implements Serializable {

    private Long id;

    private String name;


    private Set<TaskScheduledDTO> fathers = new HashSet<>();

    private Long schedulingId;

    private Long taskId;

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

    public Set<TaskScheduledDTO> getFathers() {
        return fathers;
    }

    public void setFathers(Set<TaskScheduledDTO> taskScheduleds) {
        this.fathers = taskScheduleds;
    }

    public Long getSchedulingId() {
        return schedulingId;
    }

    public void setSchedulingId(Long schedulingId) {
        this.schedulingId = schedulingId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TaskScheduledDTO taskScheduledDTO = (TaskScheduledDTO) o;
        if (taskScheduledDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), taskScheduledDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TaskScheduledDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", scheduling=" + getSchedulingId() +
            ", task=" + getTaskId() +
            "}";
    }
}
