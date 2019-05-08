'use strict';

var rootUrl ="";

findAll();


function findAll(){
	console.log('findAll start.')
	$.ajax({
		type:  "GET",
		url: rootUrl,
		dataType: "json",
		success: renderTable

	});
}

function renderTable(data){
	var headerRow = '<tr><th>申請ID</th><th>申請日</th><th>更新日</th><th>申請者</th><th>タイトル</th><th>金額</th>'+
		'<th>ステータス</th></tr>';

	$('#expenses').children().remove();

	if(data,length === 0){
		$('expenses').append('<p>現在データが存在していません</p>')
	}else{
		var table=$('<table>').attr('border',1);
		table.append(headerRow);
		$.each(data, function(index, post){
			var row = $('<tr>');
			row.append($('<td>')).text();
			row.append($('<td>')).text();
			row.append($('<td>')).text();
			row.append($('<td>')).text();
			row.append($('<td>')).text();
			row.append($('<td>')).text();
			row.append($('<td>')).text();
			row.append($('<td>')).append(
					$('<button>').text("詳細表示").attr("type","button").attr("onclick")
					);
		});
		$('#expenses').append(table);
	}
}