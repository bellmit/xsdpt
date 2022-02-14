var list;
var status = 1;

$(document).ready(function () {

    yhjs = $.cookie('yhjs');
    var fybh = $.cookie('fybh');
    getCurrentFyName(fybh);
    getUserList();

});



//获取用户集合
function getUserList() {
    $.ajax({
        url: "/getUserList.do",
        type: 'post',
        success: function (data) {
            list = data;
            $("#user_table").dataTable().fnDestroy();
            show_table(data);
            $('#user_table').DataTable({
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
            });
        },error:function () {
            alert("获取用户列表信息失败")
        }
    });
}

function editUser(e){
    var index = $(e).attr('data-index');
    var obj = list[index];
    var fybh = $.cookie('fybh');
    if (fybh==-1){
        $("#org").css("display","block")
    }
    $("#yhbh").val(obj.yhbh);
    $("#yhdm").val(obj.yhdm);
    $("#yhkl").val(obj.yhkl);
    $("#yhmc").val(obj.yhmc);
    if (obj.yhjs=="admin"){
        $("#yhjs1").attr("checked","checked");
    }
    if (obj.yhjs=="user"){
        $("#yhjs2").attr("checked","checked");
    }
    if (obj.yhjs=="zjzy"){
        $("#yhjs3").attr("checked","checked");
    }
    $("#zx").val(obj.seatnumber);
    $("#selectOrg option[value='"+obj.fybh+"']").prop("selected",true);
    $("#mwwx").val(obj.entryshow);
    $("#xfwx").val(obj.cuccshow);
    $("#ydwx").val(obj.cmccshow);
    $("#lxdh").val(obj.lxdh);
    $("#zfygbs").val(obj.wwempid)
    $("#editUserModel").modal('show');
}

$("#submitEditUser").on("click",function () {
    var data = {
        yhbh: $("#yhbh").val().trim(),
        yhdm: $("#yhdm").val().trim(),
        yhkl: $("#yhkl").val().trim(),
        yhjs: $("input[type=radio]:checked").val(),
        seatnumber: $("#zx").val().trim(),
        yhmc: $("#yhmc").val().trim(),
        entryshow: $("#mwwx").val().trim(),
        cuccshow: $("#xfwx").val().trim(),
        cmccshow: $("#ydwx").val().trim(),
        lxdh: $("#lxdh").val().trim(),
        fybh: $("#selectOrg option:checked").val(),
        wwempid:$("#zfygbs").val().trim()
    }
    $.ajax({
        url: "/update_user.zf",
        type: 'post',
        headers:{'Content-type':'application/json'},
        dataType:"json",
        data: JSON.stringify(data),
        success: function (data) {
            alert("修改成功")
            $("#editUserModel").modal('hide');
            getUserList()
        },error:function () {
            alert("失败")
        }
    })
})

$("#add_user").on("click",function (){
    $("#addyhdm").val(""),
    $("#addyhkl").val(""),
    $("input[type=radio]:checked"),
    $("#addzx").val(""),
    $("#addyhmc").val(""),
    $("#addmwwx").val(""),
    $("#addlxdh").val(""),
    $("#addzfygbs").val("")
    $("#addUserModel").modal('show');
    var fybh = $.cookie('fybh');
    if (fybh==-1){
        $("#addorg").css("display","block")
    }
})

$("#submitAddUser").on("click",function () {
    if ($("#addzfygbs").val().trim()==""){
        alert("请输入智法员工标识！")
        return
    }
    if ($("#addyhdm").val().trim()==""){
        alert("请输入用户代码！")
        return
    }
    if ($("#addyhkl").val().trim()==""){
        alert("请输入用户密码！")
        return
    }
    if ($("input[type=radio]:checked").val()==""){
        alert("请选择用户角色！")
        return
    }
    var fybh = $("#addselectOrg option:checked").val()
    if ($("#addselectOrg option:checked").val()==""){
        fybh = $.cookie('fybh');
    }

    var data = {
        yhdm: $("#addyhdm").val().trim(),
        yhkl: $("#addyhkl").val().trim(),
        yhjs: $("input[type=radio]:checked").val(),
        seatnumber: $("#addzx").val().trim(),
        yhmc: $("#addyhmc").val().trim(),
        entryshow: $("#addmwwx").val().trim(),
        cuccshow: $("#addxfwx").val().trim(),
        cmccshow: $("#addydwx").val().trim(),
        lxdh: $("#addlxdh").val().trim(),
        fybh: fybh,
        wwempid:$("#addzfygbs").val().trim()
    }
    $.ajax({
        url: "/add_user.zf",
        type: 'post',
        headers:{'Content-type':'application/json'},
        dataType:"json",
        data: JSON.stringify(data),
        success: function (data) {
            if (data.code=="200"){
                alert("添加成功")
                $("#addUserModel").modal('hide');
                getUserList()
            }else {
                alert(data.msg || "异常")
            }
        },error:function () {
            alert("失败")
        }
    })
})

function show_table(data) {
    var test_data = {
        title: '基本例子',
        isAdmin: false,
        list: data,
    };
    var html = template('test', test_data);
    $("#user_tbody").empty().append(html);
}

$("#return_main").on("click", function () {
    if(yhjs=='admin'){
        location.href = "/wbryIndex"
    }else{
        location.href = "/gdIndex"
    }

});

