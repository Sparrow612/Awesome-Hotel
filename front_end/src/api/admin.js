import { axios } from '@/utils/request'
const api = {
    adminPre: '/api/admin'
}
export function getManagerListAPI(){
    return axios({
        url: `${api.adminPre}/getAllManagers`,
        method: 'POST'
    })
}
export function addManagerAPI(data) {
    return axios({
        url: `${api.adminPre}/addManager`,
        method: 'POST',
        data
    })
}
export function addSalesPersonAPI(data) {
    return axios({
        url: `${api.adminPre}/addSalesPerson`,
        method: 'POST',
        data
    })
}
export function getSalesPersonListAPI() {
    return axios({
        url: `${api.adminPre}/getAllSalesPerson`,
        method: 'POST'
    })
}
export function deleteHotelManagerAPI(id) {
    return axios({
        url: `${api.adminPre}/${id}/deleteHotelManager`,
        method: 'POST'
    })
}
export function deleteSalesPersonAPI(id) {
    return axios({
        url: `${api.adminPre}/${id}/deleteSalesPerson`,
        method: 'POST'
    })
}

