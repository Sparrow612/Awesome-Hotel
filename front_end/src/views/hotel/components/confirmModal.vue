<template>
    <a-modal
            :visible="confirmModalVisible"
            title="确定订单"
            cancelText="取消"
            okText="确定"
            @cancel="cancle"
            @ok="submit"
    >
        <p>确认订单填写无误？</p>
    </a-modal>
</template>

<script>
    import { mapGetters, mapMutations, mapActions } from 'vuex'
    export default {
        name: "confirmModal",
        computed: {
            ...mapGetters([
                'confirmModalVisible',
            ]),
        },
        methods: {
            ...mapMutations([
                'set_confirmState',
                'set_confirmModalVisible',
            ]),
            ...mapActions([

            ]),
           submit(e){
               this.set_confirmModalVisible(true) // 这里没写完
           },
           cancle(){
               this.set_confirmModalVisible(false)
           }
        }
    }

</script>

<style scoped>

</style>