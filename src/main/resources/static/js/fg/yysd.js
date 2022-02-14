var ajxhs;
var fybh;
var bmmc;//部门名称
var bgdh;//办公电话
var sjhm;//手机号码
var tempSddzs=[];
var tempDhs=[];
var tempRow ;
var tempIndex;
var innerFile={};
var externalFile = [];
var uploadFile=[];
var xxqjws=[];
var ajlx;

$(document).ready(function () {

});

function getSdrxx() {
    $.ajax({
        url: "/getSdrxx.aj",
        type:"post",
        success: function (data) {
            bmmc = data.bmmc;
            bgdh = data.bgdh;
            sjhm = data.sjhm;
            fgmc = data.sdrmc;
            $("#fgmc").append("<span>"+fgmc+"</span>&nbsp;&nbsp;&nbsp;&nbsp;");
            var html1 ="<input style='width: ";
            var html2 ="%;background-color: rgb(249,249,249);border-style: solid;border-width: 1px;border-color: rgb(160,160,160);height: 2em' disabled value='";
            var html3 ="'>";
            $("#sdrmc").append(html1+"80"+html2+data.sdrmc+html3);
            $("#fymc").append(html1+"80"+html2+data.fymc+html3);
            $("#bmmc").append(html1+"80"+html2+data.bmmc+html3);
            $("#bgdh").append(html1+"80"+html2+data.bgdh+html3);
            $("#sjhm").append(html1+"80"+html2+data.sjhm+html3);

        },error:function () {
            alert("获取案件信息失败")
        }
    });
}

function operateFormatter(value, row, index) {
    return [
        '<button type="button" class="RoleOfEdit btn btn-default myButton btn-sm"><i class="layui-icon layui-icon-edit" style="color: #1E9FFF;"></i> </button>'
    ].join('');
}


window.operateEvents = {
    'click .RoleOfEdit': function (e, value, row, index) {
        var sddzs=[];
        if(row.sddz!=null){
            sddzs = row.sddz.split(";");
        }
        var dhs=[];
        if(row.dh!=null){
            dhs = row.dh.split(";");
        }
        tempSddzs = sddzs;
        tempDhs = dhs;
        tempRow = row;
        tempIndex = index;
        var test_data = {
            dsr: row,
            sddzs:sddzs,
            dhs:dhs
        }
        var html = template('dsrxx', test_data);

        layer.open({
            type: 1,
            title: false,
            area: ['700px', '450px'],
            closeBtn: 0,
            shadeClose: true,
            skin: 'yourclass',
            content: html
        });

    },
};

function addSddz() {
    html = "<input class='layui-input' id='newSddz' style='margin: 5% 5% 5% 5%;width: 90%'> </br> <button class='btn btn-primary' type='button' style='margin:5% 40% 5% 40%;text-align: center' onclick='saveNewSddz()'>保存</button>" ;
    layer.open({
        type: 1,
        title: "新增送达地址",
        area: ['500px', '300px'],
        closeBtn: 0,
        shadeClose: true,
        skin: 'yourclass',
        content: html
    });
}

function addDh() {
    html = "<input class='layui-input' id='newDh' style='margin: 5% 5% 5% 5%;width: 90%'> </br> <button class='btn btn-primary' type='button' style='margin:5% 40% 5% 40%;text-align: center' onclick='saveNewDh()'>保存</button>" ;
    layer.open({
        type: 1,
        title: "新增手机号码",
        area: ['500px', '300px'],
        closeBtn: 0,
        shadeClose: true,
        skin: 'yourclass',
        content: html
    });
}

function saveNewSddz() {
    parent.layer.close(parent.layer.index);
    $("#sddzTbody").append(" <tr><td width='15%'>地址"
        +(tempSddzs.length+1)
        +":</td><td width='60%'>"
        +$('#newSddz').val()
        +"</td>" +
        "<td width='25%'>" +
        "<button type=\"button\" class=\"btn btn-default myButton btn-sm\" data-index="+tempSddzs.length+"  onclick=\"editSddz(this)\"><i class=\"layui-icon layui-icon-edit\" style=\"color: #1E9FFF;\"></i> </button>" +
        "<button type=\"button\" class=\"btn btn-default myButton btn-sm\" data-index="+tempSddzs.length+"  onclick=\"deleteSddz(this)\"><i class=\"layui-icon layui-icon-delete\" style=\"color: red;\"></i> </button></td></tr>")
    tempSddzs.push($('#newSddz').val());
    showSddz();

}

