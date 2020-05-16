<template>
    <div>
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
        <div class="Browselist">
            <a-layout>
                <a-layout-header style="font-size: large;background-color: azure;color: black;">
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
                        :dataSource="rooms"
                >
                    <span slot="action" slot-scope="text, record">
                        <a-button type="primary" @click="order(record)">预定</a-button>
                    </span>
                </a-table>
            </a-layout>
        </div>
    </div>
</template>
<script>
import HotelCard from './components/hotelCard'
import { mapGetters, mapActions, mapMutations } from 'vuex'
const columns = [
    {
        title: '名称',
        dataIndex: 'hotelName',
        key: 'hotelName',
    },
    {
        title: '星级',
        dataIndex: 'hotelStar',
        key: 'hotelStar',
    },
    {
        title: '评分',
        dataIndex: 'rate',
        key: 'rate',
    },
    {
        title: '简介',
        key: 'description',
        dataIndex: 'description',
    },
    {
        title: '是否预定过',
        key: 'onceOrdered',
        dataIndex: 'onceOrdered',
    },
    {
        title: 'Action',
        key: 'action',
        scopedSlots: { customRender: 'action' },
    },
];
export default {
  name: 'home',
  components: {
    HotelCard
  },
  data(){
    return{
        columns,
        emptyBox: [{ name: 'box1' }, { name: 'box2'}, {name: 'box3'}]
    }
  },
  async mounted() {
    await this.getHotelList()
  },
  computed: {
    ...mapGetters([
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