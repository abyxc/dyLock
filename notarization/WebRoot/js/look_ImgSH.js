/****************绾痡s鍥剧墖鏀惧ぇ寮瑰眰 lzy 2016骞�鏈�7鏃�1锛�锛�锛�:**************************/
function imgShow(src){
		if(Number(src.length-1) == Number(src.lastIndexOf('/'))){
			src = src.substring(0,src.length-1);
		}
		$("#bigimg").attr("src", src);
		$("<img/>").attr("src", src).load(function(){
			var windowW = $(window).width();
			var windowH = $(window).height();
			var realWidth = this.width;
			var realHeight = this.height;
			var imgWidth, imgHeight;
			var scale = 0.8;
			
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
			$('.details_div').hide();
			$(this).fadeOut("fast");
		});
	}