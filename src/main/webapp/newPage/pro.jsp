<%@page import="mydao.*"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>省份页面</title>
    <link type="text/css" href="../CSS/province.css" rel="stylesheet"/>
</head>
<body>
<div id="wrap">

<p id="province">this is my province page</p>
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
    <div class="provinceData">
        <ul class="dataBoard">
            <li>
                <div class="dataTitle">现有确诊</div>
                <div class="currentInflu">XXXX</div>
                <!-- span class="yesterday">昨天</span-->
                <!--span class="yesterdayInflu">xxxx</span-->
            </li>

            <li>
                <div class="dataTitle">累计确诊</div>
                <div class="totalInflu">XXXX</div>
                <!--span class="yesterday">昨天</span-->
                <!--span class="totalYesterdayInflu">xxxx</span-->
            </li>

            <li>
                <div class="dataTitle">累计治愈</div>
                <div class="totalSafe">XXXX</div>
                <!--span class="yesterday">昨天</span-->
                <!--span class="totalYesterdaySafe">xxxx</span-->
            </li>

            <li>
                <div class="dataTitle">累计死亡</div>
                <div class="totalDead">XXXX</div>
                <!--span class="yesterday">昨天</span-->
                <!--span class="totalYesterdayDead">xxxx</span-->
            </li>
        </ul>

    </div>
    <div class="data">
        <input type="date" id="cur_day_province" value="2020-02-02"/>
        <input type="button" class="btn" value="查询"/>
    </div>
<div id="main" style="width: 600px;height:400px;margin:0 auto;"></div>
    <div class="buttons">
        <button  id="but-1" type="button" value="btn-1" onclick="aaa(this.id)">新增确诊趋势</button>
        <button  id="but-2" type="button" value="btn-2" onclick="aaa(this.id)">累计确诊趋势</button>
        <button  id="but-3" type="button" value="btn-3" onclick="aaa(this.id)">累计治愈/死亡</button>
        <style type="text/css" rel="stylesheet">
            button{
                width: 100px;
                height: 30px;
            }

        </style>
    </div>
  <script>
  
  
  
  
  var cnt=0;
  var everyDate=new Array();
  var everIp=new Array();
    <%
    request.setCharacterEncoding("utf-8");
    String txtMsg=request.getParameter("test2"); %>
    document.getElementById("province").textContent="<%=txtMsg %>";
   <% 

    InfectStatistic infectStatistic=new InfectStatistic("2020-02-02");
    infectStatistic.analyzeInfectSituation();
    for (int i=0;i<34;i++)
    {
    	if (infectStatistic.allProvince.get(i).provinceName.equals(txtMsg))
    	{
    		for (int j=0;j<infectStatistic.allProvince.get(i).everyDayAddIp.size();j++)
    		{%>
    			everyDate[cnt]="<%=infectStatistic.allNeedDate[i]%>";
    			everIp[cnt]="<%=infectStatistic.allProvince.get(i).everyDayAddIp.get(j)%>";
    			cnt=cnt+1;
    		<%}
    	}
    }
  %> 
 
  alert(everIp[0]);
  alert(everIp[10]);
  alert(everyDate[0]);
  var option1;
  option1.xAxis.type='category';
  option1.yAxis.type='value';
  option1.yAxis.series.type='line';
  option1.xAxis.data=everyDate;
  option1.yAxis.series[0].data=everIp;

 </script>
    <script type="text/javascript" src="../JS/echarts.min.js"></script>
    <script type="text/javascript" src="../JS/myJS/ShowEchart.js"></script>

	


<script type="text/javascript" src="../JS/svgmap/js/lib/SyntaxHighlighter.js"></script>
</div>
</body>
</html>