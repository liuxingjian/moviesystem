<template>
  <div id="app">
   <router-view></router-view>
  </div>
</template>

<script>

export default {
  name: 'App',

}
</script>

<style>
.el-tooltip__popper {
width: 80%;/*修改宽度*/
line-height: 14px;
background: rgb(255, 255, 255) !important;/*背景色  !important优先级*/
opacity: 1 !important;/*背景色透明度*/
color: #000000 !important;/*字体颜色*/
overflow: auto;
}
  /* 更改背景色 */
  .el-tooltip__popper.is-dark {
    background: rgba(255, 255, 255, 0.9)!important;
    color: #FFF;
    max-width: 18%;
    line-height: 24px;
  }
  /* 更改箭头颜色 */
  .el-tooltip__popper[x-placement^=top] .popper__arrow::after {
      border-top-color: rgba(70,76,91,.9)!important;
  }
  .el-tooltip__popper[x-placement^=top] .popper__arrow{
    border-top-color: rgba(91, 90, 70, 0.9)!important;
  }

</style>

