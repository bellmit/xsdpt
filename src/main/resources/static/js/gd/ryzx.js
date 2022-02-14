var page=1
var total=0
var fybh=0
function show_table(data) {
    var test_data = {
        list: data
    };
    var htmlstr = template('test', test_data);
    $("#gdxx_tbody").empty().html(htmlstr);
}

function show_page() {
    var test_data = {
        page:page,
        total:total,
    };
    var htmlstr = template('pageBox', test_data);
    $("#page").empty().html(htmlstr);
}

window.onload = loadData('4')
window.onload = loadTotal()

function loadTotal() {
    var data = {
        scope: $(".layui-btn-normal").attr("data-id"),
        fybh:fybh
    }
    $.ajax({
        url: "getTotal.aj",
        type: 'POST',
        // contentType:'application/json',
        data: data,
        success: function (data) {
            total=data
            show_page()
        }, error: function () {
            //layer.msg("查询失败");
        }

    });

}
function next() {
    var data = {
        scope: $(".layui-btn-normal").attr("data-id"),
        page:page+1,
        fybh:fybh
    }
    $.ajax({
        url: "getDataByPage.aj",
        type: 'POST',
        // contentType:'application/json',
        data: data,
        success: function (data) {
            show_table(data);
            var table_info_html = "共" + data.length + "条数据";
            $("#table_info").empty().html(table_info_html);
            page=page+1
            show_page()
        }, error: function () {
            // layer.msg("查询失败");
        }

    });
}
function up() {
    var data = {
        scope: $(".layui-btn-normal").attr("data-id"),
        page:page-1,
        fybh:fybh
    }
    $.ajax({
        url: "getDataByPage.aj",
        type: 'POST',
        // contentType:'application/json',
        data: data,
        success: function (data) {
            show_table(data);
            var table_info_html = "共" + data.length + "条数据";
            $("#table_info").empty().html(table_info_html);
            page=page-1
            show_page()
        }, error: function () {
            // layer.msg("查询失败");
        }

    });
}
function loadData(scope1) {
    var data = {
        scope: scope1,
        page:page,
        fybh:fybh
    }
    $.ajax({
        url: "getDataByPage.aj",
        type: 'POST',
        // contentType:'application/json',
        data: data,
        success: function (data) {
            show_table(data);
            var table_info_html = "共" + data.length + "条数据";
            $("#table_info").empty().html(table_info_html);
        }, error: function () {
            //layer.msg("查询失败");
        }

    });
}

function switchTo(e, scope1) {
    $(".layui-btn").removeClass("layui-btn-normal")
    // document.getElementsByClassName("layui-btn layui-btn-primary").classList.remove()
    $(e).addClass("layui-btn-normal")
    page=1
    loadData(scope1)
    loadTotal()

    show_page()
}

function menu(val) {
    fybh=val
    page=1
    var data = {
        scope: $(".layui-btn-normal").attr("data-id"),
        page:page,
        fybh:fybh
    }
    $.ajax({
        url: "getDataByPage.aj",
        type: 'POST',
        // contentType:'application/json',
        data: data,
        success: function (data) {
            show_table(data);
            var table_info_html = "共" + data.length + "条数据";
            $("#table_info").empty().html(table_info_html);
            loadTotal()
        }, error: function () {
            // layer.msg("查询失败");
        }

    });
}