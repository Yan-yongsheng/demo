<template lang="">
    <div>
        <el-container>
            <el-aside width="280px" height="500px">
                <el-card class="box-card" style="width: 276px;position: fixed;top: 35px ;">
                    <template #header>
                        <div class="card-header">
                            <span>需求描述</span>
                        </div>
                    </template>
                    <div class="text item">
                        姓名：{{userDemand.name}}
                    </div>
                    <hr>
                    <div class="text item">
                        电话：{{userDemand.phone}}
                    </div>
                    <hr>
                    <div class="text item">
                        邮箱：{{userDemand.mail}}
                    </div>
                    <hr>
                    <div class="text item">
                        需求：{{userDemand.demand}}
                    </div>
                    <hr>
                    <div class="text item">
                        价格约束：{{userDemand.price}}
                    </div>
                    <hr>
                    <div class="text item">
                        时间约束：{{userDemand.cycle}}
                    </div>
                    <hr>
                    <el-space size="small" style="width: 100%; margin-top: 5px;overflow: scroll;">
                        <el-button type="danger" size="small" round @click="sortByDefault">默认排序</el-button>
                        <el-button type="warning" size="small" round @click="limitResult">组合优化</el-button>
                        <!-- <el-button type="primary" size="small" round>按评价排序</el-button> -->
                    </el-space>
                    <div style="margin-top: 20px;font-size: 15px;color: rgb(98, 89, 89);">
                        &nbsp;&nbsp;&nbsp;&nbsp;共匹配到{{sum}}条结果，其中{{groupSum}}条为组合服务
                    </div>
                </el-card>
            </el-aside>

            <el-main style="text-align: center;">
                <el-row v-for="(result,index) in resultListShow">
                    <el-space>
                        <el-button type="success" round size="mini">{{index+1}}</el-button>
                        <el-card class="box-card" style="width: 250px" v-for="item in result">
                            <template #header>
                                <div class="card-header">
                                    <div v-html="item.serviceTitle"></div>
                                </div>
                            </template>
                            <div class="text item" v-html="item.institutionTitle">
                            </div>
                            <br>
                            <div>时间/天：{{item.cycle}}&nbsp;&nbsp;&nbsp;价格/元：{{item.price}}</div>
                            <br>
                            <el-button type="text" style="color: rgb(6, 131, 248);"
                                @click="showServiceDetail(item.serviceTitle,item.serviceDetail)">服务描述</el-button>
                            <el-button type="text">
                                <a :href="item.serviceUrl" target="_blank"
                                    style="text-decoration: none;color: rgb(6, 131, 248);">
                                    详情页
                                </a>
                            </el-button>
                            <el-popover placement="top-start" title="评价" :width="200" trigger="click">
                                <template v-slot:default>
                                    <div>服务质量评分：{{item.qualityScore}}</div>
                                    <div>服务速度评分：{{item.speedScore}}</div>
                                    <div>服务态度评分：{{item.attitudeScore}}</div>
                                </template>
                                <template #reference>
                                    <el-button type="text" style="color: rgb(6, 131, 248);">评价
                                    </el-button>
                                </template>
                            </el-popover>
                            <el-button type="text" style="color: rgb(6, 131, 248);" @click="writeComment(item.id)">发表评价
                            </el-button>
                        </el-card>
                    </el-space>
                </el-row>

                <el-pagination background :current-page="currentPage" :page-size="pageSize" :pager-count="pagerCount"
                    layout="prev, pager, next" :total="totalCount" style="margin-top: 10px;"
                    @current-change="onPageChange">
                </el-pagination>
            </el-main>
        </el-container>
        <el-dialog v-model="dialogVisible" title="发布评价" width="50%" @close="onDialogClose">
            <el-form ref="form" :model="form" label-width="130px">
                <el-form-item label="服务评分" prop="score" style="line-height: 50px;">
                    <el-rate v-model="form.score" style="text-align: center;line-height: 50px;" size="large" />
                </el-form-item>
                <el-form-item label="发表评论" prop="content">
                    <el-input v-model="form.content"></el-input>
                </el-form-item>
            </el-form>

            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="dialogVisible = false">取消</el-button>
                    <el-button type="primary" @click="submitComment">确认</el-button>
                </span>
            </template>
        </el-dialog>
        <el-drawer v-model="drawer" :direction="direction">
            <template v-slot:title>
                <div v-html="drawerTitle"></div>
            </template>
            <div style="height: 50%;overflow: scroll;" v-html="drawerContent"></div>
        </el-drawer>
    </div>
