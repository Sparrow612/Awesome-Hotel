<!-- created by glh 2020-05-23 -->
<!-- 网站营销人员用来管理订单情况，包括浏览所有订单和处理异常订单 -->
<template>
    <div>
        <div class="info-wrapper">
            <a-tabs>
                <a-tab-pane tab="所有订单" key="1">
                    <a-table
                            :columns="columns_of_orders"
                            :dataSource="allOrderList"
                            :rowKey="record => record.id"
                            bordered
                    >
                        <a-tag slot="hotelName" color="orange" slot-scope="text">
                            {{text}}
                        </a-tag>
                        <span slot="roomType" slot-scope="text">
                        <a-tag color="green" v-if="text === 'BigBed'">大床房</a-tag>
                        <a-tag color="green" v-if="text === 'DoubleBed'">双床房</a-tag>
                        <a-tag color="green" v-if="text === 'Family'">家庭房</a-tag>
                    </span>
                        <a-tag slot="checkInDate" color="red" slot-scope="text">
                            {{text}}
                        </a-tag>
                        <a-tag slot="checkOutDate" color="red" slot-scope="text">
                            {{text}}
                        </a-tag>
                        <span slot="price" slot-scope="text">
                        <span>￥ {{ text }}</span>
                    </span>
                        <a-tag slot="orderState" color="blue" slot-scope="text">
                            {{ text }}
                        </a-tag>
                        <span slot="action" slot-scope="record">
                        <a-button type="primary" size="small" @click="showOrderDetail(record)">查看详情</a-button>
                    </span>
                    </a-table>
                </a-tab-pane>

                <a-tab-pane key="2">
                    <span slot="tab">
                        今日未执行
                        <sup style="color: red">{{ todayUnExecutedOrders.length }}</sup>
                    </span>
                    <a-table
                            :columns="columns_of_orders_nofilter"
                            :dataSource="todayUnExecutedOrders"
                            :rowKey="record => record.id"
                            bordered
                    >
                        <a-tag slot="hotelName" color="orange" slot-scope="text">
                            {{text}}
                        </a-tag>
                        <span slot="roomType" slot-scope="text">
                        <a-tag color="green" v-if="text === 'BigBed'">大床房</a-tag>
                        <a-tag color="green" v-if="text === 'DoubleBed'">双床房</a-tag>
                        <a-tag color="green" v-if="text === 'Family'">家庭房</a-tag>
                    </span>
                        <a-tag slot="checkInDate" color="red" slot-scope="text">
                            {{text}}
                        </a-tag>
                        <a-tag slot="checkOutDate" color="red" slot-scope="text">
                            {{text}}
                        </a-tag>
                        <span slot="price" slot-scope="text">
                        <span>￥ {{ text }}</span>
                    </span>
                        <a-tag slot="orderState" color="blue" slot-scope="text">
                            {{ text }}
                        </a-tag>
                        <span slot="action" slot-scope="record">
                        <a-button type="primary" size="small" @click="showOrderDetail(record)">查看详情</a-button>
                    </span>
                    </a-table>
                </a-tab-pane>

                <a-tab-pane key="3">
                    <span slot="tab">
                        异常订单
                        <sup style="color: red">{{ allAbnormalOrders.length }}</sup>
                    </span>
                    <a-table
                            :columns="columns_of_orders_nofilter"
                            :dataSource="allAbnormalOrders"
                            :rowKey="record => record.id"
                            bordered
                    >
                        <a-tag slot="hotelName" color="orange" slot-scope="text">
                            {{text}}
                        </a-tag>
                        <span slot="roomType" slot-scope="text">
                        <a-tag color="green" v-if="text === 'BigBed'">大床房</a-tag>
                        <a-tag color="green" v-if="text === 'DoubleBed'">双床房</a-tag>
                        <a-tag color="green" v-if="text === 'Family'">家庭房</a-tag>
                    </span>
                        <a-tag slot="checkInDate" color="red" slot-scope="text">
                            {{text}}
                        </a-tag>
                        <a-tag slot="checkOutDate" color="red" slot-scope="text">
                            {{text}}
                        </a-tag>
                        <span slot="price" slot-scope="text">
                        <span>￥ {{ text }}</span>
                    </span>
                        <a-tag slot="orderState" color="blue" slot-scope="text">
                            {{ text }}
                        </a-tag>
                        <span slot="action" slot-scope="record">
                        <a-button type="danger" size="small" @click="handleAbnormalOrder(record)">处理异常</a-button>
                    </span>
                    </a-table>
                </a-tab-pane>


            </a-tabs>

        </div>
        <orderDetail></orderDetail>
        <handleAbnormalOrder></handleAbnormalOrder>
    </div>
