import{x as v,_ as p}from"./index.13ae9900.js";import{_}from"./avatar.b64ff0bc.js";import{d as m,V as f,y as F,r as u,a as x,f as t,i as n,w as o,o as g,g as i,a7 as b,a8 as y}from"./vendor.bbaa8c82.js";function w(){return v.get("/index/console")}const E=m({setup(){const s=f({version:{version:"",website:""},today:{},menu:[],visitor:[],article:[],visitorOption:{xAxis:{type:"category",data:[0]},yAxis:{type:"value"},legend:{data:["\u8BBF\u95EE\u91CF"]},itemStyle:{color:"red"},tooltip:{trigger:"axis"},series:[{name:"\u8BBF\u95EE\u91CF",data:[0],type:"line"}]}}),d=()=>{w().then(e=>{console.log("res",e),s.version=e.version,s.today=e.today,s.menu=e.menu,s.visitor=e.visitor,s.article=e.article,s.visitorOption.xAxis.data=[],s.visitorOption.series[0].data=[],e.visitor.date.reverse().forEach(c=>{s.visitorOption.xAxis.data.push(c)}),e.visitor.list.forEach(c=>{s.visitorOption.series[0].data.push(c)}),console.log("res.visitor.list",e.visitor.list)}).catch(e=>{console.log("err",e)})};return F(()=>{d()}),{workbenchData:s,getWorkbench:d}}}),a=s=>(b("data-v-cb0e80e8"),s=s(),y(),s),B={class:"workbench"},k={class:"flex workbench-header"},D=a(()=>t("span",{class:"card-title"},"\u8D26\u53F7\u4FE1\u606F",-1)),A={class:"m-b-20"},C=a(()=>t("span",{class:"card-title"},"\u4ECA\u65E5\u6570\u636E",-1)),S={class:"muted xs m-l-15"},O={class:"flex"},V={class:"flex-1"},I=a(()=>t("div",{class:"lighter m-b-10"},"\u8BBF\u95EE\u91CF",-1)),$={class:"f-s-32 m-b-10"},N={class:"lighter"},U={class:"flex-1"},W=a(()=>t("div",{class:"lighter m-b-10"},"\u9500\u552E\u989D",-1)),q={class:"f-s-32 m-b-10"},M={class:"lighter"},j={class:"flex-1"},z=a(()=>t("div",{class:"lighter m-b-10"},"\u65B0\u589E\u7528\u6237",-1)),G={class:"f-s-32 m-b-10"},H={class:"lighter"},J={class:"m-t-15 function"},K=a(()=>t("span",{class:"card-title"},"\u5E38\u7528\u529F\u80FD",-1)),L={class:"nav-lists"},P=a(()=>t("div",{class:"nav-item flex-col m-t-10"},[t("div",{class:"flex flex-center"},[t("img",{style:{width:"48px",height:"48px"},src:_})]),t("div",{class:"m-t-8 normal text-center"},"\u5DE5\u4F5C\u53F0")],-1)),Q=a(()=>t("div",{class:"nav-item flex-col m-t-10"},[t("div",{class:"flex flex-center"},[t("img",{style:{width:"48px",height:"48px"},src:_})]),t("div",{class:"m-t-8 normal text-center"},"\u7F51\u7AD9\u4FE1\u606F")],-1)),R=a(()=>t("div",{class:"nav-item flex-col m-t-10"},[t("div",{class:"flex flex-center"},[t("img",{style:{width:"48px",height:"48px"},src:_})]),t("div",{class:"m-t-8 normal text-center"},"\u7CFB\u7EDF\u73AF\u5883")],-1)),T={class:"flex m-t-15 ranking"},X=a(()=>t("span",{class:"card-title"},"\u8BBF\u95EE\u91CF\u8D8B\u52BF\u56FE",-1)),Y={class:"ranking-centent"};function Z(s,d,e,c,tt,st){const l=u("el-card"),r=u("router-link"),h=u("v-chart");return g(),x("div",B,[t("div",k,[n(l,{class:"flex-1",shadow:"never"},{header:o(()=>[D]),default:o(()=>[t("div",null,[t("div",A,"\u7248\u672C\u53F7\uFF1A"+i(s.workbenchData.version.version),1),t("div",null,"\u5B98\u7F51\u540D\u79F0\uFF1A"+i(s.workbenchData.version.website),1)])]),_:1}),n(l,{class:"flex-3 m-l-15",shadow:"never"},{header:o(()=>[t("div",null,[C,t("span",S,"\u66F4\u65B0\u65F6\u95F4\uFF1A"+i(s.workbenchData.today.time),1)])]),default:o(()=>[t("div",O,[t("div",V,[I,t("div",$,i(s.workbenchData.today.todayVisits),1),t("div",N,"\u603B\u8BBF\u95EE\u91CF\uFF1A"+i(s.workbenchData.today.totalVisits),1)]),t("div",U,[W,t("div",q,i(s.workbenchData.today.todaySales),1),t("div",M,"\u603B\u9500\u552E\u989D\uFF1A"+i(s.workbenchData.today.totalSales),1)]),t("div",j,[z,t("div",G,i(s.workbenchData.today.todayUsers),1),t("div",H,"\u603B\u8BBF\u7528\u6237\uFF1A"+i(s.workbenchData.today.totalUsers),1)])])]),_:1})]),t("div",J,[n(l,{class:"flex-1",shadow:"never"},{header:o(()=>[K]),default:o(()=>[t("div",L,[n(r,{to:""},{default:o(()=>[P]),_:1}),n(r,{to:"/setting/website/information"},{default:o(()=>[Q]),_:1}),n(r,{to:"/setting/system/environment"},{default:o(()=>[R]),_:1})])]),_:1})]),t("div",T,[n(l,{class:"flex-1",shadow:"never"},{header:o(()=>[X]),default:o(()=>[t("div",Y,[n(h,{class:"chart",option:s.workbenchData.visitorOption},null,8,["option"])])]),_:1})])])}var it=p(E,[["render",Z],["__scopeId","data-v-cb0e80e8"]]);export{it as default};
