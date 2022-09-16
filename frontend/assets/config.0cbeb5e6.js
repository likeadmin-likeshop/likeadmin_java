import{_ as V}from"./index.f712cef7.js";import{F as w,M as y,N as h,G as g,y as k,A as I,u as S,B as U}from"./element-plus.62f8b726.js";import{_ as x}from"./picker.8f877736.js";import{g as T,s as j}from"./wx_oa.36e4b213.js";import{f as q}from"./index.ccc78a7a.js";import{q as K}from"./@vueuse.724ed0af.js";import{d as R,a0 as N,ag as $,o as E,c as L,V as u,M as t,a as e,u as p,O,L as G,T as d}from"./@vue.cab01781.js";import"./@element-plus.92b4185f.js";import"./lodash-es.29c53eac.js";import"./dayjs.66926594.js";import"./axios.2d915936.js";import"./async-validator.fb49d0f5.js";import"./@ctrl.82a509e0.js";import"./@popperjs.36402333.js";import"./escape-html.e5dfadb9.js";import"./normalize-wheel-es.8aeb3683.js";import"./index.fd3131b6.js";import"./index.37cda7d4.js";import"./usePaging.70f7c50a.js";import"./index.c18d5e6d.js";import"./index.vue_vue_type_script_setup_true_lang.c996e5f2.js";import"./vue3-video-play.05975c53.js";import"./vuedraggable.a5db575d.js";import"./vue.de4be77f.js";import"./sortablejs.cd7e2c7e.js";import"./lodash.b68d77aa.js";import"./vue-router.5046cc50.js";import"./pinia.e85e8286.js";import"./vue-demi.bfae2336.js";import"./css-color-function.a8f9466d.js";import"./color.903ca10f.js";import"./clone.9d64bb7a.js";import"./color-convert.69e17089.js";import"./color-string.e356f5de.js";import"./color-name.e7a4e1d3.js";import"./nprogress.a96d99f2.js";import"./echarts.6ad8c478.js";import"./zrender.f91f2f01.js";import"./highlight.js.4ebdf9a4.js";import"./@highlightjs.0ab41b7b.js";const J=e("div",{class:"font-medium mb-7"},"\u5FAE\u4FE1\u516C\u4F17\u53F7",-1),M={class:"w-80"},z={class:"w-80"},Z=e("div",{class:"form-tips"},"\u5EFA\u8BAE\u5C3A\u5BF8\uFF1A\u5BBD400px*\u9AD8400px\u3002jpg\uFF0Cjpeg\uFF0Cpng\u683C\u5F0F",-1),H=e("div",{class:"font-medium mb-7"},"\u516C\u4F17\u53F7\u5F00\u53D1\u8005\u4FE1\u606F",-1),P={class:"w-80"},Q={class:"w-80"},W=e("div",{class:"form-tips"}," \u5C0F\u7A0B\u5E8F\u8D26\u53F7\u767B\u5F55\u5FAE\u4FE1\u516C\u4F17\u5E73\u53F0\uFF0C\u70B9\u51FB\u5F00\u53D1>\u5F00\u53D1\u8BBE\u7F6E->\u5F00\u53D1\u8005ID\uFF0C\u8BBE\u7F6EAppID\u548CAppSecret ",-1),X=e("div",{class:"font-medium mb-7"},"\u670D\u52A1\u5668\u914D\u7F6E",-1),Y={class:"flex"},uu={class:"mr-4 w-80"},eu=d("\u590D\u5236"),ou=e("div",{class:"form-tips"}," \u767B\u5F55\u5FAE\u4FE1\u516C\u4F17\u5E73\u53F0\uFF0C\u70B9\u51FB\u5F00\u53D1>\u57FA\u672C\u914D\u7F6E>\u670D\u52A1\u5668\u914D\u7F6E\uFF0C\u586B\u5199\u670D\u52A1\u5668\u5730\u5740\uFF08URL\uFF09 ",-1),lu={class:"w-80"},tu=e("div",{class:"form-tips"}," \u767B\u5F55\u5FAE\u4FE1\u516C\u4F17\u5E73\u53F0\uFF0C\u70B9\u51FB\u5F00\u53D1>\u57FA\u672C\u914D\u7F6E>\u670D\u52A1\u5668\u914D\u7F6E\uFF0C\u8BBE\u7F6E\u4EE4\u724CToken\u3002\u4E0D\u586B\u9ED8\u8BA4\u4E3A\u201Clikeshop\u201D ",-1),su={class:"w-80"},Fu=e("div",{class:"form-tips"}," \u6D88\u606F\u52A0\u5BC6\u5BC6\u94A5\u753143\u4F4D\u5B57\u7B26\u7EC4\u6210\uFF0C\u5B57\u7B26\u8303\u56F4\u4E3AA-Z,a-z,0-9 ",-1),nu=d(" \u660E\u6587\u6A21\u5F0F (\u4E0D\u4F7F\u7528\u6D88\u606F\u4F53\u52A0\u89E3\u5BC6\u529F\u80FD\uFF0C\u5B89\u5168\u7CFB\u6570\u8F83\u4F4E) "),du=d(" \u517C\u5BB9\u6A21\u5F0F (\u660E\u6587\u3001\u5BC6\u6587\u5C06\u5171\u5B58\uFF0C\u65B9\u4FBF\u5F00\u53D1\u8005\u8C03\u8BD5\u548C\u7EF4\u62A4) "),au=d(" \u5B89\u5168\u6A21\u5F0F\uFF08\u63A8\u8350\uFF09 (\u6D88\u606F\u5305\u4E3A\u7EAF\u5BC6\u6587\uFF0C\u9700\u8981\u5F00\u53D1\u8005\u52A0\u5BC6\u548C\u89E3\u5BC6\uFF0C\u5B89\u5168\u7CFB\u6570\u9AD8) "),iu=e("div",{class:"font-medium mb-7"},"\u529F\u80FD\u8BBE\u7F6E",-1),mu={class:"flex"},pu={class:"mr-4 w-80"},ru=d("\u590D\u5236"),_u=e("div",{class:"form-tips"}," \u767B\u5F55\u5FAE\u4FE1\u516C\u4F17\u5E73\u53F0\uFF0C\u70B9\u51FB\u8BBE\u7F6E>\u516C\u4F17\u53F7\u8BBE\u7F6E>\u529F\u80FD\u8BBE\u7F6E\uFF0C\u586B\u5199\u4E1A\u52A1\u57DF\u540D ",-1),Bu={class:"flex"},Eu={class:"mr-4 w-80"},cu=d("\u590D\u5236"),Du=e("div",{class:"form-tips"}," \u767B\u5F55\u5FAE\u4FE1\u516C\u4F17\u5E73\u53F0\uFF0C\u70B9\u51FB\u8BBE\u7F6E>\u516C\u4F17\u53F7\u8BBE\u7F6E>\u529F\u80FD\u8BBE\u7F6E\uFF0C\u586B\u5199JS\u63A5\u53E3\u5B89\u5168\u57DF\u540D ",-1),Cu={class:"flex"},fu={class:"mr-4 w-80"},Au=d("\u590D\u5236"),vu=e("div",{class:"form-tips"}," \u767B\u5F55\u5FAE\u4FE1\u516C\u4F17\u5E73\u53F0\uFF0C\u70B9\u51FB\u8BBE\u7F6E>\u516C\u4F17\u53F7\u8BBE\u7F6E>\u529F\u80FD\u8BBE\u7F6E\uFF0C\u586B\u5199\u7F51\u9875\u6388\u6743\u57DF\u540D ",-1),bu=d("\u4FDD\u5B58"),me=R({__name:"config",setup(Vu){const l=N({name:"",primaryId:" ",qrCode:"",appId:"",appSecret:"",url:"",token:"",encodingAesKey:"",encryptionType:1,businessDomain:"",jsDomain:"",webDomain:""}),{copy:m}=K(),_=async()=>{const B=await T();for(const o in l)l[o]=B[o]},c=async()=>{await j(l),_(),q.msgSuccess("\u64CD\u4F5C\u6210\u529F")};return _(),(B,o)=>{const D=w,a=g,n=k,F=I,C=x,i=S,r=y,f=h,A=U,v=V,b=$("perms");return E(),L("div",null,[u(a,{class:"!border-none",shadow:"never"},{default:t(()=>[u(D,{type:"warning",title:"\u6E29\u99A8\u63D0\u793A\uFF1A\u586B\u5199\u5FAE\u4FE1\u516C\u4F17\u53F7\u5F00\u53D1\u914D\u7F6E\uFF0C\u8BF7\u524D\u5F80\u5FAE\u4FE1\u516C\u4F17\u5E73\u53F0\u7533\u8BF7\u670D\u52A1\u53F7\u5E76\u5B8C\u6210\u8BA4\u8BC1",closable:!1,"show-icon":""})]),_:1}),u(A,{ref:"formRef",model:l,"label-width":"160px"},{default:t(()=>[u(a,{class:"!border-none mt-4",shadow:"never"},{default:t(()=>[J,u(F,{label:"\u516C\u4F17\u53F7\u540D\u79F0",prop:"name"},{default:t(()=>[e("div",M,[u(n,{modelValue:l.name,"onUpdate:modelValue":o[0]||(o[0]=s=>l.name=s),placeholder:"\u8BF7\u8F93\u5165\u516C\u4F17\u53F7\u540D\u79F0"},null,8,["modelValue"])])]),_:1}),u(F,{label:"\u539F\u59CBID",prop:"primaryId"},{default:t(()=>[e("div",z,[u(n,{modelValue:l.primaryId,"onUpdate:modelValue":o[1]||(o[1]=s=>l.primaryId=s),placeholder:"\u8BF7\u8F93\u5165\u539F\u59CBID"},null,8,["modelValue"])])]),_:1}),u(F,{label:"\u516C\u4F17\u53F7\u4E8C\u7EF4\u7801",prop:"qrCode"},{default:t(()=>[e("div",null,[e("div",null,[u(C,{modelValue:l.qrCode,"onUpdate:modelValue":o[2]||(o[2]=s=>l.qrCode=s),limit:1},null,8,["modelValue"])]),Z])]),_:1})]),_:1}),u(a,{class:"!border-none mt-4",shadow:"never"},{default:t(()=>[H,u(F,{label:"AppID",prop:"appId"},{default:t(()=>[e("div",P,[u(n,{modelValue:l.appId,"onUpdate:modelValue":o[3]||(o[3]=s=>l.appId=s),placeholder:"\u8BF7\u8F93\u5165AppID"},null,8,["modelValue"])])]),_:1}),u(F,{label:"AppSecret",prop:"appSecret"},{default:t(()=>[e("div",null,[e("div",Q,[u(n,{modelValue:l.appSecret,"onUpdate:modelValue":o[4]||(o[4]=s=>l.appSecret=s),placeholder:"\u8BF7\u8F93\u5165AppSecret"},null,8,["modelValue"])])])]),_:1}),u(F,null,{default:t(()=>[W]),_:1})]),_:1}),u(a,{class:"!border-none mt-4",shadow:"never"},{default:t(()=>[X,u(F,{label:"URL"},{default:t(()=>[e("div",null,[e("div",Y,[e("div",uu,[u(n,{modelValue:l.url,"onUpdate:modelValue":o[5]||(o[5]=s=>l.url=s),disabled:""},null,8,["modelValue"])]),u(i,{onClick:o[6]||(o[6]=s=>p(m)(l.url))},{default:t(()=>[eu]),_:1})]),ou])]),_:1}),u(F,{label:"Token",prop:"Token"},{default:t(()=>[e("div",null,[e("div",lu,[u(n,{modelValue:l.token,"onUpdate:modelValue":o[7]||(o[7]=s=>l.token=s),placeholder:"\u8BF7\u8F93\u5165Token"},null,8,["modelValue"])]),tu])]),_:1}),u(F,{label:"EncodingAESKey",prop:"EncodingAESKey"},{default:t(()=>[e("div",null,[e("div",su,[u(n,{modelValue:l.encodingAesKey,"onUpdate:modelValue":o[8]||(o[8]=s=>l.encodingAesKey=s),placeholder:"\u8BF7\u8F93\u5165EncodingAESKey"},null,8,["modelValue"])]),Fu])]),_:1}),u(F,{label:"\u6D88\u606F\u52A0\u5BC6\u65B9\u5F0F",required:"",prop:"status"},{default:t(()=>[e("div",null,[u(f,{class:"flex-col !items-start",modelValue:l.encryptionType,"onUpdate:modelValue":o[9]||(o[9]=s=>l.encryptionType=s)},{default:t(()=>[u(r,{label:1},{default:t(()=>[nu]),_:1}),u(r,{label:2},{default:t(()=>[du]),_:1}),u(r,{label:3},{default:t(()=>[au]),_:1})]),_:1},8,["modelValue"])])]),_:1})]),_:1}),u(a,{class:"!border-none mt-4",shadow:"never"},{default:t(()=>[iu,u(F,{label:"\u4E1A\u52A1\u57DF\u540D"},{default:t(()=>[e("div",null,[e("div",mu,[e("div",pu,[u(n,{modelValue:l.businessDomain,"onUpdate:modelValue":o[10]||(o[10]=s=>l.businessDomain=s),disabled:""},null,8,["modelValue"])]),u(i,{onClick:o[11]||(o[11]=s=>p(m)(l.businessDomain))},{default:t(()=>[ru]),_:1})]),_u])]),_:1}),u(F,{label:"JS\u63A5\u53E3\u5B89\u5168\u57DF\u540D"},{default:t(()=>[e("div",null,[e("div",Bu,[e("div",Eu,[u(n,{modelValue:l.jsDomain,"onUpdate:modelValue":o[12]||(o[12]=s=>l.jsDomain=s),disabled:""},null,8,["modelValue"])]),u(i,{onClick:o[13]||(o[13]=s=>p(m)(l.jsDomain))},{default:t(()=>[cu]),_:1})]),Du])]),_:1}),u(F,{label:"\u7F51\u9875\u6388\u6743\u57DF\u540D"},{default:t(()=>[e("div",null,[e("div",Cu,[e("div",fu,[u(n,{modelValue:l.webDomain,"onUpdate:modelValue":o[14]||(o[14]=s=>l.webDomain=s),disabled:""},null,8,["modelValue"])]),u(i,{onClick:o[15]||(o[15]=s=>p(m)(l.webDomain))},{default:t(()=>[Au]),_:1})]),vu])]),_:1})]),_:1})]),_:1},8,["model"]),O((E(),G(v,null,{default:t(()=>[u(i,{type:"primary",onClick:c},{default:t(()=>[bu]),_:1})]),_:1})),[[b,["channel:h5:save"]]])])}}});export{me as default};