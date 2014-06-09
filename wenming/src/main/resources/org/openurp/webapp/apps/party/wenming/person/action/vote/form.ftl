[#ftl]
[@b.head/]
  [@b.form action="!save"]
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
          <th width="10%">项目名称</th>
          <th width="10%">选送单位</th>
          <th width="30%">事迹</th>
          <th width="30%">特色与创新点</th>
          <th width="10%">相关支撑材料</th>
          <th width="10%">投票（投票上限：<span style="color:red">5</span>票）</th>
        </tr>
      </thead>
      <tbody>
        [#list abstractWenmingVotes?sort_by(["goodPerson","name"]) as vote]
        <tr>
          [#assign name="vote${vote_index}"/]
          <input type="hidden" name="index" value="${name}"/>
          <input type="hidden" name="${name}.goodPerson.id" value="${vote.goodPerson.id}"/>
          <input type="hidden" name="${name}.id" value="${vote.id!}"/>
          <td align="center">${vote.goodPerson.name}</td>
          <td align="center">${vote.goodPerson.department.name}</td>
          <td align="center">${vote.goodPerson.statements}</td>
          <td align="center">${vote.goodPerson.features}</td>
          <td align="center">
            [#if vote.goodPerson.attachment??]
              [@b.a target="_blank" href="../attachment?path=${vote.goodPerson.attachment.filePath}&name=${vote.goodPerson.attachment.name?url('utf-8')}"]
              下载
              [/@]
            [/#if]
          </td>
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
      [@b.submit value="action.save" onsubmit="saveAssess"/]
      [@b.submit value="action.submit" onsubmit="submitAssess"/]
    </div>
    <div style="display:none;" id="detailDiv"></div>
  [/@]  
  
  <script>
    jQuery.struts2_jquery.require("/js/base/jquery-ui.js",null,bg.getContextPath() + "/static");
    function isallselected(){
      var ayessize = $("#voteTable .yes:checked").length;
      var result = (ayessize <= 5);
      if(!result){
        alert("投票上限：5\n已投票数："+ayessize);
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
      $(this).find("td").filter(function (i){return i == 2||i == 3;}).each(function (){
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
    $(".assessTable th").filter(function (i){return i == 0 || i == 1 }).each(function (i){
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
    //$(".radio_true").attr("checked","checked");
  </script>
[@b.foot/]