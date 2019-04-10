export class TaskScheduledDTO{
    id: number;
    name: string;
    schedulingId: number;
    taskId: number;

    constructor(id: number, name: string, schedulingId: number, taskId: number){
        this.id = id;
        this.name = name;
        this.schedulingId = schedulingId;
        this.taskId = taskId;
    }
}
