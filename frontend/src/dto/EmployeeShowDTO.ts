export class EmployeeShowDTO{
    id: number;
    idUser: number;
    name: string;
    descriptionTask: string;

    constructor(id: number, idUser: number, name: string, descriptionTask: string){
        this.id = id;
        this.idUser = idUser;
        this.name = name;
        this.descriptionTask = descriptionTask;
    }
}