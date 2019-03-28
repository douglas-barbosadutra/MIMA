package com.WBSMicroservice.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
	
	@ManyToOne
	@JoinColumn(name="id_task")
	private Integer idTask;
	
	@OneToMany(mappedBy="instruction")
	@OnDelete(action=OnDeleteAction.CASCADE)
	private List<Manufacturing> manufacturing;
}
