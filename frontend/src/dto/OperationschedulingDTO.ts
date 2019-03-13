export class OperationSchedulingDTO{
    idFather: number;
    idChild: number;
    idTask: number;
    idScheduling: number;

    constructor(idFather: number, idChild: number, idTask: number, idScheduling: number){
        this.idFather = idFather;
        this.idChild = idChild;
        this.idTask = idTask;
        this.idScheduling = idScheduling;
    }
}
