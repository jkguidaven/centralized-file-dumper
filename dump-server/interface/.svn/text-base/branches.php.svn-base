<?php
	include '../db_common/db_config_branch.php';
	include '../db_common/connect_db.php';
	
	if ($result = mysqli_query($con, "SELECT * FROM branch_table WHERE Active=1")) {

	
	echo "<table class='flat-table'><tr class='main'><td width='20%'>Branch<td  align='center'>Address<td width='5%' align='center'Action</tr>";
					
		
		while ($obj = mysqli_fetch_object($result)) {
			$address = "";
			if($obj->BranchAddr1) $address .= "$obj->BranchAddr1";
			if($obj->BranchAddr2) $address .= ", $obj->BranchAddr2";
			if($obj->BranchAddr3) $address .= ", $obj->BranchAddr3";
			echo "<tr><td><a href=javascript:view_branch('$obj->BranchCode')>$obj->BranchCode - $obj->BranchDesc</a><td align='left'>$address<td align='center'>
					<a href=javascript:remove_branch('$obj->BranchCode')><img src='image/trash.png' style='width:20px;height:20px;' ></a></tr>";
				
			
		}

		
		echo "<tr><td colspan='4' class='AddRow' align='center' onClick='javascript:view_branch()'>Create New Branch</tr></Table>";



		/* free result set */
		mysqli_free_result($result);
	}


	
	/* close connection */
	mysqli_close($con);
?>