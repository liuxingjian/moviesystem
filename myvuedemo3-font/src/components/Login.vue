<template>
    <div class="login_container">
      <!-- 登录模块 -->
      <div class="login_box">
        <!--LoGo头像-->
        <!--
         <div class="avatar_box">
              <img src="../assets/logo.png" alt="">
        </div>
        -->
        <div class="movie-title">
          <span >电影管理系统云平台</span>
        </div>

        <!--表单-->
        <el-form ref="loginFormRef" :model="loginForm" :rules="loginRules" class="login_form" label-width="0">
         <el-form-item  prop="username">
           <el-input v-model="loginForm.username"  prefix-icon="el-icon-user" placeholder="请输入用户名"></el-input>
         </el-form-item>
          <el-form-item prop="password">
            <el-input v-model="loginForm.password"  prefix-icon="el-icon-lock" type="password" placeholder="请输入密码"></el-input>
          </el-form-item>

          <el-form-item prop="code">
            <span><i class="iconfont icon-icon_anquan" style="font-size: 25px"></i></span>
            <el-input type="text" auto-complete="false" 
                  v-model="code" placeholder="点击图片更换验证码"
                      style="width: 60%;margin-left: 10px"
                      @keyup.enter.native="submitLogin('loginForm')"></el-input>
            <el-image class="codeImg" :src="imgUrl" @click="resetImg"></el-image>
        </el-form-item>
          <el-form-item class="bts">
            <el-button type="primary" @click="login()">登录</el-button>
            <el-button type="info" @click="resetLoginForm()">重置</el-button>
            <el-button type="success" @click="addUser()">注册</el-button>
          </el-form-item>
        </el-form>
      </div>


  <!-- 创建新用户对话框 -->
  <el-dialog :title= title :visible.sync="open" width="40%"
         @close="dialogClosed"    >
    <!-- 内容主体区域 -->
    <el-form :model="form" :rules="formRules"
             ref="formRef" label-width="70px">
      <!-- 用户名 -->
      <el-form-item label="用户名" prop="username">
        <el-input v-model="form.username" placeholder="请输入用户名" ></el-input>
      </el-form-item>
      <!-- 密码 -->
      <el-form-item label="密码" prop="password">
        <el-input v-model="form.password" placeholder="请输入密码"></el-input>
      </el-form-item>
      <!-- 邮箱 -->
      <el-form-item label="邮箱" prop="email">
        <el-input v-model="form.email" placeholder="请输入邮箱"></el-input>
      </el-form-item>
      <el-form-item label="角色" prop="role">
        <el-input v-model="form.role"  placeholder="请输入角色"></el-input>
      </el-form-item>
    </el-form>
    <!-- 内容底部区域 -->
    <span slot="footer" class="dialog-footer">
        <el-button @click=" open = false">取 消</el-button>
        <el-button type="primary" @click="submit">确 定</el-button>
      </span>
  </el-dialog>
</div>


</template>

<script>
    export default {
       data(){   
         return{
          open:false,
          title:"",
            //用户表单
            form:{
              id:undefined,
              title:undefined,
              newsType:undefined,
              content:undefined,
              newsDate:undefined,
            },
            formRules: {
              username: [
                {required: true, message: "请输入用户名", trigger: "blur"},
              ],
              password: [
                {required: true, message: "请输入密码", trigger: "blur"},
              ],
              email: [
                {required: true, message: "请输入邮箱", trigger: "blur"},
              ],
              role: [
                {required: true, message: "请输入角色", trigger: "blur"},
              ],
            },
      
          imgUrl:'http://localhost:9000/code?time='+new Date(),
          code:'',
           loginForm:{
             username:'admin',
             password:'123456',             
           },
           loginRules:{
             username: [
               { required: true, message: '用户名是必填项', trigger: 'blur' }, //必填项
               { min: 2, max: 12, message: '长度在 5 到 12个字符', trigger: 'blur' }//字符长度
             ],
             password: [
               { required: true, message: '用户密码是必填项', trigger: 'blur' }, //必填项
               { min: 3, max: 12, message: '长度在 5 ~ 12个字符', trigger: 'blur' }//字符长度
             ],
           },
         }
       },
      methods:{
        //注册方法
        //添加用户
        addUser(){
             this.open = true;
             this.title = '添加用户'
          },

        //重置方法
        resetLoginForm() {
               this.$refs.loginFormRef.resetFields();
        },
        resetImg(){
              this.imgUrl = "http://localhost:9000/code?time="+new Date();
        },
                  //提交按钮方法
                  submit(){
            this.$refs.formRef.validate(async valid =>{
              if( !valid ) return;
              if(this.form.id != undefined){
                // 发起请求
                const {data:res} = await this.$http.put("editUser",this.form);
                if (res != "success") return this.$message.error("修改失败！！！");
                this.$message.success("修改成功！！！");
                //隐藏
                this.open = false;
                this.form = {};
                this.getUserList();
              }else{
                // 发起请求
                const {data:res} = await this.$http.post("addUser",this.form);
                if (res != "success") {
                  //userinfo.state = !userinfo.state;
                  return this.$message.error("添加失败！！！");
                }
                this.$message.success("添加成功！！！");
                //隐藏
                this.open = false;
                this.getUserList();
              }
            })
          },
        //登录方法
        login(){
          localStorage.setItem("user",JSON.stringify(this.loginForm) ) //设置localstorage

          //验证表单是否符合校验规则
          this.$refs.loginFormRef.validate( async valid =>{
              //如果valid==true,表单符合校验规则,如果valid==false,表单不符合规则,退出方法
              if (!valid) return;
              //若表单符合规则,就去调用后台接口         
              const {data:ref} = await this.$http.post("login?code="+this.code,this.loginForm);
              if (ref.flag == 'ok'){
                   this.$message.success("登录成功");
                   window.sessionStorage.setItem('flag','ok'); // session 放置
                   //跳转到系统主页,通过路由实现
                   this.$router.push({path:"/home"});
                   //将user存储到session域空间中
                   window.sessionStorage.setItem("user",ref.user);
              }else if(ref.flag == 'yzm'){
                this.$message.error("验证码错误");
              }
              else {
                   this.$message.error("登录失败");
              }
          })
        },
      }
    }
</script>

<style lang="less" scoped>
.login_container{
   background-color: #2b4b6b;
   background-image: url("./../assets/img/2.jpg") ;
   background-size: 100%;
   background-repeat:no-repeat;
   height: 100%;
   width: 100%;
}
  .login_box{
    width: 450px;
    height: 350px;
    background-color: #fff;
    border-radius: 3px;
    position: absolute;
    left: 50%;
    top: 50%;
    transform: translate(-50%,-50%);
    text-align: center;
  }
  .movie-title{
      font-size: 20px;
      margin-top: 25px;
  }
  .bts{
    display: flex;
    justify-content: flex-end;
  }
  .login_form{
     position: absolute;
     bottom: 0;
     width: 100%;
     padding: 0 10px;
     box-sizing: border-box;
  }
  .codeImg {
        margin-top: 10px;
        float: right;
    }

</style>
