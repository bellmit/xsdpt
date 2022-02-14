var xtyh = {
    fybh: "",
    ajxh: "",
    yhm: ""
};
var AjCount ={
    all:'...',
    wsd:'...',
    sdz:'...',
    ysdwqr:'...',
    ysdyqr:'...',
    ych:'...'
}
var list;
var count =0;
$(document).ready(function () {
    xtyh.fybh = $.cookie('fybh');
    xtyh.yhm = $.cookie('yhm');
    if(xtyh.fybh != 60){
        document.getElementById("drfkb").style.display="none";
    }
    getCurrentFyName(xtyh.fybh);
    getTargetJudgeAjInfos(2);
    getTargetJudgeAjInfosCount();

});
//下拉框触发
function change() {
    var sdjg = $("#select_sdjg").val();
    getTargetJudgeAjInfos(sdjg);
}


//获取指定送达结果的案件
function getTargetJudgeAjInfos(sdjg) {
    $.ajax({
        url: "/getYysdByCbr.do",
        type:"post",
        data:{
            sdjg:sdjg
        },
        success: function (data) {
            list = data;
            $("#ajjbxx_table").dataTable().fnDestroy();
            show_table(data);
            $('#ajjbxx_table').DataTable({
                bLengthChange: false, //去掉每页显示多少条数据方法
                "info": false,   //去掉底部文字
                "scrollY": "500px", // 内部滚动条
                "scrollCollapse": "true", // 内部滚动条
                destory: true,
                "language" : {
                    "emptyTable":"暂无数据",
                    "sSearch" : "搜索:",
                    "oPaginate" : {
                        "sPrevious" : "上页",
                        "sNext" : "下页"
                    }
                }
            });
            $("#ajCount").empty().append("全部:"+AjCount.all+"&nbsp;&nbsp;&nbsp;未送达:"+AjCount.wsd+"&nbsp;&nbsp;&nbsp;送达中:"+AjCount.sdz+"&nbsp;&nbsp;&nbsp;已送达未确认:"+AjCount.ysdwqr+"&nbsp;&nbsp;&nbsp;已送达已确认:"+AjCount.ysdyqr+"&nbsp;&nbsp;&nbsp;已撤回:"+AjCount.ych);
        },error:function () {
            alert("获取案件信息失败")
        }
    });
}

function show_table(data) {
    var test_data = {
        title: '基本例子',
        isAdmin: true,
        list: data,
    };
    var html = template('test', test_data);
    $("#ajjbxx_tbody").empty().append(html);
}

function  getTargetJudgeAjInfosCount(){
        $.ajax({
            url: "/getYysdByCbrCount.do",
            type:"post",
            success: function (data) {
                AjCount.all=data[0];
                AjCount.wsd=data[1];
                AjCount.sdz=data[2];
                AjCount.ysdwqr=data[3];
                AjCount.ysdyqr=data[4];
                AjCount.ych=data[5];
                $("#ajCount").empty().append("全部:"+AjCount.all+"&nbsp;&nbsp;&nbsp;未送达:"+AjCount.wsd+"&nbsp;&nbsp;&nbsp;送达中:"+AjCount.sdz+"&nbsp;&nbsp;&nbsp;已送达未确认:"+AjCount.ysdwqr+"&nbsp;&nbsp;&nbsp;已送达已确认:"+AjCount.ysdyqr+"&nbsp;&nbsp;&nbsp;已撤回:"+AjCount.ych);
            },error:function () {
              alert("获取案件信息失败")
            }
        });

    }

function returnMain() {
    location.href = "jzsdpt.aj?fybh="+xtyh.fybh +"&yhm="+xtyh.yhm;
}

function info(e){
    var index = $(e).attr('data-index');
    var obj = list[index];
    window.open("/sdLogin.aj?fybh="+xtyh.fybh+"&ajxh="+obj.ajxh+"&yhm="+xtyh.yhm);
}

function cd(){
    var index = $("input[name='index']:checked").val();
    var obj = list[index];
    if(obj.sdjg=="送达成功"){
        layer.msg("该工单已送达成功,无法催单");
        return;
    }
    if(obj.sdjg=='已撤回'){
        layer.msg("该工单已撤回,无法催单");
        return;
    }
    var msg ="【天津法院】您好!请尽快登录系统处理任务"+list[index].yysdbh +
        "号工单(案号:"+list[index].ah+"),如果有问题请联系承办法官。";
    $.ajax({
        url: "/getRyxx.aj",
        type:"post",
        data:{
          yysdbh:list[index].yysdbh
        },
        success: function (data) {
            if(data.message == "该工单尚未分配"){
                alert(data.message);
                return;
            }
            var dataJson = {
                sendtype: 1,
                yysdbh:list[index].yysdbh,
                sendphone:data.object,
                msgcontent:msg
            }
            $.ajax({
                url: "/dxtz/send_plaintext",
                type: 'post',
                headers:{'Content-type':'application/json'},
                dataType:"json",
                data:JSON.stringify(dataJson),
                success: function (data) {
                    //重新加载数据
                    if(data.message=="success"){
                        alert("催单成功");
                    }else {
                        alert("催单失败");
                    }
                },error:function () {
                    alert("回执上传失败")
                }
            });

        },error:function () {
            alert("获取案件信息失败")
        }
    });
}



