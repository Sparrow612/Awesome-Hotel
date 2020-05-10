<template>
    <div class="box">
        <a-form :form="form" style="margin-top: 50px">
            <a-form-item v-bind="formItemLayout" label="地址">
                <a-input
                        type="text" v-model="address" placeholder="请输入地址" class="searchInput"
                        v-decorator="['address', {rules: [{required: true, message: '请输入地址！'}]}]"
                />
            </a-form-item>
            <a-form-item v-bind="formItemLayout" label="商圈">
                <a-input
                        type="text" v-model="business_circle" placeholder="请输入商圈" class="searchInput"
                        v-decorator="['busscircle', {rules: [{required: true, message: '请输入商圈！'}]}]"
                />
            </a-form-item>
            <a-form-item v-bind="formItemLayout" label="关键词">
                <a-input
                        type="text" v-model="keyword" placeholder="请输入关键词进行搜索" class="searchInput"
                        v-decorator="['keyword' ,{rules: [{required: true, message: '请输入关键词！'}]}]"
                />
            </a-form-item>
            <a-form-item v-bind="formItemLayout">
                <a-button @click="search" value="搜索" class="searchButton">
                    <a-icon type="search"/>
                </a-button>
            </a-form-item>
        </a-form>
    </div>
</template>

<script>
    export default {
        name: "searchHotel",
        data() {
            return {
                formItemLayout: {
                    labelCol: {
                        xs: { span: 10 },
                        sm: { span: 5 },
                    },
                    wrapperCol: {
                        xs: { span: 20 },
                        sm: { span: 10 },
                    },
                },
                address: '',
                business_circle: '',
                keyword: '',
            }
        },
        beforeCreate() {
            this.form = this.$form.createForm(this, { name : 'searchTable'});
        },
        methods: {
            search(e){
                e.preventDefault();
                this.form.validateFieldsAndScroll((err, values) => {
                    if (!err) {
                        const data = {

                        }
                    }
                });
                // this.$message.loading("Searching", , );
                this.$message.success("搜寻成功");
            }
        }
    }
</script>

<style scoped lang="less">
    .box{
        width: 50%;
    }
    .searchButton{
        display: block;
        width: 50%;
        float: right;
        background-color: #00a0e9;
        cursor: pointer;
        color: #fff;
    }
</style>