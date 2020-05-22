<template>
    <div style="margin-top: 50px;">
        <div style="float: left; margin-left: 5%">
            <membership-coupon ></membership-coupon>
        </div>
        <div style="float: left; margin-left: 5%; text-align: center; width: 270px">
            <a-card style="width: 270px;">
                <p>VIP会员特权</p>
                <img
                    alt="example"
                    src="@/assets/membershipLogo.svg"
                    style="width: 20px; height: 20px"
                />
                <span> 尊贵标识</span>
                <a-divider type="vertical"></a-divider>
                <img
                    alt="example"
                    src="@/assets/couponLogo.svg"
                    style="width: 20px; height: 20px"
                />
                <span> 丰富优惠</span>
            </a-card>
            <a-button type="primary" @click="registerMembership" style="margin-top: 20px"><a-icon type="user" />注册会员</a-button>
            <register-membership></register-membership>
        </div>

    </div>
</template>

<script>
import membershipCoupon from "./components/membershipCoupon";
import registerMembership from "./components/registerMembership";
import { mapGetters, mapMutations, mapActions } from 'vuex'
export default {
    name: "usermembership",
    components: {
        membershipCoupon,
        registerMembership,
    },
    computed: {
        ...mapGetters([
            'registerMembershipModalVisible'
        ])
    },
    methods: {
        ...mapMutations([
            'set_registerMembershipModalVisible'
        ]),
        registerMembership() {
            this.set_registerMembershipModalVisible(true);
        }
    }

}
</script>

<style scoped>

</style>