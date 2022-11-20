<!-- <template>
    <div>
  
      <el-form :model="name">
        <el-input v-model="name.id">
      </el-input>
      <el-button type="primary" @click="(refresh())">主要按钮</el-button>
      </el-form>
      
    
    </div>


</template>

<script>
    export default{
        created(){
           this.allStaff(); 
        },       
    data(){
        return{
          staffList:[],
          name: {
            id:'1'
          }
        }
    },
    methods:{ // 刷新
	  refresh(){
	   this.$router.go(0)
	  },
                async allStaff(){
            const {data:res}=await this.$http.get("allStaff");
            this.staffList=res.data;
         }
	},

  }
</script> -->

<template>
<div>
  <div>
  <el-upload
    :limit="1"
    class="upload-demo"
    ref="upload"
    action
    :on-preview="handlePreview"
    :on-remove="handleRemove"
    :file-list="fileList"
    :auto-upload="false"
    :http-request="UploadSubmit"
  >
    <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
    <el-button
      style="margin-left: 10px"
      size="small"
      type="success"
      @click="submitUpload"
      >上传到服务器</el-button
    >
  </el-upload>   
</div>
</div>
</template>
<script>
import axios from "axios";
export default {
data() {
  return {
    fileList: [],
    filename: ""
  };
},
methods: {
  UploadSubmit(param) {
    var file = param.file;
    this.filename=file.name;
    alert(this.filename);
    //console.log(param.file);
    var file_form = new FormData(); //获取上传表单（文件）
    file_form.append("file", file);
    axios({
      url: "/up",
      method: "POST",
      headers: {
        token: localStorage.getItem("token"),
      },
      data: file_form,
    })
      .then((res) => {
        console.log(res);
      })
      .catch((err) => {
        console.log(err);
      });
  },
  submitUpload() {
    this.$refs.upload.submit();
  },
  handleRemove(file, fileList) {
    console.log(file, fileList);
  },
  handlePreview(file) {
    console.log(file);
  },
},
created() {},
};
</script>



<style lang="less" scoped>
  .el-breadcrumb{
    margin-bottom: 15px;
    font-size: 17px;
  }
</style>