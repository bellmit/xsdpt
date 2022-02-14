var ajxh;
var ktsj;
var ajlx;
var ah;



$(document).ready(function () {

    getCurrentFyNameBySessionFybh();
    getAjjbxx();
    getAjjbxx();
    getSdrxx();
    getWsInfos();
});

$(function () {

    $("#dsrTable").bootstrapTable({
        locale: "zh-CN",
        method: 'post',
        // onClickRow:function(row,$element){
        //     dsrbh = row.dsrbh;
        //     $('.selectedDsr').removeClass('selectedDsr');
        //     $($element).addClass('selectedDsr');
        // },
        columns: [
            {
                checkbox: true,
                field:'sfSelected',
                width: '5%'
            },
            {
                field: 'dsrbh',
                title: '编号',
                width: '8%'
            },
            {
                field: 'dsrssdw',
                title: '诉讼地位',
                width: '10%'
            },
            {
                field: 'dsrjc',
                title: '名称',
                width: '15%'
            },
            {
                field: 'sfzhm',
                title: '证件号码',
                width: '10%'
            },
            {
                field: 'dh',
                title: '手机号码',
                width: '10%'
            },
            {
                field: 'sddz',
                title: '送达地址',
                width: '30%'
            },
            {
                field: 'daisr',
                title: '相关诉讼人',
                width: '10%'
            },
            {
                title: '编辑',
                width: '8%',
                align: 'center',
                events: "operateEvents",
                formatter: operateFormatter
            }
        ],
    });
    $("#dsrTable").bootstrapTable('refresh',{
        url:"/getDsrxx.aj",
    });


    $("#csfs").change(function () {

        if($(".wslx").val()==''||$(".wslx").val()==null){
            alert("请先选择文书类型");
            $("#csfs").val('');
            return;
        }

        if($("#csfs").val()=="线上传输"){
            var target = document.getElementById("wsUpload");
            $("#xxsd").hide();
            target.style.visibility = "visible";
        }else  if($("#csfs").val()=="线下传输"){
            $("#xxsd").show();
            var target = document.getElementById("xxsd");
            target.style.visibility = "visible";
        }else {
        }
    })


});



function getAjjbxx() {
    $.ajax({
        url: "/getAjjbxx.aj",
        type:"post",
        success: function (data) {
            ajxh = data.ajxh;
            fybh = data.fybh;
            ktsj = data.ktsj;
            ajlx = data.ajlx;
            ah = data.ah;
            var html1 = "<input style='width: ";
            var html2 = "%;background-color: rgb(249,249,249);border-style: solid;border-width: 1px;border-color: rgb(160,160,160);height: 2em' disabled value='";
            var html3 = "'>";
            $("#ajlx").empty().append(html1+80+html2+data.ajlx+html3);
            $("#ah").empty().append(html1+80+html2+data.ah+html3);
            $("#laay").empty().append(html1+80+html2+data.laay+html3);
            $("#ajmc").empty().append(html1+80+html2+data.ajmc+html3);
            $("#ktsj").empty().append(html1+80+html2+data.ktsj+html3);
            if(ajlx != '执行') {
                $("#tbsj").hide();
            }
        },error:function () {
            alert("获取案件信息失败")
        }
    });
}

function getWsInfos() {
    $.ajax({
        url: "/getWsInfos.do",
        type:"post",
        success: function (data) {
            var strMap = new Map();
            for (var key in data) {
                strMap.set(key,data[key]);
            }
            innerFile = strMap;
        }
    })

}
function wcyy() {
    if(new Date(ktsj) < new Date()) {
        var myMsg = layer.msg("该案件已过开庭时间，是否继续预约送达？", {
            time: false,
            btn: ['确定', '取消']
            , yes: function (index) {
                wcyyaj();
                layer.close(myMsg)
            }
        });

    } else {
            wcyyaj();
        }
}
function wcyyaj() {
    if($("#yysdbz").val().length>100){
        alert("备注最多100字");
        return;
    }
    if(uploadFile.length==0){
        alert("预约内容不能为空,不送达文书时请选择线下送达");
        return;
    }
    $("#wcyy").attr('disabled',true);
    var dsrbhObject = $("#dsrTable").bootstrapTable('getData');
    for(var i = 0;i<dsrbhObject.length;i++){
        delete dsrbhObject[i].sfSelected;
    }
    for (var i = 0;i<uploadFile.length;i++){
        delete uploadFile[i].dsrmc;
    }
    var dsrJson = JSON.stringify(dsrbhObject);
    var wsbhsJson = JSON.stringify(uploadFile);
    var data ={};
    data["ajxh"] = ajxh;
    data["fybh"] = fybh;
    data["bmmc"]=bmmc;
    data["bgdh"]=bgdh;
    data["sjhm"]=sjhm;
    data["fgmc"]=fgmc;
    data["dsr"]=dsrJson;
    data["ktsj"]=ktsj;
    data["wsInfos"]=wsbhsJson;
    data["yysdbz"] = $("#yysdbz").val();
    $.ajax({
        url:"yysdSubmit.aj" ,
        type:'POST',
        contentType:'application/json',
        data:JSON.stringify(data),
        success: function (data) {
            alert("预约成功,请前往\n" +
                "审判系统-常用工具-送达平台-我的送达\n" +
                "查看详情");
            window.opener=null;
            window.open('','_self');
            window.close(this);
            // layer.msg("预约失败");
            // $("#wcyy").removeAttr('disabled');
        },error:function () {
            layer.msg("预约失败");
            // $("#wcyy").removeAttr('disabled');
        }
    });
}

function lookWs(e) {
    var i = $(e).attr('data-index');
    $.ajax({
        url: "/getFydz.aj",
        type:"post",
        success: function (data) {
            var url = data;
            url = 'http://'+url+'/bgscFile.do?ajxh='+ajxh+'&dmbh='+uploadFile[i].wslybh+'&stampId='+uploadFile[i].stampId+'&fileType=2';
            window.open(url);
        }
    })
}

