function onFocus() {
  this.parentNode.parentNode.classList.add("form__group--focus");
}

function onBlur() {
  if (this.value) return;
  this.parentNode.parentNode.classList.remove("form__group--focus");
}

const inputs = document.querySelectorAll(".form__input");

inputs.forEach(it => it.addEventListener("focus", onFocus));

inputs.forEach(it => it.addEventListener("blur", onBlur));
