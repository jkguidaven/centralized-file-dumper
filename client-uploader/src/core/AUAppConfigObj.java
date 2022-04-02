package core;

public class AUAppConfigObj {
	public String 	server_domain  		= "127.0.0.1";
	public int 		server_port    		= 8080;
	public boolean  server_secure		= false;
	public int 		server_secure_port 	= 483;
	public String	server_authenticate = "branch_authenticate.php";
	public String   server_check 		= "check_file.php";
	public String   server_upload		= "upload_file.php";
	public String   service_b_update 	= "branch_update.php";
	public boolean  server_method_post  = true;
	
	public long		idle_duration_upload= 100000;
	public long		idle_duration_cpu	= 100;	
	
	public int		trace_level			 = 2;
	public boolean	trace_backup		 = false;
	public String	trace_backup_format	 = "";
	public long  	trace_dump_size		 = 10000;
	
	public boolean	SecureEnabled 		= false;
	
	public String 	branch_name			= "BRANCH";
	public String 	branch_code  		= "0";
	public String 	branch_passcode		= "PASS";
	
	public String 	transfer_path  		= "C:/transfer";
	
	
	private static AUAppConfigObj _instance = null;
	public  static AUAppConfigObj getInstance(){
		if(_instance == null)
			_instance = new AUAppConfigObj();
		
		return _instance;
	}
	
	private AUAppConfigObj(){}
}
