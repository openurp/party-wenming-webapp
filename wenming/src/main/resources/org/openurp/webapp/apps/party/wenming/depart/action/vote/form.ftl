[#ftl]
[@b.head/]
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
    <table id="voteTable" class="gridtable assessTable">
      <thead>
        <tr>
          <th width="10">测评单位</th>
[#--
          <th width="50">自评总分</th>
          <th width="50">互评总分</th>
          <th width="50">职能部门测评总分</th>
          <th width="50">督察组测评总分</th>
--]
          <th width="150">创建活动及其效果</th>
          <th width="150">文明创建特色</th>
          <th width="330">详实材料</th>
          <th width="10">相关支撑材料</th>
          <th width="10">加分项分值</th>
          <th width="10">测评总分</th>
          <th width="50">投票（投票上限：<span class="voteMaxNum" style="color:red"></span>票）</th>
        </tr>
      </thead>
      <tbody>
        [#list assessVotes?sort_by(["department","code"]) as vote]
        <tr>
          [#assign name="vote${vote_index}"/]
          <input type="hidden" name="index" value="${name}"/>
          <input type="hidden" name="${name}.department.id" value="${vote.department.id}"/>
          <input type="hidden" name="${name}.id" value="${vote.id!}"/>
          <td align="center">${vote.department.name}</td>
[#--
          <td>${selfAssessScore[vote.department.id+""]!}</td>
          <td>${mutualAssessScore[vote.department.id+""]!}</td>
          <td>${funcDepartAssessScore[vote.department.id+""]!}</td>
          <td>${supervisorAssessScore[vote.department.id+""]!}</td>
--]
          <td>
            [#list assessApplies as assessApply]
              [#if assessApply.department.id == vote.department.id]
                ${assessApply.activitySummary}
              [/#if]
            [/#list]
          </td>
          <td>
            [#list assessApplies as assessApply]
              [#if assessApply.department.id == vote.department.id]
                ${assessApply.wenmingSummary}
              [/#if]
            [/#list]
          </td>
          <td>
            [#list assessApplies as assessApply]
              [#if assessApply.department.id == vote.department.id]
                ${assessApply.detail}
              [/#if]
            [/#list]
          </td>
          <td align="center">
            [#list assessApplies as assessApply]
              [#if assessApply.department.id == vote.department.id && assessApply.attachment ??]
                [@b.a target="_blank" href="../attachment?path=${assessApply.attachment.filePath}&name=${assessApply.attachment.name?url('utf-8')}"]
                下载
                [/@]
              [/#if]
            [/#list]
          </td>
          <td align="center">
            [#list assessApplies as assessApply]
              [#if assessApply.department.id == vote.department.id]
              ${assessApply.bonus}
              [/#if]
            [/#list]
          </td>
          <td align="center">${totalScoreMap[vote.department.id+""]!}</td>
          <td align="center">
            <input type="radio" name="${name}.ayes" value="1" id="${name}1" class="yes" [#if vote.ayes] checked="checked" [/#if] class="radio_true"/>
            <lable for="${name}1">是</lable>
            <input type="radio" name="${name}.ayes" value="0" id="${name}0" class="no" [#if vote.id?? && !vote.ayes] checked="checked" [/#if]/>
            <lable for="${name}0">否</lable>
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
  [/@]  
    
  <script>
    var needsize = Math.round($("#voteTable tbody tr").length/5);
    $(".voteMaxNum").html(needsize);
    function isallselected(){
      var ayessize = $("#voteTable .yes:checked").length;
      var result = (ayessize <= needsize);
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
        td.attr("a", 11);
        td.html(td.text());
      });
    });
    //$(".radio_true").attr("checked","checked");
  </script>
[@b.foot/]