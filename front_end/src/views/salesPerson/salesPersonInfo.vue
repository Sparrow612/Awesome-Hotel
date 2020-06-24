<template>
    <InfoForm></InfoForm>
</template>

<script>
    import {mapGetters, mapMutations, mapActions} from 'vuex'
    import InfoForm from '../../views/user/components/infoForm'

    export default {
        name: "salesPersonInfo",
        data() {
            return {

            }
        },
        components: {
            InfoForm,
        },
        computed: {
            ...mapGetters([

            ])
        },
        mounted() {
            this.getUserInfo()
        },
        methods: {
            ...mapActions([
                'getUserInfo',
            ]),
        },
    }
</script>

<style lang="less" scoped>

</style>