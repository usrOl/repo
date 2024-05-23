document.addEventListener("DOMContentLoaded", function(){
    getRecipes();

    document.getElementById("searchBtn").addEventListener("click", search)
    document.getElementById("close").addEventListener("click", closeModal)
})

//4. Feladat
function getRecipes(){
    fetch('https://dummyjson.com/recipes')
    .then(res => res.json())
    .then(data => loadTable(data.recipes));
}

//4. Feladat
function loadTable(data){
    let tbody = document.getElementById("recipes").getElementsByTagName("tbody")[0];
    tbody.innerHTML = "";

    for(let row of data){
        let newRow = tbody.insertRow();

        newRow.insertCell().innerText = row.id;
        newRow.insertCell().innerText = row.name;
        newRow.insertCell().innerText = row.cookTimeMinutes;
        newRow.insertCell().innerText = row.difficulty;
        newRow.insertCell().innerText = row.caloriesPerServing;

        //7. Feladat
        newRow.addEventListener("click", function(){
            getInstructions(row.id);
        })
    }
}

//5. Feladat
function search(){
    let searchText = document.getElementById("searchText").value
    fetch(`https://dummyjson.com/recipes/search?q=${searchText}`)
    .then(res => res.json())
    .then(data => loadTable(data.recipes));
}

//6. Feladat
function openModal(){
    $("#modal").show();
}
function closeModal(){
    $("#modal").hide();
}

//7. Feladat
function getInstructions(id){
    fetch(`https://dummyjson.com/recipes/${id}`)
    .then(res => res.json())
    .then(data => {
        document.getElementById("instructions").innerText = "";
        for(let step of data.instructions){
            document.getElementById("instructions").innerHTML += `<p>${step}<\p>`;
        }
        openModal();
    });
}

