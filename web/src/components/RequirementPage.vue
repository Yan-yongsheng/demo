<template lang="">
    <div>
        <el-button type="danger" style="margin-top: 10px;margin-left: 6px;" @click="dialogVisible=true">
            发布新需求
        </el-button>

        <el-table :data="tableData" stripe border style="width: 100%;margin-top: 10px;">
            <el-table-column label="#" type="index" />
            <el-table-column prop="name" label="姓名" />
            <el-table-column prop="phone" label="电话" />
            <el-table-column prop="mail" label="邮箱" />
            <el-table-column prop="demand" label="需求描述" />
            <el-table-column prop="price" label="价格约束" />
            <el-table-column prop="cycle" label="时间约束" />
            <el-table-column label="发布时间">
                <template v-slot:default="scope">
                    {{dateFilter(scope.row.createTime)}}
                </template>
            </el-table-column>
            <el-table-column label="匹配结果">
                <template v-slot:default="scope">
                    <el-button type="primary" size="small">
                        <router-link :to="'/result/'+scope.row.id" style="text-decoration: none;color: whitesmoke;">智能匹配
                        </router-link>
                    </el-button>
                </template>
            </el-table-column>
        </el-table>

        <el-dialog v-model="dialogVisible" title="发布需求" width="50%" @close="onDialogClose">
            <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                <el-form-item label="姓名" prop="name">
                    <el-input v-model="form.name"></el-input>
                </el-form-item>
                <el-form-item label="电话" prop="phone">
                    <el-input v-model="form.phone"></el-input>
                </el-form-item>
                <el-form-item label="邮箱" prop="mail">
                    <el-input v-model="form.mail"></el-input>
                </el-form-item>
                <el-form-item label="需求描述" prop="demand">
                    <el-input v-model="form.demand" type="textarea" placeholder="请尽可能详细的描述您的需求"></el-input>
                </el-form-item>
                <el-form-item label="时间约束" prop="cycle">
                    <el-input v-model="form.cycle"></el-input>
                </el-form-item>
                <el-form-item label="价格约束" prop="price">
                    <el-input v-model="form.price"></el-input>
                </el-form-item>
            </el-form>

            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="dialogVisible = false">取消</el-button>
                    <el-button type="primary" @click="addRequirement">确认</el-button>
                </span>
            </template>
        </el-dialog>
    </div>
</template>
<script>
    export default {
        name: "RequirementPage",
        created() {
            this.getUserDemand()
        },
        data() {
            return {
                dialogVisible: false,
                form: {
                    name: '',
                    phone: '',
                    mail: '',
                    demand: '',
                    price:'',
                    cycle:''
                },
                tableData: [],
                rules: {
                    name: [
                        { required: true, message: '姓名为必填项', trigger: 'blur' }
                    ],
                    phone: [
                        { required: true, message: '电话为必填项', trigger: 'blur' }
                    ],
                    mail: [
                        { required: true, message: '邮箱为必填项', trigger: 'blur' }
                    ],
                    demand: [
                        { required: true, message: '需求为必填项', trigger: 'blur' }
                    ]
                }
            }
        },
        methods: {
            addRequirement() {
                this.$refs.form.validate(async (valid) => {
                    if (valid) {
                        this.dialogVisible = false
                        const ret = await this.$http.post('/demand', {
                            name: this.form.name,
                            phone: this.form.phone,
                            mail: this.form.mail,
                            demand: this.form.demand,
                            price: this.form.price,
                            cycle: this.form.cycle
                        })
                        if (ret.data.code != 200) return this.$message.error(ret.data.msg)
                        this.getUserDemand()
                        this.$message.success("发布成功！")
                    } else {
                        this.$message.error("发布失败！")
                    }
                })
            },
            async getUserDemand() {
                const ret = await this.$http.get('/demand')
                if (ret.data.code != 200) return this.$message.error(ret.data.msg)
                this.tableData = ret.data.data
            },
            fillNumber(num) {
                return num > 9 ? num : '0' + num
            },
            onDialogClose() {
                this.$refs.form.resetFields()
            },
        },
        computed:{
            dateFilter(){
                return (str)=>{
                    const date=new Date(str)
                    console.log(date)
                    const yy=date.getFullYear()
                    const mm=this.fillNumber(date.getMonth()+1)
                    const dd=this.fillNumber(date.getDate())
                    const hh=this.fillNumber(date.getHours())
                    const MM=this.fillNumber(date.getMinutes())
                    const ss=this.fillNumber(date.getSeconds())

                    return `${yy}-${mm}-${dd} ${hh}:${MM}:${ss}`
                }
            }
        }
    }
</script>
<style lang="less" scoped>

</style>