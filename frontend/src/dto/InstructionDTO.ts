export class InstructionDTO{
    private id: number;
    private duration: number;
    private codex: string;
    private nameInstruction: string;
    private idTask: number;

    constructor(id: number, duration: number, codex: string, nameInstruction: string, idTask: number){
        this.id = id;
        this.duration = duration;
        this.codex = codex;
        this.nameInstruction = nameInstruction;
        this.idTask = idTask;
    }
}