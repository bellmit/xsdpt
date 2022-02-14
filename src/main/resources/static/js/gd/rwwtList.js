$(document).ready(function () {
    getRwwtList();
    xtyh.fybh = $.cookie('fybh');
    xtyh.ajxh = $.cookie('ajxh');
    xtyh.yhm = $.cookie('yhm');
    yhjs = $.cookie('yhjs');

    getCurrentFyName(xtyh.fybh);
});
var xtyh = {
    fybh: "",
    ajxh: "",
    yhm: "",
};
function getRwwtList() {
    $.ajax({
        url: "/getRwwtList.aj",
        type:"post",
        data:{
            cljg:$("#select_wtzt").val()
        },
        success: function (data) {
            $("#rwwt_table").dataTable().fnDestroy();
            showTable('rwwt_tbody','rwwt_tbody_html',data);
            $('#rwwt_table').DataTable({
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
            alert("获取委托信息失败")
        }
    });
}
$("#return_main").on("click", function () {
    if(yhjs=='admin'){
        location.href = "/wbryIndex"
    }else{
        location.href = "/gdIndex"
    }
});
function info(e){
    var yysdbh = $(e).attr('data-index');
    $.cookie('yysdbh',yysdbh);
    window.open("wbrywdsd");
}

function cljg(e) {
    var rwwtid = $(e).attr('data-index');
    layer.confirm('选择委托处理结果', {
        btn: ['成功', '失败']
        , btn1: function (index, layero) {
            var dataJson = {
                rwwtid: rwwtid,
                cljg: 'Y'
            };
           upload_rwwtjg(dataJson);
            layer.close(index);
        }, btn2: function (index, layero) {
            var dataJson = {
                rwwtid: rwwtid,
                cljg: 'N'
            };
            upload_rwwtjg(dataJson);
            layer.close(index);

        }
    });
}

function upload_rwwtjg(dataJson) {
    $.ajax({
        url: "/uploadRwwtjg.aj",
        type:"post",
        data: dataJson,
        success: function (data) {
            getRwwtList();
        }, error: function () {
            alert("上传失败")
        }
    });
}