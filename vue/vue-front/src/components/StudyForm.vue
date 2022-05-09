<template>
  <div @click="chat">
       {{ this.study }}
      <!-- <i class="fa-solid fa-comment ch" ></i> -->
  </div>
</template>

<script>
import axios from "axios";
export default {
    data() {
        return {
            study: ''
        }
    },
    created() {
        this.study = '쑤우'
    },
    methods: {
        chat() {
            const sender = prompt("아이디");
            localStorage.setItem("wschat.sender", sender);
            console.log();

            let params = new URLSearchParams();
            params.append("name", this.study);
            axios.post("/chat/room", params).then(res=> {
                localStorage.setItem("wschat.roomId", res.data.roomId);
                this.$router.push(`/chat/room/enter/${res.data.roomId}`)
                console.log(res.data);
            }).catch(err => {
                console.log(err);
            })
        }
    }

}
</script>

<style>

</style>