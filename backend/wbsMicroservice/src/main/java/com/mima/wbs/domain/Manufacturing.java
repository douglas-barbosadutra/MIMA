package com.mima.wbs.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Manufacturing.
 */
@Entity
@Table(name = "manufacturing")
public class Manufacturing implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "duration")
    private Integer duration;

    @ManyToOne
    @JsonIgnoreProperties("manufacturings")
    private Item output;

    @ManyToOne
    @JsonIgnoreProperties("manufacturings")
    private Instruction instruction;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDuration() {
        return duration;
    }

    public Manufacturing duration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Item getOutput() {
        return output;
    }

    public Manufacturing output(Item item) {
        this.output = item;
        return this;
    }

    public void setOutput(Item item) {
        this.output = item;
    }

    public Instruction getInstruction() {
        return instruction;
    }

    public Manufacturing instruction(Instruction instruction) {
        this.instruction = instruction;
        return this;
    }

    public void setInstruction(Instruction instruction) {
        this.instruction = instruction;
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
        Manufacturing manufacturing = (Manufacturing) o;
        if (manufacturing.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), manufacturing.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Manufacturing{" +
            "id=" + getId() +
            ", duration=" + getDuration() +
            "}";
    }
}
