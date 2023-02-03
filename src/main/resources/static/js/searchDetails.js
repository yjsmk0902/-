const icon = document.querySelector(".details-card__img span");

function starClick(){
    icon.querySelector("i").classList.toggle("fa-regular");
    icon.querySelector("i").classList.toggle("fa-solid");
}

icon.addEventListener("click", starClick);