<template>
    <div class="box">
        <div class="searchtab">
            <div class="searchbox">
                <a-form :form="form">
                    <a-form-item v-bind="formItemLayout" label="时间">
                        <a-range-picker
                                format="YYYY-MM-DD"
                                @change="changeDate"
                                v-decorator="['date', { rules: [{ required: true, message: '请选择入住时间' }]}]"
                                :placeholder="['入住日期','退房日期']"
                        />
                    </a-form-item>
                    <a-form-item v-bind="formItemLayout" label="地址">
                        <a-input
                                type="text" v-model="address" placeholder="请输入地址" class="searchInput"
                        />
                    </a-form-item>
                    <a-form-item v-bind="formItemLayout" label="商圈">
                        <a-input
                                type="text" v-model="business_circle" placeholder="请输入商圈" class="searchInput"
                        />
                    </a-form-item>
                    <a-form-item v-bind="formItemLayout" label="关键词">
                        <a-input
                                type="text" v-model="keyword" placeholder="请输入关键词进行搜索" class="searchInput"
                        />
                    </a-form-item>
                    <a-form-item v-bind="formItemLayout" label="价位上限 ¥">
                        <a-row>
                            <a-col :span="16">
                                <a-slider
                                        :min="1" :max="2000" v-model="value" @change="handleValueChange"
                                />
                            </a-col>
                            <a-col :span="4">
                                <a-input-number
                                        :min="1" :max="2000" v-model="value" style="margin-left: 16px"
                                />
                            </a-col>
                        </a-row>
                    </a-form-item>
                    <a-form-item v-bind="formItemLayout" label="评分下限">
                        <a-row>
                            <a-col :span="16">
                                <a-slider
                                        :min="0" :max="5" :step="0.1" v-model="rate" @change="handleRateChange"
                                />
                            </a-col>
                            <a-col :span="4">
                                <a-input-number
                                        :min="0" :max="5" :step="0.1" v-model="rate" style="margin-left: 16px"
                                />
                            </a-col>
                        </a-row>
                    </a-form-item>
                    <a-form-item v-bind="formItemLayout" label="酒店星级">
                        <a-checkbox :indeterminate="indeterminate" :checked="checkAll" @change="onCheckAllChange">
                            全选
                        </a-checkbox>
                        <a-checkbox-group v-model="checkList" :options="hotelStarOptions" @change="onChange" />
                    </a-form-item>
                    <a-form-item v-bind="formItemLayout">
                        <a-button type="primary" icon="search" @click="search" value="搜索"
                                  class="searchButton">
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
    const hotelStarOptions = ['三星级', '四星级', '五星级']
    const defaultCheckedList = ['三星级', '四星级', '五星级']
    export default {
        name: "searchHotel",
        data() {
            return {
                formItemLayout: {
                    labelCol: {
                        xs: {span: 10},
                        sm: {span: 5},
                    },
                    wrapperCol: {
                        xs: {span: 20},
                        sm: {span: 10},
                    },
                },
                address: '',
                business_circle: '',
                keyword: '',
                value: 1000,
                rate: 3.0,
                checkAll: true,
                indeterminate: false,
                hotelStarOptions,
                checkList: defaultCheckedList,
            }
        },
        beforeCreate() {
            this.form = this.$form.createForm(this, {name: 'searchTable'});
        },
        methods: {
            handleValueChange(value) {
                this.value = value
            },
            handleRateChange(rate) {
                this.rate = rate
            },
            onChange(checkedList) {
                this.indeterminate = !!checkedList.length && checkedList.length < hotelStarOptions.length;
                this.checkAll = checkedList.length === hotelStarOptions.length;
                console.log(checkedList)
            },
            onCheckAllChange(e) {
                Object.assign(this, {
                    checkedList: e.target.checked ? hotelStarOptions : [],
                    indeterminate: false,
                    checkAll: e.target.checked,
                });
            },
            search(e) {
                e.preventDefault();
                this.form.validateFieldsAndScroll((err, values) => {
                    if (!err) {
                        const data = {}
                    }
                });
                // this.$message.loading("Searching", , );
                this.$message.success("搜寻成功");
            },
        }
    }
</script>

<style scoped lang="less">
    .box {
        display: block;
    }

    .searchtab {
        width: 100%;
        display: flex;
        margin: 10px;
    }

    .searchbox {
        margin-top: 80px;
        width: 50%;
    }

    .searchButton {
        width: 120px;
        text-align: center;
        float: right;
    }

    .presentation {
        display: flex;
    }

    .hotel_img {
        margin: 20px;
        width: 400px;
        height: 600px;
    }

    .searchresult {
        width: 100%;
        margin: 10px;
    }
</style>