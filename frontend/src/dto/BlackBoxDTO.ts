export class BlackBoxDTO{
    id: number;
    row: number;    
    column: number;

    public constructor(id: number, row: number, colomn: number){
        this.id = id;
        this.row = row;
        this.column = colomn;
    }
}