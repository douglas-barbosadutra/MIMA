package com.WBSMicroservice.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class ItemDTO {
	
	private int id;
	private String name;
	private int idFather;
	private int idWBS;
	public List<ItemDTO> itemChildrenDTO;
}
