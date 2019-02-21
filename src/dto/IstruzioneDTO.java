package dto;

public class IstruzioneDTO {
	private String nomeIstruzione;
	private int durata;
	
	public IstruzioneDTO(String nomeIstruzione, int durata) {
		this.nomeIstruzione = nomeIstruzione;
		this.durata = durata;
	}
	
	public String getNomeIstruzione() {
		return nomeIstruzione;
	}
	
	public int getDurata() {
		return durata;
	}
}
