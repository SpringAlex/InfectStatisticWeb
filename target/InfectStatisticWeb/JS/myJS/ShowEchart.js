//得到省份名
var province=getUrlQueryString('province');
province=decodeURI(province);
document.getElementById("province").textContent=province;
//alert(province);
//alert("good");


function getUrlQueryString(names, urls) {
    urls = urls || window.location.href;
    urls && urls.indexOf("?") > -1 ? urls = urls
        .substring(urls.indexOf("?") + 1) : "";
    var reg = new RegExp("(^|&)" + names + "=([^&]*)(&|$)", "i");
    var r = urls ? urls.match(reg) : window.location.search.substr(1)
        .match(reg);
    if (r != null && r[2] != "")
        return unescape(r[2]);
    return null;
};


var myChart = echarts.init(document.getElementById('main'));

option1 = {
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

option2 = {
    xAxis: {
        type: 'category',
        data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
    },
    yAxis: {
        type: 'value'
    },
    series: [{
        data: [820, 932, 901, 934, 1290, 1330, 130],
        type: 'line'
    }]
};

option3 = {
    xAxis: {
        type: 'category',
        data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
    },
    yAxis: {
        type: 'value'
    },
    series: [{
        data: [820, 932, 91, 934, 1290, 130, 1320],
        type: 'line'
    }]
};

var option=option1;
// 使用刚指定的配置项和数据显示图表。
myChart.setOption(option);

function aaa(id) {
    if(id=="but-1"){
        option=option1;
        //alert("1");
        myChart.setOption(option);
    }
    if(id=="but-2"){
        option=option2;
        //alert("2");
        myChart.setOption(option);
    }
    if(id=="but-3"){
        option=option3;
        //alert("3");
        myChart.setOption(option);
    }
    //alert(id);
}


