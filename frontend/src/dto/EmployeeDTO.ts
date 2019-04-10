export class EmployeeDTO{
    id: number;
    idUser: number;
    idBusinessOwner: number;
    name: string;
    taskId: number;

    constructor(id: number, idUser: number, idBusinessOwner: number, name: string, taskId: number){
        this.id = id;
        this.idUser = idUser;
        this.idBusinessOwner = idBusinessOwner;
        this.name = name;
        this.taskId = taskId;

        
    }

    public setIdUser(id: number){
        this.idUser = id;
    }

    public setName(name: string){
        this.name = name;
    }

    public setIdTask(taskId: number): void{
        this.taskId = taskId;
    }

}