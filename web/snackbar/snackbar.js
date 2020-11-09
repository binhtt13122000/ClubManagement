function showSnackBar() {
    let x = document.getElementById("snackbar");
    console.log("vcl")
    if(x.innerHTML !== "") {
        x.className = "show";
        setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
    }
}