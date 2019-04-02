import { Injectable } from '@angular/core';
import {HttpClient}from '@angular/common/http';
import {Observable} from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class SendMessageService {

  constructor(private http: HttpClient) { }

  getconnectToMQTTClient(brockenUrl: string, jwt: string): Observable<boolean> {
    return this.http.get<boolean>('http://localhost:8080/MQTTService/sendMessage?brokenUrl=' + brockenUrl+'&jwt='+jwt);
  }

  getdisconnectToMQTTClient(brockenUrl: number, jwt: string): Observable<boolean> {
    return this.http.get<boolean>('http://localhost:8080/MQTTService/disconnectMQTTService?brokenUrl=' + brockenUrl+'&jwt='+jwt);
  }
/*
  publishMessage(mqttService: MQTTService ) {
    return this.http.post<boolean>('http://localhost:8080/MQTTService/publishMessage', mqttService);
  }

  subscribeTopic(subscribeTopic: number, jwt: string){
    return this.http.get<boolean>('http://localhost:8080/MQTTService/subscribeTopic?idItem='+subscribeTopic+"&subscribeTopic="+'&jwt='+jwt);
  }

  getMessage(mqttService: MQTTService) {
    console.log(mqttService);
    return this.http.post<string>('http://localhost:8080/MQTTService/getMessage',mqttService);
  }*/
}

