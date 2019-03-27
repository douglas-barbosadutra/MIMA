export class UserLoggedDTO{
    public jwt: string;
    public rank: number;

    constructor(jwt: string, rank: number){
        this.jwt = jwt;
        this.rank = rank;
    }
}