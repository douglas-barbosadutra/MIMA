package com.pCarpet.model;

import java.sql.Timestamp;
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


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="schedulings")


public class Scheduling {
	
	@Id
	@Column(name = "id_scheduling")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name")
	@NotNull
	private String name;
	
	@Column(name = "start")
	@NotNull
	private Timestamp start;
	
	@Column(name = "finish")
	@NotNull
	private Timestamp finish;
	
	//@Column(name="id_machine")
	@ManyToOne
	@JoinColumn(name="id_machine")
	private Machine machine;
	
	@OneToMany(mappedBy="scheduling")
	private List<OperationScheduling> operationSchedulings;

}
