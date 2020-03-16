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
	private String lastDate;  //�û�ѡ�������
	private String pathOfLog = "D:/log/";  //��־�ļ�����Ŀ¼
	
	public String[] allNeedDate;  //��¼�����û�ѡ������֮ǰ������ѡ�����죩�����ڣ���ʽ��yyyy-mm-dd
	//public ArrayList<Province> allProvince = new ArrayList<Province>();  //���ڼ�¼ʡ�����ʵ������
	public ArrayList<Province> allProvince;
	private String[] allProvinceName = {"����","����","������","�ӱ�","ɽ��","����","����","�ຣ","ɽ��","����","����","�㽭","����","����","����","����","̨��","����","����","����","�Ĵ�","����","�㶫","���ɹ�","�½�","����","����","����","����","�Ϻ�","���","����","���","����"};
	
	
	public class Province
	{
		public String provinceName;  //ʡ������

		public int ip;  //��ǰ��Ⱦ������Ŀ
	    public int sp;  //��ǰ���ƻ�����Ŀ
	    public int cure;  //��ǰ���ۼƣ�������Ŀ
	    public int dead;  //��ǰ���ۼƣ�������Ŀ
	    
	    public int allIp;  //�ۼ�ȷ�ﻼ������
	    public int newIp;  //��������ȷ������
	    
	    //�ö�̬���ݼ�¼ÿ������
	    public ArrayList<Integer> everyDayAddIp = new ArrayList<Integer>();//ÿ������ȷ��
	    public ArrayList<Integer> everyDayIP = new ArrayList<Integer>();//ÿ���ۼ�ȷ����
	    public ArrayList<Integer> everyDayCure = new ArrayList<Integer>();//ÿ���ۼ�������
	    public ArrayList<Integer> everyDayDead = new ArrayList<Integer>();//ÿ���ۼ�������
	    
	    //Description:���캯��
	    public Province(String provinceName)
	    {
	    	this.provinceName=provinceName;
	    	ip=0;
	    	sp=0;
	    	cure=0;
	    	dead=0;
	    	allIp=0;
	    	newIp=0;
	    }
	    
	   
	   //Description:��¼����
	   public void RecordTrend()
	   {
		   everyDayAddIp.add(newIp);//����ȷ������
		   everyDayIP.add(allIp);//�ۼ�ȷ������
		   everyDayCure.add(cure);//�ۼ���������
		   everyDayDead.add(dead);//�ۼ���������
		   
		   newIp = 0;//���������ȷ����������ͳ����ɺ�����ȷ����������
	   }
	}
	
	
	
	
	//Description:���캯��
	public InfectStatistic(String date) {
		this.lastDate = date;
		allProvince = new ArrayList<Province>();
		//��������ʡ��ʵ������
		for (int i = 0;i < 34;i++)
		{
			Province province = new Province(allProvinceName[i]);
			allProvince.add(province);
		}
		
		getAllNeedDate();//��ȡ�����û�ѡ�������ǰ�����ڣ�����ѡ�����죩
	}
	
	public ArrayList<Province> getAllprovince()
	{
		return allProvince;
	}
	
	//Description:��ȡ�����û�ѡ�������ǰ�����ڣ�����ѡ�����죩
	private void getAllNeedDate()
	{
		int count=0;  //���ڼ�¼����Ҫ������ڵ�����
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

	//Description:��ȡ��־�ļ����õ���ʡ����������
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
						&& oneLineOfFile.length() != 0  //����ȡ����
						&& oneLineOfFile.startsWith("//") == false)  //����ȡע����
				{
					//System.out.println(oneLineOfFile);	
					
					//ͳ��ʡ�ݵĸ�Ⱦ����������������������������������
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
	

	//Description:������־�ļ��е�ÿ�У�ͳ�Ƴ���Ӧ�������
	//Input:����־�ļ���ȡ����һ����Ϣ
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
					if (splitString[1].equals("����"))
					{
						allProvince.get(i).dead += countOfPeople;
						allProvince.get(i).ip -= countOfPeople;
						break;
					}
					else if (splitString[1].equals("����"))
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
					if (splitString[1].equals("����")
						&& splitString[2].equals("��Ⱦ����"))
					{	
						allProvince.get(i).ip += countOfPeople;
						allProvince.get(i).newIp += countOfPeople;//������Ⱦ��������
						allProvince.get(i).allIp += countOfPeople;//�ۼƸ�Ⱦ��������
						break;
					}
					else if (splitString[1].equals("����")
						&& splitString[2].equals("���ƻ���"))
					{
						allProvince.get(i).sp += countOfPeople;
						break;
					}
					else if (splitString[1].equals("���ƻ���")
						&& splitString[2].equals("ȷ���Ⱦ"))
					{
						allProvince.get(i).sp -= countOfPeople;
						allProvince.get(i).ip += countOfPeople;
						allProvince.get(i).newIp += countOfPeople;//������Ⱦ��������
						allProvince.get(i).allIp += countOfPeople;//�ۼƸ�Ⱦ��������
						break;
					}
					else if (splitString[1].equals("�ų�")
						&& splitString[2].equals("���ƻ���"))
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
					if (splitString[1].equals("��Ⱦ����"))
					{
						allProvince.get(i).ip -= countOfPeople;
					}
					else if (splitString[1].equals("���ƻ���"))
					{
						allProvince.get(i).sp -= countOfPeople;
					}
				}
				else if (allProvince.get(i).provinceName.equals(splitString[3]))
				{
					if (splitString[1].equals("��Ⱦ����"))
					{
						allProvince.get(i).ip += countOfPeople;
					}
					else if (splitString[1].equals("���ƻ���"))
					{
						allProvince.get(i).sp += countOfPeople;
					}
				}
			}
		}
	} 
	

	//Description:��ȡ�ַ������е����ֲ�ת������������
	//Input:һ�������������ַ����ַ���
	//Return:һ������
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