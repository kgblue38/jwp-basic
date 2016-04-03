// $(".qna-comment").on("click", ".answerWrite input[type=submit]", addAnswer);
$(".answerWrite input[type=submit]").click(addAnswer);
$(".qna-comment-slipp-articles").on('click', ".link-delete-article", deleteAnswer);

function addAnswer(e) {
	e.preventDefault();

	var queryString = $("form[name=answer]").serialize();

	$.ajax({
		type : 'post',
		url : '/api/qna/addAnswer',
		data : queryString,
		dataType : 'json',
		error : onError,
		success : onAddSuccess
	});
}

function deleteAnswer(e) {
	e.preventDefault();

	var data = {};
	var $eTarget = $(e.target);
	var answerId = $eTarget.siblings('input[type=hidden]').val();
	data.answerId = answerId;

	$.ajax({
		type : 'post',
		url : '/api/qna/deleteAnswer',
		data : data,
		dataType : 'json',
		error : onError,
		success : onRemoveSuccess.bind(this)
	});
}

function onRemoveSuccess(json, status) {
	if (json.result.status == true) {
		$(this).closest('article').remove();
	}
}

function onAddSuccess(json, status) {
	var answer = json.answer;
	var countOfAnswer = json.countOfAnswer;
	var answerTemplate = $("#answerTemplate").html();
	var template = answerTemplate.format(answer.writer, new Date(
			answer.createdDate), answer.contents, answer.answerId,
			answer.answerId);
	$(".qna-comment-slipp-articles").prepend(template);
	$(".qna-comment-count > strong").text(countOfAnswer);
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