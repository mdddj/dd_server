"use strict";(self.webpackChunk=self.webpackChunk||[]).push([[9201],{25874:function(G,C,e){e.d(C,{Z:function(){return V}});var O=e(25465),n=e(83062),h=e(67294),u=e(97857),o=e.n(u),c=e(15009),p=e.n(c),T=e(99289),y=e.n(T),A=e(5574),P=e.n(A),i=e(13769),E=e.n(i),s=e(20745),t=e(85893),I=function(m){return _jsx(Modal,_objectSpread({},m))};function v(K,m){var j=document.createElement("div");j.id="select-file";var M=(0,s.createRoot)(j),z=!0,$=h.cloneElement(K,o()(o()({open:z,getContainer:!1,onCancel:function(){var N=h.cloneElement($,{open:!1});M.render(N),console.log("\u5173\u95ED"),setTimeout(function(){M.unmount(),j.remove()},300)}},m),{},{onFileSelect:function(N){m==null||m.onFileSelect(N);var J=h.cloneElement($,{open:!1});M.render(J),setTimeout(function(){M.unmount(),j.remove()},300)}}));M.render($),document.body.appendChild(j)}var S=e(9096),x=e(88484),b=e(55375),a=e(10981),_=e(44034),l=e(85576),r=e(2058),R=e(14726),f=e(89048),g=["onFileSelect"],D=function(m){var j=m.file,M=m.onClick;return(0,t.jsx)(_.J,{onClick:M,isZoomed:!0,alt:j.fileName,src:j.url,className:"object-contain w-full aspect-square bg-secondary-50"})},d=function(m){var j=m.onFileSelect,M=E()(m,g),z=(0,h.useState)([]),$=P()(z,2),H=$[0],N=$[1],J=function(){var B=y()(p()().mark(function Z(){var F;return p()().wrap(function(U){for(;;)switch(U.prev=U.next){case 0:return U.next=2,(0,S.oC)({current:1,pageSize:20,remove:1});case 2:F=U.sent,N(F.data.list);case 4:case"end":return U.stop()}},Z)}));return function(){return B.apply(this,arguments)}}();return(0,f.Z)(y()(p()().mark(function B(){return p()().wrap(function(F){for(;;)switch(F.prev=F.next){case 0:return F.next=2,J();case 2:case"end":return F.stop()}},B)}))),(0,t.jsxs)(l.Z,o()(o()({title:"\u9009\u62E9\u6587\u4EF6"},M),{},{children:[(0,t.jsx)("div",{style:{marginBottom:12},children:(0,t.jsx)(r.Z,{name:"file",action:b.ty+"/api/storage/upload",headers:(0,a.P)(),onChange:function(){var B=y()(p()().mark(function Z(F){return p()().wrap(function(U){for(;;)switch(U.prev=U.next){case 0:if(F.file.status!=="done"){U.next=3;break}return U.next=3,J();case 3:case"end":return U.stop()}},Z)}));return function(Z){return B.apply(this,arguments)}}(),children:(0,t.jsx)(R.ZP,{type:"primary",icon:(0,t.jsx)(x.Z,{}),children:"\u65B0."})})}),(0,t.jsx)("div",{className:"grid grid-cols-4 gap-2",children:H.map(function(B){return(0,t.jsx)(D,{file:B,onClick:function(){return j(B)}},B.id)})})]}))};function L(K){v((0,t.jsx)(d,o()({},K)),K)}var W=function(m){var j,M;return(0,t.jsxs)("div",{style:{position:"relative"},children:[(0,t.jsx)("div",{style:{flex:1,marginRight:2},children:m.children}),(0,t.jsx)(n.Z,{title:"\u4ECE\u6587\u4EF6\u4E2D\u9009\u62E9\u56FE\u50CF",children:(0,t.jsx)(O.Z,{style:{position:"absolute",top:(j=m.top)!==null&&j!==void 0?j:6,right:(M=m.right)!==null&&M!==void 0?M:12},onClick:function(){L(m)}})})]})},V=W},51625:function(G,C,e){var O=e(15009),n=e.n(O),h=e(97857),u=e.n(h),o=e(19632),c=e.n(o),p=e(5574),T=e.n(p),y=e(99289),A=e.n(y),P=e(64317),i=e(50942),E=e(25278),s=e(67294),t=e(85893);function I(){return v.apply(this,arguments)}function v(){return v=A()(n()().mark(function x(){return n()().wrap(function(a){for(;;)switch(a.prev=a.next){case 0:return a.abrupt("return",(0,i.request)("/api/rc/types"));case 1:case"end":return a.stop()}},x)})),v.apply(this,arguments)}var S=function(b){var a,_=(0,s.useState)(""),l=T()(_,2),r=l[0],R=l[1],f=(0,i.useRequest)(function(){return I()}),g=f.data,D=f.loading;return(0,t.jsx)(P.Z,u()(u()({},b),{},{options:[].concat(c()((a=g==null?void 0:g.map(function(d){return{label:d.type,value:d.type}}))!==null&&a!==void 0?a:[]),[r!==""&&{label:r,value:r}]),fieldProps:{loading:D,dropdownRender:function(L){return(0,t.jsxs)(t.Fragment,{children:[L,(0,t.jsx)(E.Z,{onChange:function(V){return R(V.target.value)}})]})}}}))};C.Z=S},11421:function(G,C,e){e.r(C),e.d(C,{default:function(){return _}});var O=e(15009),n=e.n(O),h=e(97857),u=e.n(h),o=e(99289),c=e.n(o),p=e(25874),T=e(51625),y=e(88560),A=e(37476),P=e(5966),i=e(14726),E=e(67294),s=e(85893);function t(){var l=(0,E.useRef)();return(0,s.jsxs)(A.Y,{width:300,formRef:l,title:"\u65B0\u589E\u4E00\u4E2A\u5206\u7C7B",modalProps:{destroyOnClose:!0},trigger:(0,s.jsx)(i.ZP,{type:"primary",children:"\u65B0\u589E"}),onFinish:function(){var r=c()(n()().mark(function R(f){var g;return n()().wrap(function(d){for(;;)switch(d.prev=d.next){case 0:return d.next=2,(0,y.Mg)(f);case 2:return g=d.sent,d.abrupt("return",g.success);case 4:case"end":return d.stop()}},R)}));return function(R){return r.apply(this,arguments)}}(),children:[(0,s.jsx)(P.Z,{name:"name",label:"\u540D\u79F0"}),(0,s.jsx)(p.Z,{onFileSelect:function(R){var f;return(f=l.current)===null||f===void 0?void 0:f.setFieldValue("logo",R.url)},children:(0,s.jsx)(P.Z,{name:"logo",label:"\u56FE\u6807"})}),(0,s.jsx)(T.Z,{name:"type",label:"\u7C7B\u578B"})]})}var I=e(90930),v=e(80940),S=e(50942),x=e(4393),b=e(78957),a=e(86738);function _(){var l=(0,E.useRef)();function r(f){return R.apply(this,arguments)}function R(){return R=c()(n()().mark(function f(g){var D;return n()().wrap(function(L){for(;;)switch(L.prev=L.next){case 0:return L.next=2,(0,S.request)("/api/res/delete",{method:"DELETE",params:{id:g.id}});case 2:(D=l.current)===null||D===void 0||D.reload();case 3:case"end":return L.stop()}},f)})),R.apply(this,arguments)}return(0,s.jsx)(I._z,{title:"\u5206\u7C7B\u5217\u8868",extra:[(0,s.jsx)(t,{},"create")],children:(0,s.jsx)(x.Z,{children:(0,s.jsx)(v.Z,{actionRef:l,columns:[{key:"id",dataIndex:"id",title:"id"},{key:"name",dataIndex:"name",title:"name"},{key:"logo",dataIndex:"logo",title:"logo"},{key:"description",dataIndex:"description",title:"description"},{key:"type",dataIndex:"type",title:"type"},{key:"navJsonString",dataIndex:"navJsonString",title:"navJsonString"},{key:"parentNode",dataIndex:"parentNode",title:"parentNode"},{key:"level",dataIndex:"level",title:"level"},{key:"actions",render:function(g,D){return(0,s.jsx)(b.Z,{children:(0,s.jsx)(a.Z,{title:"\u786E\u5B9A\u5220\u9664\u5417?",onConfirm:function(){return r(D)},children:(0,s.jsx)(i.ZP,{size:"small",type:"dashed",children:"\u5220\u9664"})})})}}],request:function(){var f=c()(n()().mark(function g(D){var d;return n()().wrap(function(W){for(;;)switch(W.prev=W.next){case 0:return W.next=2,(0,y.Ip)(u()(u()({},D),{},{remove:1}));case 2:return d=W.sent,W.abrupt("return",{success:d.success,data:d.data.list,total:d.data.page.total});case 4:case"end":return W.stop()}},g)}));return function(g){return f.apply(this,arguments)}}(),search:!1})})})}},9096:function(G,C,e){e.d(C,{QU:function(){return A},Zq:function(){return T},oC:function(){return c}});var O=e(15009),n=e.n(O),h=e(99289),u=e.n(h),o=e(50942);function c(i){return p.apply(this,arguments)}function p(){return p=u()(n()().mark(function i(E){return n()().wrap(function(t){for(;;)switch(t.prev=t.next){case 0:return t.abrupt("return",(0,o.request)("/api/file/list",{params:E}));case 1:case"end":return t.stop()}},i)})),p.apply(this,arguments)}function T(i){return y.apply(this,arguments)}function y(){return y=u()(n()().mark(function i(E){return n()().wrap(function(t){for(;;)switch(t.prev=t.next){case 0:return t.abrupt("return",(0,o.request)("/api/storage/delete",{method:"DELETE",params:{id:E}}));case 1:case"end":return t.stop()}},i)})),y.apply(this,arguments)}function A(i){return P.apply(this,arguments)}function P(){return P=u()(n()().mark(function i(E){var s,t;return n()().wrap(function(v){for(;;)switch(v.prev=v.next){case 0:return s=new FormData,s.append("file",E),v.next=4,(0,o.request)("/api/auth/simple-upload",{method:"POST",data:s});case 4:return t=v.sent,v.abrupt("return",t.data);case 6:case"end":return v.stop()}},i)})),P.apply(this,arguments)}},88560:function(G,C,e){e.d(C,{Ip:function(){return p},Lq:function(){return v},Mg:function(){return P},TQ:function(){return E},TZ:function(){return x},h0:function(){return t},xi:function(){return y}});var O=e(15009),n=e.n(O),h=e(99289),u=e.n(h),o=e(10981),c=e(50942);function p(a){return T.apply(this,arguments)}function T(){return T=u()(n()().mark(function a(_){return n()().wrap(function(r){for(;;)switch(r.prev=r.next){case 0:return r.abrupt("return",(0,c.request)("/api/res/list",{method:"GET",params:_}));case 1:case"end":return r.stop()}},a)})),T.apply(this,arguments)}function y(){return A.apply(this,arguments)}function A(){return A=u()(n()().mark(function a(){return n()().wrap(function(l){for(;;)switch(l.prev=l.next){case 0:return l.abrupt("return",(0,c.request)("/api/res/all",{method:"GET"}));case 1:case"end":return l.stop()}},a)})),A.apply(this,arguments)}function P(a){return i.apply(this,arguments)}function i(){return i=u()(n()().mark(function a(_){return n()().wrap(function(r){for(;;)switch(r.prev=r.next){case 0:return r.abrupt("return",(0,c.request)("/api/auth/res-cate-save",{method:"POST",data:_}));case 1:case"end":return r.stop()}},a)})),i.apply(this,arguments)}function E(a){return s.apply(this,arguments)}function s(){return s=u()(n()().mark(function a(_){return n()().wrap(function(r){for(;;)switch(r.prev=r.next){case 0:return r.abrupt("return",(0,c.request)("/api/resource/list",{method:"GET",params:_}));case 1:case"end":return r.stop()}},a)})),s.apply(this,arguments)}function t(a){return I.apply(this,arguments)}function I(){return I=u()(n()().mark(function a(_){return n()().wrap(function(r){for(;;)switch(r.prev=r.next){case 0:return r.abrupt("return",(0,c.request)("/api/resource/add-post",{method:"POST",headers:(0,o.V)(),data:_}));case 1:case"end":return r.stop()}},a)})),I.apply(this,arguments)}function v(a){return S.apply(this,arguments)}function S(){return S=u()(n()().mark(function a(_){return n()().wrap(function(r){for(;;)switch(r.prev=r.next){case 0:return r.abrupt("return",(0,c.request)("/api/resource/delete",{method:"DELETE",params:{id:_}}));case 1:case"end":return r.stop()}},a)})),S.apply(this,arguments)}function x(a){return b.apply(this,arguments)}function b(){return b=u()(n()().mark(function a(_){return n()().wrap(function(r){for(;;)switch(r.prev=r.next){case 0:return r.abrupt("return",(0,c.request)("/api/app/resource/find-by-id",{params:{id:_}}));case 1:case"end":return r.stop()}},a)})),b.apply(this,arguments)}},10981:function(G,C,e){e.d(C,{P:function(){return n},V:function(){return h}});var O=e(92273);function n(){var u,o=(u=(0,O.rS)())!==null&&u!==void 0?u:"";return o!==""?{Authorization:o}:{}}function h(){return{"Content-Type":"multipart/form-data"}}}}]);
