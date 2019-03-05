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
@Table(name="operation_scheduling")

public class OperationScheduling {

	@Id
	@Column(name = "id_operation_scheduling")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "order")
	@NotNull
	private Integer order;
	
	//@Column(name = "id_task")
	@ManyToOne
	@JoinColumn(name="id_task")
	private Task task;
	
	//@Column(name = "id_scheduling")
	@ManyToOne
	@JoinColumn(name="id_scheduling")
	private Scheduling scheduling;
}
