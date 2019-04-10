package com.mima.machine.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Input entity.
 */
public class InputDTO implements Serializable {

    private Long id;

    private Integer idItem;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        InputDTO inputDTO = (InputDTO) o;
        if (inputDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), inputDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "InputDTO{" +
            "id=" + getId() +
            ", idItem=" + getIdItem() +
            "}";
    }
}
