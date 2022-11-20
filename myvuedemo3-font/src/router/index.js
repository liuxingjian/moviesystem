import Vue from 'vue';
import VueRouter from "vue-router";
import Login from "../components/Login";
import Home from "../components/Home";
import Welcome from "../components/Welcome";
import UserList from "../components/admin/UserList";
import Type from "../components/system/Type";
import News from "../components/system/News";
import Movie from "../components/system/Movie";
import Staff from "../components/staff/Staff.vue"
import Chatroom from "../components/chat/Chatroom"
import Charts from "../components/charts/Charts"

Vue.use(VueRouter)

const routes = [
  {
    path: '/login',
    name:'login',
    component: Login
  },

  {
    path: '/home',
    component: Home,
    redirect: '/user',
    children: [
      { 
        path:'/welcome',
        component: Welcome
      },
      {
        path:"/staff",
        name:'staff',
        component: Staff
      },
      {
        path:'/user',
        name:'user',
        component:UserList,
      },
      {
        path:'/type',
        name:'type',
        component:Type,
      },
      {
        path:'/news',
        name:'news',
        component:News,
      },
      {
        path:'/movie',
        name:'movie',
        component:Movie,
      },
      {
        path:'/chat',
        name:'chatroom',
        component:Chatroom,
      },
      {
        path:'/charts',
        name:'chatrs',
        component:Charts,
      },
    ],
  },
]

const router = new VueRouter({
  routes
})

// 挂载路由导航守卫
router.beforeEach((to, from, next) => {
  // to:将要访问的路径
  // from:从哪里访问的路径
  // next:之后要做的任务，是一个函数
  //    next（）放行， next（'/URL'）强制跳转的路径。
  if (to.path == '/login') return next();// 访问路径为登录
  // 获取flag
  const flagStr = window.sessionStorage.getItem("flag");// session取值
  if (!flagStr) return next('/login');// 没登录去登录
  next();
})

export default router// 暴露出去
