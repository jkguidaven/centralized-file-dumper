
<?php
	include '../db_common/db_config_branch.php';
	include '../db_common/connect_db.php';
	
	$BRANCH_CODE = isset($_GET["BranchCode"]) ?  $_GET['BranchCode'] : "";
	$READONLY = !isset($_GET["BranchCode"]) || isset($_GET["EditMode"]) ?"":"readonly";
	$READONLYCLASS = !isset($_GET["BranchCode"]) || isset($_GET["EditMode"]) ?"textbox":"textbox disabled";
	if(isset($_GET["BranchCode"]))
	{	
		if ($result = mysqli_query($con, "SELECT * FROM branch_table WHERE BranchCode='$BRANCH_CODE'")) {
			$obj = mysqli_fetch_object($result);
			
			$BRANCH_DESC = $obj->BranchDesc;
			$BRANCH_PASS = $obj->BranchPassCode;
			$BRANCH_CONTACT = $obj->BranchContact;
			$BRANCH_ADRR1	= $obj->BranchAddr1;
			$BRANCH_ADRR2	= $obj->BranchAddr2;
			$BRANCH_ADRR3	= $obj->BranchAddr3;
			$BRANCH_ADRR4	= $obj->BranchAddr4;
			$BRANCH_EMAIL	= $obj->BranchEmail;
			
				if($obj->Active == 0)
					echo "<div id='WarningMessageBox'>WARNING: BranchCode[$BRANCH_CODE] is no longer active..</div>";
		}else
		{
			echo "Unknown branch code.";
			exit();
		}
	}
	else
	{
			$BRANCH_DESC = "";
			$BRANCH_PASS = "";
			$BRANCH_CONTACT = "";
			$BRANCH_ADRR1	= "";
			$BRANCH_ADRR2	= "";
			$BRANCH_ADRR3	= "";
			$BRANCH_ADRR4	= "";
			$BRANCH_EMAIL	= "";
	}
				
				echo isset($_GET["BranchCode"]) ? 
										  // full access
										  "<div class='slider'><div class='button selected' content='#master_data'></div>
										  <div class='button'  		   content='#log'></div>
										  </div>" : 
										  // non-full access
										  "<div class='slider'><div class='button selected' content='#master_data'></div></div>";
										  
				echo"<table id='master_data' cellpadding='5' cellspacing='5' class='form'>
							<tr>
								<td>Branch Code:"
								
									.(isset($_GET["EditMode"]) ? 
														"<td><div class='textbox disabled'><input  name='branchcode'  initial-val='$BRANCH_CODE' readonly value='$BRANCH_CODE' /></div>" 				
																	:
														"<td><div class='$READONLYCLASS'><input  name='branchcode'  initial-val='$BRANCH_CODE' $READONLY value='$BRANCH_CODE' /></div>" 	
									).
								"<td width='100px'>Branch Name:  
								<td colspan='3'><div class='$READONLYCLASS'><input  name='branchdesc'  initial-val='$BRANCH_DESC' $READONLY value='$BRANCH_DESC' /></div>
							</tr>
							<tr>
								<td>Passcode:  
								<td><div class='$READONLYCLASS'><input  name='branchpasscode'  initial-val='$BRANCH_PASS' $READONLY   value='$BRANCH_PASS'  /></div>
								<td>Contact No:
								<td><div class='$READONLYCLASS'><input  name='branchcontactno'  initial-val='$BRANCH_CONTACT' $READONLY   value='$BRANCH_CONTACT' /></div>
								<td>Email: 
								<td><div class='$READONLYCLASS'><input  name='branchemail'  initial-val='$BRANCH_EMAIL' $READONLY  value='$BRANCH_EMAIL'  /></div>
							</tr>
							<tr>
								<td>Address 1:
								<td colspan='5'><div class='$READONLYCLASS'><input  name='branchaddr1'  initial-val='$BRANCH_ADRR1'  $READONLY   value='$BRANCH_ADRR1' /></div>
							</tr>
							<tr>
								<td>Address 2:
								<td colspan='5'><div class='$READONLYCLASS'><input  name='branchaddr2'  initial-val='$BRANCH_ADRR2'  $READONLY  value='$BRANCH_ADRR2'  /></div>
							</tr>
							<tr>
								<td>City:
								<td colspan='2'><div class='$READONLYCLASS'><input  name='branchaddr3'  initial-val='$BRANCH_ADRR3'  $READONLY   value='$BRANCH_ADRR3'  /></div>
							</tr>
							<tr>
								<td>Province:
								<td colspan='2'><div class='$READONLYCLASS'><input  name='branchaddr4'  initial-val='$BRANCH_ADRR4'  $READONLY  value='$BRANCH_ADRR4' /></div>
							</tr>
							<tr>
								<td colspan='7'>";
													
					$BC = "textbox button";

					if(!isset($_GET["BranchCode"]) || isset($_GET["EditMode"])){
						echo "<a href=javascript:OnBack()><div class='$BC'>Back</div></a> <a href=javascript:OnReset()><div class='$BC'>Reset</div></a> <div class='$BC disabled'>Edit</div> <a href=javascript:OnSave()><div class='$BC'>Save</div></a>";
					} else {
						echo "<a href=javascript:OnBack()><div class='$BC'>Back</div></a> <div class='$BC disabled'>Reset</div> <a href=javascript:OnEdit('$BRANCH_CODE')><div class='$BC'>Edit</div></a> <div class='$BC disabled'>Save</div>";
					}
					
					if(isset($_GET["Message"])){
						echo "<div id='MessageBox'>".$_GET["Message"]."</div>";
					} else if(isset($_GET["ErrMessage"])) {
						echo "<div id='ErrMessageBox'>".$_GET["ErrMessage"]."</div>";
					}
					
					echo	"</td>
							</tr>
					</table>";
					
					if(isset($_GET["BranchCode"])){
						mysqli_close($con);
						   $con=mysqli_connect("localhost","primerem_root","P@ssw0rd","primerem_filesys_db");
						   if (mysqli_connect_errno()) {
							header('HTTP/1.1 500 DB SERVER ERROR', true, 500);
							exit( "Error connecting to DB Server: '" . mysqli_connect_error() ."'");
						   }
						
						$page_size = 9;
						$page_no   = isset($_GET["page"]) ? $_GET["page"] : 1;
						$begin_row = ($page_no-1) * $page_size;
						$end_row   = $begin_row + $page_size;
	
						mysqli_query($con,"SET @ROW_NUM=0;");
						$UPLOADED_FILE_QUERY = "SELECT @ROW_NUM:=@ROW_NUM+1 AS ROW,BranchCode,UploadDesc,UploadTS,UploadID FROM file_upload_table WHERE BranchCode='".$_GET["BranchCode"]."' ORDER BY UploadTS";
						$result = mysqli_query($con,$UPLOADED_FILE_QUERY);
						$result_count = $result->num_rows;
						echo "<table id='log' class='flat-table'><tr class='main' align='center'><td >Filename<td>Uploaded<td>Downloaded</tr>";
						
						while($obj_file = mysqli_fetch_object($result)) {
						if($obj_file->ROW <= $begin_row)
							continue;
						
						if($obj_file->ROW > $end_row)
							break;
							
							$downloaded = mysqli_query($con,"SELECT * FROM download_reference_table WHERE UploadID='$obj_file->UploadID'");
							$DOWNLOAD_STRING = $downloaded->num_rows == 0 ? 
										"<td id='pending_download_link' align='center'><a  href='interface/download.php?UploadID=$obj_file->UploadID'>Pending</a></td>" : 
										"<td id='download_link' align='center'><a href='interface/download.php?UploadID=$obj_file->UploadID'>Done</a></td>";
							echo "<tr>
									<td>$obj_file->UploadDesc</td>
									<td align='center'>$obj_file->UploadTS</td>
									$DOWNLOAD_STRING
							</tr>";
						}
						
						echo "<tr><td><td><td><td></Table>";
						
						
					}
					
					
					
	/* close connection */
	mysqli_close($con);
?>