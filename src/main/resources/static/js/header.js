const menu_btn = [...document.querySelectorAll(".menu_btn")];
const currentTime = document.querySelector(".currentTime");
function checkUrlAdress(){
    return window.location.href;
}

window.addEventListener("load", function () {
    const url = checkUrlAdress();
    menu_btn.forEach(btn => {
        if(url == "http://localhost:8080/" && btn.dataset.menu == "home") {
            btn.style.color = "#FF9E00";
        }
        else if(url == "http://localhost:8080/check-reservation" && btn.dataset.menu == "avail") {
            btn.style.color = "#FF9E00";
        }
        else if(url == "http://localhost:8080/new-reservation" && btn.dataset.menu == "new") {
            btn.style.color = "#FF9E00";
        }
    })
})


const days = ["Niedziela", "Poniedziałek", "Wtorek", "Środa", "Czwartek", "Piątek", "Sobota"];

function hehe(){
    const date = new Date();
    let day = date.getDate();
    let month = date.getMonth() + 1;
    let hour = date.getHours();
    let min = date.getMinutes();
    let sec = date.getSeconds();
    const year = date.getFullYear();

    if(day < 10){
        day = "0" + day;
    }

    if(month < 10){
        month = "0" + month;
    }

    if(hour < 10) {
        hour = "0" + hour;
    }

    if(min < 10) {
        min = "0" + min;
    }

    if(sec < 10){
        sec = "0" + sec;
    }



    currentTime.innerText = `${hour}` + ":" + `${min}` + ":" + `${sec}` + " " + `${day}` + "/" + `${month}` + "/" + `${year}`;

}

const interval = setInterval(hehe, 1000);
