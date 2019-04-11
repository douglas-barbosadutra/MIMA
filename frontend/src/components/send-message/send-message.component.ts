import { Component, OnInit } from '@angular/core';
import { Message } from '@angular/compiler/src/i18n/i18n_ast';
import { FormControl, NgForm } from '@angular/forms';
import { SendMessageService } from 'src/services/send-message.service';
import { Router } from '@angular/router';
import { MqttDTO } from 'src/dto/MqttDTO';
import { HttpResponse } from '@angular/common/http';
import { interval, Subscription } from 'rxjs';
import { MqttMessageDTO } from 'src/dto/MqttMessageDTO';


@Component({
  selector: 'app-send-message',
  templateUrl: './send-message.component.html',
  styleUrls: ['./send-message.component.css']
})
export class SendMessageComponent implements OnInit {

  public mqttDTO: MqttDTO;
  public message: string;
  private isConnected: boolean;
  private subscription: Subscription;
  public message2: string;

  constructor(private router: Router, private sendmessageservice: SendMessageService) { }

  ngOnInit() {
    this.mqttDTO = new MqttDTO("tcp://broker.hivemq.com:1883","mima",0);
    this.message2 = "";
  }

  connect(){
    console.log(this.mqttDTO);
    this.sendmessageservice.connect(this.mqttDTO).subscribe((isConnected: boolean) => {
      if(isConnected){
        document.getElementById("textarea").style.display = "block";
        document.getElementById("textarea").style.marginLeft = "20%";
        document.getElementById("textarea").style.marginRight = "20%";
        document.getElementById("textarea").style.marginTop = "5%";
      }      
    })
  }

  disconnect(){
    this.sendmessageservice.disconnect().subscribe((isDisconnected: boolean) =>{
      if(isDisconnected){
        document.getElementById("textarea").style.display = "none";
        document.getElementById("textarea2").style.display = "none";
        this.stopEventRequest();
      }
    })
  }

  subscribe(){
    this.sendmessageservice.subscribeTopic().subscribe((undersigned: boolean) =>{
      if(undersigned){
        document.getElementById("textarea2").style.display = "block";
        document.getElementById("textarea2").style.marginLeft = "20%";
        document.getElementById("textarea2").style.marginRight = "20%";
        document.getElementById("textarea2").style.marginTop = "5%";

        this.startEventRequest();
      }
    })
  }

  publishMessage(){
    this.sendmessageservice.publishMessage(this.message).subscribe((messagePublished: boolean) =>{
      if(messagePublished)
        alert("Messaggio pubblicato");
      else
        alert("Pubblicazione fallita");
    })
  }

  startEventRequest(){
    const source = interval(3000);
    this.subscription = source.subscribe(val => this.updateMessage());
  }
  
  stopEventRequest(){
    this.subscription.unsubscribe();
  }

  updateMessage(){
    this.sendmessageservice.getMessage().subscribe((message: MqttMessageDTO) =>{
      if(message.message != null){
        this.message2 = message.message + "\n" + this.message2;
        console.log(message);
      }
      console.log("request");
    })
  }

}


