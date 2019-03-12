export class SchedulingDTO{
    private id: number;
    private name: string;
    private startDate: Date;
    private endDate: Date;
    private idMachine: number;

    constructor(id: number, name: string, startDate: Date, endDate: Date, idMachine: number){
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.idMachine = idMachine;
    }
}