//Input fields
const startDateInput = document.querySelector(".start_date");
const endDateInput = document.querySelector(".end_date");

//User summary on right side:
const dateOfStartSpan = document.querySelector(".dateOfStart");
const dateOfEndSpan = document.querySelector(".dateOfEnd");
const priceForParkingSpan = document.querySelector(".priceForParkingSpan");


function checkIfEmpty(){
    if(startDateInput.value == "" || endDateInput.value == ""){
        alert("puste ziomek")
        return false;
    }
    else {
        return true;
    }
}



startDateInput.addEventListener("input", function (){
    dateOfStartSpan.innerText = startDateInput.value;
    if(dateOfStartSpan != "" && dateOfEndSpan.innerText != ""){
        calcDates();
    }
})
endDateInput.addEventListener("input", function (){
    dateOfEndSpan.innerText = endDateInput.value;
    if(dateOfStartSpan != "" && dateOfEndSpan.innerText != ""){
        calcDates();
    }
})

function calcDates(){
    const _MS_PER_DAY = 1000 * 60 * 60 * 24;
    const PRICE_PER_DAY = 50;
    const startDateFromInput = startDateInput.value;
    const startYear = startDateFromInput.substring(0,4);
    const startMonth = startDateFromInput.substring(5, 7);
    const startDay = startDateFromInput.substring(8,10);

    const endDateFromInput = endDateInput.value;
    const endYear = endDateFromInput.substring(0,4);
    const endMonth = endDateFromInput.substring(5, 7);
    const endDay = endDateFromInput.substring(8,10);

    let startDate = new Date(startYear, startMonth -1 , startDay)
    let endDate = new Date(endYear, endMonth -1 , endDay)

    let diffDays = Math.floor((endDate - startDate) / _MS_PER_DAY);
    if(diffDays > 0) {
        priceForParkingSpan.innerText = `${diffDays * PRICE_PER_DAY} PLN`
    }
    else if(diffDays == 0){
        priceForParkingSpan.innerText = `${PRICE_PER_DAY} PLN`
    }
    else {
        priceForParkingSpan.innerText = "";
    }



}







