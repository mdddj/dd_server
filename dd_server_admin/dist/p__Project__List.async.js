"use strict";(self.webpackChunk=self.webpackChunk||[]).push([[5401],{30244:function(b,v,_){_.r(v),_.d(v,{default:function(){return C}});var P=_(15009),n=_.n(P),D=_(97857),o=_.n(D),M=_(99289),u=_.n(M),O=_(90930),j=_(90580),m=_(50942),y=_(45894),U=_(78957),I=_(86738),h=_(14726),r=_(85893);function g(d){return c.apply(this,arguments)}function c(){return c=u()(n()().mark(function d(t){return n()().wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return e.abrupt("return",(0,m.request)("/api/project/findBy",{method:"GET",params:t}));case 1:case"end":return e.stop()}},d)})),c.apply(this,arguments)}function B(d){return E.apply(this,arguments)}function E(){return E=u()(n()().mark(function d(t){return n()().wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return e.abrupt("return",(0,m.request)("/api/project/delete",{method:"DELETE",params:{id:t}}));case 1:case"end":return e.stop()}},d)})),E.apply(this,arguments)}function C(){var d=(0,m.useNavigate)();return(0,r.jsx)(O._z,{children:(0,r.jsx)(j.Z,{request:function(){var t=u()(n()().mark(function l(e,f){var s;return n()().wrap(function(a){for(;;)switch(a.prev=a.next){case 0:return a.next=2,g(o()(o()(o()({},e),f),{},{remove:1}));case 2:return s=a.sent,a.abrupt("return",{data:s.data.content,total:s.data.totalElements,success:s.success});case 4:case"end":return a.stop()}},l)}));return function(l,e){return t.apply(this,arguments)}}(),columns:[{key:"id",dataIndex:"id",title:"\u9879\u76EE\u4E3B\u952E"},{key:"name",dataIndex:"name",title:"\u9879\u76EE\u540D\u79F0"},{key:"description",dataIndex:"description",title:"\u9879\u76EE\u4ECB\u7ECD"},{key:"logo",dataIndex:"logo",title:"\u9879\u76EElogo",render:function(l,e){return(0,r.jsx)(r.Fragment,{children:e.logo!==""&&(0,r.jsx)(y.Z,{src:e.logo,width:44,height:44})})}},{key:"github",dataIndex:"github",title:"\u6E90\u7801\u4ED3\u5E93"},{key:"downloadUrl",dataIndex:"downloadUrl",title:"\u4E0B\u8F7D\u5730\u5740"},{key:"previewUrl",dataIndex:"previewUrl",title:"\u9884\u89C8\u5730\u5740"},{title:"\u64CD\u4F5C",render:function(l,e,f,s){return(0,r.jsxs)(U.Z,{children:[(0,r.jsx)(I.Z,{title:"\u786E\u5B9A\u5220\u9664\u5417? ",onConfirm:u()(n()().mark(function p(){return n()().wrap(function(i){for(;;)switch(i.prev=i.next){case 0:return i.next=2,B(e.id);case 2:s==null||s.reload();case 3:case"end":return i.stop()}},p)})),children:(0,r.jsx)(h.ZP,{size:"small",type:"link",children:"\u5220\u9664"})}),(0,r.jsx)(h.ZP,{size:"small",type:"link",onClick:function(){d("/project/new?id="+e.id)},children:"\u7F16\u8F91"})]})}}],rowKey:"id"})})}}}]);
