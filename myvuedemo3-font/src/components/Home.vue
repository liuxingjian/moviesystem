<template>
  <el-container class="home-container">
    <el-header >
      <div>
        <span>电影后台管理系统</span>
      </div>
      <el-button type="info" @click="logout()">安全退出</el-button>
    </el-header>
    <el-container>
      <el-aside width="200px">
        <el-menu background-color="#545c64" text-color="#fff" active-text-color="#409eff" :router="true">
          <el-submenu :index="item.path+''" v-for="item in menuList" :key="item.id">
            <template slot="title"><i class="el-icon-message"></i>{{item.title}}</template>

              <el-menu-item :index="it.path+''" v-for="it in item.sList" :key="it.id">
                <template slot="title">
                <span>{{it.title}}</span>
                </template>
              </el-menu-item>
          </el-submenu>
        </el-menu>
      </el-aside>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
    export default {
      data(){
         return{
           menuList:[],
        }
      },
      created() {
        this.getMenuList();
      },
      methods:{
          logout(){
            //清空用户域对象的值
            window.sessionStorage.clear();
            this.$router.push("/");
          },
          //获取后端菜单栏
         async getMenuList(){
           const {data:ref} = await this.$http.get("menus");
           if (ref.flag != 200) return this.message.error("列表获取失败");//访问导航栏列表失败
           this.menuList = ref.menus;
          },
      },
    }
</script>

<style lang="less" scoped>
  .home-container{
    height: 100%;
  }
 .el-header{
  background-color: #373d41 ;
   display: flex;
   justify-content: space-between;
   align-items: center;

 }
  div{
     margin: 5px  0;
     color: #dddddd;
     font-size: 20px;
   }
 .el-aside{
   background-color: #333744;
 }
   .el-menu{
     border-right: none;
   }

 .el-main{
   background-color: #eaedf1 ;
  }
</style>
