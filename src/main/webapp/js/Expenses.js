'use strict'

var rootUrl="/java_s04/api/v1.1/expenses";

findAll();

function findAll(){
	console.log('findAll start.')
	$.ajax({
		type: "GET",
		url: rootUrl,
		dataType: "json",
		success: renderTable
	});

	function renderTable(data){
		//確認
		console.log('返却値',data);
		var headerRow = '<tr><th>申請ID</th><th>申請日</th><th>更新日</th><th>申請者</th><th>タイトル</th><th>金額</th>'+
			'<th>ステータス</th></tr>';

		$('#expenses').children().remove();

		if(data.length === 0){
			$('#expenses').append('<p>現在データが存在していません</p>')
		}else{
			var table = $('<table>').attr('border',1);
			table.append(headerRow);
			console.log(data);
			$.each(data, function(index,expenses){
				var row = $('<tr>');
				row.append($('<td>').text(expenses.applicationId));
				row.append($('<td>').text(expenses.applicationDate));
				row.append($('<td>').text(expenses.updateDate));
				row.append($('<td>').text(expenses.name));
				row.append($('<td>').text(expenses.expensesName));
				row.append($('<td>').text(expenses.amountOfMoney));
				row.append($('<td>').text(expenses.statusName));
				row.append($('<td>').append(
						$('<button>').text("詳細表示").attr("type","button").attr("onclick")
						));
				table.append(row);
			});
			$('#expenses').append(table);
		}
	}

	function addExpenses(){
		console.log('addExpenses start.')
		$.ajax({
			type: "POST",
			contentType: "application/json",
			url: "rootUrl",
			dataType:"json",
			data: formToJson(),
			success: function(data,textStatus,jqXHR){
				alert('経費情報の登録に成功しました');
				findAll();
			},
			error: function(jqXHR,textStatus, errorThrown){
				alert('経費登録の登録に失敗しました')
			}
		})
	}

	function deleteById(id){
		console.log('delete start - id:'+id)
		$.ajax({
			type: "DELETE",
			url: rootUrl+'/'+id,
			success: function(){
				findAll();
				$('#expensesId').val('');
				$('#name').val('');
			}
		});
	}
}