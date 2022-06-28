<template>
    <div class="select-icon">
        <div class="flex">
            <div class="show-icon">
                <i class="iconfont" :class="icon"></i>
                <el-icon class="close-icon row-between" v-show="icon" @click="handleCloseIcon">
                    <CircleClose />
                </el-icon>
            </div>

            <el-popover placement="bottom" :width="400" trigger="click">
                <template #reference>
                    <el-button>
                        <el-icon><arrow-down /> </el-icon>
                    </el-button>
                </template>

                <div class="select-icon-item">
                    <div v-for="(item, index) in selectIcon" :key="index">
                        <div @click="selectIconItem(item)">
                            <i class="iconfont" :class="item"></i>
                        </div>
                    </div>
                </div>
            </el-popover>
        </div>
    </div>
</template>

<script lang="ts" setup>
    import { onMounted, reactive, ref, computed } from 'vue'
    import { Search } from '@element-plus/icons-vue'

    const selectIcon = ref<any>([
        'icon_kdzs_mdsz',
        'icon_xpdy_mbgl',
        'icon_xcxzb_zb',
        'icon_xycj_cj',
        'operation',
        'icon_pcshop',
        'icon_xpdy_dyjgl',
        'icon_kdzs_pldy',
        'icon_kdzs_mdmb',
        'icon_qudao_h5',
        'icon_fenxiao_goods',
        'icon_kanjia',
        'icon_hexiao_order',
        'icon_pintuan',
        'carryout',
        'icon_qiandao_jilu',
        'icon_notice',
        'icon_notice_mail',
        'gift',
        'icon_notice_buyer',
        'Field-time',
        'icon_hexiao_member2',
        'icon_coupons',
        'icon_coupons_data',
        'icon_fenxiao_member',
        'icon_qiandao_guize',
        'icon_pintuan2',
        'icon_notice_seller',
        'icon_pintuan_data',
        'icon_fenxiao_data',
        'icon_ziti_store',
        'wallet',
        'icon_kefu_comments',
        'tradingdata',
        'icon_fenxiao_order',
        'icon_hexiao_member',
        'icon_fenxiao_set',
        'icon_fenxiao_grade',
        'yiguanzhugongyingshang',
        'icon_user_gaikuang',
        'icon_qudao_weixin',
        'icon_qudao_app',
        'icon_qudao_xiaochengxu',
        'icon_dianpu_daohang',
        'icon_yingxiao_qipao',
        'icon_set_jiaoyi',
        'icon_caiwu_yue',
        'icon_caiwu_tixian',
        'icon_caiwu_jifen',
        'icon_shuju_liuliang',
        'icon_user_dengji',
        'icon_user_guanli',
        'icon_user_biaoqian',
        'icon_copy',
        'icon_set_product',
        'icon_set_save',
        'icon_dianpu_fenlei',
        'icon_dianpu_fengge',
        'icon_dianpu_sucai',
        'icon_dianpu_xiangqing',
        'icon_order_guanli',
        'icon_caiwu',
        'icon_user',
        'icon_set_user',
        'icon_shuju',
        'icon_dianpu_home',
        'icon_yingyongcenter',
        'icon_qudao',
        'icon_qudao2',
        'icon_set_store',
        'icon_dianpu_weiyem',
        'icon_set_quanxian',
        'icon_hide',
        'icon_show',
        'icon_wallet',
        'icon_set_pay',
        'icon_set_weihu',
        'icon_set_peisong',
        'icon_yingxiaowf',
        'icon_dianpu_shoppingCar',
        'icon_goods',
        'icon_sort',
        'icon_danwei',
        'icon_pingjia',
        'icon_pinpai',
        'icon_gongyingshang',
    ])

    const props = withDefaults(
        defineProps<{
            icon: any
        }>(),
        {
            icon: '',
        }
    )

    const selectIconItem = (item: string) => {
        emit('update:icon', item)
    }

    const handleCloseIcon = () => {
        emit('update:icon', '')
    }

    const emit = defineEmits(['update:icon'])
</script>

<style lang="scss" scoped>
    .show-icon {
        width: 80px;
        height: 35px;
        border: 1px solid #dcdfe6;
        border-radius: 3px;
        display: flex;
        justify-content: center;
        align-items: center;
        position: relative;

        .iconfont {
            font-size: 30px;
        }

        .close-icon {
            position: absolute;
            right: 0;
            top: 0;
            font-size: 15px;
            cursor: pointer;
        }
    }
    .select-icon-item {
        display: flex;
        flex-wrap: wrap;
        height: 300px;
        overflow: auto;
        cursor: pointer;

        .iconfont {
            font-size: 46px;
        }
    }
</style>
