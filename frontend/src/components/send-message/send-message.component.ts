import { Component, OnInit } from '@angular/core';
import { Message } from '@angular/compiler/src/i18n/i18n_ast';
import { FormControl, NgForm } from '@angular/forms';
import { SendMessageService } from 'src/services/send-message.service';
import { Router } from '@angular/router';
import { MqttDTO } from 'src/dto/MqttDTO';


@Component({
  selector: 'app-send-message',
  templateUrl: './send-message.component.html',
  styleUrls: ['./send-message.component.css']
})
export class SendMessageComponent implements OnInit {

  public mqttDTO: MqttDTO;

  constructor(private router: Router, private sendmessageservice: SendMessageService) { }

  ngOnInit() {
    this.mqttDTO = new MqttDTO("","",null);
  }

  connect(){

    console.log(this.mqttDTO);
  }

}


