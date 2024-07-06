"use strict";(self.webpackChunk=self.webpackChunk||[]).push([[854],{50216:function(H,E,e){e.d(E,{Z:function(){return h}});var x=e(15009),l=e.n(x),j=e(99289),d=e.n(j),v=e(12444),D=e.n(v),m=e(72004),G=e.n(m),g=e(31996),$=e.n(g),F=e(26037),p=e.n(F),y=e(90580),C=e(67294),n=e(85893),K=function(z){$()(P,z);var N=p()(P);function P(){return D()(this,P),N.apply(this,arguments)}return G()(P,[{key:"render",value:function(){var Y=this;return(0,n.jsx)(y.Z,{rowKey:this.props.rowKey,request:function(){var V=d()(l()().mark(function L(B){var M,a,Z;return l()().wrap(function(i){for(;;)switch(i.prev=i.next){case 0:return i.next=2,Y.props.api({page:((M=B.current)!==null&&M!==void 0?M:1)-1,pageSize:(a=B.pageSize)!==null&&a!==void 0?a:20});case 2:return Z=i.sent,i.abrupt("return",{success:Z.success,total:Z.data.page.total,data:Z.data.list});case 4:case"end":return i.stop()}},L)}));return function(L){return V.apply(this,arguments)}}(),columns:this.props.columns})}}]),P}(C.Component),h=K},25874:function(H,E,e){e.d(E,{Z:function(){return f}});var x=e(25465),l=e(83062),j=e(67294),d=e(97857),v=e.n(d),D=e(15009),m=e.n(D),G=e(99289),g=e.n(G),$=e(5574),F=e.n($),p=e(13769),y=e.n(p),C=e(20745),n=e(85893),K=function(t){return _jsx(Modal,_objectSpread({},t))};function h(r,t){var s=document.createElement("div");s.id="select-file";var u=(0,C.createRoot)(s),R=!0,o=j.cloneElement(r,v()(v()({open:R,getContainer:!1,onCancel:function(){var W=j.cloneElement(o,{open:!1});u.render(W),console.log("\u5173\u95ED"),setTimeout(function(){u.unmount(),s.remove()},300)}},t),{},{onFileSelect:function(W){t==null||t.onFileSelect(W);var T=j.cloneElement(o,{open:!1});u.render(T),setTimeout(function(){u.unmount(),s.remove()},300)}}));u.render(o),document.body.appendChild(s)}var z=e(9096),N=e(88484),P=e(55375),J=e(10981),Y=e(44034),V=e(85576),L=e(58490),B=e(14726),M=e(89048),a=["onFileSelect"],Z=function(t){var s=t.file,u=t.onClick;return(0,n.jsx)(Y.J,{onClick:u,isZoomed:!0,alt:s.fileName,src:s.url,className:"object-contain w-full aspect-square bg-secondary-50"})},Q=function(t){var s=t.onFileSelect,u=y()(t,a),R=(0,j.useState)([]),o=F()(R,2),U=o[0],W=o[1],T=function(){var A=g()(m()().mark(function I(){var O;return m()().wrap(function(S){for(;;)switch(S.prev=S.next){case 0:return S.next=2,(0,z.oC)({current:1,pageSize:20,remove:1});case 2:O=S.sent,W(O.data.list);case 4:case"end":return S.stop()}},I)}));return function(){return A.apply(this,arguments)}}();return(0,M.Z)(g()(m()().mark(function A(){return m()().wrap(function(O){for(;;)switch(O.prev=O.next){case 0:return O.next=2,T();case 2:case"end":return O.stop()}},A)}))),(0,n.jsxs)(V.Z,v()(v()({title:"\u9009\u62E9\u6587\u4EF6"},u),{},{children:[(0,n.jsx)("div",{style:{marginBottom:12},children:(0,n.jsx)(L.Z,{name:"file",action:P.ty+"/api/storage/upload",headers:(0,J.P)(),onChange:function(){var A=g()(m()().mark(function I(O){return m()().wrap(function(S){for(;;)switch(S.prev=S.next){case 0:if(O.file.status!=="done"){S.next=3;break}return S.next=3,T();case 3:case"end":return S.stop()}},I)}));return function(I){return A.apply(this,arguments)}}(),children:(0,n.jsx)(B.ZP,{type:"primary",icon:(0,n.jsx)(N.Z,{}),children:"\u65B0."})})}),(0,n.jsx)("div",{className:"grid grid-cols-4 gap-2",children:U.map(function(A){return(0,n.jsx)(Z,{file:A,onClick:function(){return s(A)}},A.id)})})]}))};function i(r){h((0,n.jsx)(Q,v()({},r)),r)}var c=function(t){var s,u;return(0,n.jsxs)("div",{style:{position:"relative"},children:[(0,n.jsx)("div",{style:{flex:1,marginRight:2},children:t.children}),(0,n.jsx)(l.Z,{title:"\u4ECE\u6587\u4EF6\u4E2D\u9009\u62E9\u56FE\u50CF",children:(0,n.jsx)(x.Z,{style:{position:"absolute",top:(s=t.top)!==null&&s!==void 0?s:6,right:(u=t.right)!==null&&u!==void 0?u:12},onClick:function(){i(t)}})})]})},f=c},10791:function(H,E,e){e.r(E),e.d(E,{default:function(){return Q}});var x=e(15009),l=e.n(x),j=e(99289),d=e.n(j),v=e(50216),D=e(25874),m=e(62309);function G(){return g.apply(this,arguments)}function g(){return g=_asyncToGenerator(_regeneratorRuntime().mark(function i(){return _regeneratorRuntime().wrap(function(f){for(;;)switch(f.prev=f.next){case 0:return f.abrupt("return",request("/api/role/list"));case 1:case"end":return f.stop()}},i)})),g.apply(this,arguments)}function $(i){return F.apply(this,arguments)}function F(){return F=d()(l()().mark(function i(c){return l()().wrap(function(r){for(;;)switch(r.prev=r.next){case 0:return r.abrupt("return",(0,m.WY)("/api/role/list-page",{params:c}));case 1:case"end":return r.stop()}},i)})),F.apply(this,arguments)}function p(i){return y.apply(this,arguments)}function y(){return y=d()(l()().mark(function i(c){return l()().wrap(function(r){for(;;)switch(r.prev=r.next){case 0:return r.abrupt("return",(0,m.WY)("/api/role/save",{method:"POST",data:c}));case 1:case"end":return r.stop()}},i)})),y.apply(this,arguments)}function C(i){return n.apply(this,arguments)}function n(){return n=d()(l()().mark(function i(c){return l()().wrap(function(r){for(;;)switch(r.prev=r.next){case 0:return r.abrupt("return",(0,m.WY)("/api/role/delete",{method:"DELETE",params:{id:c}}));case 1:case"end":return r.stop()}},i)})),n.apply(this,arguments)}var K=e(37476),h=e(5966),z=e(90672),N=e(86615),P=e(90930),J=e(14726),Y=e(4393),V=e(78957),L=e(86738),B=e(45360),M=e(67294),a=e(85893),Z=function(c){var f,r,t=(0,M.useRef)(),s=function(){var u=d()(l()().mark(function R(o){var U;return l()().wrap(function(T){for(;;)switch(T.prev=T.next){case 0:return o.id=(U=c.role)===null||U===void 0?void 0:U.id,T.next=3,p(o);case 3:return T.abrupt("return",!0);case 4:case"end":return T.stop()}},R)}));return function(o){return u.apply(this,arguments)}}();return(0,a.jsx)(a.Fragment,{children:(0,a.jsxs)(K.Y,{initialValues:c.role,title:(f=c.title)!==null&&f!==void 0?f:"\u65B0\u5EFA\u6743\u9650",trigger:(r=c.trigger)!==null&&r!==void 0?r:(0,a.jsx)(J.ZP,{type:"primary",children:"\u65B0\u5EFA\u6743\u9650"}),width:500,onFinish:s,onFinishFailed:c.onFinish,layout:"vertical",formRef:t,children:[(0,a.jsx)(h.Z,{name:"name",label:"\u540D\u79F0",required:!0}),(0,a.jsx)(z.Z,{name:"note",label:"\u5907\u6CE8"}),(0,a.jsx)(D.Z,{onFileSelect:function(R){var o;(o=t.current)===null||o===void 0||o.setFieldValue("icon",R.url)},children:(0,a.jsx)(h.Z,{name:"icon",label:"\u56FE\u6807",tooltip:"\u8F93\u5165URL\u76F4\u94FE"})}),(0,a.jsx)(N.Z.Group,{name:"status",label:"\u72B6\u6001",options:[{label:"\u542F\u7528",value:1},{label:"\u7981\u7528",value:0}],required:!0}),(0,a.jsx)(h.Z,{name:"sort",label:"\u6392\u5E8F"})]})})};function Q(){return(0,a.jsx)(P._z,{children:(0,a.jsx)(Y.Z,{title:(0,a.jsx)(Z,{}),children:(0,a.jsx)(v.Z,{api:function(c){return $(c)},columns:[{key:"id",dataIndex:"id",title:"ID"},{key:"name",dataIndex:"name",title:"\u540D\u79F0"},{key:"note",dataIndex:"note",title:"\u5907\u6CE8"},{key:"sort",dataIndex:"sort",title:"\u6392\u5E8F"},{key:"action",render:function(c,f,r,t){return(0,a.jsxs)(V.Z,{children:[(0,a.jsx)(Z,{title:"\u4FEE\u6539\u6743\u9650",trigger:(0,a.jsx)("a",{children:"\u4FEE\u6539"}),role:f,onFinish:function(){return t==null?void 0:t.reload()}}),(0,a.jsx)(L.Z,{title:"\u786E\u5B9A\u5220\u9664\u5417? ",onConfirm:d()(l()().mark(function s(){var u;return l()().wrap(function(o){for(;;)switch(o.prev=o.next){case 0:return u=B.ZP.loading("\u6B63\u5728\u5220\u9664..."),o.next=3,C(f.id);case 3:u(),t==null||t.reload();case 5:case"end":return o.stop()}},s)})),children:(0,a.jsx)("a",{children:"\u5220\u9664"})})]})}}],rowKey:"id"})})})}},9096:function(H,E,e){e.d(E,{QU:function(){return $},Zq:function(){return G},oC:function(){return D}});var x=e(15009),l=e.n(x),j=e(99289),d=e.n(j),v=e(50942);function D(p){return m.apply(this,arguments)}function m(){return m=d()(l()().mark(function p(y){return l()().wrap(function(n){for(;;)switch(n.prev=n.next){case 0:return n.abrupt("return",(0,v.request)("/api/file/list",{params:y}));case 1:case"end":return n.stop()}},p)})),m.apply(this,arguments)}function G(p){return g.apply(this,arguments)}function g(){return g=d()(l()().mark(function p(y){return l()().wrap(function(n){for(;;)switch(n.prev=n.next){case 0:return n.abrupt("return",(0,v.request)("/api/storage/delete",{method:"DELETE",params:{id:y}}));case 1:case"end":return n.stop()}},p)})),g.apply(this,arguments)}function $(p){return F.apply(this,arguments)}function F(){return F=d()(l()().mark(function p(y){var C,n;return l()().wrap(function(h){for(;;)switch(h.prev=h.next){case 0:return C=new FormData,C.append("file",y),h.next=4,(0,v.request)("/api/auth/simple-upload",{method:"POST",data:C});case 4:return n=h.sent,h.abrupt("return",n.data);case 6:case"end":return h.stop()}},p)})),F.apply(this,arguments)}},10981:function(H,E,e){e.d(E,{P:function(){return l},V:function(){return j}});var x=e(92273);function l(){var d,v=(d=(0,x.rS)())!==null&&d!==void 0?d:"";return v!==""?{Authorization:v}:{}}function j(){return{"Content-Type":"multipart/form-data"}}}}]);
