import{C as V,L as S,A as T,E as L,B as M}from"./element-plus.2e2d06fe.js";import{a as N,b as I}from"./role.b5ca26a6.js";import{m as P}from"./menu.183595c6.js";import{P as j}from"./index.16352aaf.js";import{f as q,t as H}from"./index.4a9ec79b.js";import{d as O,s as i,r as u,a0 as U,o as z,c as G,V as t,M as c,a as _,n as k}from"./@vue.791b8507.js";const J={class:"edit-popup"},te=O({__name:"auth",emits:["success","close"],setup(Q,{expose:v,emit:p}){const l=i(),f=i(),r=i(),y=u(!1),d=u(!0),h=u([]),m=u([]),o=U({id:"",name:"",remark:"",sort:0,isDisable:0,menus:[]}),C={name:[{required:!0,message:"\u8BF7\u8F93\u5165\u540D\u79F0",trigger:["blur"]}]},b=async()=>{const e=await P();m.value=e,h.value=H(e)},x=()=>{var s,n;const e=(s=l.value)==null?void 0:s.getCheckedKeys(),a=(n=l.value)==null?void 0:n.getHalfCheckedKeys();return e==null||e.unshift.apply(e,a),e},E=()=>{o.menus.forEach(e=>{k(()=>{var a;(a=l.value)==null||a.setChecked(e,!0,!1)})})},g=e=>{const a=m.value;for(let s=0;s<a.length;s++)l.value.store.nodesMap[a[s].id].expanded=e},F=e=>{var a,s;e?(a=l.value)==null||a.setCheckedKeys(h.value.map(n=>n.id)):(s=l.value)==null||s.setCheckedKeys([])},w=async()=>{var e,a;await((e=f.value)==null?void 0:e.validate()),o.menus=x(),await N({...o,menuIds:o.menus.join()}),(a=r.value)==null||a.close(),q.msgSuccess("\u64CD\u4F5C\u6210\u529F"),p("success")},D=()=>{p("close")};return v({open:()=>{var e;(e=r.value)==null||e.open()},setFormData:async e=>{await b();const a=await I({id:e.id});for(const s in o)a[s]!=null&&a[s]!=null&&(o[s]=a[s]);k(()=>{E()})}}),(e,a)=>{const s=V,n=S,B=T,A=L,R=M;return z(),G("div",J,[t(j,{ref_key:"popupRef",ref:r,title:"\u6743\u9650\u8BBE\u7F6E",async:!0,width:"550px",clickModalClose:!0,onConfirm:w,onClose:D},{default:c(()=>[t(R,{class:"ls-form",ref_key:"formRef",ref:f,rules:C,model:o,"label-width":"60px"},{default:c(()=>[t(A,{class:"h-[400px] sm:h-[600px]"},{default:c(()=>[t(B,{label:"\u6743\u9650",prop:"menus"},{default:c(()=>[_("div",null,[t(s,{label:"\u5C55\u5F00/\u6298\u53E0",onChange:g}),t(s,{label:"\u5168\u9009/\u4E0D\u5168\u9009",onChange:F}),t(s,{modelValue:d.value,"onUpdate:modelValue":a[0]||(a[0]=K=>d.value=K),label:"\u7236\u5B50\u8054\u52A8"},null,8,["modelValue"]),_("div",null,[t(n,{ref_key:"treeRef",ref:l,data:m.value,props:{label:"menuName",children:"children"},"check-strictly":!d.value,"node-key":"id","default-expand-all":y.value,"show-checkbox":""},null,8,["data","check-strictly","default-expand-all"])])])]),_:1})]),_:1})]),_:1},8,["model"])]),_:1},512)])}}});export{te as _};
