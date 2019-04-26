'use strict';

var rootUrl = "/java_s04/api/v1.1/";
var getPostsUrl = "/java_s04/api/v1.1/";
var getPhotoUrl = "/java_s04/api/v1.1/";

function initPage() {
	var newOption = $('<option>').val(0).text('指定しない').prop('selected', true);
	$('#postIdParam').append(newOption);
	makePostSelection('#postIdParam');
	findAll();
	makePostSelection('#postId');
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

	$('#employees').children().remove();

	if (data.length === 0) {
		$('#employees').append('<p>現在データが存在していません。</p>')
	} else {
		var table = $('<table>').attr('border', 1);
		table.append(headerRow);

		$.each(data, function(index, employee) {
			var row = $('<tr>');
			row.append($('<td>').text(employee.empId));
			row.append($('<td>').text(employee.name));
			row.append($('<td>').append(
					$('<button>').text("編集").attr("type","button").attr("onclick", "findById("+employee.id+')')
				));
			row.append($('<td>').append(
					$('<button>').text("削除").attr("type","button").attr("onclick", "deleteById("+employee.id+')')
				));
			table.append(row);
		});

		$('#employees').append(table);
	}
}

}