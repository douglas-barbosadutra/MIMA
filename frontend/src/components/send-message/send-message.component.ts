import { Component, OnInit } from '@angular/core';
import { Message } from '@angular/compiler/src/i18n/i18n_ast';
import { FormControl, NgForm } from '@angular/forms';
import { SendMessageService } from 'src/services/send-message.service';
import { Router } from '@angular/router';
import { MqttDTO } from 'src/dto/MqttDTO';
import { HttpResponse } from '@angular/common/http';


@Component({
  selector: 'app-send-message',
  templateUrl: './send-message.component.html',
  styleUrls: ['./send-message.component.css']
})
export class SendMessageComponent implements OnInit {

  public mqttDTO: MqttDTO;
  private isConnected: boolean;

  constructor(private router: Router, private sendmessageservice: SendMessageService) { }

  ngOnInit() {
    this.mqttDTO = new MqttDTO("tcp://test.mosquitto.org:1883","mima",0);
  }

  connect(){
    console.log(this.mqttDTO);
    this.sendmessageservice.connect(this.mqttDTO).subscribe((isConnected: boolean) => {
      console.log("connesso: " + isConnected);
    })
  }

}


