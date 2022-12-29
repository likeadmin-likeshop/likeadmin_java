import{Z as U,w as z,J as G,K as J,I as K,L as P}from"./element-plus.f5eb07a0.js";import{M as C,f as D,b as Z}from"./index.64e62b67.js";import{m as j,d as q}from"./menu.77a3989d.js";import{_ as H}from"./edit.vue_vue_type_script_setup_true_lang.1e2b9cbb.js";import{d as R,s as x,r as E,ag as Q,o as n,c as v,V as s,M as t,a as N,O as c,L as r,u as w,S as $,T as m,n as L}from"./@vue.cab01781.js";import"./@vueuse.724ed0af.js";import"./@element-plus.92b4185f.js";import"./lodash-es.29c53eac.js";import"./dayjs.22a46fd8.js";import"./axios.8058589d.js";import"./async-validator.fb49d0f5.js";import"./@ctrl.82a509e0.js";import"./@popperjs.36402333.js";import"./escape-html.e5dfadb9.js";import"./normalize-wheel-es.8aeb3683.js";import"./lodash.bbe6f09f.js";import"./vue-router.5046cc50.js";import"./pinia.e85e8286.js";import"./vue-demi.bfae2336.js";import"./css-color-function.5bd363c0.js";import"./color.232115c1.js";import"./clone.8f44c0eb.js";import"./color-convert.69e17089.js";import"./color-string.e356f5de.js";import"./color-name.e7a4e1d3.js";import"./nprogress.404eaa9c.js";import"./vue-clipboard3.19ab9072.js";import"./clipboard.6fb7c109.js";import"./echarts.6ad8c478.js";import"./zrender.f91f2f01.js";import"./highlight.js.4ebdf9a4.js";import"./@highlightjs.0ab41b7b.js";import"./index.136be2f4.js";const W={class:"menu-lists"},X=m(" \u65B0\u589E "),Y=m(" \u5C55\u5F00/\u6298\u53E0 "),ee={key:0},te={key:1},oe={key:2},ae={class:"flex"},ne=m("\u6B63\u5E38"),se=m("\u505C\u7528"),ie=m(" \u65B0\u589E "),le=m(" \u7F16\u8F91 "),ue=m(" \u5220\u9664 "),re=R({name:"menu"}),Ke=R({...re,setup(me){const b=x(),d=x();let h=!1;const p=E(!1),_=E(!1),y=E([]),k=async()=>{p.value=!0;try{const e=await j();y.value=e,p.value=!1}catch{p.value=!1}},g=async e=>{var o,i;_.value=!0,await L(),e&&((o=d.value)==null||o.setFormData({pid:e})),(i=d.value)==null||i.open("add")},A=async e=>{var o,i;_.value=!0,await L(),(o=d.value)==null||o.open("edit"),(i=d.value)==null||i.getDetail(e)},V=async e=>{await D.confirm("\u786E\u5B9A\u8981\u5220\u9664\uFF1F"),await q({id:e}),D.msgSuccess("\u5220\u9664\u6210\u529F"),k()},M=()=>{h=!h,F(y.value,h)},F=(e,o=!0)=>{var i;for(const l in e)(i=b.value)==null||i.toggleRowExpansion(e[l],o),e[l].children&&F(e[l].children,o)};return k(),(e,o)=>{const i=Z,l=z,u=G,B=U,S=J,I=K,f=Q("perms"),O=P;return n(),v("div",W,[s(I,{class:"!border-none",shadow:"never"},{default:t(()=>[N("div",null,[c((n(),r(l,{type:"primary",onClick:o[0]||(o[0]=a=>g())},{icon:t(()=>[s(i,{name:"el-icon-Plus"})]),default:t(()=>[X]),_:1})),[[f,["system:menu:add"]]]),s(l,{onClick:M},{default:t(()=>[Y]),_:1})]),c((n(),r(S,{ref_key:"tableRef",ref:b,class:"mt-4",size:"large",data:y.value,"row-key":"id","tree-props":{children:"children",hasChildren:"hasChildren"}},{default:t(()=>[s(u,{label:"\u83DC\u5355\u540D\u79F0",prop:"menuName","min-width":"150","show-overflow-tooltip":""}),s(u,{label:"\u7C7B\u578B",prop:"menuType","min-width":"80"},{default:t(({row:a})=>[a.menuType==w(C).CATALOGUE?(n(),v("div",ee,"\u76EE\u5F55")):a.menuType==w(C).MENU?(n(),v("div",te,"\u83DC\u5355")):a.menuType==w(C).BUTTON?(n(),v("div",oe,"\u6309\u94AE")):$("",!0)]),_:1}),s(u,{label:"\u56FE\u6807",prop:"menuIcon","min-width":"80"},{default:t(({row:a})=>[N("div",ae,[s(i,{name:a.menuIcon,size:20},null,8,["name"])])]),_:1}),s(u,{label:"\u6743\u9650\u6807\u8BC6",prop:"perms","min-width":"150","show-overflow-tooltip":""}),s(u,{label:"\u72B6\u6001",prop:"isDisable","min-width":"100"},{default:t(({row:a})=>[a.isDisable==0?(n(),r(B,{key:0},{default:t(()=>[ne]),_:1})):(n(),r(B,{key:1,type:"danger"},{default:t(()=>[se]),_:1}))]),_:1}),s(u,{label:"\u6392\u5E8F",prop:"menuSort","min-width":"100"}),s(u,{label:"\u66F4\u65B0\u65F6\u95F4",prop:"updateTime","min-width":"180"}),s(u,{label:"\u64CD\u4F5C",width:"160",fixed:"right"},{default:t(({row:a})=>[c((n(),r(l,{type:"primary",link:"",onClick:T=>g(a.id)},{default:t(()=>[ie]),_:2},1032,["onClick"])),[[f,["system:menu:add"]]]),c((n(),r(l,{type:"primary",link:"",onClick:T=>A(a)},{default:t(()=>[le]),_:2},1032,["onClick"])),[[f,["system:menu:edit"]]]),c((n(),r(l,{type:"danger",link:"",onClick:T=>V(a.id)},{default:t(()=>[ue]),_:2},1032,["onClick"])),[[f,["system:menu:del"]]])]),_:1})]),_:1},8,["data"])),[[O,p.value]])]),_:1}),_.value?(n(),r(H,{key:0,ref_key:"editRef",ref:d,onSuccess:k,onClose:o[1]||(o[1]=a=>_.value=!1)},null,512)):$("",!0)])}}});export{Ke as default};