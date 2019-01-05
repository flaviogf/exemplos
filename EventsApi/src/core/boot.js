module.exports = (app, io, server) => {
  const porta = process.env.PORT
  server.listen(porta, () => {
    console.log(`[API - ${process.env.NAME}] rodando na porta ${porta}`)
  })
}
