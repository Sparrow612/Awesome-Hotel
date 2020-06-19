<!-- created by glh 2020-05-22 -->
<!-- 网站营销人员的优惠券界面 -->
<template>
    <div class="info-wrapper">
        <div style="width: 100%; text-align: right; margin: 20px 0">
            <a-button type="primary" @click="addSiteCoupon">
                <a-icon type="plus"/>添加优惠策略
            </a-button>
        </div>

        <a-table
                :columns="columns"
                :dataSource="siteCouponList"
                :locale="{emptyText: '暂时没有网站优惠'}"
                bordered
        >
            <a-tag color="purple" slot="couponName" slot-scope="text">{{text}}</a-tag>
            <a-tag color="red" slot="discount" slot-scope="text">{{text*100}}%</a-tag>
            <a-tag color="blue" slot="vipLevel" slot-scope="text">{{text}}</a-tag>
        </a-table>
        <addSiteCoupon></addSiteCoupon>
    </div>
</template>

<script>
    import { mapGetters, mapMutations, mapActions } from 'vuex'
    import addSiteCoupon from "./components/addSiteCoupon";
    const columns = [
        {
            title: '优惠名称',
            dataIndex: 'couponName',
            scopedSlots: {customRender: 'couponName'},
        },
        {
            title: '折扣',
            dataIndex: 'discount',
            scopedSlots: {customRender: 'discount'},
        },
        {
            title: '优惠简介',
            dataIndex: 'description',
        },
        {
            title: '受用VIP等级（VIP指定商圈优惠）',
            dataIndex: 'vipLevel',
            scopedSlots: {customRender: 'vipLevel'},
        },
    ];
    export default {
        name: "siteCoupon",
        data() {
            return {
                columns,
            }
        },
        components: {
            addSiteCoupon,
        },
        computed: {
            ...mapGetters([
                'addSiteCouponVisible',
                'siteCouponList',
            ])
        },
        mounted() {
            this.getSiteCoupon()
        },
        methods: {
            ...mapMutations([
                'set_addSiteCouponVisible',
            ]),
            ...mapActions([
                'getSiteCoupon',
            ]),
            addSiteCoupon() {
                this.set_addSiteCouponVisible(true)
            },


        }
    }
</script>

<style scoped>

</style>
<style scoped>
    .info-wrapper {
        padding: 50px;
    .chart {
        display: flex;
        align-items: center;
        justify-content: space-between;
        margin-top: 20px
    }
    }
</style>