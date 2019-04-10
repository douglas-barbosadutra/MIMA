export class TaskDTO{
    id: number;
    description: string;
    machineId: number;

    constructor(id: number, descriprion: string, machineId: number){
        this.id = id;
        this.description = descriprion;
        this.machineId = machineId;
    }
}