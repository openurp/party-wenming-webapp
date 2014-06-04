[#ftl]
[@b.head/]
  [@b.form action="!edit"]
    [#if assessVotes?? && assessVotes?size gt 0]
      [#assign vote = assessVotes[0]/]
      <table class="infoTable">
        <tr>
         <td class="title">投票人:</td>
         <td class="content">${vote.voter.fullname}</td>
         <td class="title">投票修改时间:</td>
         <td class="content">${vote.updatedAt?string("YYYY-MM-dd HH:mm")}</td>
         <td class="title">最多可投票数:</td>
         <td class="content voteMaxNum"></td>
         <td class="content" style="color:red">确认投票请选择"是"</td>
        </tr>
      </table>
    [/#if]
    <table id="voteTable" class="gridtable assessTable">
      <thead>
        <tr>
          <th width="55">测评单位</th>
[#--
          <th width="50">自评总分</th>
          <th width="50">互评总分</th>
          <th width="50">职能部门测评总分</th>
          <th width="50">督察组测评总分</th>
          <th width="100">相关支撑材料</th>
--]
          <th width="200">创建活动及其效果</th>
          <th width="200">文明创建特色</th>
          <th width="20">加分项加分分值</th>
          <th width="20">测评总分</th>
          <th width="50">是否投票</th>
        </tr>
      </thead>
      <tbody>
        [#list assessVotes?sort_by("ayes")?reverse as vote]
        <tr>
          <td align="center">${vote.department.name}</td>
[#--
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
          <td align="center">
            [#list assessApplies as assessApply]
              [#if assessApply.department.id == vote.department.id]
              ${assessApply.bonus}
              [/#if]
            [/#list]
          </td align="center">
          <td>${totalScoreMap[vote.department.id+""]!}</td>
          <td align="center">
            [#if vote.ayes]是
            [#else]否  
            [/#if]
          </td>
        </tr>
        [/#list]
      </tbody>
    </table>
      [#if modifyable??]
          <div style="text-align:center; padding:30px;" class="footdiv">
          <input type="hidden" name="departmentType" value="${departmentType?c}">
          <input type="hidden" name="session.id" value="${assessSession.id}">
            [@b.submit value="修改"/]
          </div>
      [/#if]
  [/@]  
[@b.foot/]
<script>
  var needsize = Math.round($("#voteTable tbody tr").length/10);
  $(".voteMaxNum").html(needsize);
</script>