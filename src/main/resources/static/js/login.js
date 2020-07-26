var $loginForm = $('#loginForm');

$loginForm.submit(function () {
    var json = formToJson($loginForm);
    $.ajax({
        type: 'POST',
        url: '/login',
        data: JSON.stringify(json),
        contentType: "application/json",
        success: function (data, status) {
            console.log(data);
            if (data.code === 0) {
                window.location.href = '/index';
            } else {
                alert(data.message);
            }
        }
    })
    event.preventDefault();
})
