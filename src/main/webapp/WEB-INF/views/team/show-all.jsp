<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!-- GLOBAL HEADER -->
<jsp:include page="../common/header.jsp" />

<!-- Main content -->
<section class="content">
	<div class="card">
              <div class="card-header">
                <h3 class="card-title">Show All Team</h3>
                <div class="card-tools">
                  <div class="input-group input-group-sm" style="width: 150px;">
                    <input type="text" class="team-query form-control float-right" placeholder="Search">
                    <div class="input-group-append">
                      <button type="submit" class="team-query-submit btn btn-default"><i class="fas fa-search"></i></button>
                    </div>
                  </div>
                </div>
              </div>
              <!-- /.card-header -->
              <div class="card-body table-responsive p-0" style="height: 300px;">
                <table class="table table-head-fixed text-nowrap" id="show-all-table">
                  <thead >
                    <tr>
                     <th style="width: 30px;">Id</th>
					<th style="width: 50px;">Country</th>
					<th style="width: 100px;">Name</th>
					<th style="width: 10px;">Edit</th>
					<th style="width: 10px;">Deactive</th>
                    </tr>
                  </thead>
                  <tbody>
                  <c:forEach items="${team_list}" var="team">
			<tr>
				<td style="width: 30px;">${ team.id }</td>
				<td style="width: 50px;">${ team.country.getCountryName() }</td>
				<td style="width: 100px;">${ team.name }</td>
				<td style="width: 10px;"><a href="edit?id=${ team.id }">Edit</a></td><td style="width: 10px;"> <a
					href="deactive?id=${ team.id }">Lock</a></td>
			</tr>
		</c:forEach>                          
                  </tbody>
               
                </table><table id="search-table">
                   <tbody class="search-result">

    </tbody>
                </table>
              </div>
     </div>
</section>
<!-- /.content -->
</div>
<!-- /.content-wrapper -->



<!-- GLOBAL FOOTER -->
<jsp:include page="../common/footer.jsp" />
<script>
    $(document).ready(function(){
        $(".team-query-submit").click(function(){
            var queryString = $(".team-query").val();
            document.getElementById('show-all-table').style.visibility = "hidden";
            document.getElementById('search-table').style.visibility = "visible";
            //  /api/v1/course/search | /course/search
            $.get("/api/v1/team/search?query="+queryString, function( data ) {
                var searchResContainer = $(".search-result");
                searchResContainer.html("");
                // data = JSON.parse(data);
                if (data.length > 0) {
                    $.each(data, function( index, value ) {
                        var tblRow = "<tr>\n" +
                            "        <td style='width: 30px;'>"+ value.id +"</td>\n" +
                            "        <td style='width: 50px;'>"+ value.country.getCountryName() +"</td>\n" +
                            "        <td style='width: 100px;'>"+ value.name +"</td>\n" +
                            "        <td style='width: 100px;'>"<a href='edit?id=${ team.id }'>Edit</a>"</td>\n" +
                            "        <td style='width: 10px;'>"<a href='deactive?id=${ team.id }'>Deactive</a>"</td>\n" +
                            "    </tr>";
                        searchResContainer.append(tblRow);
                    });
                } else {
                    searchResContainer.html("<th >Nothing found!</th>");
                }
            });
        });
    });
</script>