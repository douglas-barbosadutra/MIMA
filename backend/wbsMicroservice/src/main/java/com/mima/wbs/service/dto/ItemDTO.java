package com.mima.wbs.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Item entity.
 */
public class ItemDTO implements Serializable {

    private Long id;

    private String name;


    private Long wbsId;

    private Long fatherId;

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

    public Long getWbsId() {
        return wbsId;
    }

    public void setWbsId(Long wBSId) {
        this.wbsId = wBSId;
    }

    public Long getFatherId() {
        return fatherId;
    }

    public void setFatherId(Long itemId) {
        this.fatherId = itemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ItemDTO itemDTO = (ItemDTO) o;
        if (itemDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), itemDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ItemDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", wbs=" + getWbsId() +
            ", father=" + getFatherId() +
            "}";
    }
}
