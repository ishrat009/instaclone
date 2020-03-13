<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!-- GLOBAL HEADER -->
<jsp:include page="common/header.jsp" />

<!-- Main content -->
<section class="content">


	<c:forEach items="${all_post }" var="post">
		<div class="post">
			<div class="user-block">
				<img class="img-circle img-bordered-sm"
					src="../../dist/img/user6-128x128.jpg"> <span
					class="username"> <a href="#">${post.userName}</a> <a
					href="#" class="float-right btn-tool"><i class="fas fa-times"></i></a>
				</span> <span class="description">Posted a photo - 5 days ago</span>
			</div>
			<!-- /.user-block -->
			<div class="row mb-3">
				<div class="col-12">
					<div class="col-sm-4">
						<p>Caption: ${ post.name }</p>
						<p>Location: ${ post.location }</p>
					</div>
					<div class="col-sm-8">
						<img class="img-responsive" style="width: 600px;"
							src="${pageContext.request.contextPath }${post.logo}">
					</div>
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row -->

			<p>
				<a href="#" class="link-black text-sm mr-2"><i
					class="fas fa-share mr-1"></i> Share</a> <a href="#"
					class="link-black text-sm"><i class="far fa-thumbs-up mr-1"></i>
					Like</a> <span class="float-right"> <a href="#"
					class="link-black text-sm"> <i class="far fa-comments mr-1"></i>
						Comments (5)
				</a>
				</span>
			</p>

			<input class="form-control form-control-sm" type="text"
				placeholder="Type a comment">
		</div>
	</c:forEach>
	<!-- /.card-body -->
</section>
<!-- /.content -->
</div>
<!-- /.content-wrapper -->



<!-- GLOBAL FOOTER -->
<jsp:include page="common/footer.jsp" />

