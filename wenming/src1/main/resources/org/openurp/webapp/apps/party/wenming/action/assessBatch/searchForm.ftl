<table width="100%" >
    <tr>
      <td colspan="2" class="infoTitle" align="left" valign="bottom">
       <img src="${base}/static/images/action/info.gif" align="top"/>
          <B><@text name="模糊查询"/></B>
      </td>
    <tr>
      <td  colspan="8" style="font-size:0px">
          <img src="${base}/static/images/action/keyline.gif" height="2" width="100%" align="top">
      </td>

    </tr>
    <tr> 
     <td class="infoTitle">批次名称:</td>
     <td><input name="assessBatch.name" type="text" class="ip2" maxlength="30"/>
     </td> 
     <td class="infoTitle">是否有效:</td>
     <td>
     <select name="assessBatch.ifEnable" style="width:150px;">
		    <option value="">...</option>
			<option value="true">是</option>
			<option value="false">否</option>
		</select>
     </td>
     <td colspan="2" align="center">
	      <fieldSet align=center> 
	       <legend>创建时间:</legend>
	                   开始:<input type="text" id="star_startTime" name="beginTime" onFocus="var end_startTime=$dp.$('end_startTime');WdatePicker({onpicked:function(){end_startTime.focus();},maxDate:'#F{$dp.$D(\'end_startTime\')}'})" class="Wdate" maxlength="10" value="" style="width:130"/>
&nbsp;&nbsp;&nbsp;&nbsp;结束:<input type="text" id="end_startTime" name="endTime"  onFocus="WdatePicker({minDate:'#F{$dp.$D(\'star_startTime\')}'})" maxlength="10" value="" style="width:130"  class="Wdate" />
	       </legend>
	      </fieldSet>
	 </td>
    </tr>
    <tr align="center" height="50px">
     <td colspan="8">
	     <button onclick="search()" class="buttonStyle" style="width:60px">
	     <@text name="action.query"/>
	     </button>
     </td>
    </tr>
  </table>