/**
 **
 **/
$(function () {
    var initUrl = "/o2o/shopadmin/getproduct";
    // var registerShopUrl = "/o2o/shopadmin/registershop";
    // var editShopUrl = "/o2o/shopadmin/updateshop";

    //1. queryforshopId
    //2. 如果是shopId!=null, 说明是update, 则先获取getShopById()
    //

    var isEdit = false;
    var productId = getQueryString("productid");
    if (productId != null) {
        isEdit = true;
    }
    if (isEdit) {
        getProduct(productId);
    } else {
        // getShopInitInfo();
    }

    // function getShopInitInfo() {
    //     $.getJSON(initUrl, function (res) {
    //         if (res.status == 0) {
    //             var tmpHtml = "";
    //             var tmpAreaHtml = "";
    //             res.data.shopCategoryList.map(function (item, index) {
    //                 tmpHtml += "<option value='" + item.shopCategoryId + "'>" + item.shopCategoryName + "</option>";
    //             });
    //             $("#shop_category").append(tmpHtml);
    //
    //             res.data.areaList.map(function (item, index) {
    //                 // tmpAreaHtml += '<option data-id=' + item.areaId + '>' + item.areaName + '</option>';
    //                 tmpAreaHtml += "<option value='" + item.areaId + "'>" + item.areaName + "</option>";
    //             });
    //             $("#area_category").append(tmpAreaHtml);
    //         }
    //     })
    // };

    function getProduct(productId) {
        var url = "/o2o/shopadmin/getproduct?productid="+productId;
        $.getJSON(url, function (res) {
            var status = res.status;
            if(status == 0) {
                var product = res.data.product;
                $("#product_name").val(product.productName);
                $("#priority").val(product.priority);
                $("#normal_price").val(product.normalPrice);
                $("#promotion_price").val(product.promotionPrice);
                $("#product_desc").val(product.productDesc);

                var productCategoryList = res.data.productCategoryList;
                var tmpHtml = "";
                productCategoryList.map(function (item, index) {
                    if (item.productCategoryId == product.productCategoryId) {
                        tmpHtml += "<option value='" + item.productCategoryId + "' selected='selected'>" + item.productCategoryName + "</option>";
                    } else {
                        tmpHtml += "<option value='" + item.productCategoryId + "'>" + item.productCategoryName + "</option>";
                    }
                });
                $("#product_category").append(tmpHtml);
                //
                // res.data.areaList.map(function (item, index) {
                //     if (item.areaId == shop.areaId) {
                //         tmpAreaHtml += "<option value='" + item.areaId + "' selected='selected'>" + item.areaName + "</option>";
                //     } else {
                //         tmpAreaHtml += "<option value='" + item.areaId + "'>" + item.areaName + "</option>";
                //     }
                // });
                // $("#area_category").append(tmpAreaHtml);
            }
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