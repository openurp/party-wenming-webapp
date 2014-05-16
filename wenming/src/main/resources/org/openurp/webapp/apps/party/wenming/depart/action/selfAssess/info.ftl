[#ftl]
[@b.head/]
[@b.messages slash="3"/]
<div>
  [#if !selfAssess??]
    <div style="margin-left:10px;">
      没有自评内容
      [#if addable??]
        ，[@b.a href="!edit"]开始自评[/@]
      [/#if]
    </div> 
  [#else]
    [#include "infoTable.ftl"/]
    [#if modifyable??]
      [@b.form action="!edit"]
        <div class="footdiv">
          [@b.submit value="修改"/]
        </div>
      [/@]
    [/#if]
  [/#if]
</div>
[@b.foot/]