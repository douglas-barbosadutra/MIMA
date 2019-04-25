package com.mima.machine.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A TaskScheduledRelation.
 */
@Entity
@Table(name = "task_scheduled_relation")
public class TaskScheduledRelation implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("taskScheduledRelations")
    private TaskScheduled taskScheduledFirst;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("taskScheduledRelations")
    private TaskScheduled taskScheduledSecond;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TaskScheduled getTaskScheduledFirst() {
        return taskScheduledFirst;
    }

    public TaskScheduledRelation taskScheduledFirst(TaskScheduled taskScheduled) {
        this.taskScheduledFirst = taskScheduled;
        return this;
    }

    public void setTaskScheduledFirst(TaskScheduled taskScheduled) {
        this.taskScheduledFirst = taskScheduled;
    }

    public TaskScheduled getTaskScheduledSecond() {
        return taskScheduledSecond;
    }

    public TaskScheduledRelation taskScheduledSecond(TaskScheduled taskScheduled) {
        this.taskScheduledSecond = taskScheduled;
        return this;
    }

    public void setTaskScheduledSecond(TaskScheduled taskScheduled) {
        this.taskScheduledSecond = taskScheduled;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TaskScheduledRelation taskScheduledRelation = (TaskScheduledRelation) o;
        if (taskScheduledRelation.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), taskScheduledRelation.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TaskScheduledRelation{" +
            "id=" + getId() +
            "}";
    }
}
