import{_ as T}from"./index.7112fdf6.js";import{r as C,f as H,b as N,d as R}from"./index.66650ce1.js";import{O as z,P as L,C as O,D as $,I as q,w as G,J,B as K,K as M}from"./element-plus.374f5afd.js";import{d as P,a0 as W,e as j,ag as Q,o as u,c as p,V as e,M as t,a as s,T as c,U as E,O as F,L as g,W as X,a8 as Y,u as Z,b9 as ee,b8 as te}from"./@vue.cab01781.js";import"./@vueuse.724ed0af.js";import"./lodash.b68d77aa.js";import"./axios.2d915936.js";import"./vue-router.5046cc50.js";import"./pinia.e85e8286.js";import"./vue-demi.bfae2336.js";import"./css-color-function.a8f9466d.js";import"./color.903ca10f.js";import"./clone.9d64bb7a.js";import"./color-convert.69e17089.js";import"./color-string.e356f5de.js";import"./color-name.e7a4e1d3.js";import"./@element-plus.92b4185f.js";import"./nprogress.a96d99f2.js";import"./vue-clipboard3.91d4fd5f.js";import"./clipboard.c0a70c0c.js";import"./echarts.6ad8c478.js";import"./zrender.f91f2f01.js";import"./highlight.js.4ebdf9a4.js";import"./@highlightjs.0ab41b7b.js";import"./lodash-es.29c53eac.js";import"./dayjs.66926594.js";import"./async-validator.fb49d0f5.js";import"./@ctrl.82a509e0.js";import"./@popperjs.36402333.js";import"./escape-html.e5dfadb9.js";import"./normalize-wheel-es.8aeb3683.js";function oe(){return C.get({url:"/setting/search/detail"})}function se(r){return C.post({url:"/setting/search/save",params:r})}const _=r=>(ee("data-v-7f49ea02"),r=r(),te(),r),ae={class:"hot-search"},ne=c("\u5F00\u542F"),le=c("\u5173\u95ED"),re=_(()=>s("div",{class:"form-tips"},"\u9ED8\u8BA4\u5F00\u59CB\uFF0C\u5173\u95ED\u5219\u524D\u7AEF\u4E0D\u663E\u793A\u8BE5\u529F\u80FD",-1)),ie={class:"flex"},ce={class:"flex-1 w-3/5"},ue=c("\u6DFB\u52A0"),de=c(" \u5220\u9664 "),_e={class:"w-2/5 hot-search-phone"},me=_(()=>s("span",{class:"mb-4"},"- \u70ED\u641C\u9884\u89C8\u56FE -",-1)),pe={class:"hot-search-phone-content"},he={class:"search-com"},fe={class:"search-con flex items-center px-[15px]"},De=_(()=>s("span",{class:"ml-[5px]"},"\u8BF7\u8F93\u5165\u5173\u952E\u8BCD\u641C\u7D22",-1)),be=_(()=>s("div",{class:"hot-search-title"},"\u70ED\u95E8\u641C\u7D22",-1)),ve={class:"hot-search-text"},Ee=c("\u4FDD\u5B58"),Fe=P({__name:"index",setup(r){const a=W({isHotSearch:1,list:[]}),x=j(()=>[...a.list].sort((n,l)=>l.sort-n.sort)),h=async()=>{try{const n=await oe();for(const l in a)a[l]=n[l]}catch(n){console.log("\u83B7\u53D6=>",n)}},B=()=>{a.list.push({name:"\u5173\u952E\u5B57",sort:0})},y=n=>{a.list.splice(n,1)},V=async()=>{try{await se(a),H.msgSuccess("\u64CD\u4F5C\u6210\u529F"),h()}catch(n){console.log("\u4FDD\u5B58=>",n)}};return h(),(n,l)=>{const f=z,w=L,S=O,k=$,D=q,m=G,d=J,b=K,I=M,U=N,A=T,v=Q("perms");return u(),p("div",ae,[e(D,{class:"!border-none",shadow:"never"},{default:t(()=>[e(k,{ref:"formRef",model:a,"label-width":"100px"},{default:t(()=>[e(S,{label:"\u529F\u80FD\u72B6\u6001",style:{"margin-bottom":"0"}},{default:t(()=>[s("div",null,[e(w,{modelValue:a.isHotSearch,"onUpdate:modelValue":l[0]||(l[0]=o=>a.isHotSearch=o)},{default:t(()=>[e(f,{label:1},{default:t(()=>[ne]),_:1}),e(f,{label:0},{default:t(()=>[le]),_:1})]),_:1},8,["modelValue"]),re])]),_:1})]),_:1},8,["model"])]),_:1}),e(D,{class:"!border-none mt-4",shadow:"never"},{default:t(()=>[s("div",ie,[s("div",ce,[e(m,{type:"primary",class:"mb-4",onClick:B},{default:t(()=>[ue]),_:1}),e(I,{size:"large",data:a.list},{default:t(()=>[e(d,{label:"ID",prop:"id",width:"120"},{default:t(({$index:o})=>[c(E(o),1)]),_:1}),e(d,{label:"\u5173\u952E\u8BCD",prop:"describe","min-width":"160"},{default:t(({row:o})=>[e(b,{modelValue:o.name,"onUpdate:modelValue":i=>o.name=i,clearable:""},null,8,["modelValue","onUpdate:modelValue"])]),_:1}),e(d,{label:"\u6392\u5E8F",prop:"describe","min-width":"160"},{default:t(({row:o})=>[e(b,{modelValue:o.sort,"onUpdate:modelValue":i=>o.sort=i,type:"number"},null,8,["modelValue","onUpdate:modelValue"])]),_:1}),e(d,{label:"\u64CD\u4F5C","min-width":"80",fixed:"right"},{default:t(({$index:o})=>[F((u(),g(m,{type:"danger",link:"",onClick:i=>y(o)},{default:t(()=>[de]),_:2},1032,["onClick"])),[[v,["setting:storage:edit"]]])]),_:1})]),_:1},8,["data"])]),s("div",_e,[me,s("div",pe,[s("div",he,[s("div",fe,[e(U,{name:"el-icon-Search",size:17}),De])]),be,s("div",ve,[(u(!0),p(X,null,Y(Z(x),(o,i)=>(u(),p("span",{key:i},E(o.name),1))),128))])])])])]),_:1}),F((u(),g(A,null,{default:t(()=>[e(m,{type:"primary",onClick:V},{default:t(()=>[Ee]),_:1})]),_:1})),[[v,["setting:search:save"]]])])}}});const Ze=R(Fe,[["__scopeId","data-v-7f49ea02"]]);export{Ze as default};