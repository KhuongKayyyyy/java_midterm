let listPrices = document.querySelectorAll(".price")
for (let i = 0; i < listPrices.length; i++) {
    console.log()

    let value = listPrices[i].innerText
    let valueTemp = ""

    for (let j = value.length - 1; j >= 0; j = j - 3) {
        let lIndex = j + 1
        let sIndex = j - 2

        let strSub = value.substring(sIndex, lIndex)

        valueTemp = strSub + "," + valueTemp

    }
    // let indexSub = value.length - 3
    // let fPrice = value.substring(0, indexSub)
    // let lPrice = value.substring(indexSub, indexSub + 3)

    // value = fPrice + "," + lPrice
    console.log(valueTemp)

    listPrices[i].innerText = valueTemp.substring(0, valueTemp.length - 1)

}