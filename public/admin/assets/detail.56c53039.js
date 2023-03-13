import{N as A,I as B,o as w,D as N,w as T,F as h}from"./element-plus.b75f72d9.js";import{_ as P}from"./index.vue_vue_type_script_setup_true_lang.f20a4aac.js";import{n as L,f as R,b as V}from"./index.0d74f100.js";import{u as $}from"./vue-router.5046cc50.js";import{g as I,u as q}from"./consumer.06595b43.js";import{d as y,a0 as O,s as S,ag as U,o as n,c as j,V as e,M as t,a as C,T as l,U as u,O as f,L as F}from"./@vue.cab01781.js";import"./@vueuse.724ed0af.js";import"./@element-plus.92b4185f.js";import"./lodash-es.29c53eac.js";import"./dayjs.22a46fd8.js";import"./axios.8058589d.js";import"./async-validator.fb49d0f5.js";import"./@ctrl.82a509e0.js";import"./@popperjs.36402333.js";import"./escape-html.e5dfadb9.js";import"./normalize-wheel-es.8aeb3683.js";import"./lodash.bbe6f09f.js";import"./pinia.e85e8286.js";import"./vue-demi.bfae2336.js";import"./css-color-function.5bd363c0.js";import"./color.232115c1.js";import"./clone.8f44c0eb.js";import"./color-convert.69e17089.js";import"./color-string.e356f5de.js";import"./color-name.e7a4e1d3.js";import"./nprogress.404eaa9c.js";import"./vue-clipboard3.19ab9072.js";import"./clipboard.6fb7c109.js";import"./echarts.6ad8c478.js";import"./zrender.f91f2f01.js";import"./highlight.js.4ebdf9a4.js";import"./@highlightjs.0ab41b7b.js";const z={class:"bg-page py-5 pl-20 mb-10"},H=C("div",{class:"mb-3 text-tx-regular"},"\u7528\u6237\u5934\u50CF",-1),M=y({name:"consumerDetail"}),xe=y({...M,setup(G){const b=$(),o=O({avatar:"",channel:"",createTime:"",lastLoginIp:"",lastLoginTime:"",mobile:"",nickname:"",realName:0,sex:0,sn:"",username:""}),D=S(),E=async()=>{const i=await I({id:b.query.id});Object.keys(o).forEach(a=>{o[a]=i[a]})},m=async(i,a)=>{L(i)||(await q({id:b.query.id,field:a,value:i}),R.msgSuccess("\u7F16\u8F91\u6210\u529F"),E())};return E(),(i,a)=>{const g=A,v=B,k=w,r=N,p=V,d=T,c=P,x=h,_=U("perms");return n(),j("div",null,[e(v,{class:"!border-none",shadow:"never"},{default:t(()=>[e(g,{content:"\u7528\u6237\u8BE6\u60C5",onBack:a[0]||(a[0]=s=>i.$router.back())})]),_:1}),e(v,{class:"mt-4 !border-none",header:"\u57FA\u672C\u8D44\u6599",shadow:"never"},{default:t(()=>[e(x,{ref_key:"formRef",ref:D,class:"ls-form",model:o,"label-width":"120px"},{default:t(()=>[C("div",z,[H,e(k,{src:o.avatar,size:58},null,8,["src"])]),e(r,{label:"\u7528\u6237\u7F16\u53F7\uFF1A"},{default:t(()=>[l(u(o.sn),1)]),_:1}),e(r,{label:"\u7528\u6237\u6635\u79F0\uFF1A"},{default:t(()=>[l(u(o.nickname),1)]),_:1}),e(r,{label:"\u8D26\u53F7\uFF1A"},{default:t(()=>[l(u(o.username)+" ",1),e(c,{class:"ml-[10px]",limit:32,onConfirm:a[1]||(a[1]=s=>m(s,"username"))},{default:t(()=>[f((n(),F(d,{type:"primary",link:""},{default:t(()=>[e(p,{name:"el-icon-EditPen"})]),_:1})),[[_,["user:edit"]]])]),_:1})]),_:1}),e(r,{label:"\u771F\u5B9E\u59D3\u540D\uFF1A"},{default:t(()=>[l(u(o.realName||"-")+" ",1),e(c,{class:"ml-[10px]",limit:32,onConfirm:a[2]||(a[2]=s=>m(s,"realName"))},{default:t(()=>[f((n(),F(d,{type:"primary",link:""},{default:t(()=>[e(p,{name:"el-icon-EditPen"})]),_:1})),[[_,["user:edit"]]])]),_:1})]),_:1}),e(r,{label:"\u6027\u522B\uFF1A"},{default:t(()=>[l(u(o.sex)+" ",1),e(c,{class:"ml-[10px]",type:"select",options:[{label:"\u672A\u77E5",value:0},{label:"\u7537",value:1},{label:"\u5973",value:2}],onConfirm:a[3]||(a[3]=s=>m(s,"sex"))},{default:t(()=>[f((n(),F(d,{type:"primary",link:""},{default:t(()=>[e(p,{name:"el-icon-EditPen"})]),_:1})),[[_,["user:edit"]]])]),_:1})]),_:1}),e(r,{label:"\u8054\u7CFB\u7535\u8BDD\uFF1A"},{default:t(()=>[l(u(o.mobile||"-")+" ",1),e(c,{class:"ml-[10px]",type:"number",onConfirm:a[4]||(a[4]=s=>m(s,"mobile"))},{default:t(()=>[f((n(),F(d,{type:"primary",link:""},{default:t(()=>[e(p,{name:"el-icon-EditPen"})]),_:1})),[[_,["user:edit"]]])]),_:1})]),_:1}),e(r,{label:"\u6CE8\u518C\u6765\u6E90\uFF1A"},{default:t(()=>[l(u(o.channel),1)]),_:1}),e(r,{label:"\u6CE8\u518C\u65F6\u95F4\uFF1A"},{default:t(()=>[l(u(o.createTime),1)]),_:1}),e(r,{label:"\u6700\u8FD1\u767B\u5F55\u65F6\u95F4\uFF1A"},{default:t(()=>[l(u(o.lastLoginTime),1)]),_:1})]),_:1},8,["model"])]),_:1})])}}});export{xe as default};