$(".answerWrite input[type=submit]").on('click', addAnswer);
$(".qna-comment-slipp-articles").on('click', '.link-delete-article[type=submit]', deleteAnswer);

function addAnswer(e) {
	e.preventDefault();
	var queryString = $("form[name=answer]").serialize();

	$.ajax({
		type : 'post',
		url : '/api/qna/addanswer',
		dataType : 'json',
		data : queryString,
		success : onAddSuccess,
		error : onError
	});
}

function deleteAnswer(e) {
	e.preventDefault();
	var data = {};
	data.answerId = $(e.target).siblings('input').val();

	$.ajax({
		type : 'post',
		url : '/api/qna/deleteAnswer',
		dataType : 'json',
		data: data,
		success : onDeleteSuccess.bind(this),
		error : onError
	});
}

function onAddSuccess(json, status) {
	var answerTemplate = $("#answerTemplate").html();
	var template = answerTemplate.format(json.writer,
			new Date(json.createdDate), json.contents, json.answerId,
			json.answerId);
	$(".qna-comment-slipp-articles").prepend(template);
}

function onDeleteSuccess(json, status) {
	if (json.status == true) {
		$(this).closest("article").remove();
	}
}

function onError(xhr, status) {
	alert("error");
}

String.prototype.format = function() {
	var args = arguments;
	return this.replace(/{(\d+)}/g, function(match, number) {
		return typeof args[number] != 'undefined' ? args[number] : match;
	});
};
