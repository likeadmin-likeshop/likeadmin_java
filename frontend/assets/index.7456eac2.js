var d=Object.defineProperty;var p=Object.getOwnPropertySymbols;var m=Object.prototype.hasOwnProperty,z=Object.prototype.propertyIsEnumerable;var u=(e,a,n)=>a in e?d(e,a,{enumerable:!0,configurable:!0,writable:!0,value:n}):e[a]=n,c=(e,a)=>{for(var n in a||(a={}))m.call(a,n)&&u(e,n,a[n]);if(p)for(var n of p(a))z.call(a,n)&&u(e,n,a[n]);return e};import{ab as f,V as h,d as C,r as P,o as y,a as v,i as S}from"./vendor.bbaa8c82.js";import{_ as V}from"./index.eb56d01c.js";let l={};function B(e){const{page:a=1,size:n=15,callback:g,params:r={}}=e;l=Object.assign({},f(r));const o=h({page:a,size:n,loading:!1,count:0,lists:[]}),s=()=>o.loading?Promise.reject():(o.loading=!0,g(c({pageNo:o.page,pageSize:o.size},r)).then(t=>(o.count=t==null?void 0:t.count,o.lists=t==null?void 0:t.lists,Promise.resolve(t))).catch(t=>Promise.reject(t)).finally(()=>{o.loading=!1}));return{pager:o,requestApi:s,resetParams:()=>{Object.keys(l).forEach(t=>{r[t]=l[t]}),s()},resetPage:()=>{o.page=1,s()}}}const _=C({components:{},props:{modelValue:{type:Object,default:()=>({})},pageSizes:{type:Array,default:()=>[10,20,30,40]},layout:{type:String,default:"total, sizes, prev, pager, next, jumper"}},emits:["change"],setup(e,{emit:a}){return{sizeChange:()=>{e.modelValue.page=1,a("change")},pageChange:()=>{a("change")}}}}),j={class:"pagination"};function b(e,a,n,g,r,o){const s=P("el-pagination");return y(),v("div",j,[S(s,{currentPage:e.modelValue.page,"onUpdate:currentPage":a[0]||(a[0]=i=>e.modelValue.page=i),pageSize:e.modelValue.size,"onUpdate:pageSize":a[1]||(a[1]=i=>e.modelValue.size=i),"page-sizes":e.pageSizes,layout:e.layout,total:e.modelValue.count,"hide-on-single-page":"",onSizeChange:e.sizeChange,onCurrentChange:e.pageChange},null,8,["currentPage","pageSize","page-sizes","layout","total","onSizeChange","onCurrentChange"])])}var E=V(_,[["render",b]]);export{E as P,B as u};
