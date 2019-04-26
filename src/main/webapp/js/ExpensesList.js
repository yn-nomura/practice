function init(){
	
	$.ajax({
		type:'GET',
		dataType:'json',
		url:'',
		data:requestQuery
	}).then(
			function(json){
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


function findAll() {
	console.log('findAll start.')
	$.ajax({
		type : "GET",
		url : rootUrl,
		dataType : "json",
		success : renderTable
	});
}

function renderTable(data) {
	var headerRow ='<tr>'+'<th>'+申請ID+'</th>'+'<th>'+申請日+'</th>'+'<th>'+更新日+'</th>'
	+'<th>'+申請者+'</th>'+'<th>'+申請項目名+'</th>'+'<th>'+金額+'</th>'+'<th>'+ステータス+'</th>'+'</tr>';
}