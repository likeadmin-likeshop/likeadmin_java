import{N as C,O as R,P as x,I as q,D as L,C as T,F as U,w as O,L as S}from"./element-plus.f5eb07a0.js";import{_ as $}from"./index.d32dba97.js";import{u as M,a as P}from"./vue-router.5046cc50.js";import{e as j,f as z}from"./index.1256601b.js";import{n as G,s as H}from"./message.65426c22.js";import{d as E,r as W,a0 as J,s as K,o as i,c,V as e,M as t,O as Q,L as X,T as n,U as m,a as l,W as Y,a8 as Z}from"./@vue.cab01781.js";import"./@vueuse.724ed0af.js";import"./@element-plus.92b4185f.js";import"./lodash-es.29c53eac.js";import"./dayjs.22a46fd8.js";import"./axios.8058589d.js";import"./async-validator.fb49d0f5.js";import"./@ctrl.82a509e0.js";import"./@popperjs.36402333.js";import"./escape-html.e5dfadb9.js";import"./normalize-wheel-es.8aeb3683.js";import"./lodash.bbe6f09f.js";import"./pinia.e85e8286.js";import"./vue-demi.bfae2336.js";import"./css-color-function.5bd363c0.js";import"./color.232115c1.js";import"./clone.8f44c0eb.js";import"./color-convert.69e17089.js";import"./color-string.e356f5de.js";import"./color-name.e7a4e1d3.js";import"./nprogress.404eaa9c.js";import"./vue-clipboard3.19ab9072.js";import"./clipboard.6fb7c109.js";import"./echarts.6ad8c478.js";import"./zrender.f91f2f01.js";import"./highlight.js.4ebdf9a4.js";import"./@highlightjs.0ab41b7b.js";const ee=l("div",{class:"font-medium mb-7"},"\u901A\u77E5\u540D\u79F0",-1),te=l("div",{class:"font-medium mb-7"},"\u77ED\u4FE1\u901A\u77E5",-1),oe=n("\u5173\u95ED"),se=n("\u5F00\u542F"),ae={class:"w-80"},ue={class:"flex-1"},re={class:"w-full max-w-[320px]"},le={class:"form-tips"},ne=n("\u4FDD\u5B58"),ie=E({name:"noticeEdit"}),je=E({...ie,setup(me){const _=M(),v=P(),d=W(!1),o=J({id:"",name:"",type:"",remarks:"",smsNotice:{status:0,templateId:"",content:"",tips:[]}}),g={"smsNotice.templateId":[{required:!0,message:"\u8BF7\u8F93\u5165\u6A21\u677FID",trigger:"blur"}],"smsNotice.content":[{required:!0,message:"\u8BF7\u8F93\u5165\u77ED\u4FE1\u5185\u5BB9",trigger:"blur"}]},{removeTab:D}=j(),f=K(),N=async()=>{d.value=!0;const a=await G({id:_.query.id});Object.keys(a).forEach(s=>{o[s]=a[s]}),d.value=!1},B=async()=>{var a;await((a=f.value)==null?void 0:a.validate()),await H(o),z.msgSuccess("\u64CD\u4F5C\u6210\u529F"),D(),v.back()};return _.query.id&&N(),(a,s)=>{const w=C,p=q,r=L,F=R,h=x,b=T,V=U,k=O,y=$,I=S;return i(),c("div",null,[e(p,{class:"!border-none",shadow:"never"},{default:t(()=>[e(w,{content:a.$route.meta.title,onBack:s[0]||(s[0]=u=>a.$router.back())},null,8,["content"])]),_:1}),Q((i(),X(V,{ref_key:"formRef",ref:f,model:o,"label-width":"120px",rules:g},{default:t(()=>[e(p,{class:"!border-none mt-4",shadow:"never"},{default:t(()=>[ee,e(r,{label:"\u901A\u77E5\u540D\u79F0",prop:"name"},{default:t(()=>[n(m(o.name),1)]),_:1}),e(r,{label:"\u901A\u77E5\u7C7B\u578B",prop:"name"},{default:t(()=>[n(m(o.type),1)]),_:1}),e(r,{label:"\u901A\u77E5\u4E1A\u52A1",prop:"name"},{default:t(()=>[n(m(o.remarks),1)]),_:1})]),_:1}),e(p,{class:"!border-none mt-4",shadow:"never"},{default:t(()=>[te,e(r,{label:"\u5F00\u542F\u72B6\u6001",prop:"smsNotice.status",required:""},{default:t(()=>[e(h,{modelValue:o.smsNotice.status,"onUpdate:modelValue":s[1]||(s[1]=u=>o.smsNotice.status=u)},{default:t(()=>[e(F,{label:"0"},{default:t(()=>[oe]),_:1}),e(F,{label:"1"},{default:t(()=>[se]),_:1})]),_:1},8,["modelValue"])]),_:1}),e(r,{label:"\u6A21\u677FID",prop:"smsNotice.templateId"},{default:t(()=>[l("div",ae,[e(b,{modelValue:o.smsNotice.templateId,"onUpdate:modelValue":s[2]||(s[2]=u=>o.smsNotice.templateId=u),placeholder:"\u8BF7\u8F93\u5165\u6A21\u677FID"},null,8,["modelValue"])])]),_:1}),e(r,{label:"\u77ED\u4FE1\u5185\u5BB9",prop:"smsNotice.content"},{default:t(()=>[l("div",ue,[l("div",re,[e(b,{type:"textarea",autosize:{minRows:6,maxRows:6},modelValue:o.smsNotice.content,"onUpdate:modelValue":s[3]||(s[3]=u=>o.smsNotice.content=u)},null,8,["modelValue"])]),l("div",le,[(i(!0),c(Y,null,Z(o.smsNotice.tips,(u,A)=>(i(),c("div",{key:A},m(u),1))),128))])])]),_:1})]),_:1})]),_:1},8,["model"])),[[I,d.value]]),e(y,null,{default:t(()=>[e(k,{type:"primary",onClick:B},{default:t(()=>[ne]),_:1})]),_:1})])}}});export{je as default};
