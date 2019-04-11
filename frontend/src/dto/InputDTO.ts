export class InputDTO{
    id: number
    idItem: number;
    taskScheduledId: number;

    constructor(id: number, idItem: number, taskScheduledId: number){
        this.id = id;
        this.idItem = idItem;
        this.taskScheduledId = taskScheduledId;
    }
}