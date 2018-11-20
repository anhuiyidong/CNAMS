define("echarts/chart/chord",["require","../component/base","./base","zrender/shape/Text","zrender/shape/Line","zrender/shape/Sector","../util/shape/Ribbon","../util/shape/Icon","zrender/shape/BezierCurve","../config","../util/ecData","zrender/tool/util","zrender/tool/vector","../data/Graph","../layout/Chord","../chart"],function(y){function D(i,d,c,l,h){w.call(this,i,d,c,l,h),j.call(this),this.scaleLineLength=4,this.scaleUnitAngle=4,this.refresh(l)}var w=y("../component/base"),j=y("./base"),B=y("zrender/shape/Text"),g=y("zrender/shape/Line"),E=y("zrender/shape/Sector"),b=y("../util/shape/Ribbon"),v=y("../util/shape/Icon"),x=y("zrender/shape/BezierCurve"),z=y("../config"),A=y("../util/ecData"),q=y("zrender/tool/util"),f=y("zrender/tool/vector"),C=y("../data/Graph"),k=y("../layout/Chord");return D.prototype={type:z.CHART_TYPE_CHORD,_init:function(){var u=this.series;this.selectedMap={};for(var G={},p={},h=0,F=u.length;F>h;h++){if(u[h].type===this.type){var d=this.isSelected(u[h].name);this.selectedMap[u[h].name]=d,d&&this.buildMark(h),this.reformOption(u[h]),G[u[h].name]=u[h]}}for(var h=0,F=u.length;F>h;h++){if(u[h].type===this.type){if(u[h].insertToSerie){var H=G[u[h].insertToSerie];u[h]._referenceSerie=H}else{p[u[h].name]=[u[h]]}}}for(var h=0,F=u.length;F>h;h++){if(u[h].type===this.type&&u[h].insertToSerie){for(var c=u[h]._referenceSerie;c&&c._referenceSerie;){c=c._referenceSerie}p[c.name]&&this.selectedMap[u[h].name]&&p[c.name].push(u[h])}}for(var m in p){this._buildChords(p[m])}this.addShapeList()},_getNodeCategory:function(c,a){return c.categories&&c.categories[a.category||0]},_getNodeQueryTarget:function(d,c){var a=this._getNodeCategory(d,c);return[c,a,d]},_getEdgeQueryTarget:function(d,c,a){return a=a||"normal",[c.itemStyle&&c.itemStyle[a],d.itemStyle[a].chordStyle]},_buildChords:function(O){for(var T=[],M=O[0],I=function(a){return a.layout.size>0},R=0;R<O.length;R++){var H=O[R];if(this.selectedMap[H.name]){var V;H.data&&H.matrix?V=this._getSerieGraphFromDataMatrix(H,M):H.nodes&&H.links&&(V=this._getSerieGraphFromNodeLinks(H,M)),V.filterNode(I,this),T.push(V),V.__serie=H}}if(T.length){var F=T[0];if(!M.ribbonType){var L=M.minRadius,N=M.maxRadius,P=1/0,Q=-1/0;F.eachNode(function(a){Q=Math.max(a.layout.size,Q),P=Math.min(a.layout.size,P)});var J=(N-L)/(Q-P);F.eachNode(function(c){var a=this._getNodeQueryTarget(M,c),d=this.query(a,"symbolSize");c.layout.size=Q===P?d||P:d||(c.layout.size-P)*J+L},this)}var G=new k;G.clockWise=M.clockWise,G.startAngle=M.startAngle*Math.PI/180,G.clockWise||(G.startAngle=-G.startAngle),G.padding=M.padding*Math.PI/180,G.sort=M.sort,G.sortSub=M.sortSub,G.directed=M.ribbonType,G.run(T);var S=this.query(M,"itemStyle.normal.label.show");if(M.ribbonType){this._buildSectors(M,0,F,M,T),S&&this._buildLabels(M,0,F,M,T);for(var R=0,K=0;R<O.length;R++){this.selectedMap[O[R].name]&&this._buildRibbons(O,R,T[K++],M)}M.showScale&&this._buildScales(M,0,F)}else{this._buildNodeIcons(M,0,F,M,T),S&&this._buildLabels(M,0,F,M,T);for(var R=0,K=0;R<O.length;R++){this.selectedMap[O[R].name]&&this._buildEdgeCurves(O,R,T[K++],M,F)}}this._initHoverHandler(O,T)}},_getSerieGraphFromDataMatrix:function(M,Q){for(var K=[],H=0,P=[],G=0;G<M.matrix.length;G++){P[G]=M.matrix[G].slice()}for(var R=M.data||M.nodes,G=0;G<R.length;G++){var u={},J=R[G];J.rawIndex=G;for(var L in J){"name"===L?u.id=J.name:u[L]=J[L]}var N=this._getNodeCategory(Q,J),O=N?N.name:J.name;if(this.selectedMap[O]=this.isSelected(O),this.selectedMap[O]){K.push(u),H++}else{P.splice(H,1);for(var I=0;I<P.length;I++){P[I].splice(H,1)}}}var F=C.fromMatrix(K,P,!0);return F.eachNode(function(a){a.layout={size:a.data.outValue},a.rawIndex=a.data.rawIndex}),F.eachEdge(function(a){a.layout={weight:a.data.weight}}),F},_getSerieGraphFromNodeLinks:function(M,Q){for(var K=new C(!0),H=M.data||M.nodes,P=0,G=H.length;G>P;P++){var R=H[P];if(R&&!R.ignore){var u=this._getNodeCategory(Q,R),J=u?u.name:R.name;if(this.selectedMap[J]=this.isSelected(J),this.selectedMap[J]){var L=K.addNode(R.name,R);L.rawIndex=P}}}for(var P=0,G=M.links.length;G>P;P++){var N=M.links[P],O=N.source,I=N.target;"number"==typeof O&&(O=H[O],O&&(O=O.name)),"number"==typeof I&&(I=H[I],I&&(I=I.name));var F=K.addEdge(O,I,N);F&&(F.rawIndex=P)}return K.eachNode(function(c){var a=c.data.value;if(null==a){if(a=0,Q.ribbonType){for(var d=0;d<c.outEdges.length;d++){a+=c.outEdges[d].data.weight||0}}else{for(var d=0;d<c.edges.length;d++){a+=c.edges[d].data.weight||0}}}c.layout={size:a}}),K.eachEdge(function(a){a.layout={weight:null==a.data.weight?1:a.data.weight}}),K},_initHoverHandler:function(l,h){var d=l[0],m=h[0],c=this;m.eachNode(function(a){a.shape.onmouseover=function(){m.eachNode(function(i){i.shape.style.opacity=0.1,i.labelShape&&(i.labelShape.style.opacity=0.1,i.labelShape.modSelf()),i.shape.modSelf()});for(var n=0;n<h.length;n++){for(var F=0;F<h[n].edges.length;F++){var t=h[n].edges[F],u=c._getEdgeQueryTarget(h[n].__serie,t.data);t.shape.style.opacity=0.1*c.deepQuery(u,"opacity"),t.shape.modSelf()}}a.shape.style.opacity=1,a.labelShape&&(a.labelShape.style.opacity=1);for(var n=0;n<h.length;n++){var e=h[n].getNodeById(a.id);if(e){for(var F=0;F<e.outEdges.length;F++){var t=e.outEdges[F],u=c._getEdgeQueryTarget(h[n].__serie,t.data);t.shape.style.opacity=c.deepQuery(u,"opacity");var p=h[0].getNodeById(t.node2.id);p&&(p.shape&&(p.shape.style.opacity=1),p.labelShape&&(p.labelShape.style.opacity=1))}}}c.zr.refreshNextFrame()},a.shape.onmouseout=function(){m.eachNode(function(o){o.shape.style.opacity=1,o.labelShape&&(o.labelShape.style.opacity=1,o.labelShape.modSelf()),o.shape.modSelf()});for(var p=0;p<h.length;p++){for(var t=0;t<h[p].edges.length;t++){var i=h[p].edges[t],n=[i.data,d];i.shape.style.opacity=c.deepQuery(n,"itemStyle.normal.chordStyle.opacity"),i.shape.modSelf()}}c.zr.refreshNextFrame()}})},_buildSectors:function(s,m,h,F){var d=this.parseCenter(this.zr,F.center),u=this.parseRadius(this.zr,F.radius),p=F.clockWise,c=p?1:-1;h.eachNode(function(l){var n=this._getNodeCategory(F,l.data),r=this.getColor(n?n.name:l.id),a=l.layout.startAngle/Math.PI*180*c,o=l.layout.endAngle/Math.PI*180*c,e=new E({zlevel:this.getZlevelBase(),style:{x:d[0],y:d[1],r0:u[0],r:u[1],startAngle:a,endAngle:o,brushType:"fill",opacity:1,color:r,clockWise:p},clickable:F.clickable,highlightStyle:{brushType:"fill"}});e.style.lineWidth=this.deepQuery([l.data,F],"itemStyle.normal.borderWidth"),e.highlightStyle.lineWidth=this.deepQuery([l.data,F],"itemStyle.emphasis.borderWidth"),e.style.strokeColor=this.deepQuery([l.data,F],"itemStyle.normal.borderColor"),e.highlightStyle.strokeColor=this.deepQuery([l.data,F],"itemStyle.emphasis.borderColor"),e.style.lineWidth>0&&(e.style.brushType="both"),e.highlightStyle.lineWidth>0&&(e.highlightStyle.brushType="both"),A.pack(e,s,m,l.data,l.rawIndex,l.id,l.category),this.shapeList.push(e),l.shape=e},this)},_buildNodeIcons:function(m,h,d,r){var c=this.parseCenter(this.zr,r.center),p=this.parseRadius(this.zr,r.radius),l=p[1];d.eachNode(function(G){var n=G.layout.startAngle,a=G.layout.endAngle,H=(n+a)/2,I=l*Math.cos(H),t=l*Math.sin(H),e=this._getNodeQueryTarget(r,G.data),J=this._getNodeCategory(r,G.data),s=this.deepQuery(e,"itemStyle.normal.color");s||(s=this.getColor(J?J.name:G.id));var F=new v({zlevel:this.getZlevelBase(),z:1,style:{x:-G.layout.size,y:-G.layout.size,width:2*G.layout.size,height:2*G.layout.size,iconType:this.deepQuery(e,"symbol"),color:s,brushType:"both",lineWidth:this.deepQuery(e,"itemStyle.normal.borderWidth"),strokeColor:this.deepQuery(e,"itemStyle.normal.borderColor")},highlightStyle:{color:this.deepQuery(e,"itemStyle.emphasis.color"),lineWidth:this.deepQuery(e,"itemStyle.emphasis.borderWidth"),strokeColor:this.deepQuery(e,"itemStyle.emphasis.borderColor")},clickable:r.clickable,position:[I+c[0],t+c[1]]});A.pack(F,m,h,G.data,G.rawIndex,G.id,G.category),this.shapeList.push(F),G.shape=F},this)},_buildLabels:function(H,K,F,p){var m=this.query(p,"itemStyle.normal.label.color"),L=this.query(p,"itemStyle.normal.label.rotate"),a=this.query(p,"itemStyle.normal.label.distance"),u=this.parseCenter(this.zr,p.center),G=this.parseRadius(this.zr,p.radius),I=p.clockWise,J=I?1:-1;F.eachNode(function(r){var N=r.layout.startAngle/Math.PI*180*J,n=r.layout.endAngle/Math.PI*180*J,s=(N*-J+n*-J)/2;s%=360,0>s&&(s+=360);var h=90>=s||s>=270;s=s*Math.PI/180;var M=[Math.cos(s),-Math.sin(s)],c=0;c=p.ribbonType?p.showScaleText?35+a:a:a+r.layout.size;var l=f.scale([],M,G[1]+c);f.add(l,l,u);var o={zlevel:this.getZlevelBase()+1,hoverable:!1,style:{text:null==r.data.label?r.id:r.data.label,textAlign:h?"left":"right",color:m||"#000000"}};L?(o.rotation=h?s:Math.PI+s,o.style.x=h?G[1]+c:-G[1]-c,o.style.y=0,o.position=u.slice()):(o.style.x=l[0],o.style.y=l[1]),o.style.textColor=this.deepQuery([r.data,p],"itemStyle.normal.label.textStyle.color")||"#fff",o.style.textFont=this.getFont(this.deepQuery([r.data,p],"itemStyle.normal.label.textStyle")),o=new B(o),this.shapeList.push(o),r.labelShape=o},this)},_buildRibbons:function(m,h,d,r){var c=m[h],p=this.parseCenter(this.zr,r.center),l=this.parseRadius(this.zr,r.radius);d.eachEdge(function(o,s){var G,i=d.getEdge(o.node2,o.node1);if(i&&!o.shape){if(i.shape){return void (o.shape=i.shape)}var a=o.layout.startAngle/Math.PI*180,I=o.layout.endAngle/Math.PI*180,e=i.layout.startAngle/Math.PI*180,n=i.layout.endAngle/Math.PI*180;G=this.getColor(1===m.length?o.layout.weight<=i.layout.weight?o.node1.id:o.node2.id:c.name);var t=this._getEdgeQueryTarget(c,o.data),H=this._getEdgeQueryTarget(c,o.data,"emphasis"),F=new b({zlevel:this.getZlevelBase(),style:{x:p[0],y:p[1],r:l[0],source0:a,source1:I,target0:e,target1:n,brushType:"both",opacity:this.deepQuery(t,"opacity"),color:G,lineWidth:this.deepQuery(t,"borderWidth"),strokeColor:this.deepQuery(t,"borderColor"),clockWise:r.clockWise},clickable:r.clickable,highlightStyle:{brushType:"both",opacity:this.deepQuery(H,"opacity"),lineWidth:this.deepQuery(H,"borderWidth"),strokeColor:this.deepQuery(H,"borderColor")}});A.pack(F,c,h,o.data,null==o.rawIndex?s:o.rawIndex,o.data.name||o.node1.id+"-"+o.node2.id,o.node1.id,o.node2.id),this.shapeList.push(F),o.shape=F}},this)},_buildEdgeCurves:function(m,h,d,r,c){var p=m[h],l=this.parseCenter(this.zr,r.center);d.eachEdge(function(H,G){var s=c.getNodeById(H.node1.id),a=c.getNodeById(H.node2.id),F=s.shape,I=a.shape,t=this._getEdgeQueryTarget(p,H.data),o=this._getEdgeQueryTarget(p,H.data,"emphasis"),J=new x({zlevel:this.getZlevelBase(),z:0,style:{xStart:F.position[0],yStart:F.position[1],xEnd:I.position[0],yEnd:I.position[1],cpX1:l[0],cpY1:l[1],lineWidth:this.deepQuery(t,"width"),strokeColor:this.deepQuery(t,"color"),opacity:this.deepQuery(t,"opacity")},highlightStyle:{lineWidth:this.deepQuery(o,"width"),strokeColor:this.deepQuery(o,"color"),opacity:this.deepQuery(o,"opacity")}});A.pack(J,p,h,H.data,null==H.rawIndex?G:H.rawIndex,H.data.name||H.node1.id+"-"+H.node2.id,H.node1.id,H.node2.id),this.shapeList.push(J),H.shape=J},this)},_buildScales:function(I,M,G){var o,N,a=I.clockWise,F=this.parseCenter(this.zr,I.center),H=this.parseRadius(this.zr,I.radius),J=a?1:-1,K=0,p=-1/0;I.showScaleText&&(G.eachNode(function(d){var c=d.data.value;c>p&&(p=c),K+=c}),p>10000000000?(o="b",N=1e-9):p>10000000?(o="m",N=0.000001):p>10000?(o="k",N=0.001):(o="",N=1));var L=K/(360-I.padding);G.eachNode(function(T){for(var n=T.layout.startAngle/Math.PI*180,O=T.layout.endAngle/Math.PI*180,e=n;;){if(a&&e>O||!a&&O>e){break}var d=e/180*Math.PI,h=[Math.cos(d),Math.sin(d)],r=f.scale([],h,H[1]+1);f.add(r,r,F);var Q=f.scale([],h,H[1]+this.scaleLineLength);f.add(Q,Q,F);var s=new g({zlevel:this.getZlevelBase()-1,hoverable:!1,style:{xStart:r[0],yStart:r[1],xEnd:Q[0],yEnd:Q[1],lineCap:"round",brushType:"stroke",strokeColor:"#666",lineWidth:1}});this.shapeList.push(s),e+=J*this.scaleUnitAngle}if(I.showScaleText){for(var S=n,P=5*L*this.scaleUnitAngle,R=0;;){if(a&&S>O||!a&&O>S){break}var d=S;d%=360,0>d&&(d+=360);var l=90>=d||d>=270,u=new B({zlevel:this.getZlevelBase()-1,hoverable:!1,style:{x:l?H[1]+this.scaleLineLength+4:-H[1]-this.scaleLineLength-4,y:0,text:Math.round(10*R)/10+o,textAlign:l?"left":"right"},position:F.slice(),rotation:l?[-d/180*Math.PI,0,0]:[-(d+180)/180*Math.PI,0,0]});this.shapeList.push(u),R+=P*N,S+=J*this.scaleUnitAngle*5}}},this)},refresh:function(d){if(d&&(this.option=d,this.series=d.series),this.legend=this.component.legend,this.legend){this.getColor=function(h){return this.legend.getColor(h)},this.isSelected=function(h){return this.legend.isSelected(h)}}else{var c={},a=0;this.getColor=function(h){return c[h]?c[h]:(c[h]||(c[h]=this.zr.getColor(a++)),c[h])},this.isSelected=function(){return !0}}this.backupShapeList(),this._init()},reformOption:function(c){var a=q.merge;c=a(c||{},this.ecTheme.chord),c.itemStyle.normal.label.textStyle=a(c.itemStyle.normal.label.textStyle||{},this.ecTheme.textStyle)}},q.inherits(D,j),q.inherits(D,w),y("../chart").define("chord",D),D}),define("echarts/util/shape/Ribbon",["require","zrender/shape/Base","zrender/shape/util/PathProxy","zrender/tool/util","zrender/tool/area"],function(f){function d(a){c.call(this,a),this._pathProxy=new h}var c=f("zrender/shape/Base"),h=f("zrender/shape/util/PathProxy"),b=f("zrender/tool/util"),g=f("zrender/tool/area");return d.prototype={type:"ribbon",buildPath:function(I,w){var F=w.clockWise||!1,C=this._pathProxy;C.begin(I);var L=w.x,B=w.y,x=w.r,z=w.source0/180*Math.PI,E=w.source1/180*Math.PI,G=w.target0/180*Math.PI,J=w.target1/180*Math.PI,K=L+Math.cos(z)*x,D=B+Math.sin(z)*x,A=L+Math.cos(E)*x,v=B+Math.sin(E)*x,j=L+Math.cos(G)*x,k=B+Math.sin(G)*x,H=L+Math.cos(J)*x,q=B+Math.sin(J)*x;C.moveTo(K,D),C.arc(L,B,w.r,z,E,!F),C.bezierCurveTo(0.7*(L-A)+A,0.7*(B-v)+v,0.7*(L-j)+j,0.7*(B-k)+k,j,k),(w.source0!==w.target0||w.source1!==w.target1)&&(C.arc(L,B,w.r,G,J,!F),C.bezierCurveTo(0.7*(L-H)+H,0.7*(B-q)+q,0.7*(L-K)+K,0.7*(B-D)+D,K,D))},getRect:function(a){return a.__rect?a.__rect:(this._pathProxy.isEmpty()||this.buildPath(null,a),this._pathProxy.fastBoundingRect())},isCover:function(k,j){var a=this.getRect(this.style);return k>=a.x&&k<=a.x+a.width&&j>=a.y&&j<=a.y+a.height?g.isInsidePath(this._pathProxy.pathCommands,0,"fill",k,j):void 0}},b.inherits(d,c),d}),define("zrender/shape/BezierCurve",["require","./Base","../tool/util"],function(c){var b=c("./Base"),a=function(d){this.brushTypeOnly="stroke",this.textPosition="end",b.call(this,d)};return a.prototype={type:"bezier-curve",buildPath:function(f,d){f.moveTo(d.xStart,d.yStart),"undefined"!=typeof d.cpX2&&"undefined"!=typeof d.cpY2?f.bezierCurveTo(d.cpX1,d.cpY1,d.cpX2,d.cpY2,d.xEnd,d.yEnd):f.quadraticCurveTo(d.cpX1,d.cpY1,d.xEnd,d.yEnd)},getRect:function(k){if(k.__rect){return k.__rect}var g=Math.min(k.xStart,k.xEnd,k.cpX1),f=Math.min(k.yStart,k.yEnd,k.cpY1),m=Math.max(k.xStart,k.xEnd,k.cpX1),d=Math.max(k.yStart,k.yEnd,k.cpY1),l=k.cpX2,h=k.cpY2;"undefined"!=typeof l&&"undefined"!=typeof h&&(g=Math.min(g,l),f=Math.min(f,h),m=Math.max(m,l),d=Math.max(d,h));var j=k.lineWidth||1;return k.__rect={x:g-j,y:f-j,width:m-g+j,height:d-f+j},k.__rect}},c("../tool/util").inherits(a,b),a}),define("echarts/data/Graph",["require","zrender/tool/util"],function(f){var d=f("zrender/tool/util"),c=function(a){this._directed=a||!1,this.nodes=[],this.edges=[],this._nodesMap={},this._edgesMap={}};c.prototype.isDirected=function(){return this._directed},c.prototype.addNode=function(h,a){if(this._nodesMap[h]){return this._nodesMap[h]}var i=new c.Node(h,a);return this.nodes.push(i),this._nodesMap[h]=i,i},c.prototype.getNodeById=function(a){return this._nodesMap[a]},c.prototype.addEdge=function(j,i,l){if("string"==typeof j&&(j=this._nodesMap[j]),"string"==typeof i&&(i=this._nodesMap[i]),j&&i){var h=j.id+"-"+i.id;if(this._edgesMap[h]){return this._edgesMap[h]}var k=new c.Edge(j,i,l);return this._directed&&(j.outEdges.push(k),i.inEdges.push(k)),j.edges.push(k),j!==i&&i.edges.push(k),this.edges.push(k),this._edgesMap[h]=k,k}},c.prototype.removeEdge=function(k){var j=k.node1,l=k.node2,h=j.id+"-"+l.id;this._directed&&(j.outEdges.splice(d.indexOf(j.outEdges,k),1),l.inEdges.splice(d.indexOf(l.inEdges,k),1)),j.edges.splice(d.indexOf(j.edges,k),1),j!==l&&l.edges.splice(d.indexOf(l.edges,k),1),delete this._edgesMap[h],this.edges.splice(d.indexOf(this.edges,k),1)},c.prototype.getEdge=function(h,a){return"string"!=typeof h&&(h=h.id),"string"!=typeof a&&(a=a.id),this._directed?this._edgesMap[h+"-"+a]||this._edgesMap[a+"-"+h]:this._edgesMap[h+"-"+a]},c.prototype.removeNode=function(h){if("string"!=typeof h||(h=this._nodesMap[h])){delete this._nodesMap[h.id],this.nodes.splice(d.indexOf(this.nodes,h),1);for(var a=0;a<this.edges.length;){var j=this.edges[a];j.node1===h||j.node2===h?this.removeEdge(j):a++}}},c.prototype.filterNode=function(j,h){for(var a=this.nodes.length,k=0;a>k;){j.call(h,this.nodes[k],k)?k++:(this.removeNode(this.nodes[k]),a--)}},c.prototype.filterEdge=function(j,h){for(var a=this.edges.length,k=0;a>k;){j.call(h,this.edges[k],k)?k++:(this.removeEdge(this.edges[k]),a--)}},c.prototype.eachNode=function(j,h){for(var a=this.nodes.length,k=0;a>k;k++){this.nodes[k]&&j.call(h,this.nodes[k],k)}},c.prototype.eachEdge=function(j,h){for(var a=this.edges.length,k=0;a>k;k++){this.edges[k]&&j.call(h,this.edges[k],k)}},c.prototype.clear=function(){this.nodes.length=0,this.edges.length=0,this._nodesMap={},this._edgesMap={}},c.prototype.breadthFirstTraverse=function(v,y,q,m){if("string"==typeof y&&(y=this._nodesMap[y]),y){var x="edges";"out"===q?x="outEdges":"in"===q&&(x="inEdges");for(var k=0;k<this.nodes.length;k++){this.nodes[k].__visited=!1}if(!v.call(m,y,null)){for(var z=[y];z.length;){for(var j=z.shift(),p=j[x],k=0;k<p.length;k++){var u=p[k],w=u.node1===j?u.node2:u.node1;if(!w.__visited){if(v.call(w,w,j)){return}z.push(w),w.__visited=!0}}}}}},c.prototype.clone=function(){for(var h=new c(this._directed),a=0;a<this.nodes.length;a++){h.addNode(this.nodes[a].id,this.nodes[a].data)}for(var a=0;a<this.edges.length;a++){var i=this.edges[a];h.addEdge(i.node1.id,i.node2.id,i.data)}return h};var g=function(h,a){this.id=h,this.data=a||null,this.inEdges=[],this.outEdges=[],this.edges=[]};g.prototype.degree=function(){return this.edges.length},g.prototype.inDegree=function(){return this.inEdges.length},g.prototype.outDegree=function(){return this.outEdges.length};var b=function(j,h,a){this.node1=j,this.node2=h,this.data=a||null};return c.Node=g,c.Edge=b,c.fromMatrix=function(x,B,q){if(B&&B.length&&B[0].length===B.length&&x.length===B.length){for(var A=B.length,k=new c(q),C=0;A>C;C++){var i=k.addNode(x[C].id,x[C]);i.data.value=0,q&&(i.data.outValue=i.data.inValue=0)}for(var C=0;A>C;C++){for(var v=0;A>v;v++){var w=B[C][v];q&&(k.nodes[C].data.outValue+=w,k.nodes[v].data.inValue+=w),k.nodes[C].data.value+=w,k.nodes[v].data.value+=w}}for(var C=0;A>C;C++){for(var v=C;A>v;v++){var w=B[C][v];if(0!==w){var y=k.nodes[C],z=k.nodes[v],u=k.addEdge(y,z,{});if(u.data.weight=w,C!==v&&q&&B[v][C]){var j=k.addEdge(z,y,{});j.data.weight=B[v][C]}}}}return k}},c}),define("echarts/layout/Chord",["require"],function(){var c=function(d){d=d||{},this.sort=d.sort||null,this.sortSub=d.sortSub||null,this.padding=0.05,this.startAngle=d.startAngle||0,this.clockWise=null==d.clockWise?!1:d.clockWise,this.center=d.center||[0,0],this.directed=!0};c.prototype.run=function(H){H instanceof Array||(H=[H]);var B=H.length;if(B){for(var L=H[0],A=L.nodes.length,v=[],w=0,D=0;A>D;D++){var E=L.nodes[D],I={size:0,subGroups:[],node:E};v.push(I);for(var J=0,C=0;C<H.length;C++){var z=H[C],t=z.getNodeById(E.id);if(t){I.size+=t.layout.size;for(var i=this.directed?t.outEdges:t.edges,j=0;j<i.length;j++){var F=i[j],k=F.layout.weight;I.subGroups.push({weight:k,edge:F,graph:z}),J+=k}}}w+=I.size;for(var G=I.size/J,j=0;j<I.subGroups.length;j++){I.subGroups[j].weight*=G}"ascending"===this.sortSub?I.subGroups.sort(b):"descending"===this.sort&&(I.subGroups.sort(b),I.subGroups.reverse())}"ascending"===this.sort?v.sort(a):"descending"===this.sort&&(v.sort(a),v.reverse());for(var G=(2*Math.PI-this.padding*A)/w,M=this.startAngle,K=this.clockWise?1:-1,D=0;A>D;D++){var I=v[D];I.node.layout.startAngle=M,I.node.layout.endAngle=M+K*I.size*G,I.node.layout.subGroups=[];for(var j=0;j<I.subGroups.length;j++){var q=I.subGroups[j];q.edge.layout.startAngle=M,M+=K*q.weight*G,q.edge.layout.endAngle=M}M=I.node.layout.endAngle+K*this.padding}}};var b=function(f,d){return f.weight-d.weight},a=function(f,d){return f.size-d.size};return c});