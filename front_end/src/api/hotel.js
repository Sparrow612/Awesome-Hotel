import { axios } from '@/utils/request'
const api = {
    hotelPre: '/api/hotel'
}
export function getHotelsAPI() {
    return axios({
        url: `${api.hotelPre}/all`,
        method: 'get',
    })
}
export function getHotelByIdAPI(hotelId) {
    return axios({
        url: `${api.hotelPre}/${hotelId}/detail`,
        method: 'GET',
    })
}
