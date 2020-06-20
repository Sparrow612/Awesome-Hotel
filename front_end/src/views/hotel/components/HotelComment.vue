<template>
    <a-list
            class="comment-list"
            :header="`共${hotelComments.length}条点评`"
            item-layout="horizontal"
            :data-source="hotelComments"
    >
        <a-list-item slot="renderItem" slot-scope="item">
            <a-comment :author="getUserNameById(item.userId)" :avatar="avatar_url" class="hotelComment">
                <p slot="content">
                    总体评分: <a-rate :default-value="2" disabled /><br/>
                    卫生: <a-rate :default-value="2" disabled />&nbsp;
                    环境: <a-rate :default-value="2" disabled />&nbsp;
                    服务: <a-rate :default-value="2" disabled />&nbsp;
                    设备: <a-rate :default-value="2" disabled /><br/><br/>
                    <a-tag color="cyan">评价:</a-tag>{{ item.comment }}
                </p>
            </a-comment>
        </a-list-item>
    </a-list>
</template>

<script>
    import { mapGetters, mapMutations, mapActions } from 'vuex'
    import moment from 'moment';

    export default {
        name: "HotelComment",
        data() {
            return {
                data: [
                    {
                        author: 'Han Solo',
                        avatar: 'https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png',
                        content:
                            'We supply a series of design principles, practical patterns and high quality design resources (Sketch and Axure), to help people create their product prototypes beautifully and efficiently.',
                        datetime: moment().subtract(1, 'days'),
                    },
                ],
                moment,
                avatar_url: 'https://colarhua-pic.oss-cn-shenzhen.aliyuncs.com/gary-jw-3/user_logo.svg',
            }
        },
        computed: {
            ...mapGetters([
                'hotelComments',
                'allUserList',
            ]),
        },
        async mounted() {
            await this.getHotelComments()
            await this.getAllUsers()

        },
        methods:{
            ...mapActions([
                'getHotelComments',
                'getAllUsers',
            ]),
            getUserNameById(userId) {
                for (let i = 0; i < this.allUserList.length; i++) {
                    if(this.allUserList[i].id === userId) {
                        return this.allUserList[i].userName
                    }
                }
                return '未知用户'
            }
        },

    }
</script>

<style scoped>
    .hotelComment {
        padding-left: 30px;
        border: 1px solid #919191;
        width: 100%;
        border-radius: 10%;
    }
</style>
