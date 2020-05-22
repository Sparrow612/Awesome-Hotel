import { axios } from '@/utils/request'
const api = {
    orderPre: '/api/order'
}
export function reserveHotelAPI(data) {
    return axios({
        url: `${api.orderPre}/addOrder`,
        method: 'POST',
        data,
    })
}
export function getAllOrdersAPI() {
    return axios({
        url: `${api.orderPre}/getAllOrders`,
        method: 'GET',
    })
}
export function getUserOrdersAPI(userId) {
    return axios({
        url: `${api.orderPre}/${userId}/getUserOrders`,
        method: 'GET',
    })
}
export function getHotelOrdersAPI(hotelId) {
    return axios({
        url: `${api.orderPre}/${hotelId}/allOrders`,
        method: 'GET',
    })
}
export function cancelOrderAPI(orderId) {
    return axios({
        url: `${api.orderPre}/${orderId}/annulOrder`,
        method: 'GET',
    })
}
// created by GLH 2020.05.15
// TODO 与后端对接
export function execOrderAPI(orderId) {
    return axios({
        url: `${api.orderPre}/${orderId}/execOrder`,
        method: 'GET'
    })
}
// 标记异常订单
