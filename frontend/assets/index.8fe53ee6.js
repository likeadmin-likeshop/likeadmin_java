var Z=Object.defineProperty,ee=Object.defineProperties;var te=Object.getOwnPropertyDescriptors;var S=Object.getOwnPropertySymbols;var oe=Object.prototype.hasOwnProperty,se=Object.prototype.propertyIsEnumerable;var M=(e,t,o)=>t in e?Z(e,t,{enumerable:!0,configurable:!0,writable:!0,value:o}):e[t]=o,b=(e,t)=>{for(var o in t||(t={}))oe.call(t,o)&&M(e,o,t[o]);if(S)for(var o of S(t))se.call(t,o)&&M(e,o,t[o]);return e},V=(e,t)=>ee(e,te(t));import{d as A,t as ne,c as g,r as u,a as E,b as R,w as _,e as J,o as m,f as p,n as N,g as O,F as H,h as Q,i as l,j as F,k as re,l as ie,E as T,m as ae,R as G,p as ce,u as ue,q as le,s as _e,v as de,x as pe,y as me,z as fe,N as j,A as he,B as ve,C as q,D as ge,U as ye,G as Ee,H as we,I as be,J as $e,K as Ae,L as Pe,M as Re,O as De,P as Le,Q as ke,S as Ie,T as Oe}from"./vendor.bbaa8c82.js";const Te=function(){const t=document.createElement("link").relList;if(t&&t.supports&&t.supports("modulepreload"))return;for(const n of document.querySelectorAll('link[rel="modulepreload"]'))s(n);new MutationObserver(n=>{for(const i of n)if(i.type==="childList")for(const r of i.addedNodes)r.tagName==="LINK"&&r.rel==="modulepreload"&&s(r)}).observe(document,{childList:!0,subtree:!0});function o(n){const i={};return n.integrity&&(i.integrity=n.integrity),n.referrerpolicy&&(i.referrerPolicy=n.referrerpolicy),n.crossorigin==="use-credentials"?i.credentials="include":n.crossorigin==="anonymous"?i.credentials="omit":i.credentials="same-origin",i}function s(n){if(n.ep)return;n.ep=!0;const i=o(n);fetch(n.href,i)}};Te();const je={token:e=>e.user.token,userInfo:e=>e.user.user,config:e=>e.app.config,permission:e=>e.user.permissions},Ce=1,Ve="1.0.0";function xe(e,t=1e3,o){let s=new Date(0).getTime();return function(...n){const i=new Date().getTime();if(i-s>t)return s=i,e.apply(o,n)}}function Fe(e){const t={};for(const o of e.split("&"))t[o.split("=")[0]]=o.split("=")[1];return t}function Se(e=[],t=[],o="children"){return e.forEach(s=>{const n=s[o];n?Se(n,t,o):t.push(s)}),t}const Me="modulepreload",B={},Ne="/",c=function(t,o){return!o||o.length===0?t():Promise.all(o.map(s=>{if(s=`${Ne}${s}`,s in B)return;B[s]=!0;const n=s.endsWith(".css"),i=n?'[rel="stylesheet"]':"";if(document.querySelector(`link[href="${s}"]${i}`))return;const r=document.createElement("link");if(r.rel=n?"stylesheet":Me,n||(r.as="script",r.crossOrigin=""),r.href=s,document.head.appendChild(r),n)return new Promise((d,f)=>{r.addEventListener("load",d),r.addEventListener("error",f)})})).then(()=>t())};function qe(e){return/([^?&=]+)=([^?&=]*)/g.test(e)}var P=(e,t)=>{const o=e.__vccOpts||e;for(const[s,n]of t)o[s]=n;return o};const Be=A({components:{},props:{route:{type:Object,default:()=>({})},path:{type:String}},setup(e){const{path:t,route:o}=ne(e),s=g(()=>{var d;return!!((d=o.value.children)!=null?d:[]).filter(f=>{var v;return!((v=f.meta)==null?void 0:v.hidden)}).length}),n=g(()=>r=>r!==void 0?`${t.value}/${r}`:t.value),i=g(()=>{const r=o.value.query;try{return qe(r)?Fe(r):JSON.parse(r)}catch{}});return{hasChildren:s,resolvePath:n,resolveQuery:i}}}),Ue={key:0};function ze(e,t,o,s,n,i){const r=u("sub-menu",!0),d=u("el-sub-menu"),f=u("el-menu-item"),v=u("router-link");return e.route.meta.hidden?J("",!0):(m(),E("div",Ue,[e.hasChildren?(m(),R(d,{key:0,index:e.path},{title:_(()=>[p("i",{class:N(["iconfont m-r-10 icon-szie",e.route.meta.icon])},null,2),p("span",null,O(e.route.meta.title),1)]),default:_(()=>[(m(!0),E(H,null,Q(e.route.children,(y,w)=>(m(),R(r,{key:w,route:y,path:e.resolvePath(y.path)},null,8,["route","path"]))),128))]),_:1},8,["index"])):(m(),R(v,{key:1,to:{path:e.path,query:e.resolveQuery}},{default:_(()=>[l(f,{index:e.path},{default:_(()=>[p("i",{class:N(["iconfont m-r-10",e.route.meta.icon])},null,2),p("span",null,O(e.route.meta.title),1)]),_:1},8,["index"])]),_:1},8,["to"]))]))}var Ke=P(Be,[["render",ze],["__scopeId","data-v-298a41e8"]]);const Je=A({components:{SubMenu:Ke},setup(){const{store:e,route:t}=C(),o=g(()=>e.state.permission.sidebar),s=g(()=>{var i,r;return(r=(i=t.meta)==null?void 0:i.activeMenu)!=null?r:t.path});return{config:g(()=>e.getters.config),sidebar:o,currentPath:s}}}),He={class:"layout-aside"},Qe=["src"],Ge={class:"line-1"},We={class:"scrollbar-wrap"};function Xe(e,t,o,s,n,i){const r=u("router-link"),d=u("sub-menu"),f=u("el-menu"),v=u("el-scrollbar");return m(),E("div",He,[l(r,{to:"/workbench",class:"logo flex col-center"},{default:_(()=>[p("img",{class:"logo-img",src:e.config.webLogo,alt:""},null,8,Qe),p("div",Ge,O(e.config.webName),1)]),_:1}),p("div",We,[l(v,{style:{height:"100%"},class:"ls-scrollbar"},{default:_(()=>[l(f,{"active-text-color":"#fff","background-color":"#2a2c41","default-active":e.currentPath,"text-color":"#E5E5E5"},{default:_(()=>[(m(!0),E(H,null,Q(e.sidebar,(y,w)=>(m(),R(d,{key:w,route:y,path:y.path},null,8,["route","path"]))),128))]),_:1},8,["default-active"])]),_:1})])])}var Ye=P(Je,[["render",Xe],["__scopeId","data-v-0915f7a2"]]);const Ze=A({name:"layout-main",setup(){const{route:e}=C();return{keepAlive:g(()=>e.meta.keepAlive)}}}),et={class:"layout-main"},tt={class:"p-15"};function ot(e,t,o,s,n,i){const r=u("router-view"),d=u("el-scrollbar");return m(),E("div",et,[l(d,null,{default:_(()=>[p("div",tt,[l(r)])]),_:1})])}var st=P(Ze,[["render",ot],["__scopeId","data-v-acd8804e"]]);const nt=A({setup(){const{store:e,router:t}=C(),o=g(()=>e.getters.userInfo);return console.log("____userInfo____",o),{userInfo:o,handleCommand:n=>{switch(n){case"logout":e.dispatch("user/logout").then(()=>{t.push("/login"),e.commit("permission/setPermission",{auth:null,root:0})})}}}}}),rt={class:"layout-header"},it={class:"admin-info flex flex-center m-l-40"},at={class:"m-l-10"},ct={class:"flex flex-center"},ut=F("\u4E2A\u4EBA\u8BBE\u7F6E"),lt=F("\u9000\u51FA\u767B\u5F55");function _t(e,t,o,s,n,i){const r=u("el-avatar"),d=u("arrow-down"),f=u("el-icon"),v=u("el-dropdown-item"),y=u("router-link"),w=u("el-dropdown-menu"),Y=u("el-dropdown");return m(),E("div",rt,[p("div",it,[l(r,{size:40,src:e.userInfo.avatar},null,8,["src"]),p("div",at,[l(Y,{trigger:"hover",onCommand:e.handleCommand},{dropdown:_(()=>[l(w,null,{default:_(()=>[l(y,{to:"/setting/personal_data"},{default:_(()=>[l(v,null,{default:_(()=>[ut]),_:1})]),_:1})]),_:1}),l(w,null,{default:_(()=>[l(v,{command:"logout"},{default:_(()=>[lt]),_:1})]),_:1})]),default:_(()=>[p("div",ct,[F(O(e.userInfo.username)+" ",1),l(f,{class:"el-icon--right"},{default:_(()=>[l(d)]),_:1})])]),_:1},8,["onCommand"])])])])}var dt=P(nt,[["render",_t],["__scopeId","data-v-3aaf4064"]]);const pt=A({components:{LayoutAside:Ye,LayoutMain:st,LayoutHeader:dt}}),mt={class:"layout"},ft={class:"aside"},ht={class:"main"};function vt(e,t,o,s,n,i){const r=u("layout-aside"),d=u("layout-header"),f=u("layout-main");return m(),E("div",mt,[p("div",ft,[l(r)]),p("div",ht,[l(d),l(f)])])}var U=P(pt,[["render",vt]]);const gt=[{path:"/",redirect:"workbench",name:"index",component:U},{path:"/permission",component:U,children:[{path:"admin/edit",component:()=>c(()=>import("./edit.5636a82d.js"),["assets/edit.5636a82d.js","assets/index.7e8816f7.js","assets/index.f6096b4e.css","assets/vendor.bbaa8c82.js","assets/index.99efea29.js","assets/index.7aa9d0be.css","assets/index.d57d86d3.js","assets/index.104ca5ab.css","assets/index.a653a24e.js","assets/index.561c5e64.css"]),meta:{title:"\u7F16\u8F91\u7BA1\u7406\u5458",activeMenu:"/permission/admin"}},{path:"menu/edit",component:()=>c(()=>import("./edit.5a8f0793.js"),["assets/edit.5a8f0793.js","assets/index.a653a24e.js","assets/index.561c5e64.css","assets/vendor.bbaa8c82.js","assets/index.0cd0b878.js","assets/index.c8f32c75.css"]),meta:{title:"\u7F16\u8F91\u83DC\u5355",activeMenu:"/permission/menu"}},{path:"role/edit",component:()=>c(()=>import("./edit.5e077056.js"),["assets/edit.5e077056.js","assets/edit.aa0c3a7c.css","assets/index.a653a24e.js","assets/index.561c5e64.css","assets/vendor.bbaa8c82.js"]),meta:{title:"\u7F16\u8F91\u89D2\u8272",activeMenu:"/permission/role"}}]},{path:"/login",component:()=>c(()=>import("./login.2a52ba2a.js"),["assets/login.2a52ba2a.js","assets/login.b806d417.css","assets/vendor.bbaa8c82.js"])},{path:"/500",component:()=>c(()=>import("./500.a34f10f2.js"),["assets/500.a34f10f2.js","assets/error.184b04f4.js","assets/error.af0fe351.css","assets/vendor.bbaa8c82.js"])},{path:"/:pathMatch(.*)*",component:()=>c(()=>import("./404.fa902307.js"),["assets/404.fa902307.js","assets/error.184b04f4.js","assets/error.af0fe351.css","assets/vendor.bbaa8c82.js"])}],$=re({history:ie("/"),routes:gt,scrollBehavior(e,t,o){return o||{top:0}}}),k={key:"like_admin_",set(e,t,o){e=this.getKey(e);let s={expire:o?this.time()+o:"",value:t};typeof s=="object"&&(s=JSON.stringify(s));try{window.localStorage.setItem(e,s)}catch{return!1}},get(e){e=this.getKey(e);try{const t=window.localStorage.getItem(e);if(!t)return!1;const{value:o,expire:s}=JSON.parse(t);return s&&s<this.time()?(window.localStorage.removeItem(e),!1):o}catch{return!1}},time(){return Math.round(new Date().getTime()/1e3)},remove(e){e=this.getKey(e),window.localStorage.removeItem(e)},getKey(e){return this.key+e}},I="token",eo="account",L={success:({show:e,msg:t,data:o})=>o,error:({msg:e})=>(console.log(e,"msg"),T({type:"error",message:e}),Promise.reject(e)),redirect:xe(()=>(h.commit("user/setToken",""),h.commit("user/setUser",{}),k.remove(I),$.push("/login"),Promise.reject())),page:({data:e})=>(window.location.href=e.url,e)},a=ae.create({baseURL:"https://likeadmin-java-api.yixiangonline.com/api",timeout:60*1e3,headers:{"Content-Type":"application/json",version:Ve}});a.interceptors.request.use(e=>{const t=h.getters.token;return e.headers&&(e.headers.token=t),e},e=>Promise.reject(e));a.interceptors.response.use(e=>{switch(e.data.code){case 200:return L.success(e.data);case 300:case 310:case 311:case 312:case 313:case 314:case 403:case 404:case 500:case 330:case 331:return L.error(e.data);case-1:case 332:case 333:return L.redirect();case 2:return L.page(e.data)}},e=>(console.log(e),T({type:"error",message:e}),Promise.reject(e)));function to(e){return a.post("/album/cateAdd",e)}function oo(e){return a.post("/album/cateRename",e)}function so(e){return a.post("/album/cateDel",e)}function no(e){return a.get("/album/cateList",{params:e})}function ro(e){return a.get("/album/albumList",{params:e})}function io(e){return a.post("/album/albumDel",e)}function ao(e){return a.post("/album/albumMove",e)}function yt(){return a.get("/index/config")}const Et={namespaced:!0,state:{config:{}},mutations:{setConfig(e,t){e.config=t}},actions:{getConfig({commit:e}){return new Promise((t,o)=>{yt().then(s=>{e("setConfig",s),t(s)})})}}};function co(e){return a.get("/system/admin/list",{params:e})}function uo(e){return a.post("/system/admin/add",e)}function lo(e){return a.post("/system/admin/edit",e)}function _o(e){return a.post("/system/admin/del",e)}function po(e){return a.get("/system/admin/detail",{params:e})}function mo(e){return a.post("/system/admin/disable",e)}function fo(e){return a.get("/system/role/list",{params:e})}function ho(e){return a.post("/system/role/add",b({},e))}function vo(e){return a.post("/system/role/edit",b({},e))}function go(e){return a.post("/system/role/del",b({},e))}function yo(e){return a.get("/system/role/detail",{params:e})}function Eo(){return a.get("/system/menu/list")}function wt(){return a.get("/system/menu/route")}function wo(e){return a.get("/system/menu/detail",{params:e})}function bo(e){return a.post("/system/menu/add",e)}function $o(e){return a.post("/system/menu/edit",e)}function Ao(e){return a.post("/system/menu/del",e)}const x={"/src/views/account/login.vue":()=>c(()=>import("./login.2a52ba2a.js"),["assets/login.2a52ba2a.js","assets/login.b806d417.css","assets/vendor.bbaa8c82.js"]),"/src/views/error/404.vue":()=>c(()=>import("./404.fa902307.js"),["assets/404.fa902307.js","assets/error.184b04f4.js","assets/error.af0fe351.css","assets/vendor.bbaa8c82.js"]),"/src/views/error/500.vue":()=>c(()=>import("./500.a34f10f2.js"),["assets/500.a34f10f2.js","assets/error.184b04f4.js","assets/error.af0fe351.css","assets/vendor.bbaa8c82.js"]),"/src/views/workbench/index.vue":()=>c(()=>import("./index.3b792308.js"),["assets/index.3b792308.js","assets/index.2b465026.css","assets/avatar.b64ff0bc.js","assets/vendor.bbaa8c82.js"]),"/src/views/error/components/error.vue":()=>c(()=>import("./error.184b04f4.js"),["assets/error.184b04f4.js","assets/error.af0fe351.css","assets/vendor.bbaa8c82.js"]),"/src/views/permission/admin/edit.vue":()=>c(()=>import("./edit.5636a82d.js"),["assets/edit.5636a82d.js","assets/index.7e8816f7.js","assets/index.f6096b4e.css","assets/vendor.bbaa8c82.js","assets/index.99efea29.js","assets/index.7aa9d0be.css","assets/index.d57d86d3.js","assets/index.104ca5ab.css","assets/index.a653a24e.js","assets/index.561c5e64.css"]),"/src/views/permission/admin/index.vue":()=>c(()=>import("./index.497ed047.js"),["assets/index.497ed047.js","assets/index.46b086ef.css","assets/index.d57d86d3.js","assets/index.104ca5ab.css","assets/vendor.bbaa8c82.js","assets/index.99efea29.js","assets/index.7aa9d0be.css","assets/avatar.b64ff0bc.js"]),"/src/views/permission/menu/edit.vue":()=>c(()=>import("./edit.5a8f0793.js"),["assets/edit.5a8f0793.js","assets/index.a653a24e.js","assets/index.561c5e64.css","assets/vendor.bbaa8c82.js","assets/index.0cd0b878.js","assets/index.c8f32c75.css"]),"/src/views/permission/menu/index.vue":()=>c(()=>import("./index.0997d009.js"),["assets/index.0997d009.js","assets/index.99efea29.js","assets/index.7aa9d0be.css","assets/vendor.bbaa8c82.js"]),"/src/views/permission/role/edit.vue":()=>c(()=>import("./edit.5e077056.js"),["assets/edit.5e077056.js","assets/edit.aa0c3a7c.css","assets/index.a653a24e.js","assets/index.561c5e64.css","assets/vendor.bbaa8c82.js"]),"/src/views/permission/role/index.vue":()=>c(()=>import("./index.f11411b1.js"),["assets/index.f11411b1.js","assets/index.d57d86d3.js","assets/index.104ca5ab.css","assets/vendor.bbaa8c82.js","assets/index.99efea29.js","assets/index.7aa9d0be.css"]),"/src/views/setting/personal/personal_data.vue":()=>c(()=>import("./personal_data.de6bf0ce.js"),["assets/personal_data.de6bf0ce.js","assets/index.7e8816f7.js","assets/index.f6096b4e.css","assets/vendor.bbaa8c82.js","assets/index.99efea29.js","assets/index.7aa9d0be.css","assets/index.d57d86d3.js","assets/index.104ca5ab.css","assets/index.a653a24e.js","assets/index.561c5e64.css"]),"/src/views/setting/service/online_service.vue":()=>c(()=>import("./online_service.7dc60492.js"),["assets/online_service.7dc60492.js","assets/online_service.68ccc7e3.css","assets/index.7e8816f7.js","assets/index.f6096b4e.css","assets/vendor.bbaa8c82.js","assets/index.99efea29.js","assets/index.7aa9d0be.css","assets/index.d57d86d3.js","assets/index.104ca5ab.css","assets/index.a653a24e.js","assets/index.561c5e64.css","assets/setting.e1c98f73.js"]),"/src/views/setting/system/cache.vue":()=>c(()=>import("./cache.abac1a65.js"),["assets/cache.abac1a65.js","assets/cache.c6309f89.css","assets/setting.e1c98f73.js","assets/vendor.bbaa8c82.js"]),"/src/views/setting/system/environment.vue":()=>c(()=>import("./environment.c7a94c73.js"),["assets/environment.c7a94c73.js","assets/setting.e1c98f73.js","assets/vendor.bbaa8c82.js"]),"/src/views/setting/system/journal.vue":()=>c(()=>import("./journal.b7a38096.js"),["assets/journal.b7a38096.js","assets/setting.e1c98f73.js","assets/index.d57d86d3.js","assets/index.104ca5ab.css","assets/vendor.bbaa8c82.js"]),"/src/views/setting/user/index.vue":()=>c(()=>import("./index.e427a091.js"),["assets/index.e427a091.js","assets/setting.e1c98f73.js","assets/index.7e8816f7.js","assets/index.f6096b4e.css","assets/vendor.bbaa8c82.js","assets/index.99efea29.js","assets/index.7aa9d0be.css","assets/index.d57d86d3.js","assets/index.104ca5ab.css","assets/index.a653a24e.js","assets/index.561c5e64.css"]),"/src/views/setting/user/login.vue":()=>c(()=>import("./login.726c6f4e.js"),["assets/login.726c6f4e.js","assets/login.5c00f21e.css","assets/setting.e1c98f73.js","assets/index.a653a24e.js","assets/index.561c5e64.css","assets/vendor.bbaa8c82.js"]),"/src/views/setting/website/filing.vue":()=>c(()=>import("./filing.08c5446d.js"),["assets/filing.08c5446d.js","assets/setting.e1c98f73.js","assets/index.a653a24e.js","assets/index.561c5e64.css","assets/vendor.bbaa8c82.js"]),"/src/views/setting/website/information.vue":()=>c(()=>import("./information.dc2c0f1d.js"),["assets/information.dc2c0f1d.js","assets/index.7e8816f7.js","assets/index.f6096b4e.css","assets/vendor.bbaa8c82.js","assets/index.99efea29.js","assets/index.7aa9d0be.css","assets/index.d57d86d3.js","assets/index.104ca5ab.css","assets/index.a653a24e.js","assets/index.561c5e64.css","assets/setting.e1c98f73.js"]),"/src/views/setting/website/protocol.vue":()=>c(()=>import("./protocol.36e88292.js"),["assets/protocol.36e88292.js","assets/protocol.2bacd504.css","assets/setting.e1c98f73.js","assets/vendor.bbaa8c82.js","assets/index.7e8816f7.js","assets/index.f6096b4e.css","assets/index.99efea29.js","assets/index.7aa9d0be.css","assets/index.d57d86d3.js","assets/index.104ca5ab.css","assets/index.a653a24e.js","assets/index.561c5e64.css"]),"/src/views/permission/menu/select-icon/index.vue":()=>c(()=>import("./index.0cd0b878.js"),["assets/index.0cd0b878.js","assets/index.c8f32c75.css","assets/vendor.bbaa8c82.js"])};function W(e,t=!0){return e.map(o=>{const s=bt(o,t);return o.children!=null&&o.children&&o.children.length&&(s.children=W(o.children,!1)),s})}function bt(e,t){const o={path:t?`/${e.paths}`:e.paths,name:e.paths,meta:{hidden:!e.isShow,keepAlive:!!e.isCache,title:e.menuName,perms:e.perms,query:e.params,icon:e.menuIcon,activePath:e.selected}};switch(e.menuType){case"M":o.component=G;break;case"C":o.component=$t(e.component);break}return o}console.log(x);function $t(e){try{const t=Object.keys(x).find(o=>o.includes(`${e}.vue`));if(t)return x[t];throw Error(`\u627E\u4E0D\u5230\u7EC4\u4EF6${e}\uFF0C\u8BF7\u786E\u4FDD\u7EC4\u4EF6\u8DEF\u5F84\u6B63\u786E`)}catch(t){return console.error(t),G}}const At={namespaced:!0,state:{routes:[],sidebar:[]},getters:{},mutations:{setSidebar(e,t){e.sidebar=t}},actions:{generateRoutes({commit:e}){return new Promise((t,o)=>{wt().then(s=>{const n=JSON.parse(JSON.stringify(s)),i=W(n);e("setSidebar",i),t(i)}).catch(s=>{o(s)})})}}};function Pt(e){return a.post("/system/login",V(b({},e),{terminal:Ce}))}function Rt(){return a.post("/system/logout")}function Dt(){return a.get("/system/admin/self")}function Po(e){return a.post("/system/admin/upInfo",e)}const Lt={namespaced:!0,state:{token:k.get(I)||"",user:{},permissions:[]},mutations:{setToken(e,t){e.token=t,k.set(I,t)},setUser(e,t){e.user=t},setPermission(e,t){e.permissions=t}},actions:{login({commit:e},t){const{account:o,password:s}=t;return new Promise((n,i)=>{Pt({username:o,password:s}).then(r=>{e("setToken",r.token),n(r)}).catch(r=>{i(r)})})},logout({commit:e}){return new Promise((t,o)=>{Rt().then(s=>{e("setToken",""),e("setUser",{}),e("setPermission",[]),k.remove(I),t(s)}).catch(s=>{o(s)})})},getUser({commit:e}){return new Promise((t,o)=>{Dt().then(s=>{e("setUser",s.user),e("setPermission",s.permissions),console.log("___data.user___",s.user),t(s)}).catch(s=>{o(s)})})}}};var kt={app:Et,permission:At,user:Lt};const It=ce({modules:kt,getters:je}),X=Symbol("vue-store");function Ot(){return ue(X)}var h=It;function C(){const e=Ot(),t=le(),o=_e();return{store:e,route:t,router:o}}const Tt=A({setup(){const{store:e,route:t}=C(),o=de(!0);return pe("reload",()=>{o.value=!1,fe(()=>{o.value=!0})}),me(async()=>{const n=await e.dispatch("app/getConfig");console.log("data",n);let i=document.querySelector('link[rel="icon"]');if(i){i.href=n.webFavicon;return}i=document.createElement("link"),i.rel="icon",i.href=n.webFavicon,document.head.appendChild(i)}),{routerAlive:o}}});function jt(e,t,o,s,n,i){const r=u("router-view");return e.routerAlive?(m(),R(r,{key:0})):J("",!0)}var Ct=P(Tt,[["render",jt]]);j.configure({showSpinner:!1});const z="/login",Vt=["/login"];$.beforeEach(async(e,t,o)=>{var n;if(j.start(),((n=e.meta)==null?void 0:n.title)&&(document.title=e.meta.title),h.getters.token)if(h.getters.permission.length===0)try{await h.dispatch("user/getUser"),(await h.dispatch("permission/generateRoutes")).forEach(r=>{$.addRoute("index",r)}),console.log($.getRoutes()),e.path==="/login"?o({path:"/"}):o(V(b({},e),{replace:!0}))}catch{await h.dispatch("user/logout"),o({path:z,query:{redirect:e.fullPath}}),j.done()}else o();else Vt.includes(e.path)?o():o({path:z,query:{redirect:e.fullPath}})});$.afterEach(async(e,t)=>{j.done()});var xt=e=>{e.use(he,{zIndex:3e3,locale:ve}),Object.keys(q).forEach(t=>{e.component(t,q[t])})};ge([Ee,we,be,$e,Ae,Pe,Re,De,Le,ke]);var Ft=e=>{e.component("VChart",ye)};const St="#4a5dff",Mt="#67c23a",Nt="#fb9400",qt="#f56c6c",Bt="#909399",Ut="#333333",zt="#666666",Kt="#999999";var Jt={color_primary:St,color_success:Mt,color_warning:Nt,color_danger:qt,color_ingo:Bt,font_color_primary:Ut,font_color_regular:zt,font_color_secondary:Kt};(function(){const t=new Ie(".copy-btn");t.on("success",o=>{T.success("\u590D\u5236\u6210\u529F"),o.clearSelection()}),t.on("error",o=>{console.error(o),T.success("\u590D\u5236\u5931\u8D25")})})();var Ht={mounted:(e,t)=>{e.className=e.className+" copy-btn",e.setAttribute("data-clipboard-text",t.value)},updated:(e,t)=>{e.setAttribute("data-clipboard-text",t.value)}},Qt=Object.freeze({__proto__:null,[Symbol.toStringTag]:"Module",default:Ht}),Gt={mounted:(e,t)=>{const{value:o}=t,s=h.getters&&h.getters.permission,n="*";if(Array.isArray(o))o.length>0&&(s.some(r=>n==r||o.includes(r))||e.parentNode&&e.parentNode.removeChild(e));else throw new Error(`like v-perm="['system:admin:edit']`)}},Wt=Object.freeze({__proto__:null,[Symbol.toStringTag]:"Module",default:Gt});const K={"./modules/copy.ts":Qt,"./modules/perm.ts":Wt};var Xt=e=>{Object.keys(K).forEach(t=>{const o=t.replace(/^\.\/.*\/(.*)\.\w+$/,"$1");e.directive(o,K[t].default)})};const D=Oe(Ct);D.config.globalProperties.$variables=Jt;xt(D);Ft(D);Xt(D);D.use($).use(h,X).mount("#app");export{eo as A,_o as B,Ao as C,go as D,Dt as E,Po as F,P as _,fo as a,po as b,lo as c,uo as d,to as e,oo as f,so as g,no as h,io as i,ao as j,ro as k,Ot as l,wo as m,Eo as n,bo as o,$o as p,Se as q,ho as r,vo as s,yo as t,C as u,Ve as v,k as w,a as x,co as y,mo as z};
