"use strict";(self.webpackChunk=self.webpackChunk||[]).push([[5661],{67423:function(T,c,n){n.r(c),n.d(c,{default:function(){return M}});var h=n(26068),t=n.n(h),E=n(90228),s=n.n(E),m=n(87999),u=n.n(m),A=n(89130),f=n(42824),O=n(64809),p=n(20917),g=n(48978),y=n(22570),R=n(50717),v=n(5919),i=n(62086);function M(){return(0,i.jsx)(f._z,{title:"\u52A8\u6001\u5217\u8868",children:(0,i.jsx)(O.Z,{columns:[{key:"id",dataIndex:"id",title:"\u4E3B\u952E"},{key:"user",dataIndex:"user",title:"\u53D1\u5E03\u4EBA"},{key:"category",dataIndex:"category",title:"\u5206\u7C7B"},{key:"title",dataIndex:"title",title:"\u6807\u9898"},{key:"content",dataIndex:"content",title:"\u6B63\u6587\u5185\u5BB9"},{key:"createDate",dataIndex:"createDate",title:"\u53D1\u5E03\u65F6\u95F4"},{key:"description",dataIndex:"description",title:"\u63CF\u8FF0"},{key:"type",dataIndex:"type",title:"\u7C7B\u578B"},{key:"images",dataIndex:"images",title:"\u56FE\u7247\u5217\u8868",render:function(l,o){var d;return(0,i.jsx)(p.Z,{children:(d=o.images)===null||d===void 0?void 0:d.map(function(r){return(0,i.jsx)(g.Z,{src:r.url,width:44,height:44},r.id)})})}},{key:"browserUrl",dataIndex:"browserUrl",title:"url"},{title:"\u64CD\u4F5C",render:function(l,o,d,r){return(0,i.jsx)(p.Z,{children:(0,i.jsx)(y.Z,{title:"\u786E\u5B9A\u5220\u9664\u5417?",onConfirm:u()(s()().mark(function a(){var _,e;return s()().wrap(function(D){for(;;)switch(D.prev=D.next){case 0:return _=R.ZP.loading("\u5220\u9664\u4E2D"),D.next=3,(0,A.Lq)(o.id);case 3:e=D.sent,e.success&&(r==null||r.reload()),_();case 6:case"end":return D.stop()}},a)})),children:(0,i.jsx)(v.ZP,{size:"small",danger:!0,children:"\u5220\u9664"})})})}}],request:function(){var P=u()(s()().mark(function l(o,d){var r;return s()().wrap(function(_){for(;;)switch(_.prev=_.next){case 0:return _.next=2,(0,A.TQ)(t()(t()(t()({},o),d),{},{remove:1}));case 2:return r=_.sent,_.abrupt("return",{success:r.success,data:r.data.content,total:r.data.totalElements});case 4:case"end":return _.stop()}},l)}));return function(l,o){return P.apply(this,arguments)}}(),rowKey:"id"})})}},89130:function(T,c,n){n.d(c,{Ip:function(){return A},Lq:function(){return P},Mg:function(){return g},TQ:function(){return R},TZ:function(){return o},h0:function(){return i},xi:function(){return O}});var h=n(90228),t=n.n(h),E=n(87999),s=n.n(E),m=n(80991),u=n(26674);function A(r){return f.apply(this,arguments)}function f(){return f=s()(t()().mark(function r(a){return t()().wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return e.abrupt("return",(0,u.request)("/api/res/list",{method:"GET",params:a}));case 1:case"end":return e.stop()}},r)})),f.apply(this,arguments)}function O(){return p.apply(this,arguments)}function p(){return p=s()(t()().mark(function r(){return t()().wrap(function(_){for(;;)switch(_.prev=_.next){case 0:return _.abrupt("return",(0,u.request)("/api/res/all",{method:"GET"}));case 1:case"end":return _.stop()}},r)})),p.apply(this,arguments)}function g(r){return y.apply(this,arguments)}function y(){return y=s()(t()().mark(function r(a){return t()().wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return e.abrupt("return",(0,u.request)("/api/auth/res-cate-save",{method:"POST",data:a}));case 1:case"end":return e.stop()}},r)})),y.apply(this,arguments)}function R(r){return v.apply(this,arguments)}function v(){return v=s()(t()().mark(function r(a){return t()().wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return e.abrupt("return",(0,u.request)("/api/resource/list",{method:"GET",params:a}));case 1:case"end":return e.stop()}},r)})),v.apply(this,arguments)}function i(r){return M.apply(this,arguments)}function M(){return M=s()(t()().mark(function r(a){return t()().wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return e.abrupt("return",(0,u.request)("/api/resource/add-post",{method:"POST",headers:(0,m.V)(),data:a}));case 1:case"end":return e.stop()}},r)})),M.apply(this,arguments)}function P(r){return l.apply(this,arguments)}function l(){return l=s()(t()().mark(function r(a){return t()().wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return e.abrupt("return",(0,u.request)("/api/resource/delete",{method:"DELETE",params:{id:a}}));case 1:case"end":return e.stop()}},r)})),l.apply(this,arguments)}function o(r){return d.apply(this,arguments)}function d(){return d=s()(t()().mark(function r(a){return t()().wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return e.abrupt("return",(0,u.request)("/api/app/resource/find-by-id",{params:{id:a}}));case 1:case"end":return e.stop()}},r)})),d.apply(this,arguments)}},80991:function(T,c,n){n.d(c,{P:function(){return t},V:function(){return E}});var h=n(3292);function t(){var s,m=(s=(0,h.rS)())!==null&&s!==void 0?s:"";return m!==""?{Authorization:m}:{}}function E(){return{"Content-Type":"multipart/form-data"}}}}]);
