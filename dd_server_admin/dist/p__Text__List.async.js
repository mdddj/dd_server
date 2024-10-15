"use strict";(self.webpackChunk=self.webpackChunk||[]).push([[2474],{63434:function(w,U,e){var p=e(1413),o=e(91),j=e(22270),u=e(84567),g=e(67294),T=e(90789),I=e(61147),E=e(85893),h=["options","fieldProps","proFieldProps","valueEnum"],i=g.forwardRef(function(t,_){var M=t.options,a=t.fieldProps,B=t.proFieldProps,l=t.valueEnum,r=(0,o.Z)(t,h);return(0,E.jsx)(I.Z,(0,p.Z)({ref:_,valueType:"checkbox",valueEnum:(0,j.h)(l,void 0),fieldProps:(0,p.Z)({options:M},a),lightProps:(0,p.Z)({labelFormatter:function(){return(0,E.jsx)(I.Z,(0,p.Z)({ref:_,valueType:"checkbox",mode:"read",valueEnum:(0,j.h)(l,void 0),filedConfig:{customLightMode:!0},fieldProps:(0,p.Z)({options:M},a),proFieldProps:B},r))}},r.lightProps),proFieldProps:B},r))}),R=g.forwardRef(function(t,_){var M=t.fieldProps,a=t.children;return(0,E.jsx)(u.Z,(0,p.Z)((0,p.Z)({ref:_},M),{},{children:a}))}),v=(0,T.G)(R,{valuePropName:"checked"}),s=v;s.Group=i,U.Z=s},5966:function(w,U,e){var p=e(97685),o=e(1413),j=e(91),u=e(21770),g=e(9383),T=e(55241),I=e(97435),E=e(67294),h=e(61147),i=e(85893),R=["fieldProps","proFieldProps"],v=["fieldProps","proFieldProps"],s="text",t=function(l){var r=l.fieldProps,n=l.proFieldProps,x=(0,j.Z)(l,R);return(0,i.jsx)(h.Z,(0,o.Z)({valueType:s,fieldProps:r,filedConfig:{valueType:s},proFieldProps:n},x))},_=function(l){var r=(0,u.Z)(l.open||!1,{value:l.open,onChange:l.onOpenChange}),n=(0,p.Z)(r,2),x=n[0],A=n[1];return(0,i.jsx)(g.Z.Item,{shouldUpdate:!0,noStyle:!0,children:function(W){var y,K=W.getFieldValue(l.name||[]);return(0,i.jsx)(T.Z,(0,o.Z)((0,o.Z)({getPopupContainer:function(m){return m&&m.parentNode?m.parentNode:m},onOpenChange:function(m){return A(m)},content:(0,i.jsxs)("div",{style:{padding:"4px 0"},children:[(y=l.statusRender)===null||y===void 0?void 0:y.call(l,K),l.strengthText?(0,i.jsx)("div",{style:{marginTop:10},children:(0,i.jsx)("span",{children:l.strengthText})}):null]}),overlayStyle:{width:240},placement:"rightTop"},l.popoverProps),{},{open:x,children:l.children}))}})},M=function(l){var r=l.fieldProps,n=l.proFieldProps,x=(0,j.Z)(l,v),A=(0,E.useState)(!1),$=(0,p.Z)(A,2),W=$[0],y=$[1];return r!=null&&r.statusRender&&x.name?(0,i.jsx)(_,{name:x.name,statusRender:r==null?void 0:r.statusRender,popoverProps:r==null?void 0:r.popoverProps,strengthText:r==null?void 0:r.strengthText,open:W,onOpenChange:y,children:(0,i.jsx)("div",{children:(0,i.jsx)(h.Z,(0,o.Z)({valueType:"password",fieldProps:(0,o.Z)((0,o.Z)({},(0,I.Z)(r,["statusRender","popoverProps","strengthText"])),{},{onBlur:function(Z){var m;r==null||(m=r.onBlur)===null||m===void 0||m.call(r,Z),y(!1)},onClick:function(Z){var m;r==null||(m=r.onClick)===null||m===void 0||m.call(r,Z),y(!0)}}),proFieldProps:n,filedConfig:{valueType:s}},x))})}):(0,i.jsx)(h.Z,(0,o.Z)({valueType:"password",fieldProps:r,proFieldProps:n,filedConfig:{valueType:s}},x))},a=t;a.Password=M,a.displayName="ProFormComponent",U.Z=a},9076:function(w,U,e){var p=e(9096),o=e(20637),j=e(77791),u=e(67294),g=e(21121),T=e(62832),I=e(80237),E=e(39400),h=e(85893),i=new j.Z({highlight:function(s,t){var _=i.utils.escapeHtml(s);return t&&o.Z.getLanguage(t)&&(_=o.Z.highlight(t,s,!0).value),'<pre class="hljs"><code>'.concat(_,"</code></pre>")},html:!0}),R=function(s){var t=s.onChange,_=s.value;return(0,h.jsx)(g.ZP,{className:"h-96 markdown-body",value:_,onChange:function(a){var B=a.text;return t(B)},renderHTML:function(a){return i.render(a)},onImageUpload:p.QU})};U.Z=R},73894:function(w,U,e){e.r(U),e.d(U,{default:function(){return Z},deleteItem:function(){return y}});var p=e(5574),o=e.n(p),j=e(15009),u=e.n(j),g=e(97857),T=e.n(g),I=e(99289),E=e.n(I),h=e(9076),i=e(90930),R=e(97073),v=e(37476),s=e(5966),t=e(4499),_=e(63434),M=e(50942),a=e(14726),B=e(78957),l=e(86738),r=e(67294),n=e(85893);function x(P){return A.apply(this,arguments)}function A(){return A=E()(u()().mark(function P(f){return u()().wrap(function(d){for(;;)switch(d.prev=d.next){case 0:return d.abrupt("return",(0,M.request)("/api/text/list",{method:"GET",params:f}));case 1:case"end":return d.stop()}},P)})),A.apply(this,arguments)}function $(P){return W.apply(this,arguments)}function W(){return W=E()(u()().mark(function P(f){return u()().wrap(function(d){for(;;)switch(d.prev=d.next){case 0:return d.abrupt("return",(0,M.request)("/api/text/save",{method:"POST",data:f}));case 1:case"end":return d.stop()}},P)})),W.apply(this,arguments)}function y(P){return K.apply(this,arguments)}function K(){return K=E()(u()().mark(function P(f){return u()().wrap(function(d){for(;;)switch(d.prev=d.next){case 0:return d.abrupt("return",(0,M.request)("/api/text/delete",{method:"DELETE",params:{id:f}}));case 1:case"end":return d.stop()}},P)})),K.apply(this,arguments)}function Z(){var P=r.useRef();return(0,n.jsx)(i._z,{extra:[(0,n.jsx)(m,{trigger:(0,n.jsx)(a.ZP,{children:"\u6DFB\u52A0\u5B57\u5178"}),onSuccess:function(){var c;return P==null||(c=P.current)===null||c===void 0?void 0:c.reload()}})],children:(0,n.jsx)(R.Z,{actionRef:P,request:function(){var f=E()(u()().mark(function c(d,F,D){var L;return u()().wrap(function(O){for(;;)switch(O.prev=O.next){case 0:return O.next=2,x(T()(T()(T()({},d),D),{},{remove:1}));case 2:return L=O.sent,O.abrupt("return",{success:L.success,data:L.data.content,total:L.data.totalElements});case 4:case"end":return O.stop()}},c)}));return function(c,d,F){return f.apply(this,arguments)}}(),columns:[{key:"id",dataIndex:"id",title:"\u4E3B\u952EID"},{key:"name",dataIndex:"name",title:"\u67E5\u8BE2\u5173\u952E\u5B57"},{key:"intro",dataIndex:"intro",title:"\u4ECB\u7ECD"},{key:"context",dataIndex:"context",title:"\u6B63\u6587\u5185\u5BB9"},{key:"isEncryptionText",dataIndex:"isEncryptionText",title:"\u662F\u5426\u52A0\u5BC6"},{key:"viewPassword",dataIndex:"viewPassword",title:"\u67E5\u770B\u5BC6\u7801"},{key:"originPassword",dataIndex:"originPassword",title:"\u539F\u59CB\u67E5\u770B\u5BC6\u7801"},{title:"\u64CD\u4F5C",render:function(c,d,F,D){return(0,n.jsxs)(B.Z,{children:[(0,n.jsx)(m,{trigger:(0,n.jsx)(a.ZP,{size:"small",children:"\u4FEE\u6539"}),initValue:d,onSuccess:function(){return D==null?void 0:D.reload()}}),(0,n.jsx)(l.Z,{title:"\u786E\u5B9A\u5220\u9664\u5417?",onConfirm:E()(u()().mark(function L(){var b,O;return u()().wrap(function(C){for(;;)switch(C.prev=C.next){case 0:if(b=d.id,C.t0=b,!C.t0){C.next=6;break}return C.next=5,y(b);case 5:C.t0=C.sent;case 6:O=C.t0,O&&O.success&&(D==null||D.reload());case 8:case"end":return C.stop()}},L)})),children:(0,n.jsx)(a.ZP,{size:"small",type:"dashed",children:"\u5220\u9664"})})]})}}],rowKey:"id"})})}var m=function(f){var c,d=f.trigger,F=f.onSuccess,D=f.initValue,L=(0,r.useState)((c=D==null?void 0:D.context)!==null&&c!==void 0?c:""),b=o()(L,2),O=b[0],V=b[1];return(0,n.jsxs)(v.Y,{trigger:d,onFinish:function(){var C=E()(u()().mark(function N(z){var G;return u()().wrap(function(S){for(;;)switch(S.prev=S.next){case 0:return S.next=2,$(T()(T()({},z),{},{context:O}));case 2:return G=S.sent,G.success&&(F==null||F()),S.abrupt("return",G.success);case 5:case"end":return S.stop()}},N)}));return function(N){return C.apply(this,arguments)}}(),initialValues:D,children:[(0,n.jsx)(s.Z,{name:"id",label:"\u4E3B\u952EID",disabled:!0}),(0,n.jsx)(s.Z,{name:"name",label:"\u67E5\u8BE2\u5173\u952E\u5B57"}),(0,n.jsx)(s.Z,{name:"intro",label:"\u4ECB\u7ECD"}),(0,n.jsx)(t.Z,{label:"\u6B63\u6587\u5185\u5BB9",children:(0,n.jsx)(h.Z,{onChange:V,value:O})}),(0,n.jsx)(_.Z,{name:"isEncryptionText",children:"\u662F\u5426\u52A0\u5BC6"}),(0,n.jsx)(s.Z,{name:"viewPassword",label:"\u67E5\u770B\u5BC6\u7801"}),(0,n.jsx)(s.Z,{name:"originPassword",label:"\u539F\u59CB\u67E5\u770B\u5BC6\u7801"})]})}},9096:function(w,U,e){e.d(U,{QU:function(){return i},Zq:function(){return E},oC:function(){return T}});var p=e(15009),o=e.n(p),j=e(99289),u=e.n(j),g=e(50942);function T(v){return I.apply(this,arguments)}function I(){return I=u()(o()().mark(function v(s){return o()().wrap(function(_){for(;;)switch(_.prev=_.next){case 0:return _.abrupt("return",(0,g.request)("/api/file/list",{params:s}));case 1:case"end":return _.stop()}},v)})),I.apply(this,arguments)}function E(v){return h.apply(this,arguments)}function h(){return h=u()(o()().mark(function v(s){return o()().wrap(function(_){for(;;)switch(_.prev=_.next){case 0:return _.abrupt("return",(0,g.request)("/api/storage/delete",{method:"DELETE",params:{id:s}}));case 1:case"end":return _.stop()}},v)})),h.apply(this,arguments)}function i(v){return R.apply(this,arguments)}function R(){return R=u()(o()().mark(function v(s){var t,_;return o()().wrap(function(a){for(;;)switch(a.prev=a.next){case 0:return t=new FormData,t.append("file",s),a.next=4,(0,g.request)("/api/auth/simple-upload",{method:"POST",data:t});case 4:return _=a.sent,a.abrupt("return",_.data);case 6:case"end":return a.stop()}},v)})),R.apply(this,arguments)}}}]);
