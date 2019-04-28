import { AreasInterface } from 'src/interfaces/AreasInterface';

export class Areas implements AreasInterface{

    x: number;    
    y: number;
    x1: number;
    y1: number;

    public constructor(x: number, y: number, x1: number, y1: number){
        this.x = x;
        this.y = y;
        this.x1 = x1;
        this.y1 = y1;
    }

}