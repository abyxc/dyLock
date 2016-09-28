function previewImage(file,code) {
    var num = Math.random();
    var nums=Math.random();
    var margin=$(".upload1 li").css("margin");
    var width=$(".upload-img img").width();
    $("<li id='preview" + num + "' style='"+margin+"'>  <img id='imghead" + num + "' class='imgBig' src=''> </li>").insertBefore($('.li-'+code));
    $("#fileName"+code).css('display', 'none');
    $('#div_forms'+code).append("<input accept='image/*' type='file' name='uploads' class='comment-pic-upd' id='fileName"+code+"' style='height:"+width+"px;' onchange='previewImage(this,"+code+")' >");
    var MAXWIDTH = 400;
    var MAXHEIGHT = 400;
    var li = document.getElementById('preview' + num);
    console.log(file,'file')
    if (file.files && file.files[0]) {
        li.innerHTML = '<img id=imghead' + num + '>';
        var img = document.getElementById('imghead' + num);
        img.onload = function () {
            var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
            img.width = width;
            img.height =width;
        }

        var reader1 = new FileReader();
        reader1.onload = function (evt) {
            img.src = evt.target.result;

        }
        reader1.readAsDataURL(file.files[0]);
    }
    else {
        var sFilter = 'filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
        file.select();
        var src = document.selection.createRange().text;
        li.innerHTML = '<img id=imghead' + num + '>';
        var img = document.getElementById('imghead' + num);
        img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
        var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
        status = ('rect:' + rect.top + ',' + rect.left + ',' + rect.width + ',' + rect.height);
        li.innerHTML = "<li id=divhead" + num + " style='width:" + rect.width + "px;height:" + rect.height + "px;margin-top:0px;margin-left:" + rect.left + "px;" + sFilter + src + "\"'></li>";
    }


}
function clacImgZoomParam(maxWidth, maxHeight, width, height) {
    var param = {top: 0, left: 0, width: width, height: height};
    if (width > maxWidth || height > maxHeight) {
        rateWidth = width / maxWidth;
        rateHeight = height / maxHeight;
        if (rateWidth > rateHeight) {
            param.width = maxWidth;
            param.height = Math.round(height / rateWidth);
        } else {
            param.width = Math.round(width / rateHeight);
            param.height = maxHeight;
        }
    }
    param.left = Math.round((maxWidth - param.width) / 2);
    param.top = Math.round((maxHeight - param.height) / 2);
    return param;
}