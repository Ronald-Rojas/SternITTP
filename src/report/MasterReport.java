package report;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import sqlconnect.SternQuery;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;



import com.mysql.jdbc.Statement;

import java.sql.ResultSet;

public class MasterReport {
	
	HashSet<String> userID;
	ArrayList<UserReport> userReports;
	private final String nameOfRecords="STERN_MASTER",
			             extension=".xlsx";
	
	/**
	 * Generate a complete excel file
	 * logging all hours within specified date range
	 *
	 * @param begin upper bound time stamp
	 * @param end last time stamp
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@SuppressWarnings("static-access")
	public MasterReport(String begin, String end) throws ClassNotFoundException, SQLException{
		SternQuery sq=SternQuery.getInstance();
		
		/*
		 * global search all users query
		 */
		java.sql.Statement statement=sq.connect();
		String command="SELECT * FROM "+sq.TTABLE+ " WHERE "+sq.TCOL2+ " IS NOT NULL";
		ResultSet rs=statement.executeQuery(command);
		
		userID=new HashSet<String>();
		userReports=new ArrayList<UserReport>();
		
		while(rs.next()){
			if(rs.getString(sq.TCOL2) !=null)
				userID.add(rs.getString(sq.TCOL2));
		}
		for(String user : userID){
			System.out.println(user);
			userReports.add(new UserReport(user,begin,end));
		}
	}
	/**
	 * Create 3 column excel table per sheet for each stern worker
	 * denoting time_in, time_out, total hours worked
	 * @return
	 */
	@SuppressWarnings("unused")
	public HSSFWorkbook generateExcelRecords(){
		SternQuery sq=SternQuery.getInstance();
		
		HSSFWorkbook sternLogs= new HSSFWorkbook();
		
        for(UserReport user : userReports){
                
                HSSFSheet sheet=sternLogs.createSheet(user.sternID);
                
                Row labels=sheet.createRow(0);
                
                final String [] columnNames={sq.TCOL3,sq.TCOL4,"time(hrs)"};

                for(int k=0; k < 3; ++k){
                        labels.createCell(k,Cell.CELL_TYPE_STRING);
                        labels.getCell(k).setCellValue(columnNames[k]);
                }
                        
                ArrayList<Entry> ts=user.getTimeSlotsWorked();
                for(int k=1; k < ts.size(); ++k){

                        Row row=sheet.createRow(k);

                        for(int i=0; i < 2; ++i )
                                row.createCell(i,Cell.CELL_TYPE_STRING);
                        
                        row.createCell(2,Cell.CELL_TYPE_NUMERIC);
                        
                        row.getCell(0).setCellValue(ts.get(k).getStartTime());
                        row.getCell(1).setCellValue(ts.get(k).getEndTime());
                        row.getCell(2).setCellValue(ts.get(k).hoursWorked);
                }
                Row total=sheet.createRow(ts.size());
                Cell ctotal=total.createCell(2,Cell.CELL_TYPE_NUMERIC);
                int colLowerBound=1,colUpperBound=ts.size();
                ctotal.setCellFormula("SUM("+colLowerBound+":"+colUpperBound+")");
        }
        return sternLogs;
	}
}