import{o as e,c as t,w as i,b as n,p as o,q as a,E as r,h as s,k as c,z as l,d,aa as u,u as p,X as f,m,A as g,P as h,ac as v,a3 as y,ad as _,ae as S,Y as w,L as k,g as x,l as b,t as I,e as T,F as C,x as M,a9 as L,af as V,K as P,v as A,ag as B,ah as O,ai as N,r as U,a as E,i as W,y as R}from"./index.a0d3e9bc.js";import{_ as D}from"./u-image.9e55ab2c.js";import{_ as j}from"./u-icon.c61604db.js";import{_ as J,a as Q}from"./u-form-item.b77e911f.js";import{_ as z}from"./u-verification-code.bc1f7d4a.js";import{_ as q}from"./u-form.119d728a.js";import{_ as $}from"./u-checkbox.5ae7a791.js";import{_ as F}from"./u-button.ed70d790.js";import{_ as Z}from"./plugin-vue_export-helper.21dcd24c.js";import{g as G,O as X,l as H}from"./account.eb8653d8.js";import{S as K}from"./appEnums.a2ba827b.js";import{i as Y,a as ee}from"./client.68d036db.js";import{c as te}from"./util.6435ff26.js";var ie=Z({name:"u-divider",props:{halfWidth:{type:[Number,String],default:150},borderColor:{type:String,default:"#dcdfe6"},type:{type:String,default:"primary"},color:{type:String,default:"#909399"},fontSize:{type:[Number,String],default:26},bgColor:{type:String,default:"#ffffff"},height:{type:[Number,String],default:"auto"},marginTop:{type:[String,Number],default:0},marginBottom:{type:[String,Number],default:0},useSlot:{type:Boolean,default:!0}},computed:{lineStyle(){let e={};return-1!=String(this.halfWidth).indexOf("%")?e.width=this.halfWidth:e.width=this.halfWidth+"rpx",this.borderColor&&(e.borderColor=this.borderColor),e}},methods:{click(){this.$emit("click")}}},[["render",function(l,d,u,p,f,m){const g=c;return e(),t(g,{class:"u-divider",style:a({height:"auto"==u.height?"auto":u.height+"rpx",backgroundColor:u.bgColor,marginBottom:u.marginBottom+"rpx",marginTop:u.marginTop+"rpx"}),onClick:m.click},{default:i((()=>[n(g,{class:o(["u-divider-line",[u.type?"u-divider-line--bordercolor--"+u.type:""]]),style:a([m.lineStyle])},null,8,["class","style"]),u.useSlot?(e(),t(g,{key:0,class:"u-divider-text",style:a({color:u.color,fontSize:u.fontSize+"rpx"})},{default:i((()=>[r(l.$slots,"default",{},void 0,!0)])),_:3},8,["style"])):s("v-if",!0),n(g,{class:o(["u-divider-line",[u.type?"u-divider-line--bordercolor--"+u.type:""]]),style:a([m.lineStyle])},null,8,["class","style"])])),_:3},8,["style","onClick"])}],["__scopeId","data-v-ba53c472"]]);var ne,oe={exports:{}};ne=window,oe.exports=function(e,t){if(!e.jWeixin){var i,n={config:"preVerifyJSAPI",onMenuShareTimeline:"menu:share:timeline",onMenuShareAppMessage:"menu:share:appmessage",onMenuShareQQ:"menu:share:qq",onMenuShareWeibo:"menu:share:weiboApp",onMenuShareQZone:"menu:share:QZone",previewImage:"imagePreview",getLocation:"geoLocation",openProductSpecificView:"openProductViewWithPid",addCard:"batchAddCard",openCard:"batchViewCard",chooseWXPay:"getBrandWCPayRequest",openEnterpriseRedPacket:"getRecevieBizHongBaoRequest",startSearchBeacons:"startMonitoringBeacons",stopSearchBeacons:"stopMonitoringBeacons",onSearchBeacons:"onBeaconsInRange",consumeAndShareCard:"consumedShareCard",openAddress:"editAddress"},o=function(){var e={};for(var t in n)e[n[t]]=t;return e}(),a=e.document,r=a.title,s=navigator.userAgent.toLowerCase(),c=navigator.platform.toLowerCase(),l=!(!c.match("mac")&&!c.match("win")),d=-1!=s.indexOf("wxdebugger"),u=-1!=s.indexOf("micromessenger"),p=-1!=s.indexOf("android"),f=-1!=s.indexOf("iphone")||-1!=s.indexOf("ipad"),m=(i=s.match(/micromessenger\/(\d+\.\d+\.\d+)/)||s.match(/micromessenger\/(\d+\.\d+)/))?i[1]:"",g={initStartTime:A(),initEndTime:0,preVerifyStartTime:0,preVerifyEndTime:0},h={version:1,appId:"",initTime:0,preVerifyTime:0,networkType:"",isPreVerifyOk:1,systemType:f?1:p?2:-1,clientVersion:m,url:encodeURIComponent(location.href)},v={},y={_completes:[]},_={state:0,data:{}};B((function(){g.initEndTime=A()}));var S=!1,w=[],k={config:function(t){P("config",v=t);var i=!1!==v.check;B((function(){if(i)I(n.config,{verifyJsApiList:V(v.jsApiList),verifyOpenTagList:V(v.openTagList)},function(){y._complete=function(e){g.preVerifyEndTime=A(),_.state=1,_.data=e},y.success=function(e){h.isPreVerifyOk=0},y.fail=function(e){y._fail?y._fail(e):_.state=-1};var e=y._completes;return e.push((function(){!function(){if(!(l||d||v.debug||m<"6.0.2"||h.systemType<0)){var e=new Image;h.appId=v.appId,h.initTime=g.initEndTime-g.initStartTime,h.preVerifyTime=g.preVerifyEndTime-g.preVerifyStartTime,k.getNetworkType({isInnerInvoke:!0,success:function(t){h.networkType=t.networkType;var i="https://open.weixin.qq.com/sdk/report?v="+h.version+"&o="+h.isPreVerifyOk+"&s="+h.systemType+"&c="+h.clientVersion+"&a="+h.appId+"&n="+h.networkType+"&i="+h.initTime+"&p="+h.preVerifyTime+"&u="+h.url;e.src=i}})}}()})),y.complete=function(t){for(var i=0,n=e.length;i<n;++i)e[i]();y._completes=[]},y}()),g.preVerifyStartTime=A();else{_.state=1;for(var e=y._completes,t=0,o=e.length;t<o;++t)e[t]();y._completes=[]}})),k.invoke||(k.invoke=function(t,i,n){e.WeixinJSBridge&&WeixinJSBridge.invoke(t,C(i),n)},k.on=function(t,i){e.WeixinJSBridge&&WeixinJSBridge.on(t,i)})},ready:function(e){0!=_.state?e():(y._completes.push(e),!u&&v.debug&&e())},error:function(e){m<"6.0.2"||(-1==_.state?e(_.data):y._fail=e)},checkJsApi:function(e){I("checkJsApi",{jsApiList:V(e.jsApiList)},(e._complete=function(e){if(p){var t=e.checkResult;t&&(e.checkResult=JSON.parse(t))}e=function(e){var t=e.checkResult;for(var i in t){var n=o[i];n&&(t[n]=t[i],delete t[i])}return e}(e)},e))},onMenuShareTimeline:function(e){T(n.onMenuShareTimeline,{complete:function(){I("shareTimeline",{title:e.title||r,desc:e.title||r,img_url:e.imgUrl||"",link:e.link||location.href,type:e.type||"link",data_url:e.dataUrl||""},e)}},e)},onMenuShareAppMessage:function(e){T(n.onMenuShareAppMessage,{complete:function(t){"favorite"===t.scene?I("sendAppMessage",{title:e.title||r,desc:e.desc||"",link:e.link||location.href,img_url:e.imgUrl||"",type:e.type||"link",data_url:e.dataUrl||""}):I("sendAppMessage",{title:e.title||r,desc:e.desc||"",link:e.link||location.href,img_url:e.imgUrl||"",type:e.type||"link",data_url:e.dataUrl||""},e)}},e)},onMenuShareQQ:function(e){T(n.onMenuShareQQ,{complete:function(){I("shareQQ",{title:e.title||r,desc:e.desc||"",img_url:e.imgUrl||"",link:e.link||location.href},e)}},e)},onMenuShareWeibo:function(e){T(n.onMenuShareWeibo,{complete:function(){I("shareWeiboApp",{title:e.title||r,desc:e.desc||"",img_url:e.imgUrl||"",link:e.link||location.href},e)}},e)},onMenuShareQZone:function(e){T(n.onMenuShareQZone,{complete:function(){I("shareQZone",{title:e.title||r,desc:e.desc||"",img_url:e.imgUrl||"",link:e.link||location.href},e)}},e)},updateTimelineShareData:function(e){I("updateTimelineShareData",{title:e.title,link:e.link,imgUrl:e.imgUrl},e)},updateAppMessageShareData:function(e){I("updateAppMessageShareData",{title:e.title,desc:e.desc,link:e.link,imgUrl:e.imgUrl},e)},startRecord:function(e){I("startRecord",{},e)},stopRecord:function(e){I("stopRecord",{},e)},onVoiceRecordEnd:function(e){T("onVoiceRecordEnd",e)},playVoice:function(e){I("playVoice",{localId:e.localId},e)},pauseVoice:function(e){I("pauseVoice",{localId:e.localId},e)},stopVoice:function(e){I("stopVoice",{localId:e.localId},e)},onVoicePlayEnd:function(e){T("onVoicePlayEnd",e)},uploadVoice:function(e){I("uploadVoice",{localId:e.localId,isShowProgressTips:0==e.isShowProgressTips?0:1},e)},downloadVoice:function(e){I("downloadVoice",{serverId:e.serverId,isShowProgressTips:0==e.isShowProgressTips?0:1},e)},translateVoice:function(e){I("translateVoice",{localId:e.localId,isShowProgressTips:0==e.isShowProgressTips?0:1},e)},chooseImage:function(e){I("chooseImage",{scene:"1|2",count:e.count||9,sizeType:e.sizeType||["original","compressed"],sourceType:e.sourceType||["album","camera"]},(e._complete=function(e){if(p){var t=e.localIds;try{t&&(e.localIds=JSON.parse(t))}catch(i){}}},e))},getLocation:function(e){},previewImage:function(e){I(n.previewImage,{current:e.current,urls:e.urls},e)},uploadImage:function(e){I("uploadImage",{localId:e.localId,isShowProgressTips:0==e.isShowProgressTips?0:1},e)},downloadImage:function(e){I("downloadImage",{serverId:e.serverId,isShowProgressTips:0==e.isShowProgressTips?0:1},e)},getLocalImgData:function(e){!1===S?(S=!0,I("getLocalImgData",{localId:e.localId},(e._complete=function(e){if(S=!1,0<w.length){var t=w.shift();wx.getLocalImgData(t)}},e))):w.push(e)},getNetworkType:function(e){I("getNetworkType",{},(e._complete=function(e){e=function(e){var t=e.errMsg;e.errMsg="getNetworkType:ok";var i=e.subtype;if(delete e.subtype,i)e.networkType=i;else{var n=t.indexOf(":"),o=t.substring(n+1);switch(o){case"wifi":case"edge":case"wwan":e.networkType=o;break;default:e.errMsg="getNetworkType:fail"}}return e}(e)},e))},openLocation:function(e){I("openLocation",{latitude:e.latitude,longitude:e.longitude,name:e.name||"",address:e.address||"",scale:e.scale||28,infoUrl:e.infoUrl||""},e)},getLocation:function(e){I(n.getLocation,{type:(e=e||{}).type||"wgs84"},(e._complete=function(e){delete e.type},e))},hideOptionMenu:function(e){I("hideOptionMenu",{},e)},showOptionMenu:function(e){I("showOptionMenu",{},e)},closeWindow:function(e){I("closeWindow",{},e=e||{})},hideMenuItems:function(e){I("hideMenuItems",{menuList:e.menuList},e)},showMenuItems:function(e){I("showMenuItems",{menuList:e.menuList},e)},hideAllNonBaseMenuItem:function(e){I("hideAllNonBaseMenuItem",{},e)},showAllNonBaseMenuItem:function(e){I("showAllNonBaseMenuItem",{},e)},scanQRCode:function(e){I("scanQRCode",{needResult:(e=e||{}).needResult||0,scanType:e.scanType||["qrCode","barCode"]},(e._complete=function(e){if(f){var t=e.resultStr;if(t){var i=JSON.parse(t);e.resultStr=i&&i.scan_code&&i.scan_code.scan_result}}},e))},openAddress:function(e){I(n.openAddress,{},(e._complete=function(e){var t;(t=e).postalCode=t.addressPostalCode,delete t.addressPostalCode,t.provinceName=t.proviceFirstStageName,delete t.proviceFirstStageName,t.cityName=t.addressCitySecondStageName,delete t.addressCitySecondStageName,t.countryName=t.addressCountiesThirdStageName,delete t.addressCountiesThirdStageName,t.detailInfo=t.addressDetailInfo,delete t.addressDetailInfo,e=t},e))},openProductSpecificView:function(e){I(n.openProductSpecificView,{pid:e.productId,view_type:e.viewType||0,ext_info:e.extInfo},e)},addCard:function(e){for(var t=e.cardList,i=[],o=0,a=t.length;o<a;++o){var r=t[o],s={card_id:r.cardId,card_ext:r.cardExt};i.push(s)}I(n.addCard,{card_list:i},(e._complete=function(e){var t=e.card_list;if(t){for(var i=0,n=(t=JSON.parse(t)).length;i<n;++i){var o=t[i];o.cardId=o.card_id,o.cardExt=o.card_ext,o.isSuccess=!!o.is_succ,delete o.card_id,delete o.card_ext,delete o.is_succ}e.cardList=t,delete e.card_list}},e))},chooseCard:function(e){I("chooseCard",{app_id:v.appId,location_id:e.shopId||"",sign_type:e.signType||"SHA1",card_id:e.cardId||"",card_type:e.cardType||"",card_sign:e.cardSign,time_stamp:e.timestamp+"",nonce_str:e.nonceStr},(e._complete=function(e){e.cardList=e.choose_card_info,delete e.choose_card_info},e))},openCard:function(e){for(var t=e.cardList,i=[],o=0,a=t.length;o<a;++o){var r=t[o],s={card_id:r.cardId,code:r.code};i.push(s)}I(n.openCard,{card_list:i},e)},consumeAndShareCard:function(e){I(n.consumeAndShareCard,{consumedCardId:e.cardId,consumedCode:e.code},e)},chooseWXPay:function(e){I(n.chooseWXPay,M(e),e)},openEnterpriseRedPacket:function(e){I(n.openEnterpriseRedPacket,M(e),e)},startSearchBeacons:function(e){I(n.startSearchBeacons,{ticket:e.ticket},e)},stopSearchBeacons:function(e){I(n.stopSearchBeacons,{},e)},onSearchBeacons:function(e){T(n.onSearchBeacons,e)},openEnterpriseChat:function(e){I("openEnterpriseChat",{useridlist:e.userIds,chatname:e.groupName},e)},launchMiniProgram:function(e){I("launchMiniProgram",{targetAppId:e.targetAppId,path:function(e){if("string"==typeof e&&0<e.length){var t=e.split("?")[0],i=e.split("?")[1];return t+=".html",void 0!==i?t+"?"+i:t}}(e.path),envVersion:e.envVersion},e)},openBusinessView:function(e){I("openBusinessView",{businessType:e.businessType,queryString:e.queryString||"",envVersion:e.envVersion},(e._complete=function(e){if(p){var t=e.extraData;if(t)try{e.extraData=JSON.parse(t)}catch(i){e.extraData={}}}},e))},miniProgram:{navigateBack:function(e){e=e||{},B((function(){I("invokeMiniProgramAPI",{name:"navigateBack",arg:{delta:e.delta||1}},e)}))},navigateTo:function(e){B((function(){I("invokeMiniProgramAPI",{name:"navigateTo",arg:{url:e.url}},e)}))},redirectTo:function(e){B((function(){I("invokeMiniProgramAPI",{name:"redirectTo",arg:{url:e.url}},e)}))},switchTab:function(e){B((function(){I("invokeMiniProgramAPI",{name:"switchTab",arg:{url:e.url}},e)}))},reLaunch:function(e){B((function(){I("invokeMiniProgramAPI",{name:"reLaunch",arg:{url:e.url}},e)}))},postMessage:function(e){B((function(){I("invokeMiniProgramAPI",{name:"postMessage",arg:e.data||{}},e)}))},getEnv:function(t){B((function(){t({miniprogram:"miniprogram"===e.__wxjs_environment})}))}}},x=1,b={};return a.addEventListener("error",(function(e){if(!p){var t=e.target,i=t.tagName,n=t.src;if(("IMG"==i||"VIDEO"==i||"AUDIO"==i||"SOURCE"==i)&&-1!=n.indexOf("wxlocalresource://")){e.preventDefault(),e.stopPropagation();var o=t["wx-id"];if(o||(o=x++,t["wx-id"]=o),b[o])return;b[o]=!0,wx.ready((function(){wx.getLocalImgData({localId:n,success:function(e){t.src=e.localData}})}))}}}),!0),a.addEventListener("load",(function(e){if(!p){var t=e.target,i=t.tagName;if(t.src,"IMG"==i||"VIDEO"==i||"AUDIO"==i||"SOURCE"==i){var n=t["wx-id"];n&&(b[n]=!1)}}}),!0),t&&(e.wx=e.jWeixin=k),k}function I(t,i,n){e.WeixinJSBridge?WeixinJSBridge.invoke(t,C(i),(function(e){L(t,e,n)})):P(t,n)}function T(t,i,n){e.WeixinJSBridge?WeixinJSBridge.on(t,(function(e){n&&n.trigger&&n.trigger(e),L(t,e,i)})):P(t,n||i)}function C(e){return(e=e||{}).appId=v.appId,e.verifyAppId=v.appId,e.verifySignType="sha1",e.verifyTimestamp=v.timestamp+"",e.verifyNonceStr=v.nonceStr,e.verifySignature=v.signature,e}function M(e){return{timeStamp:e.timestamp+"",nonceStr:e.nonceStr,package:e.package,paySign:e.paySign,signType:e.signType||"SHA1"}}function L(e,t,i){"openEnterpriseChat"!=e&&"openBusinessView"!==e||(t.errCode=t.err_code),delete t.err_code,delete t.err_desc,delete t.err_detail;var n=t.errMsg;n||(n=t.err_msg,delete t.err_msg,n=function(e,t){var i=e,n=o[i];n&&(i=n);var a="ok";if(t){var r=t.indexOf(":");"confirm"==(a=t.substring(r+1))&&(a="ok"),"failed"==a&&(a="fail"),-1!=a.indexOf("failed_")&&(a=a.substring(7)),-1!=a.indexOf("fail_")&&(a=a.substring(5)),"access denied"!=(a=(a=a.replace(/_/g," ")).toLowerCase())&&"no permission to execute"!=a||(a="permission denied"),"config"==i&&"function not exist"==a&&(a="ok"),""==a&&(a="fail")}return i+":"+a}(e,n),t.errMsg=n),(i=i||{})._complete&&(i._complete(t),delete i._complete),n=t.errMsg||"",v.debug&&!i.isInnerInvoke&&alert(JSON.stringify(t));var a=n.indexOf(":");switch(n.substring(a+1)){case"ok":i.success&&i.success(t);break;case"cancel":i.cancel&&i.cancel(t);break;default:i.fail&&i.fail(t)}i.complete&&i.complete(t)}function V(e){if(e){for(var t=0,i=e.length;t<i;++t){var o=e[t],a=n[o];a&&(e[t]=a)}return e}}function P(e,t){if(!(!v.debug||t&&t.isInnerInvoke)){var i=o[e];i&&(e=i),t&&t._complete&&delete t._complete,console.log('"'+e+'",',t||"")}}function A(){return(new Date).getTime()}function B(t){u&&(e.WeixinJSBridge?t():a.addEventListener&&a.addEventListener("WeixinJSBridgeReady",t,!1))}}(ne);var ae=oe.exports;const re={getSignLink:()=>(void 0!==window.signLink&&""!==window.signLink||(window.signLink=location.href.split("#")[0]),Y()?location.href.split("#")[0]:window.signLink),getUrl(){G().then((e=>{location.href=e.url}))},authLogin:e=>new Promise(((t,i)=>{X({code:e}).then((e=>{t(e)})).catch((e=>{i(e)}))})),ready:()=>new Promise((e=>{ae.ready((()=>{e("success")}))})),share(e){this.ready().then((()=>{const{shareTitle:t,shareLink:i,shareImage:n,shareDesc:o}=e;ae.updateTimelineShareData({title:t,link:i,imgUrl:n}),ae.updateAppMessageShareData({title:t,link:i,imgUrl:n,desc:o}),ae.onMenuShareWeibo({title:t,link:i,imgUrl:n,desc:o})}))},getAddress(){return new Promise(((e,t)=>{this.ready().then((()=>{ae.openAddress({success:t=>{e(t)},fail:e=>{t(e)}})}))}))},getLocation(){return new Promise(((e,t)=>{this.ready().then((()=>{ae.getLocation({type:"gcj02",success:t=>{e(t)},fail:e=>{t(e)}})}))}))}};var se=(e=>(e.MOBILE="mobile",e.ACCOUNT="account",e.MNP="mnp",e))(se||{}),ce=(e=>(e[e.ACCOUNT=1]="ACCOUNT",e[e.MOBILE=2]="MOBILE",e))(ce||{});const le=d({__name:"login",setup(a){const r=l(!0);r.value=ee();const d=u(),Z=p(),G=f(),X=l(),Y=l(""),oe=l(!1),ae=m({scene:"",username:"",password:"",code:"",mobile:""}),le=e=>{Y.value=e},de=async()=>{var e,t;ae.mobile&&(null==(e=G.value)?void 0:e.canGetCode)&&(await V({scene:K.LOGIN,mobile:ae.mobile}),uni.$u.toast("发送成功"),null==(t=G.value)||t.start())},ue=(e,t)=>{ae.scene=e,X.value=t},pe=e=>Z.getLoginConfig.loginWay.includes(e),fe=g((()=>1==Z.getLoginConfig.openAgreement)),me=g((()=>1==Z.getLoginConfig.openOtherAuth)),ge=g((()=>1==Z.getLoginConfig.forceBindMobile)),he=async e=>{const{token:t,isBindMobile:i}=e;if(!i&&ge.value)return d.temToken=t,P({url:"/pages/bind_mobile/bind_mobile"}),void _();d.login(e.token),await d.getUser(),uni.$u.toast("登录成功"),_(),A().length>1?S({success:()=>{const{onLoad:e,options:t}=te();e&&e(t)}}):B.get(O)?(console.log(O,B.get(O)),N({url:B.get(O)})):k({url:"/pages/index/index"}),B.remove(O)},{lockFn:ve}=function(e){const t=l(!1);return{isLock:t,lockFn:async(...i)=>{if(!t.value){t.value=!0;try{const n=await e(...i);return t.value=!1,n}catch(ne){throw t.value=!1,ne}}}}}((async(e,t)=>{if(!oe.value&&fe.value)return uni.$u.toast("请勾选已阅读并同意《服务协议》和《隐私协议》");if("account"==e){if(!ae.username)return uni.$u.toast("请输入账号/手机号码");if(!ae.password)return uni.$u.toast("请输入密码")}if("mobile"==e){if(!ae.mobile)return uni.$u.toast("请输入手机号码");if(!ae.code)return uni.$u.toast("请输入验证码")}const i={...ae,scene:e};t&&(i.code=t),y({title:"请稍后..."});try{const e=await H(i);he(e)}catch(n){_(),uni.$u.toast(n)}})),ye=async()=>{r.value&&re.getUrl()};return h((()=>Z.getLoginConfig),(e=>{e.loginWay&&(X.value=e.loginWay[0],ae.scene=se[ce[X.value]])}),{immediate:!0}),v((async()=>{try{d.isLogin&&(y({title:"请稍后..."}),await d.getUser(),_(),S())}catch(e){_()}})),w((async e=>{if(d.isLogin)return void k({url:"/pages/index/index"});const{code:t}=e;if(t){y({title:"请稍后..."});try{const e=await re.authLogin(t);he(e)}catch(i){throw _(),new Error(i)}}})),(a,l)=>{const d=U(E("u-image"),D),u=c,p=U(E("u-icon"),j),f=U(E("u-input"),J),m=U(E("u-form-item"),Q),g=W,h=U(E("u-verification-code"),z),v=R,y=U(E("u-form"),q),_=U(E("u-checkbox"),$),S=U(E("u-button"),F),w=U(E("u-divider"),ie);return e(),t(u,{class:"bg-white login min-h-full flex flex-col items-center px-[40rpx] pt-[80rpx] box-border"},{default:i((()=>[n(u,null,{default:i((()=>[n(d,{src:x(Z).config.website.logo,mode:"widthFix",height:"160",width:"160"},null,8,["src"])])),_:1}),n(u,{class:"mt-4 text-xl font-medium"},{default:i((()=>[b(I(x(Z).config.website.name),1)])),_:1}),n(u,{class:"w-full mt-[60rpx] pb-[60rpx]"},{default:i((()=>[n(y,{borderBottom:""},{default:i((()=>[1==X.value&&pe(1)?(e(),T(C,{key:0},[n(m,{borderBottom:""},{default:i((()=>[n(p,{class:"mr-2",size:36,name:"/static/images/icon/icon_user.png"}),n(f,{class:"flex-1",modelValue:ae.username,"onUpdate:modelValue":l[0]||(l[0]=e=>ae.username=e),border:!1,placeholder:"请输入账号/手机号码"},null,8,["modelValue"])])),_:1}),n(m,{borderBottom:""},{default:i((()=>[n(p,{class:"mr-2",size:36,name:"/static/images/icon/icon_password.png"}),n(f,{class:"flex-1",modelValue:ae.password,"onUpdate:modelValue":l[1]||(l[1]=e=>ae.password=e),type:"password",placeholder:"请输入密码",border:!1},null,8,["modelValue"]),n(g,{url:"/pages/forget_pwd/forget_pwd","hover-class":"none"},{default:i((()=>[n(u,{class:"border-l border-solid border-0 border-light pl-3 text-muted leading-4 ml-3"},{default:i((()=>[b(" 忘记密码？ ")])),_:1})])),_:1})])),_:1})],64)):s("v-if",!0),2==X.value&&pe(2)?(e(),T(C,{key:1},[n(m,{borderBottom:""},{default:i((()=>[n(p,{class:"mr-2",size:36,name:"/static/images/icon/icon_mobile.png"}),n(f,{class:"flex-1",modelValue:ae.mobile,"onUpdate:modelValue":l[2]||(l[2]=e=>ae.mobile=e),border:!1,placeholder:"请输入手机号码"},null,8,["modelValue"])])),_:1}),n(m,{borderBottom:""},{default:i((()=>[n(p,{class:"mr-2",size:36,name:"/static/images/icon/icon_code.png"}),n(f,{class:"flex-1",modelValue:ae.code,"onUpdate:modelValue":l[3]||(l[3]=e=>ae.code=e),placeholder:"请输入验证码",border:!1},null,8,["modelValue"]),n(u,{class:"border-l border-solid border-0 border-light pl-3 leading-4 ml-3 w-[180rpx]",onClick:de},{default:i((()=>[n(h,{ref_key:"uCodeRef",ref:G,seconds:60,onChange:le,"change-text":"x秒"},null,512),n(v,{class:o(ae.mobile?"text-primary":"text-muted")},{default:i((()=>[b(I(Y.value),1)])),_:1},8,["class"])])),_:1})])),_:1})],64)):s("v-if",!0)])),_:1}),x(fe)?(e(),t(u,{key:0,class:"mt-[40rpx]"},{default:i((()=>[n(_,{modelValue:oe.value,"onUpdate:modelValue":l[6]||(l[6]=e=>oe.value=e),shape:"circle"},{default:i((()=>[n(u,{class:"text-xs flex"},{default:i((()=>[b(" 已阅读并同意 "),n(g,{onClick:l[4]||(l[4]=M((()=>{}),["stop"])),class:"text-primary","hover-class":"none",url:"/pages/agreement/agreement?type=service"},{default:i((()=>[b(" 《服务协议》 ")])),_:1}),b(" 和"),n(g,{onClick:l[5]||(l[5]=M((()=>{}),["stop"])),class:"text-primary","hover-class":"none",url:"/pages/agreement/agreement?type=privacy"},{default:i((()=>[b(" 《隐私协议》 ")])),_:1})])),_:1})])),_:1},8,["modelValue"])])),_:1})):s("v-if",!0),n(u,{class:"mt-[60rpx]"},{default:i((()=>[n(S,{type:"primary",shape:"circle",onClick:l[7]||(l[7]=e=>x(ve)(ae.scene))},{default:i((()=>[b(" 登 录 ")])),_:1})])),_:1}),n(u,{class:"text-content flex justify-between mt-[40rpx]"},{default:i((()=>[n(u,{class:"flex-1"},{default:i((()=>[2==X.value&&pe(1)?(e(),t(u,{key:0,onClick:l[8]||(l[8]=e=>ue("account",1))},{default:i((()=>[b(" 账号密码登录 ")])),_:1})):s("v-if",!0),1==X.value&&pe(2)?(e(),t(u,{key:1,onClick:l[9]||(l[9]=e=>ue("mobile",2))},{default:i((()=>[b(" 短信验证码登录 ")])),_:1})):s("v-if",!0)])),_:1}),n(g,{url:"/pages/register/register","hover-class":"none"},{default:i((()=>[b("注册账号")])),_:1})])),_:1}),x(me)&&r.value?(e(),t(u,{key:1,class:"mt-[80rpx]"},{default:i((()=>{return[n(w,null,{default:i((()=>[b("第三方登录")])),_:1}),L("div",{class:"flex justify-center mt-[40rpx]"},[(t=1,Z.getLoginConfig.autoLoginAuth.includes(t)&&r.value?(e(),T("div",{key:0,class:"flex flex-col items-center",onClick:ye},[L("img",{src:"/assets/icon_wx.a848c744.png",class:"w-[80rpx] h-[80rpx]"}),L("div",{class:"text-sm mt-[10px]"},"微信登录")])):s("v-if",!0))])];var t})),_:1})):s("v-if",!0)])),_:1})])),_:1})}}});var de=Z(le,[["__scopeId","data-v-4604c459"]]);export{de as default};