[#ftl]
[@b.head title="Login" loadui=false/]
<script type="text/javascript">
  if(this.parent!=this){ this.top.location="${b.url('!login')}"; }
</script>
<div style="text-align:center;margin-top:150px;border:4px">文明项目投票登陆系统</div>
<div >
  [@b.form name="loginForm" action="!login" target="_top"]
  <table style="margin:auto;">
    <tr><td colspan="3">${message!}</td></tr>
    <tr>
      <td><label for="username"><strong>用户名:</strong></label></td>
      <td><input name="username" id="username" tabindex="1" title="请输入用户名" type="text" autofocus="autofocus" value="${(Parameters['username']!)?html}" style="width:300px;"/></td>
      <td rowspan="3" valign="top">
      [@b.submit name="submitBtn" tabindex="6" style="height:35pt;width:38pt;" value="登录" onsubmit="checkLogin"][/@]
      </td>
    </tr>
    <tr>
      <td><label for="password"><strong>密码:</strong></label></td>
      <td><input id="password" name="password"  tabindex="2" type="password" style="width:300px;"/>
      <input name="encodedPassword" type="hidden" value=""/></td>
    </tr>
  </table>
  [/@]
</div>
<script type="text/javascript">
  var form  = document.loginForm;
  function checkLogin(form){
    if(!form['username'].value){
      alert("用户名称不能为空");return false;
    }
    if(!form['password'].value){
      alert("密码不能为空");return false;
    }
    return true;
  }
</script>

[@b.foot/]
