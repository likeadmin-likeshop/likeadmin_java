import{H as e,J as o,v as n,K as t,L as s,M as c,N as a}from"./index.21969731.js";const l=(e,o=!1,n)=>new Promise(((s,c)=>{let a=t();n&&(a=t().in(n)),a[o?"selectAll":"select"](e).boundingClientRect((function(e){return o&&Array.isArray(e)&&e.length||!o&&e?s(e):void c("找不到元素")})).exec()}));function i(){const e=n();return e[e.length-1]||{}}function r(e,o="navigateTo"){const n=e.query?`${e.path}?${function(e){let o="";for(const n of Object.keys(e)){const t=e[n],s=encodeURIComponent(n)+"=";if(!u(t))if(console.log(encodeURIComponent(n),a(t)),a(t)){for(const e of Object.keys(t))if(!u(t[e])){o+=encodeURIComponent(n+"["+e+"]")+"="+encodeURIComponent(t[e])+"&"}}else console.log(s+encodeURIComponent(t),"####"),o+=s+encodeURIComponent(t)+"&"}return o.slice(0,-1)}(e.query)}`:e.path;"navigateTo"==o&&s({url:n}),"reLaunch"==o&&c({url:n})}const u=e=>null==e&&void 0===e;function d(n){return new Promise(((t,s)=>{const c=e();o({url:"/api/upload/image",filePath:n,name:"file",header:{token:c},fileType:"image",success:e=>{console.log("uploadFile res ==> ",e);const o=JSON.parse(e.data);console.log("data.code",o.code),200==o.code?t(o.data):s()},fail:e=>{console.log(e),s()}})}))}export{i as c,l as g,r as n,d as u};
