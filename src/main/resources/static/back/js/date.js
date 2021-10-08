let date = new Date()
let yyyy = date.getFullYear()
let MM = (date.getMonth() + 1) < 10 ? ("0" + (date.getMonth() + 1)) : (date.getMonth() + 1)
let dd = date.getDate() < 10 ? ("0" + date.getDate()) : date.getDate()
let HH = date.getHours() < 10 ? ("0" + date.getHours()) : date.getHours()
let mm = date.getMinutes() < 10 ? ("0" + date.getMinutes()) : date.getMinutes()

let curDay = yyyy + '-' + MM + '-' + dd + 'T' + HH + ':' + mm;
$('.measureDate').val(curDay)
console.log(date)