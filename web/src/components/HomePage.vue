<template lang="">
    <div>
        <el-container>
            <el-header style="padding: 0px;">
                <img src="https://one-stop-test.com/_nuxt/img/top.9548e1f.png" style="width: 100%;">
            </el-header>
            <el-main
                style="padding-top: 0px; padding-left: 20px; display: flex; flex-direction: row;justify-content: space-between;">
                <el-carousel style="width: 35%;">
                    <el-carousel-item>
                        <img src="../assets/light/light1.png" alt="" style="height: 100%;width: 100%;">
                    </el-carousel-item>
                    <el-carousel-item>
                        <img src="../assets/light/light2.png" alt="" style="height: 100%;width: 100%;">
                    </el-carousel-item>
                    <el-carousel-item>
                        <img src="../assets/light/light3.png" alt="" style="height: 100%;width: 100%;">
                    </el-carousel-item>
                    <el-carousel-item>
                        <img src="../assets/light/light4.png" alt="" style="height: 100%;width: 100%;">
                    </el-carousel-item>
                </el-carousel>
                <div style="width: 30%;display: flex;flex-direction: column;">
                    <div style="width: 100%; height: 50%; display: flex;justify-content: space-between;">
                        <div
                            style="width: 49%;height: 98%;background-color: #70b9e4;border-radius: 13px;box-shadow: 4px 4px 5px rgb(112 185 228 / 70%);">
                            <router-link to="/servicePage" class="medium">服务库</router-link>
                        </div>
                        <div
                            style="width: 49%;height: 98%;background-color: #70cce3;border-radius: 13px;box-shadow: 4px 4px 5px rgb(112 204 227 / 70%);">
                            <router-link to="/requirement" class="medium">需求发布</router-link>
                        </div>
                    </div>
                    <div style="width: 100%; height: 50%; display: flex;justify-content: space-between;">
                        <div
                            style="width: 49%;height: 98%;background-color: #79dde0;border-radius: 13px;box-shadow: 4px 4px 5px rgb(121 221 224 / 70%);margin-top: 3px;">
                            <router-link to="/serviceUpload" class="medium">服务上传</router-link>
                        </div>
                        <div
                            style="width: 49%;height: 98%;background-color:#77d477;border-radius: 13px;box-shadow: 4px 4px 5px rgb(119 212 119 / 70%);margin-top: 3px;">
                            <router-link to="/manager" class="medium">后台管理</router-link>
                        </div>
                         <div
                            style="width: 49%;height: 98%;background-color:#77d477;border-radius: 13px;box-shadow: 4px 4px 5px rgb(119 212 119 / 70%);margin-top: 3px;">
                            <router-link to="/welcome" class="medium">注册登录</router-link>
                        </div>
                    </div>
                </div>
                <div style="width: 33%;">
                    <div style="width: 100%;height: 33%;display: flex;border-top: 1px solid rgb(226, 162, 25);">
                        <div style="width: 50%;height: 100%;text-align: center;">
                            <img class="right-img" src="../assets/right/1.png" alt="">
                            <a class="right-a" href="https://zwdtuser.sh.gov.cn:7443/tsoauth/login.jsp"
                                target="_blank">闵行一站测</a>
                        </div>
                        <div style="width: 50%;height: 100%;text-align: center;">
                            <img class="right-img" src="../assets/right/2.png" alt="">
                            <a href="https://testhui.com/" class="right-a" target="_blank">检测汇</a>
                        </div>
                    </div>
                    <div style="width: 100%;height: 33%;display: flex;border-top: 1px solid rgb(226, 162, 25);">
                        <div style="width: 50%;height: 100%;text-align: center;">
                            <img class="right-img" src="../assets/right/3.png" alt="">
                            <a class="right-a" href="http://www.bvtracker.com/#/" target="_blank">必维</a>
                        </div>
                        <div style="width: 50%;height: 100%;text-align: center;">
                            <img class="right-img" src="../assets/right/4.png" alt="">
                            <a href="https://www.woyaoce.cn/" class="right-a" target="_blank">我要测</a>
                        </div>
                    </div>
                    <div style="width: 100%;height: 33%;display: flex;border-top: 1px solid rgb(226, 162, 25);">
                        <div style="width: 50%;height: 100%;text-align: center;">
                            <img class="right-img" src="../assets/right/5.png" alt="">
                            <a class="right-a" href="https://www.zljweb.com/" target="_blank">浙里检</a>
                        </div>
                    </div>
                </div>
            </el-main>
            <el-footer>
                <div style="color: #12a7a5;font-size: 18px;font-weight: bolder;">友情链接</div>
                <hr>
                <div style="width: 100%; display: flex;justify-content: space-between;height: 120px;margin-top: 30px;">
                    <div style="width: 18%;border: 1px solid #12a7a5;border-radius: 5px;text-align: center;">
                        <img class="bottom-img" src="../assets/bottom/1.jpg" alt="">
                        <a class="bottom-a" href="https://www.samr.gov.cn/" target="_blank">国家市场监督管理总局</a>
                    </div>
                    <div style="width: 18%;border: 1px solid #12a7a5;border-radius: 5px;text-align: center;">
                        <img class="bottom-img" src="../assets/bottom/2.jpg" alt="">
                        <a class="bottom-a" href="http://www.customs.gov.cn/" target="_blank">海关总署</a>
                    </div>
                    <div style="width: 18%;border: 1px solid #12a7a5;border-radius: 5px;text-align: center;">
                        <img class="bottom-img" src="../assets/bottom/3.jpg" alt="">
                        <a class="bottom-a" href="http://www.ccaa.org.cn/" target="_blank">中国认证认可协会</a>
                    </div>
                    <div style="width: 18%;border: 1px solid #12a7a5;border-radius: 5px;text-align: center;">
                        <img class="bottom-img" src="../assets/bottom/4.jpg" alt="">
                        <a class="bottom-a" href="http://www.csg.org.cn/" target="_blank">中国文教体育用品协会</a>
                    </div>
                    <div style="width: 18%;border: 1px solid #12a7a5;border-radius: 5px;text-align: center;">
                        <img class="bottom-img" src="../assets/bottom/5.jpg" alt="">
                        <a class="bottom-a" href="https://www.cnas.org.cn/" target="_blank">CNAS</a>
                    </div>
                </div>
            </el-footer>
        </el-container>
        <!-- <el-carousel :interval="4000" type="card" height="200px">
            <el-carousel-item>
                <router-link to="/servicePage" class="medium">服务库</router-link>
            </el-carousel-item>
            <el-carousel-item>
                <router-link to="/requirement" class="medium">需求发布</router-link>
            </el-carousel-item>
            <el-carousel-item>
                <router-link to="/serviceUpload" class="medium">服务上传</router-link>
            </el-carousel-item>
            <el-carousel-item>
                <router-link to="/manager" class="medium">后台管理</router-link>
            </el-carousel-item>
             <el-carousel-item>
                <router-link to="/welcome" class="medium">注册登录</router-link>
            </el-carousel-item>
        </el-carousel> -->
    </div>
</template>
<script>
    export default {
        name: "HomePage",
        methods: {

        },
    }
</script>
<style lang="less" scoped>
    .medium {
        display: block;
        height: 100%;
        color: whitesmoke;
        text-decoration: none;
        font-size: 30px;
        font-family: Microsoft YaHei, "STHeiti", Helvetica, Arial, sans-serif;
        text-align: center;
        /* 为什么设置100%无效 */
        line-height: 500%;
    }

    .right-img {
        margin-top: 10px;
        width: 50px;
        height: 50px;
    }

    .right-a {
        text-decoration: none;
        text-align: center;
        display: block;
        font-size: 20px;
        font-family: Microsoft YaHei, "STHeiti", Helvetica, Arial, sans-serif;
        color: #333;
        margin-top: 5px;
    }

    .right-a:hover {
        color: rgb(0, 4, 255);
    }

    .bottom-img{
        width: 60px;
        height: 60px;
        margin-top: 10px;
    }

    .bottom-a{
        text-decoration: none;
        display: block;
        font-size: 14px;
        color: #666;
        font-family: Microsoft YaHei, "STHeiti", Helvetica, Arial, sans-serif;
        margin-top: 10px;
    }

    .bottom-a:hover {
        color: #10ddd9;
    }
</style>
