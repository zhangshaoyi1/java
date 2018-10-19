<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>邮件模板管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#btnSubmit").click(function(){
				CKEDITOR.instances["text"].updateElement();
				var str = $("#text").val();
				var sum = 0;
				if(str.indexOf("{1}")<0 || str.indexOf("{2}")<0 || str.indexOf("{3}")<0 || str.indexOf("{4}")<0){
					top.$.jBox.confirm("{1}、{2}、{3}、{4}是关键字，请不要删除这些关键字","系统提示",function(v,h,f){
						if(v=="ok"){
							$("#searchForm").attr("action","${ctx}/jwcmis/email/form");
							$("#searchForm").submit();
						}
					},{buttonsFocus:1});
					top.$('.jbox-body .jbox-icon').css('top','55px');
				}
				str = str.replace("{1}","").replace("{2}","").replace("{3}","").replace("{4}","");
				if(str.indexOf("{1}")>0 || str.indexOf("{2}")>0 || str.indexOf("{3}")>0 || str.indexOf("{4}")>0){
					top.$.jBox.confirm("{1}、{2}、{3}、{4}是关键字，请不要多添加这些关键字","系统提示",function(v,h,f){
						if(v=="ok"){
							$("#searchForm").attr("action","${ctx}/jwcmis/email/form");
							$("#searchForm").submit();
						}
					},{buttonsFocus:1});
					top.$('.jbox-body .jbox-icon').css('top','55px');
				}
			});			
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/jwcmis/email/">邮件模板列表</a></li>
		<li class="active"><a href="${ctx}/jwcmis/email/form?id=${email.id}">邮件模板<shiro:hasPermission name="jwcmis:email:edit">${not empty email.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="jwcmis:email:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="email" action="${ctx}/jwcmis/email/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">模板名：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		
		<div class="control-group">
			<label class="control-label">内容:</label>
			<div class="controls">
				<label class="control-label;" style="color:red">{1}代表专家名，{2}代表硕士论文的账号，{3}代表硕士论文账户的密码，{4}代表硕士论文审批网址。请在修改模板的时候不要添加或者删除这几个关键字</label>
				<form:textarea id="text" htmlEscape="false" path="text" rows="4" maxlength="20000" class="input-xxlarge"/>
				<sys:ckeditor replace="text" uploadPath="/jwcmis/email" />
				
			</div>
			
		</div>
		<div class="control-group">
			<label class="control-label">邮件使用状态</label>
			<div class="controls" id="thesisStatus">
				<form:radiobuttons path="status" items="${fns:getDictList('emailStatus')}"  itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea id="remarks"  path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="jwcmis:email:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>