package com.pCarpet.model;

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
@Table(name="tasks")


public class Task {
	
	@Id
	@Column(name = "id_task")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "description")
	@NotNull
	private String description;
	
	@ManyToOne
	@JoinColumn(name="id_machine")
	private Machine machine;
	
	@OneToMany(mappedBy="task")
	@OnDelete(action=OnDeleteAction.CASCADE)
	private List<Instruction> instructions;
	
	@OneToMany(mappedBy="task")
	@OnDelete(action=OnDeleteAction.CASCADE)
	private List<OperationScheduling> operationSchedulings;
	

}
