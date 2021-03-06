package com.gvit.gims.attendance.Export;
import java.io.FileNotFoundException;
import java.io.IOException;
import android.content.Context;
import android.util.Log;
import com.Wsdl2Code.WebServices.Attendance.Attendance;
import com.Wsdl2Code.WebServices.Attendance.IWsdl2CodeEvents;
import com.Wsdl2Code.WebServices.Attendance.VectorString;
import com.csvreader.CsvReader;

/**
 * @author Ajaykumar Vasireddy
 * @version 0.1
 * @since 2014
 */
public class LoadCSVToDB implements IWsdl2CodeEvents{
	
	private Context context;
	private String filePath;
	private String absentee;
	private String subject;
	private String period;
	private String year;
	private String section;
	private String department;
	private String group;
	private String attenDate;

	public LoadCSVToDB(Context context, String filePath){
		this.context = context;
		this.filePath = filePath;
		persisToDB();
	}
	
	private void persisToDB(){
		try {
			CsvReader reader = new CsvReader(filePath);
			reader.readHeaders();
			VectorString absentees = new VectorString();
			while(reader.readRecord()){
				attenDate = reader.get("Date");
				group = reader.get("Group");
				department = reader.get("Department");
				section = reader.get("Section");
				year = reader.get("Year");
				period = reader.get("Period");
				subject = reader.get("Subject");
				absentee = reader.get("Absentees");
				absentees.add(absentee);
			}
			Attendance attendance = new Attendance(this);
			attendance.persistAttendanceAsync(attenDate, group, department, year, section, period, subject, absentees);
			
		} catch (FileNotFoundException e) {
			Log.e("LOAD ERROR", e.getMessage());
		} catch (IOException e) {
			Log.e("Read ERROR", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void Wsdl2CodeStartedRequest() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Wsdl2CodeFinished(String methodName, Object Data) {
		Log.i("Success Result", Data.toString());
	}

	@Override
	public void Wsdl2CodeFinishedWithException(Exception ex) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Wsdl2CodeEndedRequest() {
		// TODO Auto-generated method stub
		
	}

}
