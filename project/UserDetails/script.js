function toggleDisplay(id){
    const e = document.getElementById(id)
    e.classList.toggle("non-display")
}
function makeNonDisplay(...ids){
    for(let id of ids){
        console.log(id)
        const e = document.getElementById(id)
        e.classList.add("non-display")
    } 
}


