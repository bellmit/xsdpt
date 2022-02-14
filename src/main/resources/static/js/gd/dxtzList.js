$(document).ready(function () {
    var sd_way = $.cookie('way');
    xtyh.fybh = $.cookie('fybh');
    xtyh.ajxh = $.cookie('ajxh');
    yhjs = $.cookie('yhjs');
    xtyh.yhm = $.cookie('yhm');
    start = getLastMonthTime();
    end = getNowTime();

    $("#select_time1").val(start);
    $("#select_time2").val(end);



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
    //获取目标账户的短信通知记录
    getTargetJudgeDxtzInfos();

});
$("#select_range_time").click(function () {
    start = $("#select_time1").val();
    end = $("#select_time2").val();

    flash_table();

});

function flash_table() {
    // console.log(' 现在在未分配页面')
    // 重新获取表格数据 刷新表格
    var dttable = $('#dxtzjbxx_table').dataTable();
    // dttable.fnClearTable(); //清空一下table
    dttable.fnDestroy(); //还原初始化了的datatable
    getTargetJudgeDxtzInfos();
}

var usrList = [];
var typeArr = [];
var xtyh = {
    fybh: "",
    ajxh: "",
    yhm: "",
};
var dxmbList = []; //全局短信模板list
var yysdList =[]; //工单列表
var paramList=[];
var selectedDxtzId = 0;
var thisgd=0;
var wslb=0;
var wslbList =[];
var getParamjsonBySsdrbh=0;
var getParamjsonByYysdbh=0;
var getParamjsonByBh=0;
var thislsdx=[];


/**
 * 查询短信通知列表
 */
