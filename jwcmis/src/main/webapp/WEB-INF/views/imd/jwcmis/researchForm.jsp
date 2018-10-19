<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>研究方向信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		var researchOld = "";
		$(document).ready(function() {
			$("#inputForm").validate({
				submitHandler: function(form){
					if(checkResearchName()){
						loading('正在提交，请稍等...');
						form.submit();
					}else{
						alert("该研究方向已经存在!");
						return false;
					}
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
			researchOld = $("#research").val();
		});
		
		function checkResearchName() {
			var researchNew = research.value;
			var checkResult = true;
			var researchName = "";
			if(researchOld != researchNew && researchNew != null && researchNew != ""){		//判断是否更改
				if(researchNew){
					//由于Ajax中文传值会乱码，所以需要先翻译成utf-8内码，然后在控制台转成中文，这种转译需要连URL一起转译
					var url = "${ctx}/jwcmis/research/checkResearchName?researchName=" + researchNew;
					url = encodeURI(encodeURI(url));
					$.ajax({
						async:false,
						type:"post",
						url:url,
						contentType:"application/x-www-form-urlencoded; charset=utf-8",
						dataType:"json",
						error : function() {
					        alert('请求失败！请刷新页面重试！ ');
					    },
					    success : function(data) {
					    	if (data == 0) {
					    		research_notice.innerHTML = "验证通过!";
					    		research_notice.style.color = 'green';
							}else{
					    		research_notice.innerHTML = "研究方向已经存在!";
					    		research_notice.style.color = 'red';
								checkResult = false;
							}
					    }
					});
				}	
			}else if(researchNew != null && researchNew != ""){
				research_notice.innerHTML = "验证通过!";
	    		research_notice.style.color = 'green';
				}else{
					research_notice.innerHTML = "请输入研究方向信息!";
		    		research_notice.style.color = 'red';
					checkResult = false;
				}
			return checkResult;
		}
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/jwcmis/research/">研究方向信息列表</a></li>
		<li class="active"><a href="${ctx}/jwcmis/research/form?id=${research.id}">研究方向信息<shiro:hasPermission name="jwcmis:research:edit">${not empty research.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="jwcmis:research:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="research" action="${ctx}/jwcmis/research/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">研究方向名：</label>
			<div class="controls">
				<form:input path="name" id="research" oninput="checkResearchName()" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
				<span class="help-inline" id="research_notice"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="jwcmis:research:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>