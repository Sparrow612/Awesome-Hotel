<template>
    <a-modal
            :visible="registerMembershipModalVisible"
            title="注册会员"
            cancelText="取消"
            okText="注册"
            @cancel="cancelRegister"
            @ok="confirmRegister"
            width="450px"
    >
        <a-form :form="form">
            <a-form-item v-bind="formItemLayout" label="您的生日">
                <a-date-picker @change="onChange"/>
            </a-form-item>
        </a-form>

    </a-modal>
</template>

<script>
import { mapGetters, mapMutations, mapActions } from 'vuex'
export default {
    name: "registerMembership",
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
            'registerMembershipModalVisible'
        ])
    },
    beforeCreate() {
        // 表单名默认为“form”
        this.form = this.$form.createForm(this, { name: 'registerMembershipModal'});
    },
    methods: {
        ...mapMutations([
            'set_registerMembershipModalVisible'
        ]),
        cancelRegister() {
            this.set_registerMembershipModalVisible(false);
        },
        confirmRegister() {
            // TODO 注册会员的逻辑
            alert("register membership!")
            this.set_registerMembershipModalVisible(false);
        }
    }
}
</script>

<style scoped>

</style>