//Input fields
const firstNameInput = document.querySelector(".firstname_input");
const lastNameInput = document.querySelector(".lastname_input");
const telNumInput = document.querySelector(".phonenumber_input");
const carMarkInput = document.querySelector(".carmark_input");
const carPlateInput = document.querySelector(".carplate_input");
const startDateInput = document.querySelector(".start_date");
const endDateInput = document.querySelector(".end_date");
const form_btn = document.querySelector(".form_btn");

const loadingIconContainer = document.getElementById("loadingIconContainer")

function checkIfEmpty(){
    if(firstNameInput.value == "" || lastNameInput.value == "" || telNumInput.value == "" || carMarkInput.value == "" || carPlateInput.value == "" || startDateInput.value == "" || endDateInput.value == ""){
        console.log("pustee :/")
        return false;
    }
    else {
        loadingIconContainer.classList.remove("hideAlert")

        return true;
    }
}

form_btn.addEventListener("submit", () => {
    console.log("test");
})
//User summary on right side:
const dateOfStartSpan = document.querySelector(".dateOfStart");
const dateOfEndSpan = document.querySelector(".dateOfEnd");
const priceForParkingSpan = document.querySelector(".priceForParkingSpan");
startDateInput.addEventListener("input", function (){
    dateOfStartSpan.innerText = startDateInput.value;
    if(dateOfStartSpan.innerText != "" && dateOfEndSpan.innerText != ""){
        calcDates();
    }
})
endDateInput.addEventListener("input", function (){
    dateOfEndSpan.innerText = endDateInput.value;

    if(dateOfStartSpan.innerText != "" && dateOfEndSpan.innerText != ""){
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

//Alert
const rejectedOrderDueToSpots = document.getElementById("rejectedOrderDueToSpots")
const exitBtnRejectedOrder = document.getElementById("exitBtnRejectedOrder")
exitBtnRejectedOrder.addEventListener("click", function (){
    rejectedOrderDueToSpots.classList.add("hideAlert");
})















