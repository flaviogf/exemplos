/** @type {import('@adonisjs/lucid/src/Schema')} */
const Schema = use('Schema');

class LojaSchema extends Schema {
  up() {
    this.create('lojas', (table) => {
      table.increments();
      table.string('nome');
      table.string('tipo');
      table.text('descricao');
      table.string('telefone');
      table.string('site');
      table.string('latitude');
      table.string('longitude');
      table.timestamps();
    });
  }

  down() {
    this.drop('lojas');
  }
}

module.exports = LojaSchema;
