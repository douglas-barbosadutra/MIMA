import { Injectable } from '@angular/core';
import {HttpClient}from '@angular/common/http';
import {Observable} from 'rxjs';
import { UserDTO } from 'src/dto/UserDTO';
import { MqttDTO } from 'src/dto/MqttDTO';
import { MqttMessageDTO } from 'src/dto/MqttMessageDTO';


@Injectable({
  providedIn: 'root'
})
export class SendMessageService {

  constructor(private http: HttpClient) { }

  auth() {
    var user = JSON.parse(localStorage.getItem("currentUser")) as UserDTO;
    if(user) {
        return "Bearer " + user.authorities;
    } else {
        return "";
    }
  }

  connect(mqttDTO: MqttDTO): Observable<boolean>{
    return this.http.post<boolean>("http://localhost:8080/machineMicroservice/api/connectMQTT", mqttDTO, {
      headers: {
          "Authorization": this.auth()
      }
    });
  }

  disconnect(): Observable<boolean>{
    return this.http.post<boolean>("http://localhost:8080/machineMicroservice/api/disconnectMQTT", null, {
      headers: {
          "Authorization": this.auth()
      }
    });
  }

  publishMessage(message: string): Observable<boolean>{
    return this.http.post<boolean>("http://localhost:8080/machineMicroservice/api/publishMessage", message, {
      headers: {
          "Authorization": this.auth()
      }
    });
  }

  subscribeTopic(): Observable<boolean>{
    return this.http.post<boolean>("http://localhost:8080/machineMicroservice/api/subscribeTopic", null, {
      headers: {
          "Authorization": this.auth()
      }
    });
  }

  getMessage(): Observable<MqttMessageDTO>{
    return this.http.post<MqttMessageDTO>("http://localhost:8080/machineMicroservice/api/getMessage", null, {
      headers: {
          "Authorization": this.auth()
      },
      
    });
  }
}

