export class MatrixDTO{
    id: number;
    image: string;
    resolution: number;

    public constructor(id: number, image: string, resolution: number){
        this.id = id;
        this.image = image;
        this.resolution;
    }
}