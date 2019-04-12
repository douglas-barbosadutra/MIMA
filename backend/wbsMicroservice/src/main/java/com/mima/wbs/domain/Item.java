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

    @OneToMany(mappedBy = "father")
    private Set<Item> items = new HashSet<>();
    @OneToMany(mappedBy = "output")
    private Set<Manufacturing> manufacturings = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("items")
    private WBS wbs;

    @ManyToOne
    //@JsonIgnoreProperties("items")
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

    public Set<Item> getItems() {
        return items;
    }

    public Item items(Set<Item> items) {
        this.items = items;
        return this;
    }

    public Item addItem(Item item) {
        this.items.add(item);
        item.setFather(this);
        return this;
    }

    public Item removeItem(Item item) {
        this.items.remove(item);
        item.setFather(null);
        return this;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public Set<Manufacturing> getManufacturings() {
        return manufacturings;
    }

    public Item manufacturings(Set<Manufacturing> manufacturings) {
        this.manufacturings = manufacturings;
        return this;
    }

    public Item addManufacturing(Manufacturing manufacturing) {
        this.manufacturings.add(manufacturing);
        manufacturing.setOutput(this);
        return this;
    }

    public Item removeManufacturing(Manufacturing manufacturing) {
        this.manufacturings.remove(manufacturing);
        manufacturing.setOutput(null);
        return this;
    }

    public void setManufacturings(Set<Manufacturing> manufacturings) {
        this.manufacturings = manufacturings;
    }

    public WBS getWbs() {
        return wbs;
    }

    public Item wbs(WBS wBS) {
        this.wbs = wBS;
        return this;
    }

    public void setWbs(WBS wBS) {
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
