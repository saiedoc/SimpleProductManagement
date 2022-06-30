
class AddedProduct{

    constructor(product_name , product_price) {
        this.product_name = product_name;
        this.product_price = product_price;
    }

}

class UpdatedProduct{

    constructor(product_id , product_name , product_price) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_price = product_price;
    }

}



function loadProducts (tbody) {
    const request = new XMLHttpRequest();
    request.open("GET","http://localhost:8080/products")
    request.onload = () => {
            populateProducts(JSON.parse(request.responseText),tbody)
    }
    request.send()

}

function populateProducts(json,tbody) {
    json.forEach((row) => {

        var tr = document.createElement('tr');
        tr.innerHTML = '<td>' + row.product_id + '</td>' +
            '<td>' + row.product_name + '</td>' +
            '<td>' + row.product_price + '</td>' +
            ' <td>\n' +
            '                        <div class="intable_action_buttons">\n' +
            '                            <button type="button" data-product_id="' + row.product_id + '" data-toggle="modal" data-target="#updateModal" class="btn btn-secondary btn-sm tableedit"><i class="fas fa-edit"></i></button>\n' +
            '                            <button type="button" data-product_id="' + row.product_id + '"  class="btn btn-secondary btn-sm tabletrash"><i class="fas fa-trash"></i></button>\n' +
            '                        </div>\n' +
            '                    </td>'
        ;

        tbody.appendChild(tr);

    });
    assignButtons();
}

function addButtonEvent(){

    const productNameElement = document.querySelector("#add_product_name");
    const productNamePrice = document.querySelector("#add_product_price");
    let addedProduct = new AddedProduct(productNameElement.value,productNamePrice.value);
    var xhr = new XMLHttpRequest();
    xhr.open("POST","http://localhost:8080/products/add", true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send(JSON.stringify(addedProduct));

}


function refreshButtonEvent(){
    location.reload();
}

let currentModifiedProductID;

function saveButtonEvent(){

    const productNameElement = document.querySelector("#edit_product_name");
    const productNamePrice = document.querySelector("#edit_product_price");
    let updatedProduct = new UpdatedProduct(currentModifiedProductID,productNameElement.value,productNamePrice.value);
    var xhr = new XMLHttpRequest();
    xhr.open("PUT","http://localhost:8080/products/update", true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send(JSON.stringify(updatedProduct));

}

function assignButtons(){

    const addButton = document.querySelector("#add_popup_button");
    const refreshButton = document.querySelector("#refreshButton");
    let clickedInTableEditButtons = document.getElementsByClassName("tableedit");
    let clickedInTableTrashButtons = document.getElementsByClassName("tabletrash");
    console.log(clickedInTableEditButtons);
    const saveButton = document.querySelector("#edit_popup_button");
    addButton.addEventListener("click",addButtonEvent);
    refreshButton.addEventListener("click",refreshButtonEvent);
    saveButton.addEventListener("click",saveButtonEvent)
    console.log(clickedInTableEditButtons.length);

    [].forEach.call(clickedInTableEditButtons,editButton => {
        editButton.addEventListener("click", () => {  currentModifiedProductID = editButton.getAttribute("data-product_id"); } )
    });

    [].forEach.call(clickedInTableTrashButtons,trashButton => {
        trashButton.addEventListener("click", () => {
            currentModifiedProductID = trashButton.getAttribute("data-product_id");
            console.log(currentModifiedProductID);
            var xhr = new XMLHttpRequest();
            xhr.open("DELETE","http://localhost:8080/products/deleteById", true);
            xhr.setRequestHeader('Content-Type', 'application/json');
            xhr.send(currentModifiedProductID);
            location.reload();
        });
    });



}


document.addEventListener("DOMContentLoaded",() => {
    const productsBody = document.querySelector("#products_table > tbody");
    loadProducts(productsBody);
});


