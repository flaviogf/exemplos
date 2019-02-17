import React from 'react'

import { Pie, Doughnut } from 'react-chartjs-2'

import io from 'socket.io-client'

import { servicoEstatistica } from '../../servicos'

import strings from '../../recursos/strings'
import cores from '../../recursos/cores'

import {
  Cartao,
  Conteudo,
  Topo
} from '../../componentes'

export default class Dashboard extends React.Component {

  state = {
    estatisticaTipoOcorrencia: [],
    estatisticaPeriodo: [],
    estatisticaBairro: []
  }

  constructor(props) {
    super(props)
    this.buscaEstatisticas = this.buscaEstatisticas.bind(this)
  }

  componentDidMount() {
    this.buscaEstatisticas()
    this.configuraSocket()
  }

  buscaEstatisticas() {
    this.buscaEstatisticaTipoOcorrencias()
    this.buscaEstatisticaPeriodo()
    this.buscaEstatisticaBairro()
  }

  configuraSocket() {
    const socket = io.connect(strings.urlSocket, { autoConnect: false }).open()
    socket.on('server', () => console.log('[socket]- conectado'))
    socket.on('servidor_ocorrencias', this.buscaEstatisticas)
    socket.on('disconnect', socket.open)
  }

  async buscaEstatisticaTipoOcorrencias() {
    try {
      const estatisticaTipoOcorrencia = await servicoEstatistica.buscaEstatisticaTipoOcorrencia()
      this.setState({ estatisticaTipoOcorrencia }, () => console.log('[tipo-ocorrencia] - atualizando'))
    } catch (error) {
      console.error(error)
    }
  }

  async buscaEstatisticaPeriodo() {
    try {
      const estatisticaPeriodo = await servicoEstatistica.buscaEstatisticaPeriodo()
      this.setState({ estatisticaPeriodo }, () => console.log('[periodo] - atualizando'))
    } catch (error) {
      console.log(error)
    }
  }

  async buscaEstatisticaBairro() {
    try {
      const estatisticaBairro = await servicoEstatistica.buscaEstatisticaBairro()
      this.setState({ estatisticaBairro }, () => console.log('[bairro] - atualizando'))
    } catch (error) {
      console.log(error)
    }
  }

  get dadosGraficoTipoOcorrencia() {
    return {
      datasets: [{
        backgroundColor: this.state.estatisticaTipoOcorrencia.map(() => cores.corGrafico),
        data: this.state.estatisticaTipoOcorrencia.map(({ total }) => total)
      }],
      labels: this.state.estatisticaTipoOcorrencia.map(({ nomenclatura }) => nomenclatura)
    }
  }

  get dadosGraficoPeriodo() {
    return {
      datasets: [{
        backgroundColor: this.state.estatisticaPeriodo.map(() => cores.corGrafico),
        data: this.state.estatisticaPeriodo.map(({ total }) => total)
      }],
      labels: this.state.estatisticaPeriodo.map(({ nome }) => nome),
    }
  }

  get dadosGraficoBairro() {
    return {
      datasets: [{
        backgroundColor: this.state.estatisticaBairro.map(() => cores.corGrafico),
        data: this.state.estatisticaBairro.map(({ total }) => total)
      }],
      labels: this.state.estatisticaBairro.map(({ nome }) => nome),
    }
  }

  render() {
    return (
      <React.Fragment>
        <Topo titulo="Dashboard" />
        <Conteudo>
          <div className="card-group">
            <Cartao>
              <Pie data={this.dadosGraficoTipoOcorrencia} />
            </Cartao>
            <Cartao>
              <Doughnut data={this.dadosGraficoTipoOcorrencia} />
            </Cartao>
          </div>
          <div className="card-group">
            <Cartao>
              <Pie data={this.dadosGraficoBairro} />
            </Cartao>
            <Cartao>
              <Doughnut data={this.dadosGraficoBairro} />
            </Cartao>
          </div>
          <div className="card-group">
            <Cartao>
              <Pie data={this.dadosGraficoPeriodo} />
            </Cartao>
            <Cartao>
              <Doughnut data={this.dadosGraficoPeriodo} />
            </Cartao>
          </div>
        </Conteudo>
      </React.Fragment>
    )
  }
}
