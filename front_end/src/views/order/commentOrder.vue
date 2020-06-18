<template>
    <a-modal
            title="发布评论"
            :visible="commentOrderModalVisible"
            @ok="handleOk"
            @cancel="handleCancel"
    >
        <a-form :form="form">

            <a-form-item label="酒店名称" v-bind="formItemLayout">
                <span>{{ orderInfo.hotelName }}</span>
            </a-form-item>
            <a-form-item label="总体评分" v-bind="formItemLayout">
                <span v-if="!hasCommented">
                    <a-rate v-decorator="['points', { rules: [{ required: true, message: '请为酒店打分' }] }]" />
                </span>
                <span v-else>
                    <a-rate :default-value="orderInfo.points" disabled />
                </span>

            </a-form-item>
            <a-form-item label="卫生条件" v-bind="formItemLayout">
                <span v-if="!hasCommented">
                    <a-rate v-decorator="['sanitation', { rules: [{ required: true, message: '请为酒店卫生条件打分'}] }]" />
                </span>
                <span v-else>
                    <a-rate :default-value="orderInfo.sanitation" disabled />
                </span>
            </a-form-item>
            <a-form-item label="环境" v-bind="formItemLayout">
                <span v-if="!hasCommented">
                    <a-rate v-decorator="['environment', { rules: [{ required: true, message: '请为酒店环境打分'}] }]" />
                </span>
                <span v-else>
                    <a-rate :default-value="orderInfo.environment" disabled />
                </span>
            </a-form-item>
            <a-form-item label="服务" v-bind="formItemLayout">
                <span v-if="!hasCommented">
                    <a-rate v-decorator="['service', { rules: [{ required: true, message: '请为酒店服务打分'}] }]" />
                </span>
                <span v-else>
                    <a-rate :default-value="orderInfo.environment" disabled />
                </span>
            </a-form-item>
            <a-form-item label="设备" v-bind="formItemLayout">
                <span v-if="!hasCommented">
                    <a-rate v-decorator="['equipment', { rules: [{ required: true, message: '请为酒店设备打分'}] }]" />
                </span>
                <span v-else>
                    <a-rate :default-value="orderInfo.equipment" disabled />
                </span>
            </a-form-item>
            <a-form-item label="您的评价" v-bind="formItemLayout">
                <a-textarea v-if="!hasCommented"
                    :auto-size="{ minRows: 3, maxRows: 5 }"
                    v-decorator="['comment', { rules: [{ required: true, message: '请为酒店设备打分'}] }]"
                />
                <span v-else>
                    {{ orderInfo.comment }}
                </span>
            </a-form-item>

        </a-form>
    </a-modal>
</template>

<script>
    import { mapGetters, mapActions, mapMutations } from 'vuex'
    export default {
        name: "CommentOrder",
        data() {
            return {
                formItemLayout: {
                    labelCol: {
                        xs: {span: 12},
                        sm: {span: 6},
                    },
                    wrapperCol: {
                        xs: {span: 24},
                        sm: {span: 16},
                    },
                },
                form: this.$form.createForm(this, {name: 'coordinated'}),
            }
        },
        computed: {
            ...mapGetters([
                'commentOrderModalVisible',
                'orderInfo',
            ]),
            hasCommented() {
                return this.orderInfo.points !== null
            }
        },
        methods: {
            ...mapMutations([
                'set_commentOrderModalVisible',
            ]),
            ...mapActions([
                'addComment'
            ]),
            handleOk() {
                this.form.validateFields((err, values) => {
                    if(!err){
                        const data = {
                            userId: this.orderInfo.userId,
                            orderId: this.orderInfo.id,
                            points: this.form.getFieldValue('points'),
                            sanitation: this.form.getFieldValue('sanitation'),
                            environment: this.form.getFieldValue('environment'),
                            service: this.form.getFieldValue('service'),
                            equipment: this.form.getFieldValue('equipment'),
                            comment: this.form.getFieldValue('comment')
                        }
                        console.log(data)
                        this.addComment(data).then(() => {
                            this.form.setFieldsValue({
                                'points': '',
                                'sanitation': '',
                                'environment': '',
                                'service': '',
                                'equipment': '',
                                'comment': '',
                            })
                        })
                    }
                })
            },
            handleCancel() {
                this.form.setFieldsValue({
                    'points': '',
                    'sanitation': '',
                    'environment': '',
                    'service': '',
                    'equipment': '',
                    'comment': '',
                })
                this.set_commentOrderModalVisible(false)
            },
        }

    }
</script>

<style scoped>

</style>
