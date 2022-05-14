<template>
  <div>
      <ul>
          <li v-for="item in chatrooms" :key="item" @click="enterRoom(item.roomId)">
              {{ item.roomName }}
              <i class="fa-solid fa-circle-minus" @click="deleteRoom(item.roomId)"></i>
          </li>
      </ul>
  </div>
</template>

<script>
import axios from "axios";
export default {
    data() {
        return {
            chatrooms: []
        }
    },
    created() {
        this.findAllRoom();
    },
    methods: {
        findAllRoom() {
            axios.get("/chat/rooms").then(response => {
            this.chatrooms = response.data;
            // console.log(this.chatrooms);
            });
        },
        enterRoom(roomId) {
            const sender = '이다'
            if(sender != "") {
                localStorage.setItem("wschat.sender", sender);
                localStorage.setItem("wschat.roomId", roomId);
                location.href = "/chat/room/enter/" + roomId;
            }
        },
         deleteRoom(roomId) {
             console.log('delete');
             axios.delete('/chat/roominfo?roomId='+roomId)
             axios.delete('/chat/messages?roomId='+roomId)
            }
        }

    }
</script>

<style>

</style>