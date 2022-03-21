const menu_btn = [...document.querySelectorAll(".menu_btn")];

function checkUrlAdress(){
    return window.location.href;
}

window.addEventListener("load", function () {
    const url = checkUrlAdress();
    console.log(url);
    menu_btn.forEach(btn => {
        if(url == "http://localhost:8080/" && btn.dataset.menu == "home") {
            btn.style.color = "#ffb703";
        }
        else if(url == "http://localhost:8080/check" && btn.dataset.menu == "avail") {
            btn.style.color = "#ffb703";
        }
        else if(url == "http://localhost:8080/new" && btn.dataset.menu == "new") {
            btn.style.color = "#ffb703";
        }
    })
})
