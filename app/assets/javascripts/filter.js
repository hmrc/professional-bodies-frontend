$(document).ready(function(){
  $(".orgGroups").each(function() {
          if ($(this).find("[data-filtered='false']").length < 1){
              $(this).hide();

          } else {
              $(this).show();

          }
      })

  $("#search").on("keyup", function() {
    var value = $(this).val().toLowerCase();


    $(".organisation").filter(function() {
        if ($(this).text().toLowerCase().indexOf(value) > -1) {
            $(this).attr("data-filtered", false).toggle(true);

        } else {
            $(this).attr("data-filtered", true).toggle(false);

        }

    })

    $(".orgGroups").each(function() {
        if ($(this).find("[data-filtered='false']").length < 1){
            $(this).hide();

        } else {
            $(this).show();

        }
    })
  })
})