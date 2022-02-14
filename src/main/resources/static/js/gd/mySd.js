var list;
var dxtzVO;
var status = 1;
template.defaults.imports.checkStatus=function(status){
    if(!status){
        return "--";
    }

    if(status === 'Y'){
        return "成功";
    }else{
        return "失败";
    }
}

$(document).ready(function () {
    var yhm =  $.cookie('yhm');
    // $("#userName").append("<span>"+yhm+"</span>");
    yhjs = $.cookie('yhjs');
    start = getLastMonthTime();
    end = getNowTime();

    $("#select_time1").val(start);
    $("#select_time2").val(end);
    if ($.cookie('yhjs') == "admin"){
        $('#dxmb').css("display","block")
        $('#user').css("display","block")
    }


    getCurrentFyNameBySessionFybh();


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
    getSdry();
    getFyInfo();
    getTargetJudgeAjInfos();
    $("#sdfy_select").on("change",function () {
        $.ajax({
            url: "/getFySdry.do",
            type: 'POST',
            data:{
                fybh:$("#sdfy_select").val()
            },
            success: function (data) {
                $("#sdfysdy_select").empty();
                $("#sdfysdy_select").append("<option value=\"\">请选择送达员</option>");
                for(var i = 0; i < data.length; i++){
                    $("#sdfysdy_select").append("<option value=\""+data[i].yhbh+"\">"+data[i].yhmc+"</option>");
                }

            }
        });

    })
});
$("#tssdgl").click(function () {
    window.open("/tssdgl")
})

$("#select_range_time").click(function () {
    start = $("#select_time1").val();
    end = $("#select_time2").val();
    status = $("#select_sdjg").val();
    flash_table();

});

$("#download_sdfk_excel").click(function () {
    start = $("#select_time1").val();
    end = $("#select_time2").val();
    status = $("#select_sdjg").val();
    downloadSdxxInfo();
});

function flash_table() {
        // console.log(' 现在在未分配页面')
        // 重新获取表格数据 刷新表格
        var dttable = $('#ajjbxx_table').dataTable();
        // dttable.fnClearTable(); //清空一下table
        dttable.fnDestroy(); //还原初始化了的datatable
        getTargetJudgeAjInfos();
}

//下拉框触发
function change() {
    status = $("#select_sdjg").val();
    getTargetJudgeAjInfos();
}


//获取指定送达结果的案件
var page_dt;
function getTargetJudgeAjInfos() {
    var newEnd = end+" 23:59:59";
    $.ajax({
        url: "/getYysdInfosByTime.do?start="+start+"&end="+newEnd,
        type:"post",
        data:{
            sdjg:$("#select_sdjg").val()
        },
        success: function (data) {
            for(var i=0;i<data.length;i++){
                data[i].yysj = data[i].yysj.substring(0,data[i].yysj.lastIndexOf(':'));
            }
            list = data;
            $("#ajjbxx_table").dataTable().fnDestroy();
            show_table(data);
            page_dt = $('#ajjbxx_table').dataTable({
                bLengthChange: false, //去掉每页显示多少条数据方法
                "bAutoWidth":false,//关闭列宽自动优化
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
                },
                "columnDefs": [
                    {
                        "targets": [2],
                        render: function (data, type, full, meta) {
                            if (data) {
                                if (data.length > 30) {
                                    return data.substr(0, 30) + " <a href = 'javascript:void(0);' onclick = 'javascript:searchBtn3(\""+data+"\")' >...</a> ";
                                }else{
                                    return data;
                                }
                            }else {
                                return "";
                            }
                        }
                    }
                ]

            });
        },error:function () {
            alert("获取案件信息失败")
        }
    });
}

function downloadSdxxInfo() {
    var newEnd = end+" 23:59:59";
    var uri = "/download_sd_excel.do?start="+start+"&end="+newEnd+"&sdjg=" + $("#select_sdjg").val();
    window.open(uri)
}

function searchBtn3(id) {
    layer.msg(id);
}

