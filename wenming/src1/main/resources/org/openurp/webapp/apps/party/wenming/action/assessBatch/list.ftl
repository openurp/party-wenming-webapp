<#include "/template/head.ftl"/>
<script language="JavaScript" type="text/JavaScript" src="scripts/Menu.js"></script>
<body LEFTMARGIN="0" TOPMARGIN="0">
    <table id="assessBatchBar"></table>
    <@table.table width="100%" id="assessBatch" sortable="true">
        <@table.thead>
          <@table.selectAllTd id="assessBatchId"/>
          <@table.td width="10%" text="批次名称" id="assessBatch.name"/>
          <@table.td width="8%" text="创建时间" id="assessBatch.createTime"/>
          <@table.td width="8%" text="是否有效" id="assessBatch.ifEnable"/>
          <@table.td width="8%" text="测评开关状态" id="assessBatch.ifSwitch"/>
          <@table.td width="8%" text="测评开始时间" id="assessBatch.beginTime"/>
          <@table.td width="8%" text="测评结束时间" id="assessBatch.endTime"/>
          <@table.td width="8%" text="投票开关状态" id="assessBatch.ifVoteSwitch"/>
          <@table.td width="8%" text="投票开始时间" id="assessBatch.voteBeginTime"/>
          <@table.td width="8%" text="投票结束时间" id="assessBatch.voteEndTime"/>
        </@>
        <@table.tbody datas=assessBatchs;assessBatch>
            <@table.selectTd id="assessBatchId" value=assessBatch.id/>
            <td>${(assessBatch.name)?if_exists}</td>
            <td>${(assessBatch.createTime?string("yyyy-MM-dd"))?if_exists}</td>
 			<td><#if assessBatch.ifEnable><font color=green>是</font><#else><font color=red>否</font></#if></td>
 			<td><#if assessBatch.ifSwitch><font color=green>开</font><#else><font color=red>关</font></#if></td>
 			<td>${(assessBatch.beginTime?string("yyyy-MM-dd"))?if_exists}</td>
 			<td>${(assessBatch.endTime?string("yyyy-MM-dd"))?if_exists}</td>
 			<td><#if assessBatch.ifVoteSwitch><font color=green>开</font><#else><font color=red>关</font></#if></td>
 			<td>${(assessBatch.voteBeginTime?string("yyyy-MM-dd"))?if_exists}</td>
 			<td>${(assessBatch.voteEndTime?string("yyyy-MM-dd"))?if_exists}</td>
        </@>
    </@>
    <@htm.actionForm name="actionForm" action="assessBatch.action" entity="assessBatch" onsubmit="return false;">
    </@>
	<script>
	    var bar = new ToolBar('assessBatchBar','考核批次列表',null,true,true);
	    bar.setMessage('<@getMessage/>');
	    bar.addItem("<@text name="action.new"/>","add()",'new.gif');
	   	bar.addItem("<@text name="action.modify"/>","edit()",'update.gif');
	   	bar.addItem("<@text name="action.delete"/>","remove()");
	   	bar.addItem("注销","logout()",'update.gif');
	   	 function getIds(){
       		return(getCheckBoxValue(actionForm.document.getElementsByName("assessBatchId")));
   	    }
   	    function logout(){
	       var len=$("input[type='checkbox'][name='assessBatchId']:checked").length;
	       if(len==0){
		  	 alert("你没有选择要操作的记录！");
		  	 return false;
		   }else{
		    if(confirm("确认注销该批次？")){
 			  var assessBatchIds=getIds();
	  	 	  location.href="assessBatch.action?method=logout&assessBatchIds="+assessBatchIds;
		 	}else{
		 	  return false;
		 	}
		  }
	    }
</script>
</body>
<#include "/template/foot.ftl"/> 
