<!-- GLOBAL HEADER --><%@ page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<jsp:include page="../common/header.jsp" />
<div class="col-md-9">
	<div class="card card-info">
		<div class="card-header">
			<h3 class="card-title">Edit Profile</h3>

		</div>
		<form:form class="form-horizontal"
			action="${pageContext.request.contextPath }/profile/edit"
			modelAttribute="user">
			<form:input path="id" value="${userId}" hidden="hidden" />
			<div class="card-body">
				<div class="form-group row">
					<label class="col-sm-2 col-form-label">Profile Picture: </label>
					<div class="col-sm-10">
						<input type="file" name="file" />
					</div>
				</div>
				<div class="form-group row">
					<label class="col-sm-2 col-form-label">Full name: </label>
					<div class="col-sm-10">
						<form:input class="form-control" path="fullName"
							value="${user.fullName}" />
					</div>
				</div>

				<div class="form-group row">
					<label class="col-sm-2 col-form-label">Email: </label>
					<div class="col-sm-10">
						${user.email}
					</div>
				</div>

				<div class="form-group row">
					<label class="col-sm-2 col-form-label">User Name: </label>
					<div class="col-sm-10">
						${user.username}
					</div>
				</div>

				<div class="card-footer">
					<input type="submit" class="btn btn-primary" name="submit"
						value="Update">
				</div>
			</div>
		</form:form>
	</div>

</div>
</div>
<!-- GLOBAL FOOTER -->
<jsp:include page="../common/footer.jsp" />