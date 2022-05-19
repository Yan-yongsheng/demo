<template lang="">
    <div>
        <el-container>
            <el-header>
                <div style="position: fixed;z-index: 999;background-color: white;width: 100%;top:0;padding: 10px;">
                    <el-button @click="this.$router.go(-1)" type="text" style="text-decoration: underline;">返回
                    </el-button>
                    <el-input v-model.trim="searchText" placeholder="请输入搜索内容" class="input-with-select"
                        @keyup.enter="searchServiceResult" style="width: 50%; margin-left: 40px;">
                        <template #append>
                            <el-button type="primary" @click="searchServiceResult"
                                style="background-color: rgb(7, 118, 245);color: white;">搜全库
                            </el-button>
                        </template>
                    </el-input>
                    <div style="margin-top: 15px;font-size: 15px;color: rgb(98, 89, 89);">共为您搜索到{{totalCount}}条相关服务
                    </div>
                </div>
            </el-header>
            <el-main style="text-align: center; margin-top: 20px;">
                <el-table :data="tableData" stripe border style="width: 100%;">
                    <el-table-column label="服务名称" align="center">
                        <template v-slot:default="scope">
                            <div v-html="scope.row.serviceTitle"></div>
                        </template>
                    </el-table-column>
                    <!-- <el-table-column label="一级分类" align="center">
                        <template v-slot:default="scope">
                            <div v-html="scope.row.categoryOne"></div>
                        </template>
                    </el-table-column>
                    <el-table-column label="二级分类" align="center">
                        <template v-slot:default="scope">
                            <div v-html="scope.row.categoryTwo"></div>
                        </template>
                    </el-table-column> -->
                    <el-table-column label="服务机构" align="center">
                        <template v-slot:default="scope">
                            <div v-html="scope.row.institutionTitle"></div>
                        </template>
                    </el-table-column>
                    <el-table-column label="检测主体" align="center">
                        <template v-slot:default="scope">
                            <div v-html="scope.row.categoryThree"></div>
                        </template>
                    </el-table-column>
                    <el-table-column label="检测项目" align="center">
                        <template v-slot:default="scope">
                            <div v-html="scope.row.detectionItem"></div>
                        </template>
                    </el-table-column>
                    <el-table-column prop="cycle" label="服务时间/天" align="center" />
                    <el-table-column prop="price" label="服务价格/元" align="center" />
                    <el-table-column label="服务评分" align="center">
                        <template v-slot:default="scope">
                            <el-popover placement="top-start" title="评分" :width="200" trigger="click">
                                <template v-slot:default>
                                    <div>服务质量评分：{{scope.row.qualityScore}}</div>
                                    <div>服务速度评分：{{scope.row.speedScore}}</div>
                                    <div>服务态度评分：{{scope.row.attitudeScore}}</div>
                                    <div>机构评分：3</div>
                                </template>
                                <template #reference>
                                    <el-button type="text" style="color: rgb(40, 156, 233);">
                                        服务评分
                                    </el-button>
                                </template>
                            </el-popover>
                        </template>
                    </el-table-column>
                    <el-table-column prop="serviceDetail" label="服务描述" align="center">
                        <template v-slot:default="scope">
                            <el-button type="text" @click="sendScopeData(scope)" style="color: rgb(40, 156, 233);">
                                服务描述
                            </el-button>
                        </template>
                    </el-table-column>
                    <el-table-column label="服务详情" align="center">
                        <template v-slot:default="scope">
                            <div v-if="!scope.row.serviceUrl">暂无</div>
                            <a :href="scope.row.serviceUrl" target="_blank" v-else
                                style="font-size: 14px;color: rgb(40, 156, 233);font-weight: bolder;">
                                服务详情
                            </a>
                        </template>
                    </el-table-column>
                </el-table>

                <el-pagination background :current-page="currentPage" :page-size="pageSize" :pager-count="pagerCount"
                    layout="prev, pager, next" :total="totalCount" style="margin-top: 10px;"
                    @current-change="onPageChange">
                </el-pagination>

            </el-main>
        </el-container>

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
        created() {
            this.searchServiceResult()
        },
        name: "SearchPage",
        props: ["searchText"],
        data() {
            return {
                drawer: false,
                direction: "rtl",
                drawerTitle: "",
                drawerContent: "",
                tableData: [],
                currentPage: 1,
                pageSize: 10,
                pagerCount: 5,
                totalCount: 0
            }
        },
        methods: {
            async searchServiceResult() {
                if (!this.searchText) return
                this.currentPage = 1
                const ret = await this.$http.get('/serviceTotal/search', {
                    params: {
                        keyWords: this.searchText,
                        page: this.currentPage,
                        pageSize: this.pageSize
                    }
                })
                if (ret.data.code != 200) return this.$message.error(ret.data.msg)
                this.tableData = ret.data.data.serviceResult
                this.totalCount = ret.data.data.totalService
            },
            async onPageChange(val) {
                this.currentPage = val
                const ret = await this.$http.get('/serviceTotal/search', {
                    params: {
                        keyWords: this.searchText,
                        page: this.currentPage,
                        pageSize: this.pageSize
                    }
                })
                if (ret.data.code != 200) return this.$message.error(ret.data.msg)
                this.tableData = ret.data.data.serviceResult
            },
            sendScopeData(scope) {
                this.drawerTitle = scope.row.serviceTitle
                this.drawerContent = scope.row.serviceDetail
                this.drawer = true
            }
        },
    }
</script>
<style lang="less" scoped>

</style>