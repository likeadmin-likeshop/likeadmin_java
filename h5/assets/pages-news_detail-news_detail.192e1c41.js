import{d as a,A as e,Y as s,o as t,c as l,w as r,h as u,b as c,l as i,t as m,k as d,n as p,r as f,a as n,z as o}from"./index.21969731.js";import{_ as x}from"./u-parse.5f650ac0.js";import{_}from"./u-icon.67feec68.js";import{_ as v}from"./icon_visit.5f8ba9a3.js";import{b as w,c as h,d as y}from"./news.65bbe893.js";import{_ as b}from"./plugin-vue_export-helper.21dcd24c.js";var j=b(a({__name:"news_detail",setup(a){const b=e({});let j="";const g=async a=>{b.value=await w({id:a})};return s((a=>{j=a.id,g(j)})),(a,e)=>{const s=d,w=p,k=f(n("u-parse"),x),I=f(n("u-icon"),_),z=o;return t(),l(s,{class:"news-detail bg-white"},{default:r((()=>[u(" 标题信心 "),c(s,{class:"news-detail-header py-[20rpx] px-[30rpx]"},{default:r((()=>[c(s,{class:"text-3xl font-medium"},{default:r((()=>[i(m(b.value.title),1)])),_:1}),c(s,{class:"flex mt-[20rpx] text-xs"},{default:r((()=>[b.value.author?(t(),l(s,{key:0,class:"mr-[40rpx]"},{default:r((()=>[i("作者: "+m(b.value.author),1)])),_:1})):u("v-if",!0),c(s,{class:"text-muted mr-[40rpx]"},{default:r((()=>[i(m(b.value.createTime),1)])),_:1}),c(s,{class:"flex items-center text-muted"},{default:r((()=>[c(w,{src:v,class:"w-[30rpx] h-[30rpx]"}),c(s,{class:"ml-[10rpx]"},{default:r((()=>[i(m(b.value.visit),1)])),_:1})])),_:1})])),_:1})])),_:1}),u(" 咨询内容 "),c(s,{class:"news-detail-section bg-white p-[20rpx]"},{default:r((()=>[u(" 摘要 "),b.value.summary?(t(),l(s,{key:0,class:"summary p-[20rpx] text-base"},{default:r((()=>[i(" 摘要: "+m(b.value.summary),1)])),_:1})):u("v-if",!0),u(" 封面 "),b.value.image?(t(),l(s,{key:1,class:"mt-[20rpx]"},{default:r((()=>[c(w,{class:"w-full",src:b.value.image,mode:"widthFix"},null,8,["src"])])),_:1})):u("v-if",!0),u(" 内容 "),c(s,{class:"mt-[20rpx]"},{default:r((()=>[c(k,{html:b.value.content},null,8,["html"])])),_:1})])),_:1}),c(s,{class:"panel-btn flex items-center px-[34rpx]",onClick:e[0]||(e[0]=a=>(async a=>{try{b.value.collect?await h({articleId:a}):await y({articleId:a}),g(j)}catch(e){}})(b.value.id))},{default:r((()=>[c(I,{name:b.value.collect?"star-fill":"star",size:"36"},null,8,["name"]),c(z,{class:"ml-[10rpx]"},{default:r((()=>[i("收藏")])),_:1})])),_:1})])),_:1})}}}),[["__scopeId","data-v-3e7e0c12"]]);export{j as default};
