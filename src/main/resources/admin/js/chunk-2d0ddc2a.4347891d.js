(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2d0ddc2a"],{8381:function(e,t,n){"use strict";n.r(t);var c=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("page-view",[n("a-row",[n("a-col",{attrs:{span:24}},[e.options.developer_mode?n("div",{staticClass:"card-container"},[n("a-tabs",{attrs:{type:"card"},model:{value:e.activeKey,callback:function(t){e.activeKey=t},expression:"activeKey"}},e._l(e.panes,(function(t){return n("a-tab-pane",{key:t.key},[n("span",{attrs:{slot:"tab"},slot:"tab"},[n("a-icon",{attrs:{type:t.icon}}),e._v(e._s(t.title)+" ")],1),n(t.component,{tag:"component"})],1)})),1)],1):n("a-alert",{attrs:{description:"当前没有启用开发者选项，请启用之后再访问该页面！",message:"提示",showIcon:"",type:"error"}})],1)],1)],1)},r=[],o=(n("7ad2"),n("7c02"),n("0277"),n("3598")),a=n("6c71"),i=n("680a");function u(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var c=Object.getOwnPropertySymbols(e);t&&(c=c.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,c)}return n}function s(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?u(Object(n),!0).forEach((function(t){Object(o["a"])(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):u(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}var l={components:{PageView:i["c"]},data:function(){var e=[{title:"运行环境",icon:"safety",component:function(){return n.e("chunk-2d0cc0b1").then(n.bind(null,"4bf3"))},key:"environment"},{title:"实时日志",icon:"code",component:function(){return Promise.all([n.e("chunk-339698e7"),n.e("chunk-2d0dd7a6"),n.e("chunk-2d2253f7")]).then(n.bind(null,"e41c"))},key:"runtimeLogs"},{title:"系统变量",icon:"table",component:function(){return n.e("chunk-2d208732").then(n.bind(null,"a59a"))},key:"optionsList"},{title:"静态存储",icon:"cloud",component:function(){return Promise.all([n.e("chunk-339698e7"),n.e("chunk-2d0be659")]).then(n.bind(null,"2fa0"))},key:"staticStorage"},{title:"设置",icon:"setting",component:function(){return n.e("chunk-2d238672").then(n.bind(null,"feda"))},key:"settings"}];return{activeKey:e[0].key,panes:e}},computed:s({},Object(a["c"])(["options"])),beforeRouteEnter:function(e,t,n){var c=e.query.activeKey;n((function(e){c&&(e.activeKey=c)}))},watch:{activeKey:function(e){if(e){var t=this.$router.history.current.path;this.$router.push({path:t,query:{activeKey:e}}).catch((function(e){return e}))}}}},p=l,d=n("bdd7"),b=Object(d["a"])(p,c,r,!1,null,null,null);t["default"]=b.exports}}]);