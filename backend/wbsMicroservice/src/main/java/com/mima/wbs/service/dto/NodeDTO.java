package com.mima.wbs.service.dto;

import java.io.Serializable;
import java.util.Objects;

public class NodeDTO implements Serializable {

	private Long id;

	private String name;

	public NodeDTO(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
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
	
	@Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
    	return "NodeDTO{" +
    			"id=" + getId() +
    			", name='" + getName() +
    			"}";
    }
}