function getTargetJudgeDxtzInfos() {
    var data = {
        gdryxm: ""
    }
    loadDxtzList(data);

}
function loadDxtzList(data) {
    var newEnd=end+" 23:59:59";
    $("#demoList").html("");
    $("#dxtzjbxx_table").dataTable().fnDestroy();
    $("#dxtzjbxx_tbody").html("");
    $.ajax({
        url: "/dxtz/load_listByTime.zf?start="+start+"&end="+newEnd,
        type: 'post',
        headers:{'Content-type':'application/json'},
        dataType:"json",
        data: JSON.stringify(data),
        success: function (data) {
            show_table(data);
            $("#dxtzjbxx_table").dataTable().fnDestroy();
            $('#dxtzjbxx_table').DataTable({
                bLengthChange: false, //去掉每页显示多少条数据方法
                iDisplayLength: 100,
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
    var html = template('dxtz_td_body', test_data);
    $("#dxtzjbxx_tbody").append(html);
}

$("#return_main").on("click", function () {
    if(yhjs=='admin'){
        location.href = "/wbryIndex"
    }else{
        location.href = "/gdIndex"
    }
});

/**
 * 工单选择项改变事件
 */
var yysdbhall;
var ws_yysdbh;
$("#dxtz-yysdbh-input").on("change", function () {
    //$("#demoList").html("");
    $("#uploadFileShow").css("display","none");
    $("#JZDXLS").css("display","none");
    var thisVal = $(this).val();
    yysdbhall = $(this).val();
    if (thisVal) {
        //选择了工单
        //第一步：查询工单中当事人下的电话号码并将数据填充到页面上

        $("#tel-tbody").html(""); //清空后填充
        $("#dxtz-dsr").val("") //清空后填充
        $("#dxtz-dsr").html("<option value='0'>请选择当事人</option>") //清空后填充
        $("#dxtz-dxmb-input").html("");
        $(".laay").html("");
        $("#dxtz-mbtc1").html("");
        $("#dxtz-mbtc").html("");

        for (var i =0 ;i<yysdList.length ;i++){
            var item = yysdList[i];
            if(thisVal == item.yysdbh){
                thisgd=item;
                ws_yysdbh=thisVal;
                $.ajax({
                    url: "/lylq/get_laay.zf",
                    type: 'post',
                    data:{
                        yysdbh:thisVal
                    },
                    success: function (data) {
                        var laay=data;
                        getParamjsonByYysdbh=thisVal;
                        $(".laay").html(laay);

                    }
                });
                $.ajax({
                    url: "/getGdxqxx.aj",
                    type: 'post',
                    data:{
                        yysdbh:thisVal
                    },
                    success: function (result) {

                        $.ajax({
                            url: "/dxtz/load_template_list.zf",
                            type: 'post',
                            data:{
                                fybh:result.object.jb.fybh
                            },
                            success: function (data) {
                                $("#dxtz-dxmb-input").append(" <option>请选择短信模板</option>")
                                for (var i = 0; i < data.length; i++) {
                                    dxmbList = data;
                                    for (var i = 0; i < data.length; i++) {
                                        var item = data[i];
                                        $("#dxtz-dxmb-input").append("<option value='"+item.bh+"'>"+item.mbmc+"</option>")
                                    }
                                }

                            }
                        });
                    }
                });
                var data = {
                    sdpName: item.dsrmc,
                    sdpIdCard: item.sfzhm,
                    yysdbh: item.yysdbh
                };
                $.ajax({
                    url: "/ssdr/query_ssdr_list.zf",
                    type: 'post',
                    headers:{'Content-type':'application/json'},
                    dataType:"json",
                    data: JSON.stringify(data),
                    success: function (data) {
                        $("#sdp").css("display","block");
                        for (var i = 0; i < data.length; i++) {
                            $("#dxtz-dsr").append("<option value=\"" + data[i].ssdrbh + "\">" +  data[i].ssdrmc + "</option>");
                        }
                        $("#dxtz-dsr").selectpicker("refresh");
                        //第二步：显示短信下发主体内容
                        $("#dxtz-zhuti").show();

                    },error:function () {
                        alert("获取当事人信息失败")
                    }
                });

                // $.ajax({
                //     url: "/dxtz/query_sdp_phone.zf",
                //     type: 'post',
                //     headers:{'Content-type':'application/json'},
                //     dataType:"json",
                //     data: JSON.stringify(data),
                //     success: function (data) {
                //         var telData = data;
                //         for (var i = 0; i < telData.length; i++) {
                //             var htmlStr = "<tr>\n" +
                //                 "  <td>\n" +
                //                 "     <input type=\"checkbox\" data-hint='" + telData[i].bh + "' name='dxtz-tel-checkbox'>\n" +
                //                 "  </td>\n" +
                //                 "  <td>" + telData[i].showTel + "</td>\n" +
                //                 "</tr>";
                //             $("#tel-tbody").append(htmlStr);
                //         }
                //
                //         //第二步：显示短信下发主体内容
                //         $("#dxtz-zhuti").show();
                //     },error:function () {
                //         alert("获取号码信息失败")
                //     }
                // });
            }
        }



    } else {
        //没选中 隐藏
        $("#dxtz-zhuti").hide();
    }
})
var ssdrbh;
$("#dxtz-dsr").on("change", function () {
    // $("#demoList").html("");
    // $("#uploadFileShow").css("display","none");
    $("#tel-tbody").html(""); //清空后填充
    var thisVal = $(this).val();
    ssdrbh = thisVal;
    for (var i =0 ;i<yysdList.length ;i++){
        var item = yysdList[i];
        if(yysdbhall == item.yysdbh) {
            var data = {
                ssdrbh: thisVal,
                yysdBh: item.yysdbh
            };
            $.ajax({
                url: "/lylq/get_ws_list.zf",
                type: 'post',
                data:{
                    yysdbh:ws_yysdbh,
                    ssdrbh:ssdrbh
                },
                success: function (data) {
                    wslb=data;
                }
            });
            $.ajax({
                url: "/dxtz/query_sdp_phone.zf",
                type: 'post',
                headers: {'Content-type': 'application/json'},
                dataType: "json",
                data: JSON.stringify(data),
                success: function (data) {
                    getParamjsonBySsdrbh=thisVal;
                    $.ajax({
                        url: "/dxtz/get_paramjson.zf",
                        type: 'post',
                        data:{
                            yysdbh:getParamjsonByYysdbh,
                            ssdrbh:getParamjsonBySsdrbh,

                        },
                        success: function (result) {
                            paramList=result;
                        }
                    });
                    var telData = data;
                    for (var i = 0; i < telData.length; i++) {
                        var htmlStr = "";
                        if (telData[i].showTel[0] == "1") {
                            htmlStr += "<tr><td><input type=\"checkbox\" data-hint='" + telData[i].bh + "' name='dxtz-tel-checkbox'></td>";
                        } else {
                            htmlStr += "<tr><td><input disabled='disabled' type=\"checkbox\" data-hint='" + telData[i].bh + "' name='dxtz-tel-checkbox'></td>" ;

                        }

                        if (telData[i].hmly == 'CYR') {
                            htmlStr += "<td>相关参与人</td>"
                        } else if(telData[i].hmly){
                            htmlStr += "<td>"+_sysCodeToCn(telData[i].hmly,'SYS_HIS_DATA_LY')+"</td>";
                        } else if (telData[i].operatorType == 'ENTRY') {
                            htmlStr += "<td>录入</td>";
                        } else {
                            htmlStr += "<td>查询</td>"
                        }
                        htmlStr+="  <td>" + telData[i].showTel + "</td>";
                        htmlStr+="  <td>" + _sysCodeToCn(telData[i].newSendState,'SYS_SEND_STATE','暂无') + "</td>";
                        htmlStr+="  <td>" + _sysCodeToCn(telData[i].newFwzt,'SYS_FWZT','暂无') + "</td></tr>";
                        $("#tel-tbody").append(htmlStr);


                    }
                        //第二步：显示短信下发主体内容
                        $("#dxtz-zhuti").show();
                }, error: function () {
                    alert("获取号码信息失败")
                }
            });
        }
    }

    $.ajax({
        url: "/getDsrwslb.aj",
        type: 'post',
        data: {
            yysdbh: getParamjsonByYysdbh,
            ssdrbh: getParamjsonBySsdrbh
        },
        success: function (data) {
            $("#wslbcheckboxdxsd").html("");
            if(data.length == 0){
                $("#wslbcheckboxdxsd").append("<input class=\"form-control confirmAddress\" rows=\"3\" placeholder=\"无相应文书\"/>");
            }
            for (var i = 0; i < data.length; i++) {
                $("#wslbcheckboxdxsd").append(
                    "<div class=\"form-check\">" +
                    "<label class=\"form-check-label\">" +
                    " <input type=\"checkbox\" class=\"form-check-input\" value=\""+ data[i].yysdbhBh + "\">" + data[i].ssdrmcWslb +
                    "</label>" +
                    "</div>"
                )
            }
        }
    });
})
/**
 * 短信模板改变事件
 */
var thisitem=0;
$("#dxtz-dxmb-input").on("change", function () {
    $("#JZDXLS").css("display","none");
    $("#demoList").html("");
    $("#uploadFileShow").css("display","none");
    $("#lj").val("");
    var thisVal = $(this).val();
    if (thisVal) {
        //选择了模板
        //第一步：重新动态填充短信内容参数模板

        for(var i=0;i<paramList.length;++i){
            var item = paramList[i];
            if(thisVal != item.bh){
                continue;
            }
            thislsdx=item.paramjson;
            if(thislsdx.length!=0){
                $("#JZDXLS").css("display","block");
            }


        }
        var data = [],fymc="";
        for (var i = 0; i < dxmbList.length; i++) {
            var item = dxmbList[i];
            if(thisVal != item.bh){
                continue;
            }
            fymc=item.fymc;
            thisitem=item;


            var paramNameList = item.paramNameList;
            if (paramNameList.indexOf("文书详情")!=-1){
                $("#uploadFileShow").css("display","block");
            }

            var arr_step_one = item.mbnr.split(/{{|}}/);
            if(arr_step_one.length > 0){
                for (var j = 0;j<arr_step_one.length;j++){
                    var v = arr_step_one[j];
                    var pIndex = paramNameList.indexOf(v);
                    if(pIndex >=0){
                        data.push({
                            type: "I", //类型 ： T=>固定文本 I:变量
                            content: "请输入" + v,// 类型为 =》代表固定文本内容 I =》代表输入框placeholder值
                            paramName: v //变量名
                        });
                    }else {
                        data.push({
                            type: "T", //类型 ： T=>固定文本 I:变量
                            content: v,// 类型为 =》代表固定文本内容 I =》代表输入框placeholder值
                        });
                    }
                }
            }

        }

        $("#dxtz-mbtc").html("");
        $("#dxtz-mbtc1").html("");

        var htmlStr = "";
        var html1="";
        var htmlStr1="";

        var count=0;

        for (var i = 0; i < data.length; i++) {
            htmlStr = "";
            html1="";
            htmlStr1="";
            if (data[i].type === 'I') {
                if (data[i].paramName=="文书详情"){
                    htmlStr = "<input readonly class='dxmb-param form-control goal"+i.toString()+"' aria-describedby=\"input-group-example\" placeholder='" + data[i].content + "' name='" + data[i].paramName + "'  onmouseover=\"this.title=this.value\"/>";
                    htmlStr1=htmlStr1+"<input readonly class='dxmb-param form-control goal"+i.toString()+"' aria-describedby=\"input-group-example\"      placeholder='" + data[i].content + "' name='" + data[i].paramName + "'  onmouseover=\"this.title=this.value\"/>";
                    html1="<span> 此处为生成的文书链接 </span>";
                    ++count;
                }
                else if(data[i].paramName=="案件基本信息"||data[i].paramName=="案件信息") {
                    htmlStr = "<input  class='dxmb-param form-control goal"+i.toString()+"' aria-describedby=\"input-group-example\" value="+thisgd.ajmc+"  placeholder='" + data[i].content + "' name='" + data[i].paramName + "' onmouseover=\"this.title=this.value\"/>";
                    htmlStr1 = htmlStr1+"<input  class='dxmb-param form-control goal"+i.toString()+"' aria-describedby=\"input-group-example\" value="+thislsdx[count]+"  placeholder='" + data[i].content + "' name='" + data[i].paramName + "' onmouseover=\"this.title=this.value\"/>";
                    html1="<span class=' text-info goal"+i.toString()+"-view'></span>";
                    ++count;

                }
                else if(data[i].paramName=="文书名称"||data[i].paramName=="请输入文书名称"){
                    htmlStr = "<input  class='dxmb-param form-control goal"+i.toString()+"' aria-describedby=\"input-group-example\" value="+wslb+" placeholder='" + data[i].content + "' name='" + data[i].paramName + "' onmouseover=\"this.title=this.value\"/>";
                    htmlStr1 =htmlStr1+ "<input  class='dxmb-param form-control goal"+i.toString()+"' aria-describedby=\"input-group-example\" value="+thislsdx[count]+"  placeholder='" + data[i].content + "' name='" + data[i].paramName + "' onmouseover=\"this.title=this.value\"/>";
                    html1="<span class=' text-info goal"+i.toString()+"-view'></span>";
                    ++count;

                }

                else {
                    htmlStr = "<input class='dxmb-param form-control goal"+i.toString()+"' aria-describedby=\"input-group-example\"  placeholder='" + data[i].content + "' name='" + data[i].paramName + "' onmouseover=\"this.title=this.value\"/>";
                    htmlStr1 = htmlStr1+"<input  class='dxmb-param form-control goal"+i.toString()+"' aria-describedby=\"input-group-example\" value="+thislsdx[count]+"  placeholder='" + data[i].content + "' name='" + data[i].paramName + "' onmouseover=\"this.title=this.value\"/>";
                    html1="<span class=' text-info goal"+i.toString()+"-view'></span>";
                    ++count;
                }
            } else {
                htmlStr = "<span>" + data[i].content + "</span>";
                html1="<span>" + data[i].content + "</span>";
                htmlStr1 = htmlStr1+"<span>" + data[i].content + "</span>";

            }

            $("#dxtz-mbtc").append(htmlStr);
            $("#dxtz-mbtc1").append(html1);






        }
        $('.goal0').keyup(function(){
            var gtext = $('.goal0').val()
            $('.goal0-view').html(gtext)
        });
        $('.goal1').keyup(function(){
            var gtext = $('.goal1').val()
            $('.goal1-view').html(gtext)
        });
        $('.goal2').keyup(function(){
            var gtext = $('.goal2').val()
            $('.goal2-view').html(gtext)
        });
        $('.goal3').keyup(function(){
            var gtext = $('.goal3').val()
            $('.goal3-view').html(gtext)
        });
        $('.goal4').keyup(function(){
            var gtext = $('.goal4').val()
            $('.goal4-view').html(gtext)
        });
        $('.goal5').keyup(function(){
            var gtext = $('.goal5').val()
            $('.goal5-view').html(gtext)
        });
        $('.goal6').keyup(function(){
            var gtext = $('.goal6').val()
            $('.goal6-view').html(gtext)
        });
        $('.goal7').keyup(function(){
            var gtext = $('.goal7').val()
            $('.goal7-view').html(gtext)
        });
        $('.goal8').keyup(function(){
            var gtext = $('.goal8').val()
            $('.goal8-view').html(gtext)
        });
        $('.goal9').keyup(function(){
            var gtext = $('.goal9').val()
            $('.goal9-view').html(gtext)
        });
        $('.goal10').keyup(function(){
            var gtext = $('.goal10').val()
            $('.goal10-view').html(gtext)
        });
        $('.goal11').keyup(function(){
            var gtext = $('.goal11').val()
            $('.goal11-view').html(gtext)
        });
        $('.goal12').keyup(function(){
            var gtext = $('.goal12').val()
            $('.goal12-view').html(gtext)
        });
        $('.goal13').keyup(function(){
            var gtext = $('.goal13').val()
            $('.goal13-view').html(gtext)
        });
        $('.goal14').keyup(function(){
            var gtext = $('.goal14').val()
            $('.goal14-view').html(gtext)
        });
        $('.goal15').keyup(function(){
            var gtext = $('.goal15').val()
            $('.goal15-view').html(gtext)
        });
        $('.goal16').keyup(function(){
            var gtext = $('.goal16').val()
            $('.goal16-view').html(gtext)
        });
        $('.goal17').keyup(function(){
            var gtext = $('.goal17').val()
            $('.goal17-view').html(gtext)
        });
        $('.goal18').keyup(function(){
            var gtext = $('.goal18').val()
            $('.goal18-view').html(gtext)
        });
        $('.goal19').keyup(function(){
            var gtext = $('.goal19').val()
            $('.goal19-view').html(gtext)
        });
        $('.goal20').keyup(function(){
            var gtext = $('.goal20').val()
            $('.goal20-view').html(gtext)
        });
        $('.goal21').keyup(function(){
            var gtext = $('.goal21').val()
            $('.goal2l-view').html(gtext)
        });
        $('.goal22').keyup(function(){
            var gtext = $('.goal22').val()
            $('.goal22-view').html(gtext)
        });
        $('.goal23').keyup(function(){
            var gtext = $('.goal23').val()
            $('.goal23-view').html(gtext)
        });
        $('.goal24').keyup(function(){
            var gtext = $('.goal24').val()
            $('.goal24-view').html(gtext)
        });
        $('.goal25').keyup(function(){
            var gtext = $('.goal25').val()
            $('.goal25-view').html(gtext)
        });
        $('.goal26').keyup(function(){
            var gtext = $('.goal26').val()
            $('.goal26-view').html(gtext)
        });
        $('.goal27').keyup(function(){
            var gtext = $('.goal27').val()
            $('.goal27-view').html(gtext)
        });$('.goal28').keyup(function(){
            var gtext = $('.goal28').val()
            $('.goal28-view').html(gtext)
        });$('.goal29').keyup(function(){
            var gtext = $('.goal29').val()
            $('.goal29-view').html(gtext)
        });$('.goal30').keyup(function(){
            var gtext = $('.goal30').val()
            $('.goal30-view').html(gtext)
        })






        //第二步：显示参数模板内容
        $("#dxtz-param").show();
    } else {
        //没选中 隐藏
        $("#dxtz-param").hide();
    }
})

$("#jzlssj-dx").on("click", function () {
    $("#JZDXLS").css("display","none");
    $("#demoList").html("");
    $("#uploadFileShow").css("display","none");
    $("#lj").val("");
        //选择了模板
        //第一步：重新动态填充短信内容参数模板
        var data = [];
        var pu=thislsdx;
    if(pu.length!=0){
        $("#JZDXLS").css("display","block");
    }
            var paramNameList = thisitem.paramNameList;
            if (paramNameList.indexOf("文书详情")!=-1){
                $("#uploadFileShow").css("display","block");
            }


            var arr_step_one = thisitem.mbnr.split(/{{|}}/);
            if(arr_step_one.length > 0){
                for (var j = 0;j<arr_step_one.length;j++){
                    var v = arr_step_one[j];
                    var pIndex = paramNameList.indexOf(v);
                    if(pIndex >=0){
                        data.push({
                            type: "I", //类型 ： T=>固定文本 I:变量
                            content: "请输入" + v,// 类型为 =》代表固定文本内容 I =》代表输入框placeholder值
                            paramName: v //变量名
                        });
                    }else {
                        data.push({
                            type: "T", //类型 ： T=>固定文本 I:变量
                            content: v,// 类型为 =》代表固定文本内容 I =》代表输入框placeholder值
                        });
                    }
                }
            }



        $("#dxtz-mbtc").html("");
        $("#dxtz-mbtc1").html("");

        var htmlStr = "";
        var html1="";
        var htmlStr1="";
        var count=0;

        for (var i = 0; i < data.length; i++) {
            htmlStr = "";
            html1="";
            htmlStr1="";
            if (data[i].type === 'I') {
                if (data[i].paramName=="文书详情"){
                    htmlStr = "<input readonly class='dxmb-param form-control goal"+i.toString()+"' aria-describedby=\"input-group-example\" placeholder='" + data[i].content + "' name='" + data[i].paramName + "'  onmouseover=\"this.title=this.value\"/>";
                    htmlStr1=htmlStr1+"<input readonly class='dxmb-param form-control goal"+i.toString()+"' aria-describedby=\"input-group-example\"      placeholder='" + data[i].content + "' name='" + data[i].paramName + "'  onmouseover=\"this.title=this.value\"/>";
                    html1="<span> 此处为生成的文书链接 </span>";
                    ++count;
                }
                else if(data[i].paramName=="案件基本信息"||data[i].paramName=="案件信息") {
                    htmlStr = "<input  class='dxmb-param form-control goal"+i.toString()+"' aria-describedby=\"input-group-example\" value="+thisgd.ajmc+"  placeholder='" + data[i].content + "' name='" + data[i].paramName + "' onmouseover=\"this.title=this.value\"/>";
                    htmlStr1 = htmlStr1+"<input  class='dxmb-param form-control goal"+i.toString()+"' aria-describedby=\"input-group-example\" value="+thislsdx[count]+"  placeholder='" + data[i].content + "' name='" + data[i].paramName + "' onmouseover=\"this.title=this.value\"/>";
                    html1="<span class=' text-info goal"+i.toString()+"-view'></span>";
                    ++count;

                }
                else if(data[i].paramName=="文书名称"||data[i].paramName=="请输入文书名称"){
                    htmlStr = "<input  class='dxmb-param form-control goal"+i.toString()+"' aria-describedby=\"input-group-example\" value="+wslb+" placeholder='" + data[i].content + "' name='" + data[i].paramName + "' onmouseover=\"this.title=this.value\"/>";
                    htmlStr1 =htmlStr1+ "<input  class='dxmb-param form-control goal"+i.toString()+"' aria-describedby=\"input-group-example\" value="+thislsdx[count]+"  placeholder='" + data[i].content + "' name='" + data[i].paramName + "' onmouseover=\"this.title=this.value\"/>";
                    html1="<span class=' text-info goal"+i.toString()+"-view'></span>";
                    ++count;

                }

                else {
                    htmlStr = "<input class='dxmb-param form-control goal"+i.toString()+"' aria-describedby=\"input-group-example\"  placeholder='" + data[i].content + "' name='" + data[i].paramName + "' onmouseover=\"this.title=this.value\"/>";
                    htmlStr1 = htmlStr1+"<input  class='dxmb-param form-control goal"+i.toString()+"' aria-describedby=\"input-group-example\" value="+thislsdx[count]+"  placeholder='" + data[i].content + "' name='" + data[i].paramName + "' onmouseover=\"this.title=this.value\"/>";
                    html1="<span class=' text-info goal"+i.toString()+"-view'></span>";
                    ++count;
                }
            } else {
                htmlStr = "<span>" + data[i].content + "</span>";
                html1="<span>" + data[i].content + "</span>";
                htmlStr1 = htmlStr1+"<span>" + data[i].content + "</span>";

            }

            $("#dxtz-mbtc").append(htmlStr1);
            $("#dxtz-mbtc1").append(html1);
        }
    $('.goal0').keyup(function(){
        var gtext = $('.goal0').val()
        $('.goal0-view').html(gtext)
    });
    $('.goal1').keyup(function(){
        var gtext = $('.goal1').val()
        $('.goal1-view').html(gtext)
    });
    $('.goal2').keyup(function(){
        var gtext = $('.goal2').val()
        $('.goal2-view').html(gtext)
    });
    $('.goal3').keyup(function(){
        var gtext = $('.goal3').val()
        $('.goal3-view').html(gtext)
    });
    $('.goal4').keyup(function(){
        var gtext = $('.goal4').val()
        $('.goal4-view').html(gtext)
    });
    $('.goal5').keyup(function(){
        var gtext = $('.goal5').val()
        $('.goal5-view').html(gtext)
    });
    $('.goal6').keyup(function(){
        var gtext = $('.goal6').val()
        $('.goal6-view').html(gtext)
    });
    $('.goal7').keyup(function(){
        var gtext = $('.goal7').val()
        $('.goal7-view').html(gtext)
    });
    $('.goal8').keyup(function(){
        var gtext = $('.goal8').val()
        $('.goal8-view').html(gtext)
    });
    $('.goal9').keyup(function(){
        var gtext = $('.goal9').val()
        $('.goal9-view').html(gtext)
    });
    $('.goal10').keyup(function(){
        var gtext = $('.goal10').val()
        $('.goal10-view').html(gtext)
    });
    $('.goal11').keyup(function(){
        var gtext = $('.goal11').val()
        $('.goal11-view').html(gtext)
    });
    $('.goal12').keyup(function(){
        var gtext = $('.goal12').val()
        $('.goal12-view').html(gtext)
    });
    $('.goal13').keyup(function(){
        var gtext = $('.goal13').val()
        $('.goal13-view').html(gtext)
    });
    $('.goal14').keyup(function(){
        var gtext = $('.goal14').val()
        $('.goal14-view').html(gtext)
    });
    $('.goal15').keyup(function(){
        var gtext = $('.goal15').val()
        $('.goal15-view').html(gtext)
    });
    $('.goal16').keyup(function(){
        var gtext = $('.goal16').val()
        $('.goal16-view').html(gtext)
    });
    $('.goal17').keyup(function(){
        var gtext = $('.goal17').val()
        $('.goal17-view').html(gtext)
    });
    $('.goal18').keyup(function(){
        var gtext = $('.goal18').val()
        $('.goal18-view').html(gtext)
    });
    $('.goal19').keyup(function(){
        var gtext = $('.goal19').val()
        $('.goal19-view').html(gtext)
    });
    $('.goal20').keyup(function(){
        var gtext = $('.goal20').val()
        $('.goal20-view').html(gtext)
    });
    $('.goal21').keyup(function(){
        var gtext = $('.goal21').val()
        $('.goal2l-view').html(gtext)
    });
    $('.goal22').keyup(function(){
        var gtext = $('.goal22').val()
        $('.goal22-view').html(gtext)
    });
    $('.goal23').keyup(function(){
        var gtext = $('.goal23').val()
        $('.goal23-view').html(gtext)
    });
    $('.goal24').keyup(function(){
        var gtext = $('.goal24').val()
        $('.goal24-view').html(gtext)
    });
    $('.goal25').keyup(function(){
        var gtext = $('.goal25').val()
        $('.goal25-view').html(gtext)
    });
    $('.goal26').keyup(function(){
        var gtext = $('.goal26').val()
        $('.goal26-view').html(gtext)
    });
    $('.goal27').keyup(function(){
        var gtext = $('.goal27').val()
        $('.goal27-view').html(gtext)
    });$('.goal28').keyup(function(){
        var gtext = $('.goal28').val()
        $('.goal28-view').html(gtext)
    });$('.goal29').keyup(function(){
        var gtext = $('.goal29').val()
        $('.goal29-view').html(gtext)
    });$('.goal30').keyup(function(){
        var gtext = $('.goal30').val()
        $('.goal30-view').html(gtext)
    })





        //第二步：显示参数模板内容
        $("#dxtz-param").show();

});
function jzlssj() {
    if ($("#dxtz-mbtc").change()){
        alert("加载完成");
        return;
    }

}


/**
 * 打开新增弹窗 时  数据填充
 */
$("#add_dxtz").on("click", function () {
    typeArr = [];
    usrList=[];
    //隐藏短信表单那主体 选择工单后显示
    $("#demoList").html("");
    $("#uploadFileShow").css("display","none");
    $("#JZDXLS").css("display","none");
    $("#sdp").css("display","none");
    $("#dxtz-zhuti").hide();
    $("#dxtz-param").hide();
    //获取当前登陆账号未结束的可见的工单列表
    $("#dxtz-yysdbh-input").html("<option value=''>请选择工单</option>")
    $("#dxtz-dxmb-input").html("<option value=''>请选择短信模板</option>")
    $.ajax({
        url: "/lylq/get_yysd_list.zf",
        type: 'post',
        success: function (data) {
            yysdList = data || [];
            for (var i = 0; i < data.length; i++) {
                $("#dxtz-yysdbh-input").append("<option value=\"" + data[i].yysdbh + "\">" + data[i].yysdbh+ " - " + data[i].ah+ "</option>");
            }
            $("#dxtz-yysdbh-input").selectpicker("refresh");
        }
    });
});


/**
 * 发送短信按钮点击事件
 */
var yysdbh;
$("#sendMSg").on("click", function () {

    var yysdbh = $("#dxtz-yysdbh-input").val();
    yysdbh = $("#dxtz-yysdbh-input").val();
    if(!yysdbh){
        layerErrorMsg("请选择工单");
        return;
    }
    var telCheckedEle = $("input[name='dxtz-tel-checkbox']:checked");
    if(!telCheckedEle.length){
        layerErrorMsg("请选择下发号码");
        return;
    }
    var targetTelBhs = [];
    for (var i = 0; i < telCheckedEle.length; i++) {
        var telId = $(telCheckedEle[i]).data("hint");
        targetTelBhs.push(telId)
    }

    var dxmb = $("#dxtz-dxmb-input").val();
    if(!dxmb){
        layerErrorMsg("请选择短信模板");
        return;
    }

    var inputError = false,errorMsg="";
    var paramInputEle = $(".dxmb-param");
    var paramObjList = [];
    for (var i = 0; i < paramInputEle.length; i++) {
        var itemEle = $(paramInputEle[i]);
        var value = itemEle.val();
        if(value){
            if(!value.trim()){
                itemEle.addClass("input-error");
                inputError = true;
                errorMsg = "请不要全部输入空格"
            }
            if(value.length > 100){
                itemEle.addClass("input-error");
                inputError = true;
                errorMsg = "长度超过100，请重新输入"
            }else {
                //校验通过
                itemEle.removeClass("input-error");

                paramObjList.push({
                    paramName: itemEle.attr("name"),
                    paramValue: value,
                })
            }
        }else {
            itemEle.addClass("input-error");
            inputError = true;
            errorMsg = "请完善短信模板参数信息"
        }
    }
    if(inputError){
        layerErrorMsg(errorMsg)
        return;
    }

    var dsrwslbArray  = [];
    var checkedbox =  $('input[type=checkbox]:checked');
    $.each(checkedbox,function(){
        // console.log("你选了："+
        //     checkedbox.length+"个，其中有："+$(this).val());
        dsrwslbArray.push($(this).val());
    });


    var dataJson = {
        targetTelBhs: targetTelBhs,
        templateId: dxmb,
        yysdbh: yysdbh,
        paramObjList: paramObjList,
        ssdrbh: $("#dxtz-dsr").val(),
        urlLis: usrList,
        dsrwslbArray: dsrwslbArray
    }
    //请求API
    $.ajax({
        url: "/dxtz/send_short_msg.zf",
        type: 'post',
        headers:{'Content-type':'application/json'},
        dataType:"json",
        data:JSON.stringify(dataJson),
        success: function (data) {
            //重新加载数据
            document.location.reload();
            getTargetJudgeDxtzInfos();
            //关闭弹窗
            $("#addDxtzModel").modal('hide');
            //清空表单数据
            $("#dxtz-yysdbh-input").val("");
            alert("短信下发操作成功");
        },error:function (res) {
            var errorMsg = res.responseJSON.message || '短信下发操作失败';
            alert(errorMsg)
        }
    });



})

/**
 * 送达结果编辑按钮单机事件
 */
$('#dxtzjbxx_tbody').on("click",".edit-sdjg",function () {
    selectedDxtzId = $(this).data("hint");
})

function updateDxtzSdjg(dataJson) {
    $.ajax({
        url: "/dxtz/edit_sdjg.zf",
        type: 'post',
        headers:{'Content-type':'application/json'},
        dataType:"json",
        data:JSON.stringify(dataJson),
        success: function (data) {
            location.reload();
            // //重新加载数据
            // getTargetJudgeDxtzInfos();
            // //关闭弹窗
            // $("#editSdjgModel").modal('hide');
            // //清空表单数据
            // $("input[name='selectedSdjg']:checked
            // ").prop("checked",false);
        },error:function () {
            alert("送达结果更新失败")
        }
    });
}
function layerErrorMsg(msg){
    //异常提醒
    layui.use("layer",function () {
        layui.layer.msg(msg,{icon:7})
    })
}
/**
 * 号码表格  全选checkbox事件
 */
$("input[name='dxtz-tel-checkbox-checkAll']").on("change", function () {
    if (this.checked){
        $(":checkbox[name='dxtz-tel-checkbox']").not(":disabled").each(function () {
            this.checked = true
        })
    }else{
        $(":checkbox[name='dxtz-tel-checkbox']").not(":disabled").each(function () {
            this.checked = false
        })
    }
    // $("input[name='dxtz-tel-checkbox']").prop("checked", $(this).is(":checked"))
})


$('#dxtzjbxx_tbody').on("click",".msgcontent-look",function () {
    var msg = $(this).data("hint");
    layui.use("layer",function () {
        layui.layer.alert(msg);
    })
})

/**
 * 发送状态下拉框改变时间
 */
$("#select_send_state").on("change",function () {
    var data = {
        sendstate: $(this).val(),
        fwzt: $("#select_fwzt_state").val(),
    }
    loadDxtzList(data);
})
$("#select_fwzt_state").on("change",function () {
    var data = {
        sendstate: $("#select_send_state").val(),
        fwzt: $(this).val()
    }
    loadDxtzList(data);
})


function sdjg(e) {
    var dxtzId = $(e).attr('data-hint');
    layer.confirm('选择当前方式送达结果', {
        btn: ['成功', '失败']
        , btn1: function (index, layero) {
            var dataJson = {
                dxtzId: dxtzId,
                sdjg: 1
            }
            updateDxtzSdjg(dataJson)
        }, btn2: function (index, layero) {
            var dataJson = {
                dxtzId: dxtzId,
                sdjg: 2
            }
            updateDxtzSdjg(dataJson)
        }
    });
}

var typeRule = ['image/png','image/gif','image/jpg','image/jpeg','application/pdf'];
layui.use('upload', function() {
    var upload = layui.upload;
    var demoListView = $("#demoList"), uploadListIns = upload.render({
        elem: '#uploadFile',
        url: "/dxtz/uploadFile",
        exts: "pdf|jpg|png|jpeg|gif",
        accept: 'file',
        multiple: true,
        auto: false,
        size: 30720,
        bindAction: "#sclj-btn",
        choose: function (obj) {
            var files = obj.pushFile();
            obj.preview(function (index, file, result) {
                typeArr.push(file.type);
                var falg = false;
                for (var i=0;i<typeArr.length;i++){
                    if (typeArr[0].split("/")[0]!=file.type.split("/")[0]){
                        alert("请上传同一种类型文件");
                        falg = true;
                        delete files[index];
                        break;
                    }
                }
                if(!falg){
                    var tr = $(['<tr id="upload-' + index + '">',
                        '<td>' + file.name + '</td>',
                        '<td>',
                        '<button class="layui-btn layui-btn-xs layui-btn-danger demo-delete">删除</button>',
                        '</td>',
                        '</tr>'].join(''));
                    $(".dxmb-param[name=\"文书详情\"]").val("");

                    tr.find(".demo-delete").on("click", function () {
                        delete files[index];
                        tr.remove()
                        $(".dxmb-param[name=\"文书详情\"]").val("");
                        var delLen = $(".demo-delete").length;
                        if(delLen == 0){
                            typeArr = [];
                        }
                        uploadListIns.config.elem.next()[0].value = '';
                    });
                    demoListView.append(tr)
                }


            })
        },
        before: function (obj) {
            var files = obj.pushFile();
            for (var key in files){
                var eleLen = $("#upload-"+key).length;
                if(eleLen == 0){
                    //元素不存在 移除文件
                    delete files[key];
                }
            }
        },
        done: function (res, index, upload) {
            if (null != res) {
                usrList.push(res.data); //文件地址集合
                var delLen = $(".demo-delete").length; //删除按钮个数
                if(delLen == usrList.length){
                    //上传完成
                    $("#sclj-btn").text("生成链接").removeClass("layui-btn-disabled");
                    $("input[name='文书详情']").val(_randLink());
                }

                return;
            }
            this.error(index, upload)
        },
        error: function (index, upload) {
            var tr = demoListView.find('tr#upload-' + index),
                tds = tr.children();
            tds.eq(2).html('<span style="color: #FF5722">上传成功</span>');
            tds.eq(3).find('.demo-delete').removeClass("layui-hide");
        }
    })
})
function sclj() {
    if ($("#demoList").html()==''){
        alert("请先上传文件");
        return;
    }
    usrList = [];
    $("#sclj-btn").text("正在生成，请稍后").addClass("layui-btn-disabled");

}





$("#dxtzjbxx_tbody").on("click",".look_zy",function(){
    var dxtzid = $(this).data("hint");
    $.cookie('way',"show");
    window.open("dxtz/showImages?yhm=" + $.cookie('yhm') + "&&id=" + dxtzid);
});

function upload_sdjg(e) {
    var yysdbh = $(e).attr('data-yysdbh');
    var dztzid = $(e).attr('data-dxtzid');
    var data={
        yysdbh:yysdbh,
        dxtzid:dztzid
    };
    var html = template('upload_sdjg_html',data);
    layer.open({
        type: 1,
        title: "上传送达结果",
        area: ['600px', '400px'],
        closeBtn: 1,
        shadeClose: true,
        skin: 'layui-layer-lan',
        content: html
    });
}

function upload_sdjg_pl() {
    var plscchecked = $("input[name='plsc']:checked");
    if(!plscchecked.length){
        layerErrorMsg("请选择需要上传的短信通知");
        return;
    }
    var yysdbh="";
    var dztzid="";
    $("input[name='plsc']").each(function () {
        if($(this).is(":checked")){
            var bhidstr=$(this).val();
            var bhid=bhidstr.split(",");
            yysdbh+=","+bhid[0];
            dztzid+=","+bhid[1];
        }
    })
    var data={
        yysdbh:yysdbh,
        dxtzid:dztzid
    };
    var html = template('upload_sdjg_pl_html',data);
    layer.open({
        type: 1,
        title: "上传送达结果",
        area: ['600px', '400px'],
        closeBtn: 1,
        shadeClose: true,
        skin: 'layui-layer-lan',
        content: html
    });
}

function upload_sdjg_dxtz(e) {
    $.ajax({
        url: "/upload_sdjg.aj",
        type: 'post',
        data: {
            type:$(e).attr('data-type'),
            yysdbh: $(e).attr('data-yysdbh'),
            id: $(e).attr('data-id'),
            sdjg:$('#sdjg_select_dxsd').val(),
            wtfs:$('#select_wtfs_dxsd').val(),
            wtclrbh:$('#select_wtclr_dxsd').val()
        },
        success: function (data) {
            alert(data.message);
            var dataJson = {
                dxtzId: $(e).attr('data-id'),
                sdjg: $('#sdjg_select_dxsd').val()
            }
            updateDxtzSdjg(dataJson);
            layer.closeAll();

        }, error: function () {
            alert("上传失败")
        }
    });


}

function upload_sdjg_dxtz_pl(e) {
    $.ajax({
        url: "/upload_sdjg_pl.aj",
        type: 'post',
        data: {
            type:$(e).attr('data-type-pl'),
            yysdbh: $(e).attr('data-yysdbh-pl'),
            id: $(e).attr('data-id-pl'),
            sdjg:$('#sdjg_select_dxsd_pl').val(),
            wtfs:$('#select_wtfs_dxsd_pl').val(),
            wtclrbh:$('#select_wtclr_dxsd_pl').val()
        },
        success: function (data) {
            alert(data.message);
            var dataJson = {
                dxtzIds: $(e).attr('data-id-pl'),
                sdjg: $('#sdjg_select_dxsd_pl').val()
            }
            updateDxtzSdjg(dataJson);
            layer.closeAll();

        }, error: function () {
            alert("上传失败")
        }
    });


}

function changeWtfspl(e) {
    var wtfs = $('#'+$(e).attr('data-id'));
    var selectedWtclr = $('#'+$(e).attr('data-change'));
    $.ajax({
        url: "/query_group.aj",
        type: 'post',
        data: {
            groupName:wtfs.val()
        },
        success: function (data) {
            selectedWtclr.html("<option value=''>请选择 </option>");
            for (var i = 0; i < data.length; i++) {
                selectedWtclr.append("<option value=" + data[i].ryid + ">" +  data[i].rymc + "</option>");
            }
            selectedWtclr.selectpicker("refresh");
        }, error: function () {
            alert("获取分组失败")
        }
    });
}

// $("input[name='pl-checkAll']").on("change", function () {
//     if (this.checked){
//         $(":checkbox[name='plsc']").not(":disabled").each(function () {
//             this.checked = true
//         })
//     }else{
//         $(":checkbox[name='plsc']").not(":disabled").each(function () {
//             this.checked = false
//         })
//     }
// })
$("input[name='pl-checkAll']").on("click", function () {
    if ($(this).is(':checked')){
        $(":checkbox[name='plsc']").each(function () {
            $(this).prop("checked",true);
        })
    }else{
        $(":checkbox[name='plsc']").each(function () {
            $(this).prop("checked",false);
        })
    }
    // $("input[name='dxtz-tel-checkbox']").prop("checked", $(this).is(":checked"))
})