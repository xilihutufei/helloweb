
	
	        function GetXHR(){
	            var xhr = null;
	            if(XMLHttpRequest){
	                xhr = new XMLHttpRequest();
	            }else{
	                xhr = new ActiveXObject("Microsoft.XMLHTTP");
	            }
	            return xhr;
	
	        }
	
	        function XhrPostRequest(){
	            var xhr = GetXHR();
	            // 定义回调函数
	            xhr.onreadystatechange = function(){
	                if(xhr.readyState == 4){
	                    // 已经接收到全部响应数据，执行以下操作
	                    var data = xhr.responseText;
	                    alert(data);
	                }
	            };
	            // 指定连接方式和地址----文件方式
	            xhr.open('POST', "/test", true);
	            // 设置请求头
	            xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded; charset-UTF-8');
	            // 发送请求
	            xhr.send('n1=1;n2=2;');
	        }
	
	        function XhrGetRequest(url){
	            var xhr = GetXHR();
	            // 定义回调函数
	            xhr.onreadystatechange = function(){
	                if(xhr.readyState == 4){
	                    // 已经接收到全部响应数据，执行以下操作
	                    data = xhr.responseText;
						alert("获取响应：" + data)

	                }
	            };
	            xhr.open('get', url, false);
	            xhr.send();
	        }
