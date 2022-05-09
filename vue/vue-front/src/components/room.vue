<template>
  <!-- <!DOCTYPE html>
  <html
    lang="en"
    xmlns:v-on="http://www.w3.org/1999/xhtml"
    xmlns:v-bind="http://www.w3.org/1999/xhtml"
  >
    <head>
      <title>Websocket Chat</title>
      <meta charset="utf-8" />
      <meta
        name="viewport"
        content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
      /> -->
      <!-- <link rel="stylesheet" href="/webjars/bootstrap/4.3.1/dist/css/bootstrap.min.css"> -->
      <!-- <style>
        [v-cloak] {
          display: none;
        }
      </style>
    </head>
    <body> -->
      <div class="container" id="app" v-cloak>
        <div class="row">
          <div class="col-md-12">
            <h3>채팅방 리스트</h3>

          </div>
        </div>
        <div class="input-group">
          <div class="input-group-prepend">
            <label class="input-group-text">방제목</label>
          </div>
          <input
            type="text"
            class="form-control"
            v-model="room_name"
            v-on:keyup.enter="createRoom"
          />
          <div class="input-group-append">
            <button class="btn btn-primary" type="button" @click="createRoom">
              채팅방 개설
            </button>
          </div>
        </div>
        <ul class="list-group">
          <li
            class="list-group-item list-group-item-action"
            v-for="item in chatrooms"
            :key="item"
            @click="enterRoom(item.roomId)"
          >
            {{ item.roomName }}
          </li>
        </ul>
      </div>
      <!-- <script src="/webjars/vue/2.5.16/vue.min.js"></script>
      <script src="/webjars/axios/0.17.1/dist/axios.min.js"></script>
    </body>
  </html> -->
</template>
<script>
import axios from "axios";
export default {
  el: "#app",
  data() {
    return {
      room_name: "",
      chatrooms: []
    }
  },
  created() {
    this.findAllRoom();
  },
  methods: {
    async findAllRoom() {
      await axios.get("/chat/rooms").then(response => {
        this.chatrooms = response.data;
      });
    },
    createRoom: function() {
      if ("" === this.room_name) {
        alert("방 제목을 입력해 주십시요.");
        return;
      } else {
        var params = new URLSearchParams();
        params.append("name", this.room_name);
        axios.post("/chat/room", params)
          .then(response => {
            alert(response.data.roomName + "방 개설에 성공하였습니다.");
            console.log(response.data);
            this.room_name = "";
            this.findAllRoom();
          })
          .catch(response => {
            alert("채팅방 개설에 실패하였습니다.");
            console.log(response.data);
          });
      }
    },
    enterRoom(roomId) {
      var sender = prompt("대화명을 입력해 주세요.");
      if (sender !== "") {
        localStorage.setItem("wschat.sender", sender);
        localStorage.setItem("wschat.roomId", roomId);
        // this.$router.push(`/chat/room/enter/${roomId}`)
        location.href = "/chat/room/enter/" + roomId;
        // location.href="/chat/room/enter/";
      }
    }
  }
};
</script>
