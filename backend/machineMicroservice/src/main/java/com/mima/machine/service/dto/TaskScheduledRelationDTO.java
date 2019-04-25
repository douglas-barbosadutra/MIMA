package com.mima.machine.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the TaskScheduledRelation entity.
 */
public class TaskScheduledRelationDTO implements Serializable {

    private Long id;


    private Long taskScheduledFirstId;

    private Long taskScheduledSecondId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTaskScheduledFirstId() {
        return taskScheduledFirstId;
    }

    public void setTaskScheduledFirstId(Long taskScheduledId) {
        this.taskScheduledFirstId = taskScheduledId;
    }

    public Long getTaskScheduledSecondId() {
        return taskScheduledSecondId;
    }

    public void setTaskScheduledSecondId(Long taskScheduledId) {
        this.taskScheduledSecondId = taskScheduledId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TaskScheduledRelationDTO taskScheduledRelationDTO = (TaskScheduledRelationDTO) o;
        if (taskScheduledRelationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), taskScheduledRelationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TaskScheduledRelationDTO{" +
            "id=" + getId() +
            ", taskScheduledFirst=" + getTaskScheduledFirstId() +
            ", taskScheduledSecond=" + getTaskScheduledSecondId() +
            "}";
    }
}
