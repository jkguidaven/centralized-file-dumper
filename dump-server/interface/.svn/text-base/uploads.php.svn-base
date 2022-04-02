<?php 
	include '../db_common/db_config_filesys.php';
    include '../db_common/connect_db.php';
	
	$page_size = 10;
	$page_no   = isset($_GET["page"]) ? $_GET["page"] : 1;
	$begin_row = ($page_no-1) * $page_size;
	$end_row   = $begin_row + $page_size;
	
	mysqli_query($con,"SET @ROW_NUM=0;");
	$UPLOADED_FILE_QUERY = "SELECT @ROW_NUM:=@ROW_NUM+1 AS ROW,BranchCode,UploadDesc,UploadTS,UploadID FROM file_upload_table ORDER BY UploadTS DESC";
	$result = mysqli_query($con,$UPLOADED_FILE_QUERY);
	$result_count = $result->num_rows;
	echo "<table id='log' class='flat-table'><tr class='main' align='center'><td> Branch Code<td >Filename<td>Uploaded<td>Downloaded</tr>";
						
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
				<td align='center'>$obj_file->BranchCode</td>
				<td>$obj_file->UploadDesc</td>
				<td align='center'>$obj_file->UploadTS</td>
				$DOWNLOAD_STRING
			</tr>";
			
	}
						
	
	echo "<tr class='page_selection'><td colspan='5'>";
	
	if($result_count > 0){
		$page_count =  $result_count / $page_size;
		$decimal    = $page_count - floor($page_count);
		$page_count = floor($page_count);
		if($decimal > 0)
			$page_count++;
			
		echo "<div style='float:right'>Page Selection: <select id='page_selector' onchange='OnPageSelect()'>";
		
		for($i=1;$i<=$page_count;$i++){
			echo "<option value='$i'".($page_no == $i ? "selected" : "").">$i</option>";
		}
		echo "</select></div>";
	}
	echo "</Table>";
?>