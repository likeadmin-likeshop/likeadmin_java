import{_ as B}from"./index.6d47c9ed.js";import{I as E,w as V}from"./element-plus.f5eb07a0.js";import b from"./menu.00ecf38c.js";import h from"./preview.02e7bf70.js";import{_ as C}from"./attr-setting.vue_vue_type_script_setup_true_lang.84e61532.js";import{w as F}from"./index.f6838d76.js";import{s as N,a as P}from"./decoration.815a1b2b.js";import{n as k,f as I,d as S}from"./index.64e62b67.js";import{d as v,a0 as T,r as d,e as _,w as A,ag as M,o as g,c as O,V as r,M as n,a as J,u as f,O as U,L as W,T as L}from"./@vue.cab01781.js";import"./@vueuse.724ed0af.js";import"./@element-plus.92b4185f.js";import"./lodash-es.29c53eac.js";import"./dayjs.22a46fd8.js";import"./axios.8058589d.js";import"./async-validator.fb49d0f5.js";import"./@ctrl.82a509e0.js";import"./@popperjs.36402333.js";import"./escape-html.e5dfadb9.js";import"./normalize-wheel-es.8aeb3683.js";import"./lodash.bbe6f09f.js";import"./vue-router.5046cc50.js";import"./pinia.e85e8286.js";import"./vue-demi.bfae2336.js";import"./css-color-function.5bd363c0.js";import"./color.232115c1.js";import"./clone.8f44c0eb.js";import"./color-convert.69e17089.js";import"./color-string.e356f5de.js";import"./color-name.e7a4e1d3.js";import"./nprogress.404eaa9c.js";import"./vue-clipboard3.19ab9072.js";import"./clipboard.6fb7c109.js";import"./echarts.6ad8c478.js";import"./zrender.f91f2f01.js";import"./highlight.js.4ebdf9a4.js";import"./@highlightjs.0ab41b7b.js";import"./attr.vue_vue_type_script_setup_true_lang.9d5db47c.js";import"./index.d1b8bb0d.js";import"./picker.dbcd437c.js";import"./index.136be2f4.js";import"./picker.5d8eed0a.js";import"./index.1866acee.js";import"./usePaging.e2215e88.js";import"./index.vue_vue_type_script_setup_true_lang.2f52dd93.js";import"./vue3-video-play.05975c53.js";import"./vuedraggable.5917840d.js";import"./vue.a15b7233.js";import"./sortablejs.c69601cb.js";import"./content.vue_vue_type_script_setup_true_lang.96010cac.js";import"./decoration-img.4dec7bca.js";import"./attr.vue_vue_type_script_setup_true_lang.7c96b670.js";import"./content.ea750dee.js";import"./attr.vue_vue_type_script_setup_true_lang.2e693200.js";import"./add-nav.vue_vue_type_script_setup_true_lang.8851ce0d.js";import"./content.06cd617c.js";import"./attr.vue_vue_type_script_setup_true_lang.c2c86fa3.js";import"./content.vue_vue_type_script_setup_true_lang.e873fa0c.js";import"./attr.vue_vue_type_script_setup_true_lang.06a2fa18.js";import"./content.0331f6f6.js";import"./attr.vue_vue_type_script_setup_true_lang.0b87e513.js";import"./content.3eb29ea6.js";import"./attr.vue_vue_type_script_setup_true_lang.1024ff07.js";import"./content.vue_vue_type_script_setup_true_lang.217e9a8e.js";import"./attr.vue_vue_type_script_setup_true_lang.0583d8bc.js";import"./content.c073f4f9.js";const $={class:"decoration-pages min-w-[1100px]"},j={class:"flex h-full items-start"},q=L("\u4FDD\u5B58"),z=v({name:"decorationPages"}),G=v({...z,setup(H){const s=e=>e.map(t=>{var p;return{id:k(),...((p=F[t])==null?void 0:p.options())||{}}}),o=T({[1]:{id:1,pageType:1,name:"\u9996\u9875\u88C5\u4FEE",pageData:s(["search","banner","nav","news"])},[2]:{id:2,pageType:2,name:"\u4E2A\u4EBA\u4E2D\u5FC3",pageData:s(["user-info","my-service","user-banner"])},[3]:{id:3,pageType:3,name:"\u5BA2\u670D\u8BBE\u7F6E",pageData:s(["customer-service"])}}),a=d("1"),i=d(-1),u=_(()=>{var e,t;return(t=(e=o[a.value])==null?void 0:e.pageData)!=null?t:[]}),D=_(()=>{var e,t;return(t=(e=o[a.value])==null?void 0:e.pageData[i.value])!=null?t:""}),l=async()=>{const e=await P({id:a.value});o[String(e.id)].pageData=JSON.parse(e.pageData)},x=async()=>{await N({...o[a.value],pageData:JSON.stringify(o[a.value].pageData)}),l(),I.msgSuccess("\u4FDD\u5B58\u6210\u529F")};return A(a,()=>{i.value=u.value.findIndex(e=>!e.disabled),l()},{immediate:!0}),(e,t)=>{const c=E,p=V,w=B,y=M("perms");return g(),O("div",$,[r(c,{shadow:"never",class:"!border-none flex-1 flex","body-style":{flex:1}},{default:n(()=>[J("div",j,[r(b,{modelValue:a.value,"onUpdate:modelValue":t[0]||(t[0]=m=>a.value=m),menus:o},null,8,["modelValue","menus"]),r(h,{modelValue:i.value,"onUpdate:modelValue":t[1]||(t[1]=m=>i.value=m),pageData:f(u)},null,8,["modelValue","pageData"]),r(C,{class:"flex-1",widget:f(D)},null,8,["widget"])])]),_:1}),U((g(),W(w,{class:"mt-4",fixed:!1},{default:n(()=>[r(p,{type:"primary",onClick:x},{default:n(()=>[q]),_:1})]),_:1})),[[y,["decorate:pages:save"]]])])}}});const it=S(G,[["__scopeId","data-v-ed050f9e"]]);export{it as default};