$(function () {
    var listUrl = "/o2o/shopadmin/getproductcategorylist";
    var removeUrl = "/o2o/shopadmin/removeproductcategory?";
    var addUrl = "/o2o/shopadmin/addproductcategorys";

    getProductCategoryList();
    function getProductCategoryList() {
        $.ajax({
            url: listUrl,
            type: 'GET',
            dataType: "json",
            success: function (res) {
                var status = res.status;
                if (status == 0) {
                    var tmpHtml = '<div class="row row-product-category now">';
                    var productCategoryList = res.data.productCategoryList;
                    productCategoryList.map(function (item, index) {
                        tmpHtml += '<div class="col-33">' + item.productCategoryName + '</div>' +
                            '<div class="col-33">' + (item.priority) + '</div>' +
                            // '<div class="col-20">'+'<a href="/o2o/shopadmin/removeproductcategory?productcategoryid='+item.productCategoryId+'">删除</a></div>';
                            '<div class="col-33"><a href="#" class="button delete" data-id="' + item.productCategoryId + '">删除</a></div>'
                    });
                    tmpHtml += '</div>';
                    $(".category_wrap").html(tmpHtml);
                } else {
                    $.toast("getshopmanagement失败");
                }
            }
        })
    };

    // $("#new").click(function(){
    //     var tempHtml='<div class="row row-product-category temp">' +
    //         '<div class="col-33"><input class="category-input category" type="text" placeholder="分类名"></div>'
    //         +'<div class="col-33"><input class="category-input category" type="number" placeholder="优先级"></div>'
    //         +'<div class="col-33"><a href="#" class="button delete">删除</a></div>';
    //
    //     $(".category_wrap").append(tempHtml);
    // });
    $('#new')
        .click(
            function () {
                var tempHtml = '<div class="row row-product-category temp">'
                    + '<div class="col-33"><input class="category-input category" type="text" placeholder="分类名"></div>'
                    + '<div class="col-33"><input class="category-input priority" type="number" placeholder="优先级"></div>'
                    + '<div class="col-33"><a href="#" class="button delete">删除</a></div>'
                    + '</div>';
                $('.category_wrap').append(tempHtml);
            });

    $(".category_wrap").on('click', '.row-product-category.temp .delete', function (e) {
        console.log($(this).parent().parent());
        $(this).parent().parent().remove();
    })

    $(".category_wrap").on('click', '.row-product-category.now .delete', function (e) {
        var target = e.currentTarget;
        $.confirm('sure to remove?', function () {
            $.ajax({
                url: removeUrl + "productcategoryid=" + target.dataset.id,
                type: 'GET',
                success: function (res) {
                    var status = res.status;
                    if (status == 0) {
                        getProductCategoryList();
                    } else {
                        $.toast("delete category failed");
                    }
                }
            })
        })
    })

    $('#submit').click(function() {
        var tempArr = $('.temp');
        var productCategoryList = [];
        tempArr.map(function(index, item) {
            var tempObj = {};
            tempObj.productCategoryName = $(item).find('.category').val();
            tempObj.priority = $(item).find('.priority').val();
            if (tempObj.productCategoryName && tempObj.priority) {
                productCategoryList.push(tempObj);
            }
        });
        $.ajax({
            url : addUrl,
            type : 'POST',
            data : JSON.stringify(productCategoryList),
            contentType : 'application/json',
            success : function(res) {
                if (res.status==0) {
                    $.toast('提交成功！');
                    getProductCategoryList();
                } else {
                    $.toast('提交失败！');
                }
            }
        });
    });
})