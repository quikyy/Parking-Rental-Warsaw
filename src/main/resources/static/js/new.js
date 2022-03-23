const date_end = document.querySelector(".date_end");
const freeSpots_counter_span = document.querySelector(".freeSpots_counter_span")

let max_places = 10;


function showValue(){
    console.log(date_end.value);
}

window.addEventListener("load", function (){
    let counter = parseInt(freeSpots_counter_span.innerText);
    if(counter < max_places / 2) {
        freeSpots_counter_span.style.color = "#d30d0d";
    }
    else if(counter => max_places / 2) {
        freeSpots_counter_span.style.color = "#aacc00";
    }
})