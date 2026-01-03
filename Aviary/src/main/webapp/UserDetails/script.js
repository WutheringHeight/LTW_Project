function toggleDisplay(id){
    const e = document.getElementById(id)
    if(e)
        e.classList.toggle("non-display")
}
function makeNonDisplay(...ids){
    for(let id of ids){
        console.log(id)
        const e = document.getElementById(id)
        e.classList.add("non-display")
    } 
}
function changeInfo(id){
    let sect = document.getElementById(id)
    let infoSect = sect.querySelector('section')
    let label = infoSect.querySelector('label')
    let info = infoSect.querySelector('p')
    let btn = sect.querySelector('button')

    infoSect.removeChild(info)
    sect.removeChild(btn)

    btn.innerHTML = 'Save'
    btn.setAttribute('onclick','saveInfo(\''+ id +'\')')

    const input = document.createElement('input')
    input.value = info.textContent

    btn.addEventListener('onclick',() => {
        console.log("ahhh")
        updateUserDetail(id,input.value)
    })

    infoSect.appendChild(input)
    sect.appendChild(btn)
}
function saveInfo(id){
    let sect = document.getElementById(id)
    let infoSect = sect.querySelector('section')
    let input = infoSect.querySelector('input')
    let btn = sect.querySelector('button')

    infoSect.removeChild(input)
    sect.removeChild(btn)

    btn = document.createElement('button')
    btn.innerHTML = 'Change'
    btn.classList.add()
    btn.setAttribute('onclick','changeInfo(\''+ id +'\')')

    let info = document.createElement('p')
    info.innerHTML = input.value

    infoSect.appendChild(info)
    sect.appendChild(btn)
}

function updateUserDetail(info,data){
    console.log(info,data)
    fetch('updateUserDetail',{
        method : 'POST',
        headers: {"Content-type": "application/x-www-form-urlencoded"},
        body: info+"="+ data
    })
    console.log("sent")
}


