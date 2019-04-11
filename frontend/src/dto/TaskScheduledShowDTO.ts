export class TaskScheduledShowDTO{
    id: number;
    name: string;
    nameOutput: string;
    descriptionTask: string;

    constructor(id: number, name: string, nameOutput: string, descriptionTask: string){
        this.id = id;
        this.name = name;
        this.nameOutput = nameOutput;
        this.descriptionTask = descriptionTask;
    }

}