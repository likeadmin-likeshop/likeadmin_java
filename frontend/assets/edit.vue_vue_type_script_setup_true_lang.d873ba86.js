import{O as k,P as w,B,C as x,v as y,D as R}from"./element-plus.374f5afd.js";import{a as g,c as h,b as I}from"./role.b3df92ef.js";import{P as U}from"./index.2bd55b1e.js";import{f as N}from"./index.541a788f.js";import{d as P,s as c,r as T,e as S,a0 as j,o as q,c as A,V as a,M as t,u as G,T as _}from"./@vue.cab01781.js";const M={class:"edit-popup"},O=_("\u6B63\u5E38"),z=_("\u505C\u7528"),Z=P({__name:"edit",emits:["success","close"],setup(H,{expose:f,emit:m}){const p=c(),r=c(),d=T("add"),F=S(()=>d.value=="edit"?"\u7F16\u8F91\u89D2\u8272":"\u65B0\u589E\u89D2\u8272"),o=j({id:"",name:"",remark:"",sort:0,isDisable:0,menus:[]}),b={name:[{required:!0,message:"\u8BF7\u8F93\u5165\u540D\u79F0",trigger:["blur"]}]},V=async()=>{var e,l;await((e=p.value)==null?void 0:e.validate());const u={...o,menuIds:o.menus.join()};d.value=="edit"?await g(u):await h(u),(l=r.value)==null||l.close(),N.msgSuccess("\u64CD\u4F5C\u6210\u529F"),m("success")},D=()=>{m("close")};return f({open:(u="add")=>{var e;d.value=u,(e=r.value)==null||e.open()},setFormData:async u=>{const e=await I({id:u.id});for(const l in o)e[l]!=null&&e[l]!=null&&(o[l]=e[l])}}),(u,e)=>{const l=B,n=x,E=y,i=k,v=w,C=R;return q(),A("div",M,[a(U,{ref_key:"popupRef",ref:r,title:G(F),async:!0,width:"550px",onConfirm:V,onClose:D},{default:t(()=>[a(C,{class:"ls-form",ref_key:"formRef",ref:p,rules:b,model:o,"label-width":"60px"},{default:t(()=>[a(n,{label:"\u540D\u79F0",prop:"name"},{default:t(()=>[a(l,{class:"ls-input",modelValue:o.name,"onUpdate:modelValue":e[0]||(e[0]=s=>o.name=s),placeholder:"\u8BF7\u8F93\u5165\u540D\u79F0",clearable:""},null,8,["modelValue"])]),_:1}),a(n,{label:"\u5907\u6CE8",prop:"remark"},{default:t(()=>[a(l,{modelValue:o.remark,"onUpdate:modelValue":e[1]||(e[1]=s=>o.remark=s),type:"textarea",rows:4,placeholder:"\u8BF7\u8F93\u5165\u5907\u6CE8"},null,8,["modelValue"])]),_:1}),a(n,{label:"\u6392\u5E8F",prop:"sort"},{default:t(()=>[a(E,{modelValue:o.sort,"onUpdate:modelValue":e[2]||(e[2]=s=>o.sort=s)},null,8,["modelValue"])]),_:1}),a(n,{label:"\u72B6\u6001",prop:"sort"},{default:t(()=>[a(v,{modelValue:o.isDisable,"onUpdate:modelValue":e[3]||(e[3]=s=>o.isDisable=s)},{default:t(()=>[a(i,{label:0},{default:t(()=>[O]),_:1}),a(i,{label:1},{default:t(()=>[z]),_:1})]),_:1},8,["modelValue"])]),_:1})]),_:1},8,["model"])]),_:1},8,["title"])])}}});export{Z as _};