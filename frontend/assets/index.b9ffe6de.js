import{Z as b,H as k,u as w,I as y,G as C,J as B}from"./element-plus.62f8b726.js";import{d as x}from"./message.8cd09dd1.js";import{_ as D}from"./edit.vue_vue_type_script_setup_true_lang.72f26945.js";import{d as F,s as T,a0 as L,ag as R,o,c as V,O as d,L as i,M as t,V as a,T as m}from"./@vue.cab01781.js";import"./@vueuse.724ed0af.js";import"./@element-plus.92b4185f.js";import"./lodash-es.29c53eac.js";import"./dayjs.66926594.js";import"./axios.2d915936.js";import"./async-validator.fb49d0f5.js";import"./@ctrl.82a509e0.js";import"./@popperjs.36402333.js";import"./escape-html.e5dfadb9.js";import"./normalize-wheel-es.8aeb3683.js";import"./index.ccc78a7a.js";import"./lodash.b68d77aa.js";import"./vue-router.5046cc50.js";import"./pinia.e85e8286.js";import"./vue-demi.bfae2336.js";import"./css-color-function.a8f9466d.js";import"./color.903ca10f.js";import"./clone.9d64bb7a.js";import"./color-convert.69e17089.js";import"./color-string.e356f5de.js";import"./color-name.e7a4e1d3.js";import"./nprogress.a96d99f2.js";import"./echarts.6ad8c478.js";import"./zrender.f91f2f01.js";import"./highlight.js.4ebdf9a4.js";import"./@highlightjs.0ab41b7b.js";import"./index.fd3131b6.js";const N=m("\u5F00\u542F"),S=m("\u5173\u95ED"),$=m(" \u8BBE\u7F6E "),ft=F({__name:"index",setup(z){const p=T(),e=L({loading:!1,lists:[]}),c=async()=>{try{e.loading=!0,e.lists=await x(),e.loading=!1}catch{e.loading=!1}},u=s=>{var r;(r=p.value)==null||r.open(s)};return c(),(s,r)=>{const n=k,_=b,f=w,g=y,h=C,E=R("perms"),v=B;return o(),V("div",null,[d((o(),i(h,{class:"!border-none",shadow:"never"},{default:t(()=>[a(g,{size:"large",data:e.lists},{default:t(()=>[a(n,{label:"\u77ED\u4FE1\u6E20\u9053",prop:"name","min-width":"120"}),a(n,{label:"\u72B6\u6001","min-width":"120"},{default:t(({row:l})=>[l.status==1?(o(),i(_,{key:0},{default:t(()=>[N]),_:1})):(o(),i(_,{key:1,type:"danger"},{default:t(()=>[S]),_:1}))]),_:1}),a(n,{label:"\u64CD\u4F5C","min-width":"120",fixed:"right"},{default:t(({row:l})=>[d((o(),i(f,{type:"primary",link:"",onClick:G=>u(l.alias)},{default:t(()=>[$]),_:2},1032,["onClick"])),[[E,["setting:storage:edit"]]])]),_:1})]),_:1},8,["data"])]),_:1})),[[v,e.loading]]),a(D,{ref_key:"editRef",ref:p,onSuccess:c},null,512)])}}});export{ft as default};