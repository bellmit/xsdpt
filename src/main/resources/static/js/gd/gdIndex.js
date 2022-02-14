$(document).ready(function () {
   var yhm =  $.cookie('yhm');
    // $("#userName").append("<span>"+yhm+"</span>");
});
$("#my_songda").on("click",function(){
    location.href="mySd";
});

$("#dh_songda").on("click",function(){
    $.cookie('way',"DHSD");
    location.href="myGd_dh";
});
$("#tssd").on("click",function(){
    location.href="tsyysd";
});

$("#rwwt").on("click",function(){
    location.href="rwwt_list";
});

$("#ems_songda").on("click",function(){
    $.cookie('way',"EMSSD");
    location.href="myGd_ems";
});
$("#lylq_songda").on("click",function(){
    $.cookie('way',"LYLQ");
    location.href="lylq_list?yhm=" + $.cookie('yhm');
});
$("#dxtz_songda").on("click",function(){
    $.cookie('way',"DXTZ");
    location.href="dxtz_list?yhm=" + $.cookie('yhm');
});

$("#gg_songda").on("click",function(){
    $.cookie('way',"GGSD");
    location.href="myGd_gg";
});
$("#dbrwList").on("click",function(){
    $.cookie('way',"DBRW");
    location.href="dbrw_list?yhm=" + $.cookie('yhm');
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
