import { TaskScheduledRelationDTO } from './TaskScheduledRelationDTO';

export class TaskScheduledDTO{
    id: number;
    name: string;
    idOutput: number;
    schedulingId: number;
    taskId: number;
    taskScheduledRelationList: Array<TaskScheduledRelationDTO>;

    constructor(id: number, name: string, idOutput: number, schedulingId: number, taskId: number, taskScheduledRelationList: Array<TaskScheduledRelationDTO>){
        this.id = id;
        this.name = name;
        this.idOutput = idOutput;
        this.schedulingId = schedulingId;
        this.taskId = taskId;
        this.taskScheduledRelationList = taskScheduledRelationList;
    }
}
