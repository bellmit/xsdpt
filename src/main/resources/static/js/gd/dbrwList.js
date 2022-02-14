$(document).ready(function () {
    var sd_way = $.cookie('way');
    xtyh.fybh = $.cookie('fybh');
    xtyh.ajxh = $.cookie('ajxh');
    xtyh.yhm = $.cookie('yhm');
    yhjs = $.cookie('yhjs');

    getCurrentFyName(xtyh.fybh);

    //获取目标账户的来院领取记录
    getDbrwList(str);

});

var xtyh = {
    fybh: "",
    ajxh: "",
    yhm: "",
};

var usrList = [];
var typeArr = [];
var selectedLylqId = 0;
var list;
var thisgd=0;
var wslb=0;
var wslbList =[];
var yysdList=[];
var getParamjsonBySsdrbh=0;
var getParamjsonByYysdbh=0;
var getParamjsonByBh=0;
var thislsdx=[];
var lylqSdhz;
var xzsdfsxq;
/**
 * 查询待办任务列表
 */
var str;
var sdfs;
var jsName = "dbrwList.js";
function selectDbrw(value) {
    str = value
    getDbrwList();
}
function selectDbrwByType(value) {
    sdfs = value;
    getDbrwList();
}
function getDbrwList() {
    $("#dbrw_table").dataTable().fnDestroy();
    $("#dbrw_tbody").html("");
    $.ajax({
        url: "/dbrw/dbrwList",
        type:"post",
        data: {
            clzt: str,
            xzsdfs: sdfs,
            rymc: xtyh.yhm,
        },
        success: function (data) {
            list = data;
            show_table(data);
            $("#dbrw_table").dataTable().fnDestroy();
            $('#dbrw_table').DataTable({
                bLengthChange: false, //去掉每页显示多少条数据方法
                iDisplayLength: 10,
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
            })
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
    var html = template('dbrw_td_body', test_data);
    $("#dbrw_tbody").append(html);
}


function wcdb(e) {
    var index = $(e).attr('data-index');
    var dbrw = list[index];
    $.ajax({
        url: "/dbrw/updateById",
        type: 'post',
        headers:{'Content-type':'application/json'},
        data: JSON.stringify(dbrw),
        success: function (data) {
            var sddbrw = list[index].xzsdfs;
            console.log(JSON.stringify("********"+JSON.stringify(list[index].xzsdfsxq)))
            if(sddbrw == 1){
                var str = list[index].xzsdfsxq.replace(/\\/g,'');
                var strr = [];
                var lqsj,lylqaddress;
                if(str){
                    strr = str.split(",");
                    if(strr.length == 2){
                        lqsj = strr[0];
                        lylqaddress = strr[1];
                    }else {
                        if(strr[0].indexOf("-") > 0 && strr[0].indexOf(":") > 0){
                            lqsj = strr[0];
                        }else {
                            lylqaddress = strr[1];
                        }
                    }
                }
            }
            alert("处理完成");
            location.reload();
        }, error: function () {
            alert("处理失败")
        }
    });

}
var id;
var dsrbh;
var sdbh;
$("#ajxq").on("click", function () {
    loadGdxqxx(sdbh);
})
//送达按钮
function sddbrw(e) {
    $("#tel-tbody").html("");
    var index = $(e).attr('data-index');
    var sddbrw = list[index].xzsdfs;
    var gdh = list[index].casenumber;//工单号
    var dsr = list[index].bsdpeople;//当事人
    dsrbh = list[index].ssdrbh;
    sdbh = list[index].yysdbh;
    id = list[index].id;
    xzsdfsxq = list[index].xzsdfsxq;

    // 1 来院领取  2 邮寄送达  3 短信电子送达
    if (sddbrw == 1){
        var str = list[index].xzsdfsxq;//来院详情
        var arr = str.split(",");
        $("#addLylqModel").modal('show');
        $("#gdh").val(gdh);
        $("#dsr").val(dsr);
        $("#lylq-yysj-input").val(arr[0]);
        $("#yydd").val(arr[1]);
        lylq();
    }
    if (sddbrw == 2){
        emsShow(list[index].yysdbh,list[index].ssdrbh,list[index].bsdpeople);
    }
    if (sddbrw == 3){
        typeArr = [];
        usrList = [];
        //隐藏短信表单那主体 选择工单后显示
        $("#demoList").html("");
        $("#uploadFileShow").css("display","none");
        $("#JZDXLS").css("display","none");
        var gdha = list[index].casenumber;//工单号
        var dsra = list[index].bsdpeople;//当事人
        $("#gdha").val(gdha);
        $("#dsra").val(dsra);
        var htmlStr = "<tr>\n" +
            "  <td>\n" +
            "     <input type=\"checkbox\" data-hint='" + list[index].serviceno + "' name='dxtz-tel-checkbox'>\n" +
            "  </td>\n" +
            "  <td>" + list[index].xzsdfsxq + "</td>\n" +
            "</tr>";
        $("#tel-tbody").append(htmlStr);
        $("#addDxtzModela").modal('show');
        $.ajax({
            url: "/lylq/get_yysd.zf",
            type: 'post',
            data:{
                yysdbh:list[index].yysdbh
            },
            success: function (data) {
                thisgd=data;

            }
        });
        $.ajax({
            url: "/lylq/get_laay.zf",
            type: 'post',
            data:{
                yysdbh:list[index].yysdbh
            },
            success: function (data) {
                var laay=data;
                $(".laay").html(laay);

            }
        });
        $.ajax({
            url: "/dxtz/get_paramjson.zf",
            type: 'post',
            data:{
                yysdbh:list[index].yysdbh,
                ssdrbh:list[index].ssdrbh,

            },
            success: function (result) {
                paramList=result;
            }
        });
        $.ajax({
            url: "/getGdxqxx.aj",
            type: 'post',
            data:{
                yysdbh:list[index].yysdbh
            },
            success: function (result) {
                $.ajax({
                    url: "/lylq/get_ws_list.zf",
                    type: 'post',
                    data:{
                        yysdbh:list[index].yysdbh,
                        ssdrbh:list[index].ssdrbh
                    },
                    success: function (data) {
                        wslb=data;

                    }
                });

                var result1 = result;
                $.ajax({
                    url: "/dxtz/load_template_list.zf",
                    type: 'post',
                    data:{
                        fybh:result.object.jb.fybh
                    },
                    success: function (data) {
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

        // $.cookie('way',"DXTZ");
        // location.href="dxtz_list?yhm=" + $.cookie('yhm');
    }
}


$("#return_main").on("click", function () {
    if(yhjs=='admin'){
        location.href = "/wbryIndex"
    }else{
        location.href = "/gdIndex"
    }
});


$(function () {
    layui.use("laydate",function () {

        layui.laydate.render({
            elem:"#lylq-yysj-input",
            type: "datetime",
            min: -7,
            format: "yyyy-MM-dd HH:mm"
        })
    })

})

function lylq() {
    lylqSdhz = [];
    refreshSdhzTable();
    layui.use('upload', function(){
        var upload = layui.upload;
        //执行实例
        var uploadInst = upload.render({
            elem: '#upload_lylq_sdhz' ,
            accept:'file',
            acceptMime: 'application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.document, text/plain,application/pdf,image/*',
            auto:false,
            bindAction:'#submit',
            field:'sdhz',
            multiple:true,
            choose: function (obj){
                obj.preview(function (index,file,result) {
                    if(file.size==0){
                        layer.msg("不能上传空文件");
                        return;
                    }
                    addSdhz(file,result);
                })
            }
        });
    });
    $("#submit-lylq").on("click",function () {
        updateById();
        var lylq = {
            id: id,
            createOperator: xtyh.yhm,
            updateOperator: xtyh.yhm,
            lqsj: $("#lylq-yysj-input").val(),
            lylqaddress: $("#yydd").val(),
            ssdrbh: dsrbh,
            yysdbh: sdbh
        };
        $.ajax({
            url: "/dbrw/addLylq",
            type: 'post',
            headers:{'Content-type':'application/json'},
            data: JSON.stringify(lylq),
            success: function (data) {
                if(lylqSdhz.length == 0){
                    alert("处理完成");
                    location.reload();
                }else {
                    uploadLylqSdhz(data.object);
                }

            }, error: function () {
                alert("处理失败")
            }
        });
        $("#addLylqModel").modal('hide');

    })
}

function updateById() {
    var dataJson = {
        id : id
    };
    $.ajax({
        url: "/dbrw/updateById",
        type: 'post',
        headers: {'Content-type': 'application/json'},
        dataType: "json",
        data: JSON.stringify(dataJson),
        success: function (data) {
        }, error: function () {
            alert("处理失败")
        }
    });
}

function addSdhz(file,sdhz) {
    lylqSdhz.push({
        sdhz:sdhz,
        mc:file.name
    });
    refreshSdhzTable();
}
function   refreshSdhzTable(){
    var wsmc = $("#lylq_sdhz_wsmc");
    wsmc.empty();
    for (var i = 0 ; i < lylqSdhz.length ; i ++){
        var html = "<div>"+lylqSdhz[i].mc+"</div>";
        wsmc.append(html);
    }
}

function uploadLylqSdhz(lylqid) {
    for (var i = 0 ; i < lylqSdhz.length ; i ++){
        var dataJson = {
            lylqId: lylqid,
            sdhzBase64Str: lylqSdhz[i].sdhz,
            imageName:lylqSdhz[i].mc
        };
        $.ajax({
            url: "/lylq/upload_sdhz.zf",
            type: 'post',
            headers: {'Content-type': 'application/json'},
            dataType: "json",
            data: JSON.stringify(dataJson),
            success: function (data) {

            }, error: function () {
                alert("回执上传失败")
            }

        });
        if(i == lylqSdhz.length -1){
            lylqSdhz = [];
            alert("处理完成");
            location.reload();
        }
    }

}
var thisitem=0;
$("#dxtz-dxmb-input").on("change", function () {
    $("#demoList").html("");
    $("#uploadFileShow").css("display","none");
    $("#JZDXLS").css("display","none");
    var thisVal = $(this).val();
    if (thisVal) {
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
        //选择了模板
        //第一步：重新动态填充短信内容参数模板
        var data = [],fymc="";
        for (var i = 0; i < dxmbList.length; i++) {
            var item = dxmbList[i];
            if(thisVal != item.bh){
                continue;
            }
            fymc = item.fymc;
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
        var html1 = "";
        for (var i = 0; i < data.length; i++) {
            htmlStr = "";
            html1="";
            if (data[i].type === 'I') {
                if (data[i].paramName=="文书详情"){
                    htmlStr = "<input readonly class='dxmb-param form-control goal"+i.toString()+"' aria-describedby=\"input-group-example\" placeholder='" + data[i].content + "' name='" + data[i].paramName + "'  onmouseover=\"this.title=this.value\"/>";
                    html1="<span> 此处为生成的文书链接 </span>";
                }
                else if(data[i].paramName=="案件基本信息"||data[i].paramName=="案件信息") {
                    htmlStr = "<input  class='dxmb-param form-control goal"+i.toString()+"' aria-describedby=\"input-group-example\" value="+thisgd+"  placeholder='" + data[i].content + "' name='" + data[i].paramName + "' onmouseover=\"this.title=this.value\"/>";
                    html1="<span class=' text-info goal"+i.toString()+"-view'></span>";
                }
                else if(data[i].paramName=="文书名称"||data[i].paramName=="请输入文书名称"){
                    htmlStr = "<input  class='dxmb-param form-control goal"+i.toString()+"' aria-describedby=\"input-group-example\" value="+wslb+" placeholder='" + data[i].content + "' name='" + data[i].paramName + "' onmouseover=\"this.title=this.value\"/>";
                    html1="<span class=' text-info goal"+i.toString()+"-view'></span>";

                }

                else {
                    htmlStr = "<input class='dxmb-param form-control goal"+i.toString()+"' aria-describedby=\"input-group-example\" placeholder='" + data[i].content + "' name='" + data[i].paramName + "' onmouseover=\"this.title=this.value\"/>";
                    html1="<span class=' text-info goal"+i.toString()+"-view'></span>";
                }
            } else {
                htmlStr = "<span>" + data[i].content + "</span>";
                html1="<span>" + data[i].content + "</span>";

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
                htmlStr = "<input  class='dxmb-param form-control goal"+i.toString()+"' aria-describedby=\"input-group-example\" value="+thisgd+"  placeholder='" + data[i].content + "' name='" + data[i].paramName + "' onmouseover=\"this.title=this.value\"/>";
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



function layerErrorMsg(msg){
    //异常提醒
    layui.use("layer",function () {
        layui.layer.msg(msg,{icon:7})
    })
}
/**
 * 发送短信按钮点击事件
 */
$("#sendMSg").on("click", function () {
    var telCheckedEle = $("input[name='dxtz-tel-checkbox']:checked");
    if(!telCheckedEle.length){
        layerErrorMsg("请选择下发号码");
        return;
    }

    var dxmb = $("#dxtz-dxmb-input").val();
    if(dxmb=="请选择短信模板"){
        layerErrorMsg("请选择短信模板");
        return;
    }
    var targetTelBhs = [];
    var telCheckedEle = $("input[name='dxtz-tel-checkbox']:checked");
    for (var i = 0; i < telCheckedEle.length; i++) {
        var telId = $(telCheckedEle[i]).data("hint");
        targetTelBhs.push(telId)
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




    var dataJson = {
        id: id,
        targetTelBhs: targetTelBhs,
        templateId: dxmb,
        yysdbh: sdbh,
        paramObjList: paramObjList,
        ssdrbh: dsrbh,
        urlLis: usrList
    }
    //请求API
    $.ajax({
        url: "/dxtz/send_short_msg.zf",
        type: 'post',
        headers:{'Content-type':'application/json'},
        dataType:"json",
        data:JSON.stringify(dataJson),
        success: function (data) {
            $("#demoList").html("");
            //重新加载数据
            document.location.reload();
            //关闭弹窗
            $("#addDxtzModela").modal('hide');
            //清空表单数据
            $("#dxtz-yysdbh-input").val("");
        },error:function (res) {
            var errorMsg = res.responseJSON.message || '短信下发操作失败';
            alert(errorMsg)
        }
    });



})


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
                usrList.push(res.data);//文件地址集合
                var delLen = $(".demo-delete").length; //删除按钮个数
                if(delLen == usrList.length){
                    //上传完成
                    //todo uplaod
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
        alert("请先文件");
        return;
    }
    usrList = [];
    $("#sclj-btn").text("正在生成，请稍后").addClass("layui-btn-disabled");
}

$("input[name='dxtz-tel-checkbox-checkAll']").on("change", function () {
    if (this.checked){
        $(":checkbox[name='dxtz-tel-checkbox']").each(function () {
            this.checked = true
        })
    }else{
        $(":checkbox[name='dxtz-tel-checkbox']").each(function () {
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