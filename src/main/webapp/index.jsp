<%@page import="mydao.*"%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
    <script type="text/javascript" src="JS/jquery-3.3.1.min.js"></script>
    <script  type="text/javascript" src="JS/svgmap/js/lib/raphael-min.js"></script>
    <script  type="text/javascript" src="JS/svgmap/js/res/chinaMapConfig.js"></script>
    <script  type="text/javascript" src="JS/svgmap/js/res/worldMapConfig.js"></script>
    <script  type="text/javascript" src="JS/svgmap/js/map.js"></script>
    <script type="text/javascript" src="JS/myJS/myIndex.js"></script>

    <link href="CSS/index.css" rel="stylesheet" type="text/css"/>


	

</head>
<body>
<div class="wrap">
    <h2>InfectStatisticWeb</h2>
    <!--地图总体数据预览 -->
    <div class="chinaData">
        <ul class="dataInfo_current">
            <li>
                <div class="dataTitle">现有确诊</div>
                <div id="currentInflu">XXXX</div>
                <!--span class="yesterday">昨天</span-->
                <!--span class="yesterdayInflu">xxxx</span-->
            </li>
            <li>
                <div class="dataTitle">现有疑似</div>
                <div id=currentSuspect>XXXX</div>
                <!--span class="yesterday">昨天</span-->
                <!--span class="yesterdaySuspect">xxxx</span-->
            </li>
            <li>
                <!--div class="dataTitle">现有重症</div-->
                <!--div class="currentSerious">XXXX</div-->
                <!--span class="yesterday">昨天</span-->
                <!--span class="yesterdaySerious">xxxx</span-->
            </li>
        </ul>
        <div>
            <br>
        </div>
        <ul class="dataInfo_total">
            <li>
                <div class="dataTitle">累计确诊</div>
                <div id="totalInflu">XXXX</div>
                <!--span class="yesterday">昨天</span-->
                <!--span class="totalYesterdayInflu">xxxx</span-->
            </li>
            <li>
                <div class="dataTitle">累计治愈</div>
                <div id="totalSafe">XXXX</div>
                <!--span class="yesterday">昨天</span-->
                <!--span class="totalYesterdaySafe">xxxx</span-->
            </li>
            <li>
                <div class="dataTitle">累计死亡</div>
                <div id="totalDead">XXXX</div>
                <!--span class="yesterday">昨天</span-->
                <!--span class="totalYesterdayDead">xxxx</span-->
            </li>
        </ul>
    </div>

    <div class="tabbox">
        　　<ul>
        　　　　<li>累计感染</li>
        　　　　<li class="active">当前感染</li>
        　　</ul>
        <div class="content">
            　　<div>
            　　　     <p>累计感染地图</p>
            <div class="data">
                <input type="date" id="total_day" value="2020-02-02"/>
                <input type="button" class="btn" value="查询"/>
            </div>
            　　</div>
            <div class="active">　
                <div class="data">
                    <input type="date" id="cur_day" value="2020-02-02"/>
                    <input type="button" class="btn" value="查询"/>
                </div>
                <!-- items item9 代表ChinaMap-->
                <div class="items" id="Item9">
                    <h2>全国当前疫情</h2>
                    <a href="javascript:;" class="fold"></a>
                    <div class="itemCon">
                        <div id="ClickCallback" ></div>
                        <div id="Region" style="position:relative; height:360px;">
                            <div class="regionList" id="MapControl">
                                <ul class="list1">
                                </ul>
                                <ul class="list2">
                                </ul>
                                <ul class="list3">
                                </ul>
                            </div>


                            <div class="regionMap" id="RegionMap"></div>

                    </div>
                </div>
            </div>
            　　
            　　</div>
    </div>
        <div><img id="mapList" width=890px height=60px alt="" src="data:image/png;base64,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"></div>
    </div>
