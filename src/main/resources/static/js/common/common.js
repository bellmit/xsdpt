$(function () {

    $('#logout').click(function () {
        layer.confirm('确定退出系统吗', {
            btn: ['确定', '取消'] //按钮
        }, function () {
            /**
             * chrome会有问题，暂无解决方案
             * @type {string}
             */
            clearAllCookie();
            window.location.href = "/index.do";
        }, function () {

        });
    })
})

window.onload=function getCurrentFyName(fybh) {

    $.ajax({
        url: "/getCurrentFyName.aj",
        type: 'post',
        data: {
            fybh:fybh
        },
        success: function (data) {
            $("#current_fymc").text(data);
        }
    });

}
function clearAllCookie() {
    var keys = document.cookie.match(/[^ =;]+(?=\=)/g);
    if(keys) {
        for(var i = keys.length; i--;)
            document.cookie = keys[i] + '=0;expires=' + new Date(0).toUTCString()
    }
}
function showTable(tableId,templateId,data){
    var  showData = {
        list: data
    }
    var html = template(templateId,showData);
    var id = "#"+tableId;
    $(id).empty().append(html);
}

function Map() {
    this.elements = new Array();
// 获取Map元素个数
    this.size = function() {
        return this.elements.length;
    },
// 判断Map是否为空
        this.isEmpty = function() {
            return (this.elements.length < 1);
        },
// 删除Map所有元素
        this.clear = function() {
            this.elements = new Array();
        },
// 向Map中增加元素（key, value)
        this.put = function(_key, _value) {
            if (this.containsKey(_key) == true) {
                if (this.containsValue(_value)) {
                    if (this.remove(_key) == true) {
                        this.elements.push({
                            key : _key,
                            value : _value
                        });
                    }
                } else {
                    this.elements.push({
                        key : _key,
                        value : _value
                    });
                }
            } else {
                this.elements.push({
                    key : _key,
                    value : _value
                });
            }
        },
// 向Map中增加元素（key, value)
        this.set = function(_key, _value) {
            if (this.containsKey(_key) == true) {
                if (this.containsValue(_value)) {
                    if (this.remove(_key) == true) {
                        this.elements.push({
                            key : _key,
                            value : _value
                        });
                    }
                } else {
                    this.elements.push({
                        key : _key,
                        value : _value
                    });
                }
            } else {
                this.elements.push({
                    key : _key,
                    value : _value
                });
            }
        },
// 删除指定key的元素，成功返回true，失败返回false
        this.remove = function(_key) {
            var bln = false;
            try {
                for (i = 0; i < this.elements.length; i++) {
                    if (this.elements[i].key == _key) {
                        this.elements.splice(i, 1);
                        return true;
                    }
                }
            } catch (e) {
                bln = false;
            }
            return bln;
        },
// 删除指定key的元素，成功返回true，失败返回false
        this.deleteMap = function(_key) {
            var bln = false;
            try {
                for (i = 0; i < this.elements.length; i++) {
                    if (this.elements[i].key == _key) {
                        this.elements.splice(i, 1);
                        return true;
                    }
                }
            } catch (e) {
                bln = false;
            }
            return bln;
        },
// 获取指定key的元素值value，失败返回null
        this.get = function(_key) {
            try {
                for (i = 0; i < this.elements.length; i++) {
                    if (this.elements[i].key == _key) {
                        return this.elements[i].value;
                    }
                }
            } catch (e) {
                return null;
            }
        },
// set指定key的元素值value
        this.setValue = function(_key, _value) {
            var bln = false;
            try {
                for (i = 0; i < this.elements.length; i++) {
                    if (this.elements[i].key == _key) {
                        this.elements[i].value = _value;
                        return true;
                    }
                }
            } catch (e) {
                bln = false;
            }
            return bln;
        },
// 获取指定索引的元素（使用element.key，element.value获取key和value），失败返回null
        this.element = function(_index) {
            if (_index < 0 || _index >= this.elements.length) {
                return null;
            }
            return this.elements[_index];
        },
// 判断Map中是否含有指定key的元素
        this.containsKey = function(_key) {
            var bln = false;
            try {
                for (i = 0; i < this.elements.length; i++) {
                    if (this.elements[i].key == _key) {
                        bln = true;
                    }
                }
            } catch (e) {
                bln = false;
            }
            return bln;
        },
// 判断Map中是否含有指定key的元素
        this.has = function(_key) {
            var bln = false;
            try {
                for (i = 0; i < this.elements.length; i++) {
                    if (this.elements[i].key == _key) {
                        bln = true;
                    }
                }
            } catch (e) {
                bln = false;
            }
            return bln;
        },
// 判断Map中是否含有指定value的元素
        this.containsValue = function(_value) {
            var bln = false;
            try {
                for (i = 0; i < this.elements.length; i++) {
                    if (this.elements[i].value == _value) {
                        bln = true;
                    }
                }
            } catch (e) {
                bln = false;
            }
            return bln;
        },
// 获取Map中所有key的数组（array）
        this.keys = function() {
            var arr = new Array();
            for (i = 0; i < this.elements.length; i++) {
                arr.push(this.elements[i].key);
            }
            return arr;
        },
// 获取Map中所有value的数组（array）
        this.values = function() {
            var arr = new Array();
            for (i = 0; i < this.elements.length; i++) {
                arr.push(this.elements[i].value);
            }
            return arr;
        };
    /**
     * map遍历数组
     * @param callback [function] 回调函数；
     * @param context [object] 上下文；
     */
    this.forEach = function forEach(callback,context){
        context = context || window;
//IE6-8下自己编写回调函数执行的逻辑
        var newAry = new Array();
        for(var i = 0; i < this.elements.length;i++) {
            if(typeof callback === 'function') {
                var val = callback.call(context,this.elements[i].value,this.elements[i].key,this.elements);
                newAry.push(this.elements[i].value);
            }
        }
        return newAry;
    }
}
function changeWtfs(e) {
    var wtfs = $('#'+$(e).attr('data-id'));
    var selectedWtclr = $('#'+$(e).attr('data-change'));
    $.ajax({
        url: "/query_group.aj",
        type: 'post',
        data: {
            groupName:wtfs.val(),
            fybh:xtyh.fybh
        },
        success: function (data) {
            selectedWtclr.html("<option value=''>请选择 </option>");
            for (var i = 0; i < data.length; i++) {
                selectedWtclr.append("<option value=" + data[i].ryid + ">" +  data[i].rymc + "</option>");
            }
            selectedWtclr.selectpicker("refresh");
        }, error: function () {
            alert("获取分组失败")
        }
    });
}

