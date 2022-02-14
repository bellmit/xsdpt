var sjfypmSj = '周';
var sjfypmLx = '委托数';
$(document).ready(function () {
    initTitle();//标题数据
    initSssdsjTable('日');//实时送达数据
    initSlrcdTable();//失联人触达数据
    initSdfsfbTable();//送达方式分布
    initSjfypmTable();//三级法院排名
});

function initTitle() {
    $.ajax({
        url: "/getTitle.aj",
        type: 'post',
        success: function (data) {
            $("#ljsdajs").empty().append(data.ljsdajs);
            $("#ljsdrc").empty().append(data.ljsdrc);
            $("#ljwtrc").empty().append(data.ljwtrc);
            $("#ljsdcs").empty().append(data.ljsdcs);
            $("#sdcgl").empty().append(data.sdcgl);
            var myDate = new Date();
            $("#nowTime").empty().append( myDate.toLocaleString('chinese', { hour12: false }) );
        }
    })
}
function initSssdsjTable(lx) {
    changeColor(lx);
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('sssdsj_echarts'),'walden');

    // 指定图表的配置项和数据
    var option = {
        tooltip: {
            trigger: 'axis',
            formatter: '{b0}<br/>{a0}: {c0}<br />{a1}: {c1}<br />{a2}: {c2}'
        },
        textStyle:{
            color:'#ffffff'
        },


        color: ['#5ae220','#ffaf00', '#f19ec2','#ffffff'],
        xAxis: {
            data: [],
            splitLine:{
                show: false
            },
            triggerEvent:true
        },
        legend: {
            data:['预约送达任务数', '送达中任务数', '完成送达任务数','送达成功率'],
            textStyle:{
                color:'#ffffff'
            },
           left:'10%'
        },
        grid:{
            left:'5%'
        },
        yAxis: [{
            name:'单位/件',
            splitNumber:5,
            splitLine:{
                show: false
            }
        }],
        series: []
    };
    $.ajax({
        url: "/getSssdsj.aj",
        type: 'post',
        data:{
          lx:lx
        },
        success: function (data) {
          option.xAxis.data=data.fyjc;
          var yysdrws = {
              name:"预约送达任务数",
              type:"bar",
              data:data.yysdrws
          };
          option.series.push(yysdrws);
            var sdzrws = {
                name:"送达中任务数",
                type:"bar",
                data:data.sdzrws
            };
            option.series.push(sdzrws);
            var wcsdrws = {
                name:"完成送达任务数",
                type:"bar",
                data:data.wcsdrws
            };
            option.series.push(wcsdrws);
            // var sdcgl = {
            //     name:"送达成功率",
            //     type:"line",
            //     yAxisIndex: 1,
            //     data:data.sdcgl
            // };
            // option.series.push(sdcgl);
            myChart.setOption(option);
            myChart.on("click", function (param) {
                location.href="/jumpToReportData?fyjjc="+param.value;
            })
        },error:function () {
            alert("获取实时送达数据失败")
        }
    });
}

