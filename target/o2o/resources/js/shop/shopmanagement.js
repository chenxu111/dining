$(function(){
    var shopId = getQueryString('shopid');
    var url = "/o2o/shopadmin/getshopmanagement?shopId="+shopId;
    getShopInfo(shopId);

    function getShopInfo(shopId) {
        $.toast("getShopInfo" + shopId);
        $.ajax({
            url: url,
            type: 'GET',
            dataType: "json",
            success: function (res) {
                var status = res.status;
                if (status == 0) {
                    $.toast("getmanagementinfo success!");
                    var redirect = res.data.redirect;
                    if(redirect){
                        window.location.href=res.data.url;
                    }else {
                        var shopId = res.data.shopId;
                        $("#shop_info").attr("href","/o2o/shopadmin/shopoperation?shopid="+shopId);
                    }
                } else {

                    $.toast("getshopmanagement失败");
                }
            }
        })
    }

    // function getQueryString(name) {
    //     var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
    //     var r = window.location.search.substr(1).match(reg);
    //     if (r != null) {
    //         return unescape(r[2]);
    //     }
    //     return null;
    // }
})