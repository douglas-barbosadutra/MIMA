export class SchedulingDTO{
    private id: number;
    private name: string;
    private start: string;
    private finish: string;
    private machineId: number;

    constructor(id: number, name: string, start: string, finish: string, machineId: number){
        this.id = id;
        this.name = name;
        this.start = start;
        this.finish = finish;
        this.machineId = machineId;
    }

    public setStartDate(start: string){
        this.start = start;
    }

    public setEndDate(finish: string){
        this.finish = finish;
    }
}