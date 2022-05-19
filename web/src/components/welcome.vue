<template>
  <div>
    <el-input v-model="account" placeholder="请输入帐号"></el-input>
    <el-input v-model="password" placeholder="请输入密码" show-password></el-input>
    <el-button type="primary" @click="login">登录</el-button>
    <el-button type="primary" @click="register">注册</el-button>
  </div>
</template>

<script>
export default {
  name: 'welcome',
  data () {
    return {
      account: '',
      password: ''
    }
  },
  methods: {
    register: async function () {
      const ret = await this.$http.post('/register/', {
        account: this.account,
        password: this.password
      }).then(function (response) {
        console.log(response);
      }).catch(function (error) {
        console.log(error);
      });
      // this.$router.push({path:'/registry'});
    },
    login: async function () {
      const ret = await this.$http.post('/login/', {
        account: this.account,
        password: this.password
      }).then(function () {
        alert('登录成功');
      }).catch(function (e) {
        alert(e)
      })
      // this.$router.push({path: '/board'});
    }
  }
}
</script>

<style scoped>

</style>