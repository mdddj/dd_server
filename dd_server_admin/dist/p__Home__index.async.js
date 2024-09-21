!(function(){"use strict";var Nt=Object.defineProperty,$t=Object.defineProperties;var Dt=Object.getOwnPropertyDescriptors;var W=Object.getOwnPropertySymbols;var je=Object.prototype.hasOwnProperty,Re=Object.prototype.propertyIsEnumerable;var Pe=(p,x,a)=>x in p?Nt(p,x,{enumerable:!0,configurable:!0,writable:!0,value:a}):p[x]=a,h=(p,x)=>{for(var a in x||(x={}))je.call(x,a)&&Pe(p,a,x[a]);if(W)for(var a of W(x))Re.call(x,a)&&Pe(p,a,x[a]);return p},I=(p,x)=>$t(p,Dt(x));var S=(p,x)=>{var a={};for(var j in p)je.call(p,j)&&x.indexOf(j)<0&&(a[j]=p[j]);if(p!=null&&W)for(var j of W(p))x.indexOf(j)<0&&Re.call(p,j)&&(a[j]=p[j]);return a};(self.webpackChunk=self.webpackChunk||[]).push([[3371],{51823:function(p,x,a){a.r(x),a.d(x,{default:function(){return yt}});var j=a(90930),u=a(67294),Ie=a(46347),[Ne,V]=(0,Ie.k)({name:"CardContext",strict:!0,errorMessage:"useCardContext: `context` is undefined. Seems you forgot to wrap component within <Card />"}),Z=a(59488),$e=a(65512),Y=(0,Z.tv)({slots:{base:["flex","flex-col","relative","overflow-hidden","h-auto","outline-none","text-foreground","box-border","bg-content1",...$e.Dh],header:["flex","p-3","z-10","w-full","justify-start","items-center","shrink-0","overflow-inherit","color-inherit","subpixel-antialiased"],body:["relative","flex","flex-1","w-full","p-3","flex-auto","flex-col","place-content-inherit","align-items-inherit","h-auto","break-words","text-left","overflow-y-auto","subpixel-antialiased"],footer:["p-3","h-auto","flex","w-full","items-center","overflow-hidden","color-inherit","subpixel-antialiased"]},variants:{shadow:{none:{base:"shadow-none"},sm:{base:"shadow-small"},md:{base:"shadow-medium"},lg:{base:"shadow-large"}},radius:{none:{base:"rounded-none",header:"rounded-none",footer:"rounded-none"},sm:{base:"rounded-small",header:"rounded-t-small",footer:"rounded-b-small"},md:{base:"rounded-medium",header:"rounded-t-medium",footer:"rounded-b-medium"},lg:{base:"rounded-large",header:"rounded-t-large",footer:"rounded-b-large"}},fullWidth:{true:{base:"w-full"}},isHoverable:{true:{base:"data-[hover=true]:bg-content2 dark:data-[hover=true]:bg-content2"}},isPressable:{true:{base:"cursor-pointer"}},isBlurred:{true:{base:["bg-background/80","dark:bg-background/20","backdrop-blur-md","backdrop-saturate-150"]}},isFooterBlurred:{true:{footer:["bg-background/10","backdrop-blur","backdrop-saturate-150"]}},isDisabled:{true:{base:"opacity-disabled cursor-not-allowed"}},disableAnimation:{true:"",false:{base:"transition-transform-background motion-reduce:transition-none"}}},compoundVariants:[{isPressable:!0,class:"data-[pressed=true]:scale-[0.97] tap-highlight-transparent"}],defaultVariants:{radius:"lg",shadow:"md",fullWidth:!1,isHoverable:!1,isPressable:!1,isDisabled:!1,isFooterBlurred:!1}}),De=a(11086),F=a(37127),Se=a(2459),Fe=a(18419),Me=a(3136),Ee=a(26590),Be=a(72970),ze=a(47234);function q(t){if(!t)return;let n=!0;return e=>{let s=I(h({},e),{preventDefault(){e.preventDefault()},isDefaultPrevented(){return e.isDefaultPrevented()},stopPropagation(){console.error("stopPropagation is now the default behavior for events in React Spectrum. You can use continuePropagation() to revert this behavior.")},continuePropagation(){n=!1}});t(s),n&&e.stopPropagation()}}function He(t){return{keyboardProps:t.isDisabled?{}:{onKeyDown:q(t.onKeyDown),onKeyUp:q(t.onKeyUp)}}}let _=u.createContext(null);function ke(t){let n=(0,u.useContext)(_)||{};(0,Be.l)(n,t);let d=n,{ref:e}=d;return S(d,["ref"])}function St(t,n){let l=t,{children:e}=l,s=S(l,["children"]),d=$h8xso$useObjectRef(n),i=I(h({},s),{ref:d});return $h8xso$react.createElement(_.Provider,{value:i},e)}let Ft=null;function Oe(t,n){let{focusProps:e}=(0,ze.K)(t),{keyboardProps:s}=He(t),d=(0,F.d)(e,s),i=ke(n),l=t.isDisabled?{}:i,r=(0,u.useRef)(t.autoFocus);return(0,u.useEffect)(()=>{r.current&&n.current&&(0,Ee.e)(n.current),r.current=!1},[n]),{focusableProps:(0,F.d)(I(h({},d),{tabIndex:t.excludeFromTabOrder&&!t.isDisabled?-1:void 0}),l)}}var Te=a(66706);function Ae(t,n){let{elementType:e="button",isDisabled:s,onPress:d,onPressStart:i,onPressEnd:l,onPressChange:r,preventFocusOnPress:c,allowFocusWhenDisabled:v,onClick:f,href:b,target:R,rel:B,type:y="button",allowTextSelectionOnPress:N}=t,D;e==="button"?D={type:y,disabled:s}:D={role:"button",tabIndex:s?void 0:0,href:e==="a"&&s?void 0:b,target:e==="a"?R:void 0,type:e==="input"?y:void 0,disabled:e==="input"?s:void 0,"aria-disabled":!s||e==="input"?void 0:s,rel:e==="a"?B:void 0};let{pressProps:$,isPressed:g}=(0,Te.r)({onPressStart:i,onPressEnd:l,onPressChange:r,onPress:d,isDisabled:s,preventFocusOnPress:c,allowTextSelectionOnPress:N,ref:n}),{focusableProps:m}=Oe(t,n);v&&(m.tabIndex=s?-1:m.tabIndex);let C=(0,F.d)(m,$,(0,Me.z)(t,{labelable:!0}));return{isPressed:g,buttonProps:(0,F.d)(D,C,{"aria-haspopup":t["aria-haspopup"],"aria-expanded":t["aria-expanded"],"aria-controls":t["aria-controls"],"aria-pressed":t["aria-pressed"],onClick:P=>{f==null||f(P)}})}}var We=a(76733),M=a(15607),U=a(49869),ee=a(49037),E=a(50262),G=a(27963),K=a(33295);function Ue(t={}){const[n,e]=(0,u.useState)([]),s=(0,u.useCallback)(i=>{const l=i.currentTarget,r=Math.max(l.clientWidth,l.clientHeight),c=l.getBoundingClientRect();e(v=>[...v,{key:(0,ee.QI)(v.length.toString()),size:r,x:i.clientX-c.left-r/2,y:i.clientY-c.top-r/2}])},[]),d=(0,u.useCallback)(i=>{e(l=>l.filter(r=>r.key!==i))},[]);return h({ripples:n,onClick:s,onClear:d},t)}function Ge(t){var n,e,s,d;const i=(0,We.w)(),[l,r]=(0,M.oe)(t,Y.variantKeys),Ce=l,{ref:c,as:v,children:f,onClick:b,onPress:R,autoFocus:B,className:y,classNames:N,allowTextSelectionOnPress:D=!0}=Ce,$=S(Ce,["ref","as","children","onClick","onPress","autoFocus","className","classNames","allowTextSelectionOnPress"]),g=(0,K.gy)(c),m=v||(t.isPressable?"button":"div"),C=typeof m=="string",P=(e=(n=t.disableAnimation)!=null?n:i==null?void 0:i.disableAnimation)!=null?e:!1,z=(d=(s=t.disableRipple)!=null?s:i==null?void 0:i.disableRipple)!=null?d:!1,T=(0,U.W)(N==null?void 0:N.base,y),{onClick:Ct,onClear:me,ripples:pe}=Ue(),xe=X=>{!P&&!z&&g.current&&Ct(X)},{buttonProps:be,isPressed:L}=Ae(h({onPress:R,elementType:v,isDisabled:!t.isPressable,onClick:(0,De.t)(b,xe),allowTextSelectionOnPress:D},$),g),{hoverProps:ge,isHovered:J}=(0,Fe.X)(h({isDisabled:!t.isHoverable},$)),{isFocusVisible:Q,isFocused:Pt,focusProps:ye}=(0,Se.F)({autoFocus:B}),A=(0,u.useMemo)(()=>Y(I(h({},r),{disableAnimation:P})),[(0,ee.Xx)(r),P]),jt=(0,u.useMemo)(()=>({slots:A,classNames:N,disableAnimation:P,isDisabled:t.isDisabled,isFooterBlurred:t.isFooterBlurred,fullWidth:t.fullWidth}),[A,N,t.isDisabled,t.isFooterBlurred,P,t.fullWidth]),Rt=(0,u.useCallback)((X={})=>h({ref:g,className:A.base({class:T}),tabIndex:t.isPressable?0:-1,"data-hover":(0,E.PB)(J),"data-pressed":(0,E.PB)(L),"data-focus":(0,E.PB)(Pt),"data-focus-visible":(0,E.PB)(Q),"data-disabled":(0,E.PB)(t.isDisabled)},(0,F.d)(t.isPressable?I(h(h({},be),ye),{role:"button"}):{},t.isHoverable?ge:{},(0,G.z)($,{enabled:C}),(0,G.z)(X))),[g,A,T,C,t.isPressable,t.isHoverable,t.isDisabled,J,L,Q,be,ye,ge,$]),It=(0,u.useCallback)(()=>({ripples:pe,onClear:me}),[pe,me]);return{context:jt,domRef:g,Component:m,classNames:N,children:f,isHovered:J,isPressed:L,disableAnimation:P,isPressable:t.isPressable,isHoverable:t.isHoverable,disableRipple:z,handleClick:xe,isFocusVisible:Q,getCardProps:Rt,getRippleProps:It}}var Ke=a(18522),we=a(97642),o=a(85893),Le=a(240),te=a(96681),Je=a(16014);class Qe extends u.Component{getSnapshotBeforeUpdate(n){const e=this.props.childRef.current;if(e&&n.isPresent&&!this.props.isPresent){const s=this.props.sizeRef.current;s.height=e.offsetHeight||0,s.width=e.offsetWidth||0,s.top=e.offsetTop,s.left=e.offsetLeft}return null}componentDidUpdate(){}render(){return this.props.children}}function Xe({children:t,isPresent:n}){const e=(0,u.useId)(),s=(0,u.useRef)(null),d=(0,u.useRef)({width:0,height:0,top:0,left:0}),{nonce:i}=(0,u.useContext)(Je._);return(0,u.useInsertionEffect)(()=>{const{width:l,height:r,top:c,left:v}=d.current;if(n||!s.current||!l||!r)return;s.current.dataset.motionPopId=e;const f=document.createElement("style");return i&&(f.nonce=i),document.head.appendChild(f),f.sheet&&f.sheet.insertRule(`
          [data-motion-pop-id="${e}"] {
            position: absolute !important;
            width: ${l}px !important;
            height: ${r}px !important;
            top: ${c}px !important;
            left: ${v}px !important;
          }
        `),()=>{document.head.removeChild(f)}},[n]),(0,o.jsx)(Qe,{isPresent:n,childRef:s,sizeRef:d,children:u.cloneElement(t,{ref:s})})}const Ve=({children:t,initial:n,isPresent:e,onExitComplete:s,custom:d,presenceAffectsLayout:i,mode:l})=>{const r=(0,te.h)(Ze),c=(0,u.useId)(),v=(0,u.useMemo)(()=>({id:c,initial:n,isPresent:e,custom:d,onExitComplete:f=>{r.set(f,!0);for(const b of r.values())if(!b)return;s&&s()},register:f=>(r.set(f,!1),()=>r.delete(f))}),i?[Math.random()]:[e]);return(0,u.useMemo)(()=>{r.forEach((f,b)=>r.set(b,!1))},[e]),u.useEffect(()=>{!e&&!r.size&&s&&s()},[e]),l==="popLayout"&&(t=(0,o.jsx)(Xe,{isPresent:e,children:t})),(0,o.jsx)(Le.O.Provider,{value:v,children:t})};function Ze(){return new Map}var Ye=a(25364),qe=a(45487);const H=t=>t.key||"";function ne(t){const n=[];return u.Children.forEach(t,e=>{(0,u.isValidElement)(e)&&n.push(e)}),n}var _e=a(58868);const et=({children:t,exitBeforeEnter:n,custom:e,initial:s=!0,onExitComplete:d,presenceAffectsLayout:i=!0,mode:l="sync"})=>{(0,qe.k)(!n,"Replace exitBeforeEnter with mode='wait'");const r=(0,u.useMemo)(()=>ne(t),[t]),c=r.map(H),v=(0,u.useRef)(!0),f=(0,u.useRef)(r),b=(0,te.h)(()=>new Map),[R,B]=(0,u.useState)(r),[y,N]=(0,u.useState)(r);(0,_e.L)(()=>{v.current=!1,f.current=r;for(let g=0;g<y.length;g++){const m=H(y[g]);c.includes(m)?b.delete(m):b.get(m)!==!0&&b.set(m,!1)}},[y,c.length,c.join("-")]);const D=[];if(r!==R){let g=[...r];for(let m=0;m<y.length;m++){const C=y[m],P=H(C);c.includes(P)||(g.splice(m,0,C),D.push(C))}l==="wait"&&D.length&&(g=D),N(ne(g)),B(r);return}const{forceRender:$}=(0,u.useContext)(Ye.p);return(0,o.jsx)(o.Fragment,{children:y.map(g=>{const m=H(g),C=r===y||c.includes(m),P=()=>{if(b.has(m))b.set(m,!0);else return;let z=!0;b.forEach(T=>{T||(z=!1)}),z&&($==null||$(),N(f.current),d&&d())};return(0,o.jsx)(Ve,{isPresent:C,initial:!v.current||s?void 0:!1,custom:C?void 0:e,presenceAffectsLayout:i,mode:l,onExitComplete:C?void 0:P,children:g},m)})})};var tt=a(80663);function Mt(t,n){const e=n-t+1;return Array.from({length:e},(s,d)=>d+t)}function nt(t,n,e){return Math.min(Math.max(t,n),e)}function Et(t,n=100){return Math.min(Math.max(t,0),n)}var se=t=>{const{ripples:n=[],motionProps:e,color:s="currentColor",style:d,onClear:i}=t;return(0,o.jsx)(o.Fragment,{children:n.map(l=>{const r=nt(.01*l.size,.2,l.size>100?.75:.5);return(0,o.jsx)(Ke.X,{features:we.H,children:(0,o.jsx)(et,{mode:"popLayout",children:(0,o.jsx)(tt.m.span,h({animate:{transform:"scale(2)",opacity:0},className:"nextui-ripple",exit:{opacity:0},initial:{transform:"scale(0)",opacity:.35},style:h({position:"absolute",backgroundColor:s,borderRadius:"100%",transformOrigin:"center",pointerEvents:"none",overflow:"hidden",inset:0,zIndex:0,top:l.y,left:l.x,width:`${l.size}px`,height:`${l.size}px`},d),transition:{duration:r},onAnimationComplete:()=>{i(l.key)}},e))})},l.key)})})};se.displayName="NextUI.Ripple";var st=se,ae=(0,M.Gp)((t,n)=>{const{children:e,context:s,Component:d,isPressable:i,disableAnimation:l,disableRipple:r,getCardProps:c,getRippleProps:v}=Ge(I(h({},t),{ref:n}));return(0,o.jsxs)(d,I(h({},c()),{children:[(0,o.jsx)(Ne,{value:s,children:e}),i&&!l&&!r&&(0,o.jsx)(st,h({},v()))]}))});ae.displayName="NextUI.Card";var oe=ae,re=(0,M.Gp)((t,n)=>{var e;const R=t,{as:s,className:d,children:i}=R,l=S(R,["as","className","children"]),r=s||"div",c=(0,K.gy)(n),{slots:v,classNames:f}=V(),b=(0,U.W)(f==null?void 0:f.header,d);return(0,o.jsx)(r,I(h({ref:c,className:(e=v.header)==null?void 0:e.call(v,{class:b})},l),{children:i}))});re.displayName="NextUI.CardHeader";var le=re;function at(t){let n=(0,G.z)(t,{enabled:typeof t.elementType=="string"}),e;return t.orientation==="vertical"&&(e="vertical"),t.elementType!=="hr"?{separatorProps:I(h({},n),{role:"separator","aria-orientation":e})}:{separatorProps:n}}var ot=(0,Z.tv)({base:"shrink-0 bg-divider border-none",variants:{orientation:{horizontal:"w-full h-divider",vertical:"h-full w-divider"}},defaultVariants:{orientation:"horizontal"}});function rt(t){const v=t,{as:n,className:e,orientation:s}=v,d=S(v,["as","className","orientation"]);let i=n||"hr";i==="hr"&&s==="vertical"&&(i="div");const{separatorProps:l}=at({elementType:typeof i=="string"?i:"hr",orientation:s}),r=(0,u.useMemo)(()=>ot({orientation:s,className:e}),[s,e]),c=(0,u.useCallback)((f={})=>h(h(h({className:r,role:"separator","data-orientation":s},l),d),f),[r,s,l,d]);return{Component:i,getDividerProps:c}}var ie=(0,M.Gp)((t,n)=>{const{Component:e,getDividerProps:s}=rt(h({},t));return(0,o.jsx)(e,h({ref:n},s()))});ie.displayName="NextUI.Divider";var de=ie,ue=(0,M.Gp)((t,n)=>{var e;const R=t,{as:s,className:d,children:i}=R,l=S(R,["as","className","children"]),r=s||"div",c=(0,K.gy)(n),{slots:v,classNames:f}=V(),b=(0,U.W)(f==null?void 0:f.body,d);return(0,o.jsx)(r,I(h({ref:c,className:(e=v.body)==null?void 0:e.call(v,{class:b})},l),{children:i}))});ue.displayName="NextUI.CardBody";var ce=ue,lt=a(50749),it=a(74330),dt=a(40411),ut=a(68864),fe=a(37676),k=function(n){return(0,o.jsxs)("div",{className:"flex flex-row gap-2",children:[(0,o.jsx)("div",{className:"font-bold w-32",children:n.title}),(0,o.jsx)("div",{children:n.value})]})},ct=function(){var n=(0,lt.Z)(),e=n.lastJsonMessage,s=n.sendMessage,d=n.readyText,i=n.data,l=n.loading,r=n.readyState;(0,u.useEffect)(function(){r===fe.kQ.OPEN&&setInterval(function(){s("Memory")},2e3)},[r]);var c=e!=null?e:i;return(0,o.jsxs)(oe,{isBlurred:!0,children:[(0,o.jsxs)(le,{className:"flex flex-row justify-between",children:[(0,o.jsx)("h4",{className:"font-bold text-lg",children:"\u5185\u5B58\u76D1\u542C"}),(0,o.jsxs)("span",{children:[l&&(0,o.jsx)(it.Z,{}),r===fe.kQ.OPEN&&(0,o.jsx)(dt.Z,{status:"success"})," ",d]})]}),(0,o.jsx)(de,{}),(0,o.jsx)(ce,{children:c&&(0,o.jsxs)("div",{className:"flex flex-col gap-2 relative",children:[(0,o.jsx)(k,{title:"\u603B\u5185\u5B58",value:c.totalString}),(0,o.jsx)(k,{title:"\u5DF2\u4F7F\u7528",value:c.useString}),(0,o.jsx)(k,{title:"\u53EF\u7528",value:c.availableString}),(0,o.jsx)(k,{title:"\u4F7F\u7528\u7387",value:Math.floor(c.usageRate*100)+"%"}),(0,o.jsx)("div",{className:"absolute right-2 overflow-y-hidden bg-green h-full",children:(0,o.jsx)(ut.Z,{percent:Math.floor(c.usageRate*100),type:"dashboard"})})]})})]})},ft=ct,vt=a(15009),ve=a.n(vt),ht=a(99289),mt=a.n(ht),he=a(50942);function pt(){return w.apply(this,arguments)}function w(){return w=mt()(ve()().mark(function t(){return ve()().wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return e.abrupt("return",(0,he.request)("/system/info/get",{method:"GET"}));case 1:case"end":return e.stop()}},t)})),w.apply(this,arguments)}var xt=function(){var n=(0,he.useRequest)(function(){return pt()}),e=n.data,s=n.loading;return(0,o.jsxs)(oe,{children:[(0,o.jsx)(le,{children:(0,o.jsx)("h4",{className:"font-bold text-lg",children:"\u7CFB\u7EDF\u4FE1\u606F"})}),(0,o.jsx)(de,{}),(0,o.jsxs)(ce,{children:[s&&(0,o.jsx)("div",{children:"\u52A0\u8F7D\u4E2D"}),e&&(0,o.jsxs)("div",{className:"flex flex-col gap-2",children:[(0,o.jsx)(O,{title:"java\u7248\u672C",value:e.javaInfo.version+"(".concat(e==null?void 0:e.javaSpecInfo.vendor,")")}),(0,o.jsx)(O,{title:"jvm",value:"".concat(e==null?void 0:e.jvmInfo.name)}),(0,o.jsx)(O,{title:"\u7CFB\u7EDF\u7C7B\u578B",value:"".concat(e==null?void 0:e.osInfo.name," ").concat(e==null?void 0:e.osInfo.version," ").concat(e==null?void 0:e.osInfo.arch)}),(0,o.jsx)(O,{title:"\u4E3B\u673A\u540D\u79F0",value:"".concat(e==null?void 0:e.userInfo.name)})]})]})]})},O=function(n){return(0,o.jsxs)("div",{className:"flex flex-row gap-2",children:[(0,o.jsx)("div",{className:"font-bold w-32",children:n.title}),(0,o.jsx)("div",{children:n.value})]})},bt=xt,gt=function(){return(0,o.jsx)(j._z,{ghost:!0,children:(0,o.jsxs)("div",{className:"grid gap-4 grid-cols-2",children:[(0,o.jsx)(ft,{}),(0,o.jsx)(bt,{})]})})},yt=gt}}]);
}());