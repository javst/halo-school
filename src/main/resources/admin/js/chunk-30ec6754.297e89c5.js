(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-30ec6754"],{"5b50":function(t,e,a){"use strict";function n(t,e){(null==e||e>t.length)&&(e=t.length);for(var a=0,n=new Array(e);a<e;a++)n[a]=t[a];return n}function r(t){if(Array.isArray(t))return n(t)}function o(t){if("undefined"!==typeof Symbol&&null!=t[Symbol.iterator]||null!=t["@@iterator"])return Array.from(t)}function s(t,e){if(t){if("string"===typeof t)return n(t,e);var a=Object.prototype.toString.call(t).slice(8,-1);return"Object"===a&&t.constructor&&(a=t.constructor.name),"Map"===a||"Set"===a?Array.from(t):"Arguments"===a||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(a)?n(t,e):void 0}}function i(){throw new TypeError("Invalid attempt to spread non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}function u(t){return r(t)||o(t)||s(t)||i()}a.d(e,"a",(function(){return u}))},caf6:function(t,e,a){"use strict";var n=a("9efd"),r="/api/admin/posts",o={listLatest:function(t){return Object(n["a"])({url:"".concat(r,"/latest"),params:{top:t},method:"get"})},query:function(t){return Object(n["a"])({url:r,params:t,method:"get"})},get:function(t){return Object(n["a"])({url:"".concat(r,"/").concat(t),method:"get"})},inStock:function(t,e){return Object(n["a"])({url:r+"/inStock",method:"post",data:e,params:{categories:t}})},inStockAll:function(t){return Object(n["a"])({url:r+"/inStockAll",method:"post",data:t})},create:function(t,e){return Object(n["a"])({url:r,method:"post",data:t,params:{autoSave:e}})},update:function(t,e,a){return Object(n["a"])({url:"".concat(r,"/").concat(t),method:"put",data:e,params:{autoSave:a}})},updateDraft:function(t,e){return Object(n["a"])({url:"".concat(r,"/").concat(t,"/status/draft/content"),method:"put",data:{content:e}})},updateStatus:function(t,e){return Object(n["a"])({url:"".concat(r,"/").concat(t,"/status/").concat(e),method:"put"})},updateStatusInBatch:function(t,e){return Object(n["a"])({url:"".concat(r,"/status/").concat(e),data:t,method:"put"})},delete:function(t){return Object(n["a"])({url:"".concat(r,"/").concat(t),method:"delete"})},deleteInBatch:function(t){return Object(n["a"])({url:"".concat(r),data:t,method:"delete"})},preview:function(t){return Object(n["a"])({url:"".concat(r,"/preview/").concat(t),method:"get"})},postStatus:{PUBLISHED:{value:"PUBLISHED",color:"green",status:"success",text:"已发布"},DRAFT:{value:"DRAFT",color:"yellow",status:"warning",text:"草稿"},RECYCLE:{value:"RECYCLE",color:"red",status:"error",text:"回收站"},INTIMATE:{value:"INTIMATE",color:"blue",status:"success",text:"私密"}},permalinkType:{DEFAULT:{type:"DEFAULT",text:"默认"},YEAR:{type:"YEAR",text:"年份型"},DATE:{type:"DATE",text:"年月型"},DAY:{type:"DAY",text:"年月日型"},ID:{type:"ID",text:"ID 型"},ID_SLUG:{type:"ID_SLUG",text:"ID 别名型"}}};e["a"]=o},f806:function(t,e,a){"use strict";a.r(e);var n=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("page-view",[a("a-card",{attrs:{bordered:!1,bodyStyle:{padding:"16px"}}},[a("div",{staticClass:"table-page-search-wrapper"},[a("a-form",{attrs:{layout:"inline"}},[a("a-row",{attrs:{gutter:48}},[a("a-col",{attrs:{md:6,sm:24}},[a("a-form-item",{attrs:{label:"姓名："}},[a("a-input",{model:{value:t.queryParam.username,callback:function(e){t.$set(t.queryParam,"username",e)},expression:"queryParam.username"}})],1)],1),a("a-col",{attrs:{md:6,sm:24}},[a("a-form-item",{attrs:{label:"申请日期："}},[a("div",[a("a-date-picker",{on:{change:t.onChange},model:{value:t.queryParam.datetime,callback:function(e){t.$set(t.queryParam,"datetime",e)},expression:"queryParam.datetime"}}),a("br")],1)])],1),a("a-col",{attrs:{md:6,sm:24}},[a("span",{staticClass:"table-page-search-submitButtons"},[a("a-space",[a("a-button",{attrs:{type:"primary"},on:{click:function(e){return t.handleQuery()}}},[t._v("查询")]),a("a-button",{on:{click:function(e){return t.handleResetParam()}}},[t._v("重置")]),a("a-button",{on:{click:function(e){return t.printOrder()}}},[t._v("打印")])],1)],1)])],1)],1)],1),a("a-table",{ref:"print",attrs:{bordered:"",columns:t.columns,"data-source":t.ordersList,pagination:!1,loading:t.postsLoading},scopedSlots:t._u([{key:"username",fn:function(e){return a("a",{},[t._v(t._s(e))])}},{key:"states",fn:function(e){return a("span",{},[a("a-tag",{attrs:{color:0==e?"volcano":1==e?"green":"geekblue"}},[t._v(" "+t._s(0==e?"待审核":1==e?"已通过":"未通过")+" ")])],1)}}])},[a("span",{attrs:{slot:"username"},slot:"username"},[a("a-icon",{attrs:{type:"smile-o"}}),t._v(" 姓名")],1)]),a("div",{staticClass:"page-wrapper"},[t.pagination.open?a("a-pagination",{staticClass:"pagination",attrs:{"page-size-options":t.pageSizeOptions,total:t.total,"show-size-changer":"","page-size":t.pageSize,current:t.current,defaultPageSize:20,showSizeChanger:"",showLessItems:""},on:{showSizeChange:t.onShowSizeChange,change:t.handlePaginationChange},scopedSlots:t._u([{key:"buildOptionText",fn:function(e){return[e.value!==t.total?a("span",[t._v(t._s(e.value)+"条/页")]):t._e(),e.value===t.total?a("span",[t._v("全部")]):t._e()]}}],null,!1,3668733497),model:{value:t.current,callback:function(e){t.current=e},expression:"current"}}):t._e()],1)],1)],1)},r=[],o=a("5b50"),s=a("c8c3"),i=a("9efd"),u=(a("caf6"),"/api/admin/compete"),c={getLatest:function(t,e){return Object(i["a"])({url:"".concat(u,"/latest"),params:{top:e,start:t},method:"get"})},countOrder:function(){return Object(i["a"])({url:"".concat(u,"/countCompete"),method:"post"})},queryOrder:function(t,e){return Object(i["a"])({url:"".concat(u,"/queryCompete"),method:"post",data:{username:t,createTime:e},params:{username:t,createTime:e}})}},l=c,d=a("9158"),m=[{dataIndex:"username",key:"username",title:"姓名",slots:{title:"username"},scopedSlots:{customRender:"username"}},{title:"设备名称",dataIndex:"title",key:"title"},{title:"元件型号",dataIndex:"norm",key:"norm"},{title:"购买链接",dataIndex:"link",key:"link"},{title:"数量",key:"number",dataIndex:"number"},{title:"申请时间",key:"createTime",dataIndex:"createTime"}],p={components:{PageView:s["a"]},data:function(){return{columns:m,ordersList:[],queryParam:{username:null,state:null,datetime:null},pageSizeOptions:["10","20","30","40","50"],current:1,pageSize:10,total:null,props:{value:10},pagination:{page:null,size:null,open:!0},postsLoading:!1}},mounted:function(){var t=this;this.getLatestOrder(),l.countOrder().then((function(e){t.total=e.data.data,console.log(e.data),t.pageSizeOptions.push(e.data.data)}))},methods:{printOrder:function(){this.$print(this.$refs.print)},getLatestOrder:function(){var t=this;l.getLatest().then((function(e){t.ordersList=e.data.data;var a=0;for(a;a<t.ordersList.length;a++)t.ordersList[a].createTime=Object(d["a"])(t.ordersList[a].createTime)}))},handlePaginationChange:function(t,e){var a=this;this.$log.debug("Current: ".concat(t,", PageSize: ").concat(e)),this.pagination.page=t,this.pagination.size=e,this.postsLoading=!0,l.getLatest((t-1)*e,e).then((function(t){a.ordersList=t.data.data;var e=0;for(e;e<a.ordersList.length;e++)a.ordersList[e].createTime=Object(d["a"])(a.ordersList[e].createTime);a.postsLoading=!1}))},handleQuery:function(){var t=this;console.log(this.queryParam),this.postsLoading=!1,console.log(this.queryParam.username,this.queryParam.datetime),null==this.queryParam.username&&null==this.queryParam.datetime?this.getLatestOrder():l.queryOrder(this.queryParam.username,this.queryParam.datetime).then((function(e){t.ordersList=e.data.data;var a=0;for(a;a<t.ordersList.length;a++)t.ordersList[a].createTime=Object(d["a"])(t.ordersList[a].createTime);t.postsLoading=!1}))},onShowSizeChange:function(t,e){this.pageSize=e,this.handlePaginationChange(this.current,this.pageSize)},onChange:function(t,e){this.queryParam.datetime=e,console.log(this.queryParam.datetime,e)},handleResetParam:function(){this.queryParam.datetime=null,this.queryParam.username=null,this.queryParam.state=-1},passOrder:function(t,e){var a=this,n=1==e?"订单审核通过":"订单审核不通过";this.$message.loading({content:"Loading..."}),console.log(t),l.passOrder(t.id,e).then((function(r){r.data>0?(a.$message.success(n,5),t.state=e):a.$message.error("操作失败")}))},deleteOrder:function(t){var e=this;this.$message.loading({content:"Loading..."}),l.deleteOrder(t.id).then((function(a){if(console.log(a),a.data>0){e.$message.success("删除成功",5);var n=Object(o["a"])(e.ordersList);e.ordersList=n.filter((function(e){return e.id!=t.id}))}else e.$message.error("删除失败")}))}}},h=p,g=a("bdd7"),f=Object(g["a"])(h,n,r,!1,null,null,null);e["default"]=f.exports}}]);