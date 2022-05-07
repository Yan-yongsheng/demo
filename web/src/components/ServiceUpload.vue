<template lang="">
    <div>
        <el-button type="primary" style="margin-top: 10px;margin-left: 6px;" @click="dialogVisible1=true">
            机构信息上传
        </el-button>
        <el-button type="danger" style="margin-top: 10px;margin-left: 6px;" @click="dialogVisible2=true">
            服务发布
        </el-button>

        <el-table :data="tableData" stripe border style="width: 100%;margin-top: 10px;">
            <el-table-column prop="categoryThree" label="检测主体" align="center" />
            <el-table-column prop="serviceTitle" label="服务名称" align="center" />
            <el-table-column prop="institutionTitle" label="服务机构" align="center" />
            <el-table-column prop="detectionItem" label="检测项目" align="center" />
            <el-table-column prop="standard" label="检测标准" align="center" />
            <el-table-column prop="equipment" label="仪器设备" align="center" />
            <el-table-column prop="serviceDetail" label="服务描述" align="center">
                <template v-slot:default="scope">
                    <el-button type="text" @click="sendScopeData(scope)" style="color: rgb(40, 156, 233);">服务描述</el-button>
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
            <el-table-column label="上传时间" align="center">
                <template v-slot:default="scope">
                    {{dateFilter(scope.row.createTime)}}
                </template>
            </el-table-column>

        </el-table>

        <el-dialog v-model="dialogVisible1" title="机构信息上传" width="50%" @close="onDialogClose1">
            <el-form ref="qualify" :model="qualify" :rules="rules1" label-width="80px">
                <el-form-item label="机构名称" prop="institutionTitle">
                    <el-input v-model="qualify.institutionTitle"></el-input>
                </el-form-item>
                <el-form-item label="机构位置" prop="address">
                    <el-input v-model="qualify.address"></el-input>
                </el-form-item>
                <el-form-item label="机构行业" prop="business">
                    <el-input v-model="qualify.business"></el-input>
                </el-form-item>
                <el-form-item label="机构资质" prop="qualification">
                    <el-input v-model="qualify.qualification"></el-input>
                </el-form-item>
                <el-form-item label="评价指标">
                    <el-input v-model="qualify.number1" placeholder="请输入拥有资质检测证书数量"></el-input>
                    <el-input v-model="qualify.number2" style="margin-top: 5px;" placeholder="请输入可检测类别项目数量"></el-input>
                    <el-input v-model="qualify.number3" style="margin-top: 5px;" placeholder="请输入业务涉及领域数量"></el-input>
                    <el-input v-model="qualify.number4" style="margin-top: 5px;" placeholder="请输入出具检验认证报告数量"></el-input>
                    <el-input v-model="qualify.number5" style="margin-top: 5px;" placeholder="请输入新检测方法研发数量"></el-input>
                    <el-input v-model="qualify.number6" style="margin-top: 5px;" placeholder="请输入制定检测标准数量"></el-input>
                    <el-input v-model="qualify.number7" style="margin-top: 5px;" placeholder="请输入研发人员占人员比重增长率"></el-input>
                    <el-input v-model="qualify.number8" style="margin-top: 5px;" placeholder="请输入硕士以上研发人员占人员比重增长率"></el-input>
                    <el-input v-model="qualify.number9" style="margin-top: 5px;" placeholder="请输入自有研发投入增长率"></el-input>
                    <el-input v-model="qualify.number10" style="margin-top: 5px;" placeholder="请输入获政府科技项目资金投入增长率"></el-input>
                </el-form-item>
            </el-form>

            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="dialogVisible1 = false">取消</el-button>
                    <el-button type="primary" @click="qualifyUpload">确认</el-button>
                </span>
            </template>
        </el-dialog>


        <el-dialog v-model="dialogVisible2" title="服务发布 (请确保已完成机构信息上传)" width="50%" @close="onDialogClose2">
            <el-form ref="form" :model="form" :rules="rules2" label-width="80px">
                <el-form-item label="服务描述" prop="serviceDetail">
                    <el-input v-model="form.serviceDetail" type="textarea" placeholder="请尽可能详细的描述您的服务"></el-input>
                    <el-button type="primary" size="mini" @click="getMatchResult">自动分解</el-button>
                    <hr style="width: 100%;">
                </el-form-item>
                <el-form-item label="机构名称" prop="institutionTitle">
                    <el-input v-model="form.institutionTitle"></el-input>
                </el-form-item>
                <el-form-item label="检测项目" prop="detectionItem">
                    <el-input v-model="form.detectionItem"></el-input>
                </el-form-item>
                <el-form-item label="检测标准" prop="standard">
                    <el-input v-model="form.standard"></el-input>
                </el-form-item>
                <el-form-item label="仪器设备" prop="equipment">
                    <el-input v-model="form.equipment"></el-input>
                </el-form-item>
                <el-form-item label="一级分类" prop="categoryOne">
                    <el-input v-model="form.categoryOne" placeholder="参照服务库的一级类别"></el-input>
                </el-form-item>
                <el-form-item label="二级分类" prop="categoryTwo">
                    <el-input v-model="form.categoryTwo" placeholder="参照服务库的二级类别"></el-input>
                </el-form-item>
                <el-form-item label="检测主体" prop="categoryThree">
                    <el-input v-model="form.categoryThree" placeholder="参照服务库的检测主体"></el-input>
                </el-form-item>
                <el-form-item label="服务名称" prop="serviceTitle">
                    <el-input v-model="form.serviceTitle"></el-input>
                </el-form-item>
                <el-form-item label="服务地址" prop="serviceUrl">
                    <el-input v-model="form.serviceUrl"></el-input>
                </el-form-item>
            </el-form>

            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="dialogVisible2 = false">取消</el-button>
                    <el-button type="primary" @click="serviceUpload">确认</el-button>
                </span>
            </template>
        </el-dialog>

        <el-drawer v-model="drawer" :title="drawerTitle" :direction="direction">
            <div style="height: 50%;overflow: scroll;">{{drawerContent}}</div>
        </el-drawer>
    </div>
