package converter;

import dto.*;
import main.model.Istruzione;

public class IstruzioneConverter{

	public static Istruzione convertToIstruzione(IstruzioneDTO istruzionedto) {
		return (new Istruzione(istruzionedto.getNomeIstruzione(), istruzionedto.getDurata()));
	}

	public static IstruzioneDTO convertToDto(Istruzione istruzione) {
		return (new IstruzioneDTO(istruzione.getNome(), istruzione.getDurata()));
	}
}
