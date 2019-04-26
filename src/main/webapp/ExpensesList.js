function executeAjax () {
	'use strict';
	var applicant = '1';

	var requestQuery = {Parameter : applicant};
	console.log(requestQuery);

	$.ajax({
		type:'GET',
		dataType:'json',
		url:'',
		data:requestQuery
	}).then(
			function(json){
				console.log('返却値',json);
				var tableElements ='';
				var syainBusyo='';
				var label='<tr>'+'<td>'+'ID'+'</td>'+'<td>'+'部署名'+'</td>';
				var sylabel='<option value='+'0'+'>'+'選択してください'+'</option>'
				$('#js-department').append(label);
				$('#syainBusyo').append(sylabel);
				for(var i=0;i<json.length;i++){
					var department=json[i];

					tableElements+='<tr>';
					var num = department.departmentCategory;
					var name = department.department;
					tableElements+='<td>'+num+'</td>'+'<td>'+name+'</td>';

//					tableElements+='<td>'+'<input type="button" value="編集" class = "departmentEdit" id = de'+ (i+1) +'>'
//					+'</td>'+'<td>'+'<input type="button" value="削除" class = "departmentDelete" id = dd'+ (i+1) +' >'+'</td>';
					tableElements+='<td>'+'<button class="departmentEdit" id=de'+num+'>'+'編集'+'</button>'
					+'<td>'+'<button class="departmentDelete" id=de'+num+'>'+'削除'+'</button>'

					tableElements+='</tr>';

//					部署データを社員編集ページに送る
					syainBusyo+='<option value='+num+'>'+name+'</option>';
				}
				console.log('tableElements:',tableElements);
				$('#js-department').append(tableElements);

				console.log('部署データ',syainBusyo);
				$('#syainBusyo').append(syainBusyo);

				setClickDelBtn();
				setClickEditBtn();
				editBtn();




			},function(XMLHttpRequest,textStatus,errorThrown){
				alert('データの通信に失敗しました')
			}



	);
}