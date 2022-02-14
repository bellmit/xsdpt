
$(document).ready(function () {

    getCurrentFyNameBySessionFybh();

    getCaAjjbxx();
    // getCaDsrxx();
    getSdrxx();
    getCaWsInfos();
});

function getMsg(){
    var url=location.search;
    var theRequest=new Object();
    if(url.indexOf("?")!=-1){
        var str=url.substr(1);
        strs=str.substr("&");
        for(var i=0;i<str.length;i++){
            theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);
        }
        return theRequest;
    }
}

function getCaAjjbxx() {
    var msg=getMsg();
    $.ajax({
        url: "/getCaAjjbxx.aj",
        type:"post",
        data:{
           "ajxhs":msg['ajxh'],
            "fybh":msg['fybh']
        },
        success: function (data) {
            fybh = data[0].fybh;
           showTable('caajTbody','CaAjjbxxTbodyHtml',data);
        },error:function () {
            alert("获取案件信息失败")
        }
    });
}
$(function () {

    $("#dsrTable").bootstrapTable({
        locale: "zh-CN",
        method: 'post',
        columns: [
            {
                checkbox: true,
                field:'sfSelected',
                width: '8%'
            },
            {
                field: 'dsrjc',
                title: '名称',
                width: '30%'
            },
            {
                field: 'szah',
                title: '所在案号',
                width: '30%'
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
                width: '20%'
            },
            {
                field: 'daisr',
                title: '相关诉讼人',
                width: '10%'
            },
            {
                title: '编辑',
                width: '5%',
                align: 'center',
                events: "operateEvents",
                formatter: operateFormatter
            }
        ],
    });
    $("#dsrTable").bootstrapTable('refresh',{
        url:"/getCaDsrxx.aj",
    });

})

function getCaWsInfos() {
    $.ajax({
        url: "/getCaWsInfos.do",
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

function lookWs(e) {
    var i = $(e).attr('data-index');
    $.ajax({
        url: "/getFydz.aj",
        type:"post",
        success: function (data) {
            var url = data;
            url = 'http://'+url+'/bgscFile.do?ajxh='+uploadFile[i].ajxh+'&dmbh='+uploadFile[i].wslybh+'&stampId='+uploadFile[i].stampId+'&fileType=2';
            window.open(url);
        }
    })
}

function wcyy() {
    if(uploadFile.length==0){
        alert("预约内容不能为空,不送达文书时请选择线下送达");
        return;
    }
    if($("#yysdbz").val().length>100){
        alert("备注最多100字");
        return;
    }

    $("#wcyy").attr('disabled',true);
    layer.msg("正在为您智能拆分任务,请耐心等待");
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
    data["fybh"] = fybh;
    data["bmmc"]=bmmc;
    data["bgdh"]=bgdh;
    data["sjhm"]=sjhm;
    data["fgmc"]=fgmc;
    data["dsr"]=dsrJson;
    data["wsInfos"]=wsbhsJson;






    data["yysdbz"] = $("#yysdbz").val();
    $.ajax({
        url:"yysdCaSubmit.aj" ,
        type:'POST',
        contentType:'application/json',
        data:JSON.stringify(data),
        success: function (data) {
            var message= "已为您自动拆分为:"+data+"号工单,请前往我的主页进行查看";
            alert(message);
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
