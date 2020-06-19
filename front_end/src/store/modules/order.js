// created by glh 2020.05.12
import Vue from 'vue'
import { message } from 'ant-design-vue'
import {
    execOrderAPI,
    addCommentAPI,
    getOrderCommentAPI,
    annulOrderCommentAPI,
} from '@/api/order'

const order = {
    state: {
        orderDetailVisible: false,
        orderInfo: {},
        commentOrderModalVisible: false,
        currentOrderComment: {},
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
        set_currentOrderComment: function (state, data) {
            state.currentOrderComment = data
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
        addComment: async ({state, commit, dispatch}, data) => {
            const res = await addCommentAPI(data)
            if (res) {
                commit('set_commentOrderModalVisible', false)
                message.success('评论成功')
                dispatch('getOrderComment', state.orderInfo.id)
            }
        },
        getOrderComment: async ({state, commit}, orderId) => {
            const res = await getOrderCommentAPI(orderId)
            if (res) {
                commit('set_currentOrderComment', res)
            }
        },
        annulOrderComment: async ({state, commit, dispatch}, orderId) => {
            const res = await annulOrderCommentAPI(orderId)
            if (res) {
                message.success('撤回成功')
            }
        }
    }
}
export default order
