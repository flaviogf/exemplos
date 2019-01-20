/*
|--------------------------------------------------------------------------
| Routes
|--------------------------------------------------------------------------
|
| Http routes are entry points to your web application. You can create
| routes for different URLs and bind Controller actions to them.
|
| A complete guide on routing is available here.
| http://adonisjs.com/docs/4.0/routing
|
*/

/** @type {typeof import('@adonisjs/framework/src/Route/Manager')} */
const Route = use('Route');

Route.post('/autenticacao', 'AutenticacaoController.autentica');

Route.resource('usuarios', 'UsuarioController')
  .apiOnly()
  .except(['show', 'update', 'destroy'])
  .middleware(['auth']);

Route.resource('lojas', 'LojaController')
  .apiOnly()
  .except(['store', 'update', 'destroy'])
  .middleware(['auth']);

Route.resource('contatos', 'ContatoController')
  .apiOnly()
  .middleware(['auth']);
