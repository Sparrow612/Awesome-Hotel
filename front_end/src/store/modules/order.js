// created by glh 2020.05.12
import Vue from 'vue'
import { message } from 'ant-design-vue'
import {
    execOrderAPI,
    addCommentAPI,
    getOrderCommentAPI,
} from '@/api/order'

const order = {
    state: {
        orderDetailVisible: false,
        orderInfo: {},
        commentOrderModalVisible: false,
        orderComment: '',
    },
    mutations: {
        set_orderDetailVisible: function (state, data) {
            state.orderDetailVisible = data
        },
        set_orderInfo: function (state, data) {
            state.orderInfo = data
        },
        set_commentOrderModalVisible: function (state, data) {
            state.commentOrderModalVisible = data
        },
        set_orderComment: function (state, data) {
            state.orderComment = data
        }
    },
    actions: {
        execOrder: async({ state, dispatch }, orderId) => {
            const res = await execOrderAPI(orderId)
            if(res) {
                dispatch('geAllOrders')
                message.success('撤销成功')
            }else{
                message.error('撤销失败')
            }
        },
        addComment: async ({state, commit}, data) => {
            const res = await addCommentAPI(data)
            if (res) {
                commit('set_commentOrderModalVisible', false)
                message.success('评论成功')
            }
        },
        getOrderComment: async ({state, commit}, orderId) => {

        }
    }
}
export default order
