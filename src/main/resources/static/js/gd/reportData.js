
var entry_dljDxtzNum="";
var repair_dljDxtzNum="";
var G_startTime = "";
var G_endTime = "";
var tsName = "";
var gdOrAjType = "";
var dsrDw = "";
$(document).ready(function () {
    layui.use("laydate",function () {
        layui.laydate.render({
            elem:"#date_select",
            range: "~",
            format: "yyyy-MM-dd",
            done: function (value, date, endDate) {
                G_startTime = value.split(" ~ ")[0];
                G_endTime = value.split(" ~ ")[1];
                G_timeChage();
                $(".radio-btn-cus").addClass("bgc-white")
            }
        })
    });
    layui.use("laydate",function () {
        layui.laydate.render({
            elem:"#date_select",
            range: "~",
            format: "yyyy-MM-dd",
            done: function (value, date, endDate) {
                G_startTime = value.split(" ~ ")[0];
                G_endTime = value.split(" ~ ")[1];
                G_timeChage();
                $(".radio-btn-cus").addClass("bgc-white")
            }
        })
    });
    getBmmc();//获取办案审判庭
    getSsdrlx();//获取受送达人类型
    loadLjData();
    loadTableSlcd();
    loadLaayChart();
    loadPjsdzqChart();
    // loadSdwsfbChart();
    $("#search").click(function () {

        G_timeChage();
    });
    $(".radio-btn-cus").click(function () {

        var checkName = $(this).attr("name");
        $(".radio-btn-cus").addClass("bgc-white")
        $(this).removeClass("bgc-white")
        var dateArr = [];
        if(checkName === '全部'){
            dateArr = [];
        }else if(checkName === '本日'){
            dateArr = _getDayStartDateAndEndDateRange();
        }else if(checkName === '本周'){
            dateArr = _getWeekStartDateAndEndDateRange();
        }else if(checkName === '本月'){
            dateArr = _getMonthStartDateAndDateRange();
        }

        if(dateArr.length == 2){
            $("#date_select").val(dateArr[0] + " ~ " + dateArr[1]);
            G_startTime = dateArr[0];
            G_endTime = dateArr[1];
        }else {
            $("#date_select").val("");
            G_startTime = "";
            G_endTime = "";
        }
        G_timeChage();

    });
    $(".radio-btn-slcd").click(function () {
        radio_btn_slcd = $(this).attr("name");
        $(".radio-btn-slcd").addClass("bgc-white")
        $(this).removeClass("bgc-white")
        loadTableSlcd();
    });
    $(".radio-btn-tjkj").click(function () {

        var checkName = $(this).attr("name");
        $(".radio-btn-tjkj").addClass("bgc-white")
        $(this).removeClass("bgc-white")
        if(checkName === '工单'){
            gdOrAjType = "GD";
        } else {
            gdOrAjType = "AJ";
        }
        G_timeChage();
    });

});
function G_timeChage(){
    tsName = $("#select_bmmc").val();
    dsrDw = $("#select_ssdw").val();
    loadLjData();
    loadTableSlcd();
    loadLaayChart();
    loadPjsdzqChart();
    // loadSdwsfbChart();
}



