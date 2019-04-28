export class TaskScheduledRelationDTO{
    id: number;
    taskScheduledFirstId: number;
    taskScheduledSecondId: number;

    constructor(id: number, taskScheduledFirstId: number, taskScheduledSecondId: number){
        this.id = id;
        this.taskScheduledFirstId = taskScheduledFirstId;
        this.taskScheduledSecondId = taskScheduledSecondId;
    }
}