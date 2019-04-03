package com.mima.wbs.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Item.
 */
@Entity
@Table(name = "item")
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "item")
    private Set<WBS> wbs = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("")
    private Item father;

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

    public Item name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<WBS> getWbs() {
        return wbs;
    }

    public Item wbs(Set<WBS> wBS) {
        this.wbs = wBS;
        return this;
    }

    public Item addWbs(WBS wBS) {
        this.wbs.add(wBS);
        wBS.setItem(this);
        return this;
    }

    public Item removeWbs(WBS wBS) {
        this.wbs.remove(wBS);
        wBS.setItem(null);
        return this;
    }

    public void setWbs(Set<WBS> wBS) {
        this.wbs = wBS;
    }

    public Item getFather() {
        return father;
    }

    public Item father(Item item) {
        this.father = item;
        return this;
    }

    public void setFather(Item item) {
        this.father = item;
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
        Item item = (Item) o;
        if (item.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), item.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Item{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
