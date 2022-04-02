<?php 
	include_once 'db_common/check_agent.php';
	include 'db_common/db_config_filesys.php';
	include 'db_common/connect_db.php';
	
	
	$filename 		=  $_FILES["file"]["name"];
	$temp 			=  $_FILES["file"]["tmp_name"];
	$size 			=  $_FILES["file"]["size"];
	$type			=  isset($_POST["Type"]) ? $_POST["Type"] : $_FILES["file"]["type"];
	$upload_group 	=  $_POST["GroupUploadName"];
	$branch_code 	= $_POST["branch_code"];
	
	
	echo "file uploading begin: {name:".$filename.",size:".$size.",temp_folder:".$temp.",branch:".$branch_code."}\n";
	echo "file data extracting...\n";
	$extract = fopen($temp, 'r');
	$content = fread($extract, $size);
	$content = addslashes($content);
	fclose($extract);
	echo "file data successfully extracted... storing raw data to server...\n";
	$store_query = "INSERT INTO file_server_blob_table(BlobData) VALUES('".$content."')";
	mysqli_query($con,$store_query) or die(mysql_error());
	$refId = mysqli_insert_id($con);
	echo "successfully stored file into database. Raw Data reference no. ".$refId."\n";
	
	$store_query = "INSERT INTO file_server_upload_tabe(BlobID,UploadDesc,UploadSize,BranchCode,UploadMIME,UploadGroup)".
			"VALUES('".$refId."','".$filename."',".$size.",'".$branch_code."','".$type."','".$upload_group."')";
	mysqli_query($con,$store_query);
	
	if (mysqli_connect_errno()) {
		exit( "Error on SQL query:'" . mysqli_connect_error() ."'");
	}

	$refId = mysqli_insert_id($con);
	echo "successfully stored log data.reference ID:".$refId."\n";
	
	echo "OK done uploading '".$upload_group."' - ".$filename."\n";
?>