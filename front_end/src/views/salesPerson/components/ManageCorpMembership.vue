<template>
    <div>
        <a-table
                :columns="columns"
                :dataSource="allCorpVIPList"
                :locale="{emptyText: '暂时没有企业会员'}"
                bordered
        >
            <a-tag slot="corporation" color="orange" slot-scope="text">
                {{ text }}
            </a-tag>

            <span slot="level" slot-scope="text">
                {{ text }}
            </span>

            <span slot="consumption" slot-scope="text">
                {{ text }}
            </span>

            <span slot="status" slot-scope="text">
                <a-tag color="blue" v-if="text===1">正常</a-tag>
                <a-tag color="red" v-else>被冻结</a-tag>
            </span>

            <span slot="action" slot-scope="record">
                <a-button type="danger" size="small" v-if="record.status===1" @click="freezeCorp(record.corporationName)">冻结</a-button>
                <a-button type="primary" size="small" v-else @click="restoreCorp(record.corporationName)">恢复</a-button>
            </span>
        </a-table>
    </div>
</template>

<script>
    import { mapGetters, mapMutations, mapActions } from 'vuex'
    const columns = [
        {
            title: '企业名',
            dataIndex: 'corporationName',
            scopedSlots: { customRender: 'corporation' }
        },
        {
            title: 'VIP等级',
            dataIndex: 'level',
            scopedSlots: { customRender: 'level' }
        },
        {
            title: '累计消费￥',
            dataIndex: 'consumption',
            scopedSlots: { customRender: 'consumption' }
        },
        {
            title: '状态',
            dataIndex: 'status',
            scopedSlots: { customRender: 'status' }
        },
        {
            title: '操作',
            key: 'action',
            scopedSlots: { customRender: 'action' },
        },

    ];
    export default {
        name: "ManageCorpMembership",
        data() {
            return {
                columns,
            }
        },
        computed: {
            ...mapGetters([
                'allCorpVIPList',
            ]),
        },
        async mounted() {
            await this.getAllCorpVIP()
        },
        methods: {
            ...mapActions([
                'getAllCorpVIP',
                'freezeCorpVIP',
                'restoreCorpVIP',
            ]),
            freezeCorp(corporationName) {
                console.log('freeze' + corporationName)
                this.freezeCorpVIP(corporationName)
            },
            restoreCorp(corporationName) {
                console.log('restore ' + corporationName)
                this.restoreCorpVIP(corporationName)
            },
        }
    }
</script>

<style scoped>

</style>
