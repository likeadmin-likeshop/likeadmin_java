import{d as a,z as e,m as s,W as o,at as t,o as l,c as r,w as d,b as u,l as p,t as m,h as i,aB as f,ai as n,k as c,r as b,a as w}from"./index.fe40b928.js";import{a as _,_ as x}from"./u-form-item.c826576b.js";import{_ as h}from"./u-form.0d3f4d0a.js";import{_ as v}from"./u-button.027407c6.js";import{_ as V}from"./plugin-vue_export-helper.21dcd24c.js";import"./u-icon.ecff0a89.js";import"./emitter.5b880544.js";var y=V(a({__name:"change_password",setup(a){const V=e(""),y=s({password:"",password2:""}),j=async()=>y.oldPassword||"set"==V.value?y.password?y.password2?y.password!=y.password2?uni.$u.toast("两次输入的密码不一致"):(await f(y),uni.$u.toast("操作成功"),void setTimeout((()=>{n()}),1e3)):uni.$u.toast("请输入确认密码"):uni.$u.toast("请输入密码"):uni.$u.toast("请输入原来的密码");return o((a=>{V.value=a.type||"","set"==V.value&&t({title:"设置登录密码"})})),(a,e)=>{const s=c,o=b(w("u-input"),_),t=b(w("u-form-item"),x),f=b(w("u-form"),h),n=b(w("u-button"),v);return l(),r(s,{class:"register bg-white min-h-full flex flex-col items-center px-[40rpx] pt-[100rpx] box-border"},{default:d((()=>[u(s,{class:"w-full"},{default:d((()=>[u(s,{class:"text-2xl font-medium mb-[60rpx]"},{default:d((()=>[p(m("set"==V.value?"设置登录密码":"修改登录密码"),1)])),_:1}),u(f,{borderBottom:"","label-width":150},{default:d((()=>["set"!=V.value?(l(),r(t,{key:0,label:"原密码",borderBottom:""},{default:d((()=>[u(o,{class:"flex-1",type:"password",modelValue:y.oldPassword,"onUpdate:modelValue":e[0]||(e[0]=a=>y.oldPassword=a),border:!1,placeholder:"请输入原来的密码"},null,8,["modelValue"])])),_:1})):i("v-if",!0),u(t,{label:"新密码",borderBottom:""},{default:d((()=>[u(o,{class:"flex-1",type:"password",modelValue:y.password,"onUpdate:modelValue":e[1]||(e[1]=a=>y.password=a),placeholder:"6-20位数字+字母或符号组合",border:!1},null,8,["modelValue"])])),_:1}),u(t,{label:"确认密码",borderBottom:""},{default:d((()=>[u(o,{class:"flex-1",type:"password",modelValue:y.password2,"onUpdate:modelValue":e[2]||(e[2]=a=>y.password2=a),placeholder:"再次输入新密码",border:!1},null,8,["modelValue"])])),_:1})])),_:1}),u(s,{class:"mt-[100rpx]"},{default:d((()=>[u(n,{type:"primary",shape:"circle",onClick:j},{default:d((()=>[p(" 确定 ")])),_:1})])),_:1})])),_:1})])),_:1})}}}),[["__scopeId","data-v-14e4bd27"]]);export{y as default};
