export class TimeDTO{
    private nameInstruction: string;
    private item: string;
    private effectiveDuration: number;
    private expectedDuration: number;
    private result: object;

    constructor(nameInstruction: string, item: string, effectiveDuration: number, expectedDuration: number, result: object){
        this.nameInstruction = nameInstruction;
        this.item = item;
        this.effectiveDuration = effectiveDuration;
        this.expectedDuration = expectedDuration;
        this.result = result;
    }

}