$(function(){
    var num1=0.20;


    var data = {"jiangsu":{"value":num1,"index":"1","stateInitColor":"4"},
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
        "chongqing":{"value":"1.0%","index":"34","stateInitColor":"6"}};

    var i = 1;

    for(k in data){

        if(i <= 12){

            var _cls = i < 4 ? 'active' : '';

            $('#MapControl .list1').append('<li name="'+k+'"><div class="mapInfo"><i class="'+_cls+'">'
                +(i++)+'</i><span>'+chinaMapConfig.names[k]
                +'</span><b>'
                +data[k].value
                +'</b></div></li>')

        }else if(i <= 24){

            $('#MapControl .list2').append('<li name="'+k+'"><div class="mapInfo"><i>'
                +(i++)+'</i><span>'+chinaMapConfig.names[k]
                +'</span><b>'
                +data[k].value
                +'</b></div></li>')

        }else{

            $('#MapControl .list3').append('<li name="'+k+'"><div class="mapInfo"><i>'
                +(i++)+'</i><span>'+chinaMapConfig.names[k]
                +'</span><b>'
                +data[k].value
                +'</b></div></li>')

        }

    }



    var mapObj_1 = {};

    var stateColorList1 = ['4f060d','811c24','cb2a30','e45a4f','f59e83','fcebcf','fcebcf'];



    $('#RegionMap').SVGMap({

        external: mapObj_1,

        mapName: 'china',

        mapWidth: 350,

        mapHeight: 350,

        stateData: data,

        // stateTipWidth: 118,

        // stateTipHeight: 47,

        // stateTipX: 2,

        // stateTipY: 0,
        clickCallback: function(stateData, obj){
            window.location.href="newPage/province.jsp";
        },


        stateTipHtml: function (mapData, obj) {

            var _value = mapData[obj.id].value;

            var _idx = mapData[obj.id].index;

            var active = '';

            _idx < 4 ? active = 'active' : active = '';

            var tipStr = '<div class="mapInfo"><i class="' + active + '">' + _idx + '</i><span>' + obj.name + '</span><b>' + _value + '</b></div>';

            return tipStr;

        }

    });
    $('#MapControl li').hover(function () {
        var thisName = $(this).attr('name');
        var thisHtml = $(this).html();
        $('#MapControl li').removeClass('select');
        $(this).addClass('select');
        $(document.body).append('<div id="StateTip"></div');
        $('#StateTip').css({
            left: $(mapObj_1[thisName].node).offset().left - 50,
            top: $(mapObj_1[thisName].node).offset().top - 40
        }).html(thisHtml).show();
        mapObj_1[thisName].attr({

            fill: '#c7fffd'
        });
    }, function () {
        var thisName = $(this).attr('name');
        $('#StateTip').remove();
        $('#MapControl li').removeClass('select');
        mapObj_1[$(this).attr('name')].attr({
            fill: "#" + stateColorList1[data[$(this).attr('name')].stateInitColor]
        });
    });
    $('#MapColor').show();


});
