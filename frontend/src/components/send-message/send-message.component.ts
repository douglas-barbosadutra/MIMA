import { Component, OnInit } from '@angular/core';
import { connect } from 'http2';
import { Message } from '@angular/compiler/src/i18n/i18n_ast';
import { FormControl, NgForm } from '@angular/forms';
import { SendMessageService } from 'src/services/send-message.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-send-message',
  templateUrl: './send-message.component.html',
  styleUrls: ['./send-message.component.css']
})
export class SendMessageComponent implements OnInit {


  constructor(private router: Router, private sendmessageservice: SendMessageService) { }

  ngOnInit() {
    this.getconnectToMQTTClient();
    this.getdisconnectToMQTTClient();
    this.getpublishMessage();
    this.getsubscribeTopic();
    this.getMessage();
  }

  getconnectToMQTTClient() {
    this.sendMessageService.connectToMQTTClient(boolean(sessionStorage.getconnect("brokenURL")), sessionStorage.getconnectToMQTTClient("brokenUrl")).subscribe((data) => {
      if (data != null) {
        this.connectToMQTTClient = new Array();
        this.connectToMQTTClient = data;
        sessionStorage.setConnectToMQTTClient("connectToMQTTClientLength", JSON.stringify(this.connectToMQTTClient.length));
      }
    });
  }

  getdisconnectToMQTTClient() {
    this.MQTTService.disconnectToMQTTClient(boolean(sessionStorage.getdisconnectToMQTTClient("disconnectToMQTTClient")), sessionStorage.getdisconnectToMQTTClient("disconnectToMQTTClient")).subscribe((data) => {
      if (data != null) {
        this.disconnectToMQTTClient = new Array();
        this.disconnectToMQTTClient = data;
        sessionStorage.setConnectToMQTTClient("disconnectToMQTTClientLength", JSON.stringify(this.disconnectToMQTTClient.length));
      }
    });
  }
  
  getpublishMessage(f: NgForm) {

    this.sendMessageService = new SendMessageComponent(sessionStorage.getconnectToMQTTClient("brokenURL"), this.sendMessageService);

    this.sendMessageService.publish(this.MQTTSerivce).subscribe((data: any) => {

      if (data != null)
        alert("Messaggio inviato");
      else
        alert("Messaggio non inviato");

      this.router.navigateByUrl("homeUser");
    });
  }

subscribeTopic() {
  this.sendmessageservice.subscribeTopic(boolean(sessionStorage.getsubscribeTopic("subscribeTopic")), sessionStorage.getsubscribeTopic("subscribeTopic")).subscribe((data: any) => {
    if (data != null) {
      this.subscribeTopic = data;
    }});
  }

  getMessage() {
      Message message = new Message();
      message.setMessage(message);
      return(mqttService.getMessage());
  }
}