function loadYxbr(url,eleId) {
    var dataObj = {
        startTime: G_startTime,
        endTime:G_endTime
    }

    var yxdh_chart = echarts.init(document.getElementById(eleId));
    $.ajax({
        url: url,
        type: "post",
        headers:{'Content-type':'application/json'},
        dataType:"json",
        data: JSON.stringify(dataObj),
        success: function (data) {
            var code = data.code;
            if(code == '200'){
                var dataObj = data.data;
                var dataList = [];
                var nameList = [];
                for(var key in dataObj){
                    nameList.push(key)
                    dataList.push(dataObj[key])
                }
                yxdh_chart.setOption({
                    title:{
                        text: eleId == "yxdh_chart" ? "有效本人转化率":"时间段分布",
                        textStyle:{
                            color: "#ffffff"
                        }

                    },
                    tooltip:{
                        trigger: 'axis',
                        axisPointer:{
                            type:"cross",
                            crossStyle:{
                                color:'#999'
                            }
                        }
                    },
                    legend:{
                        data: [eleId == "yxdh_chart" ? "转化率":"电话接通率"],
                        textStyle:{
                            color: '#ffffff'
                        }
                    },
                    xAxis:[
                        {
                            type: 'category',
                            data:nameList,
                            axisLabel:{
                                show:true,
                                textStyle:{
                                    color:"#ffffff",
                                    fontSize: 8
                                }
                            },
                            axisPointer:{
                                type:"shadow"
                            }
                        }
                    ],
                    yAxis:[
                        {
                            type:'value',
                            min:0,
                            axisLabel:{
                                formatter: '{value}',
                                textStyle:{
                                    color:"#ffffff"
                                }
                            }
                        }
                    ],
                    series:[
                        {
                            name: eleId == "yxdh_chart" ? "转化率":"电话接通率",
                            type:'bar',
                            itemStyle:{
                                color: eleId == "yxdh_chart" ? "#FFAF00":"#5AE220"
                            },
                            data: dataList
                        }
                    ]
                });
            }else {
                //业务异常
            }
        }, error: function () {
            alert("提交失败")
        }
    })
}

var LaayChart_dataObj = {};
var radio_btn_laay = "laayCaseData";
function loadLaayChart(){
    var dataObj = {
        startTime: G_startTime,
        endTime:G_endTime,
        tsName : tsName,
        dsrDw : dsrDw,
        gdOrahType : gdOrAjType
    }
    $.ajax({
        url: "/report/chart_case_laay.zf",
        type: "post",
        headers:{'Content-type':'application/json'},
        dataType:"json",
        data: JSON.stringify(dataObj),
        success: function (data) {
            var code = data.code;
            if(code == '200'){
                LaayChart_dataObj = data.data;
                loadLaayChartDataToHtml(LaayChart_dataObj);
            }else {
                //业务异常
            }
        }, error: function () {
            alert("提交失败")
        }
    })
}

/**
 * 第一行第二个表
 */
