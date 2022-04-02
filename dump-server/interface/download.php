<?php
	include '../db_common/db_config_filesys.php';
    include '../db_common/connect_db.php';

	$UploadID = $_GET["UploadID"];

	$result = mysqli_query($con,"SELECT * FROM file_upload_table where UploadID='$UploadID'");
	if(!$result){
		echo "unable to find file for the following ID = $UploadID";
	}else{
		$obj = mysqli_fetch_object($result);
		
			$sql = "SELECT BlobData FROM file_blob_table where BlobID=$obj->BlobID";
			$res = mysqli_query($con,$sql);
			if($res->num_rows == 0){
				header('HTTP/1.1 500 INVALID REQUEST', true, 500);
				exit( "Invalid Request: 'no Raw Data found stored in the database... download request is rejected..'");
			}else{
				$obj_file = mysqli_fetch_object($res);
				header("Content-Disposition: attachment; filename=$obj->UploadDesc");
				header("Content-Type: $obj->UploadMIME");
				echo $obj_file->BlobData;
			}
		
	}
	mysqli_close($con);  
?>