function ajpsxq() {
    var index = $("input[name='index']:checked").val();
    var yysdbh = list[index].yysdbh;
    $.cookie('yysdbh',yysdbh);
    window.open("fgwbrywdsd");
}

function gdpsxq() {
    var index = $("input[name='index']:checked").val();
    var obj = list[index];
    loadGdxqxx(obj.yysdbh);
}

function formatDate(e) {
    var date = new Date(e);
    Y = date.getFullYear() + '-';
    M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
    D = date.getDate() + ' ';
    h = date.getHours() + ':';
    m = date.getMinutes() + ':';
    s = date.getSeconds();
    var dateFormat = Y + M + D + h + m + s;
    return dateFormat;

}


function fkb() {
    var index = $("input[name='index']:checked").val();
    var obj = list[index];
    if(obj.sdjg==null||obj.sdjg==""||obj.sdjg=="已撤回"||obj.sdjg.isUndefined){
        layer.msg("该工单还未送达,暂无反馈表");
        return;
    }
    window.open("/look_fk?yysdbh="+obj.yysdbh);
}

function drfkb() {
    var index = $("input[name='index']:checked").val();
    if(index==null)
        alert("请先选择工单");
    var obj = list[index];
    var dataParam = {
        yysdbh : obj.yysdbh
    };
    $.ajax({
        url: "/sdfk/find_ssdr_sdjg.aj",
        type: 'post',
        data: dataParam,
        success: function (data) {
               if(data.object.length==0){
                   alert("暂无当事人已送达");
               }else {
                   showSsdr(data.object);
               }
        },error:function () {
            alert("操作失败")
        }
    });
}

function showSsdr(ssdrList) {
    var test_data = {
        title: '基本例子',
        isAdmin: true,
        list: ssdrList
    };
   var html = template('sdfk_ssdr_confirm',test_data);
    layer.open({
        type: 1,
        title: false,
        closeBtn: 1,
        shadeClose: true,
        area: ['400px', '300px'],
        content: html
    });
}

function download(e) {
    var index = $(e).attr('data-index');
    var ws = xqxx.ws[index];
    var url = 'downloadWs.do?yysdbh=' + ws.yysdbh + '&bh=' + ws.bh;
    window.open(url)
}


/**
 * 提交修复
 */
function submitRepair(e) {
    var yysdbh = $("input[name='index']:checked").val();
    var ssdrbh = $(e).attr('data-hint');
    if(yysdbh){
        var dataParam = {
            pubYysdSsdrKeyList: [{
                yysdbh: yysdbh,
                ssdrbh: ssdrbh
            }],
            yhm: $.cookie('yhm')
        }
        $.ajax({
            url: "/hzzf/submit_repair.zf",
            type: 'post',
            headers:{'Content-type':'application/json'},
            dataType:"json",
            data: JSON.stringify(dataParam),
            success: function (data) {
                if(data.code == '200'){
                    //成功
                    alert("提交成功");
                    $(".layui-layer-ico").click();
                }else {
                    //失败
                    alert(data.message);
                }
            },error:function () {
                alert("操作失败")
            }
        });
    }
}
function ch() {
    layer.confirm('确定要撤回吗？', {
        btn: ['确定','取消'] //按钮
    }, function(){
        var index = $("input[name='index']:checked").val();
        var obj = list[index];
        if(obj.sdjg !=  null&&obj.sdjg!=''){
            layer.msg("该工单处理完成,无法撤回");
            return;
        }
        if(obj.sdjg=='已撤回'){
            layer.msg("该工单已撤回,无法再次撤回");
            return;
        }

        $.ajax({
            url: "/gdch.aj",
            type:"post",
            data:{
                yysdbh:list[index].yysdbh
            },
            success: function (data) {
                alert(data.message);
                getTargetJudgeAjInfos($("#select_sdjg").val());
                getTargetJudgeAjInfosCount();
                layer.closeAll();
            },error:function () {
                alert("撤回失败")
            }
        });
    }, function(){
    });
}

function download_sdfkb() {
    var ssdrbh = $("input[name='ssdrs']:checked").val();
    if(ssdrbh == null)
        return;
    var index = $("input[name='index']:checked").val();
    var obj = list[index];
    var url = 'sdfk/downloadSdfkb.do?yysdbh=' + obj.yysdbh + '&ssdrbh=' +ssdrbh;
    window.open(url)

}