function loadPjsdzqChart(){
    var pjsdzq_chart = echarts.init(document.getElementById("pjsdzq_echarts"));
    var dataObj = {
        startTime: G_startTime,
        endTime:G_endTime,
        tsName : tsName,
        dsrDw : dsrDw,
        gdOrAhType : gdOrAjType
    };
    $.ajax({
        url: "/report/chart_pjsdzq.zf",
        type: "post",
        headers:{'Content-type':'application/json'},
        dataType:"json",
        data: JSON.stringify(dataObj),
        success: function (data) {
            var code = data.code;
            if(code == '200'){
                var dataObj = data.data;
                var dataList = [];
                var nameList = [];

                for(var key in dataObj){                //把案件周期排到最上面
                    if(key!='案件平均送达周期'){
                        nameList.push(key);
                        dataList.push(dataObj[key]);
                    }
                }
                for(var key in dataObj){
                    if(key=='案件平均送达周期'){
                        nameList.push(key);
                        dataList.push(dataObj[key]);
                    }
                }
                pjsdzq_chart.setOption({
                    title: {
                        text: '平均送达周期(天)',
                        left: 'center',
                        textStyle:{
                            color:'#ffffff'
                        }
                    },
                    color:['#028fff'],
                    tooltip: {
                        trigger: 'axis'
                    },
                    textStyle:{
                        color:'#ffffff'
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    xAxis: {
                        type: 'value',
                        splitLine: {
                            show: false
                        },
                        axisTick:{
                            show:false//不显示坐标轴刻度线
                        },
                        axisLine: {
                            show: false//不显示坐标轴线
                        },
                        axisLabel: {
                            show: false//不显示坐标轴上的文字
                        },
                        boundaryGap: [0, 0.01]
                    },
                    yAxis: {
                        type: 'category',
                        splitLine: {
                            show: false
                        },
                        axisTick:{
                            show:false//不显示坐标轴刻度线
                        },
                        axisLine: {
                            show: false//不显示坐标轴线
                        },
                        data: nameList
                    },
                    series: [
                        {
                            type: 'bar',
                            data: dataList,
                            barMaxWidth: 20,
                            itemStyle: {
                                barBorderRadius: [5, 5, 5, 5],
                                color: {
                                    x: 1,
                                    y: 0,
                                    x2: 0,
                                    y2: 0,
                                    colorStops: [{
                                        offset: 0, color: '#a2e0eb' // 0% 处的颜色
                                    }, {
                                        offset: 1, color: '#043fff' // 100% 处的颜色
                                    }],
                                    global: false // 缺省为 false
                                }
                            }
                        }
                    ]
                });
            }else {
                //业务异常
            }
        }, error: function () {
            alert("提交失败")
        }
    })
}

var radio_btn_laay_suc='laayCaseSucData';

/**
 * 第二行第三个表
 * @param dataObj
 */
function loadLaayChartDataToHtml(dataObj) {
    dataObj = dataObj || {};
    var dataList = dataObj[radio_btn_laay];
    var nameList = [];
    for(var key in dataList){
        nameList.push(dataList[key].name)
    }
    if(nameList.length == 0){
        nameList.push("暂无数据")
        dataList.push({name:"暂无数据",value:0})
    }
    var laay_chart = echarts.init(document.getElementById('laay_echarts'));
    laay_chart.setOption({
        title: {
            text: '案由对应送达方式',
            left: 'center',
            top:10,
            textStyle: {
                color: '#ffffff'
            }
        },
        textStyle: {
            color: '#ffffff'
        },
        tooltip: {
            trigger: 'item',
            formatter: function (params) {
                    return params.name + ":" + params.value + "(成功率:" + params.data.suc+"%)" + "(占比:" + params.data.zb+"%)";
            }
        },
        // legend:{
        //     type: 'scroll',
        //     orient: 'vertical',
        //     right: 10,
        //     top: 30,
        //     data: nameList,
        //     selected: true,
        //     textStyle: {
        //         color: '#ffffff'
        //     }
        // },
        color:['#3FB1E3','#6BE6C1','#626C91','#A0A7E6','#C4EBAD','#626C91'],
        series: [
            {
                name: '案由对应送达方式',
                type: 'pie',
                radius: '50%',
                center: ['40%', '50%'],
                data: dataList,
                emphasis: {
                    itemStyle: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    });
}


$(".radio-btn-laay").click(function () {
    radio_btn_laay = $(this).attr("name");
    $(".radio-btn-laay").addClass("bgc-white")
    $(this).removeClass("bgc-white")
    // loadLaayChartDataToHtml(LaayChart_dataObj);
})



$(".radio-btn-webcall").click(function () {
    radio_btn_webcall = $(this).attr("name");

    $(".radio-btn-webcall").addClass("bgc-white")
    $(this).removeClass("bgc-white")
    loadCusTableDataToHtml(TableSlcd_dataObj);
})
var TableSlcd_dataObj = {};
var radio_btn_webcall = "明文";
var radio_btn_slcd = "";
function loadTableSlcd() {
    var dataObj = {
        startTime: G_startTime,
        endTime:G_endTime,
        reportCusType:radio_btn_slcd,
        tsName : tsName,
        dsrDw : dsrDw,
        gdOrAhType : gdOrAjType
    }
    $.ajax({
        url: "/report/chart_slcd_table.zf",
        type: "post",
        headers:{'Content-type':'application/json'},
        dataType:"json",
        data: JSON.stringify(dataObj),
        success: function (data) {
            var code = data.code;
            if(code == '200'){
                TableSlcd_dataObj = data.data;
                loadCusTableDataToHtml(TableSlcd_dataObj);
                entry_dljDxtzNum=data.data.entryDxtzChart.dljDxtzNum;
                repair_dljDxtzNum=data.data.repairDxtzChart.dljDxtzNum;
            }else {
                //业务异常
            }
        }, error: function () {
            alert("提交失败")
        }
    })
}
function loadCusTableDataToHtml(dataObj) {
    dataObj = dataObj || {};
    for(var key in dataObj){
        if(typeof dataObj[key] === 'object'){
            for (var itemKey in dataObj[key]){
                $("#"+key+"_"+itemKey).text(dataObj[key][itemKey]);
            }
        }else {
            $("#"+key).text(dataObj[key])
        }
    }
}

/**
 * 加载 第一行 第一个图表数据
 */
function loadLjData() {
    var dataObj = {
        startTime: G_startTime,
        endTime:G_endTime,
        tsName : tsName,
        dsrDw : dsrDw,
        gdOrAhType : gdOrAjType
    }
    $.ajax({
        url: "/report/chart_case.zf",
        type: "post",
        headers:{'Content-type':'application/json'},
        dataType:"json",
        data: JSON.stringify(dataObj),
        success: function (data) {
            var code = data.code;
            if(code == '200'){
                var dataObj = data.data;
                for(var key in dataObj){
                    $("#"+key).text(dataObj[key])
                }
                load_sdfsfb_echarts(dataObj);
                load_sdfslj_echarts(dataObj);
            }else {
                //业务异常
            }
        }, error: function () {
            alert("提交失败")
        }
    })
}

/**
 * 加载第1行第三个表
 * @param dataObj
 */
function load_sdfslj_echarts(dataObj) {
    var myChart = echarts.init(document.getElementById('sdfslj_echarts'),'walden');
    option = {
        color: ['#f3adf1','#00ffff',"#ffaf00"],
        tooltip: {
            trigger: 'axis',
            formatter: '{b0}<br/>{a0}: {c0}<br />{a1}: {c1}<br />{a2}: {c2}%'
        },
        legend: {
            data: ['累计送达', '送达成功'],
            textStyle:{
                color:'#ffffff'
            }
        },
        xAxis: {
            type: 'category',
            axisLine: {
                lineStyle: {
                    color: '#fff'
                },
                axisLabel : {//坐标轴刻度标签的相关设置。
                    interval:0,
                    rotate:"45"
                }
            },
            splitLine:{
                show: false
            },
            data: ['电话拨打', '短信下发', '邮寄送达', '来院领取', '直接送达']
        },
        yAxis: [{
            type: 'value',
            name: '人数',
            splitNumber:5,
            splitLine:{
                show: false
            },
            axisLine: {
                lineStyle: {
                    color: '#fff'
                }
            }
        },
            {
                type: 'value',
                name: '触达率',
                max:100,
                splitNumber:5,
                splitLine:{
                    show: false
                },
                axisLine:{
                    lineStyle: {
                        color: '#fff'
                    }
                },
                axisLabel: {
                    show: true,
                    interval: 'auto',
                    formatter: '{value}%'
                },
                show: true
            }
        ],
        series:[]
    };

    var dataChartSuc = [{name:"电话拨打",value:0},{name:"短信下发",value:0},{name:"邮寄送达",value:0},{name:"来院领取",value:0},{name:"直接送达",value:0}];
    var dataChartSum = [{name:"电话拨打",value:0},{name:"短信下发",value:0},{name:"邮寄送达",value:0},{name:"来院领取",value:0},{name:"直接送达",value:0}];
    var dataChartSucRate = [{name:"电话拨打",value:0},{name:"短信下发",value:0},{name:"邮寄送达",value:0},{name:"来院领取",value:0},{name:"直接送达",value:0}];
    if(dataObj){
        dataChartSuc.forEach(function (item) {
            if(item.name == '短信下发'){
                item.value = dataObj.ljDxtzSucNum|| 0;
            }else if(item.name == '电话拨打'){
                item.value = dataObj.ljWebCallSucNum || 0;
            }else if(item.name == '直接送达'){
                item.value = dataObj.ljZjsdSucNum || 0;
            }else if(item.name == '来院领取'){
                item.value = dataObj.ljLylqSucNum || 0;
            }else if(item.name == '邮寄送达'){
                item.value = dataObj.ljEmsSucNum || 0;
            }
        });

        dataChartSum.forEach(function (item) {
            if(item.name == '短信下发'){
                item.value = dataObj.ljDxtzSucNum + dataObj.ljDxtzFailNum || 0;
            }else if(item.name == '电话拨打'){
                item.value = dataObj.ljWebCallSucNum + dataObj.ljWebCallFailNum || 0;
            }else if(item.name == '直接送达'){
                item.value = dataObj.ljZjsdSucNum + dataObj.ljZjsdFailNum || 0;
            }else if(item.name == '来院领取'){
                item.value = dataObj.ljLylqSucNum + dataObj.ljLylqFailNum || 0;
            }else if(item.name == '邮寄送达'){
                item.value = dataObj.ljEmsSucNum + dataObj.ljEmsFailNum || 0;
            }
        });

        dataChartSucRate.forEach(function (item) {

            function dealRate(suc, fail) {
                if(suc+fail==0) return 0;
                else {
                    return ((Math.round((suc/(suc+fail) * 10000)))/100.00).toFixed(2);
                }
            }

            if(item.name == '短信下发'){
                item.value = dealRate(dataObj.ljDxtzSucNum,dataObj.ljDxtzFailNum)||0;
            }else if(item.name == '电话拨打'){
                item.value =dealRate(dataObj.ljWebCallSucNum,dataObj.ljWebCallFailNum)||0;
            }else if(item.name == '直接送达'){
                item.value =  dealRate(dataObj.ljZjsdSucNum,dataObj.ljZjsdFailNum)||0;
            }else if(item.name == '来院领取'){
                item.value = dealRate(dataObj.ljLylqSucNum,dataObj.ljLylqFailNum)||0;
            }else if(item.name == '邮寄送达'){
                item.value = dealRate(dataObj.ljEmsSucNum,dataObj.ljEmsFailNum)||0;
            }
        });
        option.series.push({
            name:'累计送达',
            type: 'bar',
            barWidth: 10,
            data:dataChartSum
        });
        option.series.push({
            name:'送达成功',
            type: 'bar',
            barWidth: 10,
            data:dataChartSuc
        });
        option.series.push({
            name:'送达成功率',
            type: 'line',
            yAxisIndex: 1,
            data:dataChartSucRate
        });
        myChart.setOption(option);

    }
}

/**
 * 加载 第2行 第2个图表数据
 * @param dataObj
 */
function load_sdfsfb_echarts(dataObj) {
    // 基于准备好的dom，初始化echarts实例
    var sdfsfb_echarts = echarts.init(document.getElementById('sdfsfb_echarts'));

    var dataChart = [{name:"电子",value:0},{name:"电话",value:0},{name:"直接",value:0},{name:"来院",value:0},{name:"邮寄",value:0}];
    if(dataObj){
        dataChart.forEach(function (item) {
            if(item.name == '电子'){
                item.value = dataObj.ljDxtzSucNum|| 0;
            }else if(item.name == '电话'){
                item.value = dataObj.ljWebCallSucNum || 0;
            }else if(item.name == '直接'){
                item.value = dataObj.ljZjsdSucNum || 0;
            }else if(item.name == '来院'){
                item.value = dataObj.ljLylqSucNum || 0;
            }else if(item.name == '邮寄'){
                item.value = dataObj.ljEmsSucNum || 0;
            }
        })
    }

    // 指定图表的配置项和数据
    var sdfsfb_echarts_option = {
        title: {
            text: '送达方式分布',
            left: 'center',
            textStyle:{
                color:'#ffffff'
            }
        },
        color:['#3FB1E3','#6BE6C1','#626C91','#A0A7E6','#C4EBAD'],
        tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        // legend: {
        //     left: 'center',
        //     top: 'bottom',
        //     data: ['电子', '电话', '直接', '来院', '邮寄']
        // },
        series: [
            {
                name: '送达方式分布',
                type: 'pie',
                radius: '50%',
                emphasis: {
                    itemStyle: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                },
                data: dataChart
            }
        ]
    };
    sdfsfb_echarts.setOption(sdfsfb_echarts_option);
}

/**
 * 加载 第1行 第2个图表数据
 */
function loadLjMonthCaseChart() {

    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('caseTotal-suc-chart'));
    // 指定图表的配置项和数据
    var option = {
        tooltip:{
            trigger: 'axis',
            axisPointer:{
                type:"cross",
                crossStyle:{
                    color:'#999'
                }
            }
        },
        legend:{
            data: ['案件送达总量','案件送达成功率'],
            textStyle:{
                color: '#ffffff'
            }
        },
        color:['#FFAF00'],
        xAxis:[
            {
                type: 'category',
                data:['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'],
                axisLabel:{
                    show:true,
                    textStyle:{
                        color:"#ffffff",
                        fontSize: 8
                    }
                },
                axisPointer:{
                    type:"shadow"
                }
            }
        ],
        yAxis:[
            {
                type:'value',
                min:0,
                axisLabel:{
                    formatter: '{value}',
                    textStyle:{
                        color:"#ffffff"
                    }
                }
            },
            {
                type:'value',
                min:0,
                axisLabel:{
                    formatter: '{value} %',
                    textStyle:{
                        color:"#ffffff"
                    }
                }
            },
        ],

    }
    var dataObj = {
        tsName : tsName,
        dsrDw : dsrDw,
        gdOrAhType : gdOrAjType
    };
    $.ajax({
        url: "/report/chart_month_case.zf",
        type: "post",
        headers: {'Content-type': 'application/json'},
        dataType: "json",
        data: JSON.stringify(dataObj),
        success: function (data) {
            var code = data.code;
            if (code == '200') {
                var dataObj = data.data;
                option.series = [
                    {
                        name: '案件送达总量',
                        type:'bar',
                        data: dataObj.ljCaseList || []
                    },
                    {
                        name: '案件送达成功率',
                        type:'line',
                        yAxisIndex:1,
                        itemStyle:{
                            normal:{
                                color:'#FFFFFF'
                            }
                        },
                        data:dataObj.ljSucCaseRateList || []
                    }
                ]

                myChart.setOption(option);
            } else {
                //业务异常
            }
        }, error: function () {
            alert("提交失败")
        }
    });
}

function loadSdwsfbChart(){
    var sdwsfb_chart = echarts.init(document.getElementById("sdwsfb_echarts"));
    var dataObj = {
        startTime: G_startTime,
        endTime:G_endTime,
        tsName : tsName,
        dsrDw : dsrDw,
        gdOrAhType : gdOrAjType
    };
    $.ajax({
        url: "/report/getSdwsfb.aj",
        type: "post",
        headers:{'Content-type':'application/json'},
        dataType:"json",
        data: JSON.stringify(dataObj),
        success: function (data) {
            var code = data.code;
            if(code == '200'){
                var dataObj = data.data;
                var dataList = [];
                for(var key in dataObj){                //把案件周期排到最上面
                    dataList.push({
                        name : key,
                        value : dataObj[key]
                    })
                }
                sdwsfb_chart.setOption({
                    title: {
                        text: '送达文书分布',
                        left: 'center',
                        textStyle:{
                            color:'#ffffff'
                        }
                    },
                    color:['#0084ff','#ded71f','#eb6b9e','#25cd84','#ff9c00','#9e3aff'],
                    tooltip: {
                        trigger: 'item',
                        formatter: '{a} <br/>{b} : {c} ({d}%)'
                    },
                    series: [
                        {
                            name: '送达文书分布',
                            type: 'pie',
                            radius: [20, 150],
                            roseType: 'radius',
                            itemStyle: {
                                borderRadius: 5
                            },
                            label: {
                                show: false
                            },
                            emphasis: {
                                label: {
                                    show: true
                                }
                            },
                            data: dataList
                        }]
                })
            }else {
                //业务异常
            }
        }, error: function () {
            alert("提交失败")
        }
    })
}
 function getBmmc() {
     $.ajax({
         url: "/report/getBmmc.aj",
         type: 'get',
         success: function (data) {
            for (var i = 0 ; i < data.length ; i++){
                $("#select_bmmc").append("<option value=\"" +data[i] + "\">" +   data[i] + "</option>");
            }
         },error:function () {
             alert("获取部门名称失败")
         }
     })
 }

 function getSsdrlx() {
     $.ajax({
         url: "/report/getSsdrlx.aj",
         type: 'get',
         success: function (data) {
             for (var i = 0 ; i < data.length ; i++){
                 $("#select_ssdw").append("<option value=\"" +data[i] + "\">" +   data[i] + "</option>");
             }
         },error:function () {
             alert("获取当事人类型失败")
         }
     })
 }