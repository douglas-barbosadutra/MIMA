package com.mima.machine.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Machine.
 */
@Entity
@Table(name = "machine")
public class Machine implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "model")
    private String model;

    @Column(name = "id_user")
    private Integer idUser;

    @OneToMany(mappedBy = "machine")
    private Set<Task> tasks = new HashSet<>();
    @OneToMany(mappedBy = "machine")
    private Set<Scheduling> schedulings = new HashSet<>();
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

    public Machine name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public Machine model(String model) {
        this.model = model;
        return this;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public Machine idUser(Integer idUser) {
        this.idUser = idUser;
        return this;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public Machine tasks(Set<Task> tasks) {
        this.tasks = tasks;
        return this;
    }

    public Machine addTask(Task task) {
        this.tasks.add(task);
        task.setMachine(this);
        return this;
    }

    public Machine removeTask(Task task) {
        this.tasks.remove(task);
        task.setMachine(null);
        return this;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public Set<Scheduling> getSchedulings() {
        return schedulings;
    }

    public Machine schedulings(Set<Scheduling> schedulings) {
        this.schedulings = schedulings;
        return this;
    }

    public Machine addScheduling(Scheduling scheduling) {
        this.schedulings.add(scheduling);
        scheduling.setMachine(this);
        return this;
    }

    public Machine removeScheduling(Scheduling scheduling) {
        this.schedulings.remove(scheduling);
        scheduling.setMachine(null);
        return this;
    }

    public void setSchedulings(Set<Scheduling> schedulings) {
        this.schedulings = schedulings;
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
        Machine machine = (Machine) o;
        if (machine.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), machine.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Machine{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", model='" + getModel() + "'" +
            ", idUser=" + getIdUser() +
            "}";
    }
}
