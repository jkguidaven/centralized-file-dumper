<?php

       $con=mysqli_connect(HOST,USER,PASSWORD,DATABASE);
	   if (mysqli_connect_errno()) {
		header('HTTP/1.1 500 DB SERVER ERROR', true, 500);
		exit( "Error connecting to DB Server: '" . mysqli_connect_error() ."'");
	   }
?>