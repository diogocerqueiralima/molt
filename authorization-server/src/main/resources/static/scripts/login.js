window.addEventListener("load", onLoad)

function onLoad() {

    const urlParams = new URLSearchParams(window.location.search)

    if (urlParams.has("error")) {
        loadErrorAnimation("Não foi possível validar o seu login.")
    }

}

function loadErrorAnimation(message) {

    const error = document.getElementById("error");
    const errorMsg = document.getElementById("content")

    errorMsg.textContent = message
    error.style.animation = "enter 1s forwards"

    setTimeout(() => {
        error.style.animation = "exit 1s forwards"
    }, 3000)

}