<?php
	include_once 'db_common/check_agent.php';
	include 'db_common/db_config_filesys.php';
    include 'db_common/connect_db.php';

	$filename 		=  $_FILES["file"]["name"];
	$temp 			=  $_FILES["file"]["tmp_name"];
	$size 			=  $_FILES["file"]["size"];
	$type			=  isset($_POST["Type"]) ? $_POST["Type"] : $_FILES["file"]["type"];
	$branch_code 	= $_POST["branch_code"];
	
	echo "file uploading begin: {name:".$filename.",size:".$size.",temp_folder:".$temp.",branch:".$branch_code."}\n";
	echo "file data extracting...\n";
	$extract = fopen($temp, 'r'); 
	$content = fread($extract, $size); 
	$content = addslashes($content); 
	fclose($extract);  
	
	echo "file data successfully extracted... storing raw data to server...\n";
		$store_query = "INSERT INTO file_blob_table(BlobData) VALUES('".$content."')";
		mysqli_query($con,$store_query) or die(mysql_error());
		$refId = mysqli_insert_id($con); 
	echo "successfully stored file into database. Raw Data reference no. ".$refId."\n";
	
	$result = mysqli_query($con,"SELECT * FROM file_upload_table where UploadDesc='$filename'");
	if($result->num_rows==0){
		echo "creating upload logs to store in database...\n";
		$store_query = "INSERT INTO file_upload_table(BlobID,UploadDesc,UploadSize,BranchCode,UploadMIME)".
					   "VALUES('".$refId."','".$filename."',".$size.",'".$branch_code."','".$type."')";
		mysqli_query($con,$store_query);
		
		if (mysqli_connect_errno()) {
			exit( "Error on SQL query:'" . mysqli_connect_error() ."'");
		}
		
		$refId = mysqli_insert_id($con); 
		echo "successfully stored log data.reference ID:".$refId."\n";
	} else {
		$obj_file = mysqli_fetch_object($result);
		
		// update upload header
		echo "updating upload header['".$obj_file->UploadID."'] to new BlOB reference...\n";
		$store_query = "UPDATE 	file_upload_table ".
					   "SET 	BlobID='".$refId."',UploadSize='".$size."',UploadTS=now() ".
					   "WHERE 	UploadID='".$obj_file->UploadID."'";
		mysqli_query($con,$store_query);
		
		if (mysqli_connect_errno()) {
			exit( "Error on SQL query:'" . mysqli_connect_error() ."'");
		}
		echo "successfully updated upload header...";
		
		// update download history references
		echo "reseting download history...\n";
		$store_query = "UPDATE 	download_reference_table ".
					   "SET		OnSync='0' ".
					   "WHERE   UploadID='".$obj_file->UploadID."'";
		mysqli_query($con,$store_query);
		
		if (mysqli_connect_errno()) {
			exit( "Error on SQL query:'" . mysqli_connect_error() ."'");
		}
	}
	
	mysqli_close($con);  
?>