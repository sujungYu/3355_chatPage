import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import room from '../components/room'
import roomdetail from '../components/roomdetail'
Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/chat/room',
      name: 'room',
      component: room
    },
    {
      path: '/chat/room/enter/:id',
      name: 'roomdetail',
      component:roomdetail
    }
  ]
})
