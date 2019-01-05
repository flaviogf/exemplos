import strings from '../recursos/strings'

export const servicoEstatistica = {
  async buscaEstatisticaTipoOcorrencia() {
    const resposta = await fetch(`${strings.urlApi}/estatistica/tipoOcorrencia`)
    return resposta.json()
  },
  async buscaEstatisticaBairro() {
    const resposta = await fetch(`${strings.urlApi}/estatistica/bairro`)
    return resposta.json()
  },
  async buscaEstatisticaPeriodo() {
    const resposta = await fetch(`${strings.urlApi}/estatistica/periodo`)
    return resposta.json()
  }
}
