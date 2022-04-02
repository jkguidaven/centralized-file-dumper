<?php
	include_once 'db_common/check_agent.php';
	include 'db_common/db_config_filesys.php';
    include 'db_common/connect_db.php';

	$ServerID = $_POST["ServerID"];
	$UploadID = $_POST["UploadID"];
	$FileName = $_POST["FileName"];
	$FileSize = $_POST["Size"];

	$result = mysqli_query($con,"SELECT * FROM file_upload_table where UploadID='$UploadID'");
	if(!$result){
		echo "unable to find file for the following ID = $UploadID";
	}else{
		$obj = mysqli_fetch_object($result);
		if($obj->UploadDesc != $FileName){
			header('HTTP/1.1 500 INVALID REQUEST', true, 500);
			exit( "Invalid Request: 'Filename does not match the ID.. download request is rejected..'");
			
		}else{
			
			$sql = "SELECT BlobData FROM file_blob_table where BlobID=$obj->BlobID";
			$res = mysqli_query($con,$sql);
			if($res->num_rows == 0){
				header('HTTP/1.1 500 INVALID REQUEST', true, 500);
				exit( "Invalid Request: 'no Raw Data found stored in the database... download request is rejected..'");
			}else{
				
				$check_query = "SELECT * FROM download_reference_table ".
				 			   "where DownloadID='".$ServerID."' AND UploadID='".$UploadID."'";
				$res = mysqli_query($con,$check_query) or die(mysql_error());
				
				if($res->num_rows == 0){
					$store_query = "INSERT INTO download_reference_table(DownloadID,UploadID,OnSync)".
								   "VALUES('$ServerID',$UploadID,1)";
					mysqli_query($con,$store_query) or die(mysql_error());
				}else {
					$store_query = "UPDATE 	download_reference_table ".
					   			   "SET		OnSync='1',DownloadTS=now() ".
					   			   "WHERE 	DownloadID='".$ServerID."' AND UploadID='".$UploadID."'";
					mysqli_query($con,$store_query) or die(mysql_error());
				}

				echo "Successfully updated server!";
			}
		}
	}
	mysqli_close($con);  
?>