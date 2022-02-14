$(document).ready(function () {
    var yhm = $.cookie('yhm');
    $("#userName").append("<span>" + yhm + "</span>");
    getCurrentFyNameBySessionFybh();


});
$("#my_songda").on("click", function () {
    location.href = "mySd";
});

$("#zhijie_songda").on("click", function () {
    $.cookie('way', "zjsd");
    location.href = "zjsd_list?yhm=" + $.cookie('yhm');
});


