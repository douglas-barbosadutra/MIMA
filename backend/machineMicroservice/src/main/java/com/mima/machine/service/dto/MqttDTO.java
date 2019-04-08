package com.mima.machine.service.dto;

public class MqttDTO {
	
	private String brokenURL;
	
	private String topic;
	
	private Integer qos;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brokenURL == null) ? 0 : brokenURL.hashCode());
		result = prime * result + ((qos == null) ? 0 : qos.hashCode());
		result = prime * result + ((topic == null) ? 0 : topic.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MqttDTO other = (MqttDTO) obj;
		if (brokenURL == null) {
			if (other.brokenURL != null)
				return false;
		} else if (!brokenURL.equals(other.brokenURL))
			return false;
		if (qos == null) {
			if (other.qos != null)
				return false;
		} else if (!qos.equals(other.qos))
			return false;
		if (topic == null) {
			if (other.topic != null)
				return false;
		} else if (!topic.equals(other.topic))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MqttDTO [brokenURL=" + brokenURL + ", topic=" + topic + ", qos=" + qos + "]";
	}

	public String getBrokenURL() {
		return brokenURL;
	}

	public void setBrokenURL(String brokenURL) {
		this.brokenURL = brokenURL;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public Integer getQos() {
		return qos;
	}

	public void setQos(Integer qos) {
		this.qos = qos;
	}

}
