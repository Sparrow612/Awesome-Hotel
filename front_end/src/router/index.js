import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/login.vue'

Vue.use(VueRouter)
const routes = [
  {
    path: '/login',
    name: 'login',
    component: Login
  },
  {
    path: '/',
    redirect: '/NJUSE'
  },
  {
    path: '/NJUSE',
    name: 'layout',
    redirect: '/hotel/hotelList',
    component: ()=> import('@/views/layout'),
    children: [
      {
        path: '/hotel/hotelList',
        name: 'hotelList',
        component: () => import('@/views/hotel/hotelList')
      },
      {
        path: '/hotel/searchHotel',
        name: 'searchHotel',
        component: () => import('@/views/hotel/searchHotel')
      },
      {
        path: '/hotel/hotelDetail/:hotelId',
        name: 'hotelDetail',
        component: () => import('@/views/hotel/hotelDetail')
      },
      {
        path: '/user/info/:userId',
        name: 'userInfo',
        component: () => import('@/views/user/info')
      },
      {
        path: '/manager/info/:userId',
        name: 'managerInfo',
        component: () => import('@/views/hotelManager/info')
      },
      {
        path: '/user/membership',
        name: 'userMembership',
        component: () => import('@/views/user/userMembership')
      },
      {
        path: '/hotel/membership',
        name: 'hotelMembership',
        component: () => import('@/views/hotel/hotelMembership')
      },
      {
        path: '/hotelManager/manageHotel',
        name: 'manageHotel',
        component: () => import('@/views/hotelManager/manageHotel')
      },
      {
        path: '/admin/manageUser',
        name: 'manageUser',
        component: () => import('@/views/admin/manageUser')
      },
      {
        path: '/salesPerson/manageHotelMembership',
        name: 'manageHotelMembership',
        component: () => import('@/views/salesPerson/manageHotelMembership')
      },
      {
        path: '/salesPerson/manageSiteCoupon',
        name: 'manageSiteCoupon',
        component: () => import('@/views/salesPerson/siteCoupon')
      }
    ]
  },
]
const createRouter = () => new VueRouter({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes
})
const router = createRouter()

export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
