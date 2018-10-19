<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>专家信息管理</title>
	<meta name="decorator" content="default" charset="UTF-8" />
	<script type="text/javascript">
		var emailOld = "";
		var phoneOld = "";
		var bankCardNumberOld = "";
		$(document).ready(function() {
			$("#inputForm").validate({
				submitHandler: function (form){
					if(checkEmail()){
						if(checkPhone()) {
							if(checkBankCardNumber()){
								loading('正在提交，请稍等...');
								form.submit();
							}else {
								alert("银行卡号验证未通过! ");
								return false;
							}
						}else {
							alert("手机号码验证未通过! ");
							return false;
						}
					}else{
						alert("邮箱验证未通过! ");
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
			//已分配论文数填充
			var distributeNum = $("#distributeNum").val();
			if(distributeNum == null || distributeNum == ""){
				$("#distributeNum").val(0);
			}
			//获取原有的邮箱、手机号、银行卡号
			emailOld = $("#email").val();
			phoneOld = $("#phone").val();
			bankCardNumberOld = $("#bankCardNumber").val();
		});	
		
		//全局变量，用于传送需要查重的邮箱、手机号、银行卡号值
		var checkString = "";
		//邮箱验证
		function checkEmail() {
			//邮箱的正则表达式
			var emailReg = /^\w+@[a-zA-Z0-9]{2,10}(?:\.[a-z]{2,4}){1,3}$/;
			//实时获取邮箱
			var emailNew = email.value;
			//标志变量
			var checkResult = true;
			if(emailOld != emailNew && emailNew != null & emailNew != ""){		//判断是否更改
				if(emailReg.test(emailNew)){
					$.ajax({
						async:false,
						type:"post",
						url:"${ctx}/jwcmis/expert/checkRepeat?checkString=" + emailNew,
						dataType:'html',
						error : function() {
					        alert('请求失败！请刷新页面重试！ ');
					    },
					    success : function(data) {
					    	if (data == 0) {
					    		email_notice.innerHTML = '邮箱验证通过！';
								email_notice.style.color = 'green';
							}
							else {
								email_notice.innerHTML = "邮箱已经被占用!";
								email_notice.style.color = 'red';
								checkResult = false;
							}
					    }
					});
				}else {
					email_notice.innerHTML = '邮箱验证未通过，请重新输入！';
					email_notice.style.color = 'red';
					checkResult = false;
				}
			}else if(emailNew != null & emailNew != ""){
				email_notice.innerHTML = '邮箱验证通过！';
				email_notice.style.color = 'green';
				}else{
					email_notice.innerHTML = '请输入邮箱！';
					email_notice.style.color = 'red';
					checkResult = false;
				}
			return checkResult;
		}
		
		//手机号码验证
		function checkPhone() {
			//手机号的正则表达式
			var phoneReg = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
			//实时获取手机号
			var phoneNew = phone.value;
			var checkResult = true;
			if(phoneOld != phoneNew && phoneNew != null && phoneNew != ""){
				if(phoneReg.test(phoneNew)){
					$.ajax({
						async:false,
						type:"post",
						url:"${ctx}/jwcmis/expert/checkRepeat?checkString=" + phoneNew,
						dataType:'html',
						error : function() {
					        alert('请求失败！请刷新页面重试！ ');
					    },
					    success : function(data) {
					    	if (data == 0) {
					    		phone_notice.innerHTML = '手机号码验证通过！';
					    		phone_notice.style.color = 'green';
							}
							else {
								phone_notice.innerHTML = "手机号码已经被占用!";
								phone_notice.style.color = 'red';
								checkResult = false;
							}
					    }
					});
				}else {
					phone_notice.innerHTML = '手机号码验证未通过！';
					phone_notice.style.color = 'red';
					checkResult = false;
				}
			}else if(phoneNew != null && phoneNew != ""){
				phone_notice.innerHTML = '手机号码验证通过！';
		   		phone_notice.style.color = 'green';
				}else{
					phone_notice.innerHTML = '请输入手机号码！';
					phone_notice.style.color = 'red';
					checkResult = false;
				}
			return checkResult;
		}
		
		//银行卡号验证
		function checkBankCardNumber() {
			//实时获取银行卡号
			var bankCardNumberNew = bankCardNumber.value;
			if(bankCardNumberOld != bankCardNumberNew && bankCardNumberNew != null && bankCardNumberNew != ""){
				//长度校验
				if(bankCardNumberNew == "" || bankCardNumberNew.length < 16 || bankCardNumberNew.length > 19) {
					bankCardNumber_notice.innerHTML = "银行卡号位数必须在16~19之间，请完整输入银行卡号！";
					bankCardNumber_notice.style.color = 'red';
					return false;
				}
				//开头6位校验
				var strBin="10,18,30,35,37,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,58,60,62,65,68,69,84,87,88,94,95,98,99";    
				if(strBin.indexOf(bankCardNumberNew.substring(0, 2)) == -1) {
					bankCardNumber_notice.innerHTML = "银行卡号开头6位不符合规范，请检查后重新输入！";
					bankCardNumber_notice.style.color = 'red';
					return false;
				}
				//全数字校验
				var num = /^\d*$/;
				if( !num.exec(bankCardNumberNew)) {
					bankCardNumber_notice.innerHTML = "银行卡号必须全为数字，请检查后重新输入！";
					bankCardNumber_notice.style.color = 'red';
					return false;
				}
				//Luhm验证
				var lastNum = bankCardNumberNew.substr(bankCardNumberNew.length-1, 1);//取出最后一位（与luhm进行比较）
				var first15Num = bankCardNumberNew.substr(0, bankCardNumberNew.length-1);//前15或18位
				var newArr = new Array();
				for(var i = first15Num.length-1; i > -1; i --){	//前15或18位倒序存进数组
					newArr.push(first15Num.substr(i, 1));
				}
				var arrJiShu = new Array();					//奇数位*2的积 <9
				var arrJiShu2 = new Array();				//奇数位*2的积 >9
				var arrOuShu = new Array();					//偶数位数组
				for(var j = 0; j < newArr.length; j++){
					if( (j+1)%2 == 1) {							//奇数位
						if(parseInt(newArr[j]) * 2 < 9)
							arrJiShu.push(parseInt(newArr[j]) * 2);
						else
							arrJiShu2.push(parseInt(newArr[j]) * 2);
					}else									//偶数位
						arrOuShu.push(newArr[j]);
				}
				var jishu_child1 = new Array();//奇数位*2 >9 的分割之后的数组个位数
				var jishu_child2 = new Array();//奇数位*2 >9 的分割之后的数组十位数
				for(var h = 0; h < arrJiShu2.length; h++) {
				    jishu_child1.push(parseInt(arrJiShu2[h]) % 10);
				    jishu_child2.push(parseInt(arrJiShu2[h]) / 10);
				}        
				var sumJiShu = 0; //奇数位*2 < 9 的数组之和
				var sumOuShu = 0; //偶数位数组之和
				var sumJiShuChild1 = 0; //奇数位*2 >9 的分割之后的数组个位数之和
				var sumJiShuChild2 = 0; //奇数位*2 >9 的分割之后的数组十位数之和
				var sumTotal = 0;
				for(var m = 0; m<arrJiShu.length; m++){
				    sumJiShu = sumJiShu+parseInt(arrJiShu[m]);
				}
				for(var n = 0; n < arrOuShu.length; n++){
				    sumOuShu = sumOuShu+parseInt(arrOuShu[n]);
				}
				for(var p = 0; p < jishu_child1.length; p++) {
				    sumJiShuChild1 = sumJiShuChild1 + parseInt(jishu_child1[p]);
				    sumJiShuChild2 = sumJiShuChild2 + parseInt(jishu_child2[p]);
				}      
				//计算总和
				sumTotal = parseInt(sumJiShu) + parseInt(sumOuShu) + parseInt(sumJiShuChild1) + parseInt(sumJiShuChild2);
				//计算Luhm值
				var k = parseInt(sumTotal)%10==0?10:parseInt(sumTotal)%10;        
				var luhm= 10-k;
				if(lastNum == luhm){
					$.ajax({
						async:false,
						type:"post",
						url:"${ctx}/jwcmis/expert/checkRepeat?checkString=" + bankCardNumberNew,
						dataType:'html',
						error : function() {
					        alert('请求失败！请刷新页面重试！ ');
					    },
					    success : function(data) {
					    	if (data == 0) {
					    		bankCardNumber_notice.innerHTML ="银行卡号验证通过！";
					    		bankCardNumber_notice.style.color = 'green';
							}
							else {
								bankCardNumber_notice.innerHTML ="银行卡号已被占用！";
								bankCardNumber_notice.style.color = 'red';
								return false;
							}
					    }
					});
				}else{
					bankCardNumber_notice.innerHTML ="银行卡号不合法，请检查后重新输入！";
					bankCardNumber_notice.style.color = 'red';
					return false;
				}
			}else if( bankCardNumberNew != null && bankCardNumberNew != ""){
				bankCardNumber_notice.innerHTML ="银行卡号验证通过！";
		   		bankCardNumber_notice.style.color = 'green';
				}else{
					bankCardNumber_notice.innerHTML ="请输入银行卡号！";
					bankCardNumber_notice.style.color = 'red';
					return false;
				}
			return true;
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/jwcmis/expert/">专家信息列表</a></li>
		<li class="active"><a href="${ctx}/jwcmis/expert/form?id=${expert.id}">专家信息<shiro:hasPermission name="jwcmis:expert:edit">${not empty expert.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="jwcmis:expert:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="expert" action="${ctx}/jwcmis/expert/save" method="post" class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<div class="control-group">
			<label class="control-label">专家姓名：</label>
			<div class="controls">
				<form:input path="expertName" htmlEscape="false" maxlength="20" class="input-xlarge required" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">专业：</label>
			<div class="controls">
				<form:select path="major" class="input-xlarge">
					<form:options items="${majorname}" itemLabel="name" itemValue="id" htmlEscape="false" />
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">研究方向：</label>
			<div class="controls">
				<form:input path="researchName" id="researchName" onfocus="choseResearchList()" htmlEscape="false" maxlength="64" class="input-xlarge required" />
				<span class="help-inline"><font color="red">*</font> </span>
				<script type="text/javascript">
					//定义数组用于保存复选框选中的结果
					var researchSelect = [];
					//将复选框的结果存入数组
					function researchSelectAddOrDel(id,researchName) {
						//定义标志变量，用于确定复选框是否选中
						var isExtents = false, index = 0;
						for (var i=0; i<researchSelect.length; i++){
							if (researchSelect[i][1]==researchName){
								//如果数组中已有选中的结果，标志为true
								isExtents = true;
								index = i;
							}
						}
						if(isExtents){
							//标志为true，即数组中已有，就删除原来的，然后把现在选中的加进去
							researchSelect.splice(index,1);
						}else{
							//标志为false，即数组中没有选中的结果，就把它加入数组
							researchSelect.push([id,researchName]);
						}
						//每次更改就需要调用更新方法
						researchSelectRefresh();
					}
					//researchName字符串更新（即文本框内容更新）
					function researchSelectRefresh(){
						var researchNameNew = "";
						var researchDataRelation = "";
						//提取research添加到researchNameNew后面（即把数组内容用逗号隔开，合并成一个字符串）
						for (var i=0; i<researchSelect.length; i++){
							researchNameNew += researchSelect[i][1] + ",";
							researchDataRelation += researchSelect[i][0] + ",";
						}
						//删除合并字符串researchNameNew的最后一个逗号
						researchNameNew = researchNameNew.substr(0, researchNameNew.length - 1); 
						//由于第一次需要也会执行，此时数组为空，所以合并字符串中第一个位置为空，后面跟的逗号需要删除，否则合并字符串首位就有逗号
						if(researchNameNew.indexOf(",") == 0){
							researchNameNew = researchNameNew.substr(1); 
						}
						//把最后拼接所得的researchNameNew字符串值传递给表单数据researchName
						$("#researchName").val(researchNameNew);
						$("#researchDataRelation").val(researchDataRelation);
						
					}
					//把researchNameNew字符串分割成数组，以便在弹出的selectList面板中对比，已有的自动勾选
					var temp = $("#researchName").val().split(",");
					for(var i = 0 ; i < temp.length ; i++){
						//把分割完成的数组顺序放入researchSelect数组
						researchSelect.push([i,temp[i]]);
					}
					//弹出研究方向选择面板
					function choseResearchList(){
						//由于是弹出的面板，所以要自己写一个jsp
						//alert("弹出复面板");
						top.$.jBox.open("iframe:${ctx}/jwcmis/research/selectList?pageSize=8", "添加研究方向",$(top.document).width()-700,$(top.document).height()-165,{							
							buttons:{"确定":true}, 
							loaded:function(h){
								$(".jbox-content", top.document).css("overflow-y","hidden");
							}
						});
					};
				</script>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">专家类型：</label>
			<div class="controls">
				<form:radiobuttons path="expertType" id="expertType" items="${fns:getDictList('expertType')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">已分配论文数：</label>
			<div class="controls">
				<form:input path="distributeNum" id="distributeNum" readonly="true" htmlEscape="false" maxlength="1" class="input-xlarge required" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">邮箱：</label>
			<div class="controls">
				<form:input path="email" id="email" value="" oninput="checkEmail()" htmlEscape="false" maxlength="64" class="input-xlarge required" />
				<span class="help-inline" id="email_notice"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">手机号：</label>
			<div class="controls">
				<form:input path="phone" id="phone" value="" oninput="checkPhone()" htmlEscape="false" maxlength="11" class="input-xlarge required" />
				<span class="help-inline" id="phone_notice"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">银行卡号：</label>
			<div class="controls">
				<form:input path="bankCardNumber" id="bankCardNumber" oninput="checkBankCardNumber()" htmlEscape="false" maxlength="64" class="input-xlarge required" />
				<span class="help-inline" id="bankCardNumber_notice"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">单位：</label>
			<div class="controls">
				<form:input path="place" htmlEscape="false" maxlength="64" class="input-xlarge required" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge " />
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="jwcmis:expert:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存" />&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>