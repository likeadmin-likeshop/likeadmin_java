import{o as e,c as t,w as i,b as n,x as a,y as o,K as r,h as s,k as c,D as l,ab as d,d as u,ac as p,a8 as f,u as m,V as g,m as h,E as v,N as y,aa as _,a1 as w,ad as S,ae as k,W as x,s as b,g as I,l as T,t as C,e as M,F as V,B as L,a7 as A,af as P,ag as B,q as O,A as N,ah as U,ai as E,aj as W,r as R,a as D,i as j,C as J}from"./index.6c00075c.js";import{_ as Q}from"./u-image.02e8c068.js";import{_ as q}from"./u-icon.7c234862.js";import{_ as z,a as $}from"./u-form-item.7faed50a.js";import{_ as F}from"./u-verification-code.7c0ada86.js";import{_ as Z}from"./u-form.653d3903.js";import{_ as G}from"./u-checkbox.f02c1c63.js";import{_ as H}from"./u-button.752e689d.js";import{_ as X}from"./plugin-vue_export-helper.21dcd24c.js";import{g as K,O as Y,m as ee,a as te}from"./account.293f1326.js";var ie=X({name:"u-divider",props:{halfWidth:{type:[Number,String],default:150},borderColor:{type:String,default:"#dcdfe6"},type:{type:String,default:"primary"},color:{type:String,default:"#909399"},fontSize:{type:[Number,String],default:26},bgColor:{type:String,default:"#ffffff"},height:{type:[Number,String],default:"auto"},marginTop:{type:[String,Number],default:0},marginBottom:{type:[String,Number],default:0},useSlot:{type:Boolean,default:!0}},computed:{lineStyle(){let e={};return-1!=String(this.halfWidth).indexOf("%")?e.width=this.halfWidth:e.width=this.halfWidth+"rpx",this.borderColor&&(e.borderColor=this.borderColor),e}},methods:{click(){this.$emit("click")}}},[["render",function(l,d,u,p,f,m){const g=c;return e(),t(g,{class:"u-divider",style:o({height:"auto"==u.height?"auto":u.height+"rpx",backgroundColor:u.bgColor,marginBottom:u.marginBottom+"rpx",marginTop:u.marginTop+"rpx"}),onClick:m.click},{default:i((()=>[n(g,{class:a(["u-divider-line",[u.type?"u-divider-line--bordercolor--"+u.type:""]]),style:o([m.lineStyle])},null,8,["class","style"]),u.useSlot?(e(),t(g,{key:0,class:"u-divider-text",style:o({color:u.color,fontSize:u.fontSize+"rpx"})},{default:i((()=>[r(l.$slots,"default",{},void 0,!0)])),_:3},8,["style"])):s("v-if",!0),n(g,{class:a(["u-divider-line",[u.type?"u-divider-line--bordercolor--"+u.type:""]]),style:o([m.lineStyle])},null,8,["class","style"])])),_:3},8,["style","onClick"])}],["__scopeId","data-v-ba53c472"]]);function ne(e){const t=l(!1);return{isLock:t,lockFn:async(...i)=>{if(!t.value){t.value=!0;try{const n=await e(...i);return t.value=!1,n}catch(ae){throw t.value=!1,ae}}}}}var ae,oe={exports:{}};ae=window,oe.exports=function(e,t){if(!e.jWeixin){var i,n={config:"preVerifyJSAPI",onMenuShareTimeline:"menu:share:timeline",onMenuShareAppMessage:"menu:share:appmessage",onMenuShareQQ:"menu:share:qq",onMenuShareWeibo:"menu:share:weiboApp",onMenuShareQZone:"menu:share:QZone",previewImage:"imagePreview",getLocation:"geoLocation",openProductSpecificView:"openProductViewWithPid",addCard:"batchAddCard",openCard:"batchViewCard",chooseWXPay:"getBrandWCPayRequest",openEnterpriseRedPacket:"getRecevieBizHongBaoRequest",startSearchBeacons:"startMonitoringBeacons",stopSearchBeacons:"stopMonitoringBeacons",onSearchBeacons:"onBeaconsInRange",consumeAndShareCard:"consumedShareCard",openAddress:"editAddress"},a=function(){var e={};for(var t in n)e[n[t]]=t;return e}(),o=e.document,r=o.title,s=navigator.userAgent.toLowerCase(),c=navigator.platform.toLowerCase(),l=!(!c.match("mac")&&!c.match("win")),d=-1!=s.indexOf("wxdebugger"),u=-1!=s.indexOf("micromessenger"),p=-1!=s.indexOf("android"),f=-1!=s.indexOf("iphone")||-1!=s.indexOf("ipad"),m=(i=s.match(/micromessenger\/(\d+\.\d+\.\d+)/)||s.match(/micromessenger\/(\d+\.\d+)/))?i[1]:"",g={initStartTime:P(),initEndTime:0,preVerifyStartTime:0,preVerifyEndTime:0},h={version:1,appId:"",initTime:0,preVerifyTime:0,networkType:"",isPreVerifyOk:1,systemType:f?1:p?2:-1,clientVersion:m,url:encodeURIComponent(location.href)},v={},y={_completes:[]},_={state:0,data:{}};B((function(){g.initEndTime=P()}));var w=!1,S=[],k={config:function(t){A("config",v=t);var i=!1!==v.check;B((function(){if(i)I(n.config,{verifyJsApiList:L(v.jsApiList),verifyOpenTagList:L(v.openTagList)},function(){y._complete=function(e){g.preVerifyEndTime=P(),_.state=1,_.data=e},y.success=function(e){h.isPreVerifyOk=0},y.fail=function(e){y._fail?y._fail(e):_.state=-1};var e=y._completes;return e.push((function(){!function(){if(!(l||d||v.debug||m<"6.0.2"||h.systemType<0)){var e=new Image;h.appId=v.appId,h.initTime=g.initEndTime-g.initStartTime,h.preVerifyTime=g.preVerifyEndTime-g.preVerifyStartTime,k.getNetworkType({isInnerInvoke:!0,success:function(t){h.networkType=t.networkType;var i="https://open.weixin.qq.com/sdk/report?v="+h.version+"&o="+h.isPreVerifyOk+"&s="+h.systemType+"&c="+h.clientVersion+"&a="+h.appId+"&n="+h.networkType+"&i="+h.initTime+"&p="+h.preVerifyTime+"&u="+h.url;e.src=i}})}}()})),y.complete=function(t){for(var i=0,n=e.length;i<n;++i)e[i]();y._completes=[]},y}()),g.preVerifyStartTime=P();else{_.state=1;for(var e=y._completes,t=0,a=e.length;t<a;++t)e[t]();y._completes=[]}})),k.invoke||(k.invoke=function(t,i,n){e.WeixinJSBridge&&WeixinJSBridge.invoke(t,C(i),n)},k.on=function(t,i){e.WeixinJSBridge&&WeixinJSBridge.on(t,i)})},ready:function(e){0!=_.state?e():(y._completes.push(e),!u&&v.debug&&e())},error:function(e){m<"6.0.2"||(-1==_.state?e(_.data):y._fail=e)},checkJsApi:function(e){I("checkJsApi",{jsApiList:L(e.jsApiList)},(e._complete=function(e){if(p){var t=e.checkResult;t&&(e.checkResult=JSON.parse(t))}e=function(e){var t=e.checkResult;for(var i in t){var n=a[i];n&&(t[n]=t[i],delete t[i])}return e}(e)},e))},onMenuShareTimeline:function(e){T(n.onMenuShareTimeline,{complete:function(){I("shareTimeline",{title:e.title||r,desc:e.title||r,img_url:e.imgUrl||"",link:e.link||location.href,type:e.type||"link",data_url:e.dataUrl||""},e)}},e)},onMenuShareAppMessage:function(e){T(n.onMenuShareAppMessage,{complete:function(t){"favorite"===t.scene?I("sendAppMessage",{title:e.title||r,desc:e.desc||"",link:e.link||location.href,img_url:e.imgUrl||"",type:e.type||"link",data_url:e.dataUrl||""}):I("sendAppMessage",{title:e.title||r,desc:e.desc||"",link:e.link||location.href,img_url:e.imgUrl||"",type:e.type||"link",data_url:e.dataUrl||""},e)}},e)},onMenuShareQQ:function(e){T(n.onMenuShareQQ,{complete:function(){I("shareQQ",{title:e.title||r,desc:e.desc||"",img_url:e.imgUrl||"",link:e.link||location.href},e)}},e)},onMenuShareWeibo:function(e){T(n.onMenuShareWeibo,{complete:function(){I("shareWeiboApp",{title:e.title||r,desc:e.desc||"",img_url:e.imgUrl||"",link:e.link||location.href},e)}},e)},onMenuShareQZone:function(e){T(n.onMenuShareQZone,{complete:function(){I("shareQZone",{title:e.title||r,desc:e.desc||"",img_url:e.imgUrl||"",link:e.link||location.href},e)}},e)},updateTimelineShareData:function(e){I("updateTimelineShareData",{title:e.title,link:e.link,imgUrl:e.imgUrl},e)},updateAppMessageShareData:function(e){I("updateAppMessageShareData",{title:e.title,desc:e.desc,link:e.link,imgUrl:e.imgUrl},e)},startRecord:function(e){I("startRecord",{},e)},stopRecord:function(e){I("stopRecord",{},e)},onVoiceRecordEnd:function(e){T("onVoiceRecordEnd",e)},playVoice:function(e){I("playVoice",{localId:e.localId},e)},pauseVoice:function(e){I("pauseVoice",{localId:e.localId},e)},stopVoice:function(e){I("stopVoice",{localId:e.localId},e)},onVoicePlayEnd:function(e){T("onVoicePlayEnd",e)},uploadVoice:function(e){I("uploadVoice",{localId:e.localId,isShowProgressTips:0==e.isShowProgressTips?0:1},e)},downloadVoice:function(e){I("downloadVoice",{serverId:e.serverId,isShowProgressTips:0==e.isShowProgressTips?0:1},e)},translateVoice:function(e){I("translateVoice",{localId:e.localId,isShowProgressTips:0==e.isShowProgressTips?0:1},e)},chooseImage:function(e){I("chooseImage",{scene:"1|2",count:e.count||9,sizeType:e.sizeType||["original","compressed"],sourceType:e.sourceType||["album","camera"]},(e._complete=function(e){if(p){var t=e.localIds;try{t&&(e.localIds=JSON.parse(t))}catch(i){}}},e))},getLocation:function(e){},previewImage:function(e){I(n.previewImage,{current:e.current,urls:e.urls},e)},uploadImage:function(e){I("uploadImage",{localId:e.localId,isShowProgressTips:0==e.isShowProgressTips?0:1},e)},downloadImage:function(e){I("downloadImage",{serverId:e.serverId,isShowProgressTips:0==e.isShowProgressTips?0:1},e)},getLocalImgData:function(e){!1===w?(w=!0,I("getLocalImgData",{localId:e.localId},(e._complete=function(e){if(w=!1,0<S.length){var t=S.shift();wx.getLocalImgData(t)}},e))):S.push(e)},getNetworkType:function(e){I("getNetworkType",{},(e._complete=function(e){e=function(e){var t=e.errMsg;e.errMsg="getNetworkType:ok";var i=e.subtype;if(delete e.subtype,i)e.networkType=i;else{var n=t.indexOf(":"),a=t.substring(n+1);switch(a){case"wifi":case"edge":case"wwan":e.networkType=a;break;default:e.errMsg="getNetworkType:fail"}}return e}(e)},e))},openLocation:function(e){I("openLocation",{latitude:e.latitude,longitude:e.longitude,name:e.name||"",address:e.address||"",scale:e.scale||28,infoUrl:e.infoUrl||""},e)},getLocation:function(e){I(n.getLocation,{type:(e=e||{}).type||"wgs84"},(e._complete=function(e){delete e.type},e))},hideOptionMenu:function(e){I("hideOptionMenu",{},e)},showOptionMenu:function(e){I("showOptionMenu",{},e)},closeWindow:function(e){I("closeWindow",{},e=e||{})},hideMenuItems:function(e){I("hideMenuItems",{menuList:e.menuList},e)},showMenuItems:function(e){I("showMenuItems",{menuList:e.menuList},e)},hideAllNonBaseMenuItem:function(e){I("hideAllNonBaseMenuItem",{},e)},showAllNonBaseMenuItem:function(e){I("showAllNonBaseMenuItem",{},e)},scanQRCode:function(e){I("scanQRCode",{needResult:(e=e||{}).needResult||0,scanType:e.scanType||["qrCode","barCode"]},(e._complete=function(e){if(f){var t=e.resultStr;if(t){var i=JSON.parse(t);e.resultStr=i&&i.scan_code&&i.scan_code.scan_result}}},e))},openAddress:function(e){I(n.openAddress,{},(e._complete=function(e){var t;(t=e).postalCode=t.addressPostalCode,delete t.addressPostalCode,t.provinceName=t.proviceFirstStageName,delete t.proviceFirstStageName,t.cityName=t.addressCitySecondStageName,delete t.addressCitySecondStageName,t.countryName=t.addressCountiesThirdStageName,delete t.addressCountiesThirdStageName,t.detailInfo=t.addressDetailInfo,delete t.addressDetailInfo,e=t},e))},openProductSpecificView:function(e){I(n.openProductSpecificView,{pid:e.productId,view_type:e.viewType||0,ext_info:e.extInfo},e)},addCard:function(e){for(var t=e.cardList,i=[],a=0,o=t.length;a<o;++a){var r=t[a],s={card_id:r.cardId,card_ext:r.cardExt};i.push(s)}I(n.addCard,{card_list:i},(e._complete=function(e){var t=e.card_list;if(t){for(var i=0,n=(t=JSON.parse(t)).length;i<n;++i){var a=t[i];a.cardId=a.card_id,a.cardExt=a.card_ext,a.isSuccess=!!a.is_succ,delete a.card_id,delete a.card_ext,delete a.is_succ}e.cardList=t,delete e.card_list}},e))},chooseCard:function(e){I("chooseCard",{app_id:v.appId,location_id:e.shopId||"",sign_type:e.signType||"SHA1",card_id:e.cardId||"",card_type:e.cardType||"",card_sign:e.cardSign,time_stamp:e.timestamp+"",nonce_str:e.nonceStr},(e._complete=function(e){e.cardList=e.choose_card_info,delete e.choose_card_info},e))},openCard:function(e){for(var t=e.cardList,i=[],a=0,o=t.length;a<o;++a){var r=t[a],s={card_id:r.cardId,code:r.code};i.push(s)}I(n.openCard,{card_list:i},e)},consumeAndShareCard:function(e){I(n.consumeAndShareCard,{consumedCardId:e.cardId,consumedCode:e.code},e)},chooseWXPay:function(e){I(n.chooseWXPay,M(e),e)},openEnterpriseRedPacket:function(e){I(n.openEnterpriseRedPacket,M(e),e)},startSearchBeacons:function(e){I(n.startSearchBeacons,{ticket:e.ticket},e)},stopSearchBeacons:function(e){I(n.stopSearchBeacons,{},e)},onSearchBeacons:function(e){T(n.onSearchBeacons,e)},openEnterpriseChat:function(e){I("openEnterpriseChat",{useridlist:e.userIds,chatname:e.groupName},e)},launchMiniProgram:function(e){I("launchMiniProgram",{targetAppId:e.targetAppId,path:function(e){if("string"==typeof e&&0<e.length){var t=e.split("?")[0],i=e.split("?")[1];return t+=".html",void 0!==i?t+"?"+i:t}}(e.path),envVersion:e.envVersion},e)},openBusinessView:function(e){I("openBusinessView",{businessType:e.businessType,queryString:e.queryString||"",envVersion:e.envVersion},(e._complete=function(e){if(p){var t=e.extraData;if(t)try{e.extraData=JSON.parse(t)}catch(i){e.extraData={}}}},e))},miniProgram:{navigateBack:function(e){e=e||{},B((function(){I("invokeMiniProgramAPI",{name:"navigateBack",arg:{delta:e.delta||1}},e)}))},navigateTo:function(e){B((function(){I("invokeMiniProgramAPI",{name:"navigateTo",arg:{url:e.url}},e)}))},redirectTo:function(e){B((function(){I("invokeMiniProgramAPI",{name:"redirectTo",arg:{url:e.url}},e)}))},switchTab:function(e){B((function(){I("invokeMiniProgramAPI",{name:"switchTab",arg:{url:e.url}},e)}))},reLaunch:function(e){B((function(){I("invokeMiniProgramAPI",{name:"reLaunch",arg:{url:e.url}},e)}))},postMessage:function(e){B((function(){I("invokeMiniProgramAPI",{name:"postMessage",arg:e.data||{}},e)}))},getEnv:function(t){B((function(){t({miniprogram:"miniprogram"===e.__wxjs_environment})}))}}},x=1,b={};return o.addEventListener("error",(function(e){if(!p){var t=e.target,i=t.tagName,n=t.src;if(("IMG"==i||"VIDEO"==i||"AUDIO"==i||"SOURCE"==i)&&-1!=n.indexOf("wxlocalresource://")){e.preventDefault(),e.stopPropagation();var a=t["wx-id"];if(a||(a=x++,t["wx-id"]=a),b[a])return;b[a]=!0,wx.ready((function(){wx.getLocalImgData({localId:n,success:function(e){t.src=e.localData}})}))}}}),!0),o.addEventListener("load",(function(e){if(!p){var t=e.target,i=t.tagName;if(t.src,"IMG"==i||"VIDEO"==i||"AUDIO"==i||"SOURCE"==i){var n=t["wx-id"];n&&(b[n]=!1)}}}),!0),t&&(e.wx=e.jWeixin=k),k}function I(t,i,n){e.WeixinJSBridge?WeixinJSBridge.invoke(t,C(i),(function(e){V(t,e,n)})):A(t,n)}function T(t,i,n){e.WeixinJSBridge?WeixinJSBridge.on(t,(function(e){n&&n.trigger&&n.trigger(e),V(t,e,i)})):A(t,n||i)}function C(e){return(e=e||{}).appId=v.appId,e.verifyAppId=v.appId,e.verifySignType="sha1",e.verifyTimestamp=v.timestamp+"",e.verifyNonceStr=v.nonceStr,e.verifySignature=v.signature,e}function M(e){return{timeStamp:e.timestamp+"",nonceStr:e.nonceStr,package:e.package,paySign:e.paySign,signType:e.signType||"SHA1"}}function V(e,t,i){"openEnterpriseChat"!=e&&"openBusinessView"!==e||(t.errCode=t.err_code),delete t.err_code,delete t.err_desc,delete t.err_detail;var n=t.errMsg;n||(n=t.err_msg,delete t.err_msg,n=function(e,t){var i=e,n=a[i];n&&(i=n);var o="ok";if(t){var r=t.indexOf(":");"confirm"==(o=t.substring(r+1))&&(o="ok"),"failed"==o&&(o="fail"),-1!=o.indexOf("failed_")&&(o=o.substring(7)),-1!=o.indexOf("fail_")&&(o=o.substring(5)),"access denied"!=(o=(o=o.replace(/_/g," ")).toLowerCase())&&"no permission to execute"!=o||(o="permission denied"),"config"==i&&"function not exist"==o&&(o="ok"),""==o&&(o="fail")}return i+":"+o}(e,n),t.errMsg=n),(i=i||{})._complete&&(i._complete(t),delete i._complete),n=t.errMsg||"",v.debug&&!i.isInnerInvoke&&alert(JSON.stringify(t));var o=n.indexOf(":");switch(n.substring(o+1)){case"ok":i.success&&i.success(t);break;case"cancel":i.cancel&&i.cancel(t);break;default:i.fail&&i.fail(t)}i.complete&&i.complete(t)}function L(e){if(e){for(var t=0,i=e.length;t<i;++t){var a=e[t],o=n[a];o&&(e[t]=o)}return e}}function A(e,t){if(!(!v.debug||t&&t.isInnerInvoke)){var i=a[e];i&&(e=i),t&&t._complete&&delete t._complete,console.log('"'+e+'",',t||"")}}function P(){return(new Date).getTime()}function B(t){u&&(e.WeixinJSBridge?t():o.addEventListener&&o.addEventListener("WeixinJSBridgeReady",t,!1))}}(ae);var re=oe.exports;const se={getSignLink:()=>(void 0!==window.signLink&&""!==window.signLink||(window.signLink=location.href.split("#")[0]),d()?location.href.split("#")[0]:window.signLink),getUrl(){K().then((e=>{location.href=e.url}))},authLogin:e=>new Promise(((t,i)=>{Y({code:e}).then((e=>{t(e)})).catch((e=>{i(e)}))})),ready:()=>new Promise((e=>{re.ready((()=>{e("success")}))})),share(e){this.ready().then((()=>{const{shareTitle:t,shareLink:i,shareImage:n,shareDesc:a}=e;re.updateTimelineShareData({title:t,link:i,imgUrl:n}),re.updateAppMessageShareData({title:t,link:i,imgUrl:n,desc:a}),re.onMenuShareWeibo({title:t,link:i,imgUrl:n,desc:a})}))},getAddress(){return new Promise(((e,t)=>{this.ready().then((()=>{re.openAddress({success:t=>{e(t)},fail:e=>{t(e)}})}))}))},getLocation(){return new Promise(((e,t)=>{this.ready().then((()=>{re.getLocation({type:"gcj02",success:t=>{e(t)},fail:e=>{t(e)}})}))}))}};var ce=(e=>(e.MOBILE="mobile",e.ACCOUNT="account",e))(ce||{}),le=(e=>(e[e.ACCOUNT=1]="ACCOUNT",e[e.MOBILE=2]="MOBILE",e))(le||{});var de=X(u({__name:"login",setup(o){const r=l(!0);r.value=p();const d=f(),u=m(),X=g(),K=l(),Y=l(""),ae=l(!1),oe=h({scene:"",username:"",password:"",code:"",mobile:""}),re=e=>{Y.value=e},de=async()=>{var e,t;oe.mobile&&(null==(e=X.value)?void 0:e.canGetCode)&&(await P({scene:B.LOGIN,mobile:oe.mobile}),uni.$u.toast("发送成功"),null==(t=X.value)||t.start())},ue=(e,t)=>{oe.scene=e,K.value=t},pe=e=>u.getLoginConfig.loginWay.includes(e),fe=v((()=>1==u.getLoginConfig.openAgreement)),me=v((()=>1==u.getLoginConfig.openOtherAuth)),ge=v((()=>1==u.getLoginConfig.forceBindMobile)),he=async e=>{const{token:t,isBindMobile:i}=e;if(!i&&ge.value)return d.temToken=t,O({url:"/pages/bind_mobile/bind_mobile"}),void S();d.login(e.token),await d.getUser(),uni.$u.toast("登录成功"),S();const n=N();if(n.length>1){const e=n[n.length-2];k({success:()=>{const{onLoad:t,options:i}=e;t&&t(i)}})}else U.get(E)?W({url:U.get(E)}):b({url:"/pages/index/index"});U.remove(E)},{lockFn:ve}=ne((async e=>{try{if(await ye(),"account"==e){if(!oe.username)return uni.$u.toast("请输入账号/手机号码");if(!oe.password)return uni.$u.toast("请输入密码")}if("mobile"==e){if(!oe.mobile)return uni.$u.toast("请输入手机号码");if(!oe.code)return uni.$u.toast("请输入验证码")}let t;switch(w({title:"请稍后..."}),e){case"account":t=await te(oe);break;case"mobile":t=await ee(oe)}t&&he(t)}catch(t){S(),uni.$u.toast(t)}})),ye=async()=>{if(!ae.value&&fe.value)return Promise.reject("请勾选已阅读并同意《服务协议》和《隐私协议》")},{lockFn:_e}=ne((async()=>{try{await ye(),r.value&&se.getUrl()}catch(e){uni.$u.toast(e)}}));return y((()=>u.getLoginConfig),(e=>{e.loginWay&&(K.value=e.loginWay[0],oe.scene=ce[le[K.value]])}),{immediate:!0}),_((async()=>{try{d.isLogin&&(w({title:"请稍后..."}),await d.getUser(),S(),k())}catch(e){S()}})),x((async e=>{if(d.isLogin)return void b({url:"/pages/index/index"});const{code:t}=e;if(t){w({title:"请稍后..."});try{const e=await se.authLogin(t);he(e)}catch(i){throw S(),new Error(i)}}})),(o,l)=>{const d=R(D("u-image"),Q),p=c,f=R(D("u-icon"),q),m=R(D("u-input"),z),g=R(D("u-form-item"),$),h=j,v=R(D("u-verification-code"),F),y=J,_=R(D("u-form"),Z),w=R(D("u-checkbox"),G),S=R(D("u-button"),H),k=R(D("u-divider"),ie);return e(),t(p,{class:"bg-white login min-h-full flex flex-col items-center px-[40rpx] pt-[80rpx] box-border"},{default:i((()=>[n(p,null,{default:i((()=>[n(d,{src:I(u).config.website.logo,mode:"widthFix",height:"160",width:"160"},null,8,["src"])])),_:1}),n(p,{class:"mt-4 text-xl font-medium"},{default:i((()=>[T(C(I(u).config.website.name),1)])),_:1}),n(p,{class:"w-full mt-[60rpx] pb-[60rpx]"},{default:i((()=>[n(_,{borderBottom:""},{default:i((()=>[1==K.value&&pe(1)?(e(),M(V,{key:0},[n(g,{borderBottom:""},{default:i((()=>[n(f,{class:"mr-2",size:36,name:"/static/images/icon/icon_user.png"}),n(m,{class:"flex-1",modelValue:oe.username,"onUpdate:modelValue":l[0]||(l[0]=e=>oe.username=e),border:!1,placeholder:"请输入账号/手机号码"},null,8,["modelValue"])])),_:1}),n(g,{borderBottom:""},{default:i((()=>[n(f,{class:"mr-2",size:36,name:"/static/images/icon/icon_password.png"}),n(m,{class:"flex-1",modelValue:oe.password,"onUpdate:modelValue":l[1]||(l[1]=e=>oe.password=e),type:"password",placeholder:"请输入密码",border:!1},null,8,["modelValue"]),n(h,{url:"/pages/forget_pwd/forget_pwd","hover-class":"none"},{default:i((()=>[n(p,{class:"border-l border-solid border-0 border-light pl-3 text-muted leading-4 ml-3"},{default:i((()=>[T(" 忘记密码？ ")])),_:1})])),_:1})])),_:1})],64)):s("v-if",!0),2==K.value&&pe(2)?(e(),M(V,{key:1},[n(g,{borderBottom:""},{default:i((()=>[n(f,{class:"mr-2",size:36,name:"/static/images/icon/icon_mobile.png"}),n(m,{class:"flex-1",modelValue:oe.mobile,"onUpdate:modelValue":l[2]||(l[2]=e=>oe.mobile=e),border:!1,placeholder:"请输入手机号码"},null,8,["modelValue"])])),_:1}),n(g,{borderBottom:""},{default:i((()=>[n(f,{class:"mr-2",size:36,name:"/static/images/icon/icon_code.png"}),n(m,{class:"flex-1",modelValue:oe.code,"onUpdate:modelValue":l[3]||(l[3]=e=>oe.code=e),placeholder:"请输入验证码",border:!1},null,8,["modelValue"]),n(p,{class:"border-l border-solid border-0 border-light pl-3 leading-4 ml-3 w-[180rpx]",onClick:de},{default:i((()=>[n(v,{ref_key:"uCodeRef",ref:X,seconds:60,onChange:re,"change-text":"x秒"},null,512),n(y,{class:a(oe.mobile?"text-primary":"text-muted")},{default:i((()=>[T(C(Y.value),1)])),_:1},8,["class"])])),_:1})])),_:1})],64)):s("v-if",!0)])),_:1}),I(fe)?(e(),t(p,{key:0,class:"mt-[40rpx]"},{default:i((()=>[n(w,{modelValue:ae.value,"onUpdate:modelValue":l[6]||(l[6]=e=>ae.value=e),shape:"circle"},{default:i((()=>[n(p,{class:"text-xs flex"},{default:i((()=>[T(" 已阅读并同意 "),n(p,{onClick:l[4]||(l[4]=L((()=>{}),["stop"]))},{default:i((()=>[n(h,{class:"text-primary","hover-class":"none",url:"/pages/agreement/agreement?type=service"},{default:i((()=>[T(" 《服务协议》 ")])),_:1})])),_:1}),T(" 和 "),n(p,{onClick:l[5]||(l[5]=L((()=>{}),["stop"]))},{default:i((()=>[n(h,{class:"text-primary","hover-class":"none",url:"/pages/agreement/agreement?type=privacy"},{default:i((()=>[T(" 《隐私协议》 ")])),_:1})])),_:1})])),_:1})])),_:1},8,["modelValue"])])),_:1})):s("v-if",!0),n(p,{class:"mt-[60rpx]"},{default:i((()=>[n(S,{type:"primary",shape:"circle",onClick:l[7]||(l[7]=e=>I(ve)(oe.scene))},{default:i((()=>[T(" 登 录 ")])),_:1})])),_:1}),n(p,{class:"text-content flex justify-between mt-[40rpx]"},{default:i((()=>[n(p,{class:"flex-1"},{default:i((()=>[2==K.value&&pe(1)?(e(),t(p,{key:0,onClick:l[8]||(l[8]=e=>ue("account",1))},{default:i((()=>[T(" 账号密码登录 ")])),_:1})):s("v-if",!0),1==K.value&&pe(2)?(e(),t(p,{key:1,onClick:l[9]||(l[9]=e=>ue("mobile",2))},{default:i((()=>[T(" 短信验证码登录 ")])),_:1})):s("v-if",!0)])),_:1}),n(h,{url:"/pages/register/register","hover-class":"none"},{default:i((()=>[T("注册账号")])),_:1})])),_:1}),I(me)&&r.value?(e(),t(p,{key:1,class:"mt-[80rpx]"},{default:i((()=>{return[n(k,null,{default:i((()=>[T("第三方登录")])),_:1}),A("div",{class:"flex justify-center mt-[40rpx]"},[(t=1,u.getLoginConfig.autoLoginAuth.includes(t)&&r.value?(e(),M("div",{key:0,class:"flex flex-col items-center",onClick:l[10]||(l[10]=(...e)=>I(_e)&&I(_e)(...e))},[A("img",{src:"/mobile/assets/icon_wx.a848c744.png",class:"w-[80rpx] h-[80rpx]"}),A("div",{class:"text-sm mt-[10px]"},"微信登录")])):s("v-if",!0))])];var t})),_:1})):s("v-if",!0)])),_:1})])),_:1})}}}),[["__scopeId","data-v-63c0abaa"]]);export{de as default};
