/** @type {import('@adonisjs/lucid/src/Schema')} */
const Schema = use('Schema');

class ContatoSchema extends Schema {
  up() {
    this.create('contatoes', (table) => {
      table.increments();
      table.text('nome')
      table.text('telefone')
      table.integer('user_id').unsigned();
      table.foreign('user_id').references('users.id');
      table.timestamps();
    });
  }

  down() {
    this.drop('contatoes');
  }
}

module.exports = ContatoSchema;
