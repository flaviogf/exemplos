const inputs = document.querySelectorAll(".form__input");

inputs.forEach(it => it.addEventListener("focus", onFocus));
inputs.forEach(it => it.addEventListener("blur", onBlur));

function onFocus() {
  this.parentNode.classList.add("form__group--focus");
}

function onBlur() {
  if (this.value) return;
  this.parentNode.classList.remove("form__group--focus");
}
