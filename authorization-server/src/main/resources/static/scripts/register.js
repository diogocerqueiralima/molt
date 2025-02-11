window.addEventListener("load", onLoad);

function onLoad() {

    const urlParams = new URLSearchParams(window.location.search);
    const errorParam = urlParams.get("error");

    if (errorParam) {

        switch (errorParam) {
            case "USER_REGISTERED":
                loadAnimation("This user already exists.");
                break;
            case "PASSWORD_LENGTH":
                loadAnimation("The password is too short.");
                break;
            case "PASSWORD_MATCH":
                loadAnimation("Passwords does not match.");
                break;
            default:
                loadAnimation("An unexpected error occurred.");
        }

    }

    const form = document.getElementById("form");

    form.addEventListener("submit", event => {

        const username = document.getElementById("username").value;
        const password = document.getElementById("password").value;
        const confirmPassword = document.getElementById("confirmPassword").value;

        if (password.length < 8) {
            event.preventDefault();
            loadAnimation("The password is too short.");
            return;
        }

        if (password !== confirmPassword) {
            event.preventDefault();
            loadAnimation("Passwords does not match.");
        }

    });

}

function loadAnimation(message) {

    const popup = document.getElementById("popup");
    const msg = document.getElementById("content");

    msg.textContent = message;
    popup.style.animation = "enter 1s forwards";

    setTimeout(() => {
        popup.style.animation = "exit 1s forwards";
    }, 3000);

}