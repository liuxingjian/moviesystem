<template>
    <div class="circle">
      <h3>这里展示搜索数据</h3>
      <div id="myChart" :style="{width:'700px',height:'700px'}"></div>
    </div>
  </template>
  
  <script>
  export default {
    name: "Charts",
    data(){
      return{
        data:[
          { value: 1048, name: 'Search Engine' },
          { value: 735, name: 'Direct' },
          { value: 580, name: 'Email' },
          { value: 484, name: 'Union Ads' },
          { value: 300, name: 'Video Ads' }
        ],
      }
    },
    //在mounted生命周期函数发送请求给后端接口
    mounted() {
      //最后在mounted生命周期函数中调用改方法
      this.Draw()
      this.$axios.get( '/books/select').then((res) => {
        // 读取接口请求成功回传回来的数据
        var books = res.data.Books
        // 定义数组，存放一会覆盖echarts图形的数据
        var data = []
        // 循环遍历数组，取出数据，转成和data一样的格式
        for(var i = 0; i < books.length; i++) {
          var d = {name: '', value: 0}
          d.name = books[i].name
          d.value = books[i].num
          // 往data数组中添加数据
          data.push(d)
        }
        // 排序，b - a降序——a - b升序
        data.sort((a , b) => b.value - a.value)
        // 覆盖data(){}中全局变量的数据
        this.data = data
        // 画出图形
        this.Draw()
      }).catch((err) => {
        console.log(err)
        alert('请求失败！请检查后端接口！！！！')
      })
    },
    methods:{
      Draw(){
        let myChart = this.$echarts.init(document.getElementById('myChart'))
        myChart.setOption({
          tooltip: {
            trigger: 'item'
          },
          legend: {
            top: '5%',
            left: 'center'
          },
          series: [
            {
              name: 'Access From',
              type: 'pie',
              radius: ['40%', '70%'],
              avoidLabelOverlap: false,
              itemStyle: {
                borderRadius: 10,
                borderColor: '#fff',
                borderWidth: 2
              },
              label: {
                show: false,
                position: 'center'
              },
              emphasis: {
                label: {
                  show: true,
                  fontSize: '40',
                  fontWeight: 'bold'
                }
              },
              labelLine: {
                show: false
              },
              data: this.data,
            }
          ]
        })
      }
    }
  }
  </script>
  
  <style scoped>
  
  </style>
  