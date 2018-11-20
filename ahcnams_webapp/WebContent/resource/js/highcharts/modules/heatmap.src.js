(function(b){var t,h=b.Axis,n=b.Chart,s=b.Color,u=b.Legend,q=b.LegendSymbolMixin,g=b.Series,k=b.SVGRenderer,p=b.getOptions(),d=b.each,o=b.extend,l=b.extendClass,f=b.merge,e=b.pick,m=b.numberFormat,r=b.seriesTypes,i=b.wrap,c=function(){};var a=b.ColorAxis=function(){this.isColorAxis=true;this.init.apply(this,arguments)};o(a.prototype,h.prototype);o(a.prototype,{defaultColorAxisOptions:{lineWidth:0,gridLineWidth:1,tickPixelInterval:72,startOnTick:true,endOnTick:true,offset:0,marker:{animation:{duration:50},color:"gray",width:0.01},labels:{overflow:"justify"},minColor:"#EFEFFF",maxColor:"#003875",tickLength:5},init:function(w,x){var y=w.options.legend.layout!=="vertical",v;v=f(this.defaultColorAxisOptions,{side:y?2:1,reversed:!y},x,{isX:y,opposite:!y,showEmpty:false,title:null,isColor:true});h.prototype.init.call(this,w,v);if(x.dataClasses){this.initDataClasses(x)}this.initStops(x);this.isXAxis=true;this.horiz=y;this.zoomEnabled=false},tweenColors:function(y,x,w){var v=(x.rgba[3]!==1||y.rgba[3]!==1);return(v?"rgba(":"rgb(")+Math.round(x.rgba[0]+(y.rgba[0]-x.rgba[0])*(1-w))+","+Math.round(x.rgba[1]+(y.rgba[1]-x.rgba[1])*(1-w))+","+Math.round(x.rgba[2]+(y.rgba[2]-x.rgba[2])*(1-w))+(v?(","+(x.rgba[3]+(y.rgba[3]-x.rgba[3])*(1-w))):"")+")"},initDataClasses:function(A){var y=this,x=this.chart,v,z=0,w=this.options;this.dataClasses=v=[];this.legendItems=[];d(A.dataClasses,function(D,C){var B;D=f(D);v.push(D);if(!D.color){if(w.dataClassColor==="category"){B=x.options.colors;D.color=B[z++];if(z===B.length){z=0}}else{D.color=y.tweenColors(s(w.minColor),s(w.maxColor),C/(A.dataClasses.length-1))}}})},initStops:function(v){this.stops=v.stops||[[0,this.options.minColor],[1,this.options.maxColor]];d(this.stops,function(w){w.color=s(w[1])})},setOptions:function(v){h.prototype.setOptions.call(this,v);this.options.crosshair=this.options.marker;this.coll="colorAxis"},setAxisSize:function(){var B=this.legendSymbol,A=this.chart,w,C,z,v;if(B){this.left=w=B.attr("x");this.top=C=B.attr("y");this.width=z=B.attr("width");this.height=v=B.attr("height");this.right=A.chartWidth-w-z;this.bottom=A.chartHeight-C-v;this.len=this.horiz?z:v;this.pos=this.horiz?w:C}},toColor:function(C,D){var z,E=this.stops,A,B,w,v=this.dataClasses,y,x;if(v){x=v.length;while(x--){y=v[x];A=y.from;B=y.to;if((A===t||C>=A)&&(B===t||C<=B)){w=y.color;if(D){D.dataClass=x}break}}}else{if(this.isLog){C=this.val2lin(C)}z=1-((this.max-C)/((this.max-this.min)||1));x=E.length;while(x--){if(z>E[x][0]){break}}A=E[x]||E[x+1];B=E[x+1]||A;z=1-(B[0]-z)/((B[0]-A[0])||1);w=this.tweenColors(A.color,B.color,z)}return w},getOffset:function(){var w=this.legendGroup,v=this.chart.axisOffset[this.side];if(w){h.prototype.getOffset.call(this);if(!this.axisGroup.parentGroup){this.axisGroup.add(w);this.gridGroup.add(w);this.labelGroup.add(w);this.added=true}this.chart.axisOffset[this.side]=v}},setLegendColor:function(){var x,w=this.horiz,v=this.options;x=w?[0,0,1,0]:[0,0,0,1];this.legendColor={linearGradient:{x1:x[0],y1:x[1],x2:x[2],y2:x[3]},stops:v.stops||[[0,v.minColor],[1,v.maxColor]]}},drawLegendSymbol:function(z,C){var y=z.padding,D=z.options,B=this.horiz,x,v=e(D.symbolWidth,B?200:12),A=e(D.symbolHeight,B?12:200),w=e(D.labelPadding,B?16:30),E=e(D.itemDistance,10);this.setLegendColor();C.legendSymbol=this.chart.renderer.rect(0,z.baseline-11,v,A).attr({zIndex:1}).add(C.legendGroup);x=C.legendSymbol.getBBox();this.legendItemWidth=v+y+(B?E:w);this.legendItemHeight=A+y+(B?w:0)},setState:c,visible:true,setVisible:c,getSeriesExtremes:function(){var v;if(this.series.length){v=this.series[0];this.dataMin=v.valueMin;this.dataMax=v.valueMax}},drawCrosshair:function(B,w){var v=!this.cross,y=w&&w.plotX,x=w&&w.plotY,C,z=this.pos,A=this.len;if(w){C=this.toPixels(w.value);if(C<z){C=z-2}else{if(C>z+A){C=z+A+2}}w.plotX=C;w.plotY=this.len-C;h.prototype.drawCrosshair.call(this,B,w);w.plotX=y;w.plotY=x;if(!v&&this.cross){this.cross.attr({fill:this.crosshair.color}).add(this.labelGroup)}}},getPlotLinePath:function(w,v,z,x,y){if(y){return this.horiz?["M",y-4,this.top-6,"L",y+4,this.top-6,y,this.top,"Z"]:["M",this.left,y,"L",this.left-6,y+6,this.left-6,y-6,"Z"]}else{return h.prototype.getPlotLinePath.call(this,w,v,z,x)}},update:function(v,w){d(this.series,function(x){x.isDirtyData=true});h.prototype.update.call(this,v,w);if(this.legendItem){this.setLegendColor();this.chart.legend.colorizeItem(this,true)}},getDataClassLegendSymbols:function(){var z=this,y=this.chart,x=this.legendItems,w=y.options.legend,A=w.valueDecimals,B=w.valueSuffix||"",v;if(!x.length){d(this.dataClasses,function(E,C){var D=true,G=E.from,F=E.to;v="";if(G===t){v="< "}else{if(F===t){v="> "}}if(G!==t){v+=m(G,A)+B}if(G!==t&&F!==t){v+=" - "}if(F!==t){v+=m(F,A)+B}x.push(o({chart:y,name:v,options:{},drawLegendSymbol:q.drawRectangle,visible:true,setState:c,setVisible:function(){D=this.visible=!D;d(z.series,function(H){d(H.points,function(I){if(I.dataClass===C){I.setVisible(D)}})});y.legend.colorizeItem(this,D)}},E))})}return x},name:""});i(n.prototype,"getAxes",function(x){var v=this.options,w=v.colorAxis;x.call(this);this.colorAxis=[];if(w){x=new a(this,w)}});i(u.prototype,"getAllItems",function(x){var w=[],v=this.chart.colorAxis[0];if(v){if(v.options.dataClasses){w=w.concat(v.getDataClassLegendSymbols())}else{w.push(v)}d(v.series,function(y){y.options.showInLegend=false})}return w.concat(x.call(this))});var j={pointAttrToOptions:{stroke:"borderColor","stroke-width":"borderWidth",fill:"color",dashstyle:"dashStyle"},pointArrayMap:["value"],axisTypes:["xAxis","yAxis","colorAxis"],optionalAxis:"colorAxis",trackerGroups:["group","markerGroup","dataLabelsGroup"],getSymbol:c,parallelArrays:["x","y","value"],colorKey:"value",translateColors:function(){var x=this,v=this.options.nullColor,w=this.colorAxis,y=this.colorKey;d(this.data,function(z){var B=z[y],A;A=B===null?v:(w&&B!==undefined)?w.toColor(B,z):z.color||x.color;if(A){z.color=A}})}};i(k.prototype,"buildText",function(w,x){var v=x.styles&&x.styles.HcTextStroke;w.call(this,x);if(v&&x.applyTextStroke){x.applyTextStroke(v)}});k.prototype.Element.prototype.applyTextStroke=function(x){var w=this.element,v,y;x=x.split(" ");v=w.getElementsByTagName("tspan");y=w.firstChild;this.ySetter=this.xSetter;d([].slice.call(v),function(z,B){var A;if(B===0){z.setAttribute("x",w.getAttribute("x"));if((B=w.getAttribute("y"))!==null){z.setAttribute("y",B)}}A=z.cloneNode(1);A.setAttribute("stroke",x[1]);A.setAttribute("stroke-width",x[0]);A.setAttribute("stroke-linejoin","round");w.insertBefore(A,y)})};p.plotOptions.heatmap=f(p.plotOptions.scatter,{animation:false,borderWidth:0,nullColor:"#F8F8F8",dataLabels:{formatter:function(){return this.point.value},verticalAlign:"middle",crop:false,overflow:false,style:{color:"white",fontWeight:"bold",HcTextStroke:"1px rgba(0,0,0,0.5)"}},marker:null,tooltip:{pointFormat:"{point.x}, {point.y}: {point.value}<br/>"},states:{normal:{animation:true},hover:{brightness:0.2}}});r.heatmap=l(r.scatter,f(j,{type:"heatmap",pointArrayMap:["y","value"],hasPointSpecificOptions:true,supportsDrilldown:true,getExtremesFromAll:true,init:function(){r.scatter.prototype.init.apply(this,arguments);this.pointRange=this.options.colsize||1;this.yAxis.axisPointRange=this.options.rowsize||1},translate:function(){var x=this,w=x.options,y=x.xAxis,v=x.yAxis;x.generatePoints();d(x.points,function(z){var F=(w.colsize||1)/2,D=(w.rowsize||1)/2,B=Math.round(y.len-y.translate(z.x-F,0,1,0,1)),A=Math.round(y.len-y.translate(z.x+F,0,1,0,1)),E=Math.round(v.translate(z.y-D,0,1,0,1)),C=Math.round(v.translate(z.y+D,0,1,0,1));z.plotX=(B+A)/2;z.plotY=(E+C)/2;z.shapeType="rect";z.shapeArgs={x:Math.min(B,A),y:Math.min(E,C),width:Math.abs(A-B),height:Math.abs(C-E)}});x.translateColors();if(this.chart.hasRendered){d(x.points,function(z){z.shapeArgs.fill=z.color})}},drawPoints:r.column.prototype.drawPoints,animate:c,getBox:c,drawLegendSymbol:q.drawRectangle,getExtremes:function(){g.prototype.getExtremes.call(this,this.valueData);this.valueMin=this.dataMin;this.valueMax=this.dataMax;g.prototype.getExtremes.call(this)}}))}(Highcharts));