function editSddz(e) {
    var index = $(e).attr('data-index');
    html = "<input class='layui-input' id='editedSddz' style='margin: 5% 5% 5% 5%;width: 90%' value='"+tempSddzs[index]+"'> </br> <button class='btn btn-primary' type='button' style='margin:5% 40% 5% 40%;text-align: center' onclick='saveEditSddz("+index+")'>保存</button>" ;
    layer.open({
        type: 1,
        title: "编辑送达地址",
        area: ['500px', '300px'],
        closeBtn: 0,
        shadeClose: true,
        skin: 'yourclass',
        content: html
    });
}

function deleteSddz(e){
    var index = $(e).attr('data-index');
    tempSddzs.splice(index,1);
    showSddz();
}

function saveEditSddz(i) {
    tempSddzs[i]=$("#editedSddz").val();
    parent.layer.close(parent.layer.index);
    showSddz();
}

function showSddz() {
    $("#sddzTbody").empty();
    for (var i = 0 ;i<tempSddzs.length; i ++){
        $("#sddzTbody").append(" <tr><td width='15%'>地址"
            +(i+1)
            +":</td><td width='60%'>"
            +tempSddzs[i]
            +"</td>" +
            "<td width='25%'>" +
            "<button type=\"button\" class=\"btn btn-default myButton btn-sm\" data-index="+i+"  onclick=\"editSddz(this)\"><i class=\"layui-icon layui-icon-edit\" style=\"color: #1E9FFF;\"></i> </button>" +
            "<button type=\"button\" class=\"btn btn-default myButton btn-sm\" data-index="+i+"  onclick=\"deleteSddz(this)\"><i class=\"layui-icon layui-icon-delete\" style=\"color: red;\"></i> </button></td></tr>")
    }

    var sddzstr='';
    for (var i=0;i<tempSddzs.length;i++){
        sddzstr+=tempSddzs[i];
        sddzstr+=";";
    }
    if(sddzstr.length>0){
        sddzstr=sddzstr.substring(0,sddzstr.length-1);
    }
    $("#dsrTable").bootstrapTable('updateCell', {
        index: tempIndex,       //行索引
        field: "sddz",       //列名
        value: sddzstr        //cell值
    })
}


function saveNewDh() {
    var phoneReg = "^\\d{10,12}$";
    if(!/^\d{10,12}$/.test($('#newDh').val())){
       alert("联系电话为10~12位纯数字");
       return;
    }
    parent.layer.close(parent.layer.index);
    $("#dhTbody").append(" <tr><td width='15%'>号码"
        +(tempDhs.length+1)
        +":</td><td width='60%'>"
        +$('#newDh').val()
        +"</td><td width='25%'>" +
        "<button type=\"button\" class=\"btn btn-default myButton btn-sm\" data-index="+tempDhs.length+"  onclick=\"editDh(this)\"><i class=\"layui-icon layui-icon-edit\" style=\"color: #1E9FFF;\"></i> </button>" +
        "<button type=\"button\" class=\"btn btn-default myButton btn-sm\" data-index="+tempDhs.length+"  onclick=\"deleteDh(this)\"><i class=\"layui-icon layui-icon-delete\" style=\"color: red;\"></i> </button></td></tr>")
    tempDhs.push($('#newDh').val());
    var dhstr='';
    for (var i=0;i<tempDhs.length;i++){
        dhstr+=tempDhs[i];
        dhstr+=";";
    }
    if(dhstr.length>0){
        dhstr=dhstr.substring(0,dhstr.length-1);
    }
    $("#dsrTable").bootstrapTable('updateCell', {
        index: tempIndex,       //行索引
        field: "dh",       //列名
        value: dhstr        //cell值
    })

}

