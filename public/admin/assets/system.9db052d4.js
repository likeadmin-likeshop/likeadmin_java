import{r}from"./index.f6ec1d34.js";function e(){return r.get({url:"/monitor/server"})}function o(t){return r.get({url:"/system/log/operate",params:t})}function s(){return r.get({url:"/monitor/cache"})}function a(t){return r.get({url:"/crontab/list",params:t})}function u(t){return r.post({url:"/crontab/add",params:t})}function c(t){return r.get({url:"/crontab/detail",params:t})}function i(t){return r.post({url:"/crontab/edit",params:t})}function l(t){return r.post({url:"/crontab/del",params:t})}export{e as a,o as b,c,i as d,u as e,l as f,a as g,s};
