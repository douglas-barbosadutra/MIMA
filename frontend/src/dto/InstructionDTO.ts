export class InstructionDTO{
    private id: number;
    private name: string;
    private duration: number;
    private gcodeFile: string;
    private idTask: number;

    constructor(id: number, name: string, duration: number, gcodeFile: string, idTask: number){
        this.id = id;
        this.duration = duration;
        this.gcodeFile = gcodeFile;
        this.name = name;
        this.idTask = idTask;
    }
}