function getNowTime() {
    return getToday('yyyy-MM-dd');
}

function getLastMonthTime() {
    return getMonthAgoDate('yyyy-MM-dd');
}

function getAdayTime() {
    return getAdayAgoDate('yyyy-MM-dd');
}

//获取一个月前的日期
function getMonthAgoDate(format) {
    return getSpecificDate(new Date(), format, -30);
}

function getAdayAgoDate(format) {
    return getSpecificDate(new Date(), format, -1);
}

//获取当天日期 yyyy-MM-dd
function getToday(format) {
    return getSpecificDate(new Date(), format, 0)
}

/**
 * 获取指定的日期
 * @param date 日期
 * @param format 格式
 * @param diffDays 和当前日期的差值
 */
function getSpecificDate(date, format, diffDays) {
    var targetDate = new Date(date.getTime() + diffDays * 24 * 3600 * 1000);
    if (format == null) {
        format = 'yyyy-MM-dd'
    }
    return dateFormat2(targetDate, format)
}

/**
 * 日期格式化
 * @param date 日期类型
 * @param format 格式
 * @returns {*}
 */
function dateFormat2(date, format) {
    var list = [
        {field: 'year', val: date.getFullYear(), replace: 'yyyy'},
        {field: 'month', val: date.getMonth() + 1, replace: 'MM'},
        {field: 'day', val: date.getDate(), replace: 'dd'},
        {field: 'hour', val: date.getHours(), replace: 'HH'},
        {field: 'minute', val: date.getMinutes(), replace: 'mm'},
        {field: 'second', val: date.getSeconds(), replace: 'ss'},
    ];
    var formatDate = format;
    for (var i = 0; i < list.length; i++) {
        formatDate = formatDate.replace(list[i].replace, list[i].val)
    }
    return formatDate
}

function getCurrentFyName(fybh) {

    $.ajax({
        url: "/getCurrentFyName.aj",
        type: 'post',
        data: {
            fybh:fybh
        },
        success: function (data) {
            $("#current_fymc").html(data);
        }
    });

}

function getCurrentFyNameBySessionFybh() {
    var mc=$.cookie("mc")
    $("#userName").append("<span>"+mc+"</span>")
    $.ajax({
        url: "/getCurrentFyNameBySessionFybh.aj",
        type: 'post',
        success: function (data) {
            $("#current_fymc").html(data);
        }
    });
}

function loadGdxqxx(yysdbh) {
    $.ajax({
        type: 'post',
        url: '/getGdxqxx.aj',//获取工单详情信息
        data: {
            yysdbh: yysdbh
        },
        success: function (content) {
            //转换时间格式
            xqxx = content.object;
            content.object.jb.yysj = formatDate(content.object.jb.yysj);
            var test_data = {
                gdxqxx: content.object,
                isAdmin : 0
            }
            var html = template('gdxqxx', test_data);
            layer.open({
                type: 1,
                title: false,
                closeBtn: 1,
                shadeClose: true,
                area: ['800px', '600px'],
                content: html
            });
        }
    })
}

function formatDate(e) {
    var date = new Date(e);
    Y = date.getFullYear() + '-';
    M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
    D = date.getDate() + ' ';
    h = date.getHours() + ':';
    m = date.getMinutes() + ':';
    s = date.getSeconds();
    var dateFormat = Y + M + D + h + m + s;
    return dateFormat;

}