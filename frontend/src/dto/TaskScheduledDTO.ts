export class TaskScheduledDTO{
    id: number;
    name: string;
    idOutput: number;
    schedulingId: number;
    taskId: number;

    constructor(id: number, name: string, idOutput: number, schedulingId: number, taskId: number){
        this.id = id;
        this.name = name;
        this.idOutput = idOutput;
        this.schedulingId = schedulingId;
        this.taskId = taskId;
    }
}
