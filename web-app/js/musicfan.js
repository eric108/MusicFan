/**
 * 
 */

function goToResults() {
    var input = document.getElementById("inputField").value;
    var selected = document.getElementById("selectSearch").value;
    var selectedIndex = document.getElementById("selectIndex").value;

    document.getElementById("search-form").action =selected+'/' + input+'/' + selectedIndex;
    
}