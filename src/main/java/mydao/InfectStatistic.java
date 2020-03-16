package mydao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.text.StyledEditorKit.ForegroundAction;



public class InfectStatistic {
	private String lastDate;  //用户选择的日期
	private String pathOfLog = "D:/log/";  //日志文件所在目录
	
	public String[] allNeedDate;  //记录所有用户选定日期之前（包括选定当天）的日期，格式：yyyy-mm-dd
	//public ArrayList<Province> allProvince = new ArrayList<Province>();  //用于记录省份类的实例对象
	public ArrayList<Province> allProvince;
	private String[] allProvinceName = {"辽宁","吉林","黑龙江","河北","山西","陕西","甘肃","青海","山东","安徽","江苏","浙江","河南","湖北","湖南","江西","台湾","福建","云南","海南","四川","贵州","广东","内蒙古","新疆","广西","西藏","宁夏","北京","上海","天津","重庆","香港","澳门"};
	public String[] allProvinceName2 = {"liaoning","jilin","heilongjiang","hebei","shanxi","shaanxi","gansu","qinghai","shandong","anhui","jiangsu","zhejiang","henan","hubei","hunan","jiangxi","taiwan","fujian","yunnan","hainan","sichuan","guizhou","guangdong","neimongol","xinjiang","guangxi","xizang","ningxia","beijing","shanghai","tianjin","chongqing","hongkong","macau"};
			
	public class Province
	{
		public String provinceName;  //省份名字
		public String provinceName2;//省份拼音名字

		public int ip;  //当前感染患者数目
	    public int sp;  //当前疑似患者数目
	    public int cure;  //当前（累计）治愈数目
	    public int dead;  //当前（累计）死亡数目
	    
	    public int allIp;  //累计确诊患者数量
	    public int newIp;  //当天新增确诊数量
	    
	    //用动态数据记录每天的情况
	    public ArrayList<Integer> everyDayAddIp = new ArrayList<Integer>();//每天新增确诊
	    public ArrayList<Integer> everyDayIP = new ArrayList<Integer>();//每天累计确诊数
	    public ArrayList<Integer> everyDayCure = new ArrayList<Integer>();//每天累计治愈数
	    public ArrayList<Integer> everyDayDead = new ArrayList<Integer>();//每天累计死亡数
	    
	    //Description:构造函数
	    public Province(String provinceName,String provinceName2)
	    {
	    	this.provinceName=provinceName;
	    	this.provinceName2=provinceName2;
	    	ip=0;
	    	sp=0;
	    	cure=0;
	    	dead=0;
	    	allIp=0;
	    	newIp=0;
	    }
	    
	   
	   //Description:记录趋势
	   public void RecordTrend()
	   {
		   everyDayAddIp.add(newIp);//新增确诊趋势
		   everyDayIP.add(allIp);//累计确诊趋势
		   everyDayCure.add(cure);//累计治愈趋势
		   everyDayDead.add(dead);//累计死亡趋势
		   
		   newIp = 0;//当天的新增确诊量，当天统计完成后，新增确诊数量清零
	   }
	}
	
	
	
	
	//Description:构造函数
	public InfectStatistic(String date) {
		this.lastDate = date;
		allProvince = new ArrayList<Province>();
		//创建所有省份实例对象
		for (int i = 0;i < 34;i++)
		{
			Province province = new Province(allProvinceName[i],allProvinceName2[i]);
			allProvince.add(province);
		}
		
		getAllNeedDate();//获取符合用户选择的日期前的日期（包括选定当天）
	}
	
	public int[] getNationSituation()
	{
		int nationIp=0;//全国现有确诊
		int nationSp=0;//全国现有疑似
		int nationAllIp=0;//全国累计确诊
		int nationAllCure=0;//全国累计(现有)治愈
		int nationAllDead=0;//全国累计(现有)死亡
		
		for (int i=0;i<34;i++)
		{
			nationIp+=allProvince.get(i).ip;
			nationSp+=allProvince.get(i).sp;
			nationAllIp+=allProvince.get(i).allIp;
			nationAllCure+=allProvince.get(i).cure;
			nationAllDead+=allProvince.get(i).dead;
		}
		
		int[] nation= {nationIp,nationSp,nationAllIp,nationAllCure,nationAllDead};
		return nation;
	}
	
	//Description:获取符合用户选择的日期前的日期（包括选定当天）
	private void getAllNeedDate()
	{
		int count=0;  //用于记录符合要求的日期的天数
		File file = new File(pathOfLog);
        File[] allLogFiles = file.listFiles();   
        String[] allLogFilesName = new String[allLogFiles.length];
        for(int i = 0;i < allLogFiles.length;i++)
        {
        	allLogFilesName[i] = allLogFiles[i].getName();
        	if (allLogFilesName[i].compareTo(lastDate+".log.txt") <= 0)
        	{
        		count++;
        	}
        	else 
        	{
        		break;
        	}
        }
        
        if (count >= 1){
        	allNeedDate = new String[count];
        	for (int i = 0;i < count;i++)
        	{
        		int index = allLogFilesName[i].indexOf('.');
        		allNeedDate[i] = allLogFilesName[i].substring(0, index);
        	}
        }
	}

