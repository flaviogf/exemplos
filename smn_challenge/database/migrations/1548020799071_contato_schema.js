/** @type {import('@adonisjs/lucid/src/Schema')} */
const Schema = use('Schema');

class ContatoSchema extends Schema {
  up() {
    this.alter('contatoes', (table) => {
      table.string('imagem');
    });
  }

  down() {
    this.table('contatoes', (table) => {
      table.dropColumn('imagem');
    });
  }
}

module.exports = ContatoSchema;
