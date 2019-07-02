<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html lang="en"> 
<head> 
    <meta charset="utf-8"> 
    <title>工作台</title> 
	<!-- 导入css样式 -->
	<link rel="stylesheet" href="${ctx }/resources/bootstrap/css/bootstrap.min.css" />
	<link rel="stylesheet" href="${ctx }/resources/bootstrap/style.css" />
	<script type="text/javascript" src="${ctx }/resources/jquery/jquery-1.11.0.min.js"></script>
	<script type="text/javascript" src="${ctx }/resources/jquery/jquery-migrate-1.2.1.min.js"></script>
	<script type="text/javascript" src="${ctx}/resources/easyUI/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${ctx}/resources/easyUI/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${ctx}/resources/bootstrap/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="${ctx}/resources/easyUI/easyui.css">
	<script type="text/javascript">
	$(function(){
		// 如果有提示就弹出来 
		if("${tip}"){
			$.messager.alert('错误提示',"${tip}",'info');
		}
		
		/** 提交表单函数 */
		$("#btn_submit").click(function(){
			// 对表单中所有字段做校验
			var name = $("#name");
			var phone = $("#mobile");
			var msg = "";
			if ($.trim(name.val()) == ""){
				msg += "姓名不能为空!";
				name.focus();
			}else if ($.trim(phone.val()) == ""){
				msg += "手机号码不能为空!";
				phone.focus();
			}else if (!/^1[3|5|8]\d{9}$/.test(phone.val())){
				msg += "手机号码格式不正确!";
				phone.focus();
			}
			// 直接提交
			if (msg != ""){
				$.messager.alert('错误提示',msg,'error');
			}else{
				$("#updateSelfForm").submit();
			}
		});
	});
	</script>
</head> 
<body >
	<div class="container">
	      <div class="row info-content">
		 	<form id="updateSelfForm" class="form-horizontal" method="post" action="${ctx }/identity/user/updateSelf"  style="margin-top: 20px;">
				<!-- 隐藏用户的id,修改用 -->
				<input type="hidden"  name="id" value="${user_session.id}"/>
				<div class="form-group">
					<label class="col-sm-2 control-label">手机号</label>
					<div class="col-sm-3">
						<button class="btn" style="background: #11a9e2;color: #ffffff;" disabled="disabled" type="button">
						  ${user_session.mobile} <span class="badge"></span>
						</button>				
					</div>
					<label class="col-sm-2 control-label">用户姓名</label>
					<div class="col-sm-3">
						<input type="text" id="name" name="name" value="${user_session.name}"  class="form-control" placeholder="请输入您的电子邮件">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">性别</label>
					<div class="col-sm-3">
						<select  class="btn btn-default" >
							<c:if test="${user_session.sex == 1 }">
								<option>男</option>
							</c:if>
								<c:if test="${user_session.sex == 2 }">
								<option>女</option>
							</c:if>
							
						</select>
					</div>
					<label class="col-sm-2 control-label">部门</label>
					<div class="col-sm-3">
						<select  class="btn btn-default" id="deptSelect" >
							 <option >${user_session.dept.name}</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">职位</label>
					<div class="col-sm-3">
						<select class="btn btn-default" id="jobSelect" >
								 <option>${user_session.job.name}</option>
						</select>	
					</div>
				</div>	
				<div class="form-group">
					<label class="col-sm-2 control-label"></label>
					<div class="col-sm-3">
						 <button type="button" id="btn_submit"  class="btn btn-info"><span class="glyphicon glyphicon-edit"> 提交修改</span></button>
					</div>
				</div>
		 	 </form>
		    </div>
		</div>
 
</body> 
</html>
