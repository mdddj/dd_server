(self.webpackChunk=self.webpackChunk||[]).push([[8239],{23902:function(ce,j,i){"use strict";i.d(j,{Z:function(){return T}});var n=i(37469),x=i(93236),D={icon:{tag:"svg",attrs:{viewBox:"64 64 896 896",focusable:"false"},children:[{tag:"path",attrs:{d:"M257.7 752c2 0 4-.2 6-.5L431.9 722c2-.4 3.9-1.3 5.3-2.8l423.9-423.9a9.96 9.96 0 000-14.1L694.9 114.9c-1.9-1.9-4.4-2.9-7.1-2.9s-5.2 1-7.1 2.9L256.8 538.8c-1.5 1.5-2.4 3.3-2.8 5.3l-29.5 168.2a33.5 33.5 0 009.4 29.8c6.6 6.4 14.9 9.9 23.8 9.9zm67.4-174.4L687.8 215l73.3 73.3-362.7 362.6-88.9 15.7 15.6-89zM880 836H144c-17.7 0-32 14.3-32 32v36c0 4.4 3.6 8 8 8h784c4.4 0 8-3.6 8-8v-36c0-17.7-14.3-32-32-32z"}}]},name:"edit",theme:"outlined"},A=D,N=i(16286),p=function(ee,Q){return x.createElement(N.Z,(0,n.Z)({},ee,{ref:Q,icon:A}))},m=x.forwardRef(p),T=m},89143:function(ce,j,i){"use strict";i.d(j,{Y:function(){return Je}});var n=i(98106),x=i(2292),D=i(35194),A=i(30309),N=i(50446),p=i(94190),m=i(62614),T=i(5874),K=i(72266),ee=i.n(K),Q=i(10753),L=i(21092),u=i(93236),ue=i(19103),We=i(46438),I=i(62086),me=["children","trigger","onVisibleChange","onOpenChange","modalProps","onFinish","submitTimeout","title","width","visible","open"];function Je(H){var ge,ve,Qe=H.children,Ce=H.trigger,Ye=H.onVisibleChange,qe=H.onOpenChange,g=H.modalProps,Se=H.onFinish,ye=H.submitTimeout,_e=H.title,et=H.width,Ne=H.visible,tt=H.open,B=(0,N.Z)(H,me);(0,L.ET)(!B.footer||!(g!=null&&g.footer),"ModalForm \u662F\u4E00\u4E2A ProForm \u7684\u7279\u6B8A\u5E03\u5C40\uFF0C\u5982\u679C\u60F3\u81EA\u5B9A\u4E49\u6309\u94AE\uFF0C\u8BF7\u4F7F\u7528 submit.render \u81EA\u5B9A\u4E49\u3002");var Te=(0,u.useContext)(m.ZP.ConfigContext),nt=(0,u.useState)([]),ot=(0,A.Z)(nt,2),rt=ot[1],lt=(0,u.useState)(!1),He=(0,A.Z)(lt,2),Le=He[0],$e=He[1],ke=(0,Q.Z)(!!Ne,{value:tt||Ne,onChange:qe||Ye}),it=(0,A.Z)(ke,2),Re=it[0],re=it[1],Pe=(0,u.useRef)(null),st=(0,u.useCallback)(function(R){Pe.current===null&&R&&rt([]),Pe.current=R},[]),xe=(0,u.useRef)(),mt=(0,u.useCallback)(function(){var R,v,h,P=(R=(v=B.form)!==null&&v!==void 0?v:(h=B.formRef)===null||h===void 0?void 0:h.current)!==null&&R!==void 0?R:xe.current;P&&g!==null&&g!==void 0&&g.destroyOnClose&&P.resetFields()},[g==null?void 0:g.destroyOnClose,B.form,B.formRef]);(0,u.useImperativeHandle)(B.formRef,function(){return xe.current},[xe.current]);var at=(0,u.useMemo)(function(){return Ce?u.cloneElement(Ce,(0,D.Z)((0,D.Z)({key:"trigger"},Ce.props),{},{onClick:function(){var R=(0,x.Z)((0,n.Z)().mark(function h(P){var F,O;return(0,n.Z)().wrap(function(z){for(;;)switch(z.prev=z.next){case 0:re(!Re),(F=Ce.props)===null||F===void 0||(O=F.onClick)===null||O===void 0||O.call(F,P);case 2:case"end":return z.stop()}},h)}));function v(h){return R.apply(this,arguments)}return v}()})):null},[re,Ce,Re]),Ze=(0,u.useMemo)(function(){var R,v,h,P,F,O;return B.submitter===!1?!1:ee()({searchConfig:{submitText:(R=(v=g==null?void 0:g.okText)!==null&&v!==void 0?v:(h=Te.locale)===null||h===void 0||(h=h.Modal)===null||h===void 0?void 0:h.okText)!==null&&R!==void 0?R:"\u786E\u8BA4",resetText:(P=(F=g==null?void 0:g.cancelText)!==null&&F!==void 0?F:(O=Te.locale)===null||O===void 0||(O=O.Modal)===null||O===void 0?void 0:O.cancelText)!==null&&P!==void 0?P:"\u53D6\u6D88"},resetButtonProps:{preventDefault:!0,disabled:ye?Le:void 0,onClick:function(z){var Ae;re(!1),g==null||(Ae=g.onCancel)===null||Ae===void 0||Ae.call(g,z)}}},B.submitter)},[(ge=Te.locale)===null||ge===void 0||(ge=ge.Modal)===null||ge===void 0?void 0:ge.cancelText,(ve=Te.locale)===null||ve===void 0||(ve=ve.Modal)===null||ve===void 0?void 0:ve.okText,g,B.submitter,re,Le,ye]),gt=(0,u.useCallback)(function(R,v){return(0,I.jsxs)(I.Fragment,{children:[R,Pe.current&&v?(0,I.jsx)(u.Fragment,{children:(0,ue.createPortal)(v,Pe.current)},"submitter"):v]})},[]),ct=(0,u.useCallback)(function(){var R=(0,x.Z)((0,n.Z)().mark(function v(h){var P,F,O;return(0,n.Z)().wrap(function(z){for(;;)switch(z.prev=z.next){case 0:return P=Se==null?void 0:Se(h),ye&&P instanceof Promise&&($e(!0),F=setTimeout(function(){return $e(!1)},ye),P.finally(function(){clearTimeout(F),$e(!1)})),z.next=4,P;case 4:return O=z.sent,O&&re(!1),z.abrupt("return",O);case 7:case"end":return z.stop()}},v)}));return function(v){return R.apply(this,arguments)}}(),[Se,re,ye]),ut=(0,p.X)(Re);return(0,I.jsxs)(I.Fragment,{children:[(0,I.jsx)(T.Z,(0,D.Z)((0,D.Z)((0,D.Z)({title:_e,width:et||800},g),ut),{},{onCancel:function(v){var h;ye&&Le||(re(!1),g==null||(h=g.onCancel)===null||h===void 0||h.call(g,v))},afterClose:function(){var v;mt(),Re&&re(!1),g==null||(v=g.afterClose)===null||v===void 0||v.call(g)},footer:B.submitter!==!1?(0,I.jsx)("div",{ref:st,style:{display:"flex",justifyContent:"flex-end"}}):null,children:(0,I.jsx)(We.I,(0,D.Z)((0,D.Z)({formComponentType:"ModalForm",layout:"vertical"},B),{},{onInit:function(v,h){var P;B.formRef&&(B.formRef.current=h),B==null||(P=B.onInit)===null||P===void 0||P.call(B,v,h),xe.current=h},formRef:xe,submitter:Ze,onFinish:function(){var R=(0,x.Z)((0,n.Z)().mark(function v(h){var P;return(0,n.Z)().wrap(function(O){for(;;)switch(O.prev=O.next){case 0:return O.next=2,ct(h);case 2:return P=O.sent,O.abrupt("return",P);case 4:case"end":return O.stop()}},v)}));return function(v){return R.apply(this,arguments)}}(),contentRender:gt,children:Qe}))})),at]})}},18918:function(ce,j,i){"use strict";i.d(j,{N:function(){return n}});const n=x=>({color:x.colorLink,textDecoration:"none",outline:"none",cursor:"pointer",transition:`color ${x.motionDurationSlow}`,"&:focus, &:hover":{color:x.colorLinkHover},"&:active":{color:x.colorLinkActive}})},69645:function(ce,j,i){"use strict";i.d(j,{Z:function(){return x}});var n=i(93236);function x(D,A,N){const p=n.useRef({});function m(T){if(!p.current||p.current.data!==D||p.current.childrenColumnName!==A||p.current.getRowKey!==N){let ee=function(Q){Q.forEach((L,u)=>{const ue=N(L,u);K.set(ue,L),L&&typeof L=="object"&&A in L&&ee(L[A]||[])})};const K=new Map;ee(D),p.current={data:D,childrenColumnName:A,kvMap:K,getRowKey:N}}return p.current.kvMap.get(T)}return[m]}},36607:function(ce,j,i){"use strict";i.d(j,{Z:function(){return Nt}});var n=i(93236),x=i(23902),D=i(82187),A=i.n(D),N=i(96362),p=i(844),m=i(97643),T=i(10753),K=i(35094),ee=i(9679),Q=i(56391),L=i(23940),u=function(e,o){var r={};for(var t in e)Object.prototype.hasOwnProperty.call(e,t)&&o.indexOf(t)<0&&(r[t]=e[t]);if(e!=null&&typeof Object.getOwnPropertySymbols=="function")for(var l=0,t=Object.getOwnPropertySymbols(e);l<t.length;l++)o.indexOf(t[l])<0&&Object.prototype.propertyIsEnumerable.call(e,t[l])&&(r[t[l]]=e[t[l]]);return r};const ue={border:0,background:"transparent",padding:0,lineHeight:"inherit",display:"inline-block"};var I=n.forwardRef((e,o)=>{const r=d=>{const{keyCode:a}=d;a===L.Z.ENTER&&d.preventDefault()},t=d=>{const{keyCode:a}=d,{onClick:E}=e;a===L.Z.ENTER&&E&&E()},{style:l,noStyle:c,disabled:C}=e,M=u(e,["style","noStyle","disabled"]);let y={};return c||(y=Object.assign({},ue)),C&&(y.pointerEvents="none"),y=Object.assign(Object.assign({},y),l),n.createElement("div",Object.assign({role:"button",tabIndex:0,ref:o},M,{onKeyDown:r,onKeyUp:t,style:y}))}),me=i(75235),Je=i(76449),H=i(80898),ge=i(37469),ve={icon:{tag:"svg",attrs:{viewBox:"64 64 896 896",focusable:"false"},children:[{tag:"path",attrs:{d:"M864 170h-60c-4.4 0-8 3.6-8 8v518H310v-73c0-6.7-7.8-10.5-13-6.3l-141.9 112a8 8 0 000 12.6l141.9 112c5.3 4.2 13 .4 13-6.3v-75h498c35.3 0 64-28.7 64-64V178c0-4.4-3.6-8-8-8z"}}]},name:"enter",theme:"outlined"},Qe=ve,Ce=i(16286),Ye=function(o,r){return n.createElement(Ce.Z,(0,ge.Z)({},o,{ref:r,icon:Qe}))},qe=n.forwardRef(Ye),g=qe,Se=i(1786),ye=i(57098),_e=i(18918),et=i(59338),Ne=i(22601),tt=i(24045);const B=(e,o,r,t)=>{const{titleMarginBottom:l,fontWeightStrong:c}=t;return{marginBottom:l,color:r,fontWeight:c,fontSize:e,lineHeight:o}},Te=e=>{const o=[1,2,3,4,5],r={};return o.forEach(t=>{r[`
      h${t}&,
      div&-h${t},
      div&-h${t} > textarea,
      h${t}
    `]=B(e[`fontSizeHeading${t}`],e[`lineHeightHeading${t}`],e.colorTextHeading,e)}),r},nt=e=>{const{componentCls:o}=e;return{"a&, a":Object.assign(Object.assign({},(0,_e.N)(e)),{textDecoration:e.linkDecoration,"&:active, &:hover":{textDecoration:e.linkHoverDecoration},[`&[disabled], &${o}-disabled`]:{color:e.colorTextDisabled,cursor:"not-allowed","&:active, &:hover":{color:e.colorTextDisabled},"&:active":{pointerEvents:"none"}}})}},ot=e=>({code:{margin:"0 0.2em",paddingInline:"0.4em",paddingBlock:"0.2em 0.1em",fontSize:"85%",fontFamily:e.fontFamilyCode,background:"rgba(150, 150, 150, 0.1)",border:"1px solid rgba(100, 100, 100, 0.2)",borderRadius:3},kbd:{margin:"0 0.2em",paddingInline:"0.4em",paddingBlock:"0.15em 0.1em",fontSize:"90%",fontFamily:e.fontFamilyCode,background:"rgba(150, 150, 150, 0.06)",border:"1px solid rgba(100, 100, 100, 0.2)",borderBottomWidth:2,borderRadius:3},mark:{padding:0,backgroundColor:Ne.gold[2]},"u, ins":{textDecoration:"underline",textDecorationSkipInk:"auto"},"s, del":{textDecoration:"line-through"},strong:{fontWeight:600},"ul, ol":{marginInline:0,marginBlock:"0 1em",padding:0,li:{marginInline:"20px 0",marginBlock:0,paddingInline:"4px 0",paddingBlock:0}},ul:{listStyleType:"circle",ul:{listStyleType:"disc"}},ol:{listStyleType:"decimal"},"pre, blockquote":{margin:"1em 0"},pre:{padding:"0.4em 0.6em",whiteSpace:"pre-wrap",wordWrap:"break-word",background:"rgba(150, 150, 150, 0.1)",border:"1px solid rgba(100, 100, 100, 0.2)",borderRadius:3,fontFamily:e.fontFamilyCode,code:{display:"inline",margin:0,padding:0,fontSize:"inherit",fontFamily:"inherit",background:"transparent",border:0}},blockquote:{paddingInline:"0.6em 0",paddingBlock:0,borderInlineStart:"4px solid rgba(100, 100, 100, 0.2)",opacity:.85}}),rt=e=>{const{componentCls:o,paddingSM:r}=e,t=r;return{"&-edit-content":{position:"relative","div&":{insetInlineStart:e.calc(e.paddingSM).mul(-1).equal(),marginTop:e.calc(t).mul(-1).equal(),marginBottom:`calc(1em - ${(0,tt.bf)(t)})`},[`${o}-edit-content-confirm`]:{position:"absolute",insetInlineEnd:e.calc(e.marginXS).add(2).equal(),insetBlockEnd:e.marginXS,color:e.colorTextDescription,fontWeight:"normal",fontSize:e.fontSize,fontStyle:"normal",pointerEvents:"none"},textarea:{margin:"0!important",MozTransition:"none",height:"1em"}}}},lt=e=>({[`${e.componentCls}-copy-success`]:{[`
    &,
    &:hover,
    &:focus`]:{color:e.colorSuccess}},[`${e.componentCls}-copy-icon-only`]:{marginInlineStart:0}}),He=()=>({[`
  a&-ellipsis,
  span&-ellipsis
  `]:{display:"inline-block",maxWidth:"100%"},"&-single-line":{whiteSpace:"nowrap"},"&-ellipsis-single-line":{overflow:"hidden",textOverflow:"ellipsis","a&, span&":{verticalAlign:"bottom"},"> code":{paddingBlock:0,maxWidth:"calc(100% - 1.2em)",display:"inline-block",overflow:"hidden",textOverflow:"ellipsis",verticalAlign:"bottom",boxSizing:"content-box"}},"&-ellipsis-multiple-line":{display:"-webkit-box",overflow:"hidden",WebkitLineClamp:3,WebkitBoxOrient:"vertical"}}),Le=e=>{const{componentCls:o,titleMarginTop:r}=e;return{[o]:Object.assign(Object.assign(Object.assign(Object.assign(Object.assign(Object.assign(Object.assign(Object.assign(Object.assign({color:e.colorText,wordBreak:"break-word",lineHeight:e.lineHeight,[`&${o}-secondary`]:{color:e.colorTextDescription},[`&${o}-success`]:{color:e.colorSuccess},[`&${o}-warning`]:{color:e.colorWarning},[`&${o}-danger`]:{color:e.colorError,"a&:active, a&:focus":{color:e.colorErrorActive},"a&:hover":{color:e.colorErrorHover}},[`&${o}-disabled`]:{color:e.colorTextDisabled,cursor:"not-allowed",userSelect:"none"},[`
        div&,
        p
      `]:{marginBottom:"1em"}},Te(e)),{[`
      & + h1${o},
      & + h2${o},
      & + h3${o},
      & + h4${o},
      & + h5${o}
      `]:{marginTop:r},[`
      div,
      ul,
      li,
      p,
      h1,
      h2,
      h3,
      h4,
      h5`]:{[`
        + h1,
        + h2,
        + h3,
        + h4,
        + h5
        `]:{marginTop:r}}}),ot(e)),nt(e)),{[`
        ${o}-expand,
        ${o}-collapse,
        ${o}-edit,
        ${o}-copy
      `]:Object.assign(Object.assign({},(0,_e.N)(e)),{marginInlineStart:e.marginXXS})}),rt(e)),lt(e)),He()),{"&-rtl":{direction:"rtl"}})}},$e=()=>({titleMarginTop:"1.2em",titleMarginBottom:"0.5em"});var ke=(0,et.I$)("Typography",e=>[Le(e)],$e),Re=e=>{const{prefixCls:o,"aria-label":r,className:t,style:l,direction:c,maxLength:C,autoSize:M=!0,value:y,onSave:d,onCancel:a,onEnd:E,component:b,enterIcon:S=n.createElement(g,null)}=e,Y=n.useRef(null),W=n.useRef(!1),ne=n.useRef(),[de,fe]=n.useState(y);n.useEffect(()=>{fe(y)},[y]),n.useEffect(()=>{if(Y.current&&Y.current.resizableTextArea){const{textArea:U}=Y.current.resizableTextArea;U.focus();const{length:_}=U.value;U.setSelectionRange(_,_)}},[]);const $=U=>{let{target:_}=U;fe(_.value.replace(/[\n\r]/g,""))},k=()=>{W.current=!0},le=()=>{W.current=!1},V=U=>{let{keyCode:_}=U;W.current||(ne.current=_)},Me=()=>{d(de.trim())},ie=U=>{let{keyCode:_,ctrlKey:Ee,altKey:we,metaKey:je,shiftKey:De}=U;ne.current===_&&!W.current&&!Ee&&!we&&!je&&!De&&(_===L.Z.ENTER?(Me(),E==null||E()):_===L.Z.ESC&&a())},w=()=>{Me()},se=b?`${o}-${b}`:"",[X,q,G]=ke(o),pe=A()(o,`${o}-edit-content`,{[`${o}-rtl`]:c==="rtl"},t,se,q,G);return X(n.createElement("div",{className:pe,style:l},n.createElement(ye.Z,{ref:Y,maxLength:C,value:de,onChange:$,onKeyDown:V,onKeyUp:ie,onCompositionStart:k,onCompositionEnd:le,onBlur:w,"aria-label":r,rows:1,autoSize:M}),S!==null?(0,Se.Tm)(S,{className:`${o}-edit-content-confirm`}):null))},re=i(874),Pe=i.n(re),st=i(99513),xe=function(e,o,r,t){function l(c){return c instanceof r?c:new r(function(C){C(c)})}return new(r||(r=Promise))(function(c,C){function M(a){try{d(t.next(a))}catch(E){C(E)}}function y(a){try{d(t.throw(a))}catch(E){C(E)}}function d(a){a.done?c(a.value):l(a.value).then(M,y)}d((t=t.apply(e,o||[])).next())})},at=e=>{let{copyConfig:o,children:r}=e;const[t,l]=n.useState(!1),[c,C]=n.useState(!1),M=n.useRef(null),y=()=>{M.current&&clearTimeout(M.current)},d={};o.format&&(d.format=o.format),n.useEffect(()=>y,[]);const a=(0,st.zX)(E=>xe(void 0,void 0,void 0,function*(){var b;E==null||E.preventDefault(),E==null||E.stopPropagation(),C(!0);try{const S=typeof o.text=="function"?yield o.text():o.text;Pe()(S||String(r)||"",d),C(!1),l(!0),y(),M.current=setTimeout(()=>{l(!1)},3e3),(b=o.onCopy)===null||b===void 0||b.call(o,E)}catch(S){throw C(!1),S}}));return{copied:t,copyLoading:c,onClick:a}};function Ze(e,o){return n.useMemo(()=>{const r=!!e;return[r,Object.assign(Object.assign({},o),r&&typeof e=="object"?e:null)]},[e])}var ct=(e,o)=>{const r=n.useRef(!1);n.useEffect(()=>{r.current?e():r.current=!0},o)},ut=function(e,o){var r={};for(var t in e)Object.prototype.hasOwnProperty.call(e,t)&&o.indexOf(t)<0&&(r[t]=e[t]);if(e!=null&&typeof Object.getOwnPropertySymbols=="function")for(var l=0,t=Object.getOwnPropertySymbols(e);l<t.length;l++)o.indexOf(t[l])<0&&Object.prototype.propertyIsEnumerable.call(e,t[l])&&(r[t[l]]=e[t[l]]);return r},v=n.forwardRef((e,o)=>{const{prefixCls:r,component:t="article",className:l,rootClassName:c,setContentRef:C,children:M,direction:y,style:d}=e,a=ut(e,["prefixCls","component","className","rootClassName","setContentRef","children","direction","style"]),{getPrefixCls:E,direction:b,typography:S}=n.useContext(me.E_),Y=y!=null?y:b;let W=o;C&&(W=(0,ee.sQ)(o,C));const ne=E("typography",r),[de,fe,$]=ke(ne),k=A()(ne,S==null?void 0:S.className,{[`${ne}-rtl`]:Y==="rtl"},l,c,fe,$),le=Object.assign(Object.assign({},S==null?void 0:S.style),d);return de(n.createElement(t,Object.assign({className:k,style:le,ref:W},a),M))}),h=i(26985),P=i(95769),F=i(55035);function O(e){return e===!1?[!1,!1]:Array.isArray(e)?e:[e]}function Oe(e,o,r){return e===!0||e===void 0?o:e||r&&o}function z(e){const{prefixCls:o,copied:r,locale:t={},onCopy:l,iconOnly:c,tooltips:C,icon:M,loading:y}=e,d=O(C),a=O(M),{copied:E,copy:b}=t,S=r?Oe(d[1],E):Oe(d[0],b),W=typeof S=="string"?S:r?E:b;return n.createElement(H.Z,{key:"copy",title:S},n.createElement(I,{className:A()(`${o}-copy`,{[`${o}-copy-success`]:r,[`${o}-copy-icon-only`]:c}),onClick:l,"aria-label":W},r?Oe(a[1],n.createElement(h.Z,null),!0):Oe(a[0],y?n.createElement(F.Z,null):n.createElement(P.Z,null),!0)))}var Ae=i(83288);const Ke=n.forwardRef((e,o)=>{let{style:r,children:t}=e;const l=n.useRef(null);return n.useImperativeHandle(o,()=>({isExceed:()=>{const c=l.current;return c.scrollHeight>c.clientHeight},getHeight:()=>l.current.clientHeight})),n.createElement("span",{"aria-hidden":!0,ref:l,style:Object.assign({position:"fixed",display:"block",left:0,top:0,pointerEvents:"none",backgroundColor:"rgba(255, 0, 0, 0.65)"},r)},t)});function vt(e){const o=typeof e;return o==="string"||o==="number"}function Rt(e){let o=0;return e.forEach(r=>{vt(r)?o+=String(r).length:o+=1}),o}function yt(e,o){let r=0;const t=[];for(let l=0;l<e.length;l+=1){if(r===o)return t;const c=e[l],M=vt(c)?String(c).length:1,y=r+M;if(y>o){const d=o-r;return t.push(String(c).slice(0,d)),t}t.push(c),r=y}return e}const dt=0,ft=1,pt=2,Et=3,Fe={display:"-webkit-box",overflow:"hidden",WebkitBoxOrient:"vertical"};function Pt(e){const{enableMeasure:o,width:r,text:t,children:l,rows:c,expanded:C,miscDeps:M,onEllipsis:y}=e,d=n.useMemo(()=>(0,p.Z)(t),[t]),a=n.useMemo(()=>Rt(d),[t]),E=n.useMemo(()=>l(d,!1),[t]),[b,S]=n.useState(null),Y=n.useRef(null),W=n.useRef(null),ne=n.useRef(null),de=n.useRef(null),[fe,$]=n.useState(!1),[k,le]=n.useState(dt),[V,Me]=n.useState(0);(0,m.Z)(()=>{le(o&&r&&a?ft:dt)},[r,t,c,o,d]),(0,m.Z)(()=>{var X,q,G,pe;if(k===ft){const U=!!(!((X=W.current)===null||X===void 0)&&X.isExceed());le(U?pt:Et),S(U?[0,a]:null),$(U);const _=((q=W.current)===null||q===void 0?void 0:q.getHeight())||0,Ee=c===1?0:((G=ne.current)===null||G===void 0?void 0:G.getHeight())||0,we=((pe=de.current)===null||pe===void 0?void 0:pe.getHeight())||0,je=Ee+we,De=Math.max(_,je);Me(De+1),y(U)}},[k]);const ie=b?Math.ceil((b[0]+b[1])/2):0;(0,m.Z)(()=>{var X;const[q,G]=b||[0,0];if(q!==G){const U=(((X=Y.current)===null||X===void 0?void 0:X.getHeight())||0)>V;let _=ie;G-q===1&&(_=U?q:G),S(U?[q,_]:[_,G])}},[b,ie]);const w=n.useMemo(()=>{if(k!==pt||!b||b[0]!==b[1]){const X=l(d,!1);return k!==Et&&k!==dt?n.createElement("span",{style:Object.assign(Object.assign({},Fe),{WebkitLineClamp:c})},X):X}return l(C?d:yt(d,b[0]),fe)},[C,k,b,d].concat((0,Ae.Z)(M))),se={width:r,whiteSpace:"normal",margin:0,padding:0};return n.createElement(n.Fragment,null,w,k===ft&&n.createElement(n.Fragment,null,n.createElement(Ke,{style:Object.assign(Object.assign(Object.assign({},se),Fe),{WebkitLineClamp:c}),ref:W},E),n.createElement(Ke,{style:Object.assign(Object.assign(Object.assign({},se),Fe),{WebkitLineClamp:c-1}),ref:ne},E),n.createElement(Ke,{style:Object.assign(Object.assign(Object.assign({},se),Fe),{WebkitLineClamp:1}),ref:de},l([],!0))),k===pt&&b&&b[0]!==b[1]&&n.createElement(Ke,{style:Object.assign(Object.assign({},se),{top:400}),ref:Y},l(yt(d,ie),!0)))}var Mt=e=>{let{enableEllipsis:o,isEllipsis:r,children:t,tooltipProps:l}=e;return!(l!=null&&l.title)||!o?t:n.createElement(H.Z,Object.assign({open:r?void 0:!1},l),t)},wt=function(e,o){var r={};for(var t in e)Object.prototype.hasOwnProperty.call(e,t)&&o.indexOf(t)<0&&(r[t]=e[t]);if(e!=null&&typeof Object.getOwnPropertySymbols=="function")for(var l=0,t=Object.getOwnPropertySymbols(e);l<t.length;l++)o.indexOf(t[l])<0&&Object.prototype.propertyIsEnumerable.call(e,t[l])&&(r[t[l]]=e[t[l]]);return r};function jt(e,o){let{mark:r,code:t,underline:l,delete:c,strong:C,keyboard:M,italic:y}=e,d=o;function a(E,b){b&&(d=n.createElement(E,{},d))}return a("strong",C),a("u",l),a("del",c),a("code",t),a("mark",r),a("kbd",M),a("i",y),d}const Dt="...";var ze=n.forwardRef((e,o)=>{var r,t,l;const{prefixCls:c,className:C,style:M,type:y,disabled:d,children:a,ellipsis:E,editable:b,copyable:S,component:Y,title:W}=e,ne=wt(e,["prefixCls","className","style","type","disabled","children","ellipsis","editable","copyable","component","title"]),{getPrefixCls:de,direction:fe}=n.useContext(me.E_),[$]=(0,Je.Z)("Text"),k=n.useRef(null),le=n.useRef(null),V=de("typography",c),Me=(0,K.Z)(ne,["mark","code","delete","underline","strong","keyboard","italic"]),[ie,w]=Ze(b),[se,X]=(0,T.Z)(!1,{value:w.editing}),{triggerType:q=["icon"]}=w,G=s=>{var f;s&&((f=w.onStart)===null||f===void 0||f.call(w)),X(s)};ct(()=>{var s;se||(s=le.current)===null||s===void 0||s.focus()},[se]);const pe=s=>{s==null||s.preventDefault(),G(!0)},U=s=>{var f;(f=w.onChange)===null||f===void 0||f.call(w,s),G(!1)},_=()=>{var s;(s=w.onCancel)===null||s===void 0||s.call(w),G(!1)},[Ee,we]=Ze(S),{copied:je,copyLoading:De,onClick:Ht}=at({copyConfig:we,children:a}),[bt,kt]=n.useState(!1),[ht,Kt]=n.useState(!1),[Ct,Ft]=n.useState(!1),[xt,zt]=n.useState(!1),[Vt,Xt]=n.useState(!0),[be,Z]=Ze(E,{expandable:!1,symbol:s=>s?$==null?void 0:$.collapse:$==null?void 0:$.expand}),[oe,Gt]=(0,T.Z)(Z.defaultExpanded||!1,{value:Z.expanded}),J=be&&(!oe||Z.expandable==="collapsible"),{rows:Ie=1}=Z,Ve=n.useMemo(()=>J&&(Z.suffix!==void 0||Z.onEllipsis||Z.expandable||ie||Ee),[J,Z,ie,Ee]);(0,m.Z)(()=>{be&&!Ve&&(kt((0,Q.G)("webkitLineClamp")),Kt((0,Q.G)("textOverflow")))},[Ve,be]);const[ae,Jt]=n.useState(J),Ot=n.useMemo(()=>Ve?!1:Ie===1?ht:bt,[Ve,ht,bt]);(0,m.Z)(()=>{Jt(Ot&&J)},[Ot,J]);const St=J&&(ae?xt:Ct),Qt=J&&Ie===1&&ae,Xe=J&&Ie>1&&ae,Yt=(s,f)=>{var te;Gt(f.expanded),(te=Z.onExpand)===null||te===void 0||te.call(Z,s,f)},[Tt,qt]=n.useState(0),en=s=>{let{offsetWidth:f}=s;qt(f)},tn=s=>{var f;Ft(s),Ct!==s&&((f=Z.onEllipsis)===null||f===void 0||f.call(Z,s))};n.useEffect(()=>{const s=k.current;if(be&&ae&&s){const f=Xe?s.offsetHeight<s.scrollHeight:s.offsetWidth<s.scrollWidth;xt!==f&&zt(f)}},[be,ae,a,Xe,Vt,Tt]),n.useEffect(()=>{const s=k.current;if(typeof IntersectionObserver=="undefined"||!s||!ae||!J)return;const f=new IntersectionObserver(()=>{Xt(!!s.offsetParent)});return f.observe(s),()=>{f.disconnect()}},[ae,J]);let he={};Z.tooltip===!0?he={title:(r=w.text)!==null&&r!==void 0?r:a}:n.isValidElement(Z.tooltip)?he={title:Z.tooltip}:typeof Z.tooltip=="object"?he=Object.assign({title:(t=w.text)!==null&&t!==void 0?t:a},Z.tooltip):he={title:Z.tooltip};const Ge=n.useMemo(()=>{const s=f=>["string","number"].includes(typeof f);if(!(!be||ae)){if(s(w.text))return w.text;if(s(a))return a;if(s(W))return W;if(s(he.title))return he.title}},[be,ae,W,he.title,St]);if(se)return n.createElement(Re,{value:(l=w.text)!==null&&l!==void 0?l:typeof a=="string"?a:"",onSave:U,onCancel:_,onEnd:w.onEnd,prefixCls:V,className:C,style:M,direction:fe,component:Y,maxLength:w.maxLength,autoSize:w.autoSize,enterIcon:w.enterIcon});const nn=()=>{const{expandable:s,symbol:f}=Z;return!s||oe&&s!=="collapsible"?null:n.createElement("a",{key:"expand",className:`${V}-${oe?"collapse":"expand"}`,onClick:te=>Yt(te,{expanded:!oe}),"aria-label":oe?$.collapse:$==null?void 0:$.expand},typeof f=="function"?f(oe):f)},on=()=>{if(!ie)return;const{icon:s,tooltip:f}=w,te=(0,p.Z)(f)[0]||($==null?void 0:$.edit),Ue=typeof te=="string"?te:"";return q.includes("icon")?n.createElement(H.Z,{key:"edit",title:f===!1?"":te},n.createElement(I,{ref:le,className:`${V}-edit`,onClick:pe,"aria-label":Ue},s||n.createElement(x.Z,{role:"button"}))):null},rn=()=>Ee?n.createElement(z,Object.assign({key:"copy"},we,{prefixCls:V,copied:je,locale:$,onCopy:Ht,loading:De,iconOnly:a==null})):null,ln=s=>[s&&nn(),on(),rn()],sn=s=>[s&&!oe&&n.createElement("span",{"aria-hidden":!0,key:"ellipsis"},Dt),Z.suffix,ln(s)];return n.createElement(N.Z,{onResize:en,disabled:!J},s=>n.createElement(Mt,{tooltipProps:he,enableEllipsis:J,isEllipsis:St},n.createElement(v,Object.assign({className:A()({[`${V}-${y}`]:y,[`${V}-disabled`]:d,[`${V}-ellipsis`]:be,[`${V}-single-line`]:J&&Ie===1&&!oe,[`${V}-ellipsis-single-line`]:Qt,[`${V}-ellipsis-multiple-line`]:Xe},C),prefixCls:c,style:Object.assign(Object.assign({},M),{WebkitLineClamp:Xe?Ie:void 0}),component:Y,ref:(0,ee.sQ)(s,k,o),direction:fe,onClick:q.includes("text")?pe:void 0,"aria-label":Ge==null?void 0:Ge.toString(),title:W},Me),n.createElement(Pt,{enableMeasure:J&&!ae,text:a,rows:Ie,width:Tt,onEllipsis:tn,expanded:oe,miscDeps:[je,oe,De,ie,Ee]},(f,te)=>{let Ue=f;return f.length&&te&&!oe&&Ge&&(Ue=n.createElement("span",{key:"show-content","aria-hidden":!0},Ue)),jt(e,n.createElement(n.Fragment,null,Ue,sn(te)))}))))}),It=function(e,o){var r={};for(var t in e)Object.prototype.hasOwnProperty.call(e,t)&&o.indexOf(t)<0&&(r[t]=e[t]);if(e!=null&&typeof Object.getOwnPropertySymbols=="function")for(var l=0,t=Object.getOwnPropertySymbols(e);l<t.length;l++)o.indexOf(t[l])<0&&Object.prototype.propertyIsEnumerable.call(e,t[l])&&(r[t[l]]=e[t[l]]);return r},Lt=n.forwardRef((e,o)=>{var{ellipsis:r,rel:t}=e,l=It(e,["ellipsis","rel"]);const c=Object.assign(Object.assign({},l),{rel:t===void 0&&l.target==="_blank"?"noopener noreferrer":t});return delete c.navigate,n.createElement(ze,Object.assign({},c,{ref:o,ellipsis:!!r,component:"a"}))}),$t=n.forwardRef((e,o)=>n.createElement(ze,Object.assign({ref:o},e,{component:"div"}))),Zt=function(e,o){var r={};for(var t in e)Object.prototype.hasOwnProperty.call(e,t)&&o.indexOf(t)<0&&(r[t]=e[t]);if(e!=null&&typeof Object.getOwnPropertySymbols=="function")for(var l=0,t=Object.getOwnPropertySymbols(e);l<t.length;l++)o.indexOf(t[l])<0&&Object.prototype.propertyIsEnumerable.call(e,t[l])&&(r[t[l]]=e[t[l]]);return r};const At=(e,o)=>{var{ellipsis:r}=e,t=Zt(e,["ellipsis"]);const l=n.useMemo(()=>r&&typeof r=="object"?(0,K.Z)(r,["expandable","rows"]):r,[r]);return n.createElement(ze,Object.assign({ref:o},t,{ellipsis:l,component:"span"}))};var Bt=n.forwardRef(At),Ut=function(e,o){var r={};for(var t in e)Object.prototype.hasOwnProperty.call(e,t)&&o.indexOf(t)<0&&(r[t]=e[t]);if(e!=null&&typeof Object.getOwnPropertySymbols=="function")for(var l=0,t=Object.getOwnPropertySymbols(e);l<t.length;l++)o.indexOf(t[l])<0&&Object.prototype.propertyIsEnumerable.call(e,t[l])&&(r[t[l]]=e[t[l]]);return r};const Wt=[1,2,3,4,5];var _t=n.forwardRef((e,o)=>{const{level:r=1}=e,t=Ut(e,["level"]);let l;return Wt.includes(r)?l=`h${r}`:l="h1",n.createElement(ze,Object.assign({ref:o},t,{component:l}))});const Be=v;Be.Text=Bt,Be.Link=Lt,Be.Title=_t,Be.Paragraph=$t;var Nt=Be},874:function(ce,j,i){"use strict";var n=i(16935),x={"text/plain":"Text","text/html":"Url",default:"Text"},D="Copy to clipboard: #{key}, Enter";function A(p){var m=(/mac os x/i.test(navigator.userAgent)?"\u2318":"Ctrl")+"+C";return p.replace(/#{\s*key\s*}/g,m)}function N(p,m){var T,K,ee,Q,L,u,ue=!1;m||(m={}),T=m.debug||!1;try{ee=n(),Q=document.createRange(),L=document.getSelection(),u=document.createElement("span"),u.textContent=p,u.ariaHidden="true",u.style.all="unset",u.style.position="fixed",u.style.top=0,u.style.clip="rect(0, 0, 0, 0)",u.style.whiteSpace="pre",u.style.webkitUserSelect="text",u.style.MozUserSelect="text",u.style.msUserSelect="text",u.style.userSelect="text",u.addEventListener("copy",function(I){if(I.stopPropagation(),m.format)if(I.preventDefault(),typeof I.clipboardData=="undefined"){T&&console.warn("unable to use e.clipboardData"),T&&console.warn("trying IE specific stuff"),window.clipboardData.clearData();var me=x[m.format]||x.default;window.clipboardData.setData(me,p)}else I.clipboardData.clearData(),I.clipboardData.setData(m.format,p);m.onCopy&&(I.preventDefault(),m.onCopy(I.clipboardData))}),document.body.appendChild(u),Q.selectNodeContents(u),L.addRange(Q);var We=document.execCommand("copy");if(!We)throw new Error("copy command was unsuccessful");ue=!0}catch(I){T&&console.error("unable to copy using execCommand: ",I),T&&console.warn("trying IE specific stuff");try{window.clipboardData.setData(m.format||"text",p),m.onCopy&&m.onCopy(window.clipboardData),ue=!0}catch(me){T&&console.error("unable to copy using clipboardData: ",me),T&&console.error("falling back to prompt"),K=A("message"in m?m.message:D),window.prompt(K,p)}}finally{L&&(typeof L.removeRange=="function"?L.removeRange(Q):L.removeAllRanges()),u&&document.body.removeChild(u),ee()}return ue}ce.exports=N},56391:function(ce,j,i){"use strict";i.d(j,{G:function(){return A}});var n=i(40039),x=function(p){if((0,n.Z)()&&window.document.documentElement){var m=Array.isArray(p)?p:[p],T=window.document.documentElement;return m.some(function(K){return K in T.style})}return!1},D=function(p,m){if(!x(p))return!1;var T=document.createElement("div"),K=T.style[p];return T.style[p]=m,T.style[p]!==K};function A(N,p){return!Array.isArray(N)&&p!==void 0?D(N,p):x(N)}},16935:function(ce){ce.exports=function(){var j=document.getSelection();if(!j.rangeCount)return function(){};for(var i=document.activeElement,n=[],x=0;x<j.rangeCount;x++)n.push(j.getRangeAt(x));switch(i.tagName.toUpperCase()){case"INPUT":case"TEXTAREA":i.blur();break;default:i=null;break}return j.removeAllRanges(),function(){j.type==="Caret"&&j.removeAllRanges(),j.rangeCount||n.forEach(function(D){j.addRange(D)}),i&&i.focus()}}}}]);
