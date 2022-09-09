<template>
	<z-paging ref="paging" v-model="dataList" v-if="i == index" @query="queryList" :fixed="false" height="100%">
		<block v-for="(newsItem,newsIndex) in dataList" :key="newsIndex">
			<news-card :item="newsItem" :newsId="newsItem.id"></news-card>
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
		try{
			const { lists } = await getArticleList({
				cid: props.cid,
				pageNo, pageSize
			})
			paging.value.complete(lists);
		}catch(e){
			console.log('报错=>',e)
			//TODO handle the exception
			paging.value.complete(false);
		}
	}
</script>

<style scoped>

</style>
