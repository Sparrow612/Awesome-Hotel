const getters = {
    //user
    token: state => state.user.token,
    userId: state => state.user.userId,
    userInfo: state => state.user.userInfo,
    dateRange: state => state.user.dateRange,
    userOrderList: state => state.user.userOrderList,
    onceOrderedList: state => state.user.onceOrderedList,
    hotelListLoading: state => state.hotel.hotelListLoading,
    hotelList: state => state.hotel.hotelList,
    currentHotelInfo: state => state.hotel.currentHotelInfo,
    currentHotelId: state => state.hotel.currentHotelId,
    confirmModalVisible: state => state.hotel.confirmModalVisible,
    orderModalVisible: state => state.hotel.orderModalVisible,
    currentOrderRoom: state => state.hotel.currentOrderRoom,
    orderMatchCouponList: state => state.hotel.orderMatchCouponList,
    registerMembershipModalVisible: state => state.user.registerMembershipModalVisible,
    //admin
    managerList: state => state.admin.managerList,
    addManagerModalVisible: state => state.admin.addManagerModalVisible,
    addManagerParams: state => state.admin.addManagerParams,
    salesPersonList: state => state.admin.salesPersonList,
    addSalesPersonModalVisible: state => state.admin.addSalesPersonModalVisible,
    addSalesPersonParams: state => state.admin.addSalesPersonParams,
    modifyInfoModalVisible: state => state.admin.modifyInfoModalVisible,
    modifyUserInfo: state => state.admin.modifyUserInfo,
    addHotelModalVisible: state => state.admin.addHotelModalVisible,
    //hotelManager
    manageHotelId: state => state.hotelManager.manageHotelId,
    orderList: state => state.hotelManager.orderList,
    addRoomModalVisible: state => state.hotelManager.addRoomModalVisible,
    couponVisible: state => state.hotelManager.couponVisible,
    addCouponVisible: state => state.hotelManager.addCouponVisible,
    activeHotelId: state => state.hotelManager.activeHotelId,
    couponList: state => state.hotelManager.couponList,
    registerHotelMembershipModalVisible: state => state.hotelManager.registerHotelMembershipModalVisible,
    //salesPerson
    allOrderList: state => state.salesPerson.allOrderList,
    handleAbnormalOrderVisible: state => state.salesPerson.handleAbnormalOrderVisible,
    addSiteCouponVisible: state => state.salesPerson.addSiteCouponVisible,
    currentUserId: state => state.salesPerson.currentUserId,
    currentUserInfo: state => state.salesPerson.currentUserInfo,
    searchSuccess:state => state.salesPerson.searchSuccess,
    //order
    orderDetailVisible: state => state.order.orderDetailVisible,
    orderInfo: state => state.order.orderInfo,
    //coupon
    couponDetailVisible: state => state.coupon.couponDetailVisible,
    currentCouponInfo: state => state.coupon.currentCouponInfo,
}

export default getters
