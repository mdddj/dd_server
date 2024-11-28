!(function(){"use strict";var rr=Object.defineProperty,nr=Object.defineProperties;var or=Object.getOwnPropertyDescriptors;var Le=Object.getOwnPropertySymbols;var ct=Object.prototype.hasOwnProperty,ft=Object.prototype.propertyIsEnumerable;var dt=(x,m,i)=>m in x?rr(x,m,{enumerable:!0,configurable:!0,writable:!0,value:i}):x[m]=i,$=(x,m)=>{for(var i in m||(m={}))ct.call(m,i)&&dt(x,i,m[i]);if(Le)for(var i of Le(m))ft.call(m,i)&&dt(x,i,m[i]);return x},w=(x,m)=>nr(x,or(m));var Te=(x,m)=>{var i={};for(var u in x)ct.call(x,u)&&m.indexOf(u)<0&&(i[u]=x[u]);if(x!=null&&Le)for(var u of Le(x))m.indexOf(u)<0&&ft.call(x,u)&&(i[u]=x[u]);return i};(self.webpackChunk=self.webpackChunk||[]).push([[4336],{50335:function(x,m,i){i.d(m,{Z:function(){return H}});var u=i(1413),L=i(91),S=i(67294),k=i(66758),Q=i(70715),te=i(85893),ge=["proFieldProps","fieldProps"],ue="date",I=S.forwardRef(function(g,ne){var G=g.proFieldProps,oe=g.fieldProps,Y=(0,L.Z)(g,ge),O=(0,S.useContext)(k.Z);return(0,te.jsx)(Q.Z,(0,u.Z)({ref:ne,valueType:ue,fieldProps:(0,u.Z)({getPopupContainer:O.getPopupContainer},oe),proFieldProps:G,filedConfig:{valueType:ue,customLightMode:!0}},Y))}),F=I,be=["proFieldProps","fieldProps"],Z="dateMonth",M=S.forwardRef(function(g,ne){var G=g.proFieldProps,oe=g.fieldProps,Y=(0,L.Z)(g,be),O=(0,S.useContext)(k.Z);return(0,te.jsx)(Q.Z,(0,u.Z)({ref:ne,valueType:Z,fieldProps:(0,u.Z)({getPopupContainer:O.getPopupContainer},oe),proFieldProps:G,filedConfig:{valueType:Z,customLightMode:!0}},Y))}),de=M,ae=["fieldProps"],D="dateQuarter",ce=S.forwardRef(function(g,ne){var G=g.fieldProps,oe=(0,L.Z)(g,ae),Y=(0,S.useContext)(k.Z);return(0,te.jsx)(Q.Z,(0,u.Z)({ref:ne,valueType:D,fieldProps:(0,u.Z)({getPopupContainer:Y.getPopupContainer},G),filedConfig:{valueType:D,customLightMode:!0}},oe))}),j=ce,C=["proFieldProps","fieldProps"],f="dateWeek",q=S.forwardRef(function(g,ne){var G=g.proFieldProps,oe=g.fieldProps,Y=(0,L.Z)(g,C),O=(0,S.useContext)(k.Z);return(0,te.jsx)(Q.Z,(0,u.Z)({ref:ne,valueType:f,fieldProps:(0,u.Z)({getPopupContainer:O.getPopupContainer},oe),proFieldProps:G,filedConfig:{valueType:f,customLightMode:!0}},Y))}),R=q,se=["proFieldProps","fieldProps"],re="dateYear",B=S.forwardRef(function(g,ne){var G=g.proFieldProps,oe=g.fieldProps,Y=(0,L.Z)(g,se),O=(0,S.useContext)(k.Z);return(0,te.jsx)(Q.Z,(0,u.Z)({ref:ne,valueType:re,fieldProps:(0,u.Z)({getPopupContainer:O.getPopupContainer},oe),proFieldProps:G,filedConfig:{valueType:re,customLightMode:!0}},Y))}),U=B,A=F;A.Week=R,A.Month=de,A.Quarter=j,A.Year=U,A.displayName="ProFormComponent";var H=A},5966:function(x,m,i){var u=i(97685),L=i(1413),S=i(91),k=i(21770),Q=i(9383),te=i(55241),ge=i(97435),ue=i(67294),I=i(70715),F=i(85893),be=["fieldProps","proFieldProps"],Z=["fieldProps","proFieldProps"],M="text",de=function(C){var f=C.fieldProps,q=C.proFieldProps,R=(0,S.Z)(C,be);return(0,F.jsx)(I.Z,(0,L.Z)({valueType:M,fieldProps:f,filedConfig:{valueType:M},proFieldProps:q},R))},ae=function(C){var f=(0,k.Z)(C.open||!1,{value:C.open,onChange:C.onOpenChange}),q=(0,u.Z)(f,2),R=q[0],se=q[1];return(0,F.jsx)(Q.Z.Item,{shouldUpdate:!0,noStyle:!0,children:function(B){var U,A=B.getFieldValue(C.name||[]);return(0,F.jsx)(te.Z,(0,L.Z)((0,L.Z)({getPopupContainer:function(g){return g&&g.parentNode?g.parentNode:g},onOpenChange:function(g){return se(g)},content:(0,F.jsxs)("div",{style:{padding:"4px 0"},children:[(U=C.statusRender)===null||U===void 0?void 0:U.call(C,A),C.strengthText?(0,F.jsx)("div",{style:{marginTop:10},children:(0,F.jsx)("span",{children:C.strengthText})}):null]}),overlayStyle:{width:240},placement:"rightTop"},C.popoverProps),{},{open:R,children:C.children}))}})},D=function(C){var f=C.fieldProps,q=C.proFieldProps,R=(0,S.Z)(C,Z),se=(0,ue.useState)(!1),re=(0,u.Z)(se,2),B=re[0],U=re[1];return f!=null&&f.statusRender&&R.name?(0,F.jsx)(ae,{name:R.name,statusRender:f==null?void 0:f.statusRender,popoverProps:f==null?void 0:f.popoverProps,strengthText:f==null?void 0:f.strengthText,open:B,onOpenChange:U,children:(0,F.jsx)("div",{children:(0,F.jsx)(I.Z,(0,L.Z)({valueType:"password",fieldProps:(0,L.Z)((0,L.Z)({},(0,ge.Z)(f,["statusRender","popoverProps","strengthText"])),{},{onBlur:function(H){var g;f==null||(g=f.onBlur)===null||g===void 0||g.call(f,H),U(!1)},onClick:function(H){var g;f==null||(g=f.onClick)===null||g===void 0||g.call(f,H),U(!0)}}),proFieldProps:q,filedConfig:{valueType:M}},R))})}):(0,F.jsx)(I.Z,(0,L.Z)({valueType:"password",fieldProps:f,proFieldProps:q,filedConfig:{valueType:M}},R))},ce=de;ce.Password=D,ce.displayName="ProFormComponent",m.Z=ce},19708:function(x,m,i){i.d(m,{i:function(){return Ht}});var u=i(67294),L=i(76733),S=i(15607),k=i(50262);function Q(e,n){if(e!=null){if((0,k.mf)(e)){e(n);return}try{e.current=n}catch(r){throw new Error(`Cannot assign value '${n}' to ref '${e}'`)}}}function te(...e){return n=>{e.forEach(r=>Q(r,n))}}var ge=i(93387),ue=i(18419),I=i(66706),F=i(59488),be=i(65512),Z=(0,F.tv)({slots:{base:"group relative max-w-fit inline-flex items-center justify-start cursor-pointer touch-none tap-highlight-transparent select-none",wrapper:["px-1","relative","inline-flex","items-center","justify-start","flex-shrink-0","overflow-hidden","bg-default-200","rounded-full",...be.ID],thumb:["z-10","flex","items-center","justify-center","bg-white","shadow-small","rounded-full","origin-right"],startContent:"z-0 absolute left-1.5 rtl:right-1.5 rtl:left-[unset] text-current",endContent:"z-0 absolute right-1.5 rtl:left-1.5 rtl:right-[unset] text-default-600",thumbIcon:"text-black",label:"relative text-foreground select-none"},variants:{color:{default:{wrapper:["group-data-[selected=true]:bg-default-400","group-data-[selected=true]:text-default-foreground"]},primary:{wrapper:["group-data-[selected=true]:bg-primary","group-data-[selected=true]:text-primary-foreground"]},secondary:{wrapper:["group-data-[selected=true]:bg-secondary","group-data-[selected=true]:text-secondary-foreground"]},success:{wrapper:["group-data-[selected=true]:bg-success","group-data-[selected=true]:text-success-foreground"]},warning:{wrapper:["group-data-[selected=true]:bg-warning","group-data-[selected=true]:text-warning-foreground"]},danger:{wrapper:["group-data-[selected=true]:bg-danger","data-[selected=true]:text-danger-foreground"]}},size:{sm:{wrapper:"w-10 h-6 mr-2 rtl:ml-2 rtl:mr-[unset]",thumb:["w-4 h-4 text-tiny","group-data-[selected=true]:ml-4 rtl:group-data-[selected=true]:ml-0 rtl:group-data-[selected=true]:mr-4"],endContent:"text-tiny",startContent:"text-tiny",label:"text-small"},md:{wrapper:"w-12 h-7 mr-2 rtl:ml-2 rtl:mr-[unset]",thumb:["w-5 h-5 text-small","group-data-[selected=true]:ml-5 rtl:group-data-[selected=true]:ml-0 rtl:group-data-[selected=true]:mr-5"],endContent:"text-small",startContent:"text-small",label:"text-medium"},lg:{wrapper:"w-14 h-8 mr-2 rtl:ml-2 rtl:mr-[unset]",thumb:["w-6 h-6 text-medium","group-data-[selected=true]:ml-6 rtl:group-data-[selected=true]:ml-0 rtl:group-data-[selected=true]:mr-6"],endContent:"text-medium",startContent:"text-medium",label:"text-large"}},isDisabled:{true:{base:"opacity-disabled pointer-events-none"}},disableAnimation:{true:{wrapper:"transition-none",thumb:"transition-none"},false:{wrapper:"transition-background",thumb:"transition-all",startContent:["opacity-0","scale-50","transition-transform-opacity","group-data-[selected=true]:scale-100","group-data-[selected=true]:opacity-100"],endContent:["opacity-100","transition-transform-opacity","group-data-[selected=true]:translate-x-3","group-data-[selected=true]:opacity-0"]}}},defaultVariants:{color:"primary",size:"md",isDisabled:!1},compoundVariants:[{disableAnimation:!1,size:"sm",class:{thumb:["group-data-[pressed=true]:w-5","group-data-[selected]:group-data-[pressed]:ml-3"]}},{disableAnimation:!1,size:"md",class:{thumb:["group-data-[pressed=true]:w-6","group-data-[selected]:group-data-[pressed]:ml-4"]}},{disableAnimation:!1,size:"lg",class:{thumb:["group-data-[pressed=true]:w-7","group-data-[selected]:group-data-[pressed]:ml-5"]}}]}),M=i(37127),de=i(11086),ae=i(49037),D=i(49869),ce=i(3136),j=i(61718);function C(e,n,r){let a=(0,u.useRef)(n),l=(0,j.i)(()=>{r&&r(a.current)});(0,u.useEffect)(()=>{var p;let c=e==null||(p=e.current)===null||p===void 0?void 0:p.form;return c==null||c.addEventListener("reset",l),()=>{c==null||c.removeEventListener("reset",l)}},[e,l])}var f=i(78837),q=i(85007),R=i(77604),se=i(68806),re=i(26301);let B=null,U=new Set,A=new Map,H=!1,g=!1;const ne={Tab:!0,Escape:!0};function G(e,n){for(let r of U)r(e,n)}function oe(e){return!(e.metaKey||!(0,se.V5)()&&e.altKey||e.ctrlKey||e.key==="Control"||e.key==="Shift"||e.key==="Meta")}function Y(e){H=!0,oe(e)&&(B="keyboard",G("keyboard",e))}function O(e){B="pointer",(e.type==="mousedown"||e.type==="pointerdown")&&(H=!0,G("pointer",e))}function We(e){(0,re.Z)(e)&&(H=!0,B="virtual")}function Ze(e){e.target===window||e.target===document||(!H&&!g&&(B="virtual",G("virtual",e)),H=!1,g=!1)}function je(){H=!1,g=!0}function Se(e){if(typeof window=="undefined"||A.get((0,f.k)(e)))return;const n=(0,f.k)(e),r=(0,f.r)(e);let a=n.HTMLElement.prototype.focus;n.HTMLElement.prototype.focus=function(){H=!0,a.apply(this,arguments)},r.addEventListener("keydown",Y,!0),r.addEventListener("keyup",Y,!0),r.addEventListener("click",We,!0),n.addEventListener("focus",Ze,!0),n.addEventListener("blur",je,!1),typeof PointerEvent!="undefined"?(r.addEventListener("pointerdown",O,!0),r.addEventListener("pointermove",O,!0),r.addEventListener("pointerup",O,!0)):(r.addEventListener("mousedown",O,!0),r.addEventListener("mousemove",O,!0),r.addEventListener("mouseup",O,!0)),n.addEventListener("beforeunload",()=>{Be(e)},{once:!0}),A.set(n,{focus:a})}const Be=(e,n)=>{const r=(0,f.k)(e),a=(0,f.r)(e);n&&a.removeEventListener("DOMContentLoaded",n),A.has(r)&&(r.HTMLElement.prototype.focus=A.get(r).focus,a.removeEventListener("keydown",Y,!0),a.removeEventListener("keyup",Y,!0),a.removeEventListener("click",We,!0),r.removeEventListener("focus",Ze,!0),r.removeEventListener("blur",je,!1),typeof PointerEvent!="undefined"?(a.removeEventListener("pointerdown",O,!0),a.removeEventListener("pointermove",O,!0),a.removeEventListener("pointerup",O,!0)):(a.removeEventListener("mousedown",O,!0),a.removeEventListener("mousemove",O,!0),a.removeEventListener("mouseup",O,!0)),A.delete(r))};function pt(e){const n=(0,f.r)(e);let r;return n.readyState!=="loading"?Se(e):(r=()=>{Se(e)},n.addEventListener("DOMContentLoaded",r)),()=>Be(e,r)}typeof document!="undefined"&&pt();function Ue(){return B!=="pointer"}function vt(){return B}function ar(e){B=e,G(e,null)}function sr(){Se();let[e,n]=$28AnR$useState(B);return $28AnR$useEffect(()=>{let r=()=>{n(B)};return U.add(r),()=>{U.delete(r)}},[]),$28AnR$useIsSSR()?null:e}const gt=new Set(["checkbox","radio","range","color","file","image","button","submit","reset"]);function Pt(e,n,r){var a;const l=typeof window!="undefined"?$28AnR$getOwnerWindow(r==null?void 0:r.target).HTMLInputElement:HTMLInputElement,p=typeof window!="undefined"?$28AnR$getOwnerWindow(r==null?void 0:r.target).HTMLTextAreaElement:HTMLTextAreaElement,c=typeof window!="undefined"?$28AnR$getOwnerWindow(r==null?void 0:r.target).HTMLElement:HTMLElement,T=typeof window!="undefined"?$28AnR$getOwnerWindow(r==null?void 0:r.target).KeyboardEvent:KeyboardEvent;return e=e||(r==null?void 0:r.target)instanceof l&&!gt.has(r==null||(a=r.target)===null||a===void 0?void 0:a.type)||(r==null?void 0:r.target)instanceof p||(r==null?void 0:r.target)instanceof c&&(r==null?void 0:r.target.isContentEditable),!(e&&n==="keyboard"&&r instanceof T&&!ne[r.key])}function ir(e={}){let{isTextInput:n,autoFocus:r}=e,[a,l]=$28AnR$useState(r||Ue());return bt(p=>{l(p)},[n],{isTextInput:n}),{isFocusVisible:a}}function bt(e,n,r){Se(),$28AnR$useEffect(()=>{let a=(l,p)=>{Pt(!!(r!=null&&r.isTextInput),l,p)&&e(Ue())};return U.add(a),()=>{U.delete(a)}},n)}function mt(e){const n=(0,f.r)(e);if(vt()==="virtual"){let r=n.activeElement;(0,q.Q)(()=>{n.activeElement===r&&e.isConnected&&(0,R.A)(e)})}else(0,R.A)(e)}var He=i(72970),ht=i(69786);class yt{isDefaultPrevented(){return this.nativeEvent.defaultPrevented}preventDefault(){this.defaultPrevented=!0,this.nativeEvent.preventDefault()}stopPropagation(){this.nativeEvent.stopPropagation(),this.isPropagationStopped=()=>!0}isPropagationStopped(){return!1}persist(){}constructor(n,r){this.nativeEvent=r,this.target=r.target,this.currentTarget=r.currentTarget,this.relatedTarget=r.relatedTarget,this.bubbles=r.bubbles,this.cancelable=r.cancelable,this.defaultPrevented=r.defaultPrevented,this.eventPhase=r.eventPhase,this.isTrusted=r.isTrusted,this.timeStamp=r.timeStamp,this.type=n}}function Tt(e){let n=(0,u.useRef)({isFocused:!1,observer:null});(0,ht.b)(()=>{const a=n.current;return()=>{a.observer&&(a.observer.disconnect(),a.observer=null)}},[]);let r=(0,j.i)(a=>{e==null||e(a)});return(0,u.useCallback)(a=>{if(a.target instanceof HTMLButtonElement||a.target instanceof HTMLInputElement||a.target instanceof HTMLTextAreaElement||a.target instanceof HTMLSelectElement){n.current.isFocused=!0;let l=a.target,p=c=>{n.current.isFocused=!1,l.disabled&&r(new yt("blur",c)),n.current.observer&&(n.current.observer.disconnect(),n.current.observer=null)};l.addEventListener("focusout",p,{once:!0}),n.current.observer=new MutationObserver(()=>{if(n.current.isFocused&&l.disabled){var c;(c=n.current.observer)===null||c===void 0||c.disconnect();let T=l===document.activeElement?null:document.activeElement;l.dispatchEvent(new FocusEvent("blur",{relatedTarget:T})),l.dispatchEvent(new FocusEvent("focusout",{bubbles:!0,relatedTarget:T}))}}),n.current.observer.observe(l,{attributes:!0,attributeFilter:["disabled"]})}},[r])}function $t(e){let{isDisabled:n,onFocus:r,onBlur:a,onFocusChange:l}=e;const p=(0,u.useCallback)(h=>{if(h.target===h.currentTarget)return a&&a(h),l&&l(!1),!0},[a,l]),c=Tt(p),T=(0,u.useCallback)(h=>{const ie=(0,f.r)(h.target);h.target===h.currentTarget&&ie.activeElement===h.target&&(r&&r(h),l&&l(!0),c(h))},[l,r,c]);return{focusProps:{onFocus:!n&&(r||l||a)?T:void 0,onBlur:!n&&(a||l)?p:void 0}}}function Ne(e){if(!e)return;let n=!0;return r=>{let a=w($({},r),{preventDefault(){r.preventDefault()},isDefaultPrevented(){return r.isDefaultPrevented()},stopPropagation(){console.error("stopPropagation is now the default behavior for events in React Spectrum. You can use continuePropagation() to revert this behavior.")},continuePropagation(){n=!1}});e(a),n&&r.stopPropagation()}}function Et(e){return{keyboardProps:e.isDisabled?{}:{onKeyDown:Ne(e.onKeyDown),onKeyUp:Ne(e.onKeyUp)}}}let Ve=u.createContext(null);function xt(e){let n=(0,u.useContext)(Ve)||{};(0,He.l)(n,e);let l=n,{ref:r}=l;return Te(l,["ref"])}function lr(e,n){let c=e,{children:r}=c,a=Te(c,["children"]),l=$h8xso$useObjectRef(n),p=w($({},a),{ref:l});return $h8xso$react.createElement(Ve.Provider,{value:p},r)}let ur=null;function Ct(e,n){let{focusProps:r}=$t(e),{keyboardProps:a}=Et(e),l=(0,M.d)(r,a),p=xt(n),c=e.isDisabled?{}:p,T=(0,u.useRef)(e.autoFocus);return(0,u.useEffect)(()=>{T.current&&n.current&&mt(n.current),T.current=!1},[n]),{focusableProps:(0,M.d)(w($({},l),{tabIndex:e.excludeFromTabOrder&&!e.isDisabled?-1:void 0}),c)}}let $e="default",De="",Me=new WeakMap;function Ge(e){if((0,se.gn)()){if($e==="default"){const n=(0,f.r)(e);De=n.documentElement.style.webkitUserSelect,n.documentElement.style.webkitUserSelect="none"}$e="disabled"}else(e instanceof HTMLElement||e instanceof SVGElement)&&(Me.set(e,e.style.userSelect),e.style.userSelect="none")}function Fe(e){if((0,se.gn)()){if($e!=="disabled")return;$e="restoring",setTimeout(()=>{(0,q.Q)(()=>{if($e==="restoring"){const n=(0,f.r)(e);n.documentElement.style.webkitUserSelect==="none"&&(n.documentElement.style.webkitUserSelect=De||""),De="",$e="default"}})},300)}else if((e instanceof HTMLElement||e instanceof SVGElement)&&e&&Me.has(e)){let n=Me.get(e);e.style.userSelect==="none"&&(e.style.userSelect=n),e.getAttribute("style")===""&&e.removeAttribute("style"),Me.delete(e)}}const Ye=u.createContext({register:()=>{}});Ye.displayName="PressResponderContext";var St=i(57121),Mt=i(82463),ze=i(25321),Ft=i(36116),Qe=i(32433);function Ot(e){let n=(0,u.useContext)(Ye);if(n){let r=n,{register:a}=r,l=Te(r,["register"]);e=(0,M.d)(l,e),a()}return(0,He.l)(n,e.ref),e}var Oe=new WeakMap;class we{continuePropagation(){(0,ze._)(this,Oe,!1)}get shouldStopPropagation(){return(0,St._)(this,Oe)}constructor(n,r,a){(0,Mt._)(this,Oe,{writable:!0,value:void 0}),(0,ze._)(this,Oe,!0),this.type=n,this.pointerType=r,this.target=a.currentTarget,this.shiftKey=a.shiftKey,this.metaKey=a.metaKey,this.ctrlKey=a.ctrlKey,this.altKey=a.altKey}}const Xe=Symbol("linkClicked");function Je(e){let Ce=Ot(e),{onPress:n,onPressChange:r,onPressStart:a,onPressEnd:l,onPressUp:p,isDisabled:c,isPressed:T,preventFocusOnPress:h,shouldCancelOnPointerExit:ie,allowTextSelectionOnPress:W,ref:pe}=Ce,me=Te(Ce,["onPress","onPressChange","onPressStart","onPressEnd","onPressUp","isDisabled","isPressed","preventFocusOnPress","shouldCancelOnPointerExit","allowTextSelectionOnPress","ref"]),[he,ve]=(0,u.useState)(!1),X=(0,u.useRef)({isPressed:!1,ignoreEmulatedMouseEvents:!1,ignoreClickAfterPress:!1,didFirePressStart:!1,isTriggeringEvent:!1,activePointerId:null,target:null,isOverTarget:!1,pointerType:null}),{addGlobalListener:K,removeAllGlobalListeners:_}=(0,Ft.x)(),J=(0,j.i)((t,P)=>{let V=X.current;if(c||V.didFirePressStart)return!1;let d=!0;if(V.isTriggeringEvent=!0,a){let y=new we("pressstart",P,t);a(y),d=y.shouldStopPropagation}return r&&r(!0),V.isTriggeringEvent=!1,V.didFirePressStart=!0,ve(!0),d}),v=(0,j.i)((t,P,V=!0)=>{let d=X.current;if(!d.didFirePressStart)return!1;d.ignoreClickAfterPress=!0,d.didFirePressStart=!1,d.isTriggeringEvent=!0;let y=!0;if(l){let o=new we("pressend",P,t);l(o),y=o.shouldStopPropagation}if(r&&r(!1),ve(!1),n&&V&&!c){let o=new we("press",P,t);n(o),y&&(y=o.shouldStopPropagation)}return d.isTriggeringEvent=!1,y}),le=(0,j.i)((t,P)=>{let V=X.current;if(c)return!1;if(p){V.isTriggeringEvent=!0;let d=new we("pressup",P,t);return p(d),V.isTriggeringEvent=!1,d.shouldStopPropagation}return!0}),ee=(0,j.i)(t=>{let P=X.current;P.isPressed&&P.target&&(P.isOverTarget&&P.pointerType!=null&&v(fe(P.target,t),P.pointerType,!1),P.isPressed=!1,P.isOverTarget=!1,P.activePointerId=null,P.pointerType=null,_(),W||Fe(P.target))}),N=(0,j.i)(t=>{ie&&ee(t)}),Ke=(0,u.useMemo)(()=>{let t=X.current,P={onKeyDown(d){if(Ie(d.nativeEvent,d.currentTarget)&&d.currentTarget.contains(d.target)){var y;_e(d.target,d.key)&&d.preventDefault();let o=!0;if(!t.isPressed&&!d.repeat){t.target=d.currentTarget,t.isPressed=!0,o=J(d,"keyboard");let s=d.currentTarget,E=Pe=>{Ie(Pe,s)&&!Pe.repeat&&s.contains(Pe.target)&&t.target&&le(fe(t.target,Pe),"keyboard")};K((0,f.r)(d.currentTarget),"keyup",(0,de.t)(E,V),!0)}o&&d.stopPropagation(),d.metaKey&&(0,se.V5)()&&((y=t.metaKeyEvents)===null||y===void 0||y.set(d.key,d.nativeEvent))}else d.key==="Meta"&&(t.metaKeyEvents=new Map)},onClick(d){if(!(d&&!d.currentTarget.contains(d.target))&&d&&d.button===0&&!t.isTriggeringEvent&&!Qe.nG.isOpening){let y=!0;if(c&&d.preventDefault(),!t.ignoreClickAfterPress&&!t.ignoreEmulatedMouseEvents&&!t.isPressed&&(t.pointerType==="virtual"||(0,re.Z)(d.nativeEvent))){!c&&!h&&(0,R.A)(d.currentTarget);let o=J(d,"virtual"),s=le(d,"virtual"),E=v(d,"virtual");y=o&&s&&E}t.ignoreEmulatedMouseEvents=!1,t.ignoreClickAfterPress=!1,y&&d.stopPropagation()}}},V=d=>{var y;if(t.isPressed&&t.target&&Ie(d,t.target)){var o;_e(d.target,d.key)&&d.preventDefault();let E=d.target;v(fe(t.target,d),"keyboard",t.target.contains(E)),_(),d.key!=="Enter"&&ke(t.target)&&t.target.contains(E)&&!d[Xe]&&(d[Xe]=!0,(0,Qe.nG)(t.target,d,!1)),t.isPressed=!1,(o=t.metaKeyEvents)===null||o===void 0||o.delete(d.key)}else if(d.key==="Meta"&&(!((y=t.metaKeyEvents)===null||y===void 0)&&y.size)){var s;let E=t.metaKeyEvents;t.metaKeyEvents=void 0;for(let Pe of E.values())(s=t.target)===null||s===void 0||s.dispatchEvent(new KeyboardEvent("keyup",Pe))}};if(typeof PointerEvent!="undefined"){P.onPointerDown=s=>{if(s.button!==0||!s.currentTarget.contains(s.target))return;if((0,re.c)(s.nativeEvent)){t.pointerType="virtual";return}Re(s.currentTarget)&&s.preventDefault(),t.pointerType=s.pointerType;let E=!0;t.isPressed||(t.isPressed=!0,t.isOverTarget=!0,t.activePointerId=s.pointerId,t.target=s.currentTarget,!c&&!h&&(0,R.A)(s.currentTarget),W||Ge(t.target),E=J(s,t.pointerType),K((0,f.r)(s.currentTarget),"pointermove",d,!1),K((0,f.r)(s.currentTarget),"pointerup",y,!1),K((0,f.r)(s.currentTarget),"pointercancel",o,!1)),E&&s.stopPropagation()},P.onMouseDown=s=>{s.currentTarget.contains(s.target)&&s.button===0&&(Re(s.currentTarget)&&s.preventDefault(),s.stopPropagation())},P.onPointerUp=s=>{!s.currentTarget.contains(s.target)||t.pointerType==="virtual"||s.button===0&&Ee(s,s.currentTarget)&&le(s,t.pointerType||s.pointerType)};let d=s=>{s.pointerId===t.activePointerId&&(t.target&&Ee(s,t.target)?!t.isOverTarget&&t.pointerType!=null&&(t.isOverTarget=!0,J(fe(t.target,s),t.pointerType)):t.target&&t.isOverTarget&&t.pointerType!=null&&(t.isOverTarget=!1,v(fe(t.target,s),t.pointerType,!1),N(s)))},y=s=>{s.pointerId===t.activePointerId&&t.isPressed&&s.button===0&&t.target&&(Ee(s,t.target)&&t.pointerType!=null?v(fe(t.target,s),t.pointerType):t.isOverTarget&&t.pointerType!=null&&v(fe(t.target,s),t.pointerType,!1),t.isPressed=!1,t.isOverTarget=!1,t.activePointerId=null,t.pointerType=null,_(),W||Fe(t.target))},o=s=>{ee(s)};P.onDragStart=s=>{s.currentTarget.contains(s.target)&&ee(s)}}else{P.onMouseDown=o=>{if(o.button!==0||!o.currentTarget.contains(o.target))return;if(Re(o.currentTarget)&&o.preventDefault(),t.ignoreEmulatedMouseEvents){o.stopPropagation();return}t.isPressed=!0,t.isOverTarget=!0,t.target=o.currentTarget,t.pointerType=(0,re.Z)(o.nativeEvent)?"virtual":"mouse",!c&&!h&&(0,R.A)(o.currentTarget),J(o,t.pointerType)&&o.stopPropagation(),K((0,f.r)(o.currentTarget),"mouseup",d,!1)},P.onMouseEnter=o=>{if(!o.currentTarget.contains(o.target))return;let s=!0;t.isPressed&&!t.ignoreEmulatedMouseEvents&&t.pointerType!=null&&(t.isOverTarget=!0,s=J(o,t.pointerType)),s&&o.stopPropagation()},P.onMouseLeave=o=>{if(!o.currentTarget.contains(o.target))return;let s=!0;t.isPressed&&!t.ignoreEmulatedMouseEvents&&t.pointerType!=null&&(t.isOverTarget=!1,s=v(o,t.pointerType,!1),N(o)),s&&o.stopPropagation()},P.onMouseUp=o=>{o.currentTarget.contains(o.target)&&!t.ignoreEmulatedMouseEvents&&o.button===0&&le(o,t.pointerType||"mouse")};let d=o=>{if(o.button===0){if(t.isPressed=!1,_(),t.ignoreEmulatedMouseEvents){t.ignoreEmulatedMouseEvents=!1;return}t.target&&Ee(o,t.target)&&t.pointerType!=null?v(fe(t.target,o),t.pointerType):t.target&&t.isOverTarget&&t.pointerType!=null&&v(fe(t.target,o),t.pointerType,!1),t.isOverTarget=!1}};P.onTouchStart=o=>{if(!o.currentTarget.contains(o.target))return;let s=wt(o.nativeEvent);if(!s)return;t.activePointerId=s.identifier,t.ignoreEmulatedMouseEvents=!0,t.isOverTarget=!0,t.isPressed=!0,t.target=o.currentTarget,t.pointerType="touch",!c&&!h&&(0,R.A)(o.currentTarget),W||Ge(t.target),J(o,t.pointerType)&&o.stopPropagation(),K((0,f.k)(o.currentTarget),"scroll",y,!0)},P.onTouchMove=o=>{if(!o.currentTarget.contains(o.target))return;if(!t.isPressed){o.stopPropagation();return}let s=qe(o.nativeEvent,t.activePointerId),E=!0;s&&Ee(s,o.currentTarget)?!t.isOverTarget&&t.pointerType!=null&&(t.isOverTarget=!0,E=J(o,t.pointerType)):t.isOverTarget&&t.pointerType!=null&&(t.isOverTarget=!1,E=v(o,t.pointerType,!1),N(o)),E&&o.stopPropagation()},P.onTouchEnd=o=>{if(!o.currentTarget.contains(o.target))return;if(!t.isPressed){o.stopPropagation();return}let s=qe(o.nativeEvent,t.activePointerId),E=!0;s&&Ee(s,o.currentTarget)&&t.pointerType!=null?(le(o,t.pointerType),E=v(o,t.pointerType)):t.isOverTarget&&t.pointerType!=null&&(E=v(o,t.pointerType,!1)),E&&o.stopPropagation(),t.isPressed=!1,t.activePointerId=null,t.isOverTarget=!1,t.ignoreEmulatedMouseEvents=!0,t.target&&!W&&Fe(t.target),_()},P.onTouchCancel=o=>{o.currentTarget.contains(o.target)&&(o.stopPropagation(),t.isPressed&&ee(o))};let y=o=>{t.isPressed&&o.target.contains(t.target)&&ee({currentTarget:t.target,shiftKey:!1,ctrlKey:!1,metaKey:!1,altKey:!1})};P.onDragStart=o=>{o.currentTarget.contains(o.target)&&ee(o)}}return P},[K,c,h,_,W,ee,N,v,J,le]);return(0,u.useEffect)(()=>()=>{var t;W||Fe((t=X.current.target)!==null&&t!==void 0?t:void 0)},[W]),{isPressed:T||he,pressProps:(0,M.d)(me,Ke)}}function ke(e){return e.tagName==="A"&&e.hasAttribute("href")}function Ie(e,n){const{key:r,code:a}=e,l=n,p=l.getAttribute("role");return(r==="Enter"||r===" "||r==="Spacebar"||a==="Space")&&!(l instanceof(0,f.k)(l).HTMLInputElement&&!et(l,r)||l instanceof(0,f.k)(l).HTMLTextAreaElement||l.isContentEditable)&&!((p==="link"||!p&&ke(l))&&r!=="Enter")}function wt(e){const{targetTouches:n}=e;return n.length>0?n[0]:null}function qe(e,n){const r=e.changedTouches;for(let a=0;a<r.length;a++){const l=r[a];if(l.identifier===n)return l}return null}function fe(e,n){return{currentTarget:e,shiftKey:n.shiftKey,ctrlKey:n.ctrlKey,metaKey:n.metaKey,altKey:n.altKey}}function Lt(e){let n=0,r=0;return e.width!==void 0?n=e.width/2:e.radiusX!==void 0&&(n=e.radiusX),e.height!==void 0?r=e.height/2:e.radiusY!==void 0&&(r=e.radiusY),{top:e.clientY-r,right:e.clientX+n,bottom:e.clientY+r,left:e.clientX-n}}function Dt(e,n){return!(e.left>n.right||n.left>e.right||e.top>n.bottom||n.top>e.bottom)}function Ee(e,n){let r=n.getBoundingClientRect(),a=Lt(e);return Dt(r,a)}function Re(e){return!(e instanceof HTMLElement)||!e.hasAttribute("draggable")}function _e(e,n){return e instanceof HTMLInputElement?!et(e,n):e instanceof HTMLButtonElement?e.type!=="submit"&&e.type!=="reset":!ke(e)}const kt=new Set(["checkbox","radio","range","color","file","image","button","submit","reset"]);function et(e,n){return e.type==="checkbox"||e.type==="radio"?n===" ":kt.has(e.type)}function It(e,n,r){let{isDisabled:a=!1,isReadOnly:l=!1,value:p,name:c,children:T,"aria-label":h,"aria-labelledby":ie,validationState:W="valid",isInvalid:pe}=e,me=N=>{N.stopPropagation(),n.setSelected(N.target.checked)},he=T!=null,ve=h!=null||ie!=null;!he&&!ve&&console.warn("If you do not provide children, you must specify an aria-label for accessibility");let{pressProps:X,isPressed:K}=Je({isDisabled:a}),{pressProps:_,isPressed:J}=Je({isDisabled:a||l,onPress(){n.toggle()}}),{focusableProps:v}=Ct(e,r),le=(0,M.d)(X,v),ee=(0,ce.z)(e,{labelable:!0});return C(r,n.isSelected,n.setSelected),{labelProps:(0,M.d)(_,{onClick:N=>N.preventDefault()}),inputProps:(0,M.d)(ee,$(w($({"aria-invalid":pe||W==="invalid"||void 0,"aria-errormessage":e["aria-errormessage"],"aria-controls":e["aria-controls"],"aria-readonly":l||void 0,onChange:me,disabled:a},p==null?{}:{value:p}),{name:c,type:"checkbox"}),le)),isSelected:n.isSelected,isPressed:K||J,isDisabled:a,isReadOnly:l,isInvalid:pe||W==="invalid"}}function Rt(e,n,r){let{labelProps:a,inputProps:l,isSelected:p,isPressed:c,isDisabled:T,isReadOnly:h}=It(e,n,r);return{labelProps:a,inputProps:w($({},l),{role:"switch",checked:p}),isSelected:p,isPressed:c,isDisabled:T,isReadOnly:h}}var Kt=i(99373);function At(e={}){let{isReadOnly:n}=e,[r,a]=(0,Kt.z)(e.isSelected,e.defaultSelected||!1,e.onChange);function l(c){n||a(c)}function p(){n||a(!r)}return{isSelected:r,setSelected:l,toggle:p}}var Wt=i(2459);function Zt(e={}){var n,r;const a=(0,L.w)(),[l,p]=(0,S.oe)(e,Z.variantKeys),ut=l,{ref:c,as:T,name:h,value:ie="",isReadOnly:W=!1,autoFocus:pe=!1,startContent:me,endContent:he,defaultSelected:ve,isSelected:X,children:K,thumbIcon:_,className:J,classNames:v,onChange:le,onValueChange:ee}=ut,N=Te(ut,["ref","as","name","value","isReadOnly","autoFocus","startContent","endContent","defaultSelected","isSelected","children","thumbIcon","className","classNames","onChange","onValueChange"]),Ke=T||"label",Ce=(0,u.useRef)(null),t=(0,u.useRef)(null),P=(r=(n=e.disableAnimation)!=null?n:a==null?void 0:a.disableAnimation)!=null?r:!1,V=(0,u.useId)(),d=(0,u.useMemo)(()=>{const b=N["aria-label"]||typeof K=="string"?K:void 0;return{name:h,value:ie,children:K,autoFocus:pe,defaultSelected:ve,isSelected:X,isDisabled:!!e.isDisabled,isReadOnly:W,"aria-label":b,"aria-labelledby":N["aria-labelledby"]||V,onChange:ee}},[ie,h,V,K,pe,W,X,ve,e.isDisabled,N["aria-label"],N["aria-labelledby"],ee]),y=At(d);(0,ge.G)(()=>{if(!t.current)return;const b=!!t.current.checked;y.setSelected(b)},[t.current]);const{inputProps:o,isPressed:s,isReadOnly:E}=Rt(d,y,t),{focusProps:Pe,isFocused:nt,isFocusVisible:ot}=(0,Wt.F)({autoFocus:o.autoFocus}),{hoverProps:Nt,isHovered:at}=(0,ue.X)({isDisabled:o.disabled}),st=d.isDisabled||E,[Vt,it]=(0,u.useState)(!1),{pressProps:Gt}=(0,I.r)({isDisabled:st,onPressStart(b){b.pointerType!=="keyboard"&&it(!0)},onPressEnd(b){b.pointerType!=="keyboard"&&it(!1)}}),lt=st?!1:Vt||s,ye=o.checked,Ae=o.disabled,z=(0,u.useMemo)(()=>Z(w($({},p),{disableAnimation:P})),[(0,ae.Xx)(p),P]),Yt=(0,D.W)(v==null?void 0:v.base,J),zt=b=>w($({},(0,M.d)(Nt,Gt,N,b)),{ref:Ce,className:z.base({class:(0,D.W)(Yt,b==null?void 0:b.className)}),"data-disabled":(0,k.PB)(Ae),"data-selected":(0,k.PB)(ye),"data-readonly":(0,k.PB)(E),"data-focus":(0,k.PB)(nt),"data-focus-visible":(0,k.PB)(ot),"data-hover":(0,k.PB)(at),"data-pressed":(0,k.PB)(lt)}),Qt=(0,u.useCallback)((b={})=>w($({},b),{"aria-hidden":!0,className:(0,D.W)(z.wrapper({class:(0,D.W)(v==null?void 0:v.wrapper,b==null?void 0:b.className)}))}),[z,v==null?void 0:v.wrapper]),Xt=(b={})=>w($({},(0,M.d)(o,Pe,b)),{ref:te(t,c),id:o.id,onChange:(0,de.t)(le,o.onChange)}),Jt=(0,u.useCallback)((b={})=>w($({},b),{className:z.thumb({class:(0,D.W)(v==null?void 0:v.thumb,b==null?void 0:b.className)})}),[z,v==null?void 0:v.thumb]),qt=(0,u.useCallback)((b={})=>w($({},b),{id:V,className:z.label({class:(0,D.W)(v==null?void 0:v.label,b==null?void 0:b.className)})}),[z,v==null?void 0:v.label,Ae,ye]),_t=(0,u.useCallback)((b={includeStateProps:!1})=>(0,M.d)({width:"1em",height:"1em",className:z.thumbIcon({class:(0,D.W)(v==null?void 0:v.thumbIcon)})},b.includeStateProps?{isSelected:ye}:{}),[z,v==null?void 0:v.thumbIcon,ye]),er=(0,u.useCallback)((b={})=>w($({width:"1em",height:"1em"},b),{className:z.startContent({class:(0,D.W)(v==null?void 0:v.startContent,b==null?void 0:b.className)})}),[z,v==null?void 0:v.startContent,ye]),tr=(0,u.useCallback)((b={})=>w($({width:"1em",height:"1em"},b),{className:z.endContent({class:(0,D.W)(v==null?void 0:v.endContent,b==null?void 0:b.className)})}),[z,v==null?void 0:v.endContent,ye]);return{Component:Ke,slots:z,classNames:v,domRef:Ce,children:K,thumbIcon:_,startContent:me,endContent:he,isHovered:at,isSelected:ye,isPressed:lt,isFocused:nt,isFocusVisible:ot,isDisabled:Ae,getBaseProps:zt,getWrapperProps:Qt,getInputProps:Xt,getLabelProps:qt,getThumbProps:Jt,getThumbIconProps:_t,getStartContentProps:er,getEndContentProps:tr}}var jt=i(44971);const tt={border:0,clip:"rect(0 0 0 0)",clipPath:"inset(50%)",height:"1px",margin:"-1px",overflow:"hidden",padding:0,position:"absolute",width:"1px",whiteSpace:"nowrap"};function Bt(e={}){let{style:n,isFocusable:r}=e,[a,l]=(0,u.useState)(!1),{focusWithinProps:p}=(0,jt.L)({isDisabled:!r,onFocusWithinChange:T=>l(T)}),c=(0,u.useMemo)(()=>a?n:n?$($({},tt),n):tt,[a]);return{visuallyHiddenProps:w($({},p),{style:c})}}function Ut(e){let T=e,{children:n,elementType:r="div",isFocusable:a,style:l}=T,p=Te(T,["children","elementType","isFocusable","style"]),{visuallyHiddenProps:c}=Bt(e);return u.createElement(r,(0,M.d)(p,c),n)}var xe=i(85893),rt=(0,S.Gp)((e,n)=>{const{Component:r,children:a,startContent:l,endContent:p,thumbIcon:c,getBaseProps:T,getInputProps:h,getWrapperProps:ie,getThumbProps:W,getThumbIconProps:pe,getLabelProps:me,getStartContentProps:he,getEndContentProps:ve}=Zt(w($({},e),{ref:n})),X=typeof c=="function"?c(pe({includeStateProps:!0})):c&&(0,u.cloneElement)(c,pe()),K=l&&(0,u.cloneElement)(l,he()),_=p&&(0,u.cloneElement)(p,ve());return(0,xe.jsxs)(r,w($({},T()),{children:[(0,xe.jsx)(Ut,{elementType:"span",children:(0,xe.jsx)("input",$({},h()))}),(0,xe.jsxs)("span",w($({},ie()),{children:[l&&K,(0,xe.jsx)("span",w($({},W()),{children:c&&X})),p&&_]})),a&&(0,xe.jsx)("span",w($({},me()),{children:a}))]}))});rt.displayName="NextUI.Switch";var Ht=rt},93387:function(x,m,i){i.d(m,{G:function(){return L}});var u=i(67294),L=globalThis!=null&&globalThis.document?u.useLayoutEffect:u.useEffect},99373:function(x,m,i){i.d(m,{z:function(){return L}});var u=i(67294);function L(S,k,Q){let[te,ge]=(0,u.useState)(S||k),ue=(0,u.useRef)(S!==void 0),I=S!==void 0;(0,u.useEffect)(()=>{let Z=ue.current;Z!==I&&console.warn(`WARN: A component changed from ${Z?"controlled":"uncontrolled"} to ${I?"controlled":"uncontrolled"}.`),ue.current=I},[I]);let F=I?S:te,be=(0,u.useCallback)((Z,...M)=>{let de=(ae,...D)=>{Q&&(Object.is(F,ae)||Q(ae,...D)),I||(F=ae)};typeof Z=="function"?(console.warn("We can not support a function callback. See Github Issues for details https://github.com/adobe/react-spectrum/issues/2320"),ge((D,...ce)=>{let j=Z(I?F:D,...ce);return de(j,...M),I?D:j})):(I||ge(Z),de(Z,...M))},[I,F,Q]);return[F,be]}}}]);
}());