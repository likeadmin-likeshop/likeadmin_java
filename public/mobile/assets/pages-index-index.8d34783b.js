import{_ as e}from"./u-search.22c1e2b1.js";import{r as t,a,o as s,c as n,w as l,b as r,i as c,d as i,u as o,e as d,f as u,g as p,F as f,h as m,S as y,j as h,k as _,l as g,t as b,m as x}from"./index.6c00075c.js";import{_ as v}from"./plugin-vue_export-helper.21dcd24c.js";import{_ as w}from"./u-image.02e8c068.js";import{n as k,_ as j}from"./tabbar.54f2569f.js";import{_ as O}from"./news-card.094940d7.js";import{g as C}from"./shop.52fdb4ef.js";import"./u-icon.7c234862.js";import"./icon_visit.9ad00ab7.js";var I=v({},[["render",function(i,o){const d=t(a("u-search"),e),u=c;return s(),n(u,{url:"/pages/search/search",class:"search px-[24rpx] py-[14rpx] bg-white","hover-class":"none"},{default:l((()=>[r(d,{placeholder:"请输入关键词搜索",disabled:"","show-action":!1})])),_:1})}]]);const F=i({__name:"banner",props:{content:{type:Object,default:()=>({})},styles:{type:Object,default:()=>({})}},setup(e){const{getImageUrl:c}=o();return(i,o)=>{const g=t(a("u-image"),w),b=y,x=h,v=_;return e.content.data.length&&e.content.enabled?(s(),n(v,{key:0,class:"banner h-[340rpx] bg-white translate-y-0"},{default:l((()=>[r(x,{class:"swiper h-full","indicator-dots":e.content.data.length>1,"indicator-active-color":"#4173ff",autoplay:!0},{default:l((()=>[(s(!0),d(f,null,u(e.content.data,((e,t)=>(s(),n(b,{key:t,onClick:t=>{return a=e.link,void k(a);var a}},{default:l((()=>[r(g,{mode:"aspectFit",width:"100%",height:"100%",src:p(c)(e.image)},null,8,["src"])])),_:2},1032,["onClick"])))),128))])),_:1},8,["indicator-dots"])])),_:1})):m("v-if",!0)}}}),S=i({__name:"nav",props:{content:{type:Object,default:()=>({})},styles:{type:Object,default:()=>({})}},setup(e){const{getImageUrl:c}=o();return(i,o)=>{const y=t(a("u-image"),w),h=_;return e.content.data.length&&e.content.enabled?(s(),n(h,{key:0,class:"nav pt-[30rpx] pb-[16rpx] bg-white"},{default:l((()=>[r(h,{class:"nav-item flex flex-wrap"},{default:l((()=>[(s(!0),d(f,null,u(e.content.data,((e,t)=>(s(),n(h,{key:t,class:"flex flex-col items-center w-1/5 mb-[30rpx]",onClick:t=>{return a=e.link,void k(a);var a}},{default:l((()=>[r(y,{width:"41px",height:"41px",src:p(c)(e.image),alt:""},null,8,["src"]),r(h,{class:"mt-[14rpx]"},{default:l((()=>[g(b(e.name),1)])),_:2},1024)])),_:2},1032,["onClick"])))),128))])),_:1})])),_:1})):m("v-if",!0)}}});var U=v(i({__name:"index",setup(e){const c=x({pages:[],article:[]});return(async()=>{const e=await C();c.pages=JSON.parse(e.pages),c.article=e.article})(),(e,i)=>{const o=t(a("w-search"),I),p=t(a("w-banner"),F),y=t(a("w-nav"),S),h=_,b=t(a("news-card"),O),x=t(a("tabbar"),j);return s(),n(h,{class:"index"},{default:l((()=>[(s(!0),d(f,null,u(c.pages,((e,t)=>(s(),n(h,{key:t},{default:l((()=>["search"==e.name?(s(),n(o,{key:0,content:e.content,styles:e.styles},null,8,["content","styles"])):m("v-if",!0),"banner"==e.name?(s(),n(p,{key:1,content:e.content,styles:e.styles},null,8,["content","styles"])):m("v-if",!0),"nav"==e.name?(s(),n(y,{key:2,content:e.content,styles:e.styles},null,8,["content","styles"])):m("v-if",!0)])),_:2},1024)))),128)),c.article.length?(s(),n(h,{key:0,class:"article"},{default:l((()=>[r(h,{class:"flex items-center article-title mx-[20rpx] my-[30rpx] text-2xl font-medium"},{default:l((()=>[g(" 最新资讯 ")])),_:1}),(s(!0),d(f,null,u(c.article,(e=>(s(),n(b,{key:e.id,"news-id":e.id,item:e},null,8,["news-id","item"])))),128))])),_:1})):m("v-if",!0),r(x)])),_:1})}}}),[["__scopeId","data-v-2e305e2b"]]);export{U as default};
