<template>
  <div>
      <ul>
          <li v-for="item in chatrooms" :key="item" @click="enterRoom(item.roomId)">
              {{ item.roomName }}
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
            // const sender = prompt("아이디");
            if(sender != "") {
                // localStorage.setItem("wschat.sender", sender);
                // localStorage.setItem("wschat.roomId", roomId);
                location.href = "/chat/room/enter/" + roomId;
            }
        }
    }

}
</script>

<style>

</style>