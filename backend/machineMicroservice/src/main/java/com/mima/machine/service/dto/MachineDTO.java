package com.mima.machine.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Machine entity.
 */
public class MachineDTO implements Serializable {

    private Long id;

    private String name;

    private String model;

    private Integer idUser;


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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MachineDTO machineDTO = (MachineDTO) o;
        if (machineDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), machineDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MachineDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", model='" + getModel() + "'" +
            ", idUser=" + getIdUser() +
            "}";
    }
}
