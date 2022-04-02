<?php
	include_once 'db_common/check_agent.php';
	include 'db_common/db_config_filesys.php';
    include 'db_common/connect_db.php';
    
    $UploadID = $_POST["UploadID"];
    $FileName = $_POST["FileName"];
    $FileSize = $_POST["Size"];
    
    $result = mysqli_query($con,"SELECT * FROM file_server_upload_tabe where UploadID='$UploadID'");
    if(!$result){
    	echo "unable to find file for the following ID = $UploadID";
    }
    else
    {
	    $obj = mysqli_fetch_object($result);
	    if($obj->UploadDesc != $FileName){
	    	header('HTTP/1.1 500 INVALID REQUEST', true, 500);
	    	exit( "Invalid Request: 'Filename does not match the ID.. download request is rejected..'");
		}
		else
		{
	    	
		    $sql = "SELECT BlobData FROM file_server_blob_table where BlobID=$obj->BlobID";
		    $res = mysqli_query($con,$sql);
		    if($res->num_rows == 0){
		    	header('HTTP/1.1 500 INVALID REQUEST', true, 500);
				exit( "Invalid Request: 'no Raw Data found stored in the database... download request is rejected..'");
			}
			else
			{
				$obj_file = mysqli_fetch_object($res);
				header("Content-Disposition: attachment; filename=$FileName");
				header("Content-Type: $obj->UploadMIME");
				echo $obj_file->BlobData;
		    }
		}
	}
	
	
    mysqli_close($con);
    
 ?>