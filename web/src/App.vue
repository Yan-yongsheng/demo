<template lang="">
  <div>
    <img id="bg" src="./assets/background.jpeg" alt="背景图片">
    <router-view></router-view>
  </div>
</template>
<script>
  export default {
    name: "MyApp"
  }
</script>
<style lang="less" scoped>
  body {
    margin: 0px;
    padding: 0px;
  }

  #bg {
    width: 100%;
    height: 100%;
    top: 0px;
    left: 0px;
    position: fixed;
    z-index: -999;
    filter: alpha(opacity=10);
    -moz-opacity: 0.1;
    opacity: 0.1;
  }
</style>