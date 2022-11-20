<template>
  <div>
    <h3 style="margin-top: 0">类别列表</h3>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{path:'/home'}">首页</el-breadcrumb-item>
      <el-breadcrumb-item>电影管理</el-breadcrumb-item>
      <el-breadcrumb-item>类别列表</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card>
      <el-row :gutter="25">
        <el-col :span="10">
          <el-input placeholder="请输入类别名" v-model="queryType.name">
            <el-button slot="append" icon="el-icon-search" @click="getList"></el-button>
          </el-input>
        </el-col >
        <el-col :span="2">
          <el-button type="primary" @click="add">添加类别</el-button>
        </el-col>
        <el-col :span="2">
          <el-button type="danger" @click="handleDelete" :disabled="isDisable">批量删除</el-button>
        </el-col>
      </el-row>
      <el-table :data="typeList" ref="multipleTable"  border stripe  @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55"/>
        <el-table-column type="index" label="序号"/>
        <el-table-column label="类别名称" prop="name"/>
        <el-table-column label="创建用户名" prop="createUser"/>
        <el-table-column label="创建日期" prop="createDate" >
          <span slot-scope="scope" >{{dateFormat('YYYY-mm-dd ',scope.row.createDate)}}</span>
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <!-- 修改 -->
            <el-button type="primary" icon="el-icon-edit" size="mini" @click="edit(scope.row)" ></el-button>
            <!-- 删除 -->
            <el-button type="danger" icon="el-icon-delete" size="mini"  @click="handleDelete(scope.row)"></el-button>
          </template>
        </el-table-column>
      </el-table>
      <div>
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="queryType.pageNum"
          :page-sizes="[1, 2, 5, 10]"
          :page-size="queryType.pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
        </el-pagination>
      </div>
    </el-card>
    <!-- 电影类别对话框 -->
    <el-dialog :title= title :visible.sync="open" width="40%"
               @close="dialogClosed"    >
      <!-- 内容主体区域 -->
      <el-form :model="form" :rules="formRules"
               ref="formRef" label-width="100px">
        <!-- 用户名 -->
        <el-form-item label="电影类别" prop="name">
          <el-input v-model="form.name" placeholder="请输入电影类别"></el-input>
        </el-form-item>
        <!-- 密码 -->
        <el-form-item label="创建用户名" prop="createUser">
          <el-input v-model="form.createUser" placeholder="请输入创建用户名"> </el-input>
        </el-form-item>
        <!-- 邮箱 -->
        <el-form-item label="创建日期" prop="createDate">
          <el-date-picker style="width: 100%"
            v-model="form.createDate"
            type="date"
            value-format="yyyy-MM-dd"
            format="yyyy-MM-dd"
            placeholder="选择日期">
          </el-date-picker>
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
      this.getList();
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
        names:[],
        //查询对象
        queryType:{
          name:'',
          pageNum:1,
          pageSize:5,
        },
        typeList:[],
        total:0,
        //用户表单
        form:{
          id:undefined,
          name:undefined,
          createUser:undefined,
          createDate:undefined,
        },
        formRules: {
          name: [
            {required: true, message: "请输入电影类别", trigger: "blur"},
          ],
          createUser: [
            {required: true, message: "请输入创建用户名", trigger: "blur"},
          ],
          createDate: [
            {required: true, message: "请输入创建日期", trigger: "blur"},
          ],
        }
      }
    },
    methods:{
      //时间格式化
      dateFormat(fmt, date) {
        let ret = "";
        date = new Date(date);
        const opt = {
          'Y+': date.getFullYear().toString(), // 年
          'm+': (date.getMonth() + 1).toString(), // 月
          'd+': date.getDate().toString(), // 日
          'H+': date.getHours().toString(), // 时
          'M+': date.getMinutes().toString(), // 分
          'S+': date.getSeconds().toString() // 秒
          // 有其他格式化字符需求可以继续添加，必须转化成字符串
        }
        for (let k in opt) {
          ret = new RegExp('(' + k + ')').exec(fmt)
          if (ret) {
            fmt = fmt.replace(
              ret[1],
              ret[1].length == 1 ? opt[k] : opt[k].padStart(ret[1].length, '0')
            )
          }
        }
        return fmt;
      },
      //获取所有类别列表
      async getList(){
        const {data:res} = await this.$http.get("allType",{params:this.queryType});
        this.typeList = res.data;
        this.total = res.number;
        console.log(this.typeList)
      },
      //最大数
      handleSizeChange(newSize){
        this.queryType.pageSize = newSize;
        this.getList();
      },
      //页面
      handleCurrentChange(newPage){
        this.queryType.pageNum = newPage;
        this.getList();
      },
      // 监听对话框
      dialogClosed(){
        this.$refs.formRef.resetFields();// 重置表单项
        this.form = {};
      },
      //添加电影类别
      add(){
        this.open = true;
        this.title = '添加电影类别'
      },
      //修改用户
      async edit(row){
        const  id = row.id;
        //发起请求
        const {data:res} = await this.$http.get("getUpdateType?id="+id);
        res.createDate  = this.dateFormat('YYYY-mm-dd ',res.createDate);
        this.form = res;
        this.open = true;
        this.title = '修改电影类别';
      },
      //提交按钮方法
      submit(){
        this.$refs.formRef.validate(async valid =>{
          if( !valid ) return;
          if(this.form.id != undefined){
            // 发起请求
            const {data:res} = await this.$http.put("editType",this.form);
            if (res != "success") return this.$message.error("修改失败！！！");
            this.$message.success("修改成功！！！");
            //隐藏
            this.open = false;
            this.form = {};
            this.getList();
          }else{
            // 发起请求
            const {data:res} = await this.$http.post("addType",this.form);
            if (res != "success") {
              return this.$message.error("添加失败！！！");
            }
            this.$message.success("添加成功！！！");
            //隐藏
            this.open = false;
            this.getList();
          }
        })
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.id);
        this.names = selection.map(item => item.name);
        this.isDisable = !selection.length;
      },
      //删除用户
      async handleDelete(row){
        const ids = row.id || this.ids;
        const names= row.name || this.names;
        // 弹框
        const confirmResult = await this.$confirm('是否确认删除名称为"' + names + '"的电影类别?', "提示",  {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).catch(err => err)
        // 成功删除为confirm 取消为 cancel
        if(confirmResult!='confirm'){
          this.$refs.multipleTable.clearSelection();
          return this.$message.info("已取消删除");
        }
        const {data:res} = await this.$http.delete("deleteType?id="+ids);
        if (res != "success") {
          return this.$message.error("删除失败！！！");
        }
        this.$message.success("删除成功！！！");
        this.getList();
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

