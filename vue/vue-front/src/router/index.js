import Vue from 'vue'
import Router from 'vue-router'
// import HelloWorld from '@/components/HelloWorld'
// import room from '../components/room'
// import roomdetail from '../components/roomdetail'
Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      redirect: '/study',
    },
    {
      path: '/study',
      component: () => import('@/views/DetailStudyPage.vue'), // 코드 스플리팅
    },
    {
      path: '/chat/room/enter/:id',
      component: () => import('@/views/ChatPage.vue'), // 코드 스플리팅
    },
    {
      path: '/chat/room',
      component: () => import('@/views/ChatHomePage.vue'), // 코드 스플리팅
    },
    // {
    //   path: '/',
    //   name: 'HelloWorld',
    //   component: HelloWorld
    // },
    // {
    //   path: '/chat/room',
    //   name: 'room',
    //   component: room
    // },
    // {
    //   path: '/chat/room/enter/:id',
    //   name: 'roomdetail',
    //   component:roomdetail
    // }
  ]
})
