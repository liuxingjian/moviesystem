// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import VueRouter from "vue-router";
import router from "./router";
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import 'font-awesome/css/font-awesome.css'
//导入Axios
import axios from 'axios';
import * as echarts from 'echarts';
import VueAxios from "vue-axios";

//引用全局样式
import './assets/css/global.css';
import moment from 'moment'
import VideoPlayer from 'vue-video-player'
import Video from 'video.js'
import 'video.js/dist/video-js.css'

//Vue.use(VueAxios, axios)
//挂载axios
Vue.prototype.$axios = axios;
Vue.prototype.$http = axios;
Vue.prototype.$echarts = echarts;
Vue.prototype.$moment = moment;
//设置访问根路径
axios.defaults.baseURL = "http://localhost:9000";
Vue.prototype.$video = Video
Vue.use(VideoPlayer);
Vue.use(VueRouter)
Vue.use(ElementUI);
Vue.config.productionTip = false
axios.defaults.withCredentials = true;


/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  data: function(){
    return{
      src:'3.mp4'
    }
  },
  components: { App },
  template: '<App/>'
})
