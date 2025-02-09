window.addEventListener("load", onLoad)

function onLoad() {

    const urlParams = new URLSearchParams(window.location.search)
    const errorParam = urlParams.get("error")

    if (errorParam) {

        switch (errorParam) {
            case "USER_REGISTERED":
                loadErrorAnimation("Este utilizador já existe.")
                break
            case "PASSWORD_LENGTH":
                loadErrorAnimation("A palavra-passe é muito curta.")
                break
            case "PASSWORD_MATCH":
                loadErrorAnimation("As palavras-passe não coincidem.")
                break
            default:
                loadErrorAnimation("Ocorreu um erro inesperado.")
        }

    }

    const form = document.getElementById("form");

    form.addEventListener("submit", event => {

        const password = document.getElementById("password").value;
        const confirmPassword = document.getElementById("confirmPassword").value;

        if (password.length < 8) {
            event.preventDefault()
            loadErrorAnimation("A palavra-passe é muito curta.")
            return
        }

        if (password !== confirmPassword) {
            event.preventDefault()
            loadErrorAnimation("As palavras-passe não coincidem.")
        }

    })

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