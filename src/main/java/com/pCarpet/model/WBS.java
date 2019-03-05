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


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="wbs")

public class WBS {
	
	@Id
	@Column(name = "id_wbs")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name")
	@NotNull
	private String name;
	
	//@Column(name = "id_user")
	@NotNull
	@ManyToOne
	@JoinColumn(name="id_user")
	private User user;
	
	@OneToMany(mappedBy="wbs")
	private List<Item> items;

}
