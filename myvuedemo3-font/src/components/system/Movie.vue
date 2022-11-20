<template>
  <div>
    <h3 style="margin-top: 0">电影列表</h3>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{path:'/home'}">首页</el-breadcrumb-item>
      <el-breadcrumb-item>电影管理</el-breadcrumb-item>
      <el-breadcrumb-item>电影列表</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card>
      <el-row :gutter="25">
        <el-form ref="queryForm" :model="queryMovie" label-width="80px" :inline="true">
            <el-form-item label="电影名称" prop="movieName">
              <el-input v-model="queryMovie.movieName"></el-input>
            </el-form-item>
            <el-form-item label="电影类别">
              <el-select v-model="queryMovie.type"  style="width: 100%"  placeholder="请选择电影类别">
                <el-option
                  v-for="item in typeOptions"
                  :key="item.name"
                  :label="item.name"
                  :value="item.name"
                ></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="电影演员" prop="actor">
              <el-input v-model="queryMovie.actor"></el-input>
            </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="getList" >搜索</el-button>
            <el-button type="primary" @click="reset" >重置</el-button>
          </el-form-item>

        </el-form>
      </el-row>
      <el-row :gutter="25" >
        <el-col :span="2">
          <el-button type="primary" @click="add">添加电影</el-button>
        </el-col>
        <el-col :span="2">
          <el-button type="danger" @click="handleDelete" :disabled="isDisable">批量删除</el-button>
        </el-col>
      </el-row>
      <el-table :data="movieList" ref="multipleTable"  border stripe  @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55"/>
        <el-table-column type="index" label="序号"/>
        <el-table-column label="电影名称" prop="movieName"/>
        <el-table-column label="电影类别" prop="type"/>
        <el-table-column label="演员" prop="actor" />
        <el-table-column label="导演" prop="director"/>
        <el-table-column label="电影时长" prop="minS"/>
        <el-table-column label="上映时间" prop="movieDate">
          <span slot-scope="scope" >{{dateFormat('YYYY-mm-dd ',scope.row.movieDate)}}</span>
        </el-table-column>
        <el-table-column label="电影链接" prop="film"/>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <!-- 修改 -->
            <el-button type="primary" icon="el-icon-edit" size="mini" @click="edit(scope.row)" ></el-button>
            <!-- 删除 -->
            <el-button type="danger" icon="el-icon-delete" size="mini"  @click="handleDelete(scope.row)"></el-button>

            <el-button type="success" icon="el-icon-search" size="mini"  @click="goUrl(scope.row)"></el-button>
          </template>
        </el-table-column>
      </el-table>
      <div>
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="queryMovie.pageNum"
          :page-sizes="[1, 2, 5, 10]"
          :page-size="queryMovie.pageSize"
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
        <el-form-item label="电影名称" prop="movieName">
          <el-input v-model="form.movieName" placeholder="请输入电影名称"></el-input>
        </el-form-item>
        <!-- 密码 -->
        <el-form-item label="电影类型" prop="type">
          <el-select v-model="form.type"  style="width: 100%"  placeholder="请选择电影类别">
            <el-option
              v-for="item in typeOptions"
              :key="item.name"
              :label="item.name"
              :value="item.name"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="电影演员" prop="actor">
          <el-input v-model="form.actor" placeholder="请输入电影演员"> </el-input>
        </el-form-item>
        <el-form-item label="电影导演" prop="director">
          <el-input v-model="form.director" placeholder="请输入电影导演"> </el-input>
        </el-form-item>
        <el-form-item label="电影时长" prop="minS">
          <el-input v-model="form.minS" placeholder="请输入电影时长"> </el-input>
        </el-form-item>
        <!-- 邮箱 -->
        <el-form-item label="上映日期" prop="movieDate">
          <el-date-picker style="width: 100%"
                          v-model="form.movieDate"
                          type="date"
                          value-format="yyyy-MM-dd"
                          format="yyyy-MM-dd"
                          placeholder="选择日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="电影链接" prop="film">
          <el-input v-model="form.film" placeholder="请输入电影链接"> </el-input>
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
      this.getTypes();
    },
    data(){
      return{
        //电影类别
        typeOptions:[],
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
        queryMovie:{
          movieName:'',
          type:'',
          actor:'',
          pageNum:1,
          pageSize:5,
        },
        typeList:[],
        movieList:[],
        total:0,
        //用户表单
        form:{
          id:undefined,
          movieName:undefined,
          type:undefined,
          actor:undefined,
          director:undefined,
          minS:undefined,
          movieDate:undefined,
          film:undefined,
        },
        formRules: {
          movieName: [
            {required: true, message: "请输入电影名称", trigger: "blur"},
          ],
          type: [
            {required: true, message: "请输入电影类型", trigger: "blur"},
          ],
          actor: [
            {required: true, message: "请输入电影演员", trigger: "blur"},
          ],
          director: [
            {required: true, message: "请输入电影导演", trigger: "blur"},
          ],
          minS: [
            {required: true, message: "请输入电影时长", trigger: "blur"},
          ],
          movieDate: [
            {required: true, message: "请输入电影上映日期", trigger: "blur"},
          ],
          film: [
            {required: true, message: "请输入电影链接", trigger: "blur"},
          ],
        }
      }
    },
    methods:{
      //重置查询表单
      reset(){
        this.queryMovie.movieName = '';
        this.queryMovie.actor = '';
        this.queryMovie.type = '';
        this.getList();
      },
      //获取所有类别列表
      async getTypes(){
        const {data:res} = await this.$http.get("allTypes");
        this.typeOptions = res.data;
        console.log(this.typeOptions);
      },
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
      //获取所有电影列表
      async getList(){
        const {data:res} = await this.$http.get("allMovie",{params:this.queryMovie});
        this.movieList = res.data;
        this.total = res.number;
        console.log(this.movieList);
      },
      //最大数
      handleSizeChange(newSize){
        this.queryMovie.pageSize = newSize;
        this.getList();
      },
      //页面
      handleCurrentChange(newPage){
        this.queryMovie.pageNum = newPage;
        this.getList();
      },
      // 监听对话框
      dialogClosed(){
        this.$refs.formRef.resetFields();// 重置表单项
        this.form = {};
      },
      //添加电影
      add(){
        this.open = true;
        this.title = '添加电影'
      },
      //修改用户
      async edit(row){
        const  id = row.id;
        //发起请求
        const {data:res} = await this.$http.get("getUpdateMovie?id="+id);
        res.movieDate  = this.dateFormat('YYYY-mm-dd ',res.movieDate);
        this.form = res;
        this.open = true;
        this.title = '修改电影';
      },
      //提交按钮方法
      submit(){
        this.$refs.formRef.validate(async valid =>{
          if( !valid ) return;
          if(this.form.id != undefined){
            // 发起请求
            const {data:res} = await this.$http.put("editMovie",this.form);
            if (res != "success") return this.$message.error("修改失败！！！");
            this.$message.success("修改成功！！！");
            //隐藏
            this.open = false;
            this.form = {};
            this.getList();
          }else{
            // 发起请求
            console.log(this.form);
            const {data:res} = await this.$http.post("addMovie",this.form);
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
        this.names = selection.map(item => item.movieName);
        this.isDisable = !selection.length;
      },
      //删除用户
      async handleDelete(row){
        const ids = row.id || this.ids;
        const names= row.movieName || this.names;
        // 弹框
        const confirmResult = await this.$confirm('是否确认删除名称为"' + names + '"的电影?', "提示",  {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).catch(err => err)
        // 成功删除为confirm 取消为 cancel
        if(confirmResult!='confirm'){
          this.$refs.multipleTable.clearSelection();
          return this.$message.info("已取消删除");
        }
        const {data:res} = await this.$http.delete("deleteMovie?id="+ids);
        if (res != "success") {
          return this.$message.error("删除失败！！！");
        }
        this.$message.success("删除成功！！！");
        this.getList();
      },

      goUrl(row) {
        window.open(row.film,"_blank")
      }

    },

  }
</script>

<style lang="less" scoped>
  .el-breadcrumb{
    margin-bottom: 15px;
    font-size: 17px;
  }
</style>


