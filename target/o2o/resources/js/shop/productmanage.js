$(function () {
    var listUrl = "/o2o/shopadmin/listproduct";
    // var removeUrl = "/o2o/shopadmin/removeproductcategory?";
    // var addUrl = "/o2o/shopadmin/addproductcategorys";

    listProduct();
    function listProduct() {
        $.ajax({
            url: listUrl,
            type: 'GET',
            dataType: "json",
            success: function (res) {
                var status = res.status;
                if (status == 0) {
                    var tmpHtml = '<div class="row row-product-category now">';
                    var productList = res.data.productList;
                    productList.map(function (item, index) {
                        tmpHtml += '<div class="col-33">' + item.productName + '</div>' +
                            '<div class="col-20">' + (item.priority) + '</div>' +
                            '<div class="col-40"><a href="#" class="edit" data-id="' + item.productId + '">编辑</a>' +
                            '<a href="#" class="status" data-id="' + item.productId + '">下架</a>'+
                            '<a href="#" class="preview" data-id="' + item.productId + '">预览</a>'+
                            '</div>';
                    });
                    tmpHtml += '</div>';
                    $(".product_wrap").html(tmpHtml);
                } else {
                    $.toast("getProductListfail");
                }
            }
        })
    };

    <!--蒋class为product_wrap里面的a标签绑定点击事件 -->
    $(".product_wrap").
        on('click',
        'a',
        function(e){
            var target = $(e.currentTarget);
            if(target.hasClass('edit')){
                window.location.href="/o2o/shopadmin/productoperation?productid="+e.currentTarget.dataset_id;
            }else if(target.hasClass('status')){
                // changeItemStatus();
            }else if(target.hasClass('preview')){
                //
            }

    });

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