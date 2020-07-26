// load menu
$(document).ready(function () {
        // menu tree html
        var menuTree = function (arr) {
            var str = '';
            for (const menuItem of arr) {
                // have child list
                if (menuItem.childList.length > 0) {
                    str = str
                        + '<li class="nav-item has-treeview">'
                        + '<a href="' + menuItem.functionUrl + '" class="nav-link" onclick="navClick(this)">'
                        + '<i class="nav-icon fas fa-tachometer-alt"></i>'
                        + '<p>'
                        + menuItem.functionName
                        + '<i class="right fas fa-angle-left"></i>'
                        + '</p>'
                        + '</a>'
                        + '<ul class="nav nav-treeview">'
                        + menuTree(menuItem.childList)
                        + '</ul>'
                        + '</li>';
                } else {
                    str = str + '<li class="nav-item">'
                        + '<a href="' + menuItem.functionUrl + '" class="nav-link"  onclick="navClick(this)">'
                        + '<i class="nav-icon far fa-circle"></i>'
                        + '<p>' + menuItem.functionName + '</p>'
                        + '</a>'
                        + '</li>';
                }
            }
            return str;
        };

        $.post("/menu", function (data) {
            var menuStr = menuTree(data.info);
            $('#menu').html(menuStr);
        })
    }
);

/**
 * menu click events, change main content
 * @param ele
 */
function navClick(ele) {
    // bind event on nav-link
    $this = $(ele);
    $('a.nav-link').removeClass('active');
    $this.addClass('active');
    if ($this.attr('href')) {
        // ajax: get html page and write into main content
        $.get($this.attr('href'), function (data) {
            $('#main_content').html(data);
        })
    }
    event.preventDefault();
}
