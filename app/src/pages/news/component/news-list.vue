<template>
	<z-paging ref="paging" v-model="dataList" v-if="i == index" @query="queryList" :fixed="false" height="100%"
		use-page-scroll>
		<block v-for="(newsItem,newsIndex) in dataList" :key="newsIndex">
			<news-card :item="newsItem"></news-card>
		</block>
	</z-paging>
</template>

<script lang="ts" setup>
	import { ref } from 'vue';
	import { getArticleList } from "@/api/news"

	const props = withDefaults(defineProps < {
		cid: number,
		i: number,
		index: number
	} > (), {
		cid: 0
	})

	const paging = ref(null)
	const dataList = ref([{
		title: '123'
	}])

	const queryList = async (pageNo, pageSize) => {
		// console.log(pageNo, pageSize)
		const {
			lists
		} = await getArticleList({
			cid: props.cid,
			pageNo,
			pageSize
		})
		console.log(lists)
		paging.value.complete(lists);
	}
</script>

<style scoped>

</style>