function editDh(e) {
    var index = $(e).attr('data-index');
    html = "<input class='layui-input' id='editedDh' style='margin: 5% 5% 5% 5%;width: 90%' value='"+tempDhs[index]+"'> </br> <button class='btn btn-primary' type='button' style='margin:5% 40% 5% 40%;text-align: center' onclick='saveEditDh("+index+")'>保存</button>" ;
    layer.open({
        type: 1,
        title: "编辑电话号码",
        area: ['500px', '300px'],
        closeBtn: 0,
        shadeClose: true,
        skin: 'yourclass',
        content: html
    });
}
function deleteDh(e){
    var index = $(e).attr('data-index');
    tempDhs.splice(index,1);
    showDh(tempDhs);
}

function saveEditDh(i) {
    var phoneReg = "^\\d{10,12}$";
    if(!/^\d{10,12}$/.test($('#editedDh').val())){
        alert("联系电话为10~12位纯数字");
        return;
    }
    tempDhs[i]=$("#editedDh").val();
    parent.layer.close(parent.layer.index);
    showDh();
}

function showDh() {
    $("#dhTbody").empty();
    for (var i = 0 ;i<tempDhs.length; i ++){
        $("#dhTbody").append(" <tr><td width='15%'>号码"
            +(i+1)
            +":</td><td width='60%'>"
            +tempDhs[i]
            +"</td>" +
            "<td width='25%'>" +
            "<button type=\"button\" class=\"btn btn-default myButton btn-sm\" data-index="+i+"  onclick=\"editDh(this)\"><i class=\"layui-icon layui-icon-edit\" style=\"color: #1E9FFF;\"></i> </button>" +
            "<button type=\"button\" class=\"btn btn-default myButton btn-sm\" data-index="+i+"  onclick=\"deleteDh(this)\"><i class=\"layui-icon layui-icon-delete\" style=\"color: red;\"></i> </button></td></tr>")
    }
    var dhstr='';
    for (var i=0;i<tempDhs.length;i++){
        dhstr+=tempDhs[i];
        dhstr+=";";
    }
    if(dhstr.length>0){
        dhstr=dhstr.substring(0,dhstr.length-1);
    }
    $("#dsrTable").bootstrapTable('updateCell', {
        index: tempIndex,       //行索引
        field: "dh",       //列名
        value: dhstr        //cell值
    })
}

function addSdWs() {
    var dsrSelected =   $("#dsrTable").bootstrapTable('getSelections');
    if(dsrSelected.length == 0){
        alert("至少选择一个当事人");
        return;
    }
    //填充弹出层的系统内文书
    //规则:文书来源1的全部添加,文书来源2的取被选择当事人已有文书类型的并集且去重
    var addedWslx =[];
    for (var i = 0;i<dsrSelected.length ;i++){
        var dsrbh = dsrSelected[i].dsrbh;
        var wsList = innerFile.get(dsrbh+'');
        if(wsList!=null){
            for (var j=0;j<wsList.length;j++){
                if(addedWslx.indexOf(wsList[j].wslb)==-1){
                    addedWslx.push(wsList[j].wslb);
                }
            }
        }
    }
    var data = {
        title: '文书信息',
        isAdmin: true,
        list: addedWslx
    }
    var html = template('wsInfos',data);
    layer.open({
        type: 1,
        title: "添加送达文书",
        shadeClose: true,
        closeBtn: 1,
        area: ['1280px', '720px'],
        content: html,
        zIndex: 1
    })
    if(ajlx != '执行') {
        $("#tbsj").hide();
    }
    layui.use('upload', function(){
        var upload = layui.upload;
        //执行实例
        var uploadInst = upload.render({
            elem: '#xssc' ,
            accept:'file',
            acceptMime: 'application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.document, text/plain,application/pdf,image/*',
            auto:false,
            bindAction:'#submit',
            field:'wsnr',
            multiple:true,
            choose: function (obj){
                obj.preview(function (index,file,result) {
                    if($(".wslx").val()==''||$(".wslx").val()==null){
                        layer.msg("请先选择文件类型");
                        return;
                    }
                    if(file.size==0){
                        layer.msg("不能上传空文件");
                        return;
                    }
                    addExternalFile(file,result);
                    $("#sdwsTable").bootstrapTable('append',{wslb: $(".wslx").val(), wsmc: file.name, wsly: 3,ly:'个人上传', wslybh: 0,sfSelected:true,wsnr:result});

                })
            }
        });
    });
}

