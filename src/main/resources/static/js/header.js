function toggleMenu() {
    var userInfo = document.getElementById("hmenu");
    userInfo.classList.toggle("mhidden");
};
function toggleInfo() {
    var userInfo = document.getElementById("userinfo");
    if (userInfo.style.display === "none") {
        userInfo.style.display = "block";
    } else {
        userInfo.style.display = "none";
    }
}
function togglemodify() {
    var userInfo = document.getElementById("usermodify");
    if (userInfo.style.display === "none") {
        userInfo.style.display = "block";
    } else {
        userInfo.style.display = "none";
    }
}