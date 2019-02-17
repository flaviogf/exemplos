//rotas
const usuarioRoute = require('../routes/usuario');

const routes = app => {
    app.use('/usuario', usuarioRoute);
};

module.exports = routes;