</div>
 	<script>
 	var cur_day=document.getElementById("cur_day").value;
 	var cnt=0;
 	var provinceName=new Array(34);
    var ip=new Array(34);
    var initColor=new Array(34);
    var data = {"jiangsu":{"value":"0","index":"1","stateInitColor":"4"},
            "henan":{"value":"19.77%","index":"2","stateInitColor":"0"},
            "anhui":{"value":"10.85%","index":"3","stateInitColor":"0"},
            "zhejiang":{"value":"10.02%","index":"4","stateInitColor":"0"},
            "liaoning":{"value":"8.46%","index":"5","stateInitColor":"0"},
            "beijing":{"value":"4.04%","index":"6","stateInitColor":"1"},
            "hubei":{"value":"3.66%","index":"7","stateInitColor":"1"},
            "jilin":{"value":"2.56%","index":"8","stateInitColor":"1"},
            "shanghai":{"value":"2.47%","index":"9","stateInitColor":"1"},
            "guangxi":{"value":"2.3%","index":"10","stateInitColor":"1"},
            "sichuan":{"value":"1.48%","index":"11","stateInitColor":"2"},
            "guizhou":{"value":"0.99%","index":"12","stateInitColor":"2"},
            "hunan":{"value":"0.78%","index":"13","stateInitColor":"2"},
            "shandong":{"value":"0.7%","index":"14","stateInitColor":"2"},
            "guangdong":{"value":"0.44%","index":"15","stateInitColor":"2"},
            "jiangxi":{"value":"0.34%","index":"16","stateInitColor":"3"},
            "fujian":{"value":"0.27%","index":"17","stateInitColor":"3"},
            "yunnan":{"value":"0.23%","index":"18","stateInitColor":"3"},
            "hainan":{"value":"0.21%","index":"19","stateInitColor":"3"},
            "shanxi":{"value":"0.11%","index":"20","stateInitColor":"3"},
            "hebei":{"value":"0.11%","index":"21","stateInitColor":"4"},
            "neimongol":{"value":"0.04%","index":"22","stateInitColor":"4"},
            "tianjin":{"value":"0.04%","index":"23","stateInitColor":"4"},
            "gansu":{"value":"0.04%","index":"24","stateInitColor":"4"},
            "shaanxi":{"value":"0.02%","index":"25","stateInitColor":"4"},
            "macau":{"value":"1.0%","index":"26","stateInitColor":"6"},
            "hongkong":{"value":"1.0%","index":"27","stateInitColor":"6"},
            "taiwan":{"value":"1.0%","index":"28","stateInitColor":"6"},
            "qinghai":{"value":"1.0%","index":"29","stateInitColor":"6"},
            "xizang":{"value":"1.0%","index":"30","stateInitColor":"6"},
            "ningxia":{"value":"1.0%","index":"31","stateInitColor":"6"},
            "xinjiang":{"value":"1.0%","index":"32","stateInitColor":"6"},
            "heilongjiang":{"value":"1.0%","index":"33","stateInitColor":"6"},
            "chongqing":{"value":"100","index":"34","stateInitColor":"6"}};
	<%
		String date ="2020-02-02";
		InfectStatistic infectStatistic=new InfectStatistic(date);
		infectStatistic.analyzeInfectSituation();

		for (int i=0;i<34;i++)
		{
			int theIp=infectStatistic.allProvince.get(i).ip;
			//String theIp="123";
			String name=infectStatistic.allProvinceName2[i];
			int color;
			if (theIp==0)
				color=0;
			else if (theIp>=1 && theIp<=9)
				color=1;
			else if (theIp>=10 && theIp<=99)
				color=2;
			else if (theIp>=100 && theIp<=499)
				color=3;
			else if (theIp>=500 && theIp<=999)
				color=4;
			else if (theIp>=1000 && theIp<=10000)
				color=5;
			else 
				color=6;
			
			
		%>
			ip[cnt]="<%=theIp %>";
			
			provinceName[cnt]="<%=name %>";
			var initColor="<%=color%>";
			data[provinceName[cnt]]["value"]=ip[cnt];
			data[provinceName[cnt]]["stateInitColor"]=initColor;					
			cnt=cnt+1;
		<%}%>
		
		document.getElementById("currentInflu").textContent="<%=infectStatistic.getNationSituation()[0] %>";
		document.getElementById("currentSuspect").textContent="<%=infectStatistic.getNationSituation()[1] %>";
		document.getElementById("totalInflu").textContent="<%=infectStatistic.getNationSituation()[2] %>";
		document.getElementById("totalSafe").textContent="<%=infectStatistic.getNationSituation()[3] %>";
		document.getElementById("totalDead").textContent="<%=infectStatistic.getNationSituation()[4] %>";
	</script>
<script type="text/javascript" src="JS/svgmap/js/lib/SyntaxHighlighter.js"></script>
</body>
</html>