function info(e){
    var index = $(e).attr('data-index');
    var obj = list[index];
    $.cookie('yysdbh',obj['yysdbh']);
    window.open("wbrywdsd");
}
function look_yysdbz(e){
    var index = $(e).attr('data-index');
    var obj = list[index].yysdbz;
    layer.msg(obj);
}


function show_table(data) {
    var test_data = {
        title: '基本例子',
        isAdmin: false,
        list: data
    };
    var html = template('test', test_data);
    $("#ajjbxx_tbody").empty().append(html);
}


$("#return_main").on("click", function () {
    if(yhjs=='admin'){
        location.href = "/wbryIndex"
    }else if(yhjs=='zjzy'){
        location.href = "/zjzyIndex"
    }else {
        location.href = "/gdIndex"
    }

});

function fk(index){

    $.ajax({
        url: "/feedback.aj",
        data:{
            yysdbh:list[index].yysdbh
        },
        type:"post",
        success: function (data) {
            dxtzVO = data;
            var msg ="【天津法院】法官,您好!"+dxtzVO.yysdbh +
                "号工单(案号:"+dxtzVO.ah+")的送达任务已完成,送达结果:"+dxtzVO.sdjg+",请登录系统查看送达结果及详情。如果有问题请联系送达专员("+
                dxtzVO.sdyxm+","+dxtzVO.sdydh+")";
            var dataJson = {
                yysdbh:list[index].yysdbh,
                sendtype: 2,
                // sendphone: list[index].yyrdh,
                sendphone:list[index].yyrdh,
                msgcontent:msg
            }
            $.ajax({
                url: "/dxtz/send_plaintext",
                type: 'post',
                headers:{'Content-type':'application/json'},
                dataType:"json",
                data:JSON.stringify(dataJson),
                success: function (data) {
                    if(data.message=="success"){
                        alert("法官提示短信发送成功");
                    }else {
                        alert("法官提示短信发送失败");
                    }
                    getTargetJudgeAjInfos();
                    layer.closeAll();
                },error:function () {
                    alert("法官提示短信发送异常")
                }
            });
        },error:function () {
            alert("获取送达信息失败")
        }
    });

}

function fkXx(index){

    $.ajax({
        url: "/feedback.aj",
        data:{
            yysdbh:index,
        },
        type:"post",
        success: function (data) {
            dxtzVO = data;
            var msg ="【天津法院】法官,您好!"+dxtzVO.yysdbh +
                "号工单(案号:"+dxtzVO.ah+")的送达任务已完成,送达结果:"+dxtzVO.sdjg+",请登录系统查看送达结果及详情。如果有问题请联系送达专员("+
                dxtzVO.sdyxm+","+dxtzVO.sdydh+")";
            var dataJson = {
                yysdbh:index,
                sendtype: 2,
                // sendphone: list[index].yyrdh,
                sendphone:list[index].yyrdh,
                msgcontent:msg
            }
            $.ajax({
                url: "/dxtz/send_plaintext",
                type: 'post',
                headers:{'Content-type':'application/json'},
                dataType:"json",
                data:JSON.stringify(dataJson),
                success: function (data) {
                    if(data.message=="success"){
                        alert("法官提示短信发送成功");
                    }else {
                        alert("法官提示短信发送失败");
                    }
                    getTargetJudgeAjInfos();
                    layer.closeAll();
                },error:function () {
                    alert("法官提示短信发送异常")
                }
            });
        },error:function () {
            alert("获取送达信息失败")
        }
    });

}

function sdjg(e) {
    var bh = $(e).attr('data-index');
    var yysdbh = list[bh].yysdbh;
    checkSdjg(yysdbh);
}

function checkSdjg(yysdbh) {
    $.ajax({
        url: "/checkSdjg.aj",
        type: "post",
        data: {
            yysdbh: yysdbh
        },
        success: function (data) {
            if(data.message=="成功"){
                showSubmit(yysdbh);
            }else {
                layer.msg(data.message, {
                    time: 10000,
                    btn: ['确定', '取消']
                    ,yes: function(index){
                        showSubmit(yysdbh);
                    }
                });
            }

        }, error: function () {
            alert("上传失败")
        }
    })
}

