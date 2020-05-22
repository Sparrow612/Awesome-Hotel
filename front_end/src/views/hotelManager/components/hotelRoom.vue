<template>
    <div>
        <h1>TODO: 要先获取当前酒店管理员对应的酒店</h1>
        <div style="width: 100%; text-align: right; margin: 20px 0">
            <a-button type="primary" @click="addRoom">
                <a-icon type="plus"/>录入客房
            </a-button>
        </div>

        <div class="room-info">
            <a-table
                    :columns="columns_of_rooms"
                    :dataSource="roomList"
                    bordered
            >

            </a-table>
        </div>

        <addRoomModal></addRoomModal>
    </div>

</template>

<script>
import { mapGetters, mapMutations, mapActions } from 'vuex'
import addRoomModal from "./addRoomModal";
const columns_of_rooms = [
    {
        title: '房间类型',
        dataIndex: 'roomType',
    },
    {
        title: '当前数量',
        dataIndex: 'currentNum',
    },
    {
        title: '总共数量',
        dataIndex: 'totalNum',
    },
    {
        title: '价格',
        dataIndex: 'price'
    },
];
export default {
    name: "hotelRoom",
    data() {
        return {
            columns_of_rooms,
            roomList: []
        }
    },
    components: {
        addRoomModal,
    },
    computed: {
        ...mapGetters([
            'addRoomsModalVisible'
        ])
    },
    methods: {
        ...mapMutations([
            'set_addRoomModalVisible'
        ]),

        addRoom() {
            this.set_addRoomModalVisible(true)
        }
    }

}
</script>

<style scoped>

</style>