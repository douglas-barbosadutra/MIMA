package com.mima.machine.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Scheduling entity.
 */
public class SchedulingDTO implements Serializable {

    private Long id;

    private String name;

    private String start;

    private String finish;


    private Long machineId;

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

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getFinish() {
        return finish;
    }

    public void setFinish(String finish) {
        this.finish = finish;
    }

    public Long getMachineId() {
        return machineId;
    }

    public void setMachineId(Long machineId) {
        this.machineId = machineId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SchedulingDTO schedulingDTO = (SchedulingDTO) o;
        if (schedulingDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), schedulingDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SchedulingDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", start='" + getStart() + "'" +
            ", finish='" + getFinish() + "'" +
            ", machine=" + getMachineId() +
            "}";
    }
}
