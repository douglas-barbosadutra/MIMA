export class ParamDTO{
    private jwt: string;
    private param: Object;

    constructor(jwt: string, param: Object){
        this.jwt = jwt;
        this.param = param;
    }
}