</template>
<script>
    export default {
        name: "ServiceUpload",
        created() {
            this.getServiceUploadList()
        },
        data() {
            return {
                dialogVisible1: false,
                dialogVisible2: false,
                drawer: false,
                direction: "rtl",
                drawerTitle: "",
                drawerContent: "",
                qualify:{
                    institutionTitle:'',
                    address:'',
                    business:'',
                    qualification:'',
                    number1:'',
                    number2:'',
                    number3:'',
                    number4:'',
                    number5:'',
                    number6:'',
                    number7:'',
                    number8:'',
                    number9:'',
                    number10:'',
                },
                rules1: {
                    institutionTitle: [
                        { required: true, message: '机构名称必填', trigger: 'blur' }
                    ],
                    address: [
                        { required: true, message: '机构地理位置必填', trigger: 'blur' }
                    ],
                    business: [
                        { required: true, message: '机构可检测行业信息必填', trigger: 'blur' }
                    ],
                    qualification: [
                        { required: true, message: '机构资质信息必填', trigger: 'blur' }
                    ],
                },
                form: {
                    categoryOne: '',
                    categoryTwo: '',
                    categoryThree: '',
                    serviceUrl: '',
                    serviceTitle: '',
                    detectionItem: '',
                    institutionTitle: '',
                    standard: '',
                    equipment: '',
                    serviceDetail: ''
                },
                tableData: [],
                rules2: {
                    categoryOne: [
                        { required: true, message: '一级分类必填', trigger: 'blur' }
                    ],
                    categoryTwo: [
                        { required: true, message: '二级分类必填', trigger: 'blur' }
                    ],
                    categoryThree: [
                        { required: true, message: '三级分类必填', trigger: 'blur' }
                    ],
                    serviceTitle: [
                        { required: true, message: '服务名称必填', trigger: 'blur' }
                    ],
                    detectionItem: [
                        { required: true, message: '检测项目必填', trigger: 'blur' }
                    ],
                    serviceDetail: [
                        { required: true, message: '服务描述必填', trigger: 'blur' }
                    ]
                }
            }
        },
        methods: {
            qualifyUpload(){
                this.$refs.qualify.validate(async valid=>{
                    if(valid){
                        this.dialogVisible1=false
                        this.$message.success("上传成功！")
                    }else{
                        this.$message.error("发布失败！")
                    }
                })
            },
            serviceUpload() {
                this.$refs.form.validate(async valid => {
                    if (valid) {
                        this.dialogVisible2 = false
                        const ret = await this.$http.post('/serviceUpload', {
                            categoryOne: this.form.categoryOne,
                            categoryTwo: this.form.categoryTwo,
                            categoryThree: this.form.categoryThree,
                            serviceUrl: this.form.serviceUrl,
                            serviceTitle: this.form.serviceTitle,
                            detectionItem: this.form.detectionItem,
                            institutionTitle: this.form.institutionTitle,
                            standard: this.form.standard,
                            equipment: this.form.equipment,
                            serviceDetail: this.form.serviceDetail
                        })
                        if (ret.data.code != 200) return this.$message.error(ret.data.msg)
                        this.getServiceUploadList()
                        this.$message.success(ret.data.data)
                    } else {
                        this.$message.error("发布失败！")
                    }
                })
            },
            async getServiceUploadList() {
                const ret = await this.$http.get('/serviceUpload')
                if (ret.data.code != 200) return this.$message.error(ret.data.msg)
                this.tableData = ret.data.data
            },
            onDialogClose1() {
                this.$refs.qualify.resetFields()
            },
            onDialogClose2() {
                this.$refs.form.resetFields()
            },
            async getMatchResult() {
                if (!this.form.serviceDetail) return
                const ret = await this.$http.post('/serviceUpload/match', {
                    words: this.form.serviceDetail
                })
                if (ret.data.code != 200) return this.$message.error(ret.data.msg)
                this.form.institutionTitle = ret.data.data.organization
                this.form.detectionItem = ret.data.data.project
                this.form.standard = ret.data.data.standard
                this.form.equipment = ret.data.data.equipment
                this.$message.success("分解完成")
                // 中国广州分析检测中心可以提供食品添加剂,农药残留,营养成分,理化性质,限度等相关检查，使用气相质谱仪，符合国家LC-MS-MS标准
            },
            fillNumber(num) {
                return num > 9 ? num : '0' + num
            },
            sendScopeData(scope) {
                this.drawerTitle = scope.row.serviceTitle
                this.drawerContent = scope.row.serviceDetail
                this.drawer = true
            }
        },
        computed: {
            dateFilter() {
                return (str) => {
                    const date = new Date(str)
                    const yy = date.getFullYear()
                    const mm = this.fillNumber(date.getMonth() + 1)
                    const dd = this.fillNumber(date.getDate())
                    const hh = this.fillNumber(date.getHours())
                    const MM = this.fillNumber(date.getMinutes())
                    const ss = this.fillNumber(date.getSeconds())

                    return `${yy}-${mm}-${dd} ${hh}:${MM}:${ss}`
                }
            }
        }
    }
</script>
<style lang="less" scoped>

</style>