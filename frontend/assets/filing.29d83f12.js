import{_ as V}from"./index.52704a45.js";import{C as y,D as A,w as x,F as I,I as U}from"./element-plus.151049e5.js";import{f as _,b as N}from"./index.7e85ebc5.js";import{_ as L}from"./index.ba340ca4.js";import{g as P,s as S}from"./website.cf64968b.js";import{d as f,r as T,ag as $,o as s,c as d,V as e,M as o,W as M,a8 as O,L as F,a as t,O as W,T as h}from"./@vue.cab01781.js";import"./@vueuse.724ed0af.js";import"./@element-plus.92b4185f.js";import"./lodash-es.29c53eac.js";import"./dayjs.66926594.js";import"./axios.2d915936.js";import"./async-validator.fb49d0f5.js";import"./@ctrl.82a509e0.js";import"./@popperjs.36402333.js";import"./escape-html.e5dfadb9.js";import"./normalize-wheel-es.8aeb3683.js";import"./lodash.b68d77aa.js";import"./vue-router.5046cc50.js";import"./pinia.e85e8286.js";import"./vue-demi.bfae2336.js";import"./css-color-function.a8f9466d.js";import"./color.903ca10f.js";import"./clone.9d64bb7a.js";import"./color-convert.69e17089.js";import"./color-string.e356f5de.js";import"./color-name.e7a4e1d3.js";import"./nprogress.a96d99f2.js";import"./vue-clipboard3.91d4fd5f.js";import"./clipboard.c0a70c0c.js";import"./echarts.6ad8c478.js";import"./zrender.f91f2f01.js";import"./highlight.js.4ebdf9a4.js";import"./@highlightjs.0ab41b7b.js";const j={class:"website-filing"},q=t("div",{class:"mb-5"},"\u7AD9\u70B9\u5E95\u90E8\u7248\u6743\u5907\u6848\u4FE1\u606F\u8BBE\u7F6E",-1),z={class:"bg-fill-lighter py-4"},G={class:"w-80"},H={class:"w-80"},J=t("div",{class:"form-tips"},"\u8DF3\u8F6C\u94FE\u63A5\u4E0D\u8BBE\u7F6E\uFF0C\u5219\u4E0D\u8DF3\u8F6C",-1),K=h(" \u6DFB\u52A0 "),Q=h("\u4FDD\u5B58"),R=f({name:"webFilling"}),Ue=f({...R,setup(X){const u=T([{name:"",link:""}]),r=async()=>{const n=await P();!n.length||(u.value=n)},v=()=>{u.value.push({name:"",link:""})},E=n=>{if(u.value.length<=1)return _.msgError("\u81F3\u5C11\u4FDD\u7559\u4E00\u4E2A");u.value.splice(n,1)},g=async()=>{await S(u.value),_.msgSuccess("\u64CD\u4F5C\u6210\u529F"),r()};return r(),(n,Y)=>{const i=y,m=A,B=L,C=N,p=x,D=I,w=U,b=V,k=$("perms");return s(),d("div",j,[e(w,{shadow:"never",class:"!border-none"},{default:o(()=>[q,e(D,{ref:"form",class:"ls-form","label-width":"100px"},{default:o(()=>[(s(!0),d(M,null,O(u.value,(a,c)=>(s(),F(B,{class:"mb-4",key:c,"show-close":u.value.length>1,onClose:l=>E(c)},{default:o(()=>[t("div",z,[e(m,{label:"\u663E\u793A\u5185\u5BB9",prop:"name"},{default:o(()=>[t("div",G,[t("div",null,[e(i,{modelValue:a.name,"onUpdate:modelValue":l=>a.name=l,placeholder:"\u8BF7\u8F93\u5165\u5185\u5BB9 \uFF0C\u4F8B\u5982\uFF1A ICP\u5907\u6848\u53F7"},null,8,["modelValue","onUpdate:modelValue"])])])]),_:2},1024),e(m,{label:"\u8DF3\u8F6C\u94FE\u63A5",prop:"link"},{default:o(()=>[t("div",H,[t("div",null,[e(i,{modelValue:a.link,"onUpdate:modelValue":l=>a.link=l,placeholder:"\u8BF7\u8F93\u5165\u94FE\u63A5\uFF0C\u4F8B\u5982\uFF1Ahttp://www.beian.gov.cn"},null,8,["modelValue","onUpdate:modelValue"])]),J])]),_:2},1024)])]),_:2},1032,["show-close","onClose"]))),128)),t("div",null,[e(p,{type:"primary",onClick:v},{default:o(()=>[e(C,{name:"el-icon-Plus"}),K]),_:1})])]),_:1},512)]),_:1}),W((s(),F(b,null,{default:o(()=>[e(p,{type:"primary",onClick:g},{default:o(()=>[Q]),_:1})]),_:1})),[[k,["setting:copyright:save"]]])])}}});export{Ue as default};