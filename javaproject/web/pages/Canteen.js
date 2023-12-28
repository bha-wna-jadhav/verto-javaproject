const dropdown = document.querySelector('.dropdown');
const dropdownMenu = document.querySelector('.dropdown-menu');

dropdown.addEventListener('mouseenter', () => {
    dropdownMenu.classList.remove('hidden');
});

dropdown.addEventListener('mouseleave', () => {
    dropdownMenu.classList.add('hidden');
});