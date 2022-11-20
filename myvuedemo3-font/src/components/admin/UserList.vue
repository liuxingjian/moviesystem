<template>
<div>
  <h3 style="margin-top: 0">用户列表</h3>
  <el-breadcrumb separator-class="el-icon-arrow-right">
  <el-breadcrumb-item :to="{path:'/home'}">首页</el-breadcrumb-item>
  <el-breadcrumb-item>权限管理</el-breadcrumb-item>
  <el-breadcrumb-item>用户列表</el-breadcrumb-item>
  </el-breadcrumb>
  <el-card>
    <el-row :gutter="25">
      <el-col :span="10">
        <el-input placeholder="请输入用户名" v-model="queryInfo.username">
          <el-button slot="append" icon="el-icon-search" @click="getUserList"></el-button>
        </el-input>
      </el-col >
      <el-col :span="2">
        <el-button type="primary" @click="addUser">添加用户</el-button>
      </el-col>
      <el-col :span="2">
        <el-button type="danger" @click="deleteUser" :disabled="isDisable" >批量删除</el-button>
      </el-col>
    </el-row>
    <el-table :data="userList" ref="multipleTable"  border stripe  @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"/>
       <el-table-column type="index" label="序号"/>
       <el-table-column label="用户名" prop="username"/>
       <el-table-column label="邮箱" prop="email"/>
       <el-table-column label="密码" prop="password"/>
       <el-table-column label="角色" prop="role"/>
       <el-table-column label="状态" prop="state">
         <template slot-scope="scope">
               <el-switch v-model="scope.row.state" @change="userStateChanged(scope.row)"></el-switch>
         </template>
       </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <!-- 修改 -->
          <el-button type="primary" icon="el-icon-edit" size="mini" @click="editUser(scope.row)" ></el-button>
          <!-- 删除 -->
          <el-button type="danger" icon="el-icon-delete" size="mini"  @click="deleteUser(scope.row)"></el-button>
        </template>
      </el-table-column>
    </el-table>
    <div>
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="queryInfo.pageNum"
        :page-sizes="[1, 2, 5, 10]"
        :page-size="queryInfo.pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
      </el-pagination>
    </div>
  </el-card>
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
        created() {
          this.getUserList();
        },
        data(){
          return{
            //批量删除是否禁用
            isDisable :true,
            //对话框标题
            title:'',
            //对话框
            open :false,
            //多选框选中id与username
            ids :[],
            usernames:[],
            //查询对象
            queryInfo:{
              username:'',
              pageNum:1,
              pageSize:10,
            },
            userList:[],
            total:0,
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
            }
          }
        },
        methods:{
          //获取所有用户
         async getUserList(){
         const {data:res} = await this.$http.get("allUser",{params:this.queryInfo});
         this.userList = res.data;
         this.total = res.number;
          },
          //最大数
          handleSizeChange(newSize){
            this.queryInfo.pageSize = newSize;
            this.getUserList();
          },
          //页面
          handleCurrentChange(newPage){
           this.queryInfo.pageNum = newPage;
           this.getUserList();
          },
          // 监听添加用户
          dialogClosed(){
            this.$refs.formRef.resetFields();// 重置表单项
            this.form = {};
          },
          //添加用户
          addUser(){
             this.open = true;
             this.title = '添加用户'
          },
          // 修改用户状态
          async userStateChanged(row) {
            const { data: res } = await this.$http.put(
              `userState?id=${row.id}&state=${row.state}`
            );
            if (res != "success") {
              return this.$message.error("修改用户状态失败！！！");
            }
            this.$message.success("修改用户状态成功！！！");
          },
          //修改用户
      async editUser(row){
            const  id = row.id;
            //发起请求
            const {data:res} = await this.$http.get("getUpdate?id="+id);
            this.form = res;
            this.open = true;
            this.title = '修改用户';
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
          // 多选框选中数据
          handleSelectionChange(selection) {
            this.ids = selection.map(item => item.id);
            this.usernames = selection.map(item => item.username);
            this.isDisable = !selection.length;
          },
          //删除用户
          async deleteUser(row){
            const ids = row.id || this.ids;
            const usernames= row.username || this.usernames;
            // 弹框
            const confirmResult = await this.$confirm('是否确认删除名称为"' + usernames + '"的用户?', "提示",  {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).catch(err => err)
            // 成功删除为confirm 取消为 cancel
            if(confirmResult!='confirm'){
              this.$refs.multipleTable.clearSelection();
              return this.$message.info("已取消删除");
            }
            const {data:res} = await this.$http.delete("deleteUser?id="+ids);
            if (res != "success") {
              return this.$message.error("删除失败！！！");
            }
            this.$message.success("删除成功！！！");
            this.getUserList();
          },
        },
    }
</script>

<style lang="less" scoped>
.el-breadcrumb{
  margin-bottom: 15px;
  font-size: 17px;
}
</style>
