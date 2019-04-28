import { BlackBoxInterface } from 'src/interfaces/BlackBoxInterface';

export class BlackBox implements BlackBoxInterface{
    row: number;    
    column: number;

    public constructor(row: number, colomn: number){
        this.row = row;
        this.column = colomn;
    }

}