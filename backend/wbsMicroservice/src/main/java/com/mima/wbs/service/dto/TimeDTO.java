package com.mima.wbs.service.dto;

public class TimeDTO {
	
	private String nameInstruction;
	private String item;
	private int effectiveDuration;
	private int expectedDuration;
	private String result;
	
	public TimeDTO(String nameInstruction, String item, int effectiveDuration, int expectedDuration, String result) {

		this.nameInstruction = nameInstruction;
		this.item = item;
		this.effectiveDuration = effectiveDuration;
		this.expectedDuration = expectedDuration;
		this.result = result;
	}
	
	public TimeDTO() {
		
	}

	public String getNameInstruction() {
		return nameInstruction;
	}

	public void setNameInstruction(String nameInstruction) {
		this.nameInstruction = nameInstruction;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public int getEffectiveDuration() {
		return effectiveDuration;
	}

	public void setEffectiveDuration(int effectiveDuration) {
		this.effectiveDuration = effectiveDuration;
	}

	public int getExpectedDuration() {
		return expectedDuration;
	}

	public void setExpectedDuration(int expectedDuration) {
		this.expectedDuration = expectedDuration;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "TimeDTO [nameInstruction=" + nameInstruction + ", item=" + item + ", effectiveDuration="
				+ effectiveDuration + ", expectedDuration=" + expectedDuration + ", result=" + result + "]";
	}
	
	
}
