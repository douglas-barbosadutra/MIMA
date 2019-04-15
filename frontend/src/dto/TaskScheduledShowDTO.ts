export class TaskScheduledShowDTO{
    id: number;
    name: string;
    output: string;
    descriptionTask: string;

    constructor(id: number, name: string, output: string, descriptionTask: string){
        this.id = id;
        this.name = name;
        this.output = output;
        this.descriptionTask = descriptionTask;
    }

}