function checkSdjgList(yysdbhList) {
    $.ajax({
        url: "/checkSdjgList.aj",
        type: "post",
        traditional:true,
        data: {
            yysdbhList: yysdbhList
        },
        success: function (data) {
            if(data.message=="成功"){
                rwlzPop();
            }else {
                layer.msg(data.message, {
                    btn: ['确定', '取消']
                    ,yes: function(index){
                        rwlzPop();
                    }
                });
            }
        }, error: function () {
            alert("流转失败")
        }
    })
}
function rwlzPop() {
    layer.open({
        type: 1,
        title: "任务流转",
        area: ['30em', '15em'],
        content: $('#rwlz_pop')
    })
}

$("#sdfy_select").on("change",function () {
    $.ajax({
        url: "/getFySdry.do",
        type: 'POST',
        data:{
            fybh:$("#sdfy_select").val()
        },
        success: function (data) {
            $("#sdfysdy_select").empty();
            $("#sdfysdy_select").append("<option value=\"\">请选择送达员</option>");
            for(var i = 0; i < data.length; i++){
                $("#sdfysdy_select").append("<option value=\""+data[i].yhbh+"\">"+data[i].yhmc+"</option>");
            }

        }
    });

})
function getFyInfo() {
    $.ajax({
        url: "/getFyInfos.do",
        type: 'POST',
        success: function (data) {
            for(var i = 0; i < data.length; i++){
                $("#sdfy_select").append("<option value=\""+data[i].fybh+"\">"+data[i].jc+"</option>");
            }

        }
    });
}
function getSdry() {
    $.ajax({
        url: "/getSdry.do",
        type: 'POST',
        success: function (data) {
            for(var i = 0; i < data.length; i++){
                $("#sdry_select").append("<option value=\""+data[i].yhbh+"\">"+data[i].yhmc+"</option>");
            }

        }
    });
}
function submitRwlz() {
    if($("#select_wtclr").val()==null&&$("#select_wtclr").val()==0){
        alert("未选中处理人");
    }
    if($("#sdry_select").val()==""){
        alert("没有选择送达员");
        return;
    }
    // if($("#sdry_select").val()!==""&&$("#sdfysdy_select").val()!==""){
    //     alert("只能选择一个送达员");
    //     return;;
    // }
    var sdybh;
    if ($("#sdry_select").val()!==""){
        sdybh= $("#sdry_select").val();
    } else {
        sdybh = $("#sdfysdy_select").val();
    }
    var yysdbhList = [];
    $("input[name='ids']:checked").each(function (i) {
        yysdbhList.push($(this).val());
    });
    $.ajax({
        url: "/uploadRwlzList.aj",
        type: "post",
        data: {
            yysdbhList: yysdbhList,
            target:sdybh
        },
        success: function (data) {
            layer.closeAll();
            getTargetJudgeAjInfos();
        }, error: function () {
            alert("流转失败");
        }
    })
}

var index;
function showSubmit(yysdbh) {
    var html ="<div style='margin-left: 20%;margin-top: 10px'><label><input name=\"sdjg\" type=\"radio\" value=\"1\" style='text-align: center'/>送达成功 </label> &nbsp;&nbsp;&nbsp;&nbsp;\n" +
        "<label><input name=\"sdjg\" type=\"radio\" value=\"0\" style='text-align: center'/>送达失败 </label> </div>"+
        "<div style=\"float: left;font-size: 15px;color: rgb(0,169,238);margin-left: 30px;margin-top: 30px;\">备注:&nbsp&nbsp<textarea id=\"sdjg_bz\" style='width:232px;height:144px'></textarea></div>\n" +
        "        <div style=\"text-align:center;margin-top: 60px;margin-left:50px\">\n" +
        "          <button id=\"button_sdcg\" class=\"btn btn-primary\" style=\"float: left;margin-left: 30%;margin-top: 10px\" onclick=\"sdjgSubmit("+yysdbh+")\">提交</button>\n" +
        "        </div>";
    index = layer.open({
        type: 1,
        title: "选择送达结果",
        shadeClose: true,
        area: ['300px', '350px'],
        content: html
    })
}


