export class InstructionDTO{
    private id: number;
    private durata: number;
    private codice: string;
    private nomeIstrutione: string;
    private idTask: number;

    constructor(id: number, durata: number, codice: string, nomeIstruzione: string, idTask: number){
        this.id = id;
        this.durata = durata;
        this.codice = codice;
        this.nomeIstrutione = nomeIstruzione;
        this.idTask = idTask;
    }
}