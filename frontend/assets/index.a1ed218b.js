var C=Object.defineProperty;var n=Object.getOwnPropertySymbols;var B=Object.prototype.hasOwnProperty,g=Object.prototype.propertyIsEnumerable;var r=(a,e,t)=>e in a?C(a,e,{enumerable:!0,configurable:!0,writable:!0,value:t}):a[e]=t,m=(a,e)=>{for(var t in e||(e={}))B.call(e,t)&&r(a,t,e[t]);if(n)for(var t of n(e))g.call(e,t)&&r(a,t,e[t]);return a};import{f as h,g as w}from"./setting.a61d1d28.js";import{m as E}from"./index.1e3c2ce4.js";import{F as A}from"./index.6b2980a1.js";import{d as V,v as b,r as s,a as x,i as o,w as l,F as U,o as y,f as i,j as D}from"./vendor.bbaa8c82.js";import"./index.83647b46.js";import"./index.7665392c.js";import"./index.dc22354e.js";const k=i("span",null," \u57FA\u672C\u8BBE\u7F6E ",-1),N=i("div",{class:"muted"},"\u5EFA\u8BAE\u5C3A\u5BF8\uFF1A400*400\uFF0C\u652F\u6301jpg\u3001png\u683C\u5F0F",-1),S=D("\u4FDD\u5B58"),K=V({setup(a){const e=b({default_avatar:"",wechat:""}),t=async()=>{e.value=await h()},d=async()=>{await w(m({},e.value)),t()},_=()=>{d()};return t(),(j,u)=>{const c=s("el-form-item"),f=s("el-form"),p=s("el-card"),F=s("el-button");return y(),x(U,null,[o(p,{shadow:"never",class:"m-t-16"},{header:l(()=>[k]),default:l(()=>[o(f,{ref:"formRef",model:e.value,"label-width":"100px",size:"small"},{default:l(()=>[o(c,{label:"\u7528\u6237\u9ED8\u8BA4\u5934\u50CF",prop:"default_avatar"},{default:l(()=>[o(E,{modelValue:e.value.default_avatar,"onUpdate:modelValue":u[0]||(u[0]=v=>e.value.default_avatar=v),limit:1},null,8,["modelValue"]),N]),_:1})]),_:1},8,["model"])]),_:1}),o(A,null,{default:l(()=>[o(F,{type:"primary",size:"small",onClick:_},{default:l(()=>[S]),_:1})]),_:1})],64)}}});export{K as default};
