package com.MachineMicroservice.model;

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
@Table(name="machines")

public class Machine {
	
	@Id
	@Column(name = "id_machine")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name")
	@NotNull
	private String name;
	
	@Column(name = "model")
	@NotNull
	private String model;
	
	@Column(name="id_user")
	@NotNull
	private Integer idUser;
	
	@OneToMany(mappedBy="machine")
	@OnDelete(action=OnDeleteAction.CASCADE)
	private List<Task> tasks;
	
	@OneToMany(mappedBy="machine")
	@OnDelete(action=OnDeleteAction.CASCADE)
	private List<Scheduling> schedulings;
	
}
