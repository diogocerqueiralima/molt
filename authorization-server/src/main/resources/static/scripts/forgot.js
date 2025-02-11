window.addEventListener("load", onLoad);

function onLoad() {

    const urlParams = new URLSearchParams(window.location.search);

    if (urlParams.has("success")) {
        loadAnimation("An e-mail has been sent to your mailbox.", false);
        return
    }

    if (urlParams.has("error")) {
        loadAnimation("The account was not found.", true)
    }

}

function loadAnimation(message, isError) {

    const popup = document.getElementById("popup");
    const msg = document.getElementById("content");

    if (!isError) {
        popup.style.backgroundColor = "var(--success)"
    }else {
        popup.style.backgroundColor = "var(--error)"
    }

    msg.textContent = message;
    popup.style.animation = "enter 1s forwards";

    setTimeout(() => {
        popup.style.animation = "exit 1s forwards";
    }, 3000);

}
