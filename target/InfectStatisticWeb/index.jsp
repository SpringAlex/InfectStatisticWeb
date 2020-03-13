<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
    <script type="text/javascript" src="JS/jquery-3.3.1.min.js"></script>
    <script  type="text/javascript" src="JS/svgmap/js/lib/raphael-min.js"></script>
    <script  type="text/javascript" src="JS/svgmap/js/res/chinaMapConfig.js"></script>
    <script  type="text/javascript" src="JS/svgmap/js/res/worldMapConfig.js"></script>
    <script  type="text/javascript" src="/JS/svgmap/js/map.js"></script>
    <script type="text/javascript" src="index.js"></script>

    <script type="text/javascript" src="JS/echarts.min.js"></script>

    <link href="index.css" rel="stylesheet" type="text/css"/>

</head>
<body>
<div class="wrap">
    <div class="items" id="Item9">
        <h2>外部控制</h2>
        <a href="javascript:;" class="fold"></a>
        <div class="itemCon">
            <style type="text/css">

                .regionList{float:left; margin-top:15px;}

                .regionList ul{float:left; width:110px; height:100%; margin-right:5px; display:inline; font-family:"微软雅黑"}

                .regionList ul li{ height:24px; margin-left:10px; padding:0 0px; border:1px solid #fff; line-height:24px;}

                .regionList ul li.select{border:1px solid #D3D3D3; background:#FFF1BF}



                .mapInfo i{ display:inline-block; width:15px; height:15px; margin-top:5px; line-height:15px; font-style:normal; background:#aeaeae; color:#fff; font-size:11px; font-family: Tahoma; -webkit-border-radius:15px; border-radius:15px; text-align:center}

                .mapInfo i.active{background:#E27F21;}

                .mapInfo span{ padding:0 5px 0 3px;}

                .mapInfo b{ font-weight:normal; color:#2770B5}



                .regionMap{float:left; margin-left:70px; display:inline;}

            </style>
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
                <div id="MapColor"
                     style=" display:none;
			float:left;
			width:100px;
			height:250px;
			margin:100px 0 0 10px;
			display:inline;
			background:url(JS/svgmap/images/map_color.png) center 0;"></div>
            </div>
        </div>
    </div>
    <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
    <div id="main" style="width: 600px;height:400px;">
        <script>
            var myChart = echarts.init(document.getElementById('main'));

            option = {
                xAxis: {
                    type: 'category',
                    data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
                },
                yAxis: {
                    type: 'value'
                },
                series: [{
                    data: [820, 932, 901, 934, 1290, 1330, 1320],
                    type: 'line'
                }]
            };
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        </script>
    </div>
</div>

</div>
</div>
<script type="text/javascript" src="JS/svgmap/js/lib/SyntaxHighlighter.js"></script>
</body>
</html>
