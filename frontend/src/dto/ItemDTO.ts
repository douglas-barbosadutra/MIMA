export class ItemDTO{
    id: number;
    name: String;
    idFather: number;
    idWBS: number;
    itemChildrenDTO: Array<ItemDTO>;
    level: number;

    constructor(id: number, name: string, idFather: number, idWbs: number, itemChildrenDTO: Array<ItemDTO>, level: number){
        this.id = id;
        this.name = name;
        this.idFather = idFather;
        this.idWBS = idWbs;
        this.itemChildrenDTO = itemChildrenDTO;
        this.level = level;
    }
}