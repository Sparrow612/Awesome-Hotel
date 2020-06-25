<template>
    <div class="info-wrapper">
        <a-form :form="form" style="padding: 10px; border-radius: 20px; background-color: whitesmoke">
            <a-form-item label="头像" v-bind="formItemLayout">
                <a-avatar src="./defaultAvatar.png"></a-avatar>
                <!--                        <a-button type="primary" icon="upload" style="margin-left: 20px">上传头像</a-button>-->
            </a-form-item>

            <a-form-item label="用户名" v-bind="formItemLayout">
                <a-input
                        v-decorator="['userName', { rules: [{ required: false, message: '请输入用户名' }], initialValue: userInfo.userName}]"
                        v-if="modify"
                />
                <span v-else>{{ userInfo.userName }}</span>
            </a-form-item>

            <a-form-item label="邮箱" v-bind="formItemLayout">
                <span>{{ userInfo.email }}</span>
            </a-form-item>

            <a-form-item label="手机号" v-bind="formItemLayout">
                <a-input
                        v-decorator="['phoneNumber', { rules: [{ required: false, message: '请输入手机号' },
                        { validator: this.handlePhoneNumber }], validateTrigger: 'blur' , initialValue: userInfo.phoneNumber}]"
                        v-if="modify"
                />
                <span v-else>{{ userInfo.phoneNumber}}</span>
            </a-form-item>

            <a-form-item label="VIP等级" v-bind="formItemLayout"
                 v-if="this.userInfo.userType==='Client' && this.userInfo.vipType ==='Client'">
                <div v-if="this.userVIP.status === 1">
                    <span>
                    <span :key="index" v-for="index of 5">
                        <img
                                alt="example"
                                src="@/assets/star.svg"
                                style="width: 20px; height: 20px"
                                v-if="index <= userVIP.level"
                        />
                        <img
                                alt="example"
                                src="@/assets/starGray.svg"
                                style="width: 20px; height: 20px"
                                v-else
                        />
                    </span>
                </span>
                </div>

            </a-form-item>
            <a-form-item label="VIP" v-bind="formItemLayout"
                 v-else-if="this.userInfo.userType==='Client' && JSON.stringify(this.userVIP) === '{}'">
                <span>
                    尚未成为会员，请前往会员中心注册
                </span>
            </a-form-item>
            <a-form-item label="VIP等级" v-bind="formItemLayout" v-else-if="this.userInfo.userType==='Client'">
                <a-tag color="red">您已被冻结</a-tag>
            </a-form-item>

            <a-form-item label="所属企业" v-bind="formItemLayout" v-if="this.userInfo.userType==='Client'">
                <a-input
                        v-decorator="['corporation', {initialValue: userInfo.corporation}]"
                        v-if="modify"
                />
                <span v-else>{{ userInfo.corporation }}&nbsp;
                    <span v-if="isCorpVIP">
                        <span v-if="corpVIP.status === 1"><a-tag color="blue">已成为企业会员</a-tag></span>
                        <span v-else><a-tag color="red">被冻结</a-tag></span>
                    </span>
                    <span v-else><a-tag color="cyan">尚未成为企业会员</a-tag></span>
                </span>
            </a-form-item>

            <a-form-item label="信用值" v-bind="formItemLayout" v-if="this.userInfo.userType==='Client'">
                <span>{{ userInfo.credit }}</span>
            </a-form-item>

            <a-form-item label="新密码" v-bind="formItemLayout" v-if="modify">
                <a-input
                        placeholder="请输入新密码"
                        type="password"
                        v-decorator="['password', { rules: [{ required: false, message: '请输入新密码' },
                                { validator: this.handlePassword }], validateTrigger: 'blur' }]"
                        v-if="modify"
                />
            </a-form-item>

            <a-form-item label="确认密码" v-bind="formItemLayout"
                         v-if="modify && this.form.getFieldValue('password')">
                <a-input
                        placeholder="请再次输入密码"
                        type="password"
                        v-decorator="['passwordConfirm',
                                {rules: [{ required: true, message: '请输入确认密码' }, { validator: this.handlePasswordCheck }],
                                validateTrigger: 'blur', initialValue: ''}]">
                    v-if="modify"
                </a-input>
            </a-form-item>

            <a-form-item :wrapper-col="{ span: 12, offset: 5 }" v-if="modify">
                <a-button @click="saveModify" icon="upload" style="border-radius: 20px" type="primary">
                    保存
                </a-button>
                <a-button @click="cancelModify" icon="close-circle"
                          style="margin-left: 30px; border-radius: 20px" type="default">
                    取消
                </a-button>
            </a-form-item>
            <a-form-item :wrapper-col="{ span: 8, offset: 4 }" v-else>
                <a-button @click="modifyInfo" icon="form" style="border-radius: 20px" type="primary">
                    修改信息
                </a-button>
            </a-form-item>

        </a-form>
    </div>
</template>

<script>
    import {mapActions, mapGetters, mapMutations} from "vuex";
    import {message} from "ant-design-vue";

    export default {
        name: "infoForm",
        data() {
            return {
                modify: false,
                formLayout: 'horizontal',
                formItemLayout: {
                    labelCol: {
                        span: 3
                    },
                    wrapperCol: {
                        span: 8,
                        offset: 1,
                    },
                },
            }
        },
        computed: {
            ...mapGetters([
                'userInfo',
                'userVIP',
                'isCorpVIP',
                'corpVIP',
            ])
        },
        beforeCreate() {
            this.form = this.$form.createForm(this, {name: 'userInfo'})
        },
        async mounted() {
            await this.getUserInfo()
            await this.getUserVIP(this.userInfo.id)
            console.log(this.userVIP)
            await this.corpVIPCheck(this.userInfo.corporation)
            if (this.isCorpVIP) {
                this.getCorpVIP(this.userInfo.corporation)
            }
        },
        methods: {
            ...mapActions([
                'updateUserInfo',
                'corpVIPCheck',
                'getCorpVIP',
                'getUserInfo',
                'getUserVIP',
            ]),
            ...mapMutations([

            ]),

            saveModify() {
                this.form.validateFields((err, values) => {
                    if (!err) {
                        const data = {
                            userName: this.form.getFieldValue('userName'),
                            phoneNumber: this.form.getFieldValue('phoneNumber'),
                            corporation: this.form.getFieldValue('corporation'),
                            password: this.form.getFieldValue('password'),
                        }
                        this.updateUserInfo(data)
                        this.modify = false
                    } else {
                        message.error("请输入正确的信息")
                    }
                })
            },

            modifyInfo() {
                this.modify = true
            },

            cancelModify() {
                message.info('取消修改')
                this.modify = false
            },

            goToMembership() {
                // TODO 修改header上面的current
                // 不行就砍
                // 不用链接，直接用文字代替
            },

            handlePhoneNumber(rule, value, callback) {
                if (value) {
                    const re = /1\d{10}/;
                    if (re.test(value)) {
                        callback();
                    } else {
                        callback(new Error('请输入有效手机号'));
                    }
                }
                callback()
            },

            handlePassword(rule, value, callback) {
                if (value) {
                    if (value.length < 6) {
                        callback(new Error('密码长度至少6位'))
                    }
                }
                callback()
            },

            handlePasswordCheck(rule, value, callback) {
                const password = this.form.getFieldValue('password')
                if (value === undefined) {
                    callback(new Error('请输入密码'))
                }
                if (value && password && value.trim() !== password.trim()) {
                    callback(new Error('两次密码不一致'))
                }
                callback()
            },
        }
    }
</script>

<style scoped>
    .info-wrapper {
        padding: 10px;
    }
</style>
