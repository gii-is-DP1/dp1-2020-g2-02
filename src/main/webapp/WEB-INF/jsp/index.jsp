<!DOCTYPE html>
<html>
    <head>
        <title>Hello jQuery</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
        <script>
        $(document).ready(function() {
            $.ajax({
                url: "http://localhost:8080/api/autor/1"
            }).then(function(data) {
            	var autornombre = data.nombre;
            	var autorid = data.id;
            	document.write(autornombre);
            	document.write(" "+ autorid);
 			});
            
        });
        </script>
    </head>

    <body>
        <div>
        Autor:
            <c:out value="${autornombre }" />
        </div>
    </body>
</html>