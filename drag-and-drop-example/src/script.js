(function() {
  document.querySelectorAll('.draggable').forEach(function() {
    this.ondragstart = onDragStart
  })

  const $ = document.querySelector.bind(document)

  $('.dropzone').ondragover = onDragOver

  $('.dropzone').ondrop = onDrop

  function onDragStart(event) {
    event.dataTransfer.setData('text/plain', event.target.id)

    event.target.style.backgroundColor = 'yellow'
  }

  function onDragOver(event) {
    event.preventDefault()
  }

  function onDrop(event) {
    const id = event.dataTransfer.getData('text')

    const element = $(`#${id}`)

    const dropzone = event.target

    dropzone.appendChild(element)

    event.dataTransfer.clearData()
  }
})()
