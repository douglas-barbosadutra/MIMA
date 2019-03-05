package com.pCarpet.model;


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
import javax.validation.constraints.NotNull;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="manufactorings")


public class Manufactoring {

	@Id
	@Column(name = "id_manufactoring")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "duration")
	@NotNull
	private Integer duration;
	
	@ManyToMany
	@JoinTable(name="items_manufactorings",
		joinColumns={@JoinColumn(name="id_item")},
		inverseJoinColumns= {@JoinColumn(name="id_manufactoring")})
	private List<Item> items;
	
	@Column(name = "id_item_output")
	@NotNull
	@ManyToOne
	@JoinColumn(name="id_item")
	private Item output;
	
	
}