function addWs() {
    var dsrSelected =   $("#dsrTable").bootstrapTable('getSelections');
    for (var x=0 ;x<dsrSelected.length;x++) {
        $("input[name='wsCheckbox']:checked").each(function (i) {
            var wslb = $(this).val();
            var dsrWsList = innerFile.get(dsrSelected[x].dsrbh+'');
            var ws ;
            for (var y=0;y<dsrWsList.length;y++){
                if(dsrWsList[y].wslb==wslb){
                    ws = dsrWsList[y];
                }
            }
            uploadFile.push({
                wslb:ws.wslb,
                wsly:ws.wsly,
                dsrbh:dsrSelected[x].dsrbh,
                dsrmc:dsrSelected[x].dsrjc,
                wslybh:ws.wslybh,
                wsnr:null,
                qzbh:ws.qzbh,
                stampId:ws.stampId,
                ajxh:ws.ajxh,
                bz:ws.bz
            });
        });
        for (var j=0;j<externalFile.length;j++){
            uploadFile.push({
                wslb:externalFile[j].wslb,
                wsly:externalFile[j].wsly,
                dsrbh:dsrSelected[x].dsrbh,
                wsmc:externalFile[j].wsmc,
                dsrmc:dsrSelected[x].dsrjc,
                wsnr:externalFile[j].wsnr,
                qzbh:null,
                stampId:null
            });
        }
    }
    uploadFile.sort(compare);
    showTable("sdNrTbody","sdnr",uploadFile);
    externalFile = [];
    layer.closeAll();
}
function compare(object1,object2) {
    return object1.dsrbh-object2.dsrbh;
}
function deleteWs(e) {
    var index = $(e).attr('data-index');
    uploadFile.splice(index,1);
    showTable("sdNrTbody","sdnr",uploadFile);
}

function addExternalFile(file,wsnr) {
    externalFile.push({
        wslb: $(".wslx").val(),
        wsly:3,
        wsnr:wsnr,
        wsmc:file.name
    })
    showTable("xxqjws","externalFileTbody",externalFile);
}

function addDzjzFile(fileNmae,wsnr) {
    externalFile.push({
        wslb: $(".wslx").val(),
        wsly:5,
        wsnr:wsnr,
        wsmc:fileNmae
    })
    showTable("xxqjws","externalFileTbody",externalFile);
}

function addXxsd() {
    if($(".wslx").val()==null||$(".wslx").val()==''){
        alert("请先选择文件类型");
        return;
    }
    externalFile.push({
        wslb: $(".wslx").val(),
        wsly:4,
        wsnr:null,
        qzbh:null
    })
    showTable("xxqjws","externalFileTbody",externalFile);
}

function showSubmit(e) {

    var html = "<div style=\"float: left;font-size: 15px;color: rgb(0,169,238);margin-left: 30px;margin-top: 30px;\">目录名称:&nbsp&nbsp<textarea id=\"mlmc\" style='width:232px;height:144px'></textarea></div>\n" +
        "        <div style=\"text-align:center;margin-top: 60px;margin-left:50px\">\n" +
        "          <button id=\"button_sdcg\" class=\"btn btn-primary\" style=\"float: left;margin-left: 30%;margin-top: 10px\" onclick=\"cxSubmit("+'ah'+")\">查询</button>\n" +
        "        </div>";
    layer.open({
        type: 1,
        title: "输入查询文书名称",
        shadeClose: true,
        area: ['300px', '350px'],
        content: html
    })
}

