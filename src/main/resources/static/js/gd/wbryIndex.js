$(document).ready(function () {
    var yhm =  $.cookie('yhm');
    // $("#userName").append("<span>"+yhm+"</span>");
    getCurrentFyNameBySessionFybh();
});


$("#my_songda").on("click",function(){
    location.href="mySd";
});

$("#tssd").on("click",function(){
    location.href="tsyysd";
});

$("#dh_songda").on("click",function(){
    $.cookie('way',"DHSD");
    location.href="myGd_dh";
});

$("#xtgl").on("click",function(){
    location.href="xtgl";
});

$("#ems_songda").on("click",function(){
    $.cookie('way',"EMSSD");
    location.href="myGd_ems";
});

$("#gg_songda").on("click",function(){
    $.cookie('way',"GGSD");
    location.href="myGd_gg";
});

$("#rwfp_songda").on("click",function(){
    location.href="wbryRwFp";
});
$("#lylq_songda").on("click",function(){
    $.cookie('way',"LYLQ");
    location.href="lylq_list?yhm=" + $.cookie('yhm');
});
$("#dxtz_songda").on("click",function(){
    $.cookie('way',"DXTZ");
    location.href="dxtz_list?yhm=" + $.cookie('yhm');
});

$("#dbrwList").on("click",function(){
    $.cookie('way',"DBRW");
    location.href="dbrw_list?yhm=" + $.cookie('yhm');
});

$("#qsgdcx").on("click",function(){
    window.open("qsgdcx.do")
});

$("#zhijie_songda").on("click",function(){
    $.cookie('way',"zjsd");
    location.href="zjsd_list?yhm=" + $.cookie('yhm');
});
$("#repairInfoList").on("click",function(){
    $.cookie('way',"repair");
    location.href="repair_info_list?yhm=" + $.cookie('yhm');
});

$("#reportData").on("click",function(){
    window.open("report_data.htm?yhm=" + $.cookie('yhm'));
});