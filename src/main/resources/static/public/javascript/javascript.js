if($(window).width() <= 767){
    $('.sidebar li a').mousedown(function() {
        $(this).css('backgroundColor','rgba(0, 0, 0, 0)')
    })
} else {
    $('.sidebar li a').mousedown(function() {
        $(this).css('backgroundColor','rgba(0, 0, 0, 0)')
    })
}

$(window).resize(function() {
    if($(window).width() <= 767) {
        $('.sidebar li a').mousedown(function() {
            $(this).css('backgroundColor','rgba(0, 0, 0, 0)')
        })
    } else {
        $('.sidebar li a').mousedown(function() {
            $(this).css('backgroundColor','rgba(0, 0, 0, 0)')
        })
    }
});

