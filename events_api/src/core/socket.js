module.exports = (app, io) => {
  io.on('connection', (socket) => {
    socket.emit('server', '[IO] cliente conectado')
    console.log('[IO] cliente conectado')
    socket.on('disconnect', (reason) => {
      console.log(`[IO] cliente desconectou motivo: ${reason}`)
    })
    socket.on('error', (reason) => {
      console.log(`[IO] erro ${reason}`)
    })
  })
}
