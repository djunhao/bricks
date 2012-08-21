/**
 * The cards javascript (jquery) application.
 */
$(document).ready(function() {

  $("#newcard-button").click(function () {
    newCard();
  });
  
  $("#hello").click(function () {
    hello();
  });

});

/**
 * We can host multiple cards at once, so this needs to rely
 * on a pure-element UI.
 */
function newCard() {
  card = $("#base-card").clone();

  // set up functions.
  card.setTitle = function(title) {
    card.find("h3").text(title);
  };

  card.setBody = function(body) {
    card.find(".card-text").text(body);
  };

  // Add it to the body & display.
  $("#newcard").append(card);
  card.show();
}

  function hello() {
	$.ajax({
		url: "hello",
		dataType: "json",
		success: function(data){
			$("#msg").append(data.info);
		}
	});
  }