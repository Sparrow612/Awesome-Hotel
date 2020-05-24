<!-- created by glh 2020-05-23 -->
<!-- 添加网站优惠券 -->
<template xmlns:a-form="http://www.w3.org/1999/html">
    <a-modal
            :visible="addSiteCouponVisible"
            title="添加优惠策略"
            cancelText="取消"
            okText="确定"
            @cancel="cancel"
            @ok="handleSubmit"
    >
        <a-form :form="form" style="margin-top: 30px">
            <a-form-item label="优惠券类型" v-bind="formItemLayout">
                <a-select
                        v-decorator="['type',{rules: [{required: true, message: '请选择类型'}]}]"
                        @change="changeType">
                    <a-select-option value="1">生日特惠</a-select-option>
                    <a-select-option value="2">多间特惠</a-select-option>
                    <a-select-option value="3">满减特惠</a-select-option>
                    <a-select-option value="4">限时特惠</a-select-option>
                </a-select>
            </a-form-item>
            <a-form-item label="券名" v-bind="formItemLayout">
                <a-input
                        placeholder="请填写券名"
                        v-decorator="['name',{rules: [{required: true, message: '请输入券名'}]}]"
                />
            </a-form-item>
            <a-form-item label="优惠简介" v-bind="formItemLayout">
                <a-input
                        type="textarea"
                        :rows="4"
                        placeholder="请填写优惠券简介"
                        v-decorator="['description',{rules: [{required: true, message: '请填写优惠券简介'}]}]"
                />
            </a-form-item>
            <a-form-item label="达标金额" v-bind="formItemLayout">
                <a-input
                        placeholder="请填写达标金额"
                        v-decorator="['targetMoney',{rules: [{required: true, message: '请填写达标金额'}]}]"
                />
            </a-form-item>
            <a-form-item label="优惠金额" v-bind="formItemLayout">
                <a-input
                        placeholder="请填写优惠金额"
                        v-decorator="['discountMoney',{rules: [{required: true, message: '请填写优惠金额'}]}]"
                />
            </a-form-item>
        </a-form>
    </a-modal>

</template>

<script>
    import { mapGetters, mapMutations, mapActions } from 'vuex'
    export default {
        name: "addSiteCoupon",
        data() {
            return {
                formItemLayout: {
                    labelCol: {
                        xs: { span: 12 },
                        sm: { span: 6 },
                    },
                    wrapperCol: {
                        xs: { span: 24 },
                        sm: { span: 16 },
                    },
                },
            }
        },
        computed: {
            ...mapGetters([
                'addSiteCouponVisible',
            ])
        },
        beforeCreate() {
            // 表单名默认为“form”
            this.form = this.$form.createForm(this, { name: 'addCouponModal'});
        },
        methods: {
            ...mapMutations([
                'set_addSiteCouponVisible'
            ]),
            cancel() {
                this.set_addSiteCouponVisible(false)
            },
            changeType(v){
                if( v === '3') {

                }else{
                    this.$message.warning('请实现')
                }
            },
            handleSubmit(e) {
                this.set_addSiteCouponVisible(false)
            },
        }
    }
</script>

<style scoped>

</style>