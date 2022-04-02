<?php
	include_once 'db_common/check_agent.php';
	include 	 'db_common/db_config_filesys.php';
    include 	 'db_common/connect_db.php';
    
	$SERVER_ID	=	$_POST["DumpServerID"];
	$ALL_UPLOADED_FILE_QUERY = "SELECT * FROM file_upload_table";
	$SERVER_DOWNLOADED_LIST	 = "SELECT * FROM download_reference_table where DownloadID='".$SERVER_ID."'";
	$result = mysqli_query($con,$ALL_UPLOADED_FILE_QUERY);
	
	while($obj_file = mysqli_fetch_object($result)) {
		$query = $SERVER_DOWNLOADED_LIST." and UploadID=$obj_file->UploadID";
		$dl_result = mysqli_query($con,$query);
		
		if($dl_result->num_rows == 0){
			echo $obj_file->UploadID.",".$obj_file->UploadDesc.",".$obj_file->UploadSize."\n";
		}
		else{
			$dl_data = mysqli_fetch_object($dl_result);
			if($dl_data->OnSync == false)
				echo $obj_file->UploadID.",".$obj_file->UploadDesc.",".$obj_file->UploadSize."\n";
		}
	}
	
	mysqli_close($con);  
?>