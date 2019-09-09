var event = {
	init: function(){
		var _this = this;
		$('#imsiSave').click(function(){
			_this.save('N');
		});
	},
	save: function(flag){
		var arr = $('#eventForm').serializeArray();
		var data = {};
		for (var i = 0; i < arr.length; i++){
			data[arr[i]["name"]] = arr[i]["value"];
		}
		if(data["title"].length == 0){
			var currentDate = new Date();
			var date = currentDate.toLocaleDateString() + " ";
			var time = currentDate.toLocaleTimeString();
			data["title"] = date + time + " 에 임시저장 되었습니다."; 
		}
		data["saveYn"] = flag;
		$.ajax({
			type: 'POST',
			url: '/event/new',
			dataType: 'json',
			contentType: 'application/json; charset=utf-8',
			data: JSON.stringify(data),
			success: function(result){
				if(result > 0){
					alert('정상적으로 저장되었습니다.');
				} else {
					alert('저장에 실패하였습니다.');
				}
			},
			error:function(e){  
				alert(e.responseText);  
			}  
		});
	},
	read: function(){
		location.href = "/event/" + result;
	}
}

event.init();
