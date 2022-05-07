<template lang="">
    <div>
        <el-container>
            <el-aside width="205px">
                <!-- <h4>服务导航</h4> -->
                <el-menu active-text-color="#ffd04b" background-color="#545c64" class="el-menu-vertical-demo"
                    default-active="2" text-color="#fff"
                    style="position: fixed;top: 10px;height: 97%;width: 205px;overflow: scroll;">
                    <el-sub-menu popper-append-to-body="true" unique-opened="true">
                        <template #title>
                            <div style="width: 130px;font-size: 17px;">服务库 共{{serviceTotalCount}}项</div>
                        </template>
                        <el-sub-menu v-for="(item1,index1) in categoryList" :index="index1+1" :key="index1+1">
                            <template #title>
                                <span>{{index1+1}}.&nbsp;{{item1.categoryOne}}</span>
                            </template>
                            <el-menu-item v-for="(item2,index2) in item1.subCategory" :index="index1+'-'+index2"
                                :key="index1+'-'+index2"
                                @click="getCategoryContent(item1.categoryOne,item2.categoryTwo)">
                                {{item2.categoryTwo}}&nbsp;&nbsp;({{item2.nums}})
                            </el-menu-item>
                        </el-sub-menu>
                    </el-sub-menu>
                </el-menu>
            </el-aside>

            <el-main>
                <el-input v-model.trim="searchText" placeholder="请输入搜索内容" class="input-with-select" style="width: 60%;"
                    @keyup.enter="search">
                    <template #append>
                        <el-button type="primary" @click="search"
                            style="background-color: rgb(7, 118, 245);color: white;">搜全库
                        </el-button>
                    </template>
                </el-input>

                <el-table :data="tableData" stripe border style="width: 100%;margin-top: 10px;">
                    <el-table-column prop="categoryThree" label="检测主体" align="center" />
                    <el-table-column prop="institutionTitle" label="服务机构" align="center" />
                    <el-table-column prop="serviceTitle" label="服务名称" align="center" />
                    <el-table-column prop="detectionItem" label="检测项目" align="center" />
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
                    <el-table-column label="操作" align="center">
                        <el-space>
                            <el-button size="mini" type="warning" round>修改</el-button>
                            <el-button size="mini" type="danger" round>删除</el-button>
                        </el-space>
                    </el-table-column>
                </el-table>

                <el-pagination background :current-page="currentPage" :page-size="pageSize" :pager-count="pagerCount"
                    layout="prev, pager, next" :total="totalCount" style="margin-top: 10px;"
                    @current-change="onPageChange">
                </el-pagination>
            </el-main>
        </el-container>
        <el-drawer v-model="drawer" :title="drawerTitle" :direction="direction">
            <div style="height: 50%;overflow: scroll;">{{drawerContent}}</div>
        </el-drawer>
    </div>
</template>
<script>
    export default {
        name: "ManagerPage",
        created() {
            this.getCategoryList()
            this.getCategoryContent("地矿与珠宝玉石全部分类", "金属与合金", this.currentPage, this.pageSize)
        },
        data() {
            return {
                drawer: false,
                direction: "rtl",
                drawerTitle: "",
                drawerContent: "",
                categoryOne: "",
                categoryTwo: "",
                serviceTotalCount: 0,
                categoryList: [],
                tableData: [],
                searchText: "",
                pageSize: 10,
                pagerCount: 5,
                totalCount: 0,
                currentPage: 1
            }
        },
        methods: {
            async getCategoryList() {
                const ret = await this.$http.get('/serviceTotal/category')
                if (ret.data.code != 200) return this.$message.error(ret.data.msg)
                this.serviceTotalCount = ret.data.data.totalService
                this.categoryList = ret.data.data.categoryList
            },
            async getCategoryContent(categoryOne, categoryTwo) {
                this.categoryOne = categoryOne
                this.categoryTwo = categoryTwo
                this.currentPage = 1
                const ret = await this.$http.get('/serviceTotal/category/content', {
                    params: {
                        categoryOne: categoryOne,
                        categoryTwo: categoryTwo,
                        currentPage: this.currentPage,
                        pageSize: this.pageSize
                    }
                })
                // console.log(ret)
                if (ret.data.code != 200) return this.$message.error(ret.data.msg)
                this.tableData = ret.data.data.records
                this.totalCount = ret.data.data.total
            },
            search() {
                if (!this.searchText) return
                this.$router.push('/searchPage/' + this.searchText)
            },
            async onPageChange(val) {
                this.currentPage = val
                const ret = await this.$http.get('/serviceTotal/category/content', {
                    params: {
                        categoryOne: this.categoryOne,
                        categoryTwo: this.categoryTwo,
                        currentPage: this.currentPage,
                        pageSize: this.pageSize
                    }
                })
                if (ret.data.code != 200) return this.$message.error(ret.data.msg)
                this.tableData = ret.data.data.records
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
    h4 {
        /* margin: 5px 20px; */
        font-size: 25px;
        color: rgb(8, 131, 246);
        font-family: cursive;
    }

    .el-header,
    .el-footer {
        background-color: #b3c0d1;
        color: var(--el-text-color-primary);
        text-align: center;
        line-height: 60px;
    }

    .el-aside {
        color: var(--el-text-color-primary);
    }

    .el-main {
        /* background-color: #e9eef3; */
        color: var(--el-text-color-primary);
        text-align: center;
        --el-main-padding: 0;
        padding-left: 10px;
        padding-right: 10px;
    }

    body>.el-container {
        margin-bottom: 40px;
    }

    .el-container:nth-child(5) .el-aside,
    .el-container:nth-child(6) .el-aside {
        line-height: 260px;
    }

    .el-container:nth-child(7) .el-aside {
        line-height: 320px;
    }

    .input-with-select {
        background-color: #fff;
    }
</style>