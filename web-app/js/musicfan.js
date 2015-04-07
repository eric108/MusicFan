/**
 * 
 */

function goToResults() {
    var input = document.getElementById("inputField").value;
    var selected = document.getElementById("selectSearch").value;
    document.getElementById("search-form").action = 'product/'+ selected+'?artist=' + input;
    }