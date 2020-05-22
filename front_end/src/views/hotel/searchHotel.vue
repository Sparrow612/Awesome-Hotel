<template>
    <div class="box">
        <div class="searchtab">
            <div class="searchbox">
                <a-form :form="form">
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
                        <a-button type="primary" icon="search" size="small" @click="search" value="搜索" class="searchButton">
                            搜索酒店
                        </a-button>
                    </a-form-item>
                </a-form>
            </div>
            <div class="presentation">
                <img class="hotel_img"
                    alt="example"
                    src="@/assets/cover.jpeg"
                    slot="cover"
                    referrerPolicy="no-referrer"
                />
                <h1>这里存放一个表格</h1>
            </div>
        </div>
        <div class="searchresult">
            <h1>这里用来展示搜索结果</h1>
            <p>计划在这里放一个横向的滚动列表</p>
        </div>
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
        display: block;
    }
    .searchtab{
        width: 100%;
        display: flex;
        margin: 10px;
    }
    .searchbox{
        margin-top: 80px;
        width: 50%;
    }
    .searchButton{
        display: block;
        width: 40%;
        text-align: center;
        float: right;
    }
    .presentation{
        display: flex;
    }
    .hotel_img{
        margin: 20px;
        width: 300px;
        height: 400px;
    }
    .searchresult{
        width: 100%;
        margin: 10px;
    }
</style>