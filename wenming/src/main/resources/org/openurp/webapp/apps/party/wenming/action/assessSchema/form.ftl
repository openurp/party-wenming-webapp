[#ftl]
[@b.head/]
[@b.toolbar title="测评方案编辑"]bar.addBack();[/@]
[@b.tabs]
  [@b.tab label="测评方案信息"]
    [@b.form action="!save" title="基本信息" theme="list" name="schema_form"]
      [@b.textfield name="assessSchema.name" label="方案名称" value="${assessSchema.name!}" required="true" maxlength="50"/]
      [@b.radios name="assessSchema.forTeaching" value=assessSchema.forTeaching items={'1':'是','0':'否'} label="针对教学部门" required="true"/]
      [@b.select2 label="包含部门" name1st="departmentId" name2nd="departId" 
        items1st=departments items2nd= assessSchema.departs?sort_by("code") 
        option="id,name"/]
      [@b.formfoot]
        <input type="hidden" name="assessSchema.id" value="${assessSchema.id!}" />
        [@b.reset/]&nbsp;&nbsp;[@b.submit value="action.submit"/]
      [/@]
    [/@]
  [/@]
[/@]
<script>
    departs = {}
    [#list allDepartments as d]
    departs['${d.id}']={name:"${d.name}",teaching:${d.teaching?string("true","false")}}
    [/#list]
    function removeFuncDepart(){
       jQuery("#schema_form select option").each(function(idx,obj){
          if(!departs[obj.value].teaching) obj.remove();
       })
    }
    function removeTeaching(){
       jQuery("#schema_form select option").each(function(idx,obj){
          if(departs[obj.value].teaching) obj.remove();
       })
    }
    [#if assessSchema.forTeaching]removeFuncDepart();[#else]removeTeaching()[/#if]
     jQuery("#schema_form :radio").bind("click",function(e){
        if(e.target.value=="1"){
         removeFuncDepart();
        }else{
         removeTeaching();
        }
     });
</script>
[@b.foot/]