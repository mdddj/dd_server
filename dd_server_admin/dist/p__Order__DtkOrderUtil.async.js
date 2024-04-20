"use strict";(self.webpackChunk=self.webpackChunk||[]).push([[2977],{83301:function(te,U,r){var o=r(35194),d=r(50446),Z=r(27175),f=r(93236),D=r(31765),B=r(19544),m=r(62086),N=["fieldProps","children","params","proFieldProps","mode","valueEnum","request","showSearch","options"],x=["fieldProps","children","params","proFieldProps","mode","valueEnum","request","options"],p=function(C,$){var z=C.fieldProps,c=C.children,a=C.params,F=C.proFieldProps,M=C.mode,H=C.valueEnum,Y=C.request,V=C.showSearch,y=C.options,G=(0,d.Z)(C,N),X=(0,f.useContext)(D.Z);return(0,m.jsx)(B.Z,(0,o.Z)((0,o.Z)({valueEnum:(0,Z.h)(H),request:Y,params:a,valueType:"select",filedConfig:{customLightMode:!0},fieldProps:(0,o.Z)({options:y,mode:M,showSearch:V,getPopupContainer:X.getPopupContainer},z),ref:$,proFieldProps:F},G),{},{children:c}))},A=f.forwardRef(function(l,C){var $=l.fieldProps,z=l.children,c=l.params,a=l.proFieldProps,F=l.mode,M=l.valueEnum,H=l.request,Y=l.options,V=(0,d.Z)(l,x),y=(0,o.Z)({options:Y,mode:F||"multiple",labelInValue:!0,showSearch:!0,suffixIcon:null,autoClearSearchValue:!0,optionLabelProp:"label"},$),G=(0,f.useContext)(D.Z);return(0,m.jsx)(B.Z,(0,o.Z)((0,o.Z)({valueEnum:(0,Z.h)(M),request:H,params:c,valueType:"select",filedConfig:{customLightMode:!0},fieldProps:(0,o.Z)({getPopupContainer:G.getPopupContainer},y),ref:C,proFieldProps:a},V),{},{children:z}))}),t=f.forwardRef(p),j=A,I=t;I.SearchSelect=j,I.displayName="ProFormComponent",U.Z=I},41594:function(te,U,r){var o=r(30309),d=r(35194),Z=r(50446),f=r(10753),D=r(1317),B=r(54689),m=r(71770),N=r(93236),x=r(19544),p=r(62086),A=["fieldProps","proFieldProps"],t=["fieldProps","proFieldProps"],j="text",I=function(c){var a=c.fieldProps,F=c.proFieldProps,M=(0,Z.Z)(c,A);return(0,p.jsx)(x.Z,(0,d.Z)({valueType:j,fieldProps:a,filedConfig:{valueType:j},proFieldProps:F},M))},l=function(c){var a=(0,f.Z)(c.open||!1,{value:c.open,onChange:c.onOpenChange}),F=(0,o.Z)(a,2),M=F[0],H=F[1];return(0,p.jsx)(D.Z.Item,{shouldUpdate:!0,noStyle:!0,children:function(V){var y,G=V.getFieldValue(c.name||[]);return(0,p.jsx)(B.Z,(0,d.Z)((0,d.Z)({getPopupContainer:function(E){return E&&E.parentNode?E.parentNode:E},onOpenChange:function(E){return H(E)},content:(0,p.jsxs)("div",{style:{padding:"4px 0"},children:[(y=c.statusRender)===null||y===void 0?void 0:y.call(c,G),c.strengthText?(0,p.jsx)("div",{style:{marginTop:10},children:(0,p.jsx)("span",{children:c.strengthText})}):null]}),overlayStyle:{width:240},placement:"rightTop"},c.popoverProps),{},{open:M,children:c.children}))}})},C=function(c){var a=c.fieldProps,F=c.proFieldProps,M=(0,Z.Z)(c,t),H=(0,N.useState)(!1),Y=(0,o.Z)(H,2),V=Y[0],y=Y[1];return a!=null&&a.statusRender&&M.name?(0,p.jsx)(l,{name:M.name,statusRender:a==null?void 0:a.statusRender,popoverProps:a==null?void 0:a.popoverProps,strengthText:a==null?void 0:a.strengthText,open:V,onOpenChange:y,children:(0,p.jsx)("div",{children:(0,p.jsx)(x.Z,(0,d.Z)({valueType:"password",fieldProps:(0,d.Z)((0,d.Z)({},(0,m.Z)(a,["statusRender","popoverProps","strengthText"])),{},{onBlur:function(X){var E;a==null||(E=a.onBlur)===null||E===void 0||E.call(a,X),y(!1)},onClick:function(X){var E;a==null||(E=a.onClick)===null||E===void 0||E.call(a,X),y(!0)}}),proFieldProps:F,filedConfig:{valueType:j}},M))})}):(0,p.jsx)(x.Z,(0,d.Z)({valueType:"password",fieldProps:a,proFieldProps:F,filedConfig:{valueType:j}},M))},$=I;$.Password=C,$.displayName="ProFormComponent",U.Z=$},82605:function(te,U,r){r.d(U,{Z:function(){return B}});var o=function(m){return m[m.\u8BA2\u5355\u4ED8\u6B3E=12]="\u8BA2\u5355\u4ED8\u6B3E",m[m.\u8BA2\u5355\u7ED3\u7B97=3]="\u8BA2\u5355\u7ED3\u7B97",m[m.\u8BA2\u5355\u5931\u6548=13]="\u8BA2\u5355\u5931\u6548",m[m.\u8BA2\u5355\u6210\u529F=14]="\u8BA2\u5355\u6210\u529F",m}({}),d=r(85407),Z=r(93236),f=r(62086),D=function(N){var x=N.status;return(0,f.jsx)(f.Fragment,{children:x&&(0,f.jsx)(d.Z,{color:x===o.\u8BA2\u5355\u7ED3\u7B97?"success":void 0,children:o[x].toString()})})},B=D},95052:function(te,U,r){r.r(U),r.d(U,{default:function(){return J}});var o=r(90228),d=r.n(o),Z=r(87999),f=r.n(Z),D=r(48305),B=r.n(D),m=r(82605),N=r(46779),x=r(89143),p=r(83301),A=r(93236),t=r(62086),j=function(h){var O,P;return(0,t.jsx)(x.Y,{width:(O=h.width)!==null&&O!==void 0?O:400,title:(P=h.title)!==null&&P!==void 0?P:"\u9009\u62E9\u7528\u6237",onFinish:function(){var Q=f()(d()().mark(function R(g){return d()().wrap(function(i){for(;;)switch(i.prev=i.next){case 0:if(!h.onFinish){i.next=2;break}return i.abrupt("return",h.onFinish(g,g.userId));case 2:return i.abrupt("return",!1);case 3:case"end":return i.stop()}},R)}));return function(R){return Q.apply(this,arguments)}}(),trigger:h.trigger?h.trigger:void 0,children:(0,t.jsx)(p.Z,{name:"userId",showSearch:!0,rules:[{required:!0,message:"\u8BF7\u9009\u62E9\u7528\u6237"}],request:f()(d()().mark(function Q(){var R,g;return d()().wrap(function(i){for(;;)switch(i.prev=i.next){case 0:return i.next=2,(0,N.eI)();case 2:return R=i.sent,g=R.data,i.abrupt("return",g.map(function(_){return{label:_.email+"("+_.nickName+")",value:_.id}}));case 5:case"end":return i.stop()}},Q)}))})})},I=j,l=function(h){var O=h.height,P=h.width;return(0,t.jsx)("div",{style:{height:O!=null?O:12,width:P!=null?P:12}})},C=l,$=r(34533),z=r(17783),c=r(34916),a=r(35194),F=r(50446),M=r(56600),H=r(31765),Y=r(19544),V=["fieldProps","proFieldProps"],y="dateTimeRange",G=A.forwardRef(function(S,h){var O=S.fieldProps,P=S.proFieldProps,Q=(0,F.Z)(S,V),R=(0,A.useContext)(H.Z);return(0,t.jsx)(Y.Z,(0,a.Z)({ref:h,fieldProps:(0,a.Z)({getPopupContainer:R.getPopupContainer},O),valueType:y,proFieldProps:P,filedConfig:{valueType:y,customLightMode:!0,lightFilterLabelFormatter:function(b){return(0,M.c)(b,"YYYY-MM-DD HH:mm:ss")}}},Q))}),X=G,E=r(41594),ne=r(7432),oe=r(7034),e=r(96876),u=r(95691),v=r(29925),n=r(36607),s=r(76569),L=r(20617),T=r(5726),w=r.n(T);function J(){var S,h=(0,A.useState)(void 0),O=B()(h,2),P=O[0],Q=O[1],R=function(){var g=f()(d()().mark(function b(i){var _,k;return d()().wrap(function(W){for(;;)switch(W.prev=W.next){case 0:return _=ne.ZP.loading("\u6B63\u5728\u67E5\u8BE2\u4E2D..."),W.next=3,(0,$.MC)(i);case 3:return k=W.sent,_(),ne.ZP.success(k.message),k.success&&Q(k.data),W.abrupt("return",!0);case 8:case"end":return W.stop()}},b)}));return function(i){return g.apply(this,arguments)}}();return(0,t.jsxs)(z._z,{title:"\u5927\u6DD8\u5BA2\u8BA2\u5355\u67E5\u8BE2\u5DE5\u5177",children:[(0,t.jsx)(oe.Z,{children:(0,t.jsxs)(c.t,{layout:"vertical",onFinish:R,initialValues:{},children:[(0,t.jsx)(p.Z,{name:"queryType",label:"\u67E5\u8BE2\u65F6\u95F4\u7C7B\u578B",rules:[{required:!0,message:"\u8BF7\u9009\u62E9\u67E5\u8BE2\u65F6\u95F4\u7C7B\u578B"}],valueEnum:{1:"\u6309\u7167\u8BA2\u5355\u6DD8\u5BA2\u521B\u5EFA\u65F6\u95F4\u67E5\u8BE2",2:"\u6309\u7167\u8BA2\u5355\u6DD8\u5BA2\u4ED8\u6B3E\u65F6\u95F4\u67E5\u8BE2",3:"\u6309\u7167\u8BA2\u5355\u6DD8\u5BA2\u7ED3\u7B97\u65F6\u95F4\u67E5\u8BE2",4:"\u6309\u7167\u8BA2\u5355\u66F4\u65B0\u65F6\u95F4"}}),(0,t.jsx)(X,{name:"datetimeRanger",label:"\u67E5\u8BE2\u65F6\u95F4",rules:[{required:!0,message:"\u8BF7\u8F93\u5165\u67E5\u8BE2\u65F6\u95F4"}],transform:function(b){return{endTime:w()(b[1]).format("YYYY-MM-DD HH:mm:ss"),startTime:w()(b[0]).format("YYYY-MM-DD HH:mm:ss")}}}),(0,t.jsx)(E.Z,{name:"positionIndex",label:"\u4F4D\u70B9",tooltip:"\u4F4D\u70B9\uFF0C\u7B2C\u4E00\u9875\u6570\u636E\u8FD4\u56DE\u91CC\u9762\u6709\u4E2A\u8FD9\u4E2A\u5B57\u6BB5\uFF0C\u67E5\u7B2C\u4E8C\u9875\u7684\u6570\u636E\u7684\u65F6\u5019\u5C31\u4F20\u8FC7\u53BB"}),(0,t.jsx)(E.Z,{name:"pageSize",label:"\u9875\u5927\u5C0F"}),(0,t.jsx)(p.Z,{name:"memberType",label:"\u63A8\u5E7F\u8005\u89D2\u8272\u7C7B\u578B",valueEnum:{2:"\u4E8C\u65B9",3:"\u4E09\u65B9"}}),(0,t.jsx)(p.Z,{name:"tkStatus",label:"\u6DD8\u5BA2\u8BA2\u5355\u72B6\u6001",valueEnum:{12:"\u4ED8\u6B3E",13:"\u5173\u95ED",14:"\u786E\u8BA4\u6536\u8D27",3:"\u7ED3\u7B97\u6210\u529F"}}),(0,t.jsx)(p.Z,{name:"jumpType",label:"\u8DF3\u8F6C\u7C7B\u578B",valueEnum:{"-1":"\u5411\u524D\u7FFB\u9875",1:"\u5411\u540E\u7FFB\u9875"}}),(0,t.jsx)(E.Z,{name:"pageNo",label:"\u9875\u7801"}),(0,t.jsx)(p.Z,{name:"orderScene",label:"\u573A\u666F\u8BA2\u5355\u573A\u666F\u7C7B\u578B",valueEnum:{1:"\u5E38\u89C4\u8BA2\u5355",2:"\u6E20\u9053\u8BA2\u5355",3:"\u4F1A\u5458\u8FD0\u8425\u8BA2\u5355"}})]})}),(0,t.jsx)(C,{}),(0,t.jsx)(oe.Z,{children:P&&(0,t.jsx)(e.Z,{defaultActiveKey:"1",items:[{key:"1",label:"\u67E5\u8BE2\u7ED3\u679C",children:(0,t.jsx)(u.Z,{rowKey:"tradeParentid",dataSource:(S=P.data)===null||S===void 0||(S=S.results)===null||S===void 0?void 0:S.publisherOrderDto,columns:[{dataIndex:"itemImg",title:"\u4EA7\u54C1",render:function(b,i){return(0,t.jsx)(v.Z,{src:"https:"+i.itemImg,width:50})}},{dataIndex:"tbPaidTime",title:"\u4E0B\u5355\u65F6\u95F4"},{dataIndex:"itemTitle",title:"\u6807\u9898"},{dataIndex:"itemPrice",title:"\u5355\u4EF7"},{dataIndex:"pubShareFee",title:"\u4F63\u91D1"},{dataIndex:"pubSharePreFee",title:"\u9884\u4F30\u4F63\u91D1"},{dataIndex:"relationId",title:"\u6E20\u9053ID"},{dataIndex:"tradeParentid",title:"\u8BA2\u5355\u7F16\u53F7",key:"tradeParentid",render:function(b,i){return(0,t.jsx)(n.Z.Paragraph,{copyable:{text:i.tradeParentid},children:i.tradeParentid})}},{dataIndex:"tkStatus",title:"\u72B6\u6001",render:function(b,i){return(0,t.jsx)(m.Z,{status:i.tkStatus})}},{title:"\u64CD\u4F5C",key:"action",render:function(b,i){return(0,t.jsx)(s.Z,{children:(0,t.jsx)(I,{trigger:(0,t.jsx)(L.ZP,{size:"small",children:"\u5173\u8054"}),onFinish:function(){var _=f()(d()().mark(function k(ae,W){var le;return d()().wrap(function(q){for(;;)switch(q.prev=q.next){case 0:return console.log(W),i.userId=W,q.next=4,(0,$.kq)(i);case 4:return le=q.sent,q.abrupt("return",le.success);case 6:case"end":return q.stop()}},k)}));return function(k,ae){return _.apply(this,arguments)}}()})})}}]})}]})})]})}},34533:function(te,U,r){r.d(U,{MC:function(){return B},kE:function(){return p},kq:function(){return N}});var o=r(90228),d=r.n(o),Z=r(87999),f=r.n(Z),D=r(74271);function B(t){return m.apply(this,arguments)}function m(){return m=f()(d()().mark(function t(j){return d()().wrap(function(l){for(;;)switch(l.prev=l.next){case 0:return l.abrupt("return",(0,D.request)("/api/user/order/select-by-dtk",{method:"get",params:j}));case 1:case"end":return l.stop()}},t)})),m.apply(this,arguments)}function N(t){return x.apply(this,arguments)}function x(){return x=f()(d()().mark(function t(j){return d()().wrap(function(l){for(;;)switch(l.prev=l.next){case 0:return l.abrupt("return",(0,D.request)("/api/user/order/write-order-by-dtk",{method:"post",data:j}));case 1:case"end":return l.stop()}},t)})),x.apply(this,arguments)}function p(t){return A.apply(this,arguments)}function A(){return A=f()(d()().mark(function t(j){return d()().wrap(function(l){for(;;)switch(l.prev=l.next){case 0:return l.abrupt("return",(0,D.request)("/api/user/order/list",{method:"get",params:j}));case 1:case"end":return l.stop()}},t)})),A.apply(this,arguments)}},85407:function(te,U,r){r.d(U,{Z:function(){return oe}});var o=r(93236),d=r(82187),Z=r.n(d),f=r(35094),D=r(24389),B=r(48256),m=r(1786),N=r(61550),x=r(75235),p=r(24045),A=r(99978),t=r(97151),j=r(24302),I=r(59338);const l=e=>{const{paddingXXS:u,lineWidth:v,tagPaddingHorizontal:n,componentCls:s,calc:L}=e,T=L(n).sub(v).equal(),w=L(u).sub(v).equal();return{[s]:Object.assign(Object.assign({},(0,t.Wf)(e)),{display:"inline-block",height:"auto",marginInlineEnd:e.marginXS,paddingInline:T,fontSize:e.tagFontSize,lineHeight:e.tagLineHeight,whiteSpace:"nowrap",background:e.defaultBg,border:`${(0,p.bf)(e.lineWidth)} ${e.lineType} ${e.colorBorder}`,borderRadius:e.borderRadiusSM,opacity:1,transition:`all ${e.motionDurationMid}`,textAlign:"start",position:"relative",[`&${s}-rtl`]:{direction:"rtl"},"&, a, a:hover":{color:e.defaultColor},[`${s}-close-icon`]:{marginInlineStart:w,fontSize:e.tagIconSize,color:e.colorTextDescription,cursor:"pointer",transition:`all ${e.motionDurationMid}`,"&:hover":{color:e.colorTextHeading}},[`&${s}-has-color`]:{borderColor:"transparent",[`&, a, a:hover, ${e.iconCls}-close, ${e.iconCls}-close:hover`]:{color:e.colorTextLightSolid}},["&-checkable"]:{backgroundColor:"transparent",borderColor:"transparent",cursor:"pointer",[`&:not(${s}-checkable-checked):hover`]:{color:e.colorPrimary,backgroundColor:e.colorFillSecondary},"&:active, &-checked":{color:e.colorTextLightSolid},"&-checked":{backgroundColor:e.colorPrimary,"&:hover":{backgroundColor:e.colorPrimaryHover}},"&:active":{backgroundColor:e.colorPrimaryActive}},["&-hidden"]:{display:"none"},[`> ${e.iconCls} + span, > span + ${e.iconCls}`]:{marginInlineStart:T}}),[`${s}-borderless`]:{borderColor:"transparent",background:e.tagBorderlessBg}}},C=e=>{const{lineWidth:u,fontSizeIcon:v,calc:n}=e,s=e.fontSizeSM;return(0,j.TS)(e,{tagFontSize:s,tagLineHeight:(0,p.bf)(n(e.lineHeightSM).mul(s).equal()),tagIconSize:n(v).sub(n(u).mul(2)).equal(),tagPaddingHorizontal:8,tagBorderlessBg:e.defaultBg})},$=e=>({defaultBg:new A.C(e.colorFillQuaternary).onBackground(e.colorBgContainer).toHexString(),defaultColor:e.colorText});var z=(0,I.I$)("Tag",e=>{const u=C(e);return l(u)},$),c=function(e,u){var v={};for(var n in e)Object.prototype.hasOwnProperty.call(e,n)&&u.indexOf(n)<0&&(v[n]=e[n]);if(e!=null&&typeof Object.getOwnPropertySymbols=="function")for(var s=0,n=Object.getOwnPropertySymbols(e);s<n.length;s++)u.indexOf(n[s])<0&&Object.prototype.propertyIsEnumerable.call(e,n[s])&&(v[n[s]]=e[n[s]]);return v},F=o.forwardRef((e,u)=>{const{prefixCls:v,style:n,className:s,checked:L,onChange:T,onClick:w}=e,J=c(e,["prefixCls","style","className","checked","onChange","onClick"]),{getPrefixCls:S,tag:h}=o.useContext(x.E_),O=i=>{T==null||T(!L),w==null||w(i)},P=S("tag",v),[Q,R,g]=z(P),b=Z()(P,`${P}-checkable`,{[`${P}-checkable-checked`]:L},h==null?void 0:h.className,s,R,g);return Q(o.createElement("span",Object.assign({},J,{ref:u,style:Object.assign(Object.assign({},n),h==null?void 0:h.style),className:b,onClick:O})))}),M=r(72916);const H=e=>(0,M.Z)(e,(u,v)=>{let{textColor:n,lightBorderColor:s,lightColor:L,darkColor:T}=v;return{[`${e.componentCls}${e.componentCls}-${u}`]:{color:n,background:L,borderColor:s,"&-inverse":{color:e.colorTextLightSolid,background:T,borderColor:T},[`&${e.componentCls}-borderless`]:{borderColor:"transparent"}}}});var Y=(0,I.bk)(["Tag","preset"],e=>{const u=C(e);return H(u)},$);function V(e){return typeof e!="string"?e:e.charAt(0).toUpperCase()+e.slice(1)}const y=(e,u,v)=>{const n=V(v);return{[`${e.componentCls}${e.componentCls}-${u}`]:{color:e[`color${v}`],background:e[`color${n}Bg`],borderColor:e[`color${n}Border`],[`&${e.componentCls}-borderless`]:{borderColor:"transparent"}}}};var G=(0,I.bk)(["Tag","status"],e=>{const u=C(e);return[y(u,"success","Success"),y(u,"processing","Info"),y(u,"error","Error"),y(u,"warning","Warning")]},$),X=function(e,u){var v={};for(var n in e)Object.prototype.hasOwnProperty.call(e,n)&&u.indexOf(n)<0&&(v[n]=e[n]);if(e!=null&&typeof Object.getOwnPropertySymbols=="function")for(var s=0,n=Object.getOwnPropertySymbols(e);s<n.length;s++)u.indexOf(n[s])<0&&Object.prototype.propertyIsEnumerable.call(e,n[s])&&(v[n[s]]=e[n[s]]);return v};const ne=o.forwardRef((e,u)=>{const{prefixCls:v,className:n,rootClassName:s,style:L,children:T,icon:w,color:J,onClose:S,bordered:h=!0,visible:O}=e,P=X(e,["prefixCls","className","rootClassName","style","children","icon","color","onClose","bordered","visible"]),{getPrefixCls:Q,direction:R,tag:g}=o.useContext(x.E_),[b,i]=o.useState(!0),_=(0,f.Z)(P,["closeIcon","closable"]);o.useEffect(()=>{O!==void 0&&i(O)},[O]);const k=(0,D.o2)(J),ae=(0,D.yT)(J),W=k||ae,le=Object.assign(Object.assign({backgroundColor:J&&!W?J:void 0},g==null?void 0:g.style),L),K=Q("tag",v),[q,pe,me]=z(K),ve=Z()(K,g==null?void 0:g.className,{[`${K}-${J}`]:W,[`${K}-has-color`]:J&&!W,[`${K}-hidden`]:!b,[`${K}-rtl`]:R==="rtl",[`${K}-borderless`]:!h},n,s,pe,me),ie=re=>{re.stopPropagation(),S==null||S(re),!re.defaultPrevented&&i(!1)},[,ge]=(0,B.Z)((0,B.w)(e),(0,B.w)(g),{closable:!1,closeIconRender:re=>{const Ce=o.createElement("span",{className:`${K}-close-icon`,onClick:ie},re);return(0,m.wm)(re,Ce,ee=>({onClick:ce=>{var se;(se=ee==null?void 0:ee.onClick)===null||se===void 0||se.call(ee,ce),ie(ce)},className:Z()(ee==null?void 0:ee.className,`${K}-close-icon`)}))}}),he=typeof P.onClick=="function"||T&&T.type==="a",de=w||null,fe=de?o.createElement(o.Fragment,null,de,T&&o.createElement("span",null,T)):T,ue=o.createElement("span",Object.assign({},_,{ref:u,className:ve,style:le}),fe,ge,k&&o.createElement(Y,{key:"preset",prefixCls:K}),ae&&o.createElement(G,{key:"status",prefixCls:K}));return q(he?o.createElement(N.Z,{component:"Tag"},ue):ue)});ne.CheckableTag=F;var oe=ne}}]);