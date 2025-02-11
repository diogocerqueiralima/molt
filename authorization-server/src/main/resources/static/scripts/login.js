window.addEventListener("load", onLoad);

function onLoad() {

    const urlParams = new URLSearchParams(window.location.search);

    if (urlParams.has("error")) {
        loadAnimation("Login validation failed.");
    }

    const form = document.getElementById("form");

    form.addEventListener("submit", event => {

        const username = document.getElementById("username").value;
        const password = document.getElementById("password").value;

        if (password.length < 8 || regex.test(password)) {
            event.preventDefault();
            loadAnimation("Login validation failed.");
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