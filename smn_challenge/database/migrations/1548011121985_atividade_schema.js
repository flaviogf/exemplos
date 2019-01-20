/** @type {import('@adonisjs/lucid/src/Schema')} */
const Schema = use('Schema');

class AtividadeSchema extends Schema {
  up() {
    this.create('atividades', (table) => {
      table.increments();
      table.string('mes');
      table.decimal('entrada');
      table.decimal('saida');
      table.integer('loja_id').unsigned();
      table.foreign('loja_id').references('lojas.id');
      table.timestamps();
    });
  }

  down() {
    this.drop('atividades');
  }
}

module.exports = AtividadeSchema;
