
$(document).ready(function () {

    var xtyh = {
        fybh: "",
        yhm: ""

    };

    var url = location.search; //获取url中"?"符后的字串 ('?modFlag=business&role=1')
    if(url != ""){
        var theRequest = [];
        if (url.indexOf("?") != -1) {
            var str = url.substr(1); //substr()方法返回从参数值开始到结束的字符串；
            var strs = str.split("&");
            xtyh.fybh = strs[0].split("=")[1];
            xtyh.yhm = strs[1].split("=")[1];
            // console.log(xtyh);
            $.cookie('fybh',xtyh.fybh);
            $.cookie('yhm',xtyh.yhm);
        }
    }else{
        // console.log("url为空，查找cookies");
        xtyh.fybh = $.cookie('fybh');
        xtyh.yhm = $.cookie('yhm');
    }

    getCurrentFyName(xtyh.fybh);



    $.ajax({
        url: "getYhxxByYhm",
        type: 'get',
        success:function (data) {
            $("#judge_name").text(data);
        }
    });

    $.ajax({
        url: "getSdData",
        type: 'get',
        success:function (data) {
            $("#dh_sdaj").text(data.dhsdaj);
            $("#dh_sdrs").text(data.dhsdrs);
            $("#dz_sdaj").text(data.dzsdaj);
            $("#dz_sdrs").text(data.dzsdrs);
            $("#ems_sdaj").text(data.emssdaj);
            $("#ems_sdrs").text(data.emssdrs);
            $("#gg_sdaj").text(data.ggsdaj);
            $("#gg_sdrs").text(data.ggsdrs);

            $("#zj_sdaj").text(data.zjsdaj);
            $("#zj_sdrs").text(data.zjsdrs);
            $("#lylq_sdaj").text(data.lylqsdaj);
            $("#lylq_sdrs").text(data.lylqsdrs);
            $("#wt_sdaj").text(data.wtsdaj);
            $("#wt_sdrs").text(data.wtsdrs);
            $("#zhj_sdaj").text(data.zhjsdaj);
            $("#zhj_sdrs").text(data.zhjsdrs);
            $("#dxtz_sdaj").text(data.dxtzsdaj);
            $("#dxtz_sdrs").text(data.dxtzsdrs);



            $("#songda_sum").text(data.dhsdaj+data.dzsdaj+data.emssdaj+data.ggsdaj)
            $("#already_songda_sum").text(data.dhsdaj+data.dzsdaj+data.emssdaj+data.ggsdaj)
        }
    });


});


var nums = document.getElementsByClassName("formula_number");
for(var i=0;i<nums.length;i++){
    nums[i].innerHTML = demo(nums[i].innerHTML);
}

$("#lssd").on("click",function(){
    fybh=$.cookie('fybh')
    yhm=$.cookie('yhm')
    window.open("http://130.39.113.70:8088/jzsdpt.aj?fybh="+fybh+"&yhm="+yhm)
})
 $("#my_songda").on("click",function(){
        location.href="fgMySd";
    });
$("#sjtj").on("click",function(){
    location.href="statistics";
});

$("#dh_songda").on("click",function(){
    $.cookie('way',"DHSD");
    location.href="myAj_dh";
});

$("#dz_songda").on("click",function(){
    $.cookie('way',"DZSD");
    location.href="myAj_dz";
});

$("#ems_songda").on("click",function(){
    $.cookie('way',"EMSSD");
    location.href="myAj_ems";
});
$("#zhijie_songda").on("click",function(){
    $.cookie('way',"ZJSD");
    location.href="myAj_zj";
});

$("#gg_songda").on("click",function(){
    $.cookie('way',"GGSD");
    location.href="myAj_gg";
});

$("#lylq_songda").on("click",function(){
    location.href="fgLylqList";
});

$("#dxtz_songda").on("click",function(){
    location.href="fgDxtzList";
});

function getAjInfo() {
    $.ajax({
        url: "/getAjDsrInfos.do",
        success: function (data) {
            var userName = data.fgmc;
            $("#userName").append("<span>"+userName+"</span>");
        },error:function () {
            alert("获取案件信息失败")
        }
    });
}

$("#reportData").on("click",function(){
    window.open("report_data.htm?yhm=" + $.cookie('yhm'));
});

