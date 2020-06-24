<template>
    <a-modal
            :visible="registerCorporationMembershipModalVisible"
            title="注册企业会员"
            cancelText="取消"
            okText="注册"
            @cancel="cancelRegister"
            @ok="confirmRegister"
            width="450px"
    >
        <a-form :form="form">
            <a-form-item v-bind="formItemLayout" label="您的企业">
                <a-input
                        placeholder="请输入企业名"
                        v-decorator="[
                            'corporation',
                            { rules: [{ required: true, message: '请输入企业' }], initialValue: this.userInfo.corporation},
                        ]"
                />
            </a-form-item>
        </a-form>
    </a-modal>
</template>

<script>
    import {mapGetters, mapMutations, mapActions} from 'vuex'
    export default {
        name: "registerCorporationMembership",
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
                form: this.$form.createForm(this, { name: 'registerCorporationMembershipModal' }),
            }
        },
        computed: {
            ...mapGetters([
                'registerCorporationMembershipModalVisible',
                'userInfo'
            ])
        },
        methods: {
            ...mapMutations([
                'set_registerCorporationMembershipModalVisible'
            ]),
            ...mapActions([
                'registerCorporationMembership',
            ]),
            cancelRegister() {
                this.set_registerCorporationMembershipModalVisible(false);
            },
            confirmRegister(e) {
                e.preventDefault();
                this.form.validateFields((err, values) => {
                    if (!err) {
                        let corpName = String(this.form.getFieldValue('corporation'))
                        this.registerCorporationMembership(corpName).then(() => {
                            this.set_registerCorporationMembershipModalVisible(false);
                        })
                        this.form.setFieldsValue({
                            corporation: ''
                        })
                    }
                });
            }
        }
    }
</script>

<style scoped>

</style>
