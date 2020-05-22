<!-- created by glh  2020-05-21  -->
<template>
    <div>
        <h1>TODO: 要先获取当前酒店管理员对应的酒店</h1>
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

            <span slot="action" slot-scope="record">
                <a-popconfirm
                        title="确定删除该优惠券吗？"
                        @confirm="deleteCoupon(record)"
                        okText="确定"
                        cancelText="取消"
                >
                    <a-button type="danger" size="small">删除</a-button>
                </a-popconfirm>
                <a-divider type="vertical"></a-divider>
                <a-button type="primary" size="small">修改</a-button>
            </span>
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
        scopedSlots: {customRender: 'action'}
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
            'getHotelCoupon',
            'getHotelByManagerId'
        ]),
        addCoupon() {
            this.set_addCouponVisible(true)
        },
    }
}
</script>

<style scoped>

</style>
