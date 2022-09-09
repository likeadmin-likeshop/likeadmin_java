<template>
	<view class="">
		<u-parse :html="content"></u-parse>
	</view>
</template>

<script setup lang="ts">
	import { reactive, ref } from 'vue'
	import { AgreementEnum } from '@/enums/agreementEnums'
	const agreement = ref('') // 协议类型
	
	
</script>

<style lang="scss" scoped>
</style>
