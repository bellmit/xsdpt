// ###################通用函数js#######################
var SYS_CODE_ARR = {
    SYS_REPAIR_STATUS: [{key:"RPBS002",val:"修复中"},{key:"RPBS003",val:"修复成功"},{key:"RPBS004",val:"修复失败"}], //修复状态
    SYS_HIS_DATA_LY:[{key:"HIS_YYSD",val:"历史工单"},{key:"HIS_CASE",val:"历史案件"}], //历史数据来源
    SYS_SEND_STATE:[{key:"1",val:"发送成功"},{key:"2",val:"发送失败"},{key:"0",val:"发送中"}], //短信下发状态
    SYS_FWZT:[{key:"0",val:"未访问"},{key:"1",val:"已访问"}], //短信访问状态
    SYS_DHZT:[{key:"0",val:""}, {key:"1",val:"有效-本人"}, {key:"2",val:"可联-非本人"}, {key:"3",val:"可联-关机"},
              {key:"4",val:"可联-停机"}, {key:"5",val:"可联-未接通"}, {key:"6",val:"可联-再跟进"}, {key:"7",val:"可联-正忙"},
              {key:"8",val:"空号"}, {key:"9",val:"呼叫受限"}, {key:"10",val:"可联-挂机"},
              {key:"11",val:"代理人接听"},{key:"12",val:"代理律师接听"}]

}
/**
 * 格式化时间
 * @param date
 * @param fmt
 * @returns {string}
 * @private
 */
function _formatDate(date) {
    var time = date.split('.')[0];
    return time.replace('T',' ');
}
function _formatDate1(date, fmt) {
    if (typeof(date) === "undefined" || date == null || date == '') {
        return "----";
    }

    if (typeof(date) === "string") {
        date = parseFloat(date);
    }

    date = new Date(date);
    if (typeof(fmt) === "undefined") {
        fmt = "yyyy-MM-dd hh:mm:ss";
    }
    if (/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (date.getFullYear() + '').substr(4 - RegExp.$1.length))
    }
    var o = {
        'M+': date.getMonth() + 1,
        'd+': date.getDate(),
        'h+': date.getHours(),
        'm+': date.getMinutes(),
        's+': date.getSeconds()
    }
    for (var k in o) {
        if (new RegExp("("+k+")").test(fmt)) {
            var str = o[k] + ''
            fmt = fmt.replace(RegExp.$1, RegExp.$1.length === 1 ? str : ('00' + str).substr(str.length));
        }
    }
    return fmt
}
/**
 * 字典属性转换
 * @param code
 * @param type
 * @param defaultCn 默认值
 * @private
 */
function _sysCodeToCn(code,type,defaultCn) {
    code = code + "";
    if(!defaultCn) defaultCn = "";
    if(!code) return defaultCn;

    var typeArr = SYS_CODE_ARR[type];
    if(!typeArr) return defaultCn;

    for (var i in typeArr) {
        var item = typeArr[i];
        if(code+"" === item.key){
            return item.val;
        }
    }
    return defaultCn;
}

/**
 * 生产随机假链接
 * @private
 */
function _randLink() {
    var Arr = ['q','w','e','r','t','y','u','i','o','p','a','s','d','f','g','h','j','k','l','z','x','c','v','b','n','m','Q','W','E','R','T','Y','U',
        'I','O','P','A','S','D','F','G','H','J','K','L','Z','X','C','V','B','N','M','1','2','3','4','5','6','7','8','9','0'];
    var lj = '';
    for (var i = 0;i<4;i++){
        var x = Math.floor(Math.random()*Arr.length);
        lj += Arr[x]
    }
    return "http://tjsd.tjcourt.gov.cn:8091/"+lj;
}

/**
 * 获得本日的开始日期和结束日期
 */
function _getDayStartDateAndEndDateRange(date) {
    var oneDayLong = 24*60*60*1000 ;
    var now = date || new Date();
    var weekRange = [_formatDate1(now,"yyyy-MM-dd"), _formatDate1(now,"yyyy-MM-dd")];
    return weekRange;
}
/**
 * 获得本周的开始日期和结束日期
 */
function _getWeekStartDateAndEndDateRange(date) {
    var oneDayLong = 24*60*60*1000 ;
    var now = date || new Date();
    var dayOfWeek = now.getDay() >= 1 ? now.getDay():7;
    var mondayTime = now.getTime() - (dayOfWeek-1)*oneDayLong;
    var sundayTime = now.getTime() + (7-dayOfWeek)*oneDayLong;
    var monday = new Date(mondayTime);
    var sunday = new Date(sundayTime);
    var weekRange = [_formatDate1(monday,"yyyy-MM-dd"), _formatDate1(sunday,"yyyy-MM-dd")];
    return weekRange;
}
/**
 *获得本月的开始日期和结束日期
 */
function _getMonthStartDateAndDateRange(date) {
    var oneDayLong = 24*60*60*1000;
    var now = date || new Date();
    var year = now.getFullYear();
    var monthStartDate = new Date(year, now.getMonth(), 1);//当前月1号
    var nextMonthStartDate = new Date(year, now.getMonth()+1, 1);//下个月1号
    var days = (nextMonthStartDate.getTime() - monthStartDate.getTime())/oneDayLong;//计算当前月份的天数
    var monthEndDate = new Date(year, now.getMonth(), days);
    var monthRange = [_formatDate1(monthStartDate,"yyyy-MM-dd"), _formatDate1(monthEndDate,"yyyy-MM-dd")];
    return monthRange;
}

