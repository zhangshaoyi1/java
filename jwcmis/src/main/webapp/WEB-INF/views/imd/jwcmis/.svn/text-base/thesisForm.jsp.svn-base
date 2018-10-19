<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>论文信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
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
		function addRow(list, idx, tpl, row){
			if(idx<=1){
				$(list).append(Mustache.render(tpl, {
					idx: idx, delBtn: true, row: row
				}));
				$(list+idx).find("select").each(function(){
					$(this).val($(this).attr("data-value"));
				});
				$(list+idx).find("input[type='checkbox'], input[type='radio']").each(function(){
					var ss = $(this).attr("data-value").split(',');
					for (var i=0; i<ss.length; i++){
						if($(this).val() == ss[i]){
							$(this).attr("checked","checked");
						}
					}
				});
			}else {
				$(this).attr("disabled","disabled");
				return function(){
					top.$.jBox.confirm("一条论文记录至多只能添加两条用户数据！","系统提示",function(v,h,f){
						if(v=="ok"){
							$("#searchForm").attr("action","${ctx}/jwcmis/thesis/form");
							$("#searchForm").submit();
						}
					},{buttonsFocus:1});
					top.$('.jbox-body .jbox-icon').css('top','55px');
				}();
			} 
		}
		function delRow(obj, prefix){
			var id = $(prefix+"_id");
			var delFlag = $(prefix+"_delFlag");
			if (id.val() == ""){
				$(obj).parent().parent().remove();
			}else if(delFlag.val() == "0"){
				delFlag.val("1");
				$(obj).html("&divide;").attr("title", "撤销删除");
				$(obj).parent().parent().addClass("error");
			}else if(delFlag.val() == "1"){
				delFlag.val("0");
				$(obj).html("&times;").attr("title", "删除");
				$(obj).parent().parent().removeClass("error");
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/jwcmis/thesis/">论文信息列表</a></li>
		<li class="active"><a href="${ctx}/jwcmis/thesis/form?id=${thesis.id}">论文信息<shiro:hasPermission name="jwcmis:thesis:edit">${not empty thesis.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="jwcmis:thesis:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="thesis" action="${ctx}/jwcmis/thesis/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">审评单位：</label>
			<div class="controls">
				<form:input path="reviewdepart" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">审评截止时间：</label>
			<div class="controls">
				<input name="reviewdeadline" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${thesis.reviewdeadline}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">学号：</label>
			<div class="controls">
				<form:input path="studentid" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">硕士名：</label>
			<div class="controls">
				<form:input path="mastername" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">导师：</label>
			<div class="controls">
				<form:input path="supervisor" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">论文编号：</label>
			<div class="controls">
				<form:input path="thesisnumber" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">专业：</label>
			<div class="controls">
				<form:input path="major" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">论文主题：</label>
			<div class="controls">
				<form:input path="topic" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">关键字：</label>
			<div class="controls">
				<form:input path="keywords" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">论文分配评审状态</label>
			<div class="controls" id="thesisStatus">
				<form:radiobuttons path="status" items="${fns:getDictList('thesisStatus')}"  itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">网址：</label>
			<div class="controls">
				<form:input path="url" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
			<div class="control-group">
				<label class="control-label">登入账户：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>用户名</th>
								<th>密码</th>
								<th>备注信息</th>
								<shiro:hasPermission name="jwcmis:thesis:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="accountList">
						</tbody>
						<shiro:hasPermission name="jwcmis:thesis:edit"><tfoot>
							<tr><td colspan="5"><a href="javascript:" onclick="addRow('#accountList', accountRowIdx, accountTpl);accountRowIdx=accountRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="accountTpl">//<!--
						<tr id="accountList{{idx}}">
							<td class="hide">
								<input id="accountList{{idx}}_id" name="accountList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="accountList{{idx}}_delFlag" name="accountList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="accountList{{idx}}_username" name="accountList[{{idx}}].username" type="text" value="{{row.username}}" maxlength="64" class="input-small required"/>
							</td>
							<td>
								<input id="accountList{{idx}}_password" name="accountList[{{idx}}].password" type="text" value="{{row.password}}" maxlength="64" class="input-small required"/>
							</td>
							<td>
								<textarea id="accountList{{idx}}_remarks" name="accountList[{{idx}}].remarks" rows="4" maxlength="255" class="input-small ">{{row.remarks}}</textarea>
							</td>
							<shiro:hasPermission name="jwcmis:thesis:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#accountList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var accountRowIdx = 0;
						accountTpl = $("#accountTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(thesis.accountList)};
							for (var i=0; i<data.length; i++){
								addRow('#accountList', accountRowIdx, accountTpl, data[i]);
								accountRowIdx = accountRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
		<div class="form-actions">
			<shiro:hasPermission name="jwcmis:thesis:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
	<script>
		if(${thesis.status}==1||${thesis.status}==2){
			$("#thesisStatus").find("input").attr("disabled","disable");
			$("#thesisStatus").find("font").text("*(已分配或评审，不可修改)");
		}
	</script>
</body>
</html>