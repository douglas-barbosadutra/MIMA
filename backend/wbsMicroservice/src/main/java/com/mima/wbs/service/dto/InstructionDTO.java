package com.mima.wbs.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Instruction entity.
 */
public class InstructionDTO implements Serializable {

    private Long id;

    private String name;

    private Integer duration;

    private String gcodeFile;

    private Integer idTask;


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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getGcodeFile() {
        return gcodeFile;
    }

    public void setGcodeFile(String gcodeFile) {
        this.gcodeFile = gcodeFile;
    }

    public Integer getIdTask() {
        return idTask;
    }

    public void setIdTask(Integer idTask) {
        this.idTask = idTask;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        InstructionDTO instructionDTO = (InstructionDTO) o;
        if (instructionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), instructionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "InstructionDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", duration=" + getDuration() +
            ", gcodeFile='" + getGcodeFile() + "'" +
            ", idTask=" + getIdTask() +
            "}";
    }
}
