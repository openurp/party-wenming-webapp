[#ftl]
[@b.head/]
<table  class="indexpanel">
  <tr>
    <td class="index_view">
    [@b.form name="assessBatchSearchForm"  action="!search" target="assessBatchlist" title="ui.searchForm" theme="search"]
      [@b.textfields names="assessBatch.name;批次名称"/]
    [/@]
    </td>
    <td class="index_content">[@b.div id="assessBatchlist" href="!search?orderBy=assessBatch.indexno" /]</td>
  </tr>
</table>
[@b.foot/]
[#--
<#include "/template/head.ftl"/>
<body>
	<table id="bar"></table>
	<table class="frameTable" >
		<tr valign="top">
			<form method="post" action="" name="actionForm">
			<td class="frameTable_view" width="10%" height="30%">
			   <#include "searchForm.ftl"/>
		    </td>
		    </tr>
		    <tr>
			</form>
			<td><iframe name="pagesIFrame" src="#" width="100%" height="100%" frameborder="0" scrolling="no"></iframe></td>
		</tr>
	</table>
	<script>
		var form = document.actionForm;
		search();
		function search(mode) {
			form.action = "assessBatch.action?method=list";
			form.target = "pagesIFrame";
			form.submit();
		}
		(function(){
	    	var frame = document.getElementById("fragment"),
	        setIframeHeight = function(){
	            var frameContent = frame.contentWindow.document,
	            frameHeight = Math.max(frameContent.body.scrollHeight,frameContent.documentElement.scrollHeight);
	            frame.height = frameHeight;
	        $("#fragment").attr("height",frameHeight+180);
            $("#tabs").attr("height",frameHeight+180);
	   		};
		    if(frame.addEventListener){
		        frame.addEventListener("load",setIframeHeight+1,false);
		    }else{
		        frame.attachEvent("onload",setIframeHeight);
		    }
		})();
	</script>
</body>
<#include "/template/foot.ftl"/>
--]
