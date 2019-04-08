export class MqttDTO{
    brokenURL: string;
    topic: string;
    qos: number;

    constructor(brokenURL: string, topic: string, qos: number){
        this.brokenURL = brokenURL;
        this.topic = topic;
        this.qos = qos;
    }
}