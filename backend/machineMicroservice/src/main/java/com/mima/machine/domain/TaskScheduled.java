package com.mima.machine.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @Column(name = "id_output")
    private Integer idOutput;

    @OneToMany(mappedBy = "taskScheduled")
    private Set<Input> inputs = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("taskScheduleds")
    private Scheduling scheduling;

    @ManyToOne
    @JsonIgnoreProperties("taskScheduleds")
    private Task task;

    @OneToMany(mappedBy = "taskScheduledFirst", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<TaskScheduledRelation> taskScheduledRelations = new HashSet<>();
   
    @OneToMany(mappedBy = "taskScheduledSecond", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<TaskScheduledRelation> taskScheduledRelationTwos = new HashSet<>();
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

    public Integer getIdOutput() {
        return idOutput;
    }

    public TaskScheduled idOutput(Integer idOutput) {
        this.idOutput = idOutput;
        return this;
    }

    public void setIdOutput(Integer idOutput) {
        this.idOutput = idOutput;
    }

    public Set<Input> getInputs() {
        return inputs;
    }

    public TaskScheduled inputs(Set<Input> inputs) {
        this.inputs = inputs;
        return this;
    }

    public TaskScheduled addInput(Input input) {
        this.inputs.add(input);
        input.setTaskScheduled(this);
        return this;
    }

    public TaskScheduled removeInput(Input input) {
        this.inputs.remove(input);
        input.setTaskScheduled(null);
        return this;
    }

    public void setInputs(Set<Input> inputs) {
        this.inputs = inputs;
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

    public Set<TaskScheduledRelation> getTaskScheduledRelations() {
        return taskScheduledRelations;
    }

    public TaskScheduled taskScheduledRelations(Set<TaskScheduledRelation> taskScheduledRelations) {
        this.taskScheduledRelations = taskScheduledRelations;
        return this;
    }

    public TaskScheduled addTaskScheduledRelation(TaskScheduledRelation taskScheduledRelation) {
        this.taskScheduledRelations.add(taskScheduledRelation);
        taskScheduledRelation.setTaskScheduledFirst(this);
        return this;
    }

    public TaskScheduled removeTaskScheduledRelation(TaskScheduledRelation taskScheduledRelation) {
        this.taskScheduledRelations.remove(taskScheduledRelation);
        taskScheduledRelation.setTaskScheduledFirst(null);
        return this;
    }

    public void setTaskScheduledRelations(Set<TaskScheduledRelation> taskScheduledRelations) {
        this.taskScheduledRelations = taskScheduledRelations;
    }

    public Set<TaskScheduledRelation> getTaskScheduledRelationTwos() {
        return taskScheduledRelationTwos;
    }

    public TaskScheduled taskScheduledRelationTwos(Set<TaskScheduledRelation> taskScheduledRelations) {
        this.taskScheduledRelationTwos = taskScheduledRelations;
        return this;
    }

    public TaskScheduled addTaskScheduledRelationTwo(TaskScheduledRelation taskScheduledRelation) {
        this.taskScheduledRelationTwos.add(taskScheduledRelation);
        taskScheduledRelation.setTaskScheduledSecond(this);
        return this;
    }

    public TaskScheduled removeTaskScheduledRelationTwo(TaskScheduledRelation taskScheduledRelation) {
        this.taskScheduledRelationTwos.remove(taskScheduledRelation);
        taskScheduledRelation.setTaskScheduledSecond(null);
        return this;
    }

    public void setTaskScheduledRelationTwos(Set<TaskScheduledRelation> taskScheduledRelations) {
        this.taskScheduledRelationTwos = taskScheduledRelations;
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
            ", idOutput=" + getIdOutput() +
            "}";
    }
}
