const getters = {
    //user
    token: state => state.user.token,
    userId: state => state.user.userId,
    userInfo: state => state.user.userInfo,
    userVIP: state => state.user.userVIP,
    dateRange: state => state.user.dateRange,
    userOrderList: state => state.user.userOrderList,
    creditChangeList: state => state.user.creditChangeList,
    onceOrderedList: state => state.user.onceOrderedList,
    userCollections: state => state.user.userCollections,
    registerSiteMembershipModalVisible: state => state.user.registerSiteMembershipModalVisible,
    registerCorporationMembershipModalVisible: state => state.user.registerCorporationMembershipModalVisible,
    //hotel
    hotelListLoading: state => state.hotel.hotelListLoading,
    hotelList: state => state.hotel.hotelList,
    limitedHotelList: state => state.hotel.limitedHotelList,
    searchList: state => state.hotel.searchList,
    currentHotelInfo: state => state.hotel.currentHotelInfo,
    currentHotelId: state => state.hotel.currentHotelId,
    orderModalVisible: state => state.hotel.orderModalVisible,
    currentOrderRoom: state => state.hotel.currentOrderRoom,
    orderMatchCouponList: state => state.hotel.orderMatchCouponList,
    hotelQuestion: state => state.hotel.hotelQuestion,
    answersVisible: state => state.hotel.answersVisible,
    currCollections: state => state.hotel.currCollections,
    currHotelCollectedByUser: state => state.hotel.currHotelCollectedByUser,
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
    hotelInfo: state => state.hotelManager.hotelInfo,
    //salesPerson
    allOrderList: state => state.salesPerson.allOrderList,
    siteCouponList: state => state.salesPerson.siteCouponList,
    handleAbnormalOrderVisible: state => state.salesPerson.handleAbnormalOrderVisible,
    addSiteCouponVisible: state => state.salesPerson.addSiteCouponVisible,
    currentUserId: state => state.salesPerson.currentUserId,
    currentUserInfo: state => state.salesPerson.currentUserInfo,
    searchSuccess: state => state.salesPerson.searchSuccess,
    allUserList: state => state.salesPerson.allUserList,
    allClientVIPList: state => state.salesPerson.allClientVIPList,
    allCorpVIPList: state => state.salesPerson.allCorpVIPList,
    levelConsumption: state => state.salesPerson.levelConsumption,
    levelModifyModalVisible: state => state.salesPerson.levelModifyModalVisible,
    currentLevel: state => state.salesPerson.currentLevel,
    //order
    orderDetailVisible: state => state.order.orderDetailVisible,
    orderInfo: state => state.order.orderInfo,
    commentOrderModalVisible: state => state.order.commentOrderModalVisible,
    currentOrderComment: state => state.order.currentOrderComment,
    //coupon
    couponDetailVisible: state => state.coupon.couponDetailVisible,
    currentCouponInfo: state => state.coupon.currentCouponInfo,
}

export default getters
