<<<<<<<< HEAD:public/admin/assets/useDictOptions.b34b37f1.js
import"./index.64e62b67.js";import{a0 as p,t as f}from"./@vue.cab01781.js";function d(a){const s=p({}),e=Object.keys(a),i=e.map(o=>{const t=a[o];return s[o]=[],()=>t.api(f(t.params)||{})}),r=async()=>{(await Promise.allSettled(i.map(t=>t()))).forEach((t,u)=>{const n=e[u];if(t.status=="fulfilled"){const{transformData:c}=a[n],l=c?c(t.value):t.value;s[n]=l}})};return r(),{optionsData:s,refresh:r}}export{d as u};
========
import"./index.a80222dc.js";import{a0 as p,t as f}from"./@vue.cab01781.js";function d(a){const s=p({}),e=Object.keys(a),i=e.map(o=>{const t=a[o];return s[o]=[],()=>t.api(f(t.params)||{})}),r=async()=>{(await Promise.allSettled(i.map(t=>t()))).forEach((t,u)=>{const n=e[u];if(t.status=="fulfilled"){const{transformData:c}=a[n],l=c?c(t.value):t.value;s[n]=l}})};return r(),{optionsData:s,refresh:r}}export{d as u};
>>>>>>>> wjx202212300933:public/admin/assets/useDictOptions.5cafb1f4.js
