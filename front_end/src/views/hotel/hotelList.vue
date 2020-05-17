<template>
    <div>
        <div  v-if="userInfo.userType==='Client'">
        <div class="hotelList">
            <a-layout>
                <a-layout-content style="min-width: 800px">
                    <a-spin :spinning="hotelListLoading">
                        <div class="card-wrapper">
                            <HotelCard :hotel="item" v-for="item in hotelList" :key="item.index" @click.native="jumpToDetails(item.id)"></HotelCard>
                            <div v-for="item in emptyBox" :key="item.name" class="emptyBox ant-col-xs-7 ant-col-lg-5 ant-col-xxl-3"></div>
                            <a-pagination showQuickJumper :total="hotelList.totalElements" :defaultCurrent="1" @change="pageChange"></a-pagination>
                        </div>
                    </a-spin>
                </a-layout-content>
            </a-layout>
        </div>
        <div class="Browselist" v-if="userInfo.userType==='Client'">
            <a-layout>
                <a-layout-header style="font-size: x-large;background-color: cornflowerblue;color: white;">
                    <a-icon type="eye"/>
                    浏览酒店
                </a-layout-header>
<!--                <a-layout-content style="font-size: large;background-color: aliceblue;padding: 20px">-->
<!--                    <a-icon type="down-circle"/>-->
<!--                    选择地址和商圈-->
<!--                    <div style="display: flex;">-->
<!--                        <a-input placeholder="请输入地址" class="info">-->
<!--                            <a-icon slot="prefix" type="environment" />-->
<!--                        </a-input>-->
<!--                        <a-input placeholder="请输入商圈" class="info">-->
<!--                            <a-icon slot="prefix" type="compass" />-->
<!--                        </a-input>-->
<!--                        <a-button type="primary" @click="showHotels" style="margin-left: 200px">-->
<!--                            <a-icon type="monitor"/>-->
<!--                            查看-->
<!--                        </a-button>-->
<!--                    </div>-->
<!--                </a-layout-content>-->
                <a-table
                        :columns="columns"
                        :dataSource="hotelList"
                >

                    <span slot="action" slot-scope="record">
                        <a-button type="primary" size="small" @click="order(record)">查看详情</a-button>
                    </span>
                </a-table>
            </a-layout>
        </div>
        </div>
        <div  v-if="userInfo.userType==='HotelManager'">
            <h1>酒店工作人员看到的页面</h1>
            <p>在这里展示一下酒店的营收情况</p>
            <p>展示酒店收到的订单</p>
        </div>
        <div  v-if="userInfo.userType==='Admin'">
            <h1>网站人员看到的页面</h1>
        </div>
    </div>
</template>
<script>
import HotelCard from './components/hotelCard'
import { mapGetters, mapActions, mapMutations } from 'vuex'
const columns = [
    {
        title: '名称',
        dataIndex: 'name',
    },
    {
        title: '星级',
        dataIndex: 'hotelStar',
    },
    {
        title: '评分',
        dataIndex: 'rate',
    },
    {
        title: '商圈',
        dataIndex: 'bizRegion',
    },
    {
        title: '地址',
        dataIndex: 'address',
    },
    {
        title: '简介',
        dataIndex: 'description',
    },
    {
        title: '是否预定过',
        filters: [{ text: '已预订', value: '已预订' }, { text: '未预定', value: '未预定' }],
        onFilter: (value, record) => record.onceOrdered.includes(value),
        dataIndex: 'onceOrdered',
        scopedSlots: { customRender: 'onceOrdered' }
    },
    {
        title: '操作',
        scopedSlots: { customRender: 'action' },
    },
];
export default {
  name: 'home',
  components: {
    HotelCard
  },
  data(){
    return {
        emptyBox: [{ name: 'box1' }, { name: 'box2'}, {name: 'box3'}],
        columns
    }
  },
  async mounted() {
      await this.getHotelList()
  },
  computed: {
    ...mapGetters([
        'userInfo',
        'hotelList',
        'hotelListLoading'
    ])
  },
  methods: {
      ...mapMutations([
          'set_hotelListParams',
          'set_hotelListLoading'
      ]),
      ...mapActions([
          'getHotelList'
      ]),
      pageChange(page, pageSize) {
          const data = {
              pageNo: page - 1
          }
          this.set_hotelListParams(data)
          this.set_hotelListLoading(true)
          this.getHotelList()
      },
      jumpToDetails(id){
          this.$router.push({ name: 'hotelDetail', params: { hotelId: id }})
      },
      showHotels(){

      }
  }
}
</script>

<style scoped lang="less">
    .hotelList {
        text-align: center;
        padding: 50px 0;
        .emptyBox {
          height: 0;
          margin: 10px 10px
        }
        .card-wrapper{
          display: flex;
          justify-content: space-around;
          flex-wrap: wrap;
          flex-grow: 3;
          min-height: 600px
        }
        .card-wrapper .card-item {
          margin: 30px;
          position: relative;
          height: 188px;
        }

    }
    .Browselist {

        .info{
            width: 400px;
            margin: 10px;
        }
    }
</style>