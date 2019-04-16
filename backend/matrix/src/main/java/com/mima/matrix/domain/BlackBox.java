package com.mima.matrix.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A BlackBox.
 */
@Entity
@Table(name = "black_box")
public class BlackBox implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "jhi_column")
    private Integer column;

    @Column(name = "jhi_row")
    private Integer row;

    @ManyToOne
    @JsonIgnoreProperties("blackBoxes")
    private Matrix matrix;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getColumn() {
        return column;
    }

    public BlackBox column(Integer column) {
        this.column = column;
        return this;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }

    public Integer getRow() {
        return row;
    }

    public BlackBox row(Integer row) {
        this.row = row;
        return this;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Matrix getMatrix() {
        return matrix;
    }

    public BlackBox matrix(Matrix matrix) {
        this.matrix = matrix;
        return this;
    }

    public void setMatrix(Matrix matrix) {
        this.matrix = matrix;
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
        BlackBox blackBox = (BlackBox) o;
        if (blackBox.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), blackBox.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BlackBox{" +
            "id=" + getId() +
            ", column=" + getColumn() +
            ", row=" + getRow() +
            "}";
    }
}
