package com.pCarpet.dto;

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
	private ItemDTO father;
	private int idWBS;
	private List<ItemDTO> itemChildrenDTO;
	private int level;
}
