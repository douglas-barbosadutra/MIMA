package com.mima.wbs.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A WBS.
 */
@Entity
@Table(name = "wbs")
public class WBS implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "id_user")
    private Integer idUser;

    @OneToMany(mappedBy = "wbs")
    private Set<Item> items = new HashSet<>();

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

    public WBS name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public WBS idUser(Integer idUser) {
        this.idUser = idUser;
        return this;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Set<Item> getItems() {
        return items;
    }

    public WBS items(Set<Item> items) {
        this.items = items;
        return this;
    }

    public WBS addItem(Item item) {
        this.items.add(item);
        item.setWbs(this);
        return this;
    }

    public WBS removeItem(Item item) {
        this.items.remove(item);
        item.setWbs(null);
        return this;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
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
        WBS wBS = (WBS) o;
        if (wBS.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), wBS.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "WBS{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", idUser=" + getIdUser() +
            "}";
    }
}
