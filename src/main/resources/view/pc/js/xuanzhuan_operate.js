

	var boxStyleCache = ["none", "none"];

	// pic 图片对象
	// type 类型 1 预览图片 2 关闭预览框
	// boxIndex 相框类型 1 ，2
	function showPic(pic, type, boxIndex){
		var box1 = document.getElementById("box1");
		var	box2 = document.getElementById("box2");

		if(type == 1){
			boxStyleCache[0] = box1.style.display;
			boxStyleCache[1] = box2.style.display;
			alert("box1:" + box1.style.display)
			alert("box2:" + box2.style.display)
			alert("boxStyleCache:" + boxStyleCache[0] + ", " + boxStyleCache[1])

			var showPic = document.getElementById("showPic");
			showPic.style.display = "block";
			showPic.style.height = showPic.style.width;
			box1.style.display = "none";
			box2.style.display = "none";
			var showPicImg = document.getElementById("showPicImg");
			showPicImg.setAttribute("src", pic.src);
		}else{
			var showPic = document.getElementById("showPic");
			showPic.style.display = "none";
			box1.style.display = boxStyleCache[0];
			box2.style.display = boxStyleCache[1];
		}
	}


	// pic 图片对象
	// type 类型 1 预览图片 2 关闭预览框
	// boxIndex 相框类型 1 ，2
	function showPicV2(pic, type, boxIndex){
		var box1 = document.getElementById("box1");
		var	box2 = document.getElementById("box2");

		if(type == 1){
			boxStyleCache[0] = box1.style.display;
			boxStyleCache[1] = box2.style.display;
			var showPic = document.getElementById("showPic");
			showPic.style.display = "block";
			showPic.style.height = "200px";
			showPic.style.width = "200px";
			box1.style.display = "none";
			box2.style.display = "none";
			var showPicImgDiv = document.getElementById("showPicImgDiv");
			var w = showPicImgDiv.style.width;
			var h = showPicImgDiv.style.height;
			showPicImgDiv.innerHTML =
				// "<img id='showPicImg'  src='" + pic.src + "'/>";
			"<img id='showPicImg' style='width: "+ w + "; height: " + h + ";' src='" + pic.src + "'/>";

		}else{
			var showPic = document.getElementById("showPic");
			showPic.style.display = "none";
			box1.style.display = boxStyleCache[0];
			box2.style.display = boxStyleCache[1];
		}
	}




	function myOnload(){
		var userName = getCookieByName("user");
		if(userName == null || userName.length == 0){
			return;
		}
		var ao = document.getElementById("musicPlayer");
		console.log("ao is null====>" + (ao == null));
		// 如果没播放音乐，直接加载图片，否则等音乐播放玩在加载图片
		if(ao.paused)                     {
			getPicByName(userName);
		}else{
			ao.addEventListener("ended", function () {
				console.log("======第一首音乐播放结束")
				getPicByName(userName);
			});
		}





	}


	function getCookieByName(name){
		var allcookiestr = document.cookie;
		var cookieArr = allcookiestr.split();
		for(var i=0; i<cookieArr.length; i++){
			var arr = cookieArr[i].split("=");
			if (arr[0] == name){
				console.log("当前登录用户："+ arr[1]);
				return arr[1];
			}
		}
	}

	function getPicByName(name){
		var imgs = document.getElementsByClassName("boxImg");

		var url = "/getPicByUserName?name=" + name;
		var xhr = GetXHR();
		// 定义回调函数
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4){
				// 已经接收到全部响应数据，执行以下操作
				data = xhr.responseText;
				console.log("======get pic by username res : " + data);
				if (data == null || data.length == 0){
					console.log("return data is null...");
					return;
				}

				var picArr = data.split(";");
				var len = picArr.length;
				if (len >= 1){
					for (var j=0; j<imgs.length; j++){
						imgs[j].src = picArr[j%len];
						if(j == (len - 1)){
							break;
						}
					}
				}
			}
		};
		xhr.open('get', url, false);
		xhr.send();

	}

	
	function chooseFile(){
		var file = document.getElementById("file");
		file.click();
	}
	
	function submitFile(){
	
		var fd = new FormData();
		var pic=document.getElementById("file").files[0];
		if(pic == null){
			alert("文件为空，请重新选择！！")
			return;
		}
		var type=pic.type;                                              //文件类型
	
		if(type!="image/jpeg"&&type!="image/png"){
			alert("请上传正确格式图片");
			return false;
		}
	
		var size=parseInt(pic.size)/1024/1024;             //文件大小（换算成 M）
	
		if(size>10){
			alert('文件过大');return false;
		}
	
		fd.append("file",pic);
		var xhr=new XMLHttpRequest();
		xhr.onreadystatechange=function(){
			if(xhr.readyState==4){
				alert(xhr.responseText);
				var userName = getCookieByName("user");
				if(userName == null || userName.length == 0){
					return;
				}
				getPicByName(userName);
			}
		}
		xhr.open("POST","/upload",true);
		xhr.send(fd);
	
	}


	var changeStyleMark = 1;

	function changeStyle() {
		changeStyleMark = changeStyleMark*(-1);
		var box1 = document.getElementById("box1");
		var box2 = document.getElementById("box2");
		if (changeStyleMark > 0){
			box1.style.display = "none";
			box2.style.display = "block";
		}else {
			box1.style.display = "block";
			box2.style.display = "none";
		}
	}

	var sayYouMark = 1;

	function sayYou() {
		sayYouMark = sayYouMark * (-1);
		var isShow ;

		if (sayYouMark > 0){
			isShow = "hidden";
			document.getElementById("saySpan").innerText = "点我试试";
		}else {
			isShow = "visible";
			document.getElementById("saySpan").innerText = "关闭弹幕";
		}
		var says = document.getElementsByClassName("say");
		for (var i=0; i<says.length; i++){
			says[i].style.visibility = isShow;
		}
	}


	function managerPicsV2() {

		var userName = getCookieByName("user");
		if(userName == null || userName.length == 0){
			alert("请先登录~~~");
			return;
		}

		var box1 = document.getElementById("box1");
		var	box2 = document.getElementById("box2");

		boxStyleCache[0] = box1.style.display;
		boxStyleCache[1] = box2.style.display;
		var showPic = document.getElementById("showPic");
		showPic.style.display = "block";
		showPic.style.width = "30%";
		showPic.style.height = showPic.style.width;
		box1.style.display = "none";
		box2.style.display = "none";
		var showPicImgDiv = document.getElementById("showPicImgDiv");
		showPicImgDiv.style.overflow="auto";
		showPicImgDiv.style.height=showPicImgDiv.style.width;

		var url = "/getPicByUserName?name=" + userName;
		var xhr = GetXHR();
		// 定义回调函数
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4){
				// 已经接收到全部响应数据，执行以下操作
				data = xhr.responseText;
				console.log("======get pic by username res : " + data);
				if (data == null || data.length == 0){
					console.log("return data is null...");
					alert("你还没有上传图片...")
					return;
				}

				var picArr = data.split(";");
				var len = picArr.length;
				var innerH = "";

				if (len >= 1){
					for (var i=0; i<len; i++){
						innerH = innerH + "<a href='javascript:void(0);' onclick ='delImg(\""+picArr[i]+"\")' title='点击删除'>" + "<img  src='" + picArr[i] + "' style='width: 32%; height: 32%;' /> </a>";
					}
				}
				if (innerH != ""){
					showPicImgDiv.innerHTML = innerH;
				}
			}
		};
		xhr.open('get', url, false);
		xhr.send();
	}


	// 音乐播放暂停，图片播放暂停
	function bgMusicCtrl() {
		var audio = document.getElementById('musicPlayer');
		if(audio!==null){
			//检测播放是否已暂停.audio.paused 在播放器播放时返回false.
			if(audio.paused)                     {
				audio.play();//audio.play();// 这个就是播放
				document.getElementById("bgmusicPNG").src="/pc/img/music.png";
			}else{
				audio.pause();// 这个就是暂停
				document.getElementById("bgmusicPNG").src="/pc/img/cmusic.jpg";
			}
		}
	}

	function delImg(imgPath) {
		alert("此功能暂无，敬请期待......");
	}

	// // 音乐播放结束事件，播放结束后，加载用户自己的图片
	// function ended() {
	// 	alert("end~~~")
	// }



	var showIndex = 1;
	function showFun(){
		console.log("showIndex = " + showIndex);
		if(showIndex == 1){
			console.log("展示单个图片");
			var test = document.getElementsByClassName("face");
			for(var i=0; i<test.length; i++){
				test[i].style.backgroundImage="url(/pc/img/2.jpg)";
			}
			var faces = document.getElementsByClassName("cube");
			for(var i=0; i<faces.length; i++){
				faces[i].style.display="";
			}

			document.getElementById("jsi-cherry-container").style.display = "none";
		}else{
			console.log("展示旋转相册");
			var faces = document.getElementsByClassName("cube");
			for(var i=0; i<faces.length; i++){
				faces[i].style.display="none";
			}

			document.getElementById("jsi-cherry-container").style.display = "block";
		}
		showIndex = showIndex * (-1);


	}

