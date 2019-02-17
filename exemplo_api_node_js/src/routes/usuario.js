//dependencias
const Router = require('express').Router;

//controller
const controller = require('../controllers/usuario');

const route = Router();

route.get('/', controller.get);
route.get('/:id', controller.getOne);
route.post('/', controller.post);
route.delete('/:id', controller.deletar);
route.put('/:id', controller.update);

module.exports = route;
