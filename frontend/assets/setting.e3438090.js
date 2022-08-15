import{_ as V}from"./index.a39a1019.js";import{y as D,x as A,A as b,D as U,t as y}from"./element-plus.68b74f58.js";import{M as h}from"./picker.ed2472f7.js";import{a as k,s as P}from"./index.c7fbbb27.js";import{d as x,r as I,$ as p,o as F,c as M,U as r,L as s,a as m,M as S,K as N,S as q,ad as R}from"./@vue.230e89ba.js";import"./@vueuse.88988742.js";import"./dayjs.66926594.js";import"./axios.2d915936.js";import"./@element-plus.739f7923.js";import"./lodash-es.29c53eac.js";import"./async-validator.fb49d0f5.js";import"./@ctrl.82a509e0.js";import"./@popperjs.36402333.js";import"./escape-html.e5dfadb9.js";import"./normalize-wheel-es.8aeb3683.js";import"./index.8af9b729.js";import"./index.75b18e13.js";import"./usePaging.7a589a5f.js";import"./index.98086480.js";import"./index.vue_vue_type_script_setup_true_lang.c95238d7.js";import"./vue3-video-play.1b377718.js";import"./vuedraggable.611a2338.js";import"./vue.1b105d76.js";import"./sortablejs.cd7e2c7e.js";import"./lodash.b68d77aa.js";import"./vue-router.c0281831.js";import"./pinia.1d1aade0.js";import"./vue-demi.bfae2336.js";import"./css-color-function.a8f9466d.js";import"./color.903ca10f.js";import"./clone.9d64bb7a.js";import"./color-convert.69e17089.js";import"./color-string.e356f5de.js";import"./color-name.e7a4e1d3.js";import"./nprogress.a96d99f2.js";import"./echarts.6ad8c478.js";import"./zrender.f91f2f01.js";import"./highlight.js.4ebdf9a4.js";import"./@highlightjs.b3d01323.js";const K={class:"user-setting"},L={class:"w-80"},T={class:"w-80"},$={class:"w-80"},j={class:"w-80"},z={class:"w-80"},G=q("\u4FDD\u5B58"),xe=x({__name:"setting",setup(H){const n=I(),d=k(),o=p({avatar:"",username:"",nickname:"",currPassword:"",password:"",passwordConfirm:""}),_=p({avatar:[{required:!0,message:"\u5934\u50CF\u4E0D\u80FD\u4E3A\u7A7A",trigger:["change"]}],nickname:[{required:!0,message:"\u8BF7\u8F93\u5165\u540D\u79F0",trigger:["blur"]}],currPassword:[{validator:(a,e,u)=>{o.password&&(e||u(new Error("\u8BF7\u8F93\u5165\u5F53\u524D\u5BC6\u7801"))),u()},trigger:"blur"}],password:[{validator:(a,e,u)=>{o.currPassword&&(e||u(new Error("\u8BF7\u8F93\u5165\u65B0\u7684\u5BC6\u7801"))),u()},trigger:"blur"}],passwordConfirm:[{validator:(a,e,u)=>{o.password&&(e||u(new Error("\u8BF7\u518D\u6B21\u8F93\u5165\u5BC6\u7801")),e!==o.password&&u(new Error("\u4E24\u6B21\u8F93\u5165\u5BC6\u7801\u4E0D\u4E00\u81F4!"))),u()},trigger:"blur"}]}),c=async()=>{const a=d.userInfo;console.log(a);for(const e in o)o[e]=a[e]},f=async()=>{await P(o),d.getUserInfo()},w=async()=>{var a;await((a=n.value)==null?void 0:a.validate()),f()};return c(),(a,e)=>{const u=h,l=D,i=A,B=b,E=U,C=y,g=V,v=R("perms");return F(),M("div",K,[r(E,{class:"!border-none",shadow:"never"},{default:s(()=>[r(B,{ref_key:"formRef",ref:n,class:"ls-form",model:o,rules:_,"label-width":"100px"},{default:s(()=>[r(l,{label:"\u5934\u50CF\uFF1A",prop:"avatar"},{default:s(()=>[r(u,{modelValue:o.avatar,"onUpdate:modelValue":e[0]||(e[0]=t=>o.avatar=t),limit:1},null,8,["modelValue"])]),_:1}),r(l,{label:"\u8D26\u53F7\uFF1A",prop:"username"},{default:s(()=>[m("div",L,[r(i,{modelValue:o.username,"onUpdate:modelValue":e[1]||(e[1]=t=>o.username=t),disabled:""},null,8,["modelValue"])])]),_:1}),r(l,{label:"\u540D\u79F0\uFF1A",prop:"nickname"},{default:s(()=>[m("div",T,[r(i,{modelValue:o.nickname,"onUpdate:modelValue":e[2]||(e[2]=t=>o.nickname=t),placeholder:"\u8BF7\u8F93\u5165\u540D\u79F0"},null,8,["modelValue"])])]),_:1}),r(l,{label:"\u5F53\u524D\u5BC6\u7801\uFF1A",prop:"currPassword"},{default:s(()=>[m("div",$,[r(i,{modelValue:o.currPassword,"onUpdate:modelValue":e[3]||(e[3]=t=>o.currPassword=t),modelModifiers:{trim:!0},placeholder:"\u4FEE\u6539\u5BC6\u7801\u65F6\u5FC5\u586B, \u4E0D\u4FEE\u6539\u5BC6\u7801\u65F6\u7559\u7A7A",type:"password","show-password":""},null,8,["modelValue"])])]),_:1}),r(l,{label:"\u65B0\u7684\u5BC6\u7801\uFF1A",prop:"password"},{default:s(()=>[m("div",j,[r(i,{modelValue:o.password,"onUpdate:modelValue":e[4]||(e[4]=t=>o.password=t),modelModifiers:{trim:!0},placeholder:"\u4FEE\u6539\u5BC6\u7801\u65F6\u5FC5\u586B, \u4E0D\u4FEE\u6539\u5BC6\u7801\u65F6\u7559\u7A7A",type:"password","show-password":""},null,8,["modelValue"])])]),_:1}),r(l,{label:"\u786E\u5B9A\u5BC6\u7801\uFF1A",prop:"passwordConfirm"},{default:s(()=>[m("div",z,[r(i,{modelValue:o.passwordConfirm,"onUpdate:modelValue":e[5]||(e[5]=t=>o.passwordConfirm=t),modelModifiers:{trim:!0},placeholder:"\u4FEE\u6539\u5BC6\u7801\u65F6\u5FC5\u586B, \u4E0D\u4FEE\u6539\u5BC6\u7801\u65F6\u7559\u7A7A",type:"password","show-password":""},null,8,["modelValue"])])]),_:1})]),_:1},8,["model","rules"])]),_:1}),S((F(),N(g,null,{default:s(()=>[r(C,{type:"primary",onClick:w},{default:s(()=>[G]),_:1})]),_:1})),[[v,["setting.web.web_setting/setAgreement"]]])])}}});export{xe as default};