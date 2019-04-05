package com.mima.wbs.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the WBS entity.
 */
public class WBSDTO implements Serializable {

    private Long id;

    private String name;

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

        WBSDTO wBSDTO = (WBSDTO) o;
        if (wBSDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), wBSDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "WBSDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", idUser=" + getIdUser() +
            "}";
    }
}
