import{_ as V,a as v,D as w}from"./index.47b15397.js";import{P,u as $}from"./index.4a66c573.js";import{P as x}from"./index.c531e9d1.js";import{d as z,V as q,y as A,r as s,W as D,a as y,i as e,w as o,E as N,o as l,X as u,b as m,f as _,g as j,j as f}from"./vendor.bbaa8c82.js";const M=z({components:{Pagination:P,Popup:x},setup(){const t=q({id:0,name:"",remark:"",create_time:"",num:0}),{pager:r,requestApi:i}=$({callback:v,params:t}),g=b=>{w({id:b}).then(p=>{console.log("res",p),i(),N({type:"success",message:"\u5220\u9664\u6210\u529F"})}).catch(p=>{console.log("err",p)})};return A(()=>{i()}),{formData:t,apiRoleLists:v,handleDelete:g,pager:r,requestApi:i}}}),R={class:"role"},T=f("\u65B0\u589E\u89D2\u8272"),I={class:"m-t-15"},L={class:"m-t-15"},S=f("\u7F16\u8F91"),U=f("\u5220\u9664"),W={class:"flex row-right"};function X(t,r,i,g,b,p){const d=s("el-button"),h=s("router-link"),a=s("el-table-column"),C=s("popup"),F=s("el-table"),k=s("pagination"),E=s("el-card"),c=D("perm"),B=D("loading");return l(),y("div",R,[e(E,{shadow:"never"},{default:o(()=>[e(h,{to:"/permission/role/edit"},{default:o(()=>[u((l(),m(d,{type:"primary",size:"small"},{default:o(()=>[T]),_:1})),[[c,["system:role:add"]]])]),_:1}),u((l(),y("div",I,[_("div",L,[e(F,{data:t.pager.lists,size:"medium"},{default:o(()=>[e(a,{prop:"id",label:"ID"}),e(a,{prop:"name",label:"\u540D\u79F0"}),e(a,{prop:"sort",label:"\u6392\u5E8F"}),e(a,{prop:"remark",label:"\u5907\u6CE8"}),e(a,{prop:"isDisable",label:"\u72B6\u6001"},{default:o(n=>[_("span",null,j(n.row.isDisable==0?"\u542F\u7528":"\u5173\u95ED"),1)]),_:1}),e(a,{prop:"",label:"\u6743\u9650"}),e(a,{prop:"createTime",label:"\u521B\u5EFA\u65F6\u95F4"}),e(a,{prop:"",label:"\u64CD\u4F5C"},{default:o(({row:n})=>[u((l(),m(h,{class:"m-r-10",to:{path:"/permission/role/edit",query:{id:n.id}}},{default:o(()=>[e(d,{type:"text",size:"mini"},{default:o(()=>[S]),_:1})]),_:2},1032,["to"])),[[c,["system:role:edit"]]]),u((l(),m(C,{class:"m-r-10 inline",onConfirm:G=>t.handleDelete(n.id)},{trigger:o(()=>[e(d,{type:"text",size:"mini"},{default:o(()=>[U]),_:1})]),_:2},1032,["onConfirm"])),[[c,["system:role:del"]]])]),_:1})]),_:1},8,["data"])]),_("div",W,[e(k,{modelValue:t.pager,"onUpdate:modelValue":r[0]||(r[0]=n=>t.pager=n),layout:"total, prev, pager, next, jumper",onChange:t.requestApi},null,8,["modelValue","onChange"])])])),[[B,t.pager.loading]])]),_:1})])}var Q=V(M,[["render",X]]);export{Q as default};
