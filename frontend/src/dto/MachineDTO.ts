export class MachineDTO{
    private id: number;
	private name: string;
	private model: string;
    private idUser: number;
    
    constructor(id: number, name: string, model: string, idUser: number){
        this.id = id;
        this.idUser = idUser;
        this.model = model;
        this.name = name;
    }
}