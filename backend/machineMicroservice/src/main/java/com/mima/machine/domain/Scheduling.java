package com.mima.machine.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Scheduling.
 */
@Entity
@Table(name = "scheduling")
public class Scheduling implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "jhi_start")
    private String start;

    @Column(name = "finish")
    private String finish;

    @OneToMany(mappedBy = "scheduling")
    private Set<TaskScheduled> taskScheduleds = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("schedulings")
    private Machine machine;

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

    public Scheduling name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStart() {
        return start;
    }

    public Scheduling start(String start) {
        this.start = start;
        return this;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getFinish() {
        return finish;
    }

    public Scheduling finish(String finish) {
        this.finish = finish;
        return this;
    }

    public void setFinish(String finish) {
        this.finish = finish;
    }

    public Set<TaskScheduled> getTaskScheduleds() {
        return taskScheduleds;
    }

    public Scheduling taskScheduleds(Set<TaskScheduled> taskScheduleds) {
        this.taskScheduleds = taskScheduleds;
        return this;
    }

    public Scheduling addTaskScheduled(TaskScheduled taskScheduled) {
        this.taskScheduleds.add(taskScheduled);
        taskScheduled.setScheduling(this);
        return this;
    }

    public Scheduling removeTaskScheduled(TaskScheduled taskScheduled) {
        this.taskScheduleds.remove(taskScheduled);
        taskScheduled.setScheduling(null);
        return this;
    }

    public void setTaskScheduleds(Set<TaskScheduled> taskScheduleds) {
        this.taskScheduleds = taskScheduleds;
    }

    public Machine getMachine() {
        return machine;
    }

    public Scheduling machine(Machine machine) {
        this.machine = machine;
        return this;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
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
        Scheduling scheduling = (Scheduling) o;
        if (scheduling.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), scheduling.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Scheduling{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", start='" + getStart() + "'" +
            ", finish='" + getFinish() + "'" +
            "}";
    }
}
