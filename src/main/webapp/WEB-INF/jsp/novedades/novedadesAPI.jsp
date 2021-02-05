
<!DOCTYPE html>
<html>
    <head>
        <title>API Novedades</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
        <script>
        
        $(document).ready(function() {
            $.ajax({
                url: "http://localhost:8080/api/novedades/getAll",
                type: "GET",
                dataType: "json",
            	success: function(data) {
            		var len = data.length;
                	for(var i=0; i<len; i++){
            			document.write("<h1>"+data[i].titulo+":</h1>" +"<p>"+ data[i].fechaPublicacion + " _ Contenido: " + data[i].contenido+"</p>");
                	}
            	}
 			});
        });
        </script>
    </head>


</html>

