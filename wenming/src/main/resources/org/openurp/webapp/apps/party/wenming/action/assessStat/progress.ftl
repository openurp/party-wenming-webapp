[#ftl]
<script type="text/javascript" src="${base}/static/scripts/raphael/raphael-min.js"></script>
<script type="text/javascript" src="${base}/static/scripts/raphael/g.raphael-min.js"></script>
<script type="text/javascript" src="${base}/static/scripts/raphael/g.line-min.js"></script>
<script type="text/javascript" src="${base}/static/scripts/raphael/g.pie-min.js"></script>
<div id="holder" style="width:850px;height:800px"></div>
<script>

 var hoverA = function () {
    this.sector.stop();
    this.sector.scale(1.1, 1.1, this.cx, this.cy);
    if (this.label) {
        this.label[0].stop();
        this.label[0].attr({ r: 7.5 });
        this.label[1].attr({ "font-weight": 800 });
    }
 }

 var hoverB = function () {
   this.sector.animate({ transform: 's1 1 ' + this.cx + ' ' + this.cy }, 500, "bounce");
     if (this.label) {
       this.label[0].animate({ r: 5 }, 500, "bounce");
       this.label[1].attr({ "font-weight": 400 });
     }
 }

 var  txtattr = { font: "15px sans-serif","font-weight": 800 };
 var r = Raphael("holder")

 [#assign sum=0/]
 var applyPie = r.piechart(100, 110, 70, 
 [[#list applyStat as ast]${ast[1]/departCount},[#assign sum = sum + ast[1]/][/#list]${(departCount-sum)/departCount}],
 { legend: [[#list applyStat as ast]"%%.%-${ast[0].description} ${ast[1]}",[/#list]"%%.%-未填写 ${departCount-sum}"], legendpos: "east"});

 r.text(120, 20, "文明单位申报材料提交进度图").attr(txtattr);
 applyPie.hover(hoverA, hoverB);

 [#assign sum=0/]
 var selfAssessPie = r.piechart(450, 110, 70, 
 [[#list selfAssessStat as ast]${ast[1]/departCount},[#assign sum = sum + ast[1]/][/#list]${(departCount-sum)/departCount}],
 { legend: [[#list selfAssessStat as ast]"%%.%-${ast[0].description} ${ast[1]}",[/#list]"%%.%-未填写 ${departCount-sum}"], legendpos: "east"});
 r.text(450, 20, "自评提交进度图").attr(txtattr);
 selfAssessPie.hover(hoverA, hoverB);

 var mutualAssessPie = r.piechart(100, 300, 70,
  [[#list mutualAssessStat as ast]${ast[1]/departCount},[#assign sum = sum + ast[1]/][/#list]${(departCount-sum)/departCount}],
 { legend: [[#list mutualAssessStat as ast]"%%.%-${ast[0].description} ${ast[1]}",[/#list]"%%.%-未填写 ${departCount-sum}"], legendpos: "east"});
 r.text(120, 220, "互评提交进度图").attr(txtattr);
 mutualAssessPie.hover(hoverA, hoverB);
 
 var funcAssessPie = r.piechart(450, 300, 70,
  [[#list funcDepartAssessStat as ast]${ast[1]/departCount},[#assign sum = sum + ast[1]/][/#list]${(departCount-sum)/departCount}],
 { legend: [[#list funcDepartAssessStat as ast]"%%.%-${ast[0].description} ${ast[1]}",[/#list]"%%.%-未填写 ${departCount-sum}"], legendpos: "east"});
 r.text(450, 220, "职能部门提交进度图").attr(txtattr);
 funcAssessPie.hover(hoverA, hoverB);
</script>