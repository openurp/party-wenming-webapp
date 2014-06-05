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
        </tr>
      </table>
    [/#if]
    <table id="voteTable" class="gridtable assessTable">
      <thead>
        <tr>
          <th width="4%">测评单位</th>
[#--
          <th width="50">自评总分</th>
          <th width="50">互评总分</th>
          <th width="50">职能部门测评总分</th>
          <th width="50">督察组测评总分</th>
--]
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
        [#list assessVotes?sort_by("ayes")?reverse as vote]
        <tr>
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
  jQuery.struts2_jquery.require("/js/base/jquery-ui.js",null,bg.getContextPath() + "/static");
  var needsize = Math.round($("#voteTable tbody tr").length/5);
  $(".voteMaxNum").html(needsize);
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
</script>