</template>

<script>
import orderDetail from "../order/orderDetail";
import handleAbnormalOrder from "./components/handleAbnormalOrder";
import { mapGetters, mapMutations, mapActions } from 'vuex'
import {message} from "ant-design-vue";
const columns_of_orders = [
    {
        title: '订单号',
        dataIndex: 'id',
    },
    {
        title: '酒店名',
        dataIndex: 'hotelName',
        scopedSlots: { customRender: 'hotelName' },
    },
    {
        title: '房型',
        dataIndex: 'roomType',
        scopedSlots: { customRender: 'roomType' }
    },
    {
        title: '入住时间',
        dataIndex: 'checkInDate',
        scopedSlots: { customRender: 'checkInDate' }
    },
    {
        title: '离店时间',
        dataIndex: 'checkOutDate',
        scopedSlots: { customRender: 'checkOutDate' }
    },
    {
        title: '入住人数',
        dataIndex: 'peopleNum',
    },
    {
        title: '订单价格',
        dataIndex: 'price',
        scopedSlots: { customRender: 'price' }
    },
    {
        title: '状态',
        filters: [{text: '未入住', value: '未入住'}, {text: '已撤销', value: '已撤销'}, {text: '已入住', value: '已入住'},
            {text: '已完成', value: '已完成'}, {text: '异常订单', value: '异常订单'}],
        onFilter: (value, record) => record.orderState.includes(value),
        filterMultiple: false,
        dataIndex: 'orderState',scopedSlots: { customRender: 'orderState' },
    },
    {
        title: '操作',
        key: 'action',
        scopedSlots: { customRender: 'action' },
    },

];
const columns_of_orders_nofilter = [
    {
        title: '订单号',
        dataIndex: 'id',
    },
    {
        title: '酒店名',
        dataIndex: 'hotelName',
        scopedSlots: { customRender: 'hotelName' }
    },
    {
        title: '房型',
        dataIndex: 'roomType',
        scopedSlots: { customRender: 'roomType' }
    },
    {
        title: '入住时间',
        dataIndex: 'checkInDate',
        scopedSlots: { customRender: 'checkInDate' }
    },
    {
        title: '离店时间',
        dataIndex: 'checkOutDate',
        scopedSlots: { customRender: 'checkOutDate' }
    },
    {
        title: '入住人数',
        dataIndex: 'peopleNum',
    },
    {
        title: '订单价格',
        dataIndex: 'price',
        scopedSlots: { customRender: 'price' }
    },
    {
        title: '状态',
        dataIndex: 'orderState',
        scopedSlots: { customRender: 'orderState' },
    },
    {
        title: '操作',
        key: 'action',
        scopedSlots: { customRender: 'action' },
    },

];
export default {
    name: "manageOrders",
    data() {
        return {
            columns_of_orders,
            columns_of_orders_nofilter,
            filterOptions: [],
        }
    },
    components: {
        orderDetail,
        handleAbnormalOrder,
    },
    computed: {
        ...mapGetters([
            'orderDetailVisible',
            'allOrderList',
        ]),
        allAbnormalOrders () {
            return this.allOrderList.filter(function(x) {
                return x.orderState === '异常订单'
            })
        },
        todayUnExecutedOrders () {
            return this.allOrderList.filter(function(x) {
                let checkInDate = new Date(x.checkInDate);
                let now = new Date();
                return x.orderState === '未入住' && checkInDate.toLocaleDateString()===now.toLocaleDateString()
            })
        },
    },
    async mounted() {
        await this.getAllOrders()
    },
    methods: {
        ...mapActions([
            'getAllOrders',
            'getHotelById',
            'getOrderComment',
        ]),
        ...mapMutations([
            'set_orderDetailVisible',
            'set_orderInfo',
            'set_currentHotelId',
            'set_handleAbnormalOrderVisible',
        ]),
        showOrderDetail(record) {
            this.set_orderInfo(record)
            this.set_currentHotelId(record.hotelId)
            this.getHotelById(record.hotelId)
            this.set_orderDetailVisible(true)
        },
        handleAbnormalOrder(record) {
            this.set_orderInfo(record)
            this.set_currentHotelId(record.hotelId)
            this.getOrderComment(Number(record.id))
            this.getHotelById(record.hotelId)
            this.set_handleAbnormalOrderVisible(true)
        },
    }
}
</script>
<style scoped lang="less">
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
<style lang="less">
    .info-wrapper {
        .ant-tabs-bar {
            padding-left: 30px
        }
    }
</style>
