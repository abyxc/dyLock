function previewImage(file)
		{
	var file_N = $("#"+file.id).val();
	  var isIE = /msie/i.test(navigator.userAgent) && !window.opera;         
	    var fileSize = 0; 
//	    alert(file_N);
	    if(!isimg(file_N)){
	    	alert("上传照片只能为'.jpg'格式");
	    	return false;
	    }
//	    alert("wenjain :"+file_N);
	    if (isIE && !file.files) {      
	      var filePath = file.value;      
	      var fileSystem = new ActiveXObject("Scripting.FileSystemObject");         
	      var file = fileSystem.GetFile (filePath);      
	      fileSize = file.Size;     
	    } else {     
	     fileSize = file.files[0].size;      
	     }    
	     var size = fileSize / 1024;     
	     if(size>3000){   
	      alert("上传图片不能大于3M");   
	        return false;
	     }   
		var num = Math.random();
	 	 $('#div_Add'+file.id).append("<div id='preview"+num+"' class='imgBig'  style='margin: 0 auto;'>  <img id='imghead"+num+"' class='imgBig' src=''> </div>");
//	 	 $("#"+file.id).css('display','none');comment-pic-upd
	 	$("#"+file.id).css('display','none');
	 	$("#div_tc01").css('width','100%;');
	 	$('#div_forms'+file.id).append("<input accept='image/*' type='file' name='uploads' class='comment-pic-upd' id='"+file.id+"'  onchange='previewImage(this)' />");
		  var MAXWIDTH  = 170;
		  var MAXHEIGHT = 96;
		  var div = document.getElementById('preview'+num);
		  if (file.files && file.files[0])
		  {
		    div.innerHTML = '<img onclick="dels(this)" id=imghead'+num+'>';
		    var img = document.getElementById('imghead'+num);
		    img.onload = function(){
		      var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
		      img.width = 170;
		      img.height = 96;
		      img.style.marginLeft = 0;
		      img.style.marginTop = 0;
		    }
		    var reader = new FileReader();
		    reader.onload = function(evt){img.src = evt.target.result;}
		    reader.readAsDataURL(file.files[0]);
		  }
		  else
		  {
		    var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
		    file.select();
		    var src = document.selection.createRange().text;
		    div.innerHTML = '<img onclick="dels(this)" id=imghead'+num+'>';
		    var img = document.getElementById('imghead'+num);
		    img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
		    var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
		    status =('rect:'+rect.top+','+rect.left+','+rect.width+','+rect.height);
		    div.innerHTML = "<div id=divhead"+num+" style='width:"+rect.width+"px;height:"+rect.height+"px;margin-top:0px;margin-left:"+rect.left+"px;"+sFilter+src+"\"'></div>";
		  }
		  
		  
		}
		function clacImgZoomParam( maxWidth, maxHeight, width, height ){
		    var param = {top:0, left:0, width:width, height:height};
		    if( width>maxWidth || height>maxHeight )
		    {
		        rateWidth = width / maxWidth;
		        rateHeight = height / maxHeight;
		        if( rateWidth > rateHeight )
		        {
		            param.width =  maxWidth;
		            param.height = Math.round(height / rateWidth);
		        }else
		        {
		            param.width = Math.round(width / rateHeight);
		            param.height = maxHeight;
		        }
		    }
		    param.left = Math.round((maxWidth - param.width) / 2);
		    param.top = Math.round((maxHeight - param.height) / 2);
		    return param;
		}
		 function isimg(src)
	        {
	            var ext = ['.jpg'];
	            var s = src.toLowerCase();
	            var r = false;
	            for(var i = 0; i < ext.length; i++)
	            {
	                if (s.indexOf(ext[i]) > 0)
	                {
	                    r = true;
	                    break;
	                }
	            }   
	            return r;
	        }