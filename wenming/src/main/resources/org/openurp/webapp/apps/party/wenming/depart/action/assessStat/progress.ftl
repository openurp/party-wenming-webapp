[#ftl]
<script type="text/javascript" src="${base}/static/scripts/raphael/raphael-min.js"></script>
<script type="text/javascript" src="${base}/static/scripts/raphael/g.raphael-min.js"></script>
<script type="text/javascript" src="${base}/static/scripts/raphael/g.line-min.js"></script>
<script type="text/javascript" src="${base}/static/scripts/raphael/g.pie-min.js"></script>
<div id="holder" style="width:850px;height:400px;"></div>
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
 
 [#assign sum=0/]
 var mutualAssessPie = r.piechart(100, 300, 70,
  [[#list mutualAssessStat?keys as ast][#assign cnt =mutualAssessStat.get(ast)/] ${cnt/mutualCount},[#assign sum = sum + cnt/][/#list]${(mutualCount-sum)/mutualCount}],
 { legend: [[#list mutualAssessStat?keys as ast]"%%.%-${ast.description} ${mutualAssessStat.get(ast)}",[/#list]"%%.%-未填写 ${mutualCount-sum}"], legendpos: "east"});
 r.text(120, 220, "互评提交进度图").attr(txtattr);
 mutualAssessPie.hover(hoverA, hoverB);

 [#assign sum=0/] 
 var funcAssessPie = r.piechart(450, 300, 70,
  [[#list funcDepartAssessStat?keys as ast]${funcDepartAssessStat.get(ast)/funcDepartCount},[#assign sum = sum + funcDepartAssessStat.get(ast)/][/#list]${(funcDepartCount-sum)/funcDepartCount}],
 { legend: [[#list funcDepartAssessStat?keys as ast]"%%.%-${ast.description} ${funcDepartAssessStat.get(ast)}",[/#list]"%%.%-未填写 ${funcDepartCount-sum}"], legendpos: "east"});
 r.text(450, 220, "职能部门提交进度图").attr(txtattr);
 funcAssessPie.hover(hoverA, hoverB);
</script>
<p> <B>各部门详细进度</B></p>

  <table class="gridtable" style="width:900px;" >
    <thead class="gridhead">
      <th width="10%">序号</th>
      <th width="30%">部门</th>
      <th width="15%">提交申报材料</th>
      <th width="15%">提交自评</th>
      <th width="15%">提交互评</th>
      <th width="15%">提交职能部门测评</th>
    </thead>
    [#list departs?sort_by("code") as depart]
    <tr align="center">
      <td>${depart_index+1}</td>
      <td>${depart.name}</td>
      <td>[#if appliedDepartIds?seq_contains(depart.id)]<span class="toolbar-icon action-activate"></span>[#else]<span class="toolbar-icon action-close"></span>[/#if]</td>
      <td>[#if selfAssessDepartIds?seq_contains(depart.id)]<span class="toolbar-icon action-activate"></span>[#else]<span class="toolbar-icon action-close"></span>[/#if]</td>
      <td>[#assign delta = mutualCnts.get(depart.id) - mutualAssessDeparts.get(depart.id)!0]
       [#list  1 .. mutualCnts.get(depart.id) as i]
          [#if delta=0]<span class="toolbar-icon action-activate"></span>
          [#else]
          [#assign delta = delta-1]<span class="toolbar-icon action-close"></span>
          [/#if]
       [/#list]
      </td>
      <td>
      [#if funcDeparts?seq_contains(depart)]
      [#assign delta = funcDepartCnts.get(depart.id) - funcDepartAssessDeparts.get(depart.id)!0]
      [#list  1 .. funcDepartCnts.get(depart.id) as i]
          [#if delta=0]<span class="toolbar-icon action-activate"></span>
          [#else]
          [#assign delta = delta-1]<span class="toolbar-icon action-close"></span>
          [/#if]
       [/#list]
      [/#if]
      </td>
    </tr>
    [/#list]
  </table>
  <br>