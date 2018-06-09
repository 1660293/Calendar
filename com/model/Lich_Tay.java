package trantrongkim.com.model;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

public class Lich_Tay {
	private Vector<Integer> arday;
	private int day;
	private int month;
	private int year;
	public Lich_Tay()
	{
		arday=new Vector<Integer>();
		day=Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		month=Calendar.getInstance().get(Calendar.MONTH)+1;
		year=Calendar.getInstance().get(Calendar.YEAR);
		initDay(month,year);
	}
	public int getMonth() {
		return month;
	}
	public Integer getDay()
	{
		if(arday.isEmpty())
		{
			initDay(month,year);
		}
		Integer n=arday.firstElement();
		arday.remove(0);
		return n;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public Integer layNgay()
	{
		return this.day;
	}
	//  this function init day of month
	private void initDay(int month,int year)
	{
		LocalDate d= LocalDate.of(year,month,1);
		d=d.minusDays(d.getDayOfWeek().getValue());	
		for(int i=0;i<6;i++)
		{
			
			for(int j=0;j<7;j++)
			{
				arday.addElement(d.getDayOfMonth());
				d=d.plusDays(1);
			}
		}
	}
	public String dayMonthYear()
	{
		return this.day+"/"+this.month+"/"+this.year;
	}
	// hàm tăng 1 ngày
	public void plusOneMonth()
	{
		if(this.month==12)
		{
			this.month=1;
			this.year+=1;
		}
		else this.month+=1;
		arday.removeAllElements();
		initDay(month, year);
	}
	// hàm giảm 1 ngày
	public void lessOneMonth()
	{
		if(this.month==1)
		{
			this.month=12;
			this.year-=1;
		}
		else month-=1;
		arday.removeAllElements();
		initDay( month, year);
	}
	// kiểm tra định dạng ngày tháng năm
	public boolean check(String str)
	{
		SimpleDateFormat sf=new SimpleDateFormat("dd/MM/yyyy");
		sf.setLenient(false);
		try {
			sf.parse(str);
		}catch(Exception e)
		{
			return false;
		}
		return true;
	}
}
