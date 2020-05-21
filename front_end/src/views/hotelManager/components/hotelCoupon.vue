<!-- created by glh  2020-05-21  -->
<template>
    <div>
        <div style="width: 100%; text-align: right; margin: 20px 0">
            <a-button type="primary" @click="addCoupon">
                <a-icon type="plus"/>添加优惠策略
            </a-button>
        </div>

        <a-table
                :columns="columns"
                :dataSource="couponList"
                bordered
        >
            <a-tag color="red" slot="couponName" slot-scope="text">
                {{text}}
            </a-tag>
        </a-table>
        <AddCoupon></AddCoupon>
    </div>
</template>

<script>
import { mapGetters, mapMutations, mapActions } from 'vuex'
import AddCoupon from './addCoupon'

const columns = [
    {
        title: '优惠类型',
        dataIndex: 'couponName',
        scopedSlots: {customRender: 'couponName'},
    },
    {
        title: '折扣',
        dataIndex: 'discount',
    },
    {
        title: '优惠简介',
        dataIndex: 'description',
    },
    {
        title: '优惠金额',
        dataIndex: 'discountMoney',
    },
    {
        title: '操作',
        dataIndex: 'action',
    },
];

export default {
    name: "hotelCoupon",
    data() {
        return {
            columns
        }
    },
    components: {
        AddCoupon,
    },
    computed: {
        ...mapGetters([
            'couponList',
        ])
    },
    methods: {
        ...mapMutations([
            'set_addCouponVisible',
        ]),
        ...mapActions([
            'getHotelCoupon'
        ]),
        addCoupon() {
            this.set_addCouponVisible(true)
        },
    }
}
</script>

<style scoped>

</style>
