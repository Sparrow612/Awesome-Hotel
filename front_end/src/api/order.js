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
export function getHotelOrdersInMonthAPI(hotelId) {
    return axios({
        url: `${api.orderPre}/${hotelId}/getOrdersInMonthOfAll`,
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

//对订单发表评论
export function addCommentAPI(data) {
    return axios({
        url: `${api.orderPre}/addComment`,
        method: 'POST',
        data
    })
}
//获取某个订单的评论
export function getOrderCommentAPI(orderId) {
    return axios({
        url: `${api.orderPre}/${orderId}/getComment`,
        method: 'GET',
    })
}
//撤回对某个订单的评论
export function annulOrderCommentAPI(orderId) {
    return axios({
        url: `${api.orderPre}/${orderId}/annulComment`,
        method: 'GET',
    })
}
