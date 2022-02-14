$(document).ready(function () {
    var lx=$("button.btn-primary.lx").val()
    var fybh=$.cookie('fybh')
    if(fybh==='24'){
        $("#icon").html("<img src=\"../img/statistics/tlfy-icon.png\" style=\"margin-left:4.5%;margin-top:2%;height: 60%;width: 58%;\" />")
    }else{
        $("#icon").html("<img src=\"../img/statistics/"+fybh+"-icon.png\" style=\"margin-left:4.5%;margin-top:2.5%;height: 60%;width: 58%;\" />")
    }
    getStatistics(fybh,lx)
    $("#select_time1").text(getAllTime())
    $("#select_time2").text(convert2Str())
    layui.use('laydate', function(){
        var laydate = layui.laydate;
        //执行一个laydate实例
        laydate.render({
            elem: '#select_time1' //指定元素
        });

        var laydate2 = layui.laydate;
        //执行一个laydate实例
        laydate2.render({
            elem: '#select_time2' //指定元素
        });
    });
});

function getAllTime(){
    return '2021-11-01'
}

function get7DaysAge() {
    var curDate=new Date()
    return convert2Str(new Date(curDate.getTime()+1000*60*60*24*7))
}

function get30DaysAge() {
    var curDate=new Date()
    return convert2Str(new Date(curDate.getTime()+1000*60*60*24*30))
}

function convert2Str(date = new Date()) {
    return `${date.getFullYear()}-${date.getMonth() + 1}-${date.getDate()}`
}

function changelx(e){
    // 更改前端的显示情况
    $(".btn-primary.lx").removeClass("btn-primary")
    $(e).addClass("btn-primary")

    var lx=$("button.btn-primary.lx").val()
    var fybh=$.cookie('fybh')
    $("#container").html("")
    $("#container1").html("")
    getStatistics(fybh,lx)
}

function getStatistics(fybh,lx) {
    var params = {
        fybh: fybh,
        lx: lx
    }
    $.ajax({
        url: "/getStatisticsByFg.aj",
        type: 'post',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(params),
        success: function (data) {
            console.log(data)
            $("#wcsdsl").text(data.data.total.wcsdsl)
            $("#sdcgsl").text(data.data.total.sdcgsl)
            $("#wcsdrs").text(data.data.total.wcsdrs)
            $("#sdcgrs").text(data.data.total.sdcgrs)
            $("#dzsdcgl").text(data.data.total.dzsdcgl)
            $("#tsgdds").text(data.data.total.tsgdds)
            $("#wcgdds").text(data.data.total.wcgdds)
            $("#zjsdcs").text(data.data.total.zjsdcs)
            $("#wczjsdcs").text(data.data.total.wczjsdcs)

            var html = ""
            for (var i = 0; i < data.data.details.length; i++) {
                if (data.data.details[i].bmmc != null) {
                    html += "<tr align=\"center\">"
                    html += "<td style=\"height:2em;background: #124cb8;border-right: 1px solid #00ffff;font-size: 16px\">" + data.data.details[i].bmmc + "</td>"
                    html += "<td>" + data.data.details[i].wcsdsl + "件</td>"
                    html += "<td>" + data.data.details[i].sdcgsl + "件</td>"
                    html += "<td>" + data.data.details[i].wcsdrs + "人</td>"
                    html += "<td>" + data.data.details[i].sdcgrs + "人</td>"
                    html += "<td>" + data.data.details[i].dzsdcgl + "%</td>"
                    html += "</tr>"
                }
            }
            $("#statisticsTable").html(html)

            BarLineChart(data.data.details)
            RingFigure(data.data.details)
        }
    })
}

function changeTime(){
    $("#container").html("")
    $("#container1").html("")
    getStatisticsByFg()
}

function getStatisticsByFg(){

    var params = {
        fybh: $.cookie('fybh'),
        lx: 5,
        start:$("#select_time1").text(),
        end:$("#select_time2").text()
    }
    $.ajax({
        url: "/getStatisticsByFg.aj",
        type: 'post',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(params),
        success: function (data) {
            console.log(data)
            $("#wcsdsl").text(data.data.total.wcsdsl)
            $("#sdcgsl").text(data.data.total.sdcgsl)
            $("#wcsdrs").text(data.data.total.wcsdrs)
            $("#sdcgrs").text(data.data.total.sdcgrs)
            $("#dzsdcgl").text(data.data.total.dzsdcgl)
            $("#tsgdds").text(data.data.total.tsgdds)
            $("#wcgdds").text(data.data.total.wcgdds)
            $("#zjsdcs").text(data.data.total.zjsdcs)
            $("#wczjsdcs").text(data.data.total.wczjsdcs)

            var html = ""
            for (var i = 0; i < data.data.details.length; i++) {
                if (data.data.details[i].bmmc != null) {
                    html += "<tr align=\"center\">"
                    html += "<td style=\"height:2em;background: #124cb8;border-right: 1px solid #00ffff;font-size: 16px\">" + data.data.details[i].bmmc + "</td>"
                    html += "<td>" + data.data.details[i].wcsdsl + "件</td>"
                    html += "<td>" + data.data.details[i].sdcgsl + "件</td>"
                    html += "<td>" + data.data.details[i].wcsdrs + "人</td>"
                    html += "<td>" + data.data.details[i].sdcgrs + "人</td>"
                    html += "<td>" + data.data.details[i].dzsdcgl + "%</td>"
                    html += "</tr>"
                }
            }
            $("#statisticsTable").html(html)


            BarLineChart(data.data.details)
            RingFigure(data.data.details)
        }
    })
}

function BarLineChart(data) {
    const {DualAxes} = G2Plot;
    // const data = [
    //     { time: '2019-03', value: 350, count: 800 },
    //     { time: '2019-04', value: 900, count: 600 },
    //     { time: '2019-05', value: 300, count: 400 },
    //     { time: '2019-06', value: 450, count: 380 },
    //     { time: '2019-07', value: 470, count: 220 },
    // ];

    const dualAxes = new DualAxes('container', {
        height: 250,
        data: [data, data],
        xField: 'bmmc',
        yField: ['cgnum', 'fsnum'],
        meta:{
          cgnum: {
              alias:'成功数量'
          },
            fsnum: {
                alias:'发送数量'
            }
        },
        yAxis: {
            // 格式化左坐标轴
            cgnum: {
                min: 0,
                label: {
                    formatter: (val) => `${val}条`,
                    style: {fill: 'white'}
                },
                alias:'成功送达'
            },
            // 隐藏右坐标轴
            fsnum: false
        },
        xAxis: {
            label: {
                autoHide:false,
                autoEllipsis:true,
                style: {
                    fill: 'white',
                    textAlign: 'center'
                }
            },
        },
        geometryOptions: [
            {
                geometry: 'column',
                color: '#2ad52d',
                columnWidthRatio: 0.25,
                label: {
                    position: 'middle',
                },
            },
            {
                geometry: 'line',
                smooth: true,
                color: '#00ffff',
            },
        ],
        // 更改柱线交互，默认为 [{type: 'active-region'}]
        interactions: [
            {
                type: 'element-highlight',
            },
            {
                type: 'active-region',
            },
        ],
        annotations: {
            type: ''
        },
        legend:false
    });

    dualAxes.render();
}

function RingFigure(data1){
    const { Pie, measureTextWidth } = G2Plot;
    function renderStatistic(containerWidth, text, style) {
        const { width: textWidth, height: textHeight } = measureTextWidth(text, style);
        const R = containerWidth / 2;
        // r^2 = (w / 2)^2 + (h - offsetY)^2
        let scale = 1;
        if (containerWidth < textWidth) {
            scale = Math.min(Math.sqrt(Math.abs(Math.pow(R, 2) / (Math.pow(textWidth / 2, 2) + Math.pow(textHeight, 2)))), 1);
        }
        const textStyleStr = `width:${containerWidth}px;`;
        return `<div style="${textStyleStr};font-size:${scale}em;line-height:${scale < 1 ? 1 : 'inherit'};">${text}</div>`;
    }

    // const data1 = [
    //     { type: '分类一', value: 27 },
    //     { type: '分类二', value: 25 },
    //     { type: '分类三', value: 18 },
    //     { type: '分类四', value: 15 },
    //     { type: '分类五', value: 10 },
    //     { type: '其他', value: 5 },
    // ];

    const piePlot = new Pie('container1', {
        height: 350,
        width:300,
        appendPadding: 10,
        data: data1,
        angleField: 'wcsdsl',
        colorField: 'bmmc',
        radius: 1,
        innerRadius: 0.64,
        meta: {
            value: {
                formatter: (v) => `${v} ¥`,
            },
            bmmc:{
                alias:'部门'
            }
        },
        label: {
            type: 'inner',
            offset: '-50%',
            style: {
                textAlign: 'center',
                fill:'white'
            },
            autoRotate: false,
            content: '{value}',
        },
        statistic: {
            title: {
                offsetY: -4,
                style: {
                    fontSize: '18px',
                    color:'white'
                },
                customHtml: (container, view, datum) => {
                    const { width, height } = container.getBoundingClientRect();
                    const d = Math.sqrt(Math.pow(width / 2, 2) + Math.pow(height / 2, 2));
                    const text = datum ? datum.bmmc : '总计';
                    return renderStatistic(d, text, { fontSize: 28 });
                },
            },
            content: {
                offsetY: 4,
                style: {
                    fontSize: '32px',
                    color:'white'
                },
                customHtml: (container, view, datum, data) => {
                    const { width } = container.getBoundingClientRect();

                    const text = datum ? `${datum.wcsdsl}件` : `${data.reduce((r, d) => r + d.wcsdsl, 0)}件`;
                    return renderStatistic(width, text, { fontSize: 32 });
                },
            },
        },
        // 添加 中心统计文本 交互
        interactions: [{ type: 'element-selected' }, { type: 'element-active' }, { type: 'pie-statistic-active' }],
        legend:{
            title:{
                style:{
                    fill:'white'
                }
            },
            itemName:{
                style:{
                    fill:'white'
                }
            }
        }
    });

    piePlot.render();
}