package CSVProcessManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
public class CSVReaderUtil implements Runnable{
	public String url ;
	DatabaseConnector connector =  DatabaseConnector.gerInstance();
	Statement stmt1 = null;
	Connection connection = connector.getDatabaseConnection();
	  static Map<String,String> task_map = new HashMap<String,String>();
	  static List<String> team_list = new ArrayList<String>();
	  static Map<String,String> team_skill_map = new HashMap<String,String>();
	public CSVReaderUtil(String url)
	{
		this.url = url;
	}
	public CSVReaderUtil()
	{
		 
	}
	public static void main(String[] args) {
		
		
		
		Thread task = new Thread(new CSVReaderUtil("C:\\Users\\LENOVO\\Desktop\\csv\\csv_files\\task.csv"),"First Thread");
		task.setDaemon(true);
		task.start();
		 
		 
		 Thread team = new Thread(new CSVReaderUtil("C:\\Users\\LENOVO\\Desktop\\csv\\csv_files\\team.csv"),"Second Thread");
		 team.setDaemon(true);
		 team.start();
		  
		 
		 Thread team_skill = new Thread(new CSVReaderUtil("C:\\Users\\LENOVO\\Desktop\\csv\\csv_files\\team_skill.csv"),"Third Thread");
		 team_skill.setDaemon(true);
		 team_skill.start();
		 CSVReaderUtil taskObj = new CSVReaderUtil();
		  
		 //insert task
		 for (Map.Entry<String, String> entry : task_map.entrySet())
	        {
			 taskObj.insertRecord("task",entry.getKey(),entry.getValue());
	        
	        }
		 //insert team_skill
		 for (Map.Entry<String, String> entry : team_skill_map.entrySet())
	        {
			 taskObj.insertRecord("team_skill",entry.getKey(),entry.getValue());
	        }
		 // insert team
		 Iterator it = team_list.iterator();
		 while(it.hasNext())
		 {
			 taskObj.insertRecord("team",it.next().toString(),""); 
		 }
		 
		 
		 //delete all the csv files
		 File file1 = new File("C:\\Users\\LENOVO\\Desktop\\csv\\csv_files\\task.csv");
		 File file2 = new File("C:\\Users\\LENOVO\\Desktop\\csv\\csv_files\\team.csv");
		 File file3 = new File("C:\\Users\\LENOVO\\Desktop\\csv\\csv_files\\team_skill.csv");
		 file1.delete();
		 file2.delete();
		 file3.delete();
    }
	 
	 
	
	public void processCSVData(String fileURL)
	{
		
		System.out.println("calling");
		String line = "";
		 
	        String cvsSplitBy = ",";
	        
	        try (BufferedReader br = new BufferedReader(new FileReader(fileURL))) {
	        	
	            while ((line = br.readLine()) != null) {

	              
	                String[] task_read = line.split(cvsSplitBy);
	                
	                if((fileURL.contains("task")))
	                {
	                	try {
	                	 
	                System.out.println("Country [code= " + task_read[0] + " , name=" + task_read[1] + "]");
	                	
	                task_map.put( task_read[0], task_read[1]);
	                	}
	                	catch(Exception ee)
	                	{
	                		
	                	}
	                }
	                else if ((fileURL.contains("team_skill")))
	                {
	                	team_skill_map.put(task_read[0], task_read[1]);
	                }
	                else
	                {
	                	team_list.add(task_read[0]);
	                }
	            }

	        } catch (IOException e) {
	            e.printStackTrace();
	        }	
	}



 
	public void run() {
		System.out.println("Thread started");
		processCSVData(url)	;
		
	}
	
	public void insertRecord(String table_name, String val1, String val2)
	{
		 Statement stmt;
		try {
		 if(table_name.contains("team_skill") || table_name.contains("task"))
		 {
		 stmt = connection.createStatement();
		 String sql = "INSERT INTO " +table_name+ " VALUES ('"+val1+"','"+val2+"')";
		 System.out.println(sql);
		 stmt.executeUpdate(sql);  
		 }
		 else
		 {
			 stmt = connection.createStatement();
			 String sql = "INSERT INTO " +table_name+ " VALUES ('"+val1+"')";
			 System.out.println(sql);
			 stmt.executeUpdate(sql);  
		 }
		} catch (SQLException e) {
			e.printStackTrace();
		}
          
	}
	
}
