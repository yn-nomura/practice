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
				var preTableElements='<tr>'+'<th>'+申請ID+'</th>'+'<th>'+申請日+'</th>'+'<th>'+更新日+'</th>'
				+'<th>'+申請者+'</th>'+'<th>'+申請項目名+'</th>'+'<th>'+金額+'</th>'+'<th>'+ステータス+'</th>'+'</tr>';

				for(var i=0;i<json.length;i++){
					var expensesElemens=json[i];

					tableElements+='<tr>';
					tableElements+='<td>'++'</td>'+'<td>'++'</td>'+'<td>'++'</td>'+'<td>'++'</td>'+'<td>'++'</td>'
					+'<td>'++'</td>'+'<td>'++'</td>';
					tableElements+='</tr>';

				}
				var marge = preTableElements+tableElements;
				$('#expensesList').append(marge);

			},function(XMLHttpRequest,textStatus,errorThrown){
				alert('データの通信に失敗しました')
			}



	);
}