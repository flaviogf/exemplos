//dependencias
const Usuario = require('../models/usuario');

const get = (req, res) => {
    const obj = new Usuario();

    obj.find()
        .then(resultado => res.send(resultado));
};

const getOne = (req, res) => {
    const id = req.params.id;

    const obj = new Usuario();

    obj.findOne(id)
        .then(resultado => res.send(resultado))
        .catch(erro => res.send(erro));
};

const post = (req, res) => {
    const nome = req.body.nome;

    const obj = new Usuario();
    obj.insert(nome)
        .then(resultado => res.send(resultado));
};

const deletar = (req, res) => {
    const id = req.params.id;

    const obj = new Usuario();

    obj.deletar(id)
        .then(resultado => res.send(resultado))
        .catch(erro => res.send(erro));
};

const update = (req, res) => {
    const id = req.params.id;
    const nome = req.body.nome;

    const obj = new Usuario();

    obj.update(id, nome)
        .then(resultado => res.send(resultado))
        .catch(erro => res.send(erro));
};

module.exports = { get, getOne, post, deletar, update };
