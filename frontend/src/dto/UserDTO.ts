export class UserDTO{
    id: number;
    username: string;
    password: string;
    name: string;
    surname: string;
    email: string;
    phone: string;
    rank: number;
    authorization: string;


    constructor(id: number, username: string, password: string, name: string, surname: string, email: string, phone: string, rank: number, authorization: string){
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.rank = rank;
        this.authorization = authorization;
    }
}
