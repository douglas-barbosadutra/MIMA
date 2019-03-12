export class EmployeeDTO{
    private id: number;
    private idUser: number;
    private idTask: number;
    private name: string;
    private idBusinessOwner: number;

    constructor(id: number, idUser: number, idTask: number, name: string, idBusinessOwner: number){
        this.id = id;
        this.idUser = idUser;
        this.idTask = idTask;
        this.name= name;
        this.idBusinessOwner = idBusinessOwner;
    }
}