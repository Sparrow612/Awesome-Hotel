import { axios } from '@/utils/request'

const api = {
    userPre: '/api/user'
}
export function loginAPI(data){
    return axios({
        url:`${api.userPre}/login`,
        method: 'POST',
        data
    })
}
export function registerAPI(data){
    return axios({
        url: `${api.userPre}/register`,
        method: 'POST',
        data
    })
}
export function getUserInfoAPI(id){
    return axios({
        url: `${api.userPre}/${id}/getUserInfo`,
        method: 'GET'
    })
}
export function updateUserInfoAPI(data) {
    return axios({
        url: `${api.userPre}/${data.id}/userInfo/update`,
        method: 'POST',
        data
    })
}
// created by glh 2020-05-30
export function getUserInfoByEmailAPI(params) {
    return axios({
        url: `${api.userPre}/getUserInfoByEmail`,
        method: 'GET',
        params
    })
}
export function updateUserBirthdayAPI(params) {
    return axios({
        url: `${api.userPre}/${params.id}/updateUserBirthday`,
        method: 'POST',
        params
    })
}
export function getUserCreditAPI(id) {
    return axios({
        url: `${api.userPre}/${id}/getUserCreditChanges`,
        method: 'GET',
    })
}
export function getAllUsersAPI() {
    return axios({
        url: `${api.userPre}/getAllUsers`,
        method: 'GET'
    })
}
export function userCollectHotelAPI(params) {
    return axios({
        url: `${api.userPre}/userCollection`,
        method: 'GET',
        params,
    })
}
export function addCollectionAPI(data) {
    return axios({
        url: `${api.userPre}/addCollection`,
        method: 'POST',
        data,
    })
}
export function annulCollectionAPI(collectionId){
    return axios({
        url: `${api.userPre}/${collectionId}/annulCollection`,
        method: 'POST'
    })
}
export function getUserCollectionsAPI(userId) {
    return axios({
        url: `${api.userPre}/${userId}/getUserCollection`,
        method: 'GET'
    })
}
export function getHotelCollectionNumAPI(hotelId){
    return axios({
        url: `${api.userPre}/${hotelId}/getHotelCollection`,
        method: 'GET'
    })
}