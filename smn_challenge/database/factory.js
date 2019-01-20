/*
|--------------------------------------------------------------------------
| Factory
|--------------------------------------------------------------------------
|
| Factories are used to define blueprints for database tables or Lucid
| models. Later you can use these blueprints to seed your database
| with dummy data.
|
*/

/** @type {import('@adonisjs/lucid/src/Factory')} */
const Factory = use('Factory');

Factory.blueprint('App/Models/User', () => ({
  username: 'flavio',
  email: 'flavio@smn.com.br',
  password: 'teste123',
}));

Factory.blueprint('App/Models/Contato', () => ({
  nome: 'Fernando',
  telefone: '(16) 3700-0000',
}));

Factory.blueprint('App/Models/Loja', () => ({
  nome: 'SMN - Tecnologia da informação',
  tipo: 'Fabrica de Software',
  descricao: 'Lorem ipsum tortor diam tincidunt leo congue dolor',
  telefone: '(16) 3409-9514',
  site: 'www.smn.com.br',
  latitude: '40.7143528',
  longitude: '-74.0059731',
}));

Factory.blueprint('App/Models/Atividade', () => ({
  mes: 'Janeiro',
  entrada: 3000.5,
  saida: 1500.0,
}));
