(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2d208732"],{a59a:function(e,t,a){"use strict";a.r(t);var o=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"option-tab-wrapper"},[a("a-card",{attrs:{bordered:!1,bodyStyle:{padding:0}}},[a("div",{staticClass:"table-page-search-wrapper"},[a("a-form",{attrs:{layout:"inline"}},[a("a-row",{attrs:{gutter:48}},[a("a-col",{attrs:{md:6,sm:24}},[a("a-form-item",{attrs:{label:"关键词："}},[a("a-input",{on:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.handleQuery()}},model:{value:e.queryParam.keyword,callback:function(t){e.$set(e.queryParam,"keyword",t)},expression:"queryParam.keyword"}})],1)],1),a("a-col",{attrs:{md:6,sm:24}},[a("a-form-item",{attrs:{label:"类型："}},[a("a-select",{attrs:{placeholder:"请选择类型",allowClear:""},on:{change:function(t){return e.handleQuery()}},model:{value:e.queryParam.type,callback:function(t){e.$set(e.queryParam,"type",t)},expression:"queryParam.type"}},e._l(Object.keys(e.optionType),(function(t){return a("a-select-option",{key:t,attrs:{value:t}},[e._v(e._s(e.optionType[t].text))])})),1)],1)],1),a("a-col",{attrs:{md:12,sm:24}},[a("span",{staticClass:"table-page-search-submitButtons"},[a("a-space",[a("a-button",{attrs:{type:"primary"},on:{click:function(t){return e.handleQuery()}}},[e._v("查询")]),a("a-button",{on:{click:function(t){return e.handleResetParam()}}},[e._v("重置")])],1)],1)])],1)],1)],1),a("div",{staticClass:"table-operator"},[a("a-button",{attrs:{type:"primary",icon:"plus"},on:{click:e.handleOpenFormModal}},[e._v("新增")])],1),a("div",{staticClass:"mt-4"},[a("a-table",{attrs:{rowKey:function(e){return e.id},columns:e.columns,dataSource:e.formattedData,loading:e.loading,pagination:!1,scrollToFirstRowOnChange:!0},scopedSlots:e._u([{key:"type",fn:function(t){return a("span",{},[e._v(" "+e._s(t.text)+" ")])}},{key:"createTime",fn:function(t){return a("span",{},[a("a-tooltip",{attrs:{placement:"top"}},[a("template",{slot:"title"},[e._v(" "+e._s(e._f("moment")(t))+" ")]),e._v(" "+e._s(e._f("timeAgo")(t))+" ")],2)],1)}},{key:"updateTime",fn:function(t){return a("span",{},[a("a-tooltip",{attrs:{placement:"top"}},[a("template",{slot:"title"},[e._v(" "+e._s(e._f("moment")(t))+" ")]),e._v(" "+e._s(e._f("timeAgo")(t))+" ")],2)],1)}},{key:"action",fn:function(t,o){return a("span",{},[a("a",{attrs:{href:"javascript:void(0);"},on:{click:function(t){return e.handleOpenEditFormModal(o)}}},[e._v("编辑")]),a("a-divider",{attrs:{type:"vertical"}}),a("a-popconfirm",{attrs:{title:"你确定要永久删除该变量？",okText:"确定",cancelText:"取消"},on:{confirm:function(t){return e.handleDeleteOption(o.id)}}},[a("a",{attrs:{href:"javascript:void(0);"}},[e._v("删除")])])],1)}}])}),a("div",{staticClass:"page-wrapper"},[a("a-pagination",{staticClass:"pagination",attrs:{current:e.pagination.page,total:e.pagination.total,defaultPageSize:e.pagination.size,pageSizeOptions:["1","2","5","10","20","50","100"],showSizeChanger:"",showLessItems:""},on:{showSizeChange:e.handlePaginationChange,change:e.handlePaginationChange}})],1)],1)]),a("a-modal",{attrs:{title:e.formTitle,afterClose:e.onFormClose},model:{value:e.form.visible,callback:function(t){e.$set(e.form,"visible",t)},expression:"form.visible"}},[a("template",{slot:"footer"},[a("ReactiveButton",{attrs:{loading:e.form.saving,errored:e.form.saveErrored,text:"保存",loadedText:"保存成功",erroredText:"保存失败"},on:{click:e.handleSaveOrUpdate,callback:e.handleSaveOrUpdateCallback}})],1),e.form.model.type===e.optionType.INTERNAL.value?a("a-alert",{attrs:{message:"注意：在不知道系统变量的具体用途时，请不要随意修改！",banner:"",closable:""}}):e._e(),a("a-form-model",{ref:"optionForm",attrs:{model:e.form.model,rules:e.form.rules,layout:"vertical"}},[a("a-form-model-item",{attrs:{prop:"key",label:"Key："}},[a("a-input",{ref:"keyInput",model:{value:e.form.model.key,callback:function(t){e.$set(e.form.model,"key",t)},expression:"form.model.key"}})],1),a("a-form-model-item",{attrs:{prop:"value",label:"Value："}},[a("a-input",{attrs:{type:"textarea",autoSize:{minRows:5}},model:{value:e.form.model.value,callback:function(t){e.$set(e.form.model,"value",t)},expression:"form.model.value"}})],1)],1)],2)],1)},n=[],r=(a("7ad2"),a("7c02"),a("0277"),a("6249"),a("3598")),i=a("482b"),s=a("6c71");function l(e,t){var a=Object.keys(e);if(Object.getOwnPropertySymbols){var o=Object.getOwnPropertySymbols(e);t&&(o=o.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),a.push.apply(a,o)}return a}function c(e){for(var t=1;t<arguments.length;t++){var a=null!=arguments[t]?arguments[t]:{};t%2?l(Object(a),!0).forEach((function(t){Object(r["a"])(e,t,a[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(a)):l(Object(a)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(a,t))}))}return e}var u=[{title:"Key",dataIndex:"key",ellipsis:!0,scopedSlots:{customRender:"key"}},{title:"Value",dataIndex:"value",ellipsis:!0,scopedSlots:{customRender:"value"}},{title:"类型",dataIndex:"typeProperty",width:"100px",scopedSlots:{customRender:"type"}},{title:"创建时间",dataIndex:"createTime",width:"200px",scopedSlots:{customRender:"createTime"}},{title:"更新时间",dataIndex:"updateTime",width:"200px",scopedSlots:{customRender:"updateTime"}},{title:"操作",dataIndex:"action",width:"120px",scopedSlots:{customRender:"action"}}],p={name:"OptionsList",data:function(){return{optionType:i["a"].type,columns:u,pagination:{page:1,size:10,sort:null,total:1},queryParam:{page:0,size:10,sort:null,keyword:null,type:null},loading:!1,options:[],form:{visible:!1,model:{},rules:{key:[{required:!0,message:"* Key 不能为空",trigger:["change"]}],value:[{required:!0,message:"* Value 不能为空",trigger:["change"]}]},saving:!1,saveErrored:!1}}},computed:{formattedData:function(){var e=this;return this.options.map((function(t){return t.typeProperty=e.optionType[t.type],t}))},formTitle:function(){return this.form.model.id?"编辑":"新增"}},beforeMount:function(){this.hanldeListOptions()},methods:c(c({},Object(s["b"])(["refreshOptionsCache"])),{},{hanldeListOptions:function(){var e=this;this.loading=!0,this.queryParam.page=this.pagination.page-1,this.queryParam.size=this.pagination.size,this.queryParam.sort=this.pagination.sort,i["a"].query(this.queryParam).then((function(t){e.options=t.data.data.content,e.pagination.total=t.data.data.total})).finally((function(){setTimeout((function(){e.loading=!1}),200)}))},handleQuery:function(){this.handlePaginationChange(1,this.pagination.size)},handleDeleteOption:function(e){var t=this;i["a"].delete(e).then((function(){t.$message.success("删除成功！")})).finally((function(){t.hanldeListOptions(),t.refreshOptionsCache()}))},handleOpenFormModal:function(){var e=this;this.form.visible=!0,this.$nextTick((function(){e.$refs.keyInput.focus()}))},handleOpenEditFormModal:function(e){var t=this;this.form.model=e,this.form.visible=!0,this.$nextTick((function(){t.$refs.keyInput.focus()}))},handlePaginationChange:function(e,t){this.$log.debug("Current: ".concat(e,", PageSize: ").concat(t)),this.pagination.page=e,this.pagination.size=t,this.hanldeListOptions()},handleResetParam:function(){this.queryParam.keyword=null,this.queryParam.type=null,this.handlePaginationChange(1,this.pagination.size)},onFormClose:function(){this.form.visible=!1,this.form.model={}},handleSaveOrUpdate:function(){var e=this;e.$refs.optionForm.validate((function(t){t&&(e.form.saving=!0,e.form.model.id?i["a"].update(e.form.model.id,e.form.model).catch((function(){e.form.saveErrored=!0})).finally((function(){setTimeout((function(){e.form.saving=!1}),400)})):(e.form.model.type=e.optionType.CUSTOM.value,i["a"].create(e.form.model).catch((function(){e.form.saveErrored=!0})).finally((function(){setTimeout((function(){e.form.saving=!1}),400)}))))}))},handleSaveOrUpdateCallback:function(){this.form.saveErrored?this.form.saveErrored=!1:(this.form.model={},this.form.visible=!1,this.hanldeListOptions(),this.refreshOptionsCache())}})},d=p,m=a("bdd7"),f=Object(m["a"])(d,o,n,!1,null,null,null);t["default"]=f.exports}}]);