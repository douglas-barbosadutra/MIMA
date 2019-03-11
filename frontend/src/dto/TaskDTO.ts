export class TaskDTO{
    private id: number;
    private description: string;
    private idMachine: number;

    constructor(id: number, descriprion: string, idMachine: number){
        this.id = id;
        this.description = descriprion;
        this.idMachine = idMachine;
    }
}