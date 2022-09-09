<template>
    <view class="news" >
		<!-- 搜索 -->
		<navigator url="/pages/search/search">
			<view class="news-search px-[24rpx] py-[14rpx] bg-white">
				<u-search placeholder="请输入关键词搜索" disabled :show-action="false"></u-search>
			</view>
		</navigator>
		
		<!-- 内容 -->
        <tabs 
			:current="current"
			@change="handleChange"
			height="80"
			bar-width="60"
			:barStyle="{'bottom': '0'}"
		>
            <tab 
				v-for="(item, i) in tabList"
				:key="i" 
				:name="item.name"
			>
                <view class="news-list pt-[20rpx]">
					<news-list :cid="item.id" :i="i" :index="current"></news-list>
                </view>
            </tab>
        </tabs>
    </view>
</template>

<script lang="ts" setup>
    import { ref, reactive, computed } from "vue"
    import { onLoad, onShow, onReady } from "@dcloudio/uni-app";
	import NewsList from "./component/news-list.vue"
	import { getArticleCate } from "@/api/news"

    
    const tabList = ref< any >([])
    const current = ref<number>(0)
    
    const handleChange = (index: number) => {
		console.log(index)
        current.value = Number(index)
    }
	
	const getData = async () => {
		tabList.value = await getArticleCate()
	}
    
    onLoad((options) => {
		getData()
    })
</script>

<style lang="scss">
    .news {
		&-search {
			margin-bottom: 2rpx;
		}
		
		&-list {
			height: calc(100vh - 86px);
		}
	}
</style>
