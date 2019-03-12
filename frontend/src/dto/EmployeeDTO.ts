import { UserDTO } from './UserDTO';

export class EmployeeDTO{
    private id: number;
    private user: UserDTO;
    private idTask: number;
    private idBusinessOwner: number;

    constructor(id: number, user: UserDTO, idTask: number, idBusinessOwner: number){
        this.id = id;
        this.user = user;
        this.idTask = idTask;
        this.idBusinessOwner = idBusinessOwner;
    }
}