	//Description:读取日志文件，得到各省的疫情数据
	public void analyzeInfectSituation() throws IOException
	{
		for (int i = 0;i < allNeedDate.length;i++)
		{
			String oneLineOfFile = null;
			try 
			{
				InputStreamReader isr = new InputStreamReader(
						//new FileInputStream(pathOfLog+allLogFilesName[i]), "UTF-8");
						new FileInputStream(pathOfLog+allNeedDate[i]+".log.txt"));
				BufferedReader br = new BufferedReader(isr);
				while ((oneLineOfFile = br.readLine()) != null
						&& oneLineOfFile.length() != 0  //不读取空行
						&& oneLineOfFile.startsWith("//") == false)  //不读取注释行
				{
					//System.out.println(oneLineOfFile);	
					
					//统计省份的感染人数，疑似人数，治愈人数和死亡人数
					getProvincialInformation(oneLineOfFile);
				}
				br.close();
				isr.close();
			} 
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			for (int j = 0;j < allProvince.size();j++)
			{
				allProvince.get(j).RecordTrend();
			}

		}
	}
	

	//Description:分析日志文件中的每行，统计出相应人数情况
	//Input:从日志文件读取出的一行信息
	private void getProvincialInformation(String oneLineOfFile)
	{
		String[] splitString = oneLineOfFile.split(" ");
		int countOfPeople = getStringNumber(splitString[splitString.length - 1]); 
		
		if (splitString.length == 3)
		{
			for (int i = 0;i < allProvince.size();i++)
			{
				if (allProvince.get(i).provinceName.equals(splitString[0]))
				{
					if (splitString[1].equals("死亡"))
					{
						allProvince.get(i).dead += countOfPeople;
						allProvince.get(i).ip -= countOfPeople;
						break;
					}
					else if (splitString[1].equals("治愈"))
					{
						allProvince.get(i).cure += countOfPeople;
						allProvince.get(i).ip -= countOfPeople;
						break;
					}
				}
			}
		}
		else if (splitString.length == 4)
		{
			for (int i = 0;i < allProvince.size();i++)
			{
				if (allProvince.get(i).provinceName.equals(splitString[0]))
				{
					if (splitString[1].equals("新增")
						&& splitString[2].equals("感染患者"))
					{	
						allProvince.get(i).ip += countOfPeople;
						allProvince.get(i).newIp += countOfPeople;//新增感染患者增加
						allProvince.get(i).allIp += countOfPeople;//累计感染患者增加
						break;
					}
					else if (splitString[1].equals("新增")
						&& splitString[2].equals("疑似患者"))
					{
						allProvince.get(i).sp += countOfPeople;
						break;
					}
					else if (splitString[1].equals("疑似患者")
						&& splitString[2].equals("确诊感染"))
					{
						allProvince.get(i).sp -= countOfPeople;
						allProvince.get(i).ip += countOfPeople;
						allProvince.get(i).newIp += countOfPeople;//新增感染患者增加
						allProvince.get(i).allIp += countOfPeople;//累计感染患者增加
						break;
					}
					else if (splitString[1].equals("排除")
						&& splitString[2].equals("疑似患者"))
					{
						allProvince.get(i).sp -= countOfPeople;
						break;
					}
				}
			}
		}
		else if (splitString.length == 5)
		{			
			for (int i = 0;i < allProvince.size();i++)
			{
				if (allProvince.get(i).provinceName.equals(splitString[0]))
				{
					if (splitString[1].equals("感染患者"))
					{
						allProvince.get(i).ip -= countOfPeople;
					}
					else if (splitString[1].equals("疑似患者"))
					{
						allProvince.get(i).sp -= countOfPeople;
					}
				}
				else if (allProvince.get(i).provinceName.equals(splitString[3]))
				{
					if (splitString[1].equals("感染患者"))
					{
						allProvince.get(i).ip += countOfPeople;
					}
					else if (splitString[1].equals("疑似患者"))
					{
						allProvince.get(i).sp += countOfPeople;
					}
				}
			}
		}
	} 
	

	//Description:提取字符串当中的数字并转换成整数返回
	//Input:一个包含了数字字符的字符串
	//Return:一个整数
	private int getStringNumber(String str)
	{
		str = str.trim();
		String numString = "";
		
		if(str != null && !"".equals(str))
		{
			for(int i = 0;i < str.length();i++)
			{
				if(str.charAt(i) >= 48 && str.charAt(i) <= 57)
				{
					numString += str.charAt(i);
				}
			}
		}
		
		return Integer.parseInt(numString);
	} 
}