[#ftl]
[@b.head/]
<style>
 .radiodiv label[yes='yes'].ui-state-active{
  background:green;
  color:white
 }
</style>
  [@b.form action="!save"]
    [#if assessVotes?? && assessVotes?size gt 0]
      [#assign vote = assessVotes[0]/]
      <table class="infoTable">
        <tr>
         <td class="title">投票人:</td>
         <td class="content">${vote.voter.fullname}</td>
         <td class="title">投票修改时间:</td>
         <td class="content">${vote.updatedAt?string("YYYY-MM-dd HH:mm")}</td>
        </tr>
      </table>
    [/#if]
    [#if !departmentType]
        [#--这是一段死代码，要求原本是教学部门的继续教育学院排在党办后面，后期可以通过让用户设定测评群体，及其顺序的方式满足--]
        [#assign teachingAssessVotes = []/]
        [#assign funcAssessVotes = []/]
        [#list assessVotes as vote]
            [#if vote.department.code[0..0]=='1'] [#assign teachingAssessVotes = teachingAssessVotes +[vote]/]
            [#else][#assign funcAssessVotes = funcAssessVotes + [vote]/]
            [/#if]
        [/#list]
        [#assign assessVotes = funcAssessVotes?sort_by(["department","code"]) + teachingAssessVotes?sort_by(["department","code"]) /]
    [/#if]
    <table id="voteTable" class="gridtable assessTable">
      <thead>
        <tr>
          <th width="4%">测评单位</th>
          <th width="25%">创建活动及其效果</th>
          <th width="25%">文明创建特色</th>
          <th width="25%">详实材料</th>
          <th width="4%">相关支撑材料</th>
          <th width="4%">加分项分值</th>
          <th width="4%">测评总分</th>
          <th width="8%">投票（投票上限：<span class="voteMaxNum" style="color:red"></span>票）</th>
        </tr>
      </thead>
      <tbody>
        [#list assessVotes as vote]
        <tr>
          [#assign name="vote${vote_index}"/]
          <input type="hidden" name="index" value="${name}"/>
          <input type="hidden" name="${name}.department.id" value="${vote.department.id}"/>
          <input type="hidden" name="${name}.id" value="${vote.id!}"/>
          <td align="center">${vote_index+1}.${vote.department.name}</td>
            [#assign finded=assessApplies?keys?seq_contains(vote.department.id)]
            [#if finded][#assign apply=assessApplies.get(vote.department.id)][/#if]
          <td>
                [#if finded]${apply.activitySummary}[#else]未申报[/#if]
          </td>
          <td>
                [#if finded]${apply.wenmingSummary}[#else]未申报[/#if]
          </td>
          <td>
                [#if finded]${apply.detail}[#else]未申报[/#if]
          </td>
          <td align="center">
            [#if finded && apply.attachment??]
                [@b.a target="_blank" href="../attachment?path=${apply.attachment.filePath}&name=${apply.attachment.name?url('utf-8')}"]
                下载
                [/@]
            [/#if]
          </td>
          <td align="center">
              [#if finded]${apply.bonus}[/#if]
          </td>
          <td align="center">${totalScoreMap[vote.department.id+""]!}</td>
          <td align="center">
            <div class="radiodiv">
            <input type="radio" name="${name}.ayes" value="1" id="${name}1" class="yes" [#if vote.ayes] checked="checked" [/#if] />
            <label for="${name}1" yes="yes">是</label>
            <input type="radio" name="${name}.ayes" value="0" id="${name}0" class="no" [#if vote.id?? && !vote.ayes] checked="checked" [/#if]/>
            <label for="${name}0">否</label>
            </div>
          </td>
        </tr>
        [/#list]
      </tbody>
    </table>
    <div style="text-align:center; padding:30px;" class="footdiv">
      <input type="hidden" name="save" value="0" id="saveIpt"/>
      <input type="hidden" name="departmentType" value="${departmentType?c}">
      [@b.submit value="action.save" onsubmit="saveAssess"/]
      [@b.submit value="action.submit" onsubmit="submitAssess"/]
    </div>
    <div style="display:none;" id="detailDiv"></div>
  [/@]  
  
  <script>
    jQuery.struts2_jquery.require("/js/base/jquery-ui.js",null,bg.getContextPath() + "/static");
    $(".voteMaxNum").html(${limitNum});
    function isallselected(){
      var ayessize = $("#voteTable .yes:checked").length;
      var limitNum = ${limitNum};
      var result = (ayessize <= limitNum);
      if(!result){
        alert("投票上限："+needsize+"\n已投票数："+ayessize);
        return false;
        }
      $("#voteTable tbody tr").filter(function(){
        return $(this).find(":checked").length == 0
      }).find(".no").attr("checked","checked");
      return result;
    }
    function saveAssess(){
      if(isallselected()){
        $("#saveIpt").val("1");
        return true;
      }
      return false;
    }
    
    function submitAssess(){
      if(isallselected() && confirm("提交后不能修改，是否确定提交？")){
        return true;
      }
      return false;
    }
    $(".assessTable tr").each(function (){
      $(this).find("td").not(function (i){return i == 7|| i==4;}).each(function (){
        var td = $(this);
        var content = td.html(), text = td.text(), max = 150; 
        td.html(text.substring(0, max));
        if(text.length > max){
          var a = $("<a href='#' style='margin-left:10px;'>查看更多...</a>");
          td.append(a);
          a.click(function (){
            var detailDiv = $("#detailDiv");
            detailDiv.empty().html(content);
            detailDiv.dialog({width:"90%", modal:true});
            return false;
          });
        }
      });
    });
    $(".assessTable th").filter(function (i){return i == 0 || i == 5 || i == 6}).each(function (i){
      var th = $(this);
      th.css("cursor", "pointer");
      th.hover(function (){
        th.css("background-color", "green").css("color", "#fff");
      }, function (){
        th.css("background-color", "#c7dbff").css("color", "#000");
      });
      th.click(function (){
        var thindex = $(this).index();
        var trs = new Array();
        $(".assessTable tbody tr").each(function (){
          trs.push($(this));
        });
        /*
        for(var i = 0; i < trs.length - 1; i++){
          for(var j = 1; j < trs.length - i; j++){
            var tr1 = trs[j - 1], tdtext1 = tr1.find("td").eq(thindex).text();
            var tr2 = trs[j], tdtext2 = tr2.find("td").eq(thindex).text();
            if(tdtext1 < tdtext2){
              trs[j - 1] = tr2;
              trs[j] =  tr1;
            }
          }
        }*/
        trs.sort(function (x, y){
            var tdtext1 = x.find("td").eq(thindex).text();
            var tdtext2 = y.find("td").eq(thindex).text();
            if(tdtext1 < tdtext2){
              return 1;
            }else{
              return -1;
            }
        });
        for(var i in trs){
          var tr = trs[i];
          tr.appendTo(".assessTable");
        }
       });
    });
    $(function (){
      $(".radiodiv").buttonset();
    });
  </script>
[@b.foot/]