</template>
<script>
    export default {
        name: "ResultPage",
        props: ['id'],
        created() {
            this.getUserDemandResult(this.id)
        },
        data() {
            return {
                dialogVisible: false,
                form: {
                    score: "",
                    content: ""
                },
                userDemand: {},
                resultList: [],
                resultListShow: [],
                sum: 0,
                groupSum: 0,
                drawer: false,
                direction: "rtl",
                drawerTitle: "",
                drawerContent: "",
                pageSize: 6,
                pagerCount: 5,
                totalCount: 0,
                currentPage: 1,
                noLimitData: '',
                limitData: '',
                serviceId: ''
            }
        },
        methods: {
            async getUserDemandResult(id) {
                const ret = await this.$http.get('/demand/match/' + id)
                if (ret.data.code != 200) return this.$message.error(ret.data.msg)
                this.userDemand = ret.data.data.userDemand
                this.noLimitData = ret.data.data
                this.resultList = this.noLimitData.resultList
                this.sum = this.noLimitData.sum
                this.groupSum = this.noLimitData.groupSum
                this.resultListShow = this.resultList.slice(0, this.pageSize)
                this.totalCount = this.noLimitData.sum
                this.$message.success("匹配成功")
            },
            showServiceDetail(title, detail) {
                this.drawerTitle = title,
                    this.drawerContent = detail,
                    this.drawer = true
            },
            onPageChange(val) {
                this.currentPage = val
                this.resultListShow = this.resultList.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize)
            },
            sortByDefault() {
                this.resultList = this.noLimitData.resultList
                this.sum = this.noLimitData.sum
                this.groupSum = this.noLimitData.groupSum
                this.totalCount = this.sum
                this.resultList.sort((x, y) => {
                    return x.length - y.length
                })
                this.currentPage = 1
                this.resultListShow = this.resultList.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize)
            },
            writeComment(id) {
                this.serviceId = id
                this.dialogVisible = true
            },
            async submitComment() {
                // console.log(this.form.score)
                // console.log(this.form.content)
                const ret = await this.$http.get('/demand/comment', {
                    params: {
                        goods_id: this.serviceId,
                        score: this.form.score,
                        comment_details: this.form.content
                    }
                })
                if(ret.data.code==200){
                    this.$message.success(ret.data.data)
                }else{
                    this.$message.error(ret.data.data)
                }
                // console.log(ret)
                this.dialogVisible = false
            },
            async limitResult() {
                const ret = await this.$http.get('/demand/limit/' + this.id)
                //    console.log(ret)
                if (ret.data.code == 200) {
                    this.limitData = ret.data.data
                    this.resultList = this.limitData.resultList
                    this.resultListShow = this.resultList.slice(0, this.pageSize)
                    this.sum = this.limitData.sum
                    this.groupSum = this.limitData.groupSum
                    this.totalCount = this.sum
                    this.$message.success("请求成功！")
                } else {
                    this.$message.error("请求失败！")
                }
            },
            onDialogClose() {
                this.$refs.form.resetFields()
            },
        },
    }
</script>
<style lang="less" scoped>
    .el-space::-webkit-scrollbar {
        width: 1px;
        height: 6px;
    }

    .el-space::-webkit-scrollbar-track {
        background: #f2f2f2;
    }

    .el-space::-webkit-scrollbar-thumb {
        background: rgba(0, 0, 0, 0.40);
    }

    .el-space::-webkit-scrollbar-thumb:hover {
        background: #f2f2f2
    }

    .el-space::-webkit-scrollbar-thumb:active {
        background: rgba(0, 0, 0, 0.2);
    }
</style>