<!DOCTYPE html>
<html>
    <head>
        <title>Hello jQuery</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
        <script>
        $(document).ready(function() {
            $.ajax({
                url: "http://localhost:8080/api/autor/verTodos",
                type: "GET",
                dataType: "json"
            }).then(function(data) {
            	document.write(data[0].nombre);
            	document.write(data[1].nombre);
            	document.write(data[2].nombre);
            	document.write(data[3].nombre);
 			});
            
        });
        </script>
    </head>

    <body>
        <div clas="autornombre"></div>
    </body>
</html>