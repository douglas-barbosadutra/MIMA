package com.mima.machine.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A TaskScheduled.
 */
@Entity
@Table(name = "task_scheduled")
public class TaskScheduled implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(name = "task_scheduled_father",
               joinColumns = @JoinColumn(name = "task_scheduled_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "father_id", referencedColumnName = "id"))
    private Set<TaskScheduled> fathers = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("taskScheduleds")
    private Scheduling scheduling;

    @ManyToOne
    @JsonIgnoreProperties("taskScheduleds")
    private Task task;

    @ManyToMany(mappedBy = "fathers")
    @JsonIgnore
    private Set<TaskScheduled> children = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public TaskScheduled name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<TaskScheduled> getFathers() {
        return fathers;
    }

    public TaskScheduled fathers(Set<TaskScheduled> taskScheduleds) {
        this.fathers = taskScheduleds;
        return this;
    }

    public TaskScheduled addFather(TaskScheduled taskScheduled) {
        this.fathers.add(taskScheduled);
        taskScheduled.getChildren().add(this);
        return this;
    }

    public TaskScheduled removeFather(TaskScheduled taskScheduled) {
        this.fathers.remove(taskScheduled);
        taskScheduled.getChildren().remove(this);
        return this;
    }

    public void setFathers(Set<TaskScheduled> taskScheduleds) {
        this.fathers = taskScheduleds;
    }

    public Scheduling getScheduling() {
        return scheduling;
    }

    public TaskScheduled scheduling(Scheduling scheduling) {
        this.scheduling = scheduling;
        return this;
    }

    public void setScheduling(Scheduling scheduling) {
        this.scheduling = scheduling;
    }

    public Task getTask() {
        return task;
    }

    public TaskScheduled task(Task task) {
        this.task = task;
        return this;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Set<TaskScheduled> getChildren() {
        return children;
    }

    public TaskScheduled children(Set<TaskScheduled> taskScheduleds) {
        this.children = taskScheduleds;
        return this;
    }

    public TaskScheduled addChild(TaskScheduled taskScheduled) {
        this.children.add(taskScheduled);
        taskScheduled.getFathers().add(this);
        return this;
    }

    public TaskScheduled removeChild(TaskScheduled taskScheduled) {
        this.children.remove(taskScheduled);
        taskScheduled.getFathers().remove(this);
        return this;
    }

    public void setChildren(Set<TaskScheduled> taskScheduleds) {
        this.children = taskScheduleds;
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
        TaskScheduled taskScheduled = (TaskScheduled) o;
        if (taskScheduled.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), taskScheduled.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TaskScheduled{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
