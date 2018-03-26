/**
 **
 **/
$(function () {
    var initUrl = "/o2o/shopadmin/getshopinitinfo";
    var registerShopUrl = "/o2o/shopadmin/registershop";
    var editShopUrl = "/o2o/shopadmin/updateshop";

    //1. queryforshopId
    //2. 如果是shopId!=null, 说明是update, 则先获取getShopById()
    //

    var isEdit = false;
    var shopId = getQueryString("shopid");
    if (shopId != null) {
        isEdit = true;
    }
    if (isEdit) {
        getShopById(shopId);
    } else {
        getShopInitInfo();
    }

    function getShopInitInfo() {
        $.getJSON(initUrl, function (res) {
            if (res.status == 0) {
                var tmpHtml = "";
                var tmpAreaHtml = "";
                res.data.shopCategoryList.map(function (item, index) {
                    tmpHtml += "<option value='" + item.shopCategoryId + "'>" + item.shopCategoryName + "</option>";
                });
                $("#shop_category").append(tmpHtml);

                res.data.areaList.map(function (item, index) {
                    // tmpAreaHtml += '<option data-id=' + item.areaId + '>' + item.areaName + '</option>';
                    tmpAreaHtml += "<option value='" + item.areaId + "'>" + item.areaName + "</option>";
                });
                $("#area_category").append(tmpAreaHtml);
            }
        })
    };

    function getShopById(shopId) {
        var url = "/o2o/shopadmin/getshopbyid?shopid="+shopId;
        $.getJSON(url, function (res) {
            var status = res.status;
            $.toast("status " + status);
            var shop = res.data.shop;
            $("#shop_name").val(shop.shopName);
            $("#shop_addr").val(shop.shopAddr);
            $("#shop_phone").val(shop.shopPhone);
            $("#shop_desc").val(shop.shopDesc);

            var tmpHtml = "";
            var tmpAreaHtml = "";
            res.data.shopCategoryList.map(function (item, index) {
                if(item.shopCategoryId == shop.shopCategoryId) {
                    tmpHtml += "<option value='" + item.shopCategoryId + "' selected='selected'>" + item.shopCategoryName + "</option>";
                }else {
                    tmpHtml += "<option value='" + item.shopCategoryId + "'>" + item.shopCategoryName + "</option>";
                }
            });
            $("#shop_category").append(tmpHtml);

            res.data.areaList.map(function (item, index) {
                if(item.areaId == shop.areaId) {
                    tmpAreaHtml += "<option value='" + item.areaId + "' selected='selected'>" + item.areaName + "</option>";
                }else{
                    tmpAreaHtml += "<option value='" + item.areaId + "'>" + item.areaName + "</option>";
                }
            });
            $("#area_category").append(tmpAreaHtml);
        });
    }

    $("#submit").click(function () {
        $.toast("submit click!");
        var shop = {};
        shop.shopName = $("#shop_name").val();
        shop.shopAddr = $("#shop_addr").val();
        shop.phone = $("#shop_phone").val();
        shop.shopDesc = $("#shop_desc").val();
        shop.shopCategoryId = $("#shop_category").val();
        shop.areaId = $("#area_category").val();

        var shopImg = $("#shop_img")[0].files[0];
        var formData = new FormData();
        formData.append("shopImg", shopImg);
        formData.append("shopStr", JSON.stringify(shop));

        // 将数据提交至后台处理相关操作
        $.ajax({
            url: (isEdit ? editShopUrl : registerShopUrl),
            type: 'POST',
            data: formData,
            contentType: false,
            processData: false,
            cache: false,
            success: function (res) {
                if (res.status == 0) {
                    $.toast('提交成功！');
                    // if (!isEdit) {
                    // 若为注册操作，成功后返回店铺列表页
                    window.location.href = "/o2o/shopadmin/shoplist";
                    // }
                } else {
                    $.toast('提交失败！' + res.msg);
                }
                // 点击验证码图片的时候，注册码会改变
                $('#captcha_img').click();
            },
            fail: function (res) {
                $.toast(res);
            }
        });
    });

    $("#back").click(function(){
       $.toast("back clicked");
       window.history.back();
    });
})