<?php 

	include_once 'db_common/check_agent.php';
	include 'db_common/db_config_branch.php';
	include 'db_common/connect_db.php';

	$BranchCode = $_POST['BranchCode'];
	$BranchPass = $_POST['BranchPass'];
	$auth_result = "fail";
	
	$result = mysqli_query($con,"SELECT * FROM branch_table WHERE BranchCode='$BranchCode' AND Active=1");

	if($result->num_rows > 0){
		$obj = mysqli_fetch_object($result);
		
		if($obj->BranchPassCode == $BranchPass){
			echo "BranchDesc=$obj->BranchDesc\n";
			
			$addr = "";
			
			if($obj->BranchAddr1 != NULL) $addr .= $obj->BranchAddr1;
			if($obj->BranchAddr2 != NULL) $addr .= ", ".$obj->BranchAddr2;
			if($obj->BranchAddr3 != NULL) $addr .= ", ".$obj->BranchAddr3;
			
			echo $addr != "" ? "Address=$addr\n" : "NoAddress\n";
			echo $obj->BranchContact != NULL ? "Contact=".$obj->BranchContact."\n" : "NoContact\n";
			
			
			$auth_result = "success";
		}
	}
	
	
	echo $auth_result;
?>