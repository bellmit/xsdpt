var list;
var sd_way;
var xtyh = {
    fybh: "",
    ajxh: "",
    yhm: ""
};
$(document).ready(function () {
    sd_way = $.cookie('way');
    xtyh.fybh = $.cookie('fybh');
    xtyh.yhm = $.cookie('yhm');

    getCurrentFyName(xtyh.fybh);

    getTargetJudgeAjInfos();
});





function getTargetJudgeAjInfos() {
    $.ajax({
        url: "/getTargetJudgeAjInfos.do",
        type:'POST',
        success: function (data) {
            list=data;
            $("#ajjbxx_table").dataTable().fnDestroy();
            show_table(data);
            $('#ajjbxx_table').DataTable();

        },error:function () {
            alert("获取案件信息失败")
        }
    });
}

function show_table(data) {
    var test_data = {
        title: '基本例子',
        isAdmin: true,
        list: data
    };
    var html = template('test', test_data);
    $("#ajjbxx_tbody").append(html);
}



$("#return_main").on("click",function () {
    location.href = "jzsdpt.aj?fybh="+xtyh.fybh+"&yhm="+xtyh.yhm;
});


function sd(e) {
    var index = $(e).attr('data-index');
    var ajxh = list[index].ajxh;
    $.ajax({
        type: 'post',
        url: 'getFgSdUrl.aj',
        data:{
            sdWay : sd_way,
            ajxh : ajxh
        },
        success: function (content) {
            if (content.success) {
                var url = content.object;
                window.open(url);
            }
            else {
                alert("获取送达地址失败");
            }
        }
    })
}
