<template>
    <a-carousel autoplay>
        <div class="privilege">
            <h2>网站会员</h2>
            <h3 v-for="item in this.levels" :key="item.level">
                LV{{ item.level }}: 累计消费￥{{ item.request }} (减免{{ item.reduction * 100 }}%) <br/>
            </h3>
        </div>

        <div class="siteCoupon" v-for="coupon in ClientMembershipCouponList" :key="coupon.id">
            <div v-if="coupon.couponType === 1">
                <h2>网站会员节日特惠</h2>
                <h3>{{ coupon.startTime }} ~ {{ coupon.endTime }}</h3>
                <h3>折扣{{ coupon.discount * 100 }}%</h3>
            </div>

            <div v-if="coupon.couponType === 2">
                <h2>网站会员商圈特惠</h2>
                <h3>{{ coupon.bizRegion }}</h3>
                <h3>会员等级{{ coupon.vipLevel }}&nbsp;可享受折扣{{ coupon.discount * 100 }}%</h3>
            </div>
        </div>
    </a-carousel>
</template>

<script>
    import { mapGetters, mapMutations, mapActions } from 'vuex'
    export default {
        name: "ClientMembershipCoupon",
        data() {
            return {

            }
        },
        computed: {
            ...mapGetters([
                'levelConsumption',
                'levels',
                'corpLevels',
                'siteCouponList',
            ]),
            ClientMembershipCouponList() {
                return this.siteCouponList.filter(function(x) {
                    return x.couponType !== 3
                })
            }
        },
        async mounted() {
            await this.getClientLevel()
            await this.getCorpLevel()
            await this.getSiteCoupon()
        },
        methods: {
            ...mapActions([
                'getClientLevel',
                'getCorpLevel',
                'getSiteCoupon',
            ]),
        },
    }
</script>

<style scoped>

    .ant-carousel {
        width: 800px;
    }

    .ant-carousel >>> .slick-slide {
        text-align: center;
        height: 600px;
        background-image: url('../../../assets/background.png');
        overflow: hidden;
    }

    .ant-carousel >>> .slick-slide h3 {
        color: #fff;
    }

    .privilege {
        margin-top: 180px;
        font-size: large;
    }

    .siteCoupon {
        margin-top: 230px;
        font-size: large;
    }

    h2 {
        color: #ffffff;
    }
</style>
