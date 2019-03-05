package com.pCarpet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="instructions")


public class Instruction {
	
	@Id
	@Column(name = "id_instruction")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name")
	@NotNull
	private String name;
	
	@Column(name = "duration")
	@NotNull
	private Integer duration;
	
	@Column(name = "gcode_file")
	@NotNull
	private String gcodeFile;
	
	@Column(name="id_task")
	@ManyToOne
	@JoinColumn(name="id_task")
	private Task task;

}
