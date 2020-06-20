<template>
    <a-carousel autoplay>
        <div class="privilege">
            <h2>网站会员</h2>
            <h3 v-for="item in this.levels" :key="item.level">
                LV{{ item.level }}: 累计消费{{ item.request }}￥ 减免{{ item.reduction * 100 }}% <br/>
            </h3>
        </div>
        <div class="privilege">
            <h2>企业会员</h2>
            <h3 v-for="item in this.corpLevels" :key="item.level">
                LV{{ item.level }}: 累计消费{{ item.request }}￥ 减免{{ item.reduction * 100 }}% <br/>
            </h3>
        </div>
        <!--  这里可以再补充优惠券的部分，暂时还没想好怎么展示   -->
    </a-carousel>
</template>

<script>
    import { mapGetters, mapMutations, mapActions } from 'vuex'
    export default {
        name: "membershipCoupon",
        data() {
            return {

            }
        },
        computed: {
            ...mapGetters([
                'levelConsumption',
                'levels',
                'corpLevels',
            ]),
        },
        async mounted() {
            await this.getClientLevel()
            await this.getCorpLevel()
        },
        methods: {
            ...mapActions([
                'getClientLevel',
                'getCorpLevel',
            ]),
        },
    }
</script>

<style scoped>
    /* For demo */
    .ant-carousel {
        width: 600px;
    }

    .ant-carousel >>> .slick-slide {
        text-align: center;
        height: 340px;

        background: #83c2f8;
        overflow: hidden;
    }

    .ant-carousel >>> .slick-slide h3 {
        color: #fff;
    }

    .privilege {
        margin-top: 80px;
    }

    h2 {
        color: #ffffff;
    }
</style>
