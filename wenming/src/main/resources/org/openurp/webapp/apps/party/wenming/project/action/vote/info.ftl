[#ftl]
[@b.head/]
  [@b.form action="!edit"]
    [#if abstractWenmingVotes?? && abstractWenmingVotes?size gt 0]
      [#assign vote = abstractWenmingVotes[0]/]
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
          <th width="2%">序号</th>
          <th width="10%">项目名称</th>
          <th width="7%">选送单位</th>
          <th width="33%">项目方案</th>
          <th width="34%">特色与创新点</th>
          <th width="7%">材料</th>
          <th width="7%">投票（投票上限：<span style="color:red">5</span>票）</th>
        </tr>
      </thead>
      <tbody>
        [#list abstractWenmingVotes?sort_by("ayes")?reverse as vote]
        <tr>
          <td align="center">${vote_index+1}</td>
          <td align="center">${vote.goodProject.name}</td>
          <td align="center">${vote.goodProject.department.name}</td>
          <td align="center">${vote.goodProject.plan}</td>
          <td align="center">${vote.goodProject.features}</td>
          <td align="center">
            [#if vote.goodProject.attachment??]
              [@b.a target="_blank" href="../attachment?path=${vote.goodProject.attachment.filePath}&name=${vote.goodProject.attachment.name?url('utf-8')}"]
              支撑材料
              [/@]
            [/#if]
            [#if vote.goodProject.middleSummary ?? && vote.goodProject.middleSummary.attachment??]
              [@b.a target="_blank" href="../attachment?path=${vote.goodProject.middleSummary.attachment.filePath}&name=${vote.goodProject.middleSummary.attachment.name?url('utf-8')}"]
              <br/>中期审核材料
              [/@]
            [/#if]
            [#if vote.goodProject.finalSummary?? && vote.goodProject.finalSummary.attachment??]
              [@b.a target="_blank" href="../attachment?path=${vote.goodProject.finalSummary.attachment.filePath}&name=${vote.goodProject.finalSummary.attachment.name?url('utf-8')}"]
              <br/>终期审核材料
              [/@]
            [/#if]
          </td>
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
          <input type="hidden" name="session.id" value="${wenmingSession.id}">
            [@b.submit value="修改"/]
          </div>
      [/#if]
  [/@]  
  
  <script>
    jQuery.struts2_jquery.require("/js/base/jquery-ui.js",null,bg.getContextPath() + "/static");
    $(".assessTable tr").each(function (){
      $(this).find("td").filter(function (i){return i == 2|| i==3;}).each(function (){
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
[@b.foot/]