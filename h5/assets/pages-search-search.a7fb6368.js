import{d as e,o as a,c as s,w as t,h as l,b as r,l as c,e as o,f as h,t as u,F as i,k as n,m as d,z as p,X as _,ag as f,aE as m,aF as g,aG as x,at as y,r as v,a as w}from"./index.32331f76.js";import{_ as k}from"./u-search.9438f7cc.js";import{_ as b}from"./news-card.bcc0f7e8.js";import{_ as j}from"./z-paging.4d918537.js";import{_ as C}from"./plugin-vue_export-helper.21dcd24c.js";import{b as V,c as z}from"./shop.c14561a0.js";import"./u-icon.fe371d73.js";import"./u-image.8b817e21.js";import"./icon_visit.5f8ba9a3.js";var I=C(e({__name:"suggest",props:{hot_search:{default:[]},his_search:{default:[]}},emits:["search","clear"],setup(e,{emit:d}){const p=e=>{d("search",e)};return(_,f)=>{const m=n;return a(),s(m,{class:"suggest bg-white"},{default:t((()=>[l(" 热门搜索 "),e.hot_search.length?(a(),s(m,{key:0,class:"hot"},{default:t((()=>[r(m,{class:"font-medium pl-[24rpx] pt-[26rpx] pb-[6rpx] text-lg"},{default:t((()=>[c("热门搜索")])),_:1}),r(m,{class:"w-full px-[24rpx]"},{default:t((()=>[(a(!0),o(i,null,h(e.hot_search,((e,l)=>(a(),s(m,{key:l,class:"keyword truncate max-w-full",onClick:a=>p(e)},{default:t((()=>[c(u(e),1)])),_:2},1032,["onClick"])))),128))])),_:1})])),_:1})):l("v-if",!0),e.hot_search.length&&e.his_search.length?(a(),s(m,{key:1,class:"mx-[24rpx] my-[40rpx] border-b border-solid border-0 border-light"})):l("v-if",!0),l(" 历史搜索 "),e.his_search.length?(a(),s(m,{key:2,class:"history"},{default:t((()=>[r(m,{class:"flex justify-between px-[24rpx] pb-[6rpx] pt-[26rpx]"},{default:t((()=>[r(m,{class:"text-lg font-medium"},{default:t((()=>[c("历史搜索")])),_:1}),r(m,{class:"text-xs text-muted",onClick:f[0]||(f[0]=()=>d("clear"))},{default:t((()=>[c("清空")])),_:1})])),_:1}),r(m,{class:"w-full px-[24rpx]"},{default:t((()=>[(a(!0),o(i,null,h(e.his_search,((e,l)=>(a(),s(m,{key:l,class:"keyword truncate",onClick:a=>p(e)},{default:t((()=>[c(u(e),1)])),_:2},1032,["onClick"])))),128))])),_:1})])),_:1})):l("v-if",!0)])),_:1})}}}),[["__scopeId","data-v-77e692a1"]]);var S=C(e({__name:"search",setup(e){const c=d({hot_search:[],his_search:[],result:[],searching:!1}),u=p(""),C=_(),S=e=>{u.value=e,u.value&&(c.his_search.includes(u.value)||(c.his_search.unshift(u.value),f.set(m,c.his_search))),C.value.reload(),c.searching=!0},F=async()=>{(await y({title:"温馨提示",content:"是否清空历史记录？"})).confirm&&(f.set(m,""),c.his_search=[])},U=async(e,a)=>{try{const{lists:s}=await z({keyword:u.value,pageNo:e,pageSize:a});C.value.complete(s)}catch(s){console.log("报错=>",s),C.value.complete(!1)}};return(async()=>{try{c.hot_search=await V()}catch(e){console.log("获取热门搜索失败=>",e)}})(),c.his_search=f.get(m)||[],(e,d)=>{const p=v(w("u-search"),k),_=n,f=v(w("news-card"),b),m=v(w("z-paging"),j);return a(),s(_,{class:"search"},{default:t((()=>[l(" 搜索框 "),r(_,{class:"px-[24rpx] py-[14rpx] bg-white"},{default:t((()=>[r(p,{modelValue:u.value,"onUpdate:modelValue":d[0]||(d[0]=e=>u.value=e),placeholder:"请输入关键词搜索",height:"72",onSearch:S,onCustom:S,onClear:d[1]||(d[1]=e=>c.searching=!1)},null,8,["modelValue"])])),_:1}),l(" 搜索 "),r(_,{class:"search-content"},{default:t((()=>[l("  "),g(r(I,{onSearch:S,onClear:F,hot_search:c.hot_search,his_search:c.his_search},null,8,["hot_search","his_search"]),[[x,!c.searching]]),l("  "),g(r(_,{class:"search-content-s pt-[20rpx]"},{default:t((()=>[r(m,{ref_key:"paging",ref:C,modelValue:c.result,"onUpdate:modelValue":d[2]||(d[2]=e=>c.result=e),onQuery:U,fixed:!1,height:"100%"},{default:t((()=>[(a(!0),o(i,null,h(c.result,((e,t)=>(a(),s(f,{key:e.id,item:e,newsId:e.id},null,8,["item","newsId"])))),128))])),_:1},8,["modelValue"])])),_:1},512),[[x,c.searching]])])),_:1})])),_:1})}}}),[["__scopeId","data-v-629a231d"]]);export{S as default};