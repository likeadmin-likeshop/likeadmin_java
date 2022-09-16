import{_ as y}from"./index.725cb2a2.js";import{I as B,w as V}from"./element-plus.374f5afd.js";import E from"./menu.d6a87683.js";import b from"./preview.17c323a9.js";import{_ as h}from"./attr-setting.vue_vue_type_script_setup_true_lang.f41c5cf3.js";import{w as C}from"./index.4b56f13f.js";import{s as N,a as k}from"./decoration.83857d56.js";import{l as F,f as I,d as P}from"./index.194b16de.js";import{d as S,a0 as T,r as d,e as _,w as A,ag as M,o as g,c as O,V as r,M as n,a as J,u as f,O as U,L as W,T as L}from"./@vue.cab01781.js";import"./@vueuse.724ed0af.js";import"./@element-plus.92b4185f.js";import"./lodash-es.29c53eac.js";import"./dayjs.66926594.js";import"./axios.2d915936.js";import"./async-validator.fb49d0f5.js";import"./@ctrl.82a509e0.js";import"./@popperjs.36402333.js";import"./escape-html.e5dfadb9.js";import"./normalize-wheel-es.8aeb3683.js";import"./lodash.b68d77aa.js";import"./vue-router.5046cc50.js";import"./pinia.e85e8286.js";import"./vue-demi.bfae2336.js";import"./css-color-function.a8f9466d.js";import"./color.903ca10f.js";import"./clone.9d64bb7a.js";import"./color-convert.69e17089.js";import"./color-string.e356f5de.js";import"./color-name.e7a4e1d3.js";import"./nprogress.a96d99f2.js";import"./vue-clipboard3.91d4fd5f.js";import"./clipboard.c0a70c0c.js";import"./echarts.6ad8c478.js";import"./zrender.f91f2f01.js";import"./highlight.js.4ebdf9a4.js";import"./@highlightjs.0ab41b7b.js";import"./attr.vue_vue_type_script_setup_true_lang.657d8c98.js";import"./index.af9cbf12.js";import"./picker.32e5fd3f.js";import"./index.b3f0a6a3.js";import"./picker.9f29eafd.js";import"./index.d438bf9c.js";import"./usePaging.bfe23d97.js";import"./vue3-video-play.05975c53.js";import"./vuedraggable.a5db575d.js";import"./vue.de4be77f.js";import"./sortablejs.cd7e2c7e.js";import"./content.vue_vue_type_script_setup_true_lang.42552d36.js";import"./decoration-img.8eefe9ea.js";import"./attr.vue_vue_type_script_setup_true_lang.fbe9714b.js";import"./content.48cafc6f.js";import"./attr.vue_vue_type_script_setup_true_lang.9b4c7a4c.js";import"./add-nav.vue_vue_type_script_setup_true_lang.f97c27f3.js";import"./content.f61021fb.js";import"./attr.vue_vue_type_script_setup_true_lang.688715fa.js";import"./content.vue_vue_type_script_setup_true_lang.16664998.js";import"./attr.vue_vue_type_script_setup_true_lang.06a2fa18.js";import"./content.b422a8aa.js";import"./attr.vue_vue_type_script_setup_true_lang.0b87e513.js";import"./content.6de7b310.js";import"./attr.vue_vue_type_script_setup_true_lang.ee1a2f4c.js";import"./content.vue_vue_type_script_setup_true_lang.0a04c032.js";import"./attr.vue_vue_type_script_setup_true_lang.0583d8bc.js";import"./content.b63c1c8a.js";const $={class:"decoration-pages min-w-[1100px]"},j={class:"flex h-full items-start"},q=L("\u4FDD\u5B58"),z=S({__name:"index",setup(G){const s=e=>e.map(t=>{var p;return{id:F(),...((p=C[t])==null?void 0:p.options())||{}}}),o=T({[1]:{id:1,pageType:1,name:"\u5546\u57CE\u9996\u9875",pageData:s(["search","banner","nav","news"])},[2]:{id:2,pageType:2,name:"\u4E2A\u4EBA\u4E2D\u5FC3",pageData:s(["user-info","my-service","user-banner"])},[3]:{id:3,pageType:3,name:"\u5BA2\u670D\u8BBE\u7F6E",pageData:s(["customer-service"])}}),a=d("1"),i=d(-1),u=_(()=>{var e,t;return(t=(e=o[a.value])==null?void 0:e.pageData)!=null?t:[]}),v=_(()=>{var e,t;return(t=(e=o[a.value])==null?void 0:e.pageData[i.value])!=null?t:""}),l=async()=>{const e=await k({id:a.value});o[String(e.id)].pageData=JSON.parse(e.pageData)},D=async()=>{await N({...o[a.value],pageData:JSON.stringify(o[a.value].pageData)}),l(),I.msgSuccess("\u4FDD\u5B58\u6210\u529F")};return A(a,()=>{i.value=u.value.findIndex(e=>!e.disabled),l()},{immediate:!0}),(e,t)=>{const c=B,p=V,x=y,w=M("perms");return g(),O("div",$,[r(c,{shadow:"never",class:"!border-none flex-1 flex","body-style":{flex:1}},{default:n(()=>[J("div",j,[r(E,{modelValue:a.value,"onUpdate:modelValue":t[0]||(t[0]=m=>a.value=m),menus:o},null,8,["modelValue","menus"]),r(b,{modelValue:i.value,"onUpdate:modelValue":t[1]||(t[1]=m=>i.value=m),pageData:f(u)},null,8,["modelValue","pageData"]),r(h,{class:"flex-1",widget:f(v)},null,8,["widget"])])]),_:1}),U((g(),W(x,{class:"mt-4",fixed:!1},{default:n(()=>[r(p,{type:"primary",onClick:D},{default:n(()=>[q]),_:1})]),_:1})),[[w,["decorate:pages:save"]]])])}}});const ot=P(z,[["__scopeId","data-v-77ef06d2"]]);export{ot as default};