export class UserDTO{
    id: number;
    login: string;
    password: string;
    firstName: string;
    lastName: string;
    email: string;
    authorities: Array<string>;


    constructor(id: number, login: string, password: string, firstName: string, lastName: string, email: string, authorities: Array<string>){
        this.id = id;
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.authorities = authorities;
    }
}
