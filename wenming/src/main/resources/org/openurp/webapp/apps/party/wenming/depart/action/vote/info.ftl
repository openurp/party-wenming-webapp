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
         [#if isSubmit??]
           <td class="title">投票状态:</td>
           <td style="color:red">已完成投票</td>
         [/#if]
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
          <th width="8%">投票（投票上限：<span class="voteMaxNum" style="color:red">${limitNum}</span>票）</th>
        </tr>
      </thead>
      <tbody>
        [#list assessVotes?sort_by("ayes")?reverse as vote]
        <tr>
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
          </td align="center">
          <td>${totalScoreMap[vote.department.id+""]!}</td>
          <td align="center">
            [#if vote.ayes]<span class="toolbar-icon action-activate"></span>
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