(() => {
  const button = document.querySelector(".menu-button");
  const navbar = document.querySelector(".navbar");

  function toggleMenu() {
    button.classList.toggle("menu-button--activated");
    navbar.classList.toggle("navbar--opened");
  }

  button.onclick = toggleMenu;
})();
