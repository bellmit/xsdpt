var fybh;
var ajxxAndCbrxx;
var ajxh;
var dsrbh=0;
// 页面加载就进行渲染
$(document).ready(function () {
    getSdyInfo()
});

function return_main() {
    var yhjs=$.cookie('yhjs')
    console.log(yhjs)
    if(yhjs=='admin'){
        location.href = "/wbryIndex"
    }else if(yhjs=='zjzy'){
        location.href = "/zjzyIndex"
    }else {
        location.href = "/gdIndex"
    }
};

function return_glym(){
    location.href="/tssdgl"
}

function searchForAj(){
    $("#ssajModel").modal('show');
}

function searchForYyr() {
    $("#yyrxx").modal('show');
}

function searchForDsr() {
    $("#dsrxx").modal('show');
}

function findAjInLike() {
    var content=$("#search").val();
    var html='';
    var yhm=$.cookie('yhm')
    var params={
        content:content,
        yhm:yhm
    }
    $.ajax({
        url:"/tsyysd/findAjInLike",
        type: 'post',
        dataType:'json',
        contentType:'application/json',
        data:JSON.stringify(params),
        success:function (data) {
            if(data.length==0){
                html+='<tr><td colspan="3" align="center">暂无符合要求的案件</td></tr>'
            }
            for (var i = 0; i < data.length; i++){
                html+='<tr align="center">'
                html+='<td><button type="button" class="myButton hasAuthority" onclick="getAjxxAndCbrxx('+data[i].ajxh+')"><img src="/img/get.png" height="24" width="24" style="margin: 0"></button></td>'
                html+='<td>'+data[i].ah+'</td>'
                html+='<td>'+data[i].ajmc+'</td>'
                html+='</tr>'
            }
            $("#aj_tbody").empty();
            $("#aj_tbody").append(html);
        }
    })
}

function getSdyInfo() {
    var yhm=$.cookie('yhm')
    var params={
        yhm:yhm
    }
    $.ajax({
        url:"/tsyysd/getSdyInfo",
        type: 'post',
        dataType:'json',
        contentType:'application/json',
        data:JSON.stringify(params),
        success:function (data) {
            $("#sdzy").text(data.yhmc)
            $("#zydh").text(data.seatnumber)
            if(data.fybh===-1){
                fybh=53
            }else{
                fybh=data.fybh;
            }
        }
    })
}

function getAjxxAndCbrxx(ajxh){
    $("#ssajModel").modal('hide');
    $("#aj_tbody").empty();
    $("#bmmc").empty()
    $("#lxfs").empty()
    this.ajxh=ajxh
    getAjxx(ajxh)
    getDsrxx(ajxh)
}

function getAjxx(ajxh) {
    var yhm=$.cookie('yhm')
    var params={
        ajxh:ajxh,
        fybh:fybh,
        yhm:yhm
    }
    $.ajax({
        url:"/tsyysd/getAjInfo",
        type: 'post',
        dataType:'json',
        contentType:'application/json',
        data:JSON.stringify(params),
        success:function (data) {
            if(data[0]==null){
                alert("暂无该案件信息")
                return
            }
            ajxxAndCbrxx=data
            $("#ah").empty()
            $("#ajmc").empty()
            $("#laay").empty()
            $("#cbfg").empty()
            // $("#dsr").empty()
            $("#ah").text(data[0].ah)
            $("#ajmc").text(data[0].ajmc)
            $("#laay").text(data[0].laay)
            $("#yyr").html("<button class=\"btn btn-primary\" onclick=\"searchForYyr()\" style=\"font-size: 14px\">选择</button>")
            // $("#dsrxz").html("<button class=\"btn btn-primary\" onclick=\"searchForDsr()\">选择</button>")
            var html=''
            for(var i=0;i<data.length;i++){
                if(data[i].sfcbr==="Y"){
                    $("#cbfg").text(data[i].xm)
                }
                html+='<tr align="center">'
                html+='<td><button type="button" class="myButton hasAuthority" onclick="SelectYyrxx('+data[i].yhbh+')"><img src="/img/get.png" height="24" width="24" style="margin: 0"></button></td>'
                html+='<td>'+data[i].xm+'</td>'
                html+='<td>'+data[i].dmms+'</td>'
                html+='<td>'+data[i].phone+'</td>'
                html+='</tr>'
            }
            $("#yyr_tbody").empty();
            $("#yyr_tbody").append(html);
        }
    })
}

function getDsrxx(ajxh) {
    var yhm=$.cookie('yhm')
    var params={
        ajxh:ajxh,
        yhm:yhm
    }
    $.ajax({
        url: "/tsyysd/getDsrjb",
        type: 'post',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(params),
        success: function (data) {
            var html=''
            for(var i=0;i<data.length;i++){
                html+='<tr align="center">'
                html+='<td><input type="checkbox" name="dsrs" value="'+data[i].dsrbh+'"> </td>'
                html+='<td>'+data[i].dsrjc+'</td>'
                html+='</tr>'
            }
            $("#dsr_tbody").empty();
            $("#dsr_tbody").append(html);
        }
    })
}

function SelectDsrxx(dsrbh,dsrxm){
    $("#dsrxx").modal('hide');
    this.dsrbh=dsrbh

    // $("#dsr").empty()
    // $("#dsr").text(dsrxm)
    // $("#dsrxz").empty()
}

function SelectYyrxx(yhbh){
    $("#yyrxx").modal('hide');
    for(var i=0;i<ajxxAndCbrxx.length;i++){
        if(ajxxAndCbrxx[i].yhbh===yhbh){
            $("#yyr").empty()
            $("#yyr").text(ajxxAndCbrxx[i].xm)
            $("#bmmc").text(ajxxAndCbrxx[i].dmms)
            $("#lxfs").text(ajxxAndCbrxx[i].phone)
        }
    }
}

function submitTsyysd(){
    var yhm=$.cookie('yhm')
    // console.log(ajxh)
    // console.log(dsrbh)
    // console.log($("#ah").text())
    // console.log($("#yyr").text())
    var dsrlist=[]
    $("input[name='dsrs']:checked").each(function (i) {
        dsrlist.push($(this).val());
    });
    if(dsrlist.length==0){
        alert("未选中任何当事人");
        return;
    }
    if($("#ah").text()===''||$("#yyr").text()==='选择'){
        alert("请确认信息是否填写完整")
        return
    }
    var params={
        ajxh:ajxh,
        yhm:yhm,
        dsrlist:dsrlist,
        ah:$("#ah").text(),
        ajmc:$("#ajmc").text(),
        cbfg:$("#cbfg").text(),
        yyr:$("#yyr").text(),
        yybm:$("#bmmc").text(),
        fgdh:$("#lxfs").text(),
        ay:$("#laay").text()
    }
    $.ajax({
        url: "/tsyysd/submitTsyysd",
        type: 'post',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(params),
        success: function (data) {
            alert(data.message)
        }
    })
}