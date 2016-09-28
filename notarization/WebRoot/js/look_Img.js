/****************纯js图片放大弹层 lzy 2016年1月17日21：1：:！2:**************************/
function imgShow(_this){
		var src = _this.attr("src");
		src = src.replace('url_thumbnail','');
		$("#bigimg").attr("src", src);
		$("<img/>").attr("src", src).load(function(){
			var windowW = $(window).width();
			var windowH = $(window).height();
			var realWidth = this.width;
			var realHeight = this.height;
			var imgWidth, imgHeight;
			var scale = 0.9;
			
			if(realHeight>windowH*scale) {
				imgHeight = windowH*scale;
				imgWidth = imgHeight/realHeight*realWidth;
				if(imgWidth>windowW*scale) {
					imgWidth = windowW*scale;
				}
			} else if(realWidth>windowW*scale) {
				imgWidth = windowW*scale;
				imgHeight = imgWidth/realWidth*realHeight;
			} else {
				imgWidth = realWidth;
				imgHeight = realHeight;
			}
			$("#bigimg").css("width",imgWidth);
			
			var w = (windowW-imgWidth)/2;
			var h = (windowH-imgHeight)/2;
			$("#innerdiv").css({"top":h, "left":w});
			$("#outerdiv").fadeIn("fast");
		});
		$("#outerdiv").click(function(){
			$(this).fadeOut("fast");
		});
	}