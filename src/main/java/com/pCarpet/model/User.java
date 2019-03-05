package com.pCarpet.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name="users")
public class User {

	@Id
	@Column(name = "id_user")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name")
	@NotNull
	private String name;
	
	@Column(name = "surname")
	@NotNull
	private String surname;
	
	@NotNull
	@Column(name = "email")
	private String email;
	
	@NotNull
	@Column(name = "phone")
	private String phone;

	@NotNull
	@Column(name = "rank")
	private int rank;

	@Column(name = "username",unique=true)
	@NotNull
	private String username;

	@Column(name = "password")
	@NotNull
	private String password;

	@OneToMany(mappedBy="user")
	private List<Machine> machines;
	
	@OneToMany(mappedBy="user")
	private List<WBS> wbs;
	

}
