export class TaskScheduledDTO{
    id: number;
    idTask: number;
    hasFather: boolean;
    name: string;
    idScheduling: number;
    taskScheduledChildren: Array<TaskScheduledDTO>;

    constructor(id: number, idTask: number, hasFather: boolean, name: string, idScheduling: number, taskScheduledChildren: Array<TaskScheduledDTO>){
        this.id = id;
        this.idTask = idTask;
        this.hasFather = hasFather;
        this.name = name;
        this.idScheduling = idScheduling;
        this.taskScheduledChildren = taskScheduledChildren;
    }
}
