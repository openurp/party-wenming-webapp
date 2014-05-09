<#include "/template/head.ftl"/>
<script language="JavaScript" type="text/JavaScript" src="${base}/static/scripts/common/Validator.js"></script>
<script language="JavaScript" type="text/JavaScript" src="${base}/js/jquery-1.3.2.min.js"></script>
<script language="JavaScript" type="text/JavaScript" src="${base}/js/jquery.validate.min.js"></script>
<script language="JavaScript" type="text/JavaScript" src="${base}/js/messages_cn.js"></script>
<script language="JavaScript" type="text/JavaScript" src="${base}/js/jquery.metadata.js"></script>
<script language="JavaScript" type="text/JavaScript" src="${base}/js/myValidate.js"></script>
<link href="${base}/style/screen.css"  rel="stylesheet" type="text/css">
<link href="${base}/style/style_comm.css"  rel="stylesheet" type="text/css">
 <body>
 <table id="demoBar"></table> 
 <form name="userForm" action="assessBatch.action?method=save" method="post">
  <input type="hidden" name="assessBatch.id" value="${(assessBatch.id)?if_exists}"/>
  <table width="100%" id="table3" cellpadding="0" cellspacing="0">
    <tr>
      <td class="title1">基本信息</td>
    </tr>
   </table>
   
   <table width="100%"  id="table4" class="table4">
    <tr>
     <td class="title" style="width:20%"><font color="red">*</font>批次名称：</td>
     <td>
      <input type="text" name="assessBatch.name" value="${(assessBatch.name)?if_exists}" class="{maxlength:50,required:true} ip2"/>
     <td class="title" ><font color="red">*</font>创建时间：</td>
     <td>
      <input type="text" name="assessBatch.createTime" value="${(assessBatch.createTime?string("yyyy-MM-dd"))?if_exists}"  onFocus="WdatePicker()" class="{required:true} Wdate ip2" />
     </td>
   </tr>
   <tr>
     <td class="title">是否有效:</td>
     <td>
   		<select name="assessBatch.ifEnable" style="width:150px;">
   			<option value="true" <#if assessBatch.ifLeader?exists && assessBatch.ifEnable>selected=selected</#if>>是</option>
			<option value="false" <#if assessBatch.ifEnable?exists && !assessBatch.ifEnable >selected=selected</#if>>否</option>
		</select>
     </td>
     <td class="title">测评开关状态:</td>
     <td>
   		<select name="assessBatch.ifSwitch" style="width:150px;">
   			<option value="false" <#if assessBatch.ifSwitch?exists && !assessBatch.ifSwitch >selected=selected</#if>>关</option>
   			<option value="true" <#if assessBatch.ifSwitch?exists && assessBatch.ifSwitch>selected=selected</#if>>开</option>
		</select>
     </td>
   </tr>
   <tr>
     <td class="title" ><font color="red">*</font>测评开始时间：</td>
     <td >
      <input type="text" id="beginTime" name="assessBatch.beginTime" value="${(assessBatch.beginTime?string("yyyy-MM-dd"))?if_exists}"  onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')}'})" class="{required:true} Wdate ip2" />
     </td>
      <td class="title" ><font color="red">*</font>测评结束时间：</td>
      <td>
      	<input type="text" id="endTime" name="assessBatch.endTime" value="${(assessBatch.endTime?string("yyyy-MM-dd"))?if_exists}"  onFocus="WdatePicker({minDate:'#F{$dp.$D(\'beginTime\')}'})" class="{required:true} Wdate ip2" />
	  </td>
   </tr>
    <tr>
     <td class="title">投票开关状态:</td>
     <td>
   		<select name="assessBatch.ifVoteSwitch" style="width:150px;">
   			<option value="false" <#if assessBatch.ifVoteSwitch?exists && !assessBatch.ifVoteSwitch >selected=selected</#if>>关</option>
   			<option value="true" <#if assessBatch.ifVoteSwitch?exists && assessBatch.ifVoteSwitch>selected=selected</#if>>开</option>
		</select>
     </td>
      <td class="title" ><font color="red">*</font>投票开始时间：</td>
      <td>
      	<input type="text" id="voteBeginTime" name="assessBatch.voteBeginTime" value="${(assessBatch.voteBeginTime?string("yyyy-MM-dd"))?if_exists}"  onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'voteEndTime\')}',minDate:'#F{$dp.$D(\'endTime\')}'})" class="{required:true} Wdate ip2" />
	  </td>
   </tr>
   <tr>
      <td class="title" ><font color="red">*</font>投票结束时间：</td>
      <td colspan="3">
      	<input type="text" id="voteEndTime" name="assessBatch.voteEndTime" value="${(assessBatch.voteEndTime?string("yyyy-MM-dd"))?if_exists}"  onFocus="WdatePicker({minDate:'#F{$dp.$D(\'voteBeginTime\')}'})" class="{required:true} Wdate ip2" />
	  </td>
   </tr>
  </table>
  <table width="100%" class="formTable" style="padding:5px;">
   <tr class="darkColumn" align="center">
     <td colspan="8" height="50px" align="center" >
       <input type="hidden" name="courseTypeIds" />
       <input type="submit" value="<@text name="action.submit"/>" name="button1" class="buttonStyle" style="width:60;height:25"/>&nbsp;
       <input type="reset"  name="reset1" value="<@text name="action.reset"/>" class="buttonStyle" style="width:60;height:25"/>
     </td>
   </tr>   
 </table>
 <br><br><br><br> <br><br><br><br> <br><br><br><br> <br><br><br><br>
  </form>
  <script language="javascript" >
    var bar = new ToolBar('demoBar','考核批次编辑',null,true,true);
     bar.addBack("<@text name="action.back"/>");  
      $().ready(function(){
     	$("form[name='userForm']").validate();
     	//让class为table4 的偶数行变色
     		$(".table4 tr:odd").attr("class","td_bg_1");
     });
  </script>
 </body>
<#include "/template/foot.ftl"/>
