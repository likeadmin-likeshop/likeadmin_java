import{c as f}from"./setting.8edb0bf7.js";import{_ as E}from"./index.0ca15b12.js";import{d as C,v as r,V as D,y as A,r as _,a as B,i as u,w as l,f as s,o as x,g as t,j as p,ac as m,a7 as b,a8 as y}from"./vendor.bbaa8c82.js";const a=c=>(b("data-v-43eb9f50"),c=c(),y(),c),g={class:"cache"},w=a(()=>s("div",{class:"m-b-20 lg"},"\u57FA\u672C\u4FE1\u606F",-1)),S={class:"flex basic-information"},O={class:"flex-1"},k=a(()=>s("span",{class:"m-r-40 nr"},"Redis\u7248\u672C",-1)),z={class:"flex-1"},I=a(()=>s("span",{class:"m-r-40 nr"},"\u8FD0\u884C\u6A21\u5F0F",-1)),V={class:"flex-1"},N=a(()=>s("span",{class:"m-r-40 nr"},"\u7AEF\u53E3",-1)),M={class:"flex-1"},P=a(()=>s("span",{class:"m-r-40 nr"},"\u5BA2\u6237\u7AEF\u6570",-1)),R={class:"flex basic-information"},j={class:"flex-1"},K=a(()=>s("span",{class:"m-r-40 nr"},"\u8FD0\u884C\u65F6\u95F4(\u5929)",-1)),L={class:"flex-1"},T=a(()=>s("span",{class:"m-r-40 nr"},"\u4F7F\u7528\u5185\u5B58",-1)),U={class:"flex-1"},X=a(()=>s("span",{class:"m-r-40 nr"},"\u4F7F\u7528CPU",-1)),q={class:"flex-1"},G=a(()=>s("span",{class:"m-r-40 nr"},"\u5185\u5B58\u914D\u7F6E",-1)),H={class:"flex basic-information"},J={class:"flex-1"},Q=a(()=>s("span",{class:"m-r-40 nr"},"AOF\u662F\u5426\u5F00\u542F",-1)),W={class:"flex-1"},Y=a(()=>s("span",{class:"m-r-40 nr"},"RDB\u662F\u5426\u6210\u529F",-1)),Z={class:"flex-1"},$=a(()=>s("span",{class:"m-r-40 nr"},"Key\u6570\u91CF",-1)),ss={class:"flex-1"},es=a(()=>s("span",{class:"m-r-40 nr"},"\u7F51\u7EDC\u5165\u53E3/\u51FA\u53E3",-1)),as=a(()=>s("span",null,"/",-1)),ts={class:"m-t-15 flex"},os=a(()=>s("div",{class:"p-b-60 lg"},"\u547D\u4EE4\u7EDF\u8BA1",-1)),us={class:"statistical-chart"},ns=a(()=>s("div",{class:"p-b-40 lg"},"\u5185\u5B58\u4FE1\u606F",-1)),ls={class:"statistical-chart"},cs=C({setup(c){r([{content:"\u7CFB\u7EDF\u7F13\u5B58",desc:"\u7CFB\u7EDF\u8FD0\u884C\u8FC7\u7A0B\u4E2D\u4EA7\u751F\u7684\u5404\u7C7B\u7F13\u5B58\u6570\u636E"}]);const e=r({}),n=D({commandChartOption:{tooltip:{trigger:"item"},series:[{label:{show:!0},labelLine:{show:!0},type:"pie",radius:"85%",color:["#0D47A1","#1565C0","#1976D2","#1E88E5","#2196F3","#42A5F5","#64B5F6","#90CAF9","#BBDEFB","#E3F2FD","#CAF0F8","#ADE8F4","#90E0EF","#48CAE4","#00B4D8","#0096C7","#0077B6","#023E8A","#03045E","#8ecae6","#98c1d9","#D9ED92","#B5E48C","#99D98C","#76C893","#52B69A","#34A0A4","#168AAD","#1A759F","#1E6091","#184E77","#457b9d"],data:[{value:"",name:""}],emphasis:{itemStyle:{shadowBlur:10,shadowOffsetX:0,shadowColor:"rgba(0, 0, 0, 0.5)"}}}]},memoryChartOption:{tooltip:{formatter:"{a} <br/>{b} : {c}%"},series:[{name:"Pressure",type:"gauge",radius:"100%",detail:{formatter:"{value}"},data:[{value:"",name:"\u5185\u5B58\u6D88\u8017"}]}]}}),h=async()=>{f({}).then(o=>{console.log(o),e.value=o.info,e.value.dbSize=o.dbSize||"",n.commandChartOption.series[0].data=o.commandStats,n.memoryChartOption.series[0].data[0].value=(o.info.used_memory/1024/1024).toFixed(2),n.memoryChartOption.series[0].detail.formatter="{value}M"}).catch(o=>{console.log("err",o)})};return A(()=>{h()}),(o,is)=>{const v=_("el-alert"),i=_("el-card"),F=_("el-form"),d=_("v-chart");return x(),B("div",g,[u(i,{shadow:"never"},{default:l(()=>[u(v,{class:"xxl",title:"\u6E29\u99A8\u63D0\u793A\uFF1A\u7BA1\u7406\u7CFB\u7EDF\u8FD0\u884C\u8FC7\u7A0B\u4E2D\u4EA7\u751F\u7684\u7F13\u5B58",type:"primary",closable:!1,"show-icon":""})]),_:1}),u(i,{class:"m-t-15",shadow:"never"},{default:l(()=>[s("div",null,[w,u(F,{inline:!0,model:e.value,size:"small"},{default:l(()=>[s("div",S,[s("div",O,[k,s("span",null,t(e.value.redis_version||"-"),1)]),s("div",z,[I,s("span",null,t(e.value.redis_mode||"-"),1)]),s("div",V,[N,s("span",null,t(e.value.tcp_port||"-"),1)]),s("div",M,[P,s("span",null,t(e.value.connected_clients||"-"),1)])]),s("div",R,[s("div",j,[K,s("span",null,t(e.value.uptime_in_days||"-"),1)]),s("div",L,[T,s("span",null,t(e.value.used_memory_human||"-"),1)]),s("div",U,[X,s("span",null,t(e.value.used_cpu_user_children||"-"),1)]),s("div",q,[G,s("span",null,t(e.value.maxmemory_human||"-"),1)])]),s("div",H,[s("div",J,[Q,s("span",null,t(e.value.aof_enabled=="0"?"\u5F00\u542F":"\u5173\u95ED"),1)]),s("div",W,[Y,s("span",null,t(e.value.rdb_last_bgsave_status=="ok"?"\u6210\u529F":"\u5931\u8D25"),1)]),s("div",Z,[$,s("span",null,t(e.value.dbSize||"-"),1)]),s("div",ss,[es,s("span",null,[p(t(e.value.instantaneous_input_kbps||"-")+" ",1),as,p(" "+t(e.value.instantaneous_output_kbps||"-"),1)])])])]),_:1},8,["model"])])]),_:1}),s("div",ts,[u(i,{class:"m-r-15 flex-1 test",shadow:"never"},{default:l(()=>[s("div",null,[os,s("div",us,[u(d,{class:"chart",option:m(n).commandChartOption},null,8,["option"])])])]),_:1}),u(i,{class:"flex-1",shadow:"never"},{default:l(()=>[s("div",null,[ns,s("div",ls,[u(d,{class:"chart",option:m(n).memoryChartOption},null,8,["option"])])])]),_:1})])])}}});var ps=E(cs,[["__scopeId","data-v-43eb9f50"]]);export{ps as default};
