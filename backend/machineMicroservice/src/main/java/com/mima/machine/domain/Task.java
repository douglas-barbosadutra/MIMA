package com.mima.machine.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.annotation.Nullable;
import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Task.
 */
@Entity
@Table(name = "task")
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "task")
    private Set<TaskScheduled> taskScheduleds = new HashSet<>();
    @OneToMany(mappedBy = "task")
    private Set<Employee> employees = new HashSet<>();
    @Nullable
    @ManyToOne
    @JsonIgnoreProperties("tasks")
    private Machine machine;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public Task description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<TaskScheduled> getTaskScheduleds() {
        return taskScheduleds;
    }

    public Task taskScheduleds(Set<TaskScheduled> taskScheduleds) {
        this.taskScheduleds = taskScheduleds;
        return this;
    }

    public Task addTaskScheduled(TaskScheduled taskScheduled) {
        this.taskScheduleds.add(taskScheduled);
        taskScheduled.setTask(this);
        return this;
    }

    public Task removeTaskScheduled(TaskScheduled taskScheduled) {
        this.taskScheduleds.remove(taskScheduled);
        taskScheduled.setTask(null);
        return this;
    }

    public void setTaskScheduleds(Set<TaskScheduled> taskScheduleds) {
        this.taskScheduleds = taskScheduleds;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public Task employees(Set<Employee> employees) {
        this.employees = employees;
        return this;
    }

    public Task addEmployee(Employee employee) {
        this.employees.add(employee);
        employee.setTask(this);
        return this;
    }

    public Task removeEmployee(Employee employee) {
        this.employees.remove(employee);
        employee.setTask(null);
        return this;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public Machine getMachine() {
        return machine;
    }

    public Task machine(Machine machine) {
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
        Task task = (Task) o;
        if (task.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), task.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Task{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
