var $=Object.defineProperty,A=Object.defineProperties;var K=Object.getOwnPropertyDescriptors;var V=Object.getOwnPropertySymbols;var T=Object.prototype.hasOwnProperty,z=Object.prototype.propertyIsEnumerable;var B=(l,o,n)=>o in l?$(l,o,{enumerable:!0,configurable:!0,writable:!0,value:n}):l[o]=n,b=(l,o)=>{for(var n in o||(o={}))T.call(o,n)&&B(l,n,o[n]);if(V)for(var n of V(o))z.call(o,n)&&B(l,n,o[n]);return l},w=(l,o)=>A(l,K(o));import{_ as I,u as q,n as M,q as N,r as S,s as U,t as j}from"./index.36d9dba1.js";import{F as P}from"./index.04cbd81f.js";import{d as x,v as F,t as G,V as H,y as J,r,a as L,i as t,w as u,E as R,o as O,f as Q,j as C}from"./vendor.bbaa8c82.js";const W=x({components:{FooterBtns:P},setup(){const{route:l,router:o}=q(),n=F(null),f=F(null),d=F(null),{formData:s,menu:c}=G(H({formData:{name:"",remark:"",menus:[],isDisable:0,sort:"",menuIds:""},menu:{permissionsTree:[],allAuthKeys:[]}})),h={name:[{required:!0,message:"\u8BF7\u8F93\u5165\u89D2\u8272\u540D\u79F0",trigger:["blur"]}]},v=()=>{var e;(e=n.value)==null||e.validate(m=>{!m||(d.value?p():D())})},i=()=>{M().then(e=>{c.value.permissionsTree=e,console.log("res",e);const m=N(e,[],"children");c.value.allAuthKeys=m.map(_=>_.id)}).catch(e=>{console.log("err",e)})},D=()=>{S(b({},s.value)).then(e=>{console.log("res",e),setTimeout(()=>{o.back()},500),R({type:"success",message:"\u4FDD\u5B58\u6210\u529F"})}).catch(e=>{console.log("err",e)})},p=()=>{s.value.menus.length==0?s.value.menuIds="":s.value.menuIds=s.value.menus.join(","),U(w(b({},s.value),{id:d.value})).then(e=>{console.log("res",e),setTimeout(()=>{o.back()},500),R({type:"success",message:"\u4FDD\u5B58\u6210\u529F"})}).catch(e=>{console.log("err",e)})},g=()=>{j({id:d.value}).then(e=>{var m;console.log("res",e),s.value=e,(m=f.value)==null||m.setCheckedKeys(e.menus)}).catch(e=>{console.log("err",e)})},k=(e,m)=>{if(console.log(e),!e.id)return;const _=s.value.menus.findIndex(E=>E==e.id);if(m){_==-1&&s.value.menus.push(e.id);return}_!=-1&&s.value.menus.splice(_,1)},y=()=>{var e;(e=f.value)==null||e.setCheckedKeys(c.value.allAuthKeys)},a=()=>{var e;(e=f.value)==null||e.setCheckedKeys([])};return J(()=>{const e=l.query;e.id&&(d.value=e.id*1,g()),i()}),{id:d,formData:s,menu:c,formRef:n,treeRef:f,rules:h,getMenu:i,roleAdd:D,roleEdit:p,roleDetail:g,onSubmit:v,handlePermissionsCheckChange:k,close:a,allSelect:y}}}),X={class:"role-edit"},Y={class:"flex",style:{"margin-top":"3px"}},Z=C(" \u5168\u9009 "),ee=C(" \u5168\u4E0D\u9009 "),oe=C("\u4FDD\u5B58");function le(l,o,n,f,d,s){const c=r("el-page-header"),h=r("el-card"),v=r("el-input"),i=r("el-form-item"),D=r("el-switch"),p=r("el-button"),g=r("el-tree"),k=r("el-form"),y=r("footer-btns");return O(),L("div",X,[t(h,{shadow:"never"},{default:u(()=>[t(c,{content:l.id?"\u7F16\u8F91\u89D2\u8272":"\u65B0\u589E\u89D2\u8272",onBack:o[0]||(o[0]=a=>l.$router.back())},null,8,["content"])]),_:1}),t(h,{shadow:"never",class:"m-t-15"},{default:u(()=>[t(k,{ref:"formRef",rules:l.rules,class:"ls-form",model:l.formData,"label-width":"150px",size:"small"},{default:u(()=>[t(i,{label:"\u540D\u79F0",prop:"name"},{default:u(()=>[t(v,{modelValue:l.formData.name,"onUpdate:modelValue":o[1]||(o[1]=a=>l.formData.name=a),placeholder:"\u8BF7\u8F93\u5165\u540D\u79F0"},null,8,["modelValue"])]),_:1}),t(i,{label:"\u5907\u6CE8",prop:"remark"},{default:u(()=>[t(v,{modelValue:l.formData.remark,"onUpdate:modelValue":o[2]||(o[2]=a=>l.formData.remark=a),placeholder:"\u8BF7\u8F93\u5165\u5907\u6CE8",type:"textarea",autosize:{minRows:4,maxRows:6}},null,8,["modelValue"])]),_:1}),t(i,{label:"\u6392\u5E8F"},{default:u(()=>[t(v,{modelValue:l.formData.sort,"onUpdate:modelValue":o[3]||(o[3]=a=>l.formData.sort=a),placeholder:"\u8BF7\u8F93\u5165",type:"number"},null,8,["modelValue"])]),_:1}),t(i,{label:"\u72B6\u6001"},{default:u(()=>[t(D,{modelValue:l.formData.isDisable,"onUpdate:modelValue":o[4]||(o[4]=a=>l.formData.isDisable=a),"active-value":0,"inactive-value":1},null,8,["modelValue"])]),_:1}),t(i,{label:"\u6743\u9650",prop:"auth_keys"},{default:u(()=>[Q("div",Y,[t(p,{type:"text",size:"mini",onClick:o[5]||(o[5]=a=>l.allSelect())},{default:u(()=>[Z]),_:1}),t(p,{type:"text",size:"mini",onClick:o[6]||(o[6]=a=>l.close())},{default:u(()=>[ee]),_:1})]),t(g,{ref:"treeRef",data:l.menu.permissionsTree,"node-key":"id","default-expand-all":"",icon:"ArrowRight",props:{children:"children",label:"menuName"},"empty-text":"","show-checkbox":"",onCheckChange:l.handlePermissionsCheckChange},null,8,["data","onCheckChange"])]),_:1})]),_:1},8,["rules","model"])]),_:1}),t(y,null,{default:u(()=>[t(p,{type:"primary",size:"small",onClick:l.onSubmit},{default:u(()=>[oe]),_:1},8,["onClick"])]),_:1})])}var ue=I(W,[["render",le],["__scopeId","data-v-67ff2042"]]);export{ue as default};
