import{_ as C}from"./index.0e26cc80.js";import{C as v,D,I as B,w as E}from"./element-plus.374f5afd.js";import{_ as g}from"./picker.02eb3f61.js";import{a as h,b as A}from"./user.ed84e35d.js";import{f as b}from"./index.541a788f.js";import{d as k,a0 as w,ag as y,o as i,c as V,V as e,M as r,a,O as x,L as S,T as N}from"./@vue.cab01781.js";import"./@vueuse.724ed0af.js";import"./@element-plus.92b4185f.js";import"./lodash-es.29c53eac.js";import"./dayjs.66926594.js";import"./axios.2d915936.js";import"./async-validator.fb49d0f5.js";import"./@ctrl.82a509e0.js";import"./@popperjs.36402333.js";import"./escape-html.e5dfadb9.js";import"./normalize-wheel-es.8aeb3683.js";import"./index.2bd55b1e.js";import"./index.cf4796a3.js";import"./usePaging.bfe23d97.js";import"./index.95e481af.js";import"./vue3-video-play.05975c53.js";import"./vuedraggable.a5db575d.js";import"./vue.de4be77f.js";import"./sortablejs.cd7e2c7e.js";import"./lodash.b68d77aa.js";import"./vue-router.5046cc50.js";import"./pinia.e85e8286.js";import"./vue-demi.bfae2336.js";import"./css-color-function.a8f9466d.js";import"./color.903ca10f.js";import"./clone.9d64bb7a.js";import"./color-convert.69e17089.js";import"./color-string.e356f5de.js";import"./color-name.e7a4e1d3.js";import"./nprogress.a96d99f2.js";import"./vue-clipboard3.91d4fd5f.js";import"./clipboard.c0a70c0c.js";import"./echarts.6ad8c478.js";import"./zrender.f91f2f01.js";import"./highlight.js.4ebdf9a4.js";import"./@highlightjs.0ab41b7b.js";const U={class:"user-setup"},j=a("div",{class:"font-medium mb-7"},"\u57FA\u672C\u8BBE\u7F6E",-1),I=a("div",null,[a("div",{class:"form-tips"}," \u7528\u6237\u6CE8\u518C\u65F6\u7ED9\u7684\u9ED8\u8BA4\u5934\u50CF\uFF0C\u5EFA\u8BAE\u5C3A\u5BF8\uFF1A400*400\u50CF\u7D20\uFF0C\u652F\u6301jpg\uFF0Cjpeg\uFF0Cpng\u683C\u5F0F ")],-1),T=N("\u4FDD\u5B58"),bt=k({__name:"setup",setup(L){const t=w({defaultAvatar:""}),u=async()=>{try{const o=await h();for(const m in t)t[m]=o[m]}catch(o){console.log("\u83B7\u53D6=>",o)}},p=async()=>{try{await A(t),b.msgSuccess("\u64CD\u4F5C\u6210\u529F"),u()}catch(o){console.log("\u4FDD\u5B58=>",o)}};return u(),(o,m)=>{const n=g,s=v,l=D,c=B,_=E,d=C,f=y("perms");return i(),V("div",U,[e(c,{shadow:"never",class:"!border-none"},{default:r(()=>[j,e(l,{ref:"formRef",model:t,"label-width":"120px"},{default:r(()=>[e(s,{label:"\u7528\u6237\u9ED8\u8BA4\u5934\u50CF"},{default:r(()=>[a("div",null,[e(n,{modelValue:t.defaultAvatar,"onUpdate:modelValue":m[0]||(m[0]=F=>t.defaultAvatar=F),limit:1},null,8,["modelValue"])])]),_:1}),e(s,null,{default:r(()=>[I]),_:1})]),_:1},8,["model"])]),_:1}),x((i(),S(d,null,{default:r(()=>[e(_,{type:"primary",onClick:p},{default:r(()=>[T]),_:1})]),_:1})),[[f,["setting:user:save"]]])])}}});export{bt as default};