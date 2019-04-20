export class ItemDTO{
    fatherId: number;
    id: number;
    items: Array<ItemDTO>;
    name: string;
    wbsId: number;
    

    constructor(id: number, name: string, wbsId: number, fatherId: number, items: Array<ItemDTO>){
        this.id = id;
        this.name = name;
        this.fatherId = fatherId;
        this.wbsId = wbsId;
        this.items = items;
    }

    public setFatherId(fatherId: number){
        this.fatherId = fatherId;
    }
}