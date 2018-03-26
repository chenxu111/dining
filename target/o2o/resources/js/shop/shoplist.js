$(function () {
    var shopListUrl = "/o2o/shopadmin/getshoplist";
    getShopList();
    function getShopList() {
        $.ajax({
            url: shopListUrl,
            type: 'GET',
            dataType: "json",
            // contentType:'application/json',
            success: function (res) {
                var status = res.status;
                if (status == 0) {
                    // $.toast("获取商店列表成功");
                    var tmpHtml = '<div class="row row-shop">';
                    var status = res.status;
                    var shopList = res.data.shopList;
                    res.data.shopList.map(function (item, index) {
                        tmpHtml += '<div class="col-40">'+ item.shopName + '</div>' +
                            '<div class="col-40">'+ shopStatus(item.enableStatus)+'</div>'+
                            '<div class="col-20">'+'<a href="/o2o/shopadmin/shopmanagement?shopid='+item.shopId+'">进入</a></div>';
                    });
                    tmpHtml+='</div>';
                    $("#shop-list").html(tmpHtml);
                } else {
                    $.toast("获取列表失败");
                }
            }
        })
    }

    function shopStatus(status){
        var statusStr='';
        if(status==0){
            statusStr="审核中";
        }else if(status==-1){
            statusStr="非法店铺";
        }else if(status==1){
            statusStr="通过审核";
        }
        return statusStr;
    }
})