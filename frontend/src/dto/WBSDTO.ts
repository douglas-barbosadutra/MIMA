export class WBSDTO{
    id: number;
    name: string;
    idUser: number;

    constructor(id: number, name: string, idUser: number){
        this.id = id;
        this.name = name;
        this.idUser = idUser;
    }
}