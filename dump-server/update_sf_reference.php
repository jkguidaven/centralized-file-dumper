<?php 
	include_once 'db_common/check_agent.php';
	include 'db_common/db_config_filesys.php';
	include 'db_common/connect_db.php';
	

	$upload_group 	=  $_POST["GroupUploadName"];

	$ALL_UPLOADED_FILE_QUERY = "SELECT * FROM server_reference_table";
	$result = mysqli_query($con,$ALL_UPLOADED_FILE_QUERY);
	
	if($result->num_rows==0){
		echo "creating latest reference group to '$upload_group' \n";
		$store_query = "INSERT INTO server_reference_table(LatestFileServerGroup) ".
				"VALUES('$upload_group')";
	}else {
		echo "updating latest reference group to '$upload_group' \n";
		$store_query = "UPDATE 	server_reference_table ".
				"SET LatestFileServerGroup='$upload_group' ";
	}
	
	mysqli_query($con,$store_query);
	
	if (mysqli_connect_errno()) {
		exit( "Error on SQL query:'" . mysqli_connect_error() ."'");
	}

	echo "successfull updated server file reference group.";
?>