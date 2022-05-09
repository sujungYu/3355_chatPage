<template>
  <!DOCTYPE html>
  <html lang="en" xmlns:v-on="http://www.w3.org/1999/xhtml">
    <head>
      <title>Websocket ChatRoom</title>
      <meta charset="utf-8" />
      <meta
        name="viewport"
        content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
      />
    </head>
    <body>
      <h2>dasa</h2>
      <div class="container" id="app" v-cloak>
        <!-- <div>
          <h2>{{ room.name }}</h2>
        </div> -->
        <div class="input-group">
          <div class="input-group-prepend">
            <label class="input-group-text">내용</label>
          </div>
          <input
            type="text"
            class="form-control"
            v-model="message"
            v-on:keypress.enter="sendMessage"
          />
          <div class="input-group-append">
            <button class="btn btn-primary" type="button" @click="sendMessage">
              보내기
            </button>
          </div>
        </div>
        <ul class="list-group">
          <li
            class="list-group-item"
            v-for="message in messages"
            v-bind:key="message"
          >
            {{ message.sender }} - {{ message.message }}
          </li>
        </ul>
        <div></div>
      </div>
      <script src="/webjars/vue/2.5.16/vue.min.js"></script>
      <script src="/webjars/axios/0.17.1/dist/axios.min.js"></script>
      <script src="/webjars/sockjs-client/1.1.2/dist/sockjs.min.js"></script>
      <script src="/webjars/stomp-websocket/2.3.4/stomp.min.js"></script>
    </body>
  </html>
</template>

<script>
// import Vue from "vue";
import Stomp from "webstomp-client";
import SockJS from "sockjs-client";
import axios from "axios";
var sock = new SockJS("/ws");
var ws = Stomp.over(sock);
var reconnect = 0;
// vue.js
export default{
  el: "#app",
  data() {
      return {
          roomId: "",
          room: {},
          sender: "",
          message: "",
          messages: []
      }
  },
  created() {
    this.roomId = localStorage.getItem("wschat.roomId");
    this.sender = localStorage.getItem("wschat.sender");
    this.findRoom();
  },
  methods: {
    findRoom: function() {
      axios.get("/chat/room/" + this.roomId).then(response => {
        this.room = response.data;
      });
    },
    sendMessage: function() {
      ws.send(
        "/pub/chat/message",
        {},
        JSON.stringify({
          type: "TALK",
          roomId: this.roomId,
          sender: this.sender,
          message: this.message
        })
      );
      this.message = "";
    },
    recvMessage: function(recv) {
      this.messages.unshift({
        type: recv.type,
        sender: recv.type == "ENTER" ? "[알림]" : recv.sender,
        message: recv.message
      });
    }
  }
};
function connect() {
  // pub/sub event
  ws.connect(
    {},
    function(frame) {
        // this.roomId = localStorage.getItem("wschat.roomId");
        // this.sender = localStorage.getItem("wschat.sender");
      ws.subscribe("/sub/chat/room/" + localStorage.getItem("wschat.roomId"), function(message) {
        var recv = JSON.parse(message.body);
        this.recvMessage(recv);
      });
      ws.send(
        "/pub/chat/message",
        {},
        JSON.stringify({
          type: "ENTER",
          roomId: localStorage.getItem("wschat.roomId"),
          sender: localStorage.getItem("wschat.sender")
        })
      );
    },
    function(error) {
      if (reconnect++ <= 5) {
        setTimeout(function() {
          console.log("connection reconnect");
          sock = new SockJS("/ws");
          ws = Stomp.over(sock);
          connect();
        }, 10 * 1000);
      }
    }
  );
}
connect();
</script>