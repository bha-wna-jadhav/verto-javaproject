const minusButton = document.querySelector("#minus");
const plusButton = document.querySelector("#plus");
const addButton = document.querySelector("#add-to-cart");
const cartCount = document.querySelector("#cart-count");
let count = 0;

minusButton.addEventListener("click", () => {
    if (count > 0) {
        count--;
        cartCount.textContent = count;
    }
});

plusButton.addEventListener("click", () => {
    count++;
    cartCount.textContent = count;
});

addButton.addEventListener("click", () => {
    // Your code to add item to cart goes here
});