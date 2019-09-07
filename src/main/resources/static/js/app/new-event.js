var event = {
	init: function(){
		var _this = this;
		$('#imsiSave').click(function(){
			_this.save();
		});
	},
	save: function(){
		var arr = $('#eventForm').serializeArray();
		var data = {};
		for (var i = 0; i < arr.length; i++){
			data[arr[i]["name"]] = arr[i]["value"];
		}
		$.ajax({
			method: 'post',
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
			error: function(jqXHR, textStatus, errorThrown){
				alert(jqXHR + " : " + textStatus + " : " + errorThrown);
			}
		});
	},
}

event.init();