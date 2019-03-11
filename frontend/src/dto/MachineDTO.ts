export class MachineDTO{
    private id: number;
	private nome: string;
	private modello: string;
    private idUser: number;
    
    constructor(id: number, nome: string, modello: string, idUser: number){
        this.id = id;
        this.idUser = idUser;
        this.modello = modello;
        this.nome = nome;
    }
}