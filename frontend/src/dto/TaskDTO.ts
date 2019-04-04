export class TaskDTO{
    private id: number;
    private description: string;
    private machineId: number;

    constructor(id: number, descriprion: string, machineId: number){
        this.id = id;
        this.description = descriprion;
        this.machineId = machineId;
    }
}