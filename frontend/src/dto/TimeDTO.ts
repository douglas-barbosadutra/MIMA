export class TimeDTO{
    private nameInstruction: string;
    private item: string;
    private effectiveDuration: number;
    private expectedDuration: number;
    private result: string;

    constructor(nameInstruction: string, item: string, effectiveDuration: number, expectedDuration: number, result: string){
        this.nameInstruction = nameInstruction;
        this.item = item;
        this.effectiveDuration = effectiveDuration;
        this.expectedDuration = expectedDuration;
        this.result = result;
    }

}