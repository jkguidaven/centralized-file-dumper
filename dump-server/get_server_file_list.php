<?php 
	include_once 'db_common/check_agent.php';
	include 'db_common/db_config_filesys.php';
	include 'db_common/connect_db.php';
	
	$SERVER_REFERENCE_QUERY = "SELECT * FROM server_reference_table";
	$result = mysqli_query($con,$SERVER_REFERENCE_QUERY);
	if($result->num_rows!=0){
		$obj = mysqli_fetch_object($result);
		echo $obj->LatestFileServerGroup."\n";
		

		$ALL_UPLOADED_FILE_QUERY = "SELECT * FROM file_server_upload_tabe WHERE UploadGroup='$obj->LatestFileServerGroup'";
		$result = mysqli_query($con,$ALL_UPLOADED_FILE_QUERY);
		if (mysqli_connect_errno()) {
			exit( "Error on SQL query:'" . mysqli_connect_error() ."'");
		}
		while($obj_file = mysqli_fetch_object($result)) {
			echo $obj_file->UploadID.",".$obj_file->UploadDesc.",".$obj_file->UploadSize."\n";
		}
	}
	
?>