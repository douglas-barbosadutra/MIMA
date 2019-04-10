package com.mima.machine.domain;



import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Input.
 */
@Entity
@Table(name = "input")
public class Input implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_item")
    private Integer idItem;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdItem() {
        return idItem;
    }

    public Input idItem(Integer idItem) {
        this.idItem = idItem;
        return this;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
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
        Input input = (Input) o;
        if (input.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), input.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Input{" +
            "id=" + getId() +
            ", idItem=" + getIdItem() +
            "}";
    }
}
