import{d as h}from"./setting.2f149cf4.js";import{d as F,V as f,v,y as D,r as _,a as b,f as s,i as e,w as o,o as g,g as l,ac as r}from"./vendor.bbaa8c82.js";import"./index.eb56d01c.js";const B={class:"system-environment"},C={class:"flex"},x=s("div",{class:"lg"},"CPU",-1),E={class:"m-t-15"},y={class:"flex"},A={class:"flex-1"},w={class:"f-s-32 m-b-10"},N=s("div",{class:"lighter"},"\u6838\u5FC3\u6570",-1),k={class:"flex-1"},z={class:"f-s-32 m-b-10"},j=s("div",{class:"lighter"},"\u7528\u6237\u4F7F\u7528\u7387",-1),I={class:"flex-1"},G={class:"f-s-32 m-b-10"},J=s("div",{class:"lighter"},"\u7CFB\u7EDF\u4F7F\u7528\u7387",-1),S={class:"flex-1"},T={class:"f-s-32 m-b-10"},V=s("div",{class:"lighter"},"\u5F53\u524D\u7A7A\u95F2\u7387",-1),P=s("div",{class:"lg"},"\u5185\u5B58",-1),M={class:"m-t-15"},U={class:"flex"},q={class:"flex-1"},H={class:"f-s-32 m-b-10"},K=s("div",{class:"lighter"},"\u603B\u5185\u5B58",-1),L={class:"flex-1"},O={class:"f-s-32 m-b-10"},Q=s("div",{class:"lighter"},"\u5DF2\u7528\u5185\u5B58",-1),R={class:"flex-1"},W={class:"f-s-32 m-b-10"},X=s("div",{class:"lighter"},"\u5269\u4F59\u5185\u5B58",-1),Y={class:"flex-1"},Z={class:"f-s-32 m-b-10"},$=s("div",{class:"lighter"},"\u4F7F\u7528\u7387",-1),ss=s("div",{class:"lg"},"\u670D\u52A1\u5668\u4FE1\u606F",-1),es={class:"m-t-15"},ts=s("div",{class:"lg"},"Java\u865A\u62DF\u673A\u4FE1\u606F",-1),os={class:"m-t-15"},as=s("div",{class:"lg"},"\u786C\u76D8\u72B6\u6001",-1),ls={class:"m-t-15"},ns=F({setup(us){const u=f({disk:[],serverFormData:[],jvmFormData:[]}),d=v({}),i=v({}),p=()=>{h().then(a=>{console.log("res",a),d.value=a.cpu,i.value=a.mem,u.disk=a.disk,u.serverFormData=[a.sys],u.jvmFormData=[a.jvm]}).catch(a=>{console.log("err",a)})};return D(()=>{p()}),(a,ds)=>{const m=_("el-form"),c=_("el-card"),t=_("el-table-column"),n=_("el-table");return g(),b("div",B,[s("div",C,[e(c,{class:"flex-1 m-r-15",shadow:"never"},{default:o(()=>[x,s("div",E,[e(m,{inline:!0,model:d.value,"label-width":"110px",size:"small"},{default:o(()=>[s("div",y,[s("div",A,[s("div",w,l(d.value.cpuNum||"-"),1),N]),s("div",k,[s("div",z,l(d.value.used||"-")+"%",1),j]),s("div",I,[s("div",G,l(d.value.sys||"-")+"%",1),J]),s("div",S,[s("div",T,l(d.value.free||"-")+"%",1),V])])]),_:1},8,["model"])])]),_:1}),e(c,{class:"flex-1",shadow:"never"},{default:o(()=>[P,s("div",M,[e(m,{inline:!0,model:i.value,"label-width":"110px",size:"small"},{default:o(()=>[s("div",U,[s("div",q,[s("div",H,l(i.value.total||"-")+"G",1),K]),s("div",L,[s("div",O,l(i.value.used||"-")+"G",1),Q]),s("div",R,[s("div",W,l(i.value.free||"-")+"G",1),X]),s("div",Y,[s("div",Z,l(i.value.usage||"-")+"%",1),$])])]),_:1},8,["model"])])]),_:1})]),e(c,{shadow:"never",class:"m-t-15"},{default:o(()=>[ss,s("div",es,[e(n,{data:r(u).serverFormData,size:"medium"},{default:o(()=>[e(t,{prop:"computerName",label:"\u670D\u52A1\u5668\u540D\u79F0"}),e(t,{prop:"computerIp",label:"\u670D\u52A1\u5668IP"}),e(t,{prop:"osName",label:"\u64CD\u4F5C\u7CFB\u7EDF"}),e(t,{prop:"osArch",label:"\u7CFB\u7EDF\u67B6\u6784"}),e(t,{prop:"userDir",label:"\u9879\u76EE\u8DEF\u5F84"})]),_:1},8,["data"])])]),_:1}),e(c,{shadow:"never",class:"m-t-15"},{default:o(()=>[ts,s("div",os,[e(n,{data:r(u).jvmFormData,size:"medium"},{default:o(()=>[e(t,{prop:"name",label:"Java\u540D\u79F0"}),e(t,{prop:"startTime",label:"\u542F\u52A8\u65F6\u95F4"}),e(t,{prop:"home",label:"\u5B89\u88C5\u8DEF\u5F84"}),e(t,{prop:"inputArgs",label:"\u8FD0\u884C\u53C2\u6570"}),e(t,{prop:"version",label:"Java\u7248\u672C"}),e(t,{prop:"runTime",label:"\u8FD0\u884C\u65F6\u957F"})]),_:1},8,["data"])])]),_:1}),e(c,{shadow:"never",class:"m-t-15"},{default:o(()=>[as,s("div",ls,[e(n,{data:r(u).disk,size:"medium"},{default:o(()=>[e(t,{prop:"dirName",label:"\u76D8\u7B26\u8DEF\u5F84"}),e(t,{prop:"sysTypeName",label:"\u6587\u4EF6\u7CFB\u7EDF"}),e(t,{prop:"typeName",label:"\u76D8\u7B26\u7C7B\u578B"}),e(t,{prop:"total",label:"\u603B\u5927\u5C0F"}),e(t,{prop:"free",label:"\u53EF\u4EE5\u5927\u5C0F"}),e(t,{prop:"used",label:"\u5DF2\u7528\u5927\u5C0F"}),e(t,{prop:"usage",label:"\u5DF2\u7528\u767E\u5206\u6BD4"})]),_:1},8,["data"])])]),_:1})])}}});export{ns as default};
