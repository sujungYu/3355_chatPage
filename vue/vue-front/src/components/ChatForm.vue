<template>
<div>
<div id="app" v-cloak>
    <div>
        <h2>{{room.roomName}}</h2>
    </div>
    <div>
        <div>
            <label>내용</label>
        </div>
        <input type="text" v-model="message" @keypress.enter="sendMessage">
        <div>
            <button type="button" @click="sendMessage">보내기</button>
        </div>
    </div>
    <ul>
        <li v-for="message in messages" :key="message">
            <!-- {{messages}} -->
          <!-- {{item}} - {{index}} -->
            {{message.sender}} - {{message.message}}
        </li>
    </ul>
</div>
</div>

</template>

<script>
import axios from 'axios'
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'
    export default{
        // el: '#app',
        data() {
          return {
            roomId: '',
            room: {},
            sender: '',
            message: '',
            messages: [],
            ws: null
          }
        },
        created() {
            this.roomId = localStorage.getItem('wschat.roomId');
            this.sender = localStorage.getItem('wschat.sender');

            // 채팅방 제목
            axios.get('/chat/roominfo?roomId='+this.roomId)
                .then(res => {
                    this.room = res.data[0];
                    console.log(this.room);
                }).catch(err => {
                    console.log(err);
                })

            // 이전 채팅 내용
            axios.get('/chat/messages?roomId='+this.roomId)
            .then(res => {
                this.messages = []
                for(let i=0; i<res.data.length; i++) {
                    let msg = {
                        "type":res.data[i].type,"sender":res.data[i].type=='ENTER'?'[알림]':res.data[i].sender,"message":res.data[i].message
                    }
                    this.messages.unshift(msg)
                }
            })

            // socket 연결
            let sock = new SockJS("/ws");
            this.ws = Stomp.over(sock);
            this.ws.connect({},frame=> {
                console.log("success", frame);
                this.ws.subscribe("/sub/chat/room/"+this.roomId, message=> {
                  let recv = JSON.parse(message.body);
                  console.log(recv);
                  this.recvMessage(recv)
              });
              this.ws.send("/pub/chat/message", JSON.stringify({type:'ENTER', roomId:this.roomId, sender:this.sender}));
              }, error=> {
              if(reconnect++ <= 5) {
                setTimeout(function() {
                    console.log("connection reconnect");
                    // sock = new SockJS("/ws");
                    // ws = Stomp.over(sock);
                    // this.connect();
                },10*1000);
              }
            });
        },
        methods: {
            findRoom() {
                axios.get('/chat/room/'+this.roomId).then(response => {
                     this.room = response.data;
                     });
            },
            sendMessage() {
                this.ws.send("/pub/chat/message", JSON.stringify({type:'TALK', roomId:this.roomId, sender:this.sender, message:this.message}));
                this.message = '';
            },
            recvMessage(recv) {
                this.messages.unshift({"type":recv.type,"sender":recv.type=='ENTER'?'[알림]':recv.sender,"message":recv.message})
                console.log(this.messages);
            },
        },

    }
</script>