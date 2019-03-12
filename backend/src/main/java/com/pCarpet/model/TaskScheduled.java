package com.pCarpet.model;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="task_scheduled")

public class TaskScheduled {

	@Id
	@Column(name = "id_operation_scheduling")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="id_task")
	private Task task;
	
	@ManyToOne
	@JoinColumn(name="id_scheduling")
	private Scheduling scheduling;
	
	@ManyToMany
	@JoinTable(name = "task_scheduled_relations",
	joinColumns=@JoinColumn(name = "fatherId"),
	inverseJoinColumns=@JoinColumn(name = "childId"))
	@OnDelete(action=OnDeleteAction.CASCADE)
	private List<TaskScheduled> childsList=new ArrayList<>();
	
	@ManyToMany
	@JoinTable(name = "task_scheduled_relations",
	joinColumns=@JoinColumn(name = "childId"),
	inverseJoinColumns=@JoinColumn(name = "fatherId"))
	private List<TaskScheduled> fatherList=new ArrayList<>();
}
