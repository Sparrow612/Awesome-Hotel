<template>
    <div class="box">
        <div class="searchBox">
            <a-input type="text" v-model="keyword" placeholder="请输入关键词进行搜索" class="searchInput" style="padding-left: 10px;"/>
            <a-button @click="search" value="搜索" class="searchButton">
                <a-icon type="search"/>
            </a-button>
        </div>
    </div>
</template>

<script>
    export default {
        name: "searchHotel"
    }
</script>

<style scoped lang="less">
    .box{
        margin: 0 auto;
        padding-top: 80px;
        height: 50px;
        width: 100%;
    }
    .searchBox{
        margin: 0 auto;
        width: 60%;
        position: relative;
    }
    .searchInput{
        display: inline-block;
        width: 85%;
        height: 38px;
        border: 1px solid #cccccc;
        float: left;
        box-sizing: border-box;
        -moz-box-sizing:border-box; /* Firefox */
        -webkit-box-sizing:border-box; /* Safari */
        border-bottom-left-radius: 5px;
        border-top-left-radius: 5px;
    }
    .searchButton{
        display: inline-block;
        width: 15%;
        height: 38px;
        line-height: 40px;
        float: left;
        background-color: #00a0e9;
        font-size: 16px;
        cursor: pointer;
        border-bottom-right-radius: 5px;
        border-top-right-radius: 5px;
        border: none;
        color: #fff;
    }
</style>