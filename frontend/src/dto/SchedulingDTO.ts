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

    public setId(id: number){
        this.id = id;
    }

    public setName(name: string){
        this.name = name;
    }

    public getStart(): string{
        return this.start;
    }

    public getFinish(): string{
        return this.finish;
    }

    public setStart(start: string){
        this.start = start;
    }

    public setFinish(finish: string){
        this.finish = finish;
    }

}