<?php
	include '../db_common/db_config_branch.php';
	include '../db_common/connect_db.php';
	
	if(isset($_POST["BranchCode"])){
		$BranchCode = $_POST["BranchCode"];
		
		if($_POST["BranchUpdateType"] == "Delete")
		{
			$insert_query = "UPDATE branch_table SET Active=0 WHERE BranchCode='$BranchCode'";
			mysqli_query($con,$insert_query);
			header("Location: branches.php"); 
			exit();
		}
		
		$BranchDesc = $_POST["BranchDesc"];
		$BranchPassCode = $_POST["BranchPass"];
		$BranchAddr1 = $_POST["BranchAddr1"];
		$BranchAddr2 = $_POST["BranchAddr2"];
		$BranchAddr3 = $_POST["BranchAddr3"];
		$BranchAddr4 = $_POST["BranchAddr4"];
		$BranchEmail = $_POST["BranchEmail"];
		$BranchContact = $_POST["BranchContact"];
		$result = mysqli_query($con, "SELECT * FROM branch_table WHERE BranchCode='$BranchCode'");
		
		if($result->num_rows != 0 && $_POST["BranchUpdateType"] != "New"){
			$insert_query = "UPDATE branch_table 
							 SET	BranchPassCode='$BranchPassCode',
									BranchDesc='$BranchDesc',
									BranchAddr1='$BranchAddr1',
									BranchAddr2='$BranchAddr2',
									BranchAddr3='$BranchAddr3',
									BranchAddr4='$BranchAddr4',
									BranchEmail='$BranchEmail',
									BranchContact='$BranchContact' 
							 WHERE  BranchCode='$BranchCode'";
							
		}
		else
		{
			$insert_query = "INSERT INTO branch_table(BranchCode,BranchDesc,BranchPassCode,BranchAddr1,BranchAddr2,BranchAddr3,BranchAddr4,BranchEmail,BranchContact)".
								   "VALUES('$BranchCode','$BranchDesc','$BranchPassCode','$BranchAddr1','$BranchAddr2','$BranchAddr3','$BranchAddr4','$BranchEmail','$BranchContact')";
		}
		
		if (!mysqli_query($con,$insert_query)) {
			header("Location: branchInfo.php?BranchCode=$BranchCode&ErrMessage=".mysqli_error($con)); 
		}
		else
		{
			if($_POST["BranchUpdateType"] != "New")
				header("Location: branchInfo.php?BranchCode=$BranchCode&Message=Successully updated branch information."); 
			else
				header("Location: branchInfo.php?BranchCode=$BranchCode&Message=Successully Added branch information."); 
				
		}
	}
	
	mysqli_close($con);
?>