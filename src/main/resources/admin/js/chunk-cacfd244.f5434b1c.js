(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-cacfd244"],{"0b97":function(t,e,a){"use strict";a.r(e);var n=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("page-view",[a("a-card",{attrs:{bordered:!1,bodyStyle:{padding:"16px"}}},[a("div",{staticClass:"table-page-search-wrapper"},[a("a-form",{attrs:{layout:"inline"}},[a("a-row",{attrs:{gutter:48}},[a("a-col",{attrs:{md:6,sm:24}},[a("a-form-item",{attrs:{label:"元件名称："}},[a("a-input",{on:{keyup:function(e){return!e.type.indexOf("key")&&t._k(e.keyCode,"enter",13,e.key,"Enter")?null:t.handleQuery()}},model:{value:t.queryParam.keyword,callback:function(e){t.$set(t.queryParam,"keyword",e)},expression:"queryParam.keyword"}})],1)],1),a("a-col",{attrs:{md:6,sm:24}},[a("a-form-item",{attrs:{label:"分类目录："}},[a("a-select",{attrs:{placeholder:"请选择分类",loading:t.categoriesLoading,allowClear:""},on:{change:function(e){return t.handleQuery()}},model:{value:t.queryParam.categoryId,callback:function(e){t.$set(t.queryParam,"categoryId",e)},expression:"queryParam.categoryId"}},t._l(t.categories,(function(e){return a("a-select-option",{key:e.id},[t._v(t._s(e.name)+"("+t._s(e.postCount)+")")])})),1)],1)],1),a("a-col",{attrs:{md:6,sm:24}},[a("span",{staticClass:"table-page-search-submitButtons"},[a("a-space",[a("a-button",{attrs:{type:"primary"},on:{click:function(e){return t.handleQuery()}}},[t._v("查询")]),a("a-button",{on:{click:function(e){return t.handleResetParam()}}},[t._v("重置")]),a("a-button",{on:{click:function(e){return t.printPost()}}},[t._v("打印")])],1)],1)])],1)],1)],1),a("div",{staticClass:"mt-4"},[t.isMobile()?a("a-list",{attrs:{itemLayout:"vertical",size:"large",pagination:!1,dataSource:t.formattedPosts,loading:t.postsLoading},scopedSlots:t._u([{key:"renderItem",fn:function(e,n){return a("a-list-item",{key:n},[a("template",{slot:"extra"},[a("span",[a("a-badge",{attrs:{status:e.statusProperty.status,text:e.statusProperty.text}})],1)]),a("a-list-item-meta",[a("template",{slot:"description"},[t._v(" "+t._s(t._f("moment")(e.createTime))+" ")]),a("span",{staticStyle:{"max-width":"300px",display:"block","white-space":"nowrap",overflow:"hidden","text-overflow":"ellipsis"},attrs:{slot:"title"},slot:"title"},[0!==e.topPriority?a("a-icon",{staticStyle:{"margin-right":"3px"},attrs:{type:"pushpin",theme:"twoTone",twoToneColor:"red"}}):t._e()],1)],2),a("span",[t._v(" "+t._s(e.summary)+"... ")]),a("br"),a("br"),t._l(e.categories,(function(e,n){return a("a-tag",{key:"category_"+n,staticStyle:{"margin-bottom":"8px"},attrs:{color:"blue"},on:{click:function(a){return t.handleSelectCategory(e)}}},[t._v(t._s(e.name))])})),a("br"),t._l(e.tags,(function(e,n){return a("a-tag",{key:"tag_"+n,staticStyle:{"margin-bottom":"8px"},attrs:{color:"green"}},[t._v(t._s(e.name))])}))],2)}}],null,!1,2215050788)}):a("a-table",{ref:"print",attrs:{rowKey:function(t){return t.id},bordered:"",columns:t.columns,dataSource:t.formattedPosts,loading:t.postsLoading,pagination:!1,scrollToFirstRowOnChange:!0},scopedSlots:t._u([{key:"categories",fn:function(e){return a("span",{},t._l(e,(function(e,n){return a("a-tag",{key:n,staticStyle:{"margin-bottom":"8px",cursor:"pointer"},attrs:{color:"blue"},on:{click:function(a){return t.handleSelectCategory(e)}}},[t._v(t._s(e.name))])})),1)}},{key:"tags",fn:function(e){return a("span",{},t._l(e,(function(e,n){return a("a-tag",{key:n,staticStyle:{"margin-bottom":"8px"},attrs:{color:"green"}},[t._v(t._s(e.name))])})),1)}},{key:"createTime",fn:function(e){return a("span",{},[a("a-tooltip",{attrs:{placement:"top"}},[a("template",{slot:"title"},[t._v(" "+t._s(t._f("moment")(e))+" ")]),t._v(" "+t._s(t._f("moment")(e))+" ")],2)],1)}}])}),a("div",{staticClass:"page-wrapper"},[t.posts&&t.posts.length>0?a("a-pagination",{staticClass:"pagination",attrs:{current:t.pagination.page,total:t.pagination.total,defaultPageSize:t.pagination.size,pageSizeOptions:["1","2","5","10","20","50","100"],showSizeChanger:"",showLessItems:""},on:{showSizeChange:t.handlePaginationChange,change:t.handlePaginationChange}}):t._e()],1)],1)])],1)},o=[],s=(a("6249"),a("0ef1"),a("1a86")),r=a("680a"),i=a("c405"),c=a("caf6"),u=[{title:"编号",key:"deviceNum",dataIndex:"deviceNum"},{title:"标题",dataIndex:"title",ellipsis:!0,scopedSlots:{customRender:"postTitle"}},{title:"类别",dataIndex:"categories",scopedSlots:{customRender:"categories"}},{title:"规格",dataIndex:"norms",key:"norms"},{title:"单位",dataIndex:"deviceType",key:"deviceType"},{title:"单价",key:"price",dataIndex:"price"},{title:"库存数量",key:"stock",dataIndex:"stock"},{title:"入库人员",key:"importPeople",dataIndex:"importPeople"},{title:"入库时间",dataIndex:"createTime",width:"170px",scopedSlots:{customRender:"createTime"}}],l={components:{PageView:r["c"]},mixins:[s["a"],s["b"]],data:function(){return{postStatus:c["a"].postStatus,pagination:{page:1,size:10,sort:null,total:1},queryParam:{page:0,size:10,sort:null,keyword:null,categoryId:null,status:null},columns:u,selectedRowKeys:[],categories:[],selectedMetas:[{key:"",value:""}],posts:[],postsLoading:!1,categoriesLoading:!1,postSettingVisible:!1,postCommentVisible:!1,selectedPost:{},selectedTagIds:[],selectedCategoryIds:[]}},computed:{formattedPosts:function(){var t=this;return this.posts.map((function(e){return e.statusProperty=t.postStatus[e.status],e}))}},beforeMount:function(){this.handleListCategories()},destroyed:function(){this.postSettingVisible&&(this.postSettingVisible=!1)},beforeRouteEnter:function(t,e,a){a((function(e){t.query.page&&(e.pagination.page=Number(t.query.page)+1),t.query.size&&(e.pagination.size=Number(t.query.size)),e.queryParam.sort=t.query.sort,e.queryParam.keyword=t.query.keyword,e.queryParam.categoryId=t.query.categoryId,e.queryParam.status=t.query.status,e.handleListPosts()}))},beforeRouteLeave:function(t,e,a){this.postSettingVisible&&(this.postSettingVisible=!1),a()},watch:{queryParam:{deep:!0,handler:function(t){if(t){var e=JSON.parse(JSON.stringify(this.queryParam)),a=this.$router.history.current.path;this.$router.push({path:a,query:e}).catch((function(t){return t}))}}}},methods:{printPost:function(){this.$print(this.$refs.print)},handleListPosts:function(){var t=this,e=!(arguments.length>0&&void 0!==arguments[0])||arguments[0];e&&(this.postsLoading=!0),this.queryParam.page=this.pagination.page-1,this.queryParam.size=this.pagination.size,this.queryParam.sort=this.pagination.sort,c["a"].query(this.queryParam).then((function(e){t.posts=e.data.data.content,console.log(t.posts),t.pagination.total=e.data.data.total})).finally((function(){setTimeout((function(){t.postsLoading=!1}),200)}))},handleListCategories:function(){var t=this;this.categoriesLoading=!0,i["a"].listAll(!0).then((function(e){t.categories=e.data.data})).finally((function(){setTimeout((function(){t.categoriesLoading=!1}),200)}))},handleEditClick:function(t){this.$router.push({name:"PostEdit",query:{postId:t.id}})},onSelectionChange:function(t){this.selectedRowKeys=t,this.$log.debug("SelectedRowKeys: ".concat(t))},getCheckboxProps:function(t){return{props:{disabled:null==this.queryParam.status||""===this.queryParam.status,name:t.title}}},handlePaginationChange:function(t,e){this.$log.debug("Current: ".concat(t,", PageSize: ").concat(e)),this.pagination.page=t,this.pagination.size=e,this.handleListPosts()},handleResetParam:function(){this.queryParam.keyword=null,this.queryParam.categoryId=null,this.queryParam.status=null,this.handleClearRowKeys(),this.handlePaginationChange(1,this.pagination.size),this.handleListCategories()},handleQuery:function(){this.handleClearRowKeys(),this.handlePaginationChange(1,this.pagination.size)},handleSelectCategory:function(t){this.queryParam.categoryId=t.id,this.handleQuery()},handleEditStatusMore:function(t){var e=this;this.selectedRowKeys.length<=0?this.$message.info("请至少选择一项！"):c["a"].updateStatusInBatch(this.selectedRowKeys,t).then((function(){e.$log.debug("postId: ".concat(e.selectedRowKeys,", status: ").concat(t)),e.selectedRowKeys=[]})).finally((function(){e.handleListPosts()}))},handleClearRowKeys:function(){this.selectedRowKeys=[]},onRefreshPostFromSetting:function(t){this.selectedPost=t},onRefreshTagIdsFromSetting:function(t){this.selectedTagIds=t},onRefreshCategoryIdsFromSetting:function(t){this.selectedCategoryIds=t},onRefreshPostMetasFromSetting:function(t){this.selectedMetas=t}}},d=l,p=a("bdd7"),h=Object(p["a"])(d,n,o,!1,null,null,null);e["default"]=h.exports},c405:function(t,e,a){"use strict";a("8354"),a("7c02");var n=a("9efd"),o="/api/admin/categories",s={};function r(t,e){e.forEach((function(e){t.key===e.parentId&&(t.children||(t.children=[]),t.children.push({key:e.id,title:e.name,isLeaf:!1}))})),t.children?t.children.forEach((function(t){return r(t,e)})):t.isLeaf=!0}s.listAll=function(){var t=arguments.length>0&&void 0!==arguments[0]&&arguments[0];return Object(n["a"])({url:"".concat(o),params:{more:t},method:"get"})},s.listTree=function(){return Object(n["a"])({url:"".concat(o,"/tree_view"),method:"get"})},s.create=function(t){return Object(n["a"])({url:o,data:t,method:"post"})},s.delete=function(t){return Object(n["a"])({url:"".concat(o,"/").concat(t),method:"delete"})},s.get=function(t){return Object(n["a"])({url:"".concat(o,"/").concat(t),method:"get"})},s.update=function(t,e){return Object(n["a"])({url:"".concat(o,"/").concat(t),data:e,method:"put"})},s.concreteTree=function(t){var e={key:0,title:"top",children:[]};return r(e,t),e.children},e["a"]=s},caf6:function(t,e,a){"use strict";var n=a("9efd"),o="/api/admin/posts",s={listLatest:function(t){return Object(n["a"])({url:"".concat(o,"/latest"),params:{top:t},method:"get"})},query:function(t){return Object(n["a"])({url:o,params:t,method:"get"})},get:function(t){return Object(n["a"])({url:"".concat(o,"/").concat(t),method:"get"})},inStock:function(t,e){return Object(n["a"])({url:o+"/inStock",method:"post",data:e,params:{categories:t}})},inStockAll:function(t){return Object(n["a"])({url:o+"/inStockAll",method:"post",data:t})},create:function(t,e){return Object(n["a"])({url:o,method:"post",data:t,params:{autoSave:e}})},update:function(t,e,a){return Object(n["a"])({url:"".concat(o,"/").concat(t),method:"put",data:e,params:{autoSave:a}})},updateDraft:function(t,e){return Object(n["a"])({url:"".concat(o,"/").concat(t,"/status/draft/content"),method:"put",data:{content:e}})},updateStatus:function(t,e){return Object(n["a"])({url:"".concat(o,"/").concat(t,"/status/").concat(e),method:"put"})},updateStatusInBatch:function(t,e){return Object(n["a"])({url:"".concat(o,"/status/").concat(e),data:t,method:"put"})},delete:function(t){return Object(n["a"])({url:"".concat(o,"/").concat(t),method:"delete"})},deleteInBatch:function(t){return Object(n["a"])({url:"".concat(o),data:t,method:"delete"})},preview:function(t){return Object(n["a"])({url:"".concat(o,"/preview/").concat(t),method:"get"})},postStatus:{PUBLISHED:{value:"PUBLISHED",color:"green",status:"success",text:"已发布"},DRAFT:{value:"DRAFT",color:"yellow",status:"warning",text:"草稿"},RECYCLE:{value:"RECYCLE",color:"red",status:"error",text:"回收站"},INTIMATE:{value:"INTIMATE",color:"blue",status:"success",text:"私密"}},permalinkType:{DEFAULT:{type:"DEFAULT",text:"默认"},YEAR:{type:"YEAR",text:"年份型"},DATE:{type:"DATE",text:"年月型"},DAY:{type:"DAY",text:"年月日型"},ID:{type:"ID",text:"ID 型"},ID_SLUG:{type:"ID_SLUG",text:"ID 别名型"}}};e["a"]=s}}]);