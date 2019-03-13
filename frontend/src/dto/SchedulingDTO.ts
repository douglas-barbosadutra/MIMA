export class SchedulingDTO{
    private id: number;
    private name: string;
    private startDate: string;
    private endDate: string;
    private idMachine: number;

    constructor(id: number, name: string, startDate: string, endDate: string, idMachine: number){
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.idMachine = idMachine;

    }

    public setStartDate(startDate: string){
        this.startDate = startDate;
    }

    public setEndDate(endDate: string){
        this.endDate = endDate;
    }
}