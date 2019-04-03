package com.mima.wbs.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Instruction.
 */
@Entity
@Table(name = "instruction")
public class Instruction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "gcode_file")
    private String gcodeFile;

    @Column(name = "id_task")
    private Integer idTask;

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

    public Instruction name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDuration() {
        return duration;
    }

    public Instruction duration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getGcodeFile() {
        return gcodeFile;
    }

    public Instruction gcodeFile(String gcodeFile) {
        this.gcodeFile = gcodeFile;
        return this;
    }

    public void setGcodeFile(String gcodeFile) {
        this.gcodeFile = gcodeFile;
    }

    public Integer getIdTask() {
        return idTask;
    }

    public Instruction idTask(Integer idTask) {
        this.idTask = idTask;
        return this;
    }

    public void setIdTask(Integer idTask) {
        this.idTask = idTask;
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
        Instruction instruction = (Instruction) o;
        if (instruction.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), instruction.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Instruction{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", duration=" + getDuration() +
            ", gcodeFile='" + getGcodeFile() + "'" +
            ", idTask=" + getIdTask() +
            "}";
    }
}
