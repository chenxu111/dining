$(function () {
    var headlineListUrl = "/o2o/frontend/getheadlinelist";
    getList();
    function getList() {

        $.ajax({
            url: headlineListUrl,
            type: 'GET',
            dataType: "json",
            success: function (res) {
                var status = res.status;
                if (status == 0) {

                    swiperHtml = "";

                    var headlineList = res.data.headlineList;
                    headlineList.map(function (item, index) {
                        swiperHtml += '' + '<div class="swiper-slide img-wrap">'
                            + '<a href="' + item.lineLink
                            + '" external><img class="banner-img" src="' + item.lineImg
                            + '" alt="' + item.lineName + '"></a>' + '</div>';
                    });
                    $('.swiper-wrapper').html(swiperHtml);

                    //设定轮播图轮换时间为3秒
                    $(".swiper-container").swiper({
                        autoplay: 3000,
                        //用户对轮播图进行操作时，是否自动停止autoplay
                        autoplayDisableOnInteraction: false
                    });

                    var shopCategoryList = res.data.shopCategoryList;
                    var categoryHtml = '';
                    //遍历大类列表，拼接出俩俩(col-50)一行的类别
                    shopCategoryList.map(function(item, index) {
                        categoryHtml += ''
                            + '<div class="col-50 shop-classify" data-category='
                            + item.shopCategoryId + '>' + '<div class="word">'
                            + '<p class="shop-title">' + item.shopCategoryName
                            + '</p>' + '<p class="shop-desc">'
                            + item.shopCategoryDesc + '</p>' + '</div>'
                            + '<div class="shop-classify-img-warp">'
                            + '<img class="shop-img" src="' + item.shopCategoryImg
                            + '">' + '</div>' + '</div>';
                    });
                    //将拼接好的类别赋值给前端HTML控件进行展示
                    $('.row').html(categoryHtml);
                } else {
                    $.toast("get headline list fail");
                }
            }
        })
    };


    // $.init();
})