function sdjgSubmit(yysdbh) {
    var jg = $("input[name='sdjg']:checked").val();
    if(jg!=1&&jg!=0){
        alert("未选中送达结果");
        return;
    }
    if($("#sdjg_bz").val()!=null&&$("#sdjg_bz").val().length>100){
        alert("备注最多100字");
        return;
    }
    $.ajax({
        url: "/uploadSdjgMain.aj",
        type: "post",
        data: {
            sdjg: jg,
            yysdbh: yysdbh,
            bz: $("#sdjg_bz").val()
        },
        success: function (data) {
            layer.close(index);
            var tableSetings = page_dt.fnSettings();
            var paging_length = tableSetings._iDisplayLength;
            var page_start = tableSetings._iDisplayStart;
            var page = (page_start/paging_length);
            var newEnd = end+" 23:59:59";
            $.ajax({
                url: "/getYysdInfosByTime.do?start="+start+"&end="+newEnd,
                type:"post",
                data:{
                    sdjg:$("#select_sdjg").val()
                },
                success: function (data) {
                    for(var i=0;i<data.length;i++){
                        data[i].yysj = data[i].yysj.substring(0,data[i].yysj.lastIndexOf(':'));
                    }
                    list = data;
                    $("#ajjbxx_table").dataTable().fnDestroy();
                    show_table(data);
                    page_dt = $('#ajjbxx_table').dataTable({
                        bLengthChange: false, //去掉每页显示多少条数据方法
                        "bAutoWidth":false,//关闭列宽自动优化
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
                        },
                        "columnDefs": [
                            {
                                "targets": [2],
                                render: function (data, type, full, meta) {
                                    if (data) {
                                        if (data.length > 30) {
                                            return data.substr(0, 30) + " <a href = 'javascript:void(0);' onclick = 'javascript:searchBtn3(\""+data+"\")' >...</a> ";
                                        }else{
                                            return data;
                                        }
                                    }else {
                                        return "";
                                    }
                                }
                            }
                        ]

                    });
                    page_dt.fnPageChange(page);
                    alert("反馈成功");
                    fkXx(yysdbh);
                },error:function () {
                    alert("获取案件信息失败")
                }
            });
            // fkXx(yysdbh);
            // getTargetJudgeAjInfos();
            // page_dt.fnPageChange(page);
            // alert("反馈成功");
            // page_dt.fnPageChange(page);
            // return;

        }, error: function () {
            alert("上传失败");
            layer.closeAll();
            getTargetJudgeAjInfos();
            return;

        }
    })
}

function look_fk(e) {
    //获取工单编号
    var index = $(e).attr('data-index');
    var yysdbh = list[index].yysdbh;
    window.open("look_fk?yysdbh="+yysdbh)
}

function look_rwwt(e) {
    var yysdbh = $(e).attr('data-index');
    $.ajax({
        url: "/look_rwwt.aj",
        type: "post",
        data: {
            yysdbh: yysdbh
        },
        success: function (data) {
            var test_data = {
                list:data
            };
           var html = template('rwwt_html',test_data);
            layer.open({
                type: 1,
                title: "委托信息",
                area: ['700px', '450px'],
                closeBtn: 1,
                shadeClose: true,
                skin: 'layui-layer-lan',
                content: html
            });
        }, error: function () {
            alert("获取委托信息失败")
        }
    })
}


$('#dxmb').on("click",function () {
    window.open("/dxmb_list");
});
$('#user').on("click",function () {
    window.open("/user_list");
});
$('#rwlz').on("click",function () {
    var yysdbhList = [];
    $("input[name='ids']:checked").each(function (i) {
        yysdbhList.push($(this).val());
    });
    if(yysdbhList.length==0){
        alert("未选中任何工单");
        return;
    }
    // if($(".wtlz .selectedWtclr").val()==null||$(".wtlz .selectedWtclr").val()==0){
    //     alert("未选中处理人");
    //     return;
    // }
    checkSdjgList(yysdbhList);

});