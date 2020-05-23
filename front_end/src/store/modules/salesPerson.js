// created by glh 2020-05-23
// 网站营销人员
import {
    getAllOrdersAPI
} from "../../api/order";

import {message} from "ant-design-vue";

const salesPerson = {
    state: {
        allOrderList: [],
    },
    mutations: {
        set_allOrderList: function (state, data) {
            state.allOrderList = data
        },
    },
    actions: {
        getAllOrders: async ({state, commit}) => {
            let res = await getAllOrdersAPI()
            if (res) {
                commit('set_allOrderList', res)
            }
        }
    }
}
export default salesPerson