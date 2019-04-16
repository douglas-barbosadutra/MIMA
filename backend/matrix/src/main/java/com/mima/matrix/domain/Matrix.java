package com.mima.matrix.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Matrix.
 */
@Entity
@Table(name = "matrix")
public class Matrix implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "num_columns")
    private Integer numColumns;

    @Column(name = "num_rows")
    private Integer numRows;

    @OneToMany(mappedBy = "matrix")
    private Set<BlackBox> blackBoxes = new HashSet<>();
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

    public Matrix name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumColumns() {
        return numColumns;
    }

    public Matrix numColumns(Integer numColumns) {
        this.numColumns = numColumns;
        return this;
    }

    public void setNumColumns(Integer numColumns) {
        this.numColumns = numColumns;
    }

    public Integer getNumRows() {
        return numRows;
    }

    public Matrix numRows(Integer numRows) {
        this.numRows = numRows;
        return this;
    }

    public void setNumRows(Integer numRows) {
        this.numRows = numRows;
    }

    public Set<BlackBox> getBlackBoxes() {
        return blackBoxes;
    }

    public Matrix blackBoxes(Set<BlackBox> blackBoxes) {
        this.blackBoxes = blackBoxes;
        return this;
    }

    public Matrix addBlackBox(BlackBox blackBox) {
        this.blackBoxes.add(blackBox);
        blackBox.setMatrix(this);
        return this;
    }

    public Matrix removeBlackBox(BlackBox blackBox) {
        this.blackBoxes.remove(blackBox);
        blackBox.setMatrix(null);
        return this;
    }

    public void setBlackBoxes(Set<BlackBox> blackBoxes) {
        this.blackBoxes = blackBoxes;
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
        Matrix matrix = (Matrix) o;
        if (matrix.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), matrix.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Matrix{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", numColumns=" + getNumColumns() +
            ", numRows=" + getNumRows() +
            "}";
    }
}