function changeColor(lx) {
    var time = ['日','周','月','年'];
    var id = ['#sssdsjDay','#sssdsjWeek','#sssdsjMonth','#sssdsjYear'];
    for (var i = 0 ;i<time.length;i++){
        if(lx==time[i]){
            $(id[i]).removeClass("white").addClass("yellow");
        }else {
            $(id[i]).removeClass("yellow").addClass("white");
        }
    }
}
// function initSdfsfbTable() {
//     // 基于准备好的dom，初始化echarts实例
//     var myChart = echarts.init(document.getElementById('sdfsfb_echarts'),'walden');
//
//     // 指定图表的配置项和数据
//     var option = {
//         title: {
//             text: '送达方式分布',
//             left: 'center',
//             top:10,
//             textStyle: {
//                 color: '#ffffff'
//             }
//         },
//         // color: ['#0059c9','#d9325d', '#c1cf37','#78e0ef','#59e220'],
//         textStyle: {
//             color: '#ffffff'
//         },
//         tooltip: {
//             trigger: 'item',
//             formatter: '{a} <br/>{b} : {c} ({d}%)'
//         }
//     };
//     $.ajax({
//         url: "/getSdfsfb.aj",
//         type: 'post',
//         success: function (data) {
//             option.legend = {
//                 type: 'scroll',
//                 orient: 'vertical',
//                 right: 100,
//                 top: 30,
//                 data: data.sdfs,
//                 selected: true,
//                 textStyle: {
//                     color: '#ffffff'
//                 }
//             };
//             option.series = [
//                 {
//                     name: '送达方式分布',
//                     type: 'pie',
//                     radius: '55%',
//                     center: ['40%', '50%'],
//                     data: data.object,
//                     emphasis: {
//                         itemStyle: {
//                             shadowBlur: 10,
//                             shadowOffsetX: 0,
//                             shadowColor: 'rgba(0, 0, 0, 0.5)'
//                         }
//                     }
//                 }
//             ];
//             myChart.setOption(option);
//         },error:function () {
//             alert("获取送达方式分布失败")
//         }
//     });
// }
function initSdfsfbTable() {
    var time = {
        startTime:"",
        endTime:""
    };
    $.ajax({
        url: "/getSdfsfb.aj",
        type: "post",
        headers:{'Content-type':'application/json'},
        dataType:"json",
        data: JSON.stringify(time),
        success: function (data) {
            var code = data.code;
            if(code == '200'){
                var dataObj = data.data;
                load_sdfsfb_echarts(dataObj);
            }else {
                //业务异常
            }
        }, error: function () {
            alert("提交失败")
        }
    })
}
function load_sdfsfb_echarts(dataObj) {
    // 基于准备好的dom，初始化echarts实例
    var sdfsfb_echarts = echarts.init(document.getElementById('sdfsfb_echarts'),'walden');

    var dataChart = [{name:"电子送达",value:0},{name:"电话送达",value:0},{name:"邮寄送达",value:0},{name:"来院领取",value:0},{name:"直接送达",value:0}];
    if(dataObj){
        dataChart.forEach(function (item) {
            if(item.name == '电子送达'){
                item.value = dataObj.ljDxtzSucNum|| 0;
            }else if(item.name == '电话送达'){
                item.value = dataObj.ljWebCallSucNum || 0;
            }else if(item.name == '直接送达'){
                item.value = dataObj.ljZjsdSucNum || 0;
            }else if(item.name == '来院领取'){
                item.value = dataObj.ljLylqSucNum || 0;
            }else if(item.name == '邮寄送达'){
                item.value = dataObj.ljEmsSucNum || 0;
            }
        })
    }
    var option = {
        title: {
        text: '送达方式分布',
            left: 'center',
            top:10,
            textStyle: {
            color: '#ffffff'
        }
    },
    // color: ['#0059c9','#d9325d', '#c1cf37','#78e0ef','#59e220'],
    textStyle: {
        color: '#ffffff'
    },
    tooltip: {
        trigger: 'item',
            formatter: '{a} <br/>{b} : {c} ({d}%)'
    },
    legend : {
            type: 'scroll',
            orient: 'vertical',
            right: 100,
            top: 30,
            data: ['电子送达','电话送达','邮寄送达','来院领取','直接送达'],
            selected: true,
            textStyle: {
                color: '#ffffff'
            }
        },
    series : [
            {
                name: '送达方式分布',
                type: 'pie',
                radius: '55%',
                center: ['40%', '50%'],
                data: dataChart,
                emphasis: {
                    itemStyle: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    }
    sdfsfb_echarts.setOption(option);
}
function initSjfypmTable() {
    changeSjfypmColor();
    $.ajax({
        url: "/getSjfypm.aj",
        type: 'post',
        data:{
            time:sjfypmSj,
            type:sjfypmLx
        },
        success: function (data) {
            var sjfypm = data.sjfypm;
            if (sjfypmLx=='成功率'){
                for (var i=0;i<data.sjfypm.length;i++){
                    sjfypm[i].value=toPercent(sjfypm[i].value);
                }
            }
            var  showData = {
                list: data.sjfypm,
                type:sjfypmLx
            };
            var html = template("sjfypm_table_tbody_html",showData);;
            $("#sjfypm_table").empty().append(html);
        },error:function () {
            alert("获取三级法院排名失败")
        }
    });
}

function changeSjfypmColor() {
    var time = ['周','月','年'];
    var id = ['#sjfypmWeek','#sjfypmMonth','#sjfypmYear'];
    for (var i = 0 ;i<time.length;i++){
        if(sjfypmSj==time[i]){
            $(id[i]).removeClass("white").addClass("yellow");
        }else {
            $(id[i]).removeClass("yellow").addClass("white");
        }
    }
    var lx = ['委托数','送达数','成功率'];
    var lxid = ['#sjfypmWts','#sjfypmSds','#sjfypmCgl'];
    for (var i = 0 ;i<lx.length;i++){
        if(sjfypmLx==lx[i]){
            $(lxid[i]).removeClass("white").addClass("yellow");
        }else {
            $(lxid[i]).removeClass("yellow").addClass("white");
        }
    }
}

function initSlrcdTable() {
    var myChart = echarts.init(document.getElementById('slrcd_echarts'),'walden');
    option = {
        color: ['#ffaf00','#5ae220',"#ffffff"],
        tooltip: {
            trigger: 'axis',
            formatter: '{b0}<br/>{a0}: {c0}<br />{a1}: {c1}<br />{a2}: {c2}%'
        },
        legend: {
            data: ['失联人数', '触达人数'],
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
            data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']
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

    $.ajax({
        url: "/getSlrcd.aj",
        type: 'post',
        success: function (data) {
            $("#slrsSum").empty().append(data.slrsSum);
            $("#cdrsSum").empty().append(data.cdrsSum);
            $("#cdlSum").empty().append(data.cdlSum);
           option.series.push({
               name:'失联人数',
               type: 'bar',
               barWidth: 10,
               data:data.slrs
           });
            option.series.push({
                name:'触达人数',
                type: 'bar',
                barWidth: 10,
                data:data.cdrs
            });
            option.series.push({
                name:'触达率',
                type: 'line',
                yAxisIndex: 1,
                data:data.cdl
            });
            myChart.setOption(option);
        },error:function () {
            alert("获取失联人触达数据失败")
        }
    });
}

function changeSjfypmLx(lx) {
    sjfypmLx = lx;
    initSjfypmTable();
}

function changeSjfypmTime(sj) {
    sjfypmSj = sj;
    initSjfypmTable();
}

function toPercent(point){
    if (point==0) {
        return 0;
    }
    var str=Number(point*100).toFixed(1);
    str+="%";
    return str;
}
