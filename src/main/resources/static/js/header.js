const menu_btn = [...document.querySelectorAll(".menu_btn")];

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
