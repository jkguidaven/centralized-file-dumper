<?php
	include_once 'db_common/check_agent.php';
	include 'db_common/db_config_filesys.php';
    include 'db_common/connect_db.php';

	$file_list = $_POST['file'];

	for($i=0;$i<count($file_list);$i++){
		$file_obj = json_decode($file_list[$i]);
		
		$result = mysqli_query($con,"SELECT * FROM file_upload_table where UploadDesc='".$file_obj->filename."'");
		$status = "done";
		if($result->num_rows == 0){
		  $status="not found";
		}else{
			$obj = mysqli_fetch_object($result);
			if($obj->UploadSize != $file_obj->{'size'})
			{
				$status ="out-sync";
			}
		}
		
		echo $status.",".$file_obj->{'filename'}.",".$file_obj->{'size'}."\n";
	}

?>