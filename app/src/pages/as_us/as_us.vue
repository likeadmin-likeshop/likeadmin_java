<template>
	<view class="user-set">
		<view class="item flex bg-white mt-[20rpx]">
			<u-avatar :src="src" shape="square"></u-avatar>
			<view class="ml-[20rpx] flex flex-1 justify-between">
				<view>
					<view class="mb-[15rpx] text-xl font-medium">闻喜的小贾</view>
					<view class="text-content text-xs">账号：tt54541512</view>
				</view>
				<u-icon name="arrow-right" color="#666"></u-icon>
			</view>
		</view>
		<view class="item bg-white mt-[20rpx] btn-border flex flex-1 justify-between">
			<view class="text-xl">登录密码</view>
			<u-icon name="arrow-right" color="#666"></u-icon>
		</view>
		<view class="item bg-white btn-border flex flex-1 justify-between">
			<view class="text-xl">绑定微信</view>
			<view class=" flex justify-between">
				<view class="text-muted mr-[20rpx]">
					未绑定
				</view>
				<u-icon name="arrow-right" color="#666"></u-icon>
			</view>
		</view>
		
		<view class="item bg-white mt-[20rpx] btn-border flex flex-1 justify-between">
			<view class="text-xl">隐私政策</view>
			<u-icon name="arrow-right" color="#666"></u-icon>
		</view>
		<view class="item bg-white btn-border flex flex-1 justify-between">
			<view class="text-xl">服务协议</view>
			<u-icon name="arrow-right" color="#666"></u-icon>
		</view>
		<view class="item bg-white btn-border flex flex-1 justify-between">
			<view class="text-xl">关于我们</view>
			<view class="flex justify-between">
				<view class="text-muted mr-[20rpx]">
					v10.2.0
				</view>
				<u-icon name="arrow-right" color="#666"></u-icon>
			</view>
		</view>
	</view>
</template>

<script setup lang="ts">
	import {
		getDecorate
	} from '@/api/shop'
	import {
		useUserStore
	} from '@/stores/user'
	import {
		onShow
	} from '@dcloudio/uni-app'
	import {
		storeToRefs
	} from 'pinia'
	import {
		reactive
	} from 'vue'
	const state = reactive < {
		pages: any[]
	} > ({
		pages: []
	})
	const getData = async () => {
		const data = await getDecorate({
			id: 2
		})
		state.pages = JSON.parse(data.pages)
	}
	const userStore = useUserStore()
	const {
		userInfo,
		isLogin
	} = storeToRefs(userStore)
	onShow(() => {
		userStore.getUser()
	})
	getData()
</script>

<style lang="scss" scoped>
	.user-set {
		.item {
			padding: 30rpx;
		}

		.btn-border {
			border-bottom: 1rpx solid $u-form-item-border-color;
		}
	}
</style>
