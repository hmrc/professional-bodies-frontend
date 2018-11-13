$(document).ready(function(){
      $("#search").on("keyup", function() {
        var value = $(this).val().toLowerCase();
        $(".organisation").filter(function() {
            if ($(this).text().toLowerCase().indexOf(value) > -1) {
                $(this).attr("data-filtered", false).toggle(true)
            } else {
                $(this).attr("data-filtered", true).toggle(false)
            }
        });
      });
    });