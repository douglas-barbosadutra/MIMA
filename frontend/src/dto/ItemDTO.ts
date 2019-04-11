export class ItemDTO{
    id: number;
    name: string;
    wbsId: number;
    fatherId: number;

    constructor(id: number, name: string, wbsId: number, fatherId: number){
        this.id = id;
        this.name = name;
        this.fatherId = fatherId;
        this.wbsId = wbsId;
    }
}