/*
|--------------------------------------------------------------------------
| LojaSeeder
|--------------------------------------------------------------------------
|
| Make use of the Factory instance to seed database with dummy data or
| make use of Lucid models directly.
|
*/

/** @type {import('@adonisjs/lucid/src/Factory')} */
const Factory = use('Factory');

class LojaSeeder {
  async run() {
    const loja = await Factory.model('App/Models/Loja').create();
    const atividade = await Factory.model('App/Models/Atividade').make();
    await loja.atividades().save(atividade);
  }
}

module.exports = LojaSeeder;
