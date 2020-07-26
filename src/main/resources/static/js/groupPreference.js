/**
 * Admin student table datatables configuration
 */
$(document).ready(function () {
    // Setup - add a text input to each footer cell
    $('.preferenceTable tfoot th').each( function () {
        const title = $(this).text();
        if (title === "StudentID" || title === "Preference" || title === "Dislike #1" || title === "Dislike #2") {
            $(this).html( '<input class="colSearch" type="text" placeholder="Search '+title+'" />' );
        }
    });
    
    const table = $('.preferenceTable').DataTable({
        initComplete: function () {
            // Apply the search
            this.api().columns().every(function () {
                const that = this;

                $('input', this.footer()).on('keyup change clear', function () {
                    if (that.search() !== this.value) {
                        that
                            .search(this.value)
                            .draw();
                    }
                });
            });
        },
    });
});

/**
 * show success modal
 * @param data
 */
function successModal(data) {
    let modalSuccessMsg = $('#modal-sm-success');
    let message;
    message = data.toString();
    modalSuccessMsg.find("span.prompt_message").text(message);
    modalSuccessMsg.modal();
}

/**
 * show failure modal
 * @param data
 */
function failureModal(data) {
    let modalFailureMsg = $('#modal-sm-failure');
    let message;
    message = data.toString();
    modalFailureMsg.find("span.prompt_message").text(message);
    modalFailureMsg.modal();
}

/**
 * reload page contents on post success
 */
function refreshPage() {
    let requestURL;
    requestURL = "groupPreference";
    $.get(requestURL, function (data) {
        $('#main_content').html(data);
    })
}

/**
 * refresh page on success modal close
 */
$('#modal-sm-success').on('hidden.bs.modal', function () {
    refreshPage();
});

/**
 * edit rubric content and post function, with modal display for success/failure
 */
$(document).ready(function () {
    $('.studentPreference').on('click', '.submitPref', function() {
        let like = $(this).parent().parent().find('#likePerson').val();
        let dislikeOne = $(this).parent().parent().find('#dislikePerson1').val();
        let dislikeTwo = $(this).parent().parent().find('#dislikePerson2').val();
        let userId = $(this).parent().find('#userID').val();
        console.log(userId);
        let json = { studentId: userId, likePersonId: like, dislikePersonId: dislikeOne,dislikePersonTwoId: dislikeTwo };

        if (like.length <= 10 && dislikeOne.length <= 10 && dislikeTwo.length <= 10) {
            $.ajax({
                type: 'POST',
                url: '/groupPreference/save',
                contentType: "application/json",
                data: JSON.stringify(json),
                success: function (data) {
                    if (data.code === 0) {
                        successModal("Success");
                    } else {
                        failureModal( "Error while posting preference")
                    }
                },
                error: function (xhr, status, error) {
                    failureModal("Error while Posting preference");
                }
            });
        } else {
            failureModal("Please enter a valid ID");
        }
    });
});

