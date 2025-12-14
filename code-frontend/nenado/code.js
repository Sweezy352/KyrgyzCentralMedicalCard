const first = document.getElementById('first')
const second = document.getElementById('second')
const plus = document.getElementById('plus')
const minus = document.getElementById('minus')
const result = document.getElementById('result')
const calculate = document.getElementById('calculate')
let action = '+'

plus.onclick = function () {
    action = '+'
}

minus.onclick = function () {
    action = '-'
}

function printResult(sum) {
    if (sum < 0) {
        result.style.color = 'red'
    } else {
        result.style.color = 'green'
    }
}

function computeNumbersWithAction(first,second,action) {
    const inp1 = Number(first.value)
    const inp2 = Number(second.value)
    if (action == '+') {
        return inp1 + inp2
    } else {
        return inp1 - inp2
    }
}

calculate.onclick = function () {
    const sum = computeNumbersWithAction(first,second,action)
    printResult(sum)
    result.textContent = sum
}