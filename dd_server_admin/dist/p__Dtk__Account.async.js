"use strict";(self.webpackChunk=self.webpackChunk||[]).push([[4796],{93559:function(ne,b,r){var w=r(48305),M=r.n(w),z=r(20036),U=r(31839),N=r(93236),n=r(62086),Y=function(V){var j=V.text,y=(0,N.useState)(!1),I=M()(y,2),Z=I[0],O=I[1],T=function(i){if(i&&i.length){var o=i.length;if(o===1)return"*";if(o===2)return i[0]+"*";if(o===3)return i[0]+"*"+i[2];if(o>3&&o<6){for(var F=i.substring(2,o-1),x=[],E=0;E<F.length;E++)x.push("*");return i[0]+i[1]+x.join("")+i[o-1]}else if(o>=6&&o<8){for(var H=i.substring(2,o-2),P=[],W=0;W<H.length;W++)P.push("*");return i[0]+i[1]+P.join("")+i[o-2]+i[o-1]}else{for(var J=i.substring(3,o-3),K=[],t=0;t<J.length;t++)K.push("*");return i[0]+i[1]+i[2]+K.join("")+i[o-3]+i[o-2]+i[o-1]}}};return j===void 0||j.length===0?(0,n.jsx)("span",{}):(0,n.jsxs)("span",{children:[Z?j:T(j),Z?(0,n.jsx)(z.Z,{className:"ml-2",onClick:function(){return O(!1)},style:{marginLeft:2}}):(0,n.jsx)(U.Z,{className:"ml-2",onClick:function(){return O(!0)},style:{marginLeft:2}})]})};b.Z=Y},57999:function(ne,b,r){r.r(b),r.d(b,{default:function(){return De}});var w=r(26068),M=r.n(w),z=r(15558),U=r.n(z),N=r(90228),n=r.n(N),Y=r(87999),h=r.n(Y),V=r(48305),j=r.n(V),y=r(74271);function I(l){return Z.apply(this,arguments)}function Z(){return Z=h()(n()().mark(function l(s){return n()().wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return e.abrupt("return",(0,y.request)("/api/v1/dtk/detail/add",{method:"POST",data:s}));case 1:case"end":return e.stop()}},l)})),Z.apply(this,arguments)}function O(){return T.apply(this,arguments)}function T(){return T=h()(n()().mark(function l(){return n()().wrap(function(a){for(;;)switch(a.prev=a.next){case 0:return a.abrupt("return",(0,y.request)("/api/v1/dtk/detail/set-default",{method:"GET"}));case 1:case"end":return a.stop()}},l)})),T.apply(this,arguments)}function $(l){return i.apply(this,arguments)}function i(){return i=h()(n()().mark(function l(s){return n()().wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return e.abrupt("return",(0,y.request)("/api/v1/dtk/detail/update-default",{method:"POST",data:s}));case 1:case"end":return e.stop()}},l)})),i.apply(this,arguments)}function o(l){return F.apply(this,arguments)}function F(){return F=h()(n()().mark(function l(s){return n()().wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return e.abrupt("return",(0,y.request)("/api/v1/dtk/detail/all",{method:"GET",params:s}));case 1:case"end":return e.stop()}},l)})),F.apply(this,arguments)}function x(l){return E.apply(this,arguments)}function E(){return E=h()(n()().mark(function l(s){return n()().wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return e.abrupt("return",(0,y.request)("/api/v1/dtk/detail/verify",{method:"POST",data:s}));case 1:case"end":return e.stop()}},l)})),E.apply(this,arguments)}function H(l){return P.apply(this,arguments)}function P(){return P=h()(n()().mark(function l(s){return n()().wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return e.abrupt("return",(0,y.request)("/api/v1/dtk/detail/delete",{method:"DELETE",params:{id:s}}));case 1:case"end":return e.stop()}},l)})),P.apply(this,arguments)}var W=r(78037),J=r(85407),K=r(93236),t=r(62086),se=function(){var s=(0,y.useRequest)(function(){return O()}),a=s.loading,e=s.data,m=s.error;return a?(0,t.jsx)(W.Z,{}):m?(0,t.jsx)("span",{children:m.message}):e?(0,t.jsxs)(J.Z,{children:["\u5DF2\u8BBE\u7F6E\u9ED8\u8BA4\u5927\u6DD8\u5BA2\u8D26\u53F7: ",e.title]}):(0,t.jsx)("span",{children:"\u672A\u8BBE\u7F6E\u5927\u6DD8\u5BA2\u8D26\u53F7"})},ue=se,ee=r(93559),re=r(89143),A=r(41594),Q=r(36607),D=r(7432),ie=r(5874),B=r(20617);function le(l){return X.apply(this,arguments)}function X(){return X=h()(n()().mark(function l(s){return n()().wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return e.abrupt("return",(0,y.request)("/api/zhe/channel-generation",{method:"POST",data:s}));case 1:case"end":return e.stop()}},l)})),X.apply(this,arguments)}var ce=function(){var s=(0,y.useModel)("@@initialState"),a=function(d){var u,p="https://mos.m.taobao.com/inviter/register?inviterCode=".concat(d,"&src=pub&app=common&rtag=").concat((u=s.initialState)===null||u===void 0||(u=u.user)===null||u===void 0?void 0:u.id);return(0,t.jsx)(Q.Z.Text,{copyable:{text:p},type:"success",children:p})},e=function(){var m=h()(n()().mark(function d(u){var p,f;return n()().wrap(function(g){for(;;)switch(g.prev=g.next){case 0:return p=D.ZP.loading("\u751F\u6210\u4E2D..."),g.next=3,le(u);case 3:return f=g.sent,p(),f.success&&ie.Z.success({content:a(f.data),width:600}),g.abrupt("return",f.success);case 7:case"end":return g.stop()}},d)}));return function(u){return m.apply(this,arguments)}}();return(0,t.jsxs)(re.Y,{trigger:(0,t.jsx)(B.ZP,{children:"\u6E20\u9053\u9080\u8BF7\u7801\u751F\u6210"}),onFinish:e,children:[(0,t.jsx)(A.Z,{label:"\u6E20\u9053\u63A8\u5E7F\u7684\u7269\u6599\u7C7B\u578B\uFF0C\u793A\u4F8B\u503C\uFF1Acommon",name:"relationApp"}),(0,t.jsx)(A.Z,{label:"\u9080\u8BF7\u7801\u7C7B\u578B\uFF0C1 - \u6E20\u9053\u9080\u8BF7\uFF0C2 - \u6E20\u9053\u88C2\u53D8\uFF0C3 -\u4F1A\u5458\u9080\u8BF7",name:"codeType"})]})},de=r(1268),oe=r(4138),pe=r(17783),fe=r(33910),q=r(1317),he=r(7034),me=r(52065),ve=r(29925),ye=r(76569),ge=r(61665),te=function(s){var a=s.updateModel,e=s.onSuccess,m=(0,K.useRef)(),d=q.Z.useForm(),u=j()(d,1),p=u[0],f=(0,K.useState)(!1),v=j()(f,2),g=v[0],R=v[1],S=q.Z.useWatch("appKey",p),L=q.Z.useWatch("appSecret",p);return(0,t.jsxs)(re.Y,{title:a?"\u4FEE\u6539\u4FE1\u606F":"\u6DFB\u52A0\u5927\u6DD8\u5BA2\u8D26\u53F7",trigger:(0,t.jsx)(B.ZP,{type:a?"default":"primary",size:a?"small":"middle",children:a?"\u4FEE\u6539":"\u6DFB\u52A0\u5927\u6DD8\u5BA2\u8D26\u53F7"}),onFinish:function(){var ae=h()(n()().mark(function _(k){var G,C;return n()().wrap(function(c){for(;;)switch(c.prev=c.next){case 0:return a&&(k.id=a.id),D.ZP.loading("\u6B63\u5728\u9A8C\u8BC1\u8D26\u53F7\u4FE1\u606F"),c.next=4,x({appKey:k.appKey,appSecret:k.appSecret});case 4:if(G=c.sent,G.success){c.next=7;break}return c.abrupt("return",!1);case 7:return D.ZP.loading(a?"\u6B63\u5728\u4FEE\u6539\u8D26\u53F7\u4FE1\u606F":"\u6B63\u5728\u6DFB\u52A0\u8D26\u53F7"),c.next=10,I(k);case 10:if(C=c.sent,!C.success){c.next=14;break}return e&&e(),c.abrupt("return",!0);case 14:return c.abrupt("return",!1);case 15:case"end":return c.stop()}},_)}));return function(_){return ae.apply(this,arguments)}}(),form:p,formRef:m,initialValues:a,submitter:{render:function(_,k){return[(0,t.jsx)(B.ZP,{disabled:!S||!L||S===""||L==="",loading:g,onClick:h()(n()().mark(function G(){var C;return n()().wrap(function(c){for(;;)switch(c.prev=c.next){case 0:if(!(S&&L)){c.next=14;break}return c.prev=1,D.ZP.loading("\u6B63\u5728\u9A8C\u8BC1"),R(!0),c.next=6,x({appKey:S,appSecret:L});case 6:C=c.sent,R(!1),C.data&&D.ZP.success(C.message),c.next=14;break;case 11:c.prev=11,c.t0=c.catch(1),R(!1);case 14:case"end":return c.stop()}},G,null,[[1,11]])})),children:"\u9A8C\u8BC1\u8D26\u53F7\u662F\u5426\u6709\u6548"},"verify")].concat(U()(k))}},autoFocusFirstInput:!0,modalProps:{destroyOnClose:!0},children:[(0,t.jsx)(A.Z,{name:"appKey",label:"appKey",rules:[{required:!0,message:"\u8BF7\u8F93\u5165appKey"},{len:13,message:"\u8BF7\u8F93\u5165\u6B63\u786E\u7684appKey"}]}),(0,t.jsx)(A.Z,{name:"appSecret",label:"appSecret",rules:[{required:!0,message:"\u8BF7\u8F93\u5165appSecret"}]}),(0,t.jsx)(A.Z,{name:"title",label:"\u6807\u9898"}),(0,t.jsx)(de.Z,{name:"intro",label:"\u4ECB\u7ECD"}),(0,t.jsx)(A.Z,{name:"logo",label:"\u56FE\u6807"}),(0,t.jsx)(A.Z,{name:"relationId",label:"\u6E20\u9053\u5173\u7CFBID"}),(0,t.jsxs)(oe.Z,{name:"selectDefault",children:["\u8BBE\u7F6E\u4E3A\u9ED8\u8BA4\u5E94\u7528 ",(0,t.jsx)(ue,{})]})]})};function De(){var l=function(){var s=h()(n()().mark(function a(e){var m;return n()().wrap(function(u){for(;;)switch(u.prev=u.next){case 0:return D.ZP.loading("\u6B63\u5728\u9A8C\u8BC1"),u.next=3,x({appKey:e.appKey,appSecret:e.appSecret});case 3:m=u.sent,m.success&&D.ZP.success(m.message);case 5:case"end":return u.stop()}},a)}));return function(e){return s.apply(this,arguments)}}();return(0,t.jsx)(pe._z,{title:"\u5927\u6DD8\u5BA2\u8D26\u53F7\u7BA1\u7406",extra:[(0,t.jsx)(te,{},"add"),(0,t.jsx)(ce,{},"channel-tool")],children:(0,t.jsx)(he.Z,{children:(0,t.jsx)(fe.Z,{rowKey:"id",columns:[{key:"id",dataIndex:"id",title:"Id"},{key:"appKey",dataIndex:"appKey",title:"appKey",render:function(a,e){return(0,t.jsx)(Q.Z.Paragraph,{copyable:{text:e.appKey},children:(0,t.jsx)(ee.Z,{text:e.appKey})})}},{key:"appSecret",dataIndex:"appSecret",title:"appSecret",search:!1,render:function(a,e){return(0,t.jsx)(Q.Z.Paragraph,{copyable:{text:e.appSecret},children:(0,t.jsx)(ee.Z,{text:e.appSecret})})}},{key:"title",dataIndex:"title",title:"\u540D\u79F0"},{key:"intro",dataIndex:"intro",title:"\u4ECB\u7ECD",search:!1},{key:"selectDefault",dataIndex:"selectDefault",title:"\u662F\u5426\u542F\u7528",search:!1,render:function(a,e,m,d){return(0,t.jsx)(me.Z,{checked:e.selectDefault,onChange:function(){var u=h()(n()().mark(function p(f){var v,g;return n()().wrap(function(S){for(;;)switch(S.prev=S.next){case 0:return v=f.target.checked,g=D.ZP.loading("\u6B63\u5728\u8BBE\u7F6E"),S.next=4,$({action:v,id:e.id});case 4:g(),d==null||d.reload();case 6:case"end":return S.stop()}},p)}));return function(p){return u.apply(this,arguments)}}(),children:"\u9ED8\u8BA4\u5E94\u7528"})}},{key:"logo",dataIndex:"logo",title:"\u56FE\u6807",search:!1,render:function(a,e){return(0,t.jsx)(ve.Z,{src:e.logo,width:32})}},{title:"\u64CD\u4F5C",search:!1,render:function(a,e,m,d){return(0,t.jsxs)(ye.Z,{children:[(0,t.jsx)(te,{updateModel:e,onSuccess:function(){return d==null?void 0:d.reload()}}),(0,t.jsx)(B.ZP,{onClick:function(){return l(e)},size:"small",children:"\u9A8C\u8BC1"}),(0,t.jsx)(ge.Z,{title:"\u786E\u8BA4\u5220\u9664\u5417?",onConfirm:h()(n()().mark(function u(){var p;return n()().wrap(function(v){for(;;)switch(v.prev=v.next){case 0:return D.ZP.loading("\u6B63\u5728\u5220\u9664"),v.next=3,H(e.id);case 3:p=v.sent,p.success&&(D.ZP.success(p.message),d==null||d.reload());case 5:case"end":return v.stop()}},u)})),children:(0,t.jsx)(B.ZP,{size:"small",children:"\u5220\u9664"})})]})}}],request:function(){var s=h()(n()().mark(function a(e,m,d){var u;return n()().wrap(function(f){for(;;)switch(f.prev=f.next){case 0:return f.next=2,o(M()(M()(M()({},e),d),{},{remove:1}));case 2:return u=f.sent,f.abrupt("return",{success:u.success,data:u.data.content,total:u.data.totalElements});case 4:case"end":return f.stop()}},a)}));return function(a,e,m){return s.apply(this,arguments)}}()})})})}}}]);