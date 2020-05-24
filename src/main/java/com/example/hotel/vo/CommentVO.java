package com.example.hotel.vo;

/**
 * @author 庄子元 181830266@smail.nju.edu.cn
 * @date 2020/5/24 16:04
 */
public class CommentVO {
    Integer userId;
    Integer orderId;
    Integer points;
    Integer sanitation;
    Integer environment;
    Integer service;
    Integer equipment;
    String comment;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getSanitation() {
        return sanitation;
    }

    public void setSanitation(Integer sanitation) {
        this.sanitation = sanitation;
    }

    public Integer getEnvironment() {
        return environment;
    }

    public void setEnvironment(Integer environment) {
        this.environment = environment;
    }

    public Integer getService() {
        return service;
    }

    public void setService(Integer service) {
        this.service = service;
    }

    public Integer getEquipment() {
        return equipment;
    }

    public void setEquipment(Integer equipment) {
        this.equipment = equipment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
