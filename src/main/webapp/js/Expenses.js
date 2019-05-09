'use strict'

var rootUrl="/java_s04/api/v1.1/expenses";

findAll();

$('#saveExpense').click(function() {

	var id = $('#eaId').val()
	if (id == '')
		addExpenses();
	else
		updateExpenses(expensesId);
	return false;
})


$('#expenseDetail').click(function(){
	var id = q;
	findDetail(id);
});

$('#newExpense').click(function(){
		renderDetails({});

});


function findAll(){
	console.log('findAll start.')
	$.ajax({
		type: "GET",
		url: rootUrl,
		dataType: "json",
		success: renderTable
	});
}

function findById(id){
	console.log('findById Start - id:'+id);
	$.ajax({
	type: "GET",
	url: rootUrl+'/'+id,
	dataType:'json',
	success:function(data){
		console.log('findById success:' +data);
		renderDetails(data)
	}
	});
}

function addExpenses(){
	console.log('addExpenses start.')
	$.ajax({
		type: "POST",
		contentType: "application/json",
		url: rootUrl,
		dataType:"json",
		data: formToJSON(),
		success: function(data,textStatus,jqXHR){
			alert('経費情報の登録に成功しました');
			$('#eaId').val(data,id);
			findAll();
		},
		error: function(jqXHR,textStatus, errorThrown){
			alert('経費登録の登録に失敗しました')
		}
	});
}

function updateExpense(expensesId){
	console.log('updateExpense start');
	$.ajax({
		type: "PUT",
		contentType: "application/json",
		url: rootUrl+'/'+expensesId,
		dataType: "json",
		data: formToJSON(),
		success: function(data,textStatus,jqXHR){
			alert('経費データの更新に成功しました');
			findAll();
		},
		error: function(jqXHR,textStatus,errorThrown){
			alert('経費データの更新に失敗しました')
		}
	})
}

function deleteById(applicationId){
	console.log('delete start - id:'+applicationId)
	$.ajax({
		type: "DELETE",
		url: rootUrl+'/'+applicationId,
		success: function(){
			alert("経費登録の削除に成功しました");
			findAll();

		},
		error : function(jqXHR, textStatus, errorThrown){
			alert('社員データの削除に失敗しました');
		}
	});
}

function findDetail(id){
	console.log('findDetail start.-id:'+id);
	$.ajax({
		type: "GET",
		url: rootUrl+'/'+id,
		dataType: "json",
		success: renderDetailTable
	});
}

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
					$('<button>').text("編集").attr("type","button").attr("onclick", "findById("+expenses.applicationId+')')
				));
			row.append($('<td>').append(
					$('<button>').text("削除").attr("type","button").attr("onclick", "deleteById("+expenses.applicationId+')')
				));

			table.append(row);
		});
		$('#expenses').append(table);
		var btn = '<td><button type="button" id="expenseDetail">詳細表示</button></td>';
		table.append(btn);
	}
}

function renderDetailTable(data){
	console.log('返却値',data);
	var headerRow = '<tr><th>申請ID</th><th>申請日</th><th>更新日</th><th>申請者</th><th>タイトル</th><th>金額</th>'+
	'<th>ステータス</th><th>更新者</th></tr>';
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
			row.append($('<td>').text(expenses.name));

			table.append(row);

		});
		$('#expenses').append(table);
	}
}

function renderDetails(expenses){
	console.log('renderDetails start');
	$('.error').text('');
	$('#eaId').val(expenses.applicationId);
	$('#eaDay').val(expenses.applicationDate);
	$('#eaUpDay').val(expenses.updateDate);
	$('#eaName').val(expenses.name);
	$('#eaEName').val(expenses.expensesName);

	$('#eaMoney').val(expenses.amountOfMoney);
	$('#eaSta').val(expenses.statusName);
}

function formToJSON(){
	var expenseId = $('#eaId').val();
	return JSON.stringify({
		"eaId":(eaId == ""? 0 :eaId),
		"eaDay":$("#eaDay").val(),
		"eaUpDay":$("#eaUpDay").val(),
		"eaName ":$("#eaName").val(),
		"eaEName":$("#eaEName").val(),

		"eaMoney":$("#eaMoney").val(),
		"easta":$("#eaSta").val()
	})
}