function cxSubmit(ah) {

    if($("#mlmc").val()!=null&&$("#mlmc").val().length>100){
        alert("最多允许输入100字");
        return;
    }
    $.ajax({
        url: "/gatewayFindByMlmc.do",
        type: "post",
        data: {
            ah: ah,
            mlmc: $("#mlmc").val(),
            fybh:fybh
        },
        success: function (data) {
            if(data.length == 0) {
                alert("查询结果为空");
            } else {
                layer.close(layer.index);
                $(".case-no").html(ah);
                $("#mlmcModel").modal('show');
                $("#tel-tbody").empty();
                for (var i = 0; i < data.length; i++) {
                    var htmlStr = "<tr><td><input type=\"checkbox\" data-hint1='" + data[i].wjid + "' data-hint2='" + data[i].wjssml + "' data-hint3='" + data[i].wjmc + "' name='mlmc-checkbox'></td>" ;
                    htmlStr+="  <td>" + data[i].wjssml + "</td>";
                    htmlStr+="  <td>" + data[i].wjmc + "</td></tr>";
                    $("#tel-tbody").append(htmlStr);
                }
            }
            return;

        }, error: function () {
            alert("查询失败");
            return;

        }
    })
}

$('input[name="dxtz-tel-checkbox-checkAll"]').on("click", function () {
    if ($(this).is(':checked')){
        $(":checkbox[name='mlmc-checkbox']").each(function () {
          $(this).prop("checked",true);
        })
    }else{
        $(":checkbox[name='mlmc-checkbox']").each(function () {
            $(this).prop("checked",false);
        })
    }
    // $("input[name='dxtz-tel-checkbox']").prop("checked", $(this).is(":checked"))
})
function tbsj() {
    var mes = layer.msg("正在处理，请耐心等待",{
        icon: 1,
        time: false
    });
    $.ajax({
        url: "/tbsj.aj?fybh="+ fybh +"&ah="+ah,
        type:"get",
        success: function (data) {
            layer.close(mes)
            alert("同步成功,请刷新界面")
            layer.closeAll();
        },error:function () {
            layer.close(mes)
            alert("同步信息失败")
        }
    });
}
var hint1;
var hint2;
var hint3;
function dzjztjws() {
    var mark = 0;
    $("input[name='mlmc-checkbox']:checked").each(function (i) {
        hint1 = $(this).attr('data-hint1');
        hint2 = $(this).attr('data-hint2');
        hint3 = $(this).attr('data-hint3');
        externalFile.push({
            wslb: hint2,
            wsly:5,
            wsnr:hint1,
            qzbh:null,
            wsmc:hint3
        })
        showTable("xxqjws","externalFileTbody",externalFile);
        mark = 1;
        // $("#sdwsTable").bootstrapTable('append',{wslb: hint2, wsmc: hint3, wsly: 5,ly:'电子卷宗', wslybh: 0,sfSelected:true,wsnr:data});
        // $.ajax({
        //     url: "/downloadFromDzjz.do",
        //     type: "post",
        //     data: {
        //         wjid: hint1,
        //         fybh:fybh
        //     },
        //     success: function (data) {
        //         addDzjzFile($(this).attr('data-hint3'),data);
        //         $("#sdwsTable").bootstrapTable('append',{wslb: hint2, wsmc: hint3, wsly: 5,ly:'电子卷宗', wslybh: 0,sfSelected:true,wsnr:data});
        //     }, error: function () {
        //         alert($(this).attr('data-hint3') + "下载失败");
        //         return;
        //
        //     }
        // })
    })
    if(mark == 1) {
        alert("添加完成");
        $("#mlmcModel").modal('hide');
    } else {
        alert("请选择至少一个文件进行添加");
    }
}
function checkboxOnclick(e) {
    var dsrSelected =   $("#dsrTable").bootstrapTable('getSelections');
    var uncheckMessage = '';
    for(var i=0;i<dsrSelected.length;i++){
        var dsrbh = dsrSelected[i].dsrbh;
        var wsList = innerFile.get(dsrbh+'');
        var hasQz = false;
        for(var j=0;j<wsList.length;j++){
            if(wsList[j].wslb==$(e).val()){
                hasQz=true;
                break;
            }
        }
        if(!hasQz){
            uncheckMessage=uncheckMessage+"当事人:"+dsrSelected[i].dsrjc+"的"+$(e).val()+"还未签章 \n";
        }
    }
    if(uncheckMessage!=''){
        $(e).prop('checked',false);
        alert(uncheckMessage);
    }
}




function fgMyIndex() {
    $.ajax({
        url: "/getFgIndex.aj",
        type:"post",
        success: function (data) {
            var url = data.object;
            window.open(url);
        }
    })
}
