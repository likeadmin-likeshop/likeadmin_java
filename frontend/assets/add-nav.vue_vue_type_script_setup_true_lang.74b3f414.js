import{y as F,u as b}from"./element-plus.f93fd622.js";import{_ as v}from"./index.39e26ece.js";import{_ as y}from"./picker.vue_vue_type_script_setup_true_lang.5eb3faa5.js";import{_ as A}from"./picker.2ad4f3fe.js";import{f as _,b as B}from"./index.e8a7ecce.js";import{d as D,o as u,c as r,a as e,W as E,a8 as C,L as U,M as m,V as t,T as $}from"./@vue.cab01781.js";const w={class:"bg-fill-light flex items-center w-full p-4 mb-4"},N={class:"upload-btn w-[60px] h-[60px]"},z={class:"ml-3 flex-1"},L={class:"flex"},T=e("span",{class:"text-tx-regular flex-none mr-3"},"\u540D\u79F0",-1),I={class:"flex mt-[18px]"},M=e("span",{class:"text-tx-regular flex-none mr-3"},"\u94FE\u63A5",-1),P=$("\u6DFB\u52A0"),K=D({__name:"add-nav",props:{modelValue:{type:Array,default:()=>[]},max:{type:Number,default:10},min:{type:Number,default:1}},setup(d){const l=d,p=()=>{var n;((n=l.modelValue)==null?void 0:n.length)<l.max?l.modelValue.push({image:"",name:"\u5BFC\u822A\u540D\u79F0",link:{}}):_.msgError(`\u6700\u591A\u6DFB\u52A0${l.max}\u4E2A`)},i=n=>{var s;if(((s=l.modelValue)==null?void 0:s.length)<=l.min)return _.msgError(`\u6700\u5C11\u4FDD\u7559${l.min}\u4E2A`);l.modelValue.splice(n,1)};return(n,s)=>{const f=B,x=A,V=F,h=y,g=v,k=b;return u(),r("div",null,[e("div",null,[(u(!0),r(E,null,C(d.modelValue,(o,c)=>(u(),U(g,{class:"max-w-[400px]",key:c,onClose:a=>i(c)},{default:m(()=>[e("div",w,[t(x,{modelValue:o.image,"onUpdate:modelValue":a=>o.image=a,"upload-class":"bg-body",size:"60px","exclude-domain":""},{upload:m(()=>[e("div",N,[t(f,{name:"el-icon-Plus",size:20})])]),_:2},1032,["modelValue","onUpdate:modelValue"]),e("div",z,[e("div",L,[T,t(V,{modelValue:o.name,"onUpdate:modelValue":a=>o.name=a,placeholder:"\u8BF7\u8F93\u5165\u540D\u79F0"},null,8,["modelValue","onUpdate:modelValue"])]),e("div",I,[M,t(h,{modelValue:o.link,"onUpdate:modelValue":a=>o.link=a},null,8,["modelValue","onUpdate:modelValue"])])])])]),_:2},1032,["onClose"]))),128))]),e("div",null,[t(k,{type:"primary",onClick:p},{default:m(()=>[P]),_:1})])])}}});export{K as _};