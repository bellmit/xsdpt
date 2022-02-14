$(document).ready(function () {
    var fybh=$.cookie('fybh')
    var lx=$("button.btn-success").val()
    var mode=$("button.btn-warning").val()
    getStatistics(fybh,lx,mode)
    getstatisticsByAdcode(fybh)
});

function changelx(e){
    // 更改前端的显示情况
    $(".btn-success").addClass("btn-secondary")
    $(".btn-success").removeClass("btn-success")
    $(e).removeClass("btn-secondary")
    $(e).addClass("btn-success")


    var fybh=$.cookie('fybh')
    var lx=$("button.btn-success").val()
    var mode=$("button.btn-warning").val()
    getStatistics(fybh,lx,mode)
}
function getStatistics(fybh,lx,mode){
    var params={
        fybh:fybh,
        lx:lx,
        mode:mode
    }
    $.ajax({
        url:"/getstatistics.aj",
        type: 'post',
        dataType:'json',
        contentType:'application/json',
        data:JSON.stringify(params),
        success:function (data) {
            $("#yygdsl").text(data.yygdsl)
            $("#sdcgsl").text(data.sdcgsl)
            $("#gdsdcgl").text(data.gdsdcgl+'%')
            $("#ljsasl").text(data.ljsasl)
            $("#ajsdsl").text(data.ajsdsl)
            $("#ajsdcgl").text(data.ajsdcgl+'%')
            $("#ssdrsl").text(data.ssdrsl)
            $("#wcsdrs").text(data.wcsdrs)
            $("#ssdrcgl").text(data.ssdrcgl+'%')
            $("#sdzgdsl").text(data.sdzgdsl)
            $("#sdzajsl").text(data.sdzajsl)
            $("#sdzrs").text(data.sdzrs)

            $("#ajpjsdzq").text(data.ajpjsdzq+'天')
            $("#yjpjsdzq").text(data.yjpjsdzq+'天')
            $("#dhpjsdzq").text(data.dhdzpjsdzq+'天')
            $("#lypjsdzq").text(data.lypjsdzq+'天')
            $("#zjpjsdzq").text(data.zjpjsdzq+'天')

            $("#dxxfzs").text(data.dxxfzs)
            $("#dxljfwl").text(data.dxljfwl+'%')
            $("#dhwhbacs").text(data.dhwhbacs)
            $("#dhwhjts").text(data.dhwhjtcs)
            $("#yjsdjs").text(data.yjjs)
            $("#wszl").text(data.wszl)
            $("#xfsl").text(data.xfzs)
            $("#xfcgs").text(data.xfcgs)
        }
    })
}

function getstatisticsByAdcode(fybh){
    const { Choropleth } = L7Plot;
    var params={
        fybh:fybh,
    }

    $.ajax({
        url: "/getstatisticsByAdcode.aj",
        type: 'post',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(params),
        success:function (data1) {
            // console.log(data1)
            for(var i=0;i<data1.length;i++){
                // console.log("测试")
                if(fybh===data1[i].fybh){
                    // console.log("进入")
                    $("#ljgdsl").text(data1[i].ljgdsl)
                    $("#ljgdwcsl").text(data1[i].ljgdwcsl)
                    $("#ljsasltitle").text(data1[i].ljsasl)
                    $("#jrgdsl").text(data1[i].jrgdsl)
                    $("#jrgdwcsl").text(data1[i].jrgdwcsl)
                    $("#jrsasl").text(data1[i].jrsasl)
                }
            }

            $.getJSON('/antd/area-list.json',(list) => {
                const data = list
                    .filter(({ level, parent }) => level === 'district' && parent === 120100)
                    .map((item) =>Object.assign({}, item, { value: getNum(item.adcode,data1) }))

                // console.log(data)

                new Choropleth('container', {
                    geoArea:{
                        url:'/antd',
                        type:'topojson'
                    },
                    map: {
                        type: 'mapbox',
                        style: 'blank',
                        center: [120.19382669582967, 30.258134],
                        zoom: 3,
                        pitch: 0,
                    },
                    source: {
                        data: data,
                        joinBy: {
                            sourceField: 'adcode',
                            geoField: 'adcode',
                        },
                    },
                    viewLevel: {
                        level: 'city',
                        adcode: 120100,
                    },
                    autoFit: true,
                    color: {
                        field: 'value',
                        value: ['#B8E1FF', '#7DAAFF', '#3D76DD', '#0047A5', '#001D70'],
                        scale: { type: 'quantize' },
                    },
                    style: {
                        opacity: 1,
                        stroke: '#ccc',
                        lineWidth: 0.6,
                        lineOpacity: 1,
                    },
                    label: {
                        visible: true,
                        field: 'name',
                        style: {
                            fill: '#000',
                            opacity: 0.8,
                            fontSize: 10,
                            stroke: '#fff',
                            strokeWidth: 1.5,
                            textAllowOverlap: false,
                            padding: [0, 0],
                        },
                    },
                    state: {
                        active: { stroke: 'black', lineWidth: 1 },
                    },
                    tooltip: {
                        items: [
                            {
                                field:'name',
                                alias:'区名'
                            },
                            {
                                field: 'value',
                                alias: '取值'
                            }
                        ],
                    },
                    zoom: {
                        position: 'bottomright',
                    },
                    legend: {
                        position: 'bottomleft',
                    },
                })
            })
        }
    })

}

function getNum(acode,data) {
    // console.log(data)
    for(var i=0;i<data.length;i++){
        // console.log(acode)
        if(acode==data[i].adcode){
            // console.log(data[i].ljgdsl)
            return data[i].ljgdsl
        }
    }
}