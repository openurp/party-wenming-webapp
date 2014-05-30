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
         <td class="title">最多可投票数:</td>
         <td class="content voteMaxNum" ></td>
        </tr>
      </table>
    [/#if]
    <table id="voteTable" class="gridtable assessTable">
      <thead>
        <tr>
          <th width="100">测评单位</th>
          <th width="50">自评总分</th>
          <th width="50">互评总分</th>
          <th width="50">职能部门测评总分</th>
          <th width="50">督察组测评总分</th>
          <th width="100">相关支撑材料</th>
          <th width="50">加分项加分分值</th>
          <th width="50">测评总分</th>
          <th width="50">是否投票</th>
        </tr>
      </thead>
      <tbody>
        [#list assessVotes?sort_by(["department","code"]) as vote]
        <tr>
          [#assign name="vote${vote_index}"/]
          <input type="hidden" name="index" value="${name}"/>
          <input type="hidden" name="${name}.department.id" value="${vote.department.id}"/>
          <input type="hidden" name="${name}.id" value="${vote.id!}"/>
          <td>${vote.department.name}</td>
          <td>${selfAssessScore[vote.department.id+""]!}</td>
          <td>${mutualAssessScore[vote.department.id+""]!}</td>
          <td>${funcDepartAssessScore[vote.department.id+""]!}</td>
          <td>${supervisorAssessScore[vote.department.id+""]!}</td>
          <td>
            [#list assessApplies as assessApply]
              [#if assessApply.department.id == vote.department.id && assessApply.attachment ??]
                [@b.a target="_blank" href="../attachment?path=${assessApply.attachment.filePath}&name=${assessApply.attachment.name?url('utf-8')}"]
                ${assessApply.attachment.name}
                [/@]
              [/#if]
            [/#list]
          </td>
          <td>
            [#list assessApplies as assessApply]
              [#if assessApply.department.id == vote.department.id]
              ${assessApply.bonus}
              [/#if]
            [/#list]
          </td>
          <td>${totalScoreMap[vote.department.id+""]!}</td>
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
    var needsize = Math.round($("#voteTable tbody tr").length/10);
    $(".voteMaxNum").html(needsize);
    function isallselected(){
      var ayessize = $("#voteTable .yes:checked").length;
      var result = (ayessize <= needsize);
      if(!result){
        alert("最多可投票数为："+needsize+"\n已投票数为："+ayessize);
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
    
    //$(".radio_true").attr("checked","checked");
  </script>
[@b.foot/]