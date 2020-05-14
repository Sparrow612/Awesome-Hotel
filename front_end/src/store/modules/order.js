// created by glh 2020.05.12

const order = {
    state: {
        orderDetailVisible: false,
        orderInfo: {},
    },
    mutations: {
        set_orderDetailVisible: function (state, data) {
            state.orderDetailVisible = data
        },
        set_orderInfo: function (state, data) {
            state.orderInfo = data
        }
    }
}
export default order
