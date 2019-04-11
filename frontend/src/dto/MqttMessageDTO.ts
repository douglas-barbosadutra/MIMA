export class MqttMessageDTO{
    message: string;

    constructor(message: string){
        this.message = message;
    }

    public setMessage(message: string){
        this.message = message;
    }

}