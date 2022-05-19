<template>
  <div>
    <el-input v-model="category" placeholder="请输入身份类别:用户 1； 商家 2； 专家 3" ></el-input>
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
      password: '',
      category: ''
    }
  },
  methods: {
    register: async function () {
      const ret = await this.$http.post('/register/', {
        account: this.account,
        password: this.password,
        category: this.category
      })
      if (ret.data.code != 200) return this.$message.error(ret.data.msg)
      console.log(ret.data.data);
      alert(ret.data.data);

      // this.$router.push({path:'/registry'});
    },
    login: async function () {
      const ret = await this.$http.post('/login/', {
        account: this.account,
        password: this.password,
        category: this.category
      })
      if (ret.data.code != 200) return this.$message.error(ret.data.msg)
      console.log(ret.data.data);
      alert(ret.data.data);
      // this.$router.push({path: '/board'});
    }
  }
}
</script>

<style scoped>

</style>