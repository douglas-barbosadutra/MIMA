package com.mima.wbs.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Manufacturing entity.
 */
public class ManufacturingDTO implements Serializable {

    private Long id;

    private Integer duration;

    private Long outputId;

    private Long instructionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Long getOutputId() {
        return outputId;
    }

    public void setOutputId(Long itemId) {
        this.outputId = itemId;
    }

    public Long getInstructionId() {
        return instructionId;
    }

    public void setInstructionId(Long instructionId) {
        this.instructionId = instructionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ManufacturingDTO manufacturingDTO = (ManufacturingDTO) o;
        if (manufacturingDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), manufacturingDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ManufacturingDTO{" +
            "id=" + getId() +
            ", duration=" + getDuration() +
            ", output=" + getOutputId() +
            ", instruction=" + getInstructionId() +
            "}";
    }
}
