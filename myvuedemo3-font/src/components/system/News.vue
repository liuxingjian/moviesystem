<template>
  <div>
    <h3 style="margin-top: 0">新闻列表</h3>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{path:'/home'}">首页</el-breadcrumb-item>
      <el-breadcrumb-item>电影管理</el-breadcrumb-item>
      <el-breadcrumb-item>新闻列表</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card>
      <el-row :gutter="25">
        <el-col :span="10">
          <el-input placeholder="请输入新闻标题" v-model="queryNews.title">
            <el-button slot="append" icon="el-icon-search" @click="getList"></el-button>
          </el-input>
        </el-col >
        <el-col :span="2">
          <el-button type="primary" @click="add">添加新闻</el-button>
        </el-col>
        <el-col :span="2">
          <el-button type="danger" @click="handleDelete" :disabled="isDisable">批量删除</el-button>
        </el-col>
      </el-row>
      <el-table :data="newsList" ref="multipleTable"  border stripe  @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55"/>
        <el-table-column type="index" label="序号"/>
        <el-table-column label="新闻标题" prop="title"/>
        <el-table-column label="新闻类别" prop="newsType"/>

        <!-- <el-table-column label="主要内容" prop="content" show-overflow-tooltip/> -->
        <el-table-column prop="content" label="内容详情" min-width="100">
          <template #default="scope">        
              <el-tooltip
                  :content="scope.row.content"
                  raw-content
                  placement="top-start"
                  v-if="scope.row.content"
              >
                <span v-if="scope.row.content && scope.row.content.length <= 15">
                     {{scope.row.content}}
                </span>
                <span v-if="scope.row.content&& scope.row.content.length > 15">                
                     {{scope.row.content.substr(0, 15) + "..."}}
                </span>
              </el-tooltip>
              <span v-else-if="scope.row.content== null"> NA </span>
          </template>
      </el-table-column>





        <el-table-column label="发生日期" prop="newsDate" >
          <span slot-scope="scope" >{{dateFormat('YYYY-mm-dd',scope.row.newsDate)}}</span>
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
          :current-page="queryNews.pageNum"
          :page-sizes="[1, 2, 5, 10]"
          :page-size="queryNews.pageSize"
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
        <el-form-item label="新闻标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入新闻标题"></el-input>
        </el-form-item>
        <!-- 密码 -->
        <el-form-item label="新闻类别" prop="newsType">
          <el-input v-model="form.newsType" placeholder="请输入新闻类别"> </el-input>
        </el-form-item>
        <!-- 邮箱 -->
        <el-form-item label="创建日期" prop="newsDate">
          <el-date-picker style="width: 100%"
                          v-model="form.newsDate"
                          type="date"
                          value-format="yyyy-MM-dd"
                          format="yyyy-MM-dd"
                          placeholder="选择日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="新闻内容" prop="content">
          <el-input v-model="form.content" placeholder="请输入新闻内容" type="textarea"> </el-input>
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
        queryNews:{
          title:'',
          pageNum:1,
          pageSize:5,
        },
        typeList:[],
        newsList:[],
        total:0,
        //用户表单
        form:{
          id:undefined,
          name:undefined,
          createUser:undefined,
          createDate:undefined,
        },
        formRules: {
          title: [
            {required: true, message: "请输入新闻标题", trigger: "blur"},
          ],
          newsType: [
            {required: true, message: "请输入新闻类别", trigger: "blur"},
          ],
          newsDate: [
            {required: true, message: "请输入发生日期", trigger: "blur"},
          ],
          content: [
            {required: true, message: "请输入新闻内容", trigger: "blur"},
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
        const {data:res} = await this.$http.get("allNews",{params:this.queryNews});
        this.newsList = res.data;
        this.total = res.number;
        console.log(this.newsList);
        console.log(this.total);
      },
      //最大数
      handleSizeChange(newSize){
        this.queryNews.pageSize = newSize;
        this.getList();
      },
      //页面
      handleCurrentChange(newPage){
        this.queryNews.pageNum = newPage;
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
        this.title = '添加新闻'
      },
      //修改新闻
      async edit(row){
        const  id = row.id;
        //发起请求
        const {data:res} = await this.$http.get("getUpdateNews?id="+id);
        res.newsDate  = this.dateFormat('YYYY-mm-dd ',res.newsDate);
        this.form = res;
        console.log(this.form)
        this.open = true;
        this.title = '修改新闻';
      },
      //提交按钮方法
      submit(){
        this.$refs.formRef.validate(async valid =>{
          if( !valid ) return;
          if(this.form.id != undefined){
            // 发起请求
            const {data:res} = await this.$http.put("editNews",this.form);
            if (res != "success") return this.$message.error("修改失败！！！");
            this.$message.success("修改成功！！！");
            //隐藏
            this.open = false;
            this.form = {};
            this.getList();
          }else{
            // 发起请求
            const {data:res} = await this.$http.post("addNews",this.form);
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
        this.names = selection.map(item => item.title);
        this.isDisable = !selection.length;
      },
      //删除用户
      async handleDelete(row){
        const ids = row.id || this.ids;
        const names= row.title || this.names;
        // 弹框
        const confirmResult = await this.$confirm('是否确认删除标题为"' + names + '"的新闻?', "提示",  {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).catch(err => err)
        // 成功删除为confirm 取消为 cancel
        if(confirmResult!='confirm'){
          this.$refs.multipleTable.clearSelection();
          return this.$message.info("已取消删除");
        }
        const {data:res} = await this.$http.delete("deleteNews